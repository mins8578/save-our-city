package org.example.service;

import org.example.domain.Policy;
import org.example.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PolicyService {

    private final PolicyRepository policyRepository;

    @Autowired
    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    // 모든 정책 조회
    public List<Policy> findAllPolicies() {
        return policyRepository.findAll();
    }

    // ID로 특정 정책 조회
    public Optional<Policy> findPolicyById(String id) {
        return policyRepository.findById(id);
    }

    // 특정 지역의 정책 조회
    public List<Policy> findPoliciesByRegion(String region) {
        return policyRepository.findByRegion(region);
    }

    // 특정 정책 유형의 정책 조회
    public List<Policy> findPoliciesByPolicyType(String policyType) {
        return policyRepository.findByPolicyType(policyType);
    }

    // 특정 대상의 정책 조회
    public List<Policy> findPoliciesByTargetAudience(String targetAudience) {
        return policyRepository.findByTargetAudience(targetAudience);
    }

    // 지역과 정책 유형으로 정책 조회
    public List<Policy> findPoliciesByRegionAndPolicyType(String region, String policyType) {
        return policyRepository.findByRegionAndPolicyType(region, policyType);
    }

    // 정책명에 특정 키워드가 포함된 정책 조회
    public List<Policy> findPoliciesByNameContaining(String keyword) {
        return policyRepository.findByNameContaining(keyword);
    }

    // 설명에 특정 키워드가 포함된 정책 조회
    public List<Policy> findPoliciesByDescriptionContaining(String keyword) {
        return policyRepository.findByDescriptionContaining(keyword);
    }

    // 정책 저장
    @Transactional
    public Policy savePolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    // 정책 삭제
    @Transactional
    public void deletePolicy(String id) {
        policyRepository.deleteById(id);
    }
}