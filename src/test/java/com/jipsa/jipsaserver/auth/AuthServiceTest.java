package com.jipsa.jipsaserver.auth;

import com.jipsa.jipsaserver.domain.Member;
import com.jipsa.jipsaserver.domain.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock MemberRepository memberRepository;
    @Mock PasswordEncoder passwordEncoder;
    @Mock JwtUtil jwtUtil;

    @InjectMocks AuthService authService;

    @Test
    @DisplayName("회원가입 성공")
    void signup_success() {
        SignupRequest req = new SignupRequest();
        req.setEmail("test@test.com");
        req.setPassword("password123");
        req.setNickname("테스터");

        given(memberRepository.existsByEmail(req.getEmail())).willReturn(false);
        given(passwordEncoder.encode(req.getPassword())).willReturn("encoded");

        authService.signup(req);

        verify(memberRepository).save(any(Member.class));
    }

    @Test
    @DisplayName("중복 이메일로 회원가입 시 예외 발생")
    void signup_duplicateEmail_throwsException() {
        SignupRequest req = new SignupRequest();
        req.setEmail("duplicate@test.com");
        req.setPassword("password123");
        req.setNickname("테스터");

        given(memberRepository.existsByEmail(req.getEmail())).willReturn(true);

        assertThatThrownBy(() -> authService.signup(req))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 사용 중인 이메일입니다.");
    }

    @Test
    @DisplayName("로그인 성공 시 JWT 토큰 반환")
    void login_success_returnsToken() {
        LoginRequest req = new LoginRequest();
        req.setEmail("test@test.com");
        req.setPassword("password123");

        Member member = Member.builder()
                .email("test@test.com")
                .password("encoded")
                .nickname("테스터")
                .build();

        given(memberRepository.findByEmail(req.getEmail())).willReturn(Optional.of(member));
        given(passwordEncoder.matches(req.getPassword(), member.getPassword())).willReturn(true);
        given(jwtUtil.generateToken(member.getEmail())).willReturn("jwt-token");

        LoginResponse response = authService.login(req);

        assertThat(response.getToken()).isEqualTo("jwt-token");
        assertThat(response.getNickname()).isEqualTo("테스터");
    }

    @Test
    @DisplayName("잘못된 비밀번호로 로그인 시 예외 발생")
    void login_wrongPassword_throwsException() {
        LoginRequest req = new LoginRequest();
        req.setEmail("test@test.com");
        req.setPassword("wrongpassword");

        Member member = Member.builder()
                .email("test@test.com")
                .password("encoded")
                .nickname("테스터")
                .build();

        given(memberRepository.findByEmail(req.getEmail())).willReturn(Optional.of(member));
        given(passwordEncoder.matches(req.getPassword(), member.getPassword())).willReturn(false);

        assertThatThrownBy(() -> authService.login(req))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이메일 또는 비밀번호가 올바르지 않습니다.");
    }
}
