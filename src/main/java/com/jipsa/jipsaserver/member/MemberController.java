package com.jipsa.jipsaserver.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberInfoResponse> getMe(@AuthenticationPrincipal String email) {
        return ResponseEntity.ok(memberService.getMe(email));
    }

    @PutMapping("/age")
    public ResponseEntity<String> updateAge(
            @AuthenticationPrincipal String email,
            @RequestBody Map<String, Object> body) {
        Integer age = (Integer) body.get("age");
        memberService.updateAge(email, age);
        return ResponseEntity.ok("나이가 저장되었습니다.");
    }

    @PutMapping("/guide-step")
    public ResponseEntity<String> updateGuideStep(
            @AuthenticationPrincipal String email,
            @RequestBody Map<String, Object> body) {
        String type = (String) body.get("type");
        int step = (int) body.get("step");
        memberService.updateGuideStep(email, type, step);
        return ResponseEntity.ok("저장되었습니다.");
    }

    @PutMapping("/asset")
    public ResponseEntity<String> updateAsset(
            @AuthenticationPrincipal String email,
            @RequestBody Map<String, String> body) {
        memberService.updateAssetProfile(email, body.get("assetProfile"));
        return ResponseEntity.ok("저장되었습니다.");
    }
}
