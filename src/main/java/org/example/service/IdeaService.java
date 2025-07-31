package org.example.service;

import org.example.domain.Idea;
import org.example.repository.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class IdeaService {

    private final IdeaRepository ideaRepository;

    @Autowired
    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    // 모든 아이디어 조회
    public List<Idea> findAllIdeas() {
        return ideaRepository.findAll();
    }

    // ID로 특정 아이디어 조회
    public Optional<Idea> findIdeaById(String id) {
        return ideaRepository.findById(id);
    }

    // 특정 카테고리의 아이디어 조회
    public List<Idea> findIdeasByCategory(String category) {
        return ideaRepository.findByCategory(category);
    }

    // 특정 대상 지역의 아이디어 조회
    public List<Idea> findIdeasByTargetRegion(String targetRegion) {
        return ideaRepository.findByTargetRegion(targetRegion);
    }

    // 제목에 특정 키워드가 포함된 아이디어 조회
    public List<Idea> findIdeasByTitleContaining(String keyword) {
        return ideaRepository.findByTitleContaining(keyword);
    }

    // 내용에 특정 키워드가 포함된 아이디어 조회
    public List<Idea> findIdeasByContentContaining(String keyword) {
        return ideaRepository.findByContentContaining(keyword);
    }

    // 좋아요 수 기준 정렬 조회
    public List<Idea> findIdeasOrderByLikeCount() {
        return ideaRepository.findAllByOrderByLikeCountDesc();
    }

    // 조회수 기준 정렬 조회
    public List<Idea> findIdeasOrderByViewCount() {
        return ideaRepository.findAllByOrderByViewCountDesc();
    }

    // 특정 기간 이후 생성된 아이디어 조회
    public List<Idea> findIdeasCreatedAfter(LocalDateTime dateTime) {
        return ideaRepository.findByCreatedAtAfter(dateTime);
    }

    // 아이디어 등록 (비밀번호 포함)
    @Transactional
    public Idea createIdea(Idea idea, String anonPassword) {
        if (idea.getId() == null || idea.getId().isEmpty()) {
            idea.setId(UUID.randomUUID().toString());
        }
        if (idea.getCreatedAt() == null) {
            idea.setCreatedAt(LocalDateTime.now());
        }

        // 비밀번호 암호화
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode(anonPassword);
        idea.setAnonPasswordHash(hash);

        return ideaRepository.save(idea);
    }

    // 아이디어 저장(수정)
    @Transactional
    public Idea saveIdea(Idea idea) {
        return ideaRepository.save(idea);
    }

    // 아이디어 조회수 증가
    @Transactional
    public Idea incrementViewCount(String id) {
        Optional<Idea> ideaOpt = ideaRepository.findById(id);
        if (ideaOpt.isPresent()) {
            Idea idea = ideaOpt.get();
            idea.setViewCount(idea.getViewCount() + 1);
            return ideaRepository.save(idea);
        }
        return null;
    }

    // 아이디어 좋아요 증가
    @Transactional
    public Idea incrementLikeCount(String id) {
        Optional<Idea> ideaOpt = ideaRepository.findById(id);
        if (ideaOpt.isPresent()) {
            Idea idea = ideaOpt.get();
            idea.setLikeCount(idea.getLikeCount() + 1);
            return ideaRepository.save(idea);
        }
        return null;
    }

    // 아이디어 삭제 (비밀번호 검증)
    @Transactional
    public boolean deleteIdea(String id, String anonPassword) {
        Optional<Idea> ideaOpt = ideaRepository.findById(id);
        if (ideaOpt.isPresent()) {
            Idea idea = ideaOpt.get();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(anonPassword, idea.getAnonPasswordHash())) {
                ideaRepository.deleteById(id);
                return true; // 삭제 성공
            } else {
                return false; // 비밀번호 불일치
            }
        }
        return false; // 존재하지 않음
    }

    // 아이디어 수정 (비밀번호 검증)
    @Transactional
    public Idea updateIdea(String id, Idea updatedIdeaData, String anonPassword) {
        Optional<Idea> ideaOpt = ideaRepository.findById(id);
        if (!ideaOpt.isPresent()) {
            return null;
        }

        Idea idea = ideaOpt.get();

        // 비밀번호 검증
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(anonPassword, idea.getAnonPasswordHash())) {
            return null; // 비밀번호 불일치
        }

        // 수정 필드 적용
        idea.setTitle(updatedIdeaData.getTitle());
        idea.setCategory(updatedIdeaData.getCategory());
        idea.setContent(updatedIdeaData.getContent());
        idea.setTargetRegion(updatedIdeaData.getTargetRegion());

        return ideaRepository.save(idea);
    }
}