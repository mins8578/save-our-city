package org.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Table(name = "ideas")
public class Idea {

    @Id
    @NotBlank(message = "아이디어 ID는 필수입니다")
    @Column(length = 50)
    private String id;

    @NotBlank(message = "제목은 필수입니다")
    @Column(length = 200, nullable = false)
    private String title;

    @NotBlank(message = "카테고리는 필수입니다")
    @Column(length = 50, nullable = false)
    private String category;

    @NotBlank(message = "내용은 필수입니다")
    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 100)
    private String targetRegion;

    @NotNull
    @Min(value = 0, message = "조회수는 0 이상이어야 합니다")
    @Column(nullable = false)
    private int viewCount = 0;

    @NotNull
    @Min(value = 0, message = "좋아요 수는 0 이상이어야 합니다")
    @Column(nullable = false)
    private int likeCount = 0;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // 등록 시 자동으로 현재 시간 설정
    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    @NotBlank(message = "비밀번호는 필수입니다")
    @Column(length = 255, nullable = false)
    private String anonPasswordHash;

    // 기본 생성자
    public Idea() {
    }

    // 모든 필드를 포함한 생성자
    public Idea(String id, String title, String category, String content,
                String targetRegion, int viewCount, int likeCount, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.content = content;
        this.targetRegion = targetRegion;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
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

    public String getAnonPasswordHash() {
        return anonPasswordHash;
    }

    public void setAnonPasswordHash(String anonPasswordHash) {
        this.anonPasswordHash = anonPasswordHash;
    }

    @Override
    public String toString() {
        return "Idea{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                ", targetRegion='" + targetRegion + '\'' +
                ", viewCount=" + viewCount +
                ", likeCount=" + likeCount +
                ", createdAt=" + createdAt +
                '}';
    }
}