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
                displayError(e);
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
                displayError(e);
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
                displayError(e);
            }
        }
        return value;
    }

    private static void displayError(Exception e) {
        System.out.println();
        System.out.println("**********************");
        System.out.println("ERROR : NumberFormatException" + e.getClass());
        System.out.println("ERROR MESSAGE : " + e.getMessage());
        System.out.println("**********************");
        System.out.println();
    }
}
