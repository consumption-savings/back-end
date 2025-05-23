package com.loanpick.config.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import com.loanpick.auth.oauth.jwt.JwtProvider;
import com.loanpick.user.entity.Gender;
import com.loanpick.user.entity.RegistrationType;
import com.loanpick.user.entity.User;

import io.jsonwebtoken.Claims;

@ActiveProfiles("test")
class JwtProviderTest {
    private String testSecret = "testtesttesttesttesttesttesttesttest";

    @DisplayName("만들어진 User 객체로 Token을 만들 수 있다.")
    @Test
    void createAccessToken() {
        // given
        User user = User.builder().id(1L).username("loanpick").email("loanpick@gmail.com").gender(Gender.MALE)
                .registrationType(RegistrationType.KAKAO).build();
        Date now = new Date();
        Date expiry = new Date(now.getTime() + 3600_000); // 1시간

        JwtProvider jwtProvider = new JwtProvider(testSecret);

        // when
        String token = jwtProvider.createAccessToken(user, now, expiry);
        Claims claims = jwtProvider.parseToken(token);

        // then
        assertEquals(user.getEmail(), claims.get("email"));
    }
}
