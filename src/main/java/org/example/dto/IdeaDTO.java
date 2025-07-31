package org.example.dto;

import org.example.domain.Idea;
import java.time.LocalDateTime;

/**
 * 아이디어 정보를 표현하는 DTO 클래스
 */
public class IdeaDTO {
    private String id;
    private String title;
    private String category;
    private String content;
    private String targetRegion;
    private int viewCount;
    private int likeCount;
    private LocalDateTime createdAt;

    // 익명 비밀번호 (평문 입력용)
    private String anonPassword;

    // 기본 생성자
    public IdeaDTO() {
    }

    // 엔티티 변환 생성자
    public IdeaDTO(Idea idea) {
        this.id = idea.getId();
        this.title = idea.getTitle();
        this.category = idea.getCategory();
        this.content = idea.getContent();
        this.targetRegion = idea.getTargetRegion();
        this.viewCount = idea.getViewCount();
        this.likeCount = idea.getLikeCount();
        this.createdAt = idea.getCreatedAt();
    }

    // 모든 필드를 포함한 생성자
    public IdeaDTO(String id, String title, String category, String content,
                   String targetRegion, int viewCount, int likeCount, LocalDateTime createdAt, String anonPassword) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.content = content;
        this.targetRegion = targetRegion;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.anonPassword = anonPassword;
    }

    // DTO를 엔티티로 변환 (비밀번호 해시는 Service에서 처리)
    public Idea toEntity(String anonPasswordHash) {
        Idea idea = new Idea(
                id, title, category, content, targetRegion, viewCount, likeCount, createdAt
        );
        idea.setAnonPasswordHash(anonPasswordHash);
        return idea;
    }

    // Getter와 Setter 메서드
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTargetRegion() {
        return targetRegion;
    }

    public void setTargetRegion(String targetRegion) {
        this.targetRegion = targetRegion;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAnonPassword() {
        return anonPassword;
    }

    public void setAnonPassword(String anonPassword) {
        this.anonPassword = anonPassword;
    }

    @Override
    public String toString() {
        return "IdeaDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                ", targetRegion='" + targetRegion + '\'' +
                ", viewCount=" + viewCount +
                ", likeCount=" + likeCount +
                ", createdAt=" + createdAt +
                ", anonPassword='****'" +
                '}';
    }
}