package org.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @NotBlank(message = "지역 ID는 필수입니다")
    @Column(length = 50)
    private String id;

    @NotBlank(message = "지역 이름은 필수입니다")
    @Column(length = 100, nullable = false)
    private String name;

    @NotBlank(message = "행정구역은 필수입니다")
    @Column(length = 50, nullable = false)
    private String province;

    @NotBlank(message = "위험등급은 필수입니다")
    @Column(length = 20, nullable = false)
    private String riskLevel;

    @NotNull(message = "소멸지수는 필수입니다")
    @DecimalMin(value = "0.0", message = "소멸지수는 0 이상이어야 합니다")
    @Column(nullable = false)
    private double extinctionIndex;

    @NotNull(message = "인구감소율은 필수입니다")
    @Column(nullable = false)
    private double populationDecreaseRate;

    @NotNull(message = "정책 수는 필수입니다")
    @Min(value = 0, message = "정책 수는 0 이상이어야 합니다")
    @Column(nullable = false)
    private int numberOfPolicies;

    // 기본 생성자
    public Region() {
    }

    // 모든 필드를 포함한 생성자
    public Region(String id, String name, String province, String riskLevel,
                  double extinctionIndex, double populationDecreaseRate, int numberOfPolicies) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.riskLevel = riskLevel;
        this.extinctionIndex = extinctionIndex;
        this.populationDecreaseRate = populationDecreaseRate;
        this.numberOfPolicies = numberOfPolicies;
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

    @Override
    public String toString() {
        return "Region{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", riskLevel='" + riskLevel + '\'' +
                ", extinctionIndex=" + extinctionIndex +
                ", populationDecreaseRate=" + populationDecreaseRate +
                ", numberOfPolicies=" + numberOfPolicies +
                '}';
    }
}