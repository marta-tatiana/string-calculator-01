package codingkata.stringcalculator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
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

    @Test public void
    should_sum_many_numbers() {
        assertThat(calculator.add("5,8,2,3,6,1,-1,0"), is(24));
    }

    @Test public void
    should_allow_for_newlines_as_delimeters() {
        assertThat(calculator.add("7\n1,2"), is(10));
    }

    @Test public void
    should_recognize_when_delimeter_is_provided() {
        assertThat(calculator.isDelimeterProvided("//;\\n1;5;6;7;-1;-5;-6"), is(true));
    }

    @Test public void
    shoud_cut_off_delimeter_definition() {
        assertThat(calculator.cutOffDellimeterDefinition("//;\\n1;5;6;7;-1;-5;-6"), equalTo("1;5;6;7;-1;-5;-6"));
    }

    @Test public void
    should_work_with_provided_delimeter() {
        assertThat(calculator.add("//;\\n1;5;6;7;-1;-5;-6"), is(7));
    }

}
