package org.example.repository;

import org.example.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, String> {

    // 특정 지역명으로 조회
    List<Region> findByName(String name);

    // 특정 도/시로 조회
    List<Region> findByProvince(String province);

    // 위험등급으로 조회
    List<Region> findByRiskLevel(String riskLevel);

    // 소멸지수가 특정 값 이하인 지역 조회
    List<Region> findByExtinctionIndexLessThan(double extinctionIndex);

    // 인구감소율이 특정 값 이상인 지역 조회
    List<Region> findByPopulationDecreaseRateGreaterThan(double populationDecreaseRate);

    // 특정 도/시의 위험등급이 일치하는 지역 조회
    List<Region> findByProvinceAndRiskLevel(String province, String riskLevel);

    List<Region> findByNameContainingIgnoreCase(String name);
}