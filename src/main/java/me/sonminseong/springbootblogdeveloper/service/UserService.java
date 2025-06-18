package me.sonminseong.springbootblogdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.sonminseong.springbootblogdeveloper.domain.User;
import me.sonminseong.springbootblogdeveloper.dto.AddUserRequest;
import me.sonminseong.springbootblogdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                // 1. 패스워드 암호화
                //.password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .password(encoder.encode(dto.getPassword()))
                .build()).getId();
    }

    // user id로 user 검색하는 메서드
    public User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
