import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JUnitQuiz {
    @Test
    public void junitTest() {
        String name1 = "a";
        String name2 = "b";
        String name3 = "c";

        //변수 null check
        assertThat(name1).isNotNull();
        assertThat(name2).isNotNull();
        assertThat(name3).isNotNull();

        // name1 == name2?
        assertThat(name1.equals(name2)).isTrue();
        assertThat(name1).isEqualTo(name2);

        // name1 !== name3?
        assertThat(name2).isNotEqualTo(name3);
    }

    public void junitTest2() {
        int number1 = 15;
        int number2 = 0;
        int number3 = -5;

        assertThat(number1).isPositive();
        assertThat(number2).isZero();
        assertThat(number3).isNegative();
        assertThat(number1).isGreaterThan(number2);
        assertThat(number3).isLessThan(number2);

    }
}
