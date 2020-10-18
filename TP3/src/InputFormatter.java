import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputFormatter {
    public static String getInputString(Scanner scanner, String text) {
        System.out.print(text);
        return scanner.nextLine();
    }

    public static int getInputPositiveInt(Scanner scanner, String text) {
        int value = -1;
        while (value < 0) {
            try {
                System.out.print(text);
                value = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e) {
                Exceptions.displayError(e);
            }
        }
        return value;
    }

    public static float getInputPositiveFloat(Scanner scanner, String text) {
        float value = -1;
        while (value < 0) {
            try {
                System.out.print(text);
                value = Float.parseFloat(scanner.nextLine());
            }
            catch (NumberFormatException e) {
                Exceptions.displayError(e);
            }
        }
        return value;
    }

    public static LocalDate getInputLocalDate(Scanner scanner, String text) {
        LocalDate value = null;
        while (value == null) {
            try {
                System.out.print(text);
                value = LocalDate.parse(scanner.nextLine());
            }
            catch (DateTimeParseException e) {
                Exceptions.displayError(e);
            }
        }
        return value;
    }

    public static int stringToInt(String text, int defaultValue) {
        try {
            return Integer.parseInt(text);
        }
        catch (NumberFormatException e) {
            Exceptions.displayError(e);
        }

        return defaultValue;
    }

    public static double stringToDouble(String text, double defaultValue) {
        try {
            return Double.parseDouble(text);
        }
        catch (NumberFormatException e) {
            Exceptions.displayError(e);
        }

        return defaultValue;
    }
}
