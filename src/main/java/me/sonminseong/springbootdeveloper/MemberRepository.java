package me.sonminseong.springbootdeveloper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    /**
     * 사용자 -> 요청 -> memberRepository.java -> 조회 -> DB(member) -> 엔티티(Member.java)
     * -> DB(member) -> 응답 -> memberRepository.java -> 응답 -> 사용자 순
     */
}
