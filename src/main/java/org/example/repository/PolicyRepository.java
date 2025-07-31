package org.example.repository;

import org.example.domain.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, String> {

    // 특정 지역의 정책 조회
    List<Policy> findByRegion(String region);

    // 특정 정책 유형으로 조회
    List<Policy> findByPolicyType(String policyType);

    // 특정 대상 조회
    List<Policy> findByTargetAudience(String targetAudience);

    // 지역과 정책 유형으로 조회
    List<Policy> findByRegionAndPolicyType(String region, String policyType);

    // 정책명에 특정 키워드가 포함된 정책 조회
    List<Policy> findByNameContaining(String keyword);

    // 설명에 특정 키워드가 포함된 정책 조회
    List<Policy> findByDescriptionContaining(String keyword);
}