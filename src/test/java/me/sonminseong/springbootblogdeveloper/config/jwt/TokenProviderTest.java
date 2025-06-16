package me.sonminseong.springbootblogdeveloper.config.jwt;

import io.jsonwebtoken.Jwts;
import me.sonminseong.springbootblogdeveloper.domain.User;
import me.sonminseong.springbootblogdeveloper.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProperties jwtProperties;

    // 1. generateToken() 검증 테스트
    @DisplayName("generateToken(): 유저 정보와 만료 기간을 전달해 토큰을 만들 수 있다.")
    @Test
    void generateToken(){
        // given
        /**
         * 토큰에 유저 정보를 추가하기 위한 테스트 유저를 만든다.
         */
        User testUser = userRepository.save(User.builder()
                .email("user@gmail.com")
                .password("test")
                .build());

        // when
        /**
         * 토큰 제공자의 generateToken() 메서드를 호출해 토큰을 만든다.
         */
        String token = tokenProvider.generateToken(testUser, Duration.ofDays(14));

        // then
        /**
         * jjwt 라이브러리를 사용해 토큰을 복호화합니다. 토큰을 만들 때 클레임으로 넣어둔
         * id 값이 given절에서 만든 유저 id와 동일한지 확인
         */
        Long userId = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id",Long.class);

        assertThat(userId).isEqualTo(testUser.getId());
    }

    // 2. validToken() 검증 테스트
    @DisplayName("validToken(): 만료된 토큰인 때에 유효성 검증에 실패한다.")
    @Test
    void validToken_invalidToken(){
        // given
        /**
         * jjwt 라이브러리를 사용해 토큰을 생성한다.
         * 이때 만료 시간은 1970년 1월 1일부터 현재 시간을 밀리초 단위로 치화한 값에 1000을 빼, 이미 만료된 토큰을 생성
         */
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
                .build()
                .createToken(jwtProperties);

        // when
        /**
         * 토큰 제공자의 validToken() 메서드를 호출해 유효한 토큰인지 검증한 뒤 결괏값을 반환받는다.
         */
        boolean result = tokenProvider.validToken(token);

        // then
        /**
         * 반환값이 false(유효한 토큰이 아님)인 것을 확인합니다.
         */
        assertThat(result).isFalse();
    }

    @DisplayName("validToken(): 유효성 토큰일 때에 유효성 검증에 성공한다.")
    @Test
    void validToken_validToken(){
        // given
        /**
         * jjwt 라이브러리를 사용해 jwt 토큰을 생성한다.
         */
        String token = JwtFactory.withDefaultValues().createToken(jwtProperties);

        // when
        /**
         * 앞서 생성한 토큰이 올바른 토큰인지 확인
         */
        boolean result = tokenProvider.validToken(token);

        // then
        /**
         * 결과 값이 true인지 확인
         */
        assertThat(result).isTrue();
    }

    // 3. getAuthentication() 검증 테스트
    @DisplayName("getAuthentication(): 토큰 기반으로 인증 정보를 가져올 수 있다.")
    @Test
    void getAuthentication(){
        // given
        /**
         * jjwt 라이브러리를 사용해 토큰을 생성
         * 이때 토큰의 제목인 subject는 "user@email.com"라는 값을 사용
         */
        String userEmail = "user@email.com";
        String token = JwtFactory.builder()
                .subject(userEmail)
                .build()
                .createToken(jwtProperties);

        // when
        /**
         * 토큰 제공자의 getAuthentication() 메서드를 호출해 인증 객체를 반환받는다.
         */
        Authentication authentication = tokenProvider.getAuthentication(token);

        // then
        /**
         * 반환받은 인증 객체의 유저 이름을 가져와 given절에서 설정한 subject값인 "user@email.com"과 같은지 확인
         */
        assertThat(((UserDetails) authentication.getPrincipal()).getUsername()).isEqualTo(userEmail);
    }

    // 4. getUserId() 검증 테스트
    @DisplayName("getUserId(): 토큰으로 유저 ID를 가져올 수 있다.")
    @Test
    void getUserId(){
        // given
        /**
         * jjwt 라이브러리를 사용해 토큰을 생성한다.
         * 이때 클레임을 추가한다. 키는 "id", 값은 1이라는 유저 ID이다.
         */
        Long userId = 1L;
        String token = JwtFactory.builder()
                .claims(Map.of("id", userId))
                .build()
                .createToken(jwtProperties);

        // when
        /**
         * 토큰 제공자의 getUserId() 메서드를 호출해 유저 ID를 반환받습니다.
         */
        Long userIdByToken = tokenProvider.getUserId(token);

        // then
        /**
         * 반환받은 유저 ID가 given절에서 설정한 유저 ID값인 1과 같은지 확인
         */
        assertThat(userIdByToken).isEqualTo(userId);
    }
}
