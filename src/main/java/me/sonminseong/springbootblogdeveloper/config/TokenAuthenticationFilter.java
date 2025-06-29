package me.sonminseong.springbootblogdeveloper.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.sonminseong.springbootblogdeveloper.config.jwt.TokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    /**
     * OncePerRequestFilter : Http Request 한 번만 호출되도록 하는 필터이다.
     * 일반 Filter 클래스를 상속받지 않는 이유는 중복 호출은 불필요한 리소스 낭비자과 성능문제 뿐 아니라
     * 인증, 인과 과정에서 하나의 요청에 대해 불필요한 인증 작업을 두 번이상 진행할 수도 있는 점으로 고려해보았을때
     * 요청 처리 과정에서 결함이 생길수도 있기 때문이다.
     * 또한 스프링에 설정 정보는 필터에서는 못 얻는데 해당 클래스에서는 접근이 자유롭다.
     */
    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException{
        // 요청 헤더의 Authorization 키의 값 조회
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        // 가져온 값에서 접두사 제거
        String token = getAccessToken(authorizationHeader);
        // 가져온 토큰이 유효한지 확인하고, 유효한 때는 인증 정보 설정
        if(tokenProvider.validToken(token)){
            Authentication authentication = tokenProvider.getAuthentication(token);
            // 인증 정보를 관리하는 시큐리티 컨텍스트에 인증 정보를 설정한다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // 인증 정보를 설정하지 않고 다음으로 넘긴다면
        // 스프링 시큐리티는 익명 사용자로 요청을 처리한다.
        // 인가 필터에서 처리한다.

        filterChain.doFilter(request,response);
    }

    private String getAccessToken(String authorizationHeader){ // 접두사 Bearer를 제외
        if(authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)){
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
