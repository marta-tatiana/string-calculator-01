package codingkata.stringcalculator;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class StringCalculatorTest {

    @Test public void
    should_return_zero_when_adding_empty_string() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.addInt(""), is(0));
    }

}
}