package me.sonminseong.springbootdeveloper;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자
/**
 * protected기본 생성자
 * 엔티티는 반드시 기본 생성자가 있어야 하고 접근 제어자는 public 또는 protected여야 한다.
 * (public 보다는 protected가 더안전)
 */
@AllArgsConstructor
@Getter
@Entity // 엔티티로 지정
/**
 * Member 객체를 JPA가 관리하는 엔티티로 지정
 * Member클래스와 실제 데이터베이스의 테이블을 매핑시킨디ㅏ.
 * @Entity의 속성 중에 name을 사용하면 name의 값을 가진 테이블 이름과 매핑되고
 * 테이블 이름을 지정하지 않으면 클래스 이름과 같은 테이블과 ㅍ매핑된다
 * @Entity(name = "member_list")
 */
public class Member {
    @Id // id 필드를 기본키로 지정
    /**
     * Long 타입의 id필드를 테이블의 기본키로 지정
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1씩 증가
    /**
     *  기본키의 생성 방식을 결정한다.
     *  AUTO : 선택한 데이터베이스 방언(dialect)에 따라 방식을 자동으로 선택
     *  IDENTITY : 기본키 생성을 데이터베이스에 위임(= AUTO_INCREMENT)
     *  SEQUENCE : 데이트베이스 시퀀스를 사용해서 키본키를 할당하는 방법 오라클레엇 주로 사용
     *  TABLE : 키 생성 테이블 사용
     */
    @Column(name = "id", updatable = false)
    /**
     *  테이터베이스 컬럼과 필드를 매핑해준다.
     *  name : 필드와 매핑할 컬럼 이름. 설정하지 않으면 필드 이름으로 지정해준다.
     *  nullable : 컬럼의 null 허용 여부, 설정하지 않으면 true(nullable)
     *  columnDefinition : 컬럼 정보 설정. default값을 줄 수 있다.
     */
    private Long id; // db 테이블의 id 컬럼과 매칭

    @Column(name = "name",nullable = false) // name이라는 not null 컬럼과 매핑
    private String name; // db 테이블의 name 컬럼과 매칭

}
