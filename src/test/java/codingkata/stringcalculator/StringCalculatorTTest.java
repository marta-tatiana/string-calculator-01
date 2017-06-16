package codingkata.stringcalculator;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class StringCalculatorTTest {

    private StringCalculator calculator;

    @Before public void
    init() {
        calculator = new StringCalculator();
    }

    @Test public void
    should_return_zero_when_adding_empty_string() {
        assertThat(calculator.add(""), is(0));
    }

    @Test public void
    should_return_number_when_only_one_added() {
        assertThat(calculator.add("3"), is(3));
    }


    @Test public void
    should_return_the_number_when_only_one_added() {
        assertThat(calculator.add("5"), is(5));
    }

    @Test public void
    should_sum_two_numbers() {
        assertThat(calculator.add("5,6"), is(11));
    }

}
