package org.example.service;

import org.example.domain.Region;
import org.example.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RegionService {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    // 모든 지역 조회
    public List<Region> findAllRegions() {
        return regionRepository.findAll();
    }

    // ID로 특정 지역 조회
    public Optional<Region> findRegionById(String id) {
        return regionRepository.findById(id);
    }

    // 특정 도/시의 지역 조회
    public List<Region> findRegionsByProvince(String province) {
        return regionRepository.findByProvince(province);
    }

    // 위험등급별 지역 조회
    public List<Region> findRegionsByRiskLevel(String riskLevel) {
        return regionRepository.findByRiskLevel(riskLevel);
    }

    // 특정 도/시의 특정 위험등급 지역 조회
    public List<Region> findRegionsByProvinceAndRiskLevel(String province, String riskLevel) {
        return regionRepository.findByProvinceAndRiskLevel(province, riskLevel);
    }

    // 소멸지수가 특정 값 이하인 지역 조회
    public List<Region> findRegionsByExtinctionIndexLessThan(double extinctionIndex) {
        return regionRepository.findByExtinctionIndexLessThan(extinctionIndex);
    }

    // 인구감소율이 특정 값 이상인 지역 조회
    public List<Region> findRegionsByPopulationDecreaseRateGreaterThan(double populationDecreaseRate) {
        return regionRepository.findByPopulationDecreaseRateGreaterThan(populationDecreaseRate);
    }

    // 지역 저장
    @Transactional
    public Region saveRegion(Region region) {
        return regionRepository.save(region);
    }

    // 지역 삭제
    @Transactional
    public void deleteRegion(String id) {
        regionRepository.deleteById(id);
    }

    // 이름으로 지역 검색 (부분 검색, 대소문자 무시)
    public List<Region> findRegionsByName(String name) {
        return regionRepository.findByNameContainingIgnoreCase(name);
    }
}