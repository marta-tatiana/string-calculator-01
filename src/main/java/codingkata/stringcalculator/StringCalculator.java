package codingkata.stringcalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private String regex = "//(.*?)\\\\n(.*)";
    private String delimeter = ",";
    private String inputs = null;

    public int add(String arguments) {
        inputs = arguments;

        return inputs.equals("") ? 0
                : handleDelimeter().guardAgainstNewlines().sum();
    }

    private StringCalculator handleDelimeter() {
        if (isDelimeterProvided(inputs)) {
            extractDelimeter(inputs);
            inputs = cutOffDellimeterDefinition(inputs);
        }
        return this;
    }

    private StringCalculator guardAgainstNewlines() {
        inputs = inputs.replace("\n", delimeter);
        return this;
    }

    private void extractDelimeter(String arguments) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(arguments);
        matcher.find();
        delimeter = matcher.group(1);
    }

    private int sum() {
        String[] numbers = inputs.split(delimeter);
        int acc = 0;
        for (String number : numbers) {
            acc += Integer.parseInt(number);
        }
        return acc;
    }


    boolean isDelimeterProvided(String arguments) {
        return arguments.matches(regex);
    }

    String cutOffDellimeterDefinition(String arguments) {
        return arguments.substring(delimeter.length() + 4);
    }


}
