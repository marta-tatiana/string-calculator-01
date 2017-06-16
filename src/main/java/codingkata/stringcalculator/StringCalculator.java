package codingkata.stringcalculator;

public class StringCalculator {
    public int add(String arguments) {
        if (arguments.equals(""))
            return 0;
        return sum(arguments);
    }

    private int sum(String arguments) {
        String[] numbers = arguments.split(",");
        int acc = 0;
        for (String number : numbers) {
            acc += Integer.parseInt(number);
        }
        return acc;
    }

}
