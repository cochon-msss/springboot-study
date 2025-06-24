package me.sonminseong.springbootblogdeveloper.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("jwt") // 자바 클래스에 프로퍼티값을 가져와서 사용하는 애너테이션
// yml에 정의한 데이터 가져온다. jwt.issuer, jwt.secretkey
public class JwtProperties { // 해당 값들을 변수로 접근하는 데 사용할 클래스
    private String issuer;
    private String secretKey;
}