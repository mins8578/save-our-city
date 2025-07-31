package org.example.controller;

import org.example.domain.Idea;
import org.example.domain.Policy;
import org.example.domain.Region;
import org.example.dto.RegionDTO;
import org.example.service.IdeaService;
import org.example.service.PolicyService;
import org.example.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PageController {

    private final RegionService regionService;
    private final PolicyService policyService;
    private final IdeaService ideaService;

    @Autowired
    public PageController(RegionService regionService, PolicyService policyService, IdeaService ideaService) {
        this.regionService = regionService;
        this.policyService = policyService;
        this.ideaService = ideaService;
    }

    @GetMapping("/")
    public String main(Model model) {
        System.out.println("🚀 메인 페이지 컨트롤러 실행됨!");

        // 🚀 전체 지역 → RegionDTO 변환 → 소멸지수 기준 내림차순 정렬 (TOP 3)
        List<RegionDTO> highRiskRegions = regionService.findAllRegions().stream()
                .map(RegionDTO::new)
                .sorted((r1, r2) -> Double.compare(r1.getExtinctionIndex(), r2.getExtinctionIndex())) // 낮은 값이 위험
                .limit(3)
                .collect(Collectors.toList());

        // 🚀 디버그 출력 확인
        System.out.println("🚀 highRiskRegions.size = " + highRiskRegions.size());
        for (RegionDTO region : highRiskRegions) {
            System.out.println("✅ " + region.getName() + " - extinctionIndex: " + region.getExtinctionIndex());
        }

        // 🚀 모델에 DTO 추가
        model.addAttribute("highRiskRegions", highRiskRegions);

        // 페이지 설정
        model.addAttribute("pageTitle", "메인");
        model.addAttribute("currentPage", "main");
        model.addAttribute("includeD3", true);

        return "main";
    }

    @GetMapping("/policies")
    public String showPolicies(Model model) {
        // 정책 데이터 로딩
        List<Policy> policies = policyService.findAllPolicies();
        model.addAttribute("policies", policies);

        // 페이지 설정
        model.addAttribute("pageTitle", "정책 조회");
        model.addAttribute("currentPage", "policies");

        return "policies"; // WEB-INF/views/policies.jsp를 반환
    }

    @GetMapping("/ideas")
    public String showIdeas(Model model) {
        // 아이디어 데이터 로딩
        List<Idea> ideas = ideaService.findAllIdeas();
        model.addAttribute("ideas", ideas);

        // 페이지 설정
        model.addAttribute("pageTitle", "아이디어 게시판");
        model.addAttribute("currentPage", "ideas");

        return "ideas"; // WEB-INF/views/ideas.jsp를 반환
    }

    @GetMapping("/regions")
    public String showRegions(Model model) {
        // 지역 데이터 로딩
        List<Region> regions = regionService.findAllRegions();
        model.addAttribute("regions", regions);

        // 페이지 설정
        model.addAttribute("pageTitle", "지역 통계");
        model.addAttribute("currentPage", "regions");
        model.addAttribute("includeD3", true); // D3.js 라이브러리 포함 (지도 표시용)

        return "regions"; // WEB-INF/views/regions.jsp를 반환
    }

    @GetMapping("/region/{id}")
    public String showRegionDetail(@PathVariable String id, Model model) {
        // 지역 데이터 로딩
        Optional<Region> regionOpt = regionService.findRegionById(id);
        if (!regionOpt.isPresent()) {
            return "redirect:/regions"; // 지역이 없으면 목록으로 리다이렉트
        }

        Region region = regionOpt.get();
        model.addAttribute("region", region);

        // 해당 지역의 정책 로딩
        List<Policy> policies = policyService.findPoliciesByRegion(region.getName());
        model.addAttribute("policies", policies);

        // 해당 지역 관련 아이디어 로딩
        List<Idea> ideas = ideaService.findIdeasByTargetRegion(region.getName());
        model.addAttribute("ideas", ideas);

        // 페이지 설정
        model.addAttribute("pageTitle", region.getName() + " 상세 정보");
        model.addAttribute("currentPage", "region");

        return "region-detail"; // WEB-INF/views/region-detail.jsp를 반환
    }

    @GetMapping("/policy/{id}")
    public String showPolicyDetail(@PathVariable String id, Model model) {
        // 정책 데이터 로딩
        Optional<Policy> policyOpt = policyService.findPolicyById(id);
        if (!policyOpt.isPresent()) {
            return "redirect:/policies"; // 정책이 없으면 목록으로 리다이렉트
        }

        Policy policy = policyOpt.get();
        model.addAttribute("policy", policy);

        // 관련 지역 로딩
        List<Region> relatedRegions = regionService.findRegionsByName(policy.getRegion());
        if (!relatedRegions.isEmpty()) {
            model.addAttribute("region", relatedRegions.get(0));
        }

        // 페이지 설정
        model.addAttribute("pageTitle", policy.getName());
        model.addAttribute("currentPage", "policy");

        return "policy-detail"; // WEB-INF/views/policy-detail.jsp를 반환
    }

    @GetMapping("/idea/{id}")
    public String showIdeaDetail(@PathVariable String id, Model model) {
        // 아이디어 데이터 로딩 및 조회수 증가
        Idea idea = ideaService.incrementViewCount(id);
        if (idea == null) {
            return "redirect:/ideas"; // 아이디어가 없으면 목록으로 리다이렉트
        }

        model.addAttribute("idea", idea);

        // 관련 지역 로딩
        if (idea.getTargetRegion() != null && !idea.getTargetRegion().isEmpty()) {
            List<Region> relatedRegions = regionService.findRegionsByName(idea.getTargetRegion());
            if (!relatedRegions.isEmpty()) {
                model.addAttribute("region", relatedRegions.get(0));
            }
        }

        // 페이지 설정
        model.addAttribute("pageTitle", idea.getTitle());
        model.addAttribute("currentPage", "idea");

        return "idea-detail"; // WEB-INF/views/idea-detail.jsp를 반환
    }
}