import org.junit.jupiter.api.*;

public class JUnitCycleTest {
    @BeforeAll // 전체 테스트를 시작하기 전에 1회 실행하므로 메소드는 static 선언
    // DB 연결 시나 테스트 환경 초기화 시 사용
    static void beforeAll() {
        System.out.println("Before All");
    }

    @BeforeEach // 테스트 케이스를 시작하기 전마다 실행
    // 테스트 메소드 내 객체 초기화나 테스트에 필요한 값을 미리 넣을 때 사용
    // 각 인스턴스에 대해 메서드를 호출해야하므로 메서드는 static이 아니어야 한다.
    public void beforeEach() {
        System.out.println("Before Each");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }

    @Test
    public void test3() {
        System.out.println("test3");
    }

    @AfterAll // 전체 테스트를 마치고 종료 전 1회 실행
    static void afterAll() {
        System.out.println("after All");
    }

    @AfterEach // 테스트 케이스 종료 전마다 실행
    public void afterEach() {
        //테스트 이후에 특정 데이터를 삭제해야 하는 경우 사용
        System.out.println("after Each");
    }
}
