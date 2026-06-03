package com.jipsa.jipsaserver.checklist;

import com.jipsa.jipsaserver.domain.ChecklistRecord;
import com.jipsa.jipsaserver.domain.ChecklistRecordRepository;
import com.jipsa.jipsaserver.domain.Member;
import com.jipsa.jipsaserver.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ChecklistService {

    private final ChecklistRecordRepository checklistRecordRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<String> getCheckedItems(String email) {
        Member member = findMember(email);
        return checklistRecordRepository.findByMemberId(member.getId())
                .stream()
                .map(ChecklistRecord::getItemId)
                .collect(Collectors.toList());
    }

    public boolean toggle(String email, String itemId) {
        Member member = findMember(email);
        Optional<ChecklistRecord> existing =
                checklistRecordRepository.findByMemberIdAndItemId(member.getId(), itemId);
        if (existing.isPresent()) {
            checklistRecordRepository.delete(existing.get());
            return false;
        } else {
            checklistRecordRepository.save(ChecklistRecord.builder()
                    .memberId(member.getId())
                    .itemId(itemId)
                    .build());
            return true;
        }
    }

    private Member findMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }
}
