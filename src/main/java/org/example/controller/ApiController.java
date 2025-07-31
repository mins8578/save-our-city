package org.example.controller;

import org.example.domain.Idea;
import org.example.domain.Policy;
import org.example.domain.Region;
import org.example.dto.IdeaDTO;
import org.example.dto.PolicyDTO;
import org.example.dto.RegionDTO;
import org.example.service.IdeaService;
import org.example.service.PolicyService;
import org.example.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST API를 제공하는 컨트롤러
 * 주로 JavaScript에서 AJAX 요청을 처리하는 용도로 사용
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    private final RegionService regionService;
    private final PolicyService policyService;
    private final IdeaService ideaService;

    @Autowired
    public ApiController(RegionService regionService, PolicyService policyService, IdeaService ideaService) {
        this.regionService = regionService;
        this.policyService = policyService;
        this.ideaService = ideaService;
    }

    // 지역 API 엔드포인트
    @GetMapping("/regions")
    public ResponseEntity<List<RegionDTO>> getAllRegions() {
        List<RegionDTO> regions = regionService.findAllRegions().stream()
                .map(RegionDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(regions);
    }

    // 🚀 위험 TOP3 지역만 리턴 (태백, 영월, 고성) + extinctionIndex 내림차순
    @GetMapping("/regions/high-risk-top3")
    public ResponseEntity<List<RegionDTO>> getHighRiskTop3Regions() {
        List<RegionDTO> highRiskRegions = regionService.findRegionsByRiskLevel("고위험").stream()
                .map(RegionDTO::new)
                .filter(region -> region.getName().equals("태백")
                        || region.getName().equals("영월")
                        || region.getName().equals("고성"))
                .sorted((r1, r2) -> Double.compare(r2.getExtinctionIndex(), r1.getExtinctionIndex()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(highRiskRegions);
    }

    @GetMapping("/region/{id}")
    public ResponseEntity<RegionDTO> getRegionById(@PathVariable String id) {
        return regionService.findRegionById(id)
                .map(RegionDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 정책 API 엔드포인트
    @GetMapping("/policies")
    public ResponseEntity<List<PolicyDTO>> getAllPolicies() {
        List<PolicyDTO> policies = policyService.findAllPolicies().stream()
                .map(PolicyDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(policies);
    }

    @GetMapping("/policies/region/{region}")
    public ResponseEntity<List<PolicyDTO>> getPoliciesByRegion(@PathVariable String region) {
        List<PolicyDTO> policies = policyService.findPoliciesByRegion(region).stream()
                .map(PolicyDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(policies);
    }

    // 정책 상세보기 → region + name 기반
    @GetMapping("/policy/{region}/{name}")
    public ResponseEntity<PolicyDTO> getPolicyByRegionAndName(@PathVariable String region, @PathVariable String name) {
        Optional<Policy> policyOpt = policyService.findPoliciesByRegion(region).stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();

        return policyOpt
                .map(PolicyDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 아이디어 API 엔드포인트
    @GetMapping("/ideas")
    public ResponseEntity<List<IdeaDTO>> getAllIdeas() {
        List<IdeaDTO> ideas = ideaService.findAllIdeas().stream()
                .map(IdeaDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ideas);
    }

    @GetMapping("/ideas/category/{category}")
    public ResponseEntity<List<IdeaDTO>> getIdeasByCategory(@PathVariable String category) {
        List<IdeaDTO> ideas = ideaService.findIdeasByCategory(category).stream()
                .map(IdeaDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ideas);
    }

    @GetMapping("/idea/{id}")
    public ResponseEntity<IdeaDTO> getIdeaById(@PathVariable String id) {
        Optional<Idea> ideaOpt = ideaService.findIdeaById(id);
        if (!ideaOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // 조회수 증가하지 않고 데이터만 반환
        return ResponseEntity.ok(new IdeaDTO(ideaOpt.get()));
    }

    @PostMapping("/idea/{id}/like")
    public ResponseEntity<IdeaDTO> likeIdea(@PathVariable String id) {
        Idea idea = ideaService.incrementLikeCount(id);
        if (idea == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new IdeaDTO(idea));
    }

    @PostMapping("/ideas")
    public ResponseEntity<IdeaDTO> createIdea(@RequestBody IdeaDTO ideaDTO) {
        // ✅ 비밀번호 가져오기
        String anonPassword = ideaDTO.getAnonPassword();

        // ✅ 엔티티 변환 (비밀번호는 service 에서 처리하므로 빈 값 넣기)
        Idea idea = ideaDTO.toEntity("");

        // ✅ 서비스 호출
        Idea savedIdea = ideaService.createIdea(idea, anonPassword);

        // ✅ 결과 반환
        return ResponseEntity.ok(new IdeaDTO(savedIdea));
    }

    // 아이디어 수정 (비밀번호 검증 포함)
    @PutMapping("/idea/{id}")
    public ResponseEntity<IdeaDTO> updateIdea(@PathVariable String id, @RequestBody IdeaDTO ideaDTO) {
        String anonPassword = ideaDTO.getAnonPassword(); // 평문 비밀번호 전달
        Idea updatedIdea = ideaService.updateIdea(id, ideaDTO.toEntity(""), anonPassword);
        if (updatedIdea == null) {
            return ResponseEntity.badRequest().build(); // 비밀번호 틀림 또는 아이디어 없음
        }
        return ResponseEntity.ok(new IdeaDTO(updatedIdea));
    }

    // 아이디어 삭제 (비밀번호 검증 포함)
    @DeleteMapping("/idea/{id}")
    public ResponseEntity<Void> deleteIdea(@PathVariable String id, @RequestParam String anonPassword) {
        boolean deleted = ideaService.deleteIdea(id, anonPassword);
        if (!deleted) {
            return ResponseEntity.badRequest().build(); // 비밀번호 틀림 또는 아이디어 없음
        }
        return ResponseEntity.ok().build();
    }
}