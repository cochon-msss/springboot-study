package me.sonminseong.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringBootDeveloperApplication.class,args);
        /**
         * 여기서 스프링 부트가 시작된다.
         * @SpringBottApplication 애너테이션을 추가하면 스프링 부트 사용에 필요한 기본 설정을 해준다.
         * SpringApplication.run() 메서드는 애플리케이션을 실행한다.
         * 첫 번째 인수는 스프링 부트 3 애플리케이션의 메인 클래스로 사용할 클래스
         * 두 번째 인수는 커맨드 라인의 인수들을 전달한다.
         *
         * @SpringBootApplication안에 선언된 어노테이션
         *  - @SpringBootConfiguration
         *      스프링 부트 관련 설정을 나타낸다. @Configuration을 상속해서 만든 어노테이션이다
         *  - @ComponentScan
         *      사용자가 등록한 빈을 읽고 등록하는 어노테이션이다
         *      @Component라는 어노테이션을 가진 클래스들을 찾아 빈으로 등록하는 역할 그렇다고 모든 빈에 @Component만 사용하는 건 아니다.
         *  - @EnableAutoConfiguration
         *      스프링 부트에서 자동 구성을 활성화하는 어노테이션이다. 스프링 부트 서버가 실행될 때 메타 파일을 읽고 정의된 설정들을 자동으로 구성하는 역할
         *
         * */
    }
}
