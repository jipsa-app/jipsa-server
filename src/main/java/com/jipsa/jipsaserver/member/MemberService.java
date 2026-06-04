package com.jipsa.jipsaserver.member;

import com.jipsa.jipsaserver.domain.Member;
import com.jipsa.jipsaserver.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberInfoResponse getMe(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return new MemberInfoResponse(
                member.getNickname(),
                member.getEmail(),
                member.getMonthlyStep() != null ? member.getMonthlyStep() : 1,
                member.getJeonseStep() != null ? member.getJeonseStep() : 1,
                member.getSaleStep() != null ? member.getSaleStep() : 1,
                member.getAssetProfile()
        );
    }

    public void updateGuideStep(String email, String type, int step) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        member.updateGuideStep(type, step);
    }

    public void updateAssetProfile(String email, String assetProfileJson) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        member.updateAssetProfile(assetProfileJson);
    }
}
