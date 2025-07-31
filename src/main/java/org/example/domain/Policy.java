package org.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Lob;

@Entity
@Table(name = "policies")
public class Policy {

    @Id
    @Column(length = 200)
    private String name;  // 정책명 → PK

    @Column(length = 100, nullable = false)
    private String region;

    @Column(length = 50, nullable = false)
    private String policyType;

    @Column(length = 100)
    private String targetAudience;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 500)
    private String pdfUrl;

    // 기본 생성자
    public Policy() {}

    // 모든 필드를 포함한 생성자
    public Policy(String name, String region, String policyType,
                  String targetAudience, String description, String pdfUrl) {
        this.name = name;
        this.region = region;
        this.policyType = policyType;
        this.targetAudience = targetAudience;
        this.description = description;
        this.pdfUrl = pdfUrl;
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

    @Override
    public String toString() {
        return "Policy{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", policyType='" + policyType + '\'' +
                ", targetAudience='" + targetAudience + '\'' +
                ", description='" + description + '\'' +
                ", pdfUrl='" + pdfUrl + '\'' +
                '}';
    }
}