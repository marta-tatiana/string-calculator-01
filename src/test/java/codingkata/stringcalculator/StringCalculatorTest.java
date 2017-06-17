package codingkata.stringcalculator;

import jdk.Exported;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class StringCalculatorTest {

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
        assertThat(calculator.add("5,8,2,3,6,1,2,0"), is(27));
    }

    @Test public void
    should_allow_for_newlines_as_delimeters() {
        assertThat(calculator.add("7\n1,2"), is(10));
    }

    @Test public void
    should_recognize_when_delimeter_is_provided() {
        assertThat(calculator.isDelimeterProvided("//[;]\\n1;5;6;7;1;5;6"), is(true));
    }

    @Test public void
    shoud_cut_off_delimeter_definition() {
        assertThat(calculator.cutOffDellimeterDefinition("//[;]\\n1;5;6;7;-1;-5;-6"), equalTo("1;5;6;7;-1;-5;-6"));
    }

    @Test public void
    should_work_with_provided_delimeter() {
        assertThat(calculator.add("//[;]\\n1;5;6;7;1;5;6"), is(31));
    }

    @Test(expected=IllegalArgumentException.class) public void
    should_throw_exception_on_negative_number() {
        calculator.add("-5");
    }

    @Test public void
    should_have_exception_message_with_negative() {
        try {
            calculator.add("-7");
        } catch (IllegalArgumentException exception) {
            String message = exception.getMessage();
            assertThat(message, equalTo("negatives not allowed: -7"));
        }
    }

    @Test public void
    should_have_all_negatives_in_exception_message() {
        try {
            calculator.add("-7,8,9,-3");
        } catch (IllegalArgumentException exception) {
            String message = exception.getMessage();
            assertThat(message, equalTo("negatives not allowed: -7, -3"));
        }
    }

    @Test public void
    should_allow_for_any_length_delimeter() {
        assertThat(calculator.add("//[aaa]\\n1aaa2aaa3"), is(6));
    }

    @Test public void
    should_allow_for_metacharacters_int_delimeter() {
        assertThat(calculator.add("//[**]\\n1**2**3"), is(6));
    }

    @Test public void
    should_allow_for_multiple_one_char_delimeters() {
        assertThat(calculator.add("//[*][^]\\n1^3*3"), is(7));
    }

    @Test public void
    should_allow_for_multiple_many_chars_delimeters() {
        assertThat(calculator.add("//[*!*][^^^]\\n1*!*3^^^3"), is(7));
    }

    @Test public void
    should_ignore_inputs_over_1000() {
        assertThat(calculator.add("1001,2"), is(2));
    }

    @Test public void
    should_return_default_delimeter_length() {
        assertThat(calculator.countDelimeterLength(), is(7));
    }

    @Test public void
    should_correctly_count_delimeter_length_for_many_delimeters() {
        calculator.setDelimeters("***", "aa");
        assertThat(calculator.countDelimeterLength(), is(13));
    }

    @Test public void
    should_extract_delimeters() {
        calculator.extractDelimeter("//[*][c]\\n1^3*3");
        assertThat(calculator.getDelimeters().size(), is(2));
        assertThat(calculator.getDelimeters().get(0), equalTo("*"));
        assertThat(calculator.getDelimeters().get(1), equalTo("c"));
    }
}
