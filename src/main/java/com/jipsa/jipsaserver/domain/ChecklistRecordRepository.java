package com.jipsa.jipsaserver.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChecklistRecordRepository extends JpaRepository<ChecklistRecord, Long> {
    List<ChecklistRecord> findByMemberId(Long memberId);
    Optional<ChecklistRecord> findByMemberIdAndItemId(Long memberId, String itemId);
    boolean existsByMemberIdAndItemId(Long memberId, String itemId);
    void deleteByMemberId(Long memberId);
}
