package me.sonminseong.springbootblogdeveloper.config;

import lombok.RequiredArgsConstructor;
import me.sonminseong.springbootblogdeveloper.domain.User;
import me.sonminseong.springbootblogdeveloper.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class OAuth2UserCustomService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        // 요청을 바탕으로 유저 정보를 담은 객체 반환
        OAuth2User user = super.loadUser(userRequest);
        saveOrUpdate(user);
        return user;
    }

    // 유저가 있으면 업데이트, 없으면 유저 생성
    private User saveOrUpdate(OAuth2User oAuth2User){
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        User user = userRepository.findByEmail(email) // 데이터가 조회가 되면 영속 상태 객체 반환
                .map(entity -> entity.update(name)) // 데이터가 있다면 nickname 수정
                .orElse(User.builder() // 없다면 새 User 객체 생성 (비영속 상태)
                        .email(email)
                        .nickname(name)
                        .build());
        return userRepository.save(user);
    }
}
