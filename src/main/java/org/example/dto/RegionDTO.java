package org.example.dto;

import org.example.domain.Region;

/**
 * 지역 정보를 표현하는 DTO 클래스
 */
public class RegionDTO {
    private String id;
    private String name;
    private String province;
    private String riskLevel;
    private double extinctionIndex;
    private double populationDecreaseRate;
    private int numberOfPolicies;

    // 기본 생성자
    public RegionDTO() {
    }

    // 엔티티 변환 생성자
    public RegionDTO(Region region) {
        this.id = region.getId();
        this.name = region.getName();
        this.province = region.getProvince();
        this.riskLevel = region.getRiskLevel();
        this.extinctionIndex = region.getExtinctionIndex();
        this.populationDecreaseRate = region.getPopulationDecreaseRate();
        this.numberOfPolicies = region.getNumberOfPolicies();
    }

    // 모든 필드를 포함한 생성자
    public RegionDTO(String id, String name, String province, String riskLevel,
                     double extinctionIndex, double populationDecreaseRate, int numberOfPolicies) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.riskLevel = riskLevel;
        this.extinctionIndex = extinctionIndex;
        this.populationDecreaseRate = populationDecreaseRate;
        this.numberOfPolicies = numberOfPolicies;
    }

    // DTO를 엔티티로 변환
    public Region toEntity() {
        return new Region(
                id, name, province, riskLevel, extinctionIndex, populationDecreaseRate, numberOfPolicies
        );
    }

    // Getter와 Setter 메서드
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public double getExtinctionIndex() {
        return extinctionIndex;
    }

    public void setExtinctionIndex(double extinctionIndex) {
        this.extinctionIndex = extinctionIndex;
    }

    public double getPopulationDecreaseRate() {
        return populationDecreaseRate;
    }

    public void setPopulationDecreaseRate(double populationDecreaseRate) {
        this.populationDecreaseRate = populationDecreaseRate;
    }

    public int getNumberOfPolicies() {
        return numberOfPolicies;
    }

    public void setNumberOfPolicies(int numberOfPolicies) {
        this.numberOfPolicies = numberOfPolicies;
    }
}