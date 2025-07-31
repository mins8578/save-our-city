package org.example.repository;

import org.example.domain.Idea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, String> {

    // 특정 카테고리의 아이디어 조회
    List<Idea> findByCategory(String category);

    // 특정 대상 지역의 아이디어 조회
    List<Idea> findByTargetRegion(String targetRegion);

    // 제목에 특정 키워드가 포함된 아이디어 조회
    List<Idea> findByTitleContaining(String keyword);

    // 내용에 특정 키워드가 포함된 아이디어 조회
    List<Idea> findByContentContaining(String keyword);

    // 좋아요 수 기준 정렬 조회
    List<Idea> findAllByOrderByLikeCountDesc();

    // 조회수 기준 정렬 조회
    List<Idea> findAllByOrderByViewCountDesc();

    // 특정 기간 이후 생성된 아이디어 조회
    List<Idea> findByCreatedAtAfter(LocalDateTime dateTime);
}