package com.jipsa.jipsaserver.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByMemberIdOrderByCreatedAtDesc(Long memberId);
}
