import org.junit.jupiter.api.*;

public class JunitCycleTest {

    /**
     *  전체 테스트를 시작하기 전에 1회 실행하므로 메서드는 static으로 선언
     *  예를 들어 데이터베이스를 연결해야 하거나 테스트 환경을 초기화할 때 사용
     *  전체 테스트 실행 주기에서 한번만 호출되어야 하기 때문에 메서드를 static으로 선언
     */
    @BeforeAll
    static void beforeAll(){
        System.out.println("@BeforeAll");
    }

    /**
     * 테스트 케이스를 시작하기 전마다 실행
     * 예를 들어 테스트 메서드에서 사용하는 객체를 초기화하거나 테스트에 필요한 값을 미리 넣을 때 사용
     * 각 인스턴스에 대해 메서드를 호출해야 하므로 static이 아니어야한다.
     */
    @BeforeEach
    public void beforeEach(){
        System.out.println("@BeforeEach");
    }
    @Test
    public void test1(){
        System.out.println("test1");
    }
    @Test
    public void test2(){
        System.out.println("test2");
    }
    @Test
    public void test3(){
        System.out.println("test3");
    }

    /**
     * 전체 테스트를 마치고 종료하기 전에 1히 실행하므로 메서드는 static으로 선언
     * 예를 들어 데이터베이스 연결을 종료할때나 공통적으로 사용하는 자원을 해제할 때 사용
     */
    @AfterAll
    static void afterAll(){
        System.out.println("@AfterAll");
    }

    /**
     * 각 테스트 케이스를 종료하기 전 매번 실행
     * 예를 들어 테스트 이후에 특정 데이터를 삭제해야하는 경우 사용
     *
     */
    @AfterEach
    public void afterEach(){
        System.out.println("@AfterEach");
    }
}
