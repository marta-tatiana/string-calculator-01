package codingkata.stringcalculator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class StringCalculator {

    private static final String NEGATIVES_NOT_ALLOWED = "negatives not allowed: ";
    private static final String MESSAGE_DELIMITER = ", ";
    private static final int REGEX_LENGTH = 4;
    private List<String> delimeters = new ArrayList<>(Collections.singletonList(","));
    private String inputs = null;
    private List<Integer> negatives;
    private int sum = 0;
    private List<String> numbers;

    int add(String arguments) {
        inputs = arguments;

        return inputs.equals("") ? 0
                : handleDelimeter()
                .guardAgainstNewlines()
                .splitInput()
                .accumulatePreservingNegatives()
                .throwIfNegatives().sum;
    }

    private StringCalculator handleDelimeter() {
        if (isDelimeterProvided(inputs)) {
            extractDelimeter(inputs);
            inputs = cutOffDellimeterDefinition(inputs);
        }
        return this;
    }

    private StringCalculator guardAgainstNewlines() {
        inputs = inputs.replace("\n", delimeters.get(0));
        return this;
    }

    void extractDelimeter(String arguments) {
        delimeters = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(arguments);
        while (matcher.find()) {
            delimeters.add(arguments.substring(matcher.start() + 1, matcher.end() - 1));
        }
    }

    private StringCalculator splitInput() {
        String delimeter = delimeters.get(0);
        replaceDelimetersWith(delimeter);
        numbers = Arrays.asList(inputs.split(Pattern.quote(delimeter)));
        return this;
    }

    private void replaceDelimetersWith(String delimeter) {
        if (delimeters.size() > 1) {
            Iterator<String> it = delimeters.iterator();
            while (it.hasNext()) {
                inputs = inputs.replace(it.next(), delimeter);
            }
        }
    }

    private StringCalculator throwIfNegatives() {
        if (negativesInInput()) {
            throw new IllegalArgumentException(buildErrorMessage());
        }
        return this;
    }

    private StringCalculator accumulatePreservingNegatives() {
        Map<Boolean, List<Integer>> streams = numbers
                .stream()
                .map(Integer::parseInt)
                .filter(x -> x <= 1000)
                .collect(Collectors.partitioningBy(x -> (x >= 0)));

        sum = streams.get(true)
                .stream()
                .reduce(0, (x,y) -> x+ y);

        negatives = streams.get(false);

        return this;
    }

    private boolean negativesInInput() {
        return negatives.size() > 0;
    }

    private String buildErrorMessage() {
        StringBuilder builder = new StringBuilder(NEGATIVES_NOT_ALLOWED);
        Iterator<Integer> it = negatives.iterator();
        while (it.hasNext()) {
            builder.append(it.next());
            if (it.hasNext()) {
                builder.append(MESSAGE_DELIMITER);
            }
        }
        return builder.toString();
    }

    boolean isDelimeterProvided(String arguments) {
        String regex = "//\\[(.*?)\\]\\\\n(.*)";
        return arguments.matches(regex);
    }

    String cutOffDellimeterDefinition(String arguments) {
        return arguments.substring(countDelimeterLength());
    }

    int countDelimeterLength() {
        int length = REGEX_LENGTH;
        for (String delimeter : delimeters) {
            length += (delimeter.length() + 2);
        }
        return length;
    }

    void setDelimeters(String... passedDelimeters) {
        delimeters = new ArrayList<String>(Arrays.asList(passedDelimeters));
    }

    List<String> getDelimeters() {
        return delimeters;
    }
}
