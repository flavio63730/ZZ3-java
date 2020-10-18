import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner( System.in );
    private static final List<Integer> menuOptions = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    private static final String menuOptionsText = "1) Afficher" + System.lineSeparator()
            + "2) Afficher que les livres" + System.lineSeparator()
            + "3) Afficher que les magazines" + System.lineSeparator()
            + "4) Ajouter un livre" + System.lineSeparator()
            + "5) Ajouter un magazine" + System.lineSeparator()
            + "6) Tier par ordre alphabétique" + System.lineSeparator()
            + "7) Tier par note de lecteurs" + System.lineSeparator()
            + "8) Tier par date de sortie" + System.lineSeparator()
            + "9) Quitter" + System.lineSeparator()
            + "Votre choix : ";

    public static void main(String[] args) {
        int choice;

        do {
            choice = menu();

            switch (choice) {
                case 1: readFile(); break;
            }
        } while (choice != 9);
    }

    private static int menu() {
        int choice = 0;

        System.out.println("********");
        System.out.println("* MENU *");
        System.out.println("********");

        while (!menuOptions.contains(choice)) {
            choice = InputFormatter.getInputPositiveInt(scanner, menuOptionsText);
        }

        return choice;
    }

    private static void readFile() {
        System.out.println("ok");
        try {
            List<MetroStop> metroStops = Parser.parse(new FileReader(""), ",");
        }
        catch (Exception e) {

        }
    }
}
