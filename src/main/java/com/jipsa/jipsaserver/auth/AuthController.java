package com.jipsa.jipsaserver.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/check-nickname")
    public ResponseEntity<Boolean> checkNickname(@RequestParam String nickname) {
        return ResponseEntity.ok(authService.checkNickname(nickname));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PutMapping("/nickname")
    public ResponseEntity<String> updateNickname(
            @AuthenticationPrincipal String email,
            @RequestBody UpdateNicknameRequest request) {
        authService.updateNickname(email, request);
        return ResponseEntity.ok("닉네임이 변경되었습니다.");
    }

    @PutMapping("/password")
    public ResponseEntity<String> updatePassword(
            @AuthenticationPrincipal String email,
            @RequestBody UpdatePasswordRequest request) {
        authService.updatePassword(email, request);
        return ResponseEntity.ok("비밀번호가 변경되었습니다.");
    }

    @DeleteMapping("/withdraw")
    public ResponseEntity<String> withdraw(
            @AuthenticationPrincipal String email) {
        authService.withdraw(email);
        return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
    }
}
