import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JUnitCycleQuiz {

    @BeforeEach
    public void beforeEach() {
        System.out.println("he");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("b");
    }

    @Test
    public void junitQuiz3() {
        System.out.println("first test");
    }

    @Test
    public void junitQuiz4() {
        System.out.println("second test");
    }
}
