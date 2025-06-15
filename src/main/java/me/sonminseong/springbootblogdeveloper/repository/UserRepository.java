package me.sonminseong.springbootblogdeveloper.repository;

import me.sonminseong.springbootblogdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // email로 사용자 정보를 가져옴
    /**
     * 이메일로 사용자를 식별할 수 있다.
     * 따라서 사용자 정보를 가져오기 위해서는 스프링 시큐리티가 이메일을 전달받아야한다.
     * FROM users
     * WHERE email = #{email}
     * jpa는 다음과 같은 쿼리를 실행한다.
     */
}
