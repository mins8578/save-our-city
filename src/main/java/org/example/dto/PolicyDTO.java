package org.example.dto;

import org.example.domain.Policy;

/**
 * 정책 정보를 표현하는 DTO 클래스
 */
public class PolicyDTO {
    private String name;
    private String region;
    private String policyType;
    private String targetAudience;
    private String description;
    private String pdfUrl;

    // 기본 생성자
    public PolicyDTO() {}

    // 엔티티 변환 생성자
    public PolicyDTO(Policy policy) {
        this.name = policy.getName();
        this.region = policy.getRegion();
        this.policyType = policy.getPolicyType();
        this.targetAudience = policy.getTargetAudience();
        this.description = policy.getDescription();
        this.pdfUrl = policy.getPdfUrl();
    }

    // 모든 필드를 포함한 생성자
    public PolicyDTO(String name, String region, String policyType,
                     String targetAudience, String description, String pdfUrl) {
        this.name = name;
        this.region = region;
        this.policyType = policyType;
        this.targetAudience = targetAudience;
        this.description = description;
        this.pdfUrl = pdfUrl;
    }

    // DTO → 엔티티 변환
    public Policy toEntity() {
        return new Policy(name, region, policyType, targetAudience, description, pdfUrl);
    }

    // Getter & Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getPolicyType() { return policyType; }
    public void setPolicyType(String policyType) { this.policyType = policyType; }

    public String getTargetAudience() { return targetAudience; }
    public void setTargetAudience(String targetAudience) { this.targetAudience = targetAudience; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPdfUrl() { return pdfUrl; }
    public void setPdfUrl(String pdfUrl) { this.pdfUrl = pdfUrl; }
}