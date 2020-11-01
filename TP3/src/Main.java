import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<MetroStop> metroStops = new ArrayList<>();
    private static final Scanner scanner = new Scanner( System.in );
    private static final List<Integer> menuOptions = Arrays.asList(1, 2, 3, 4, 5, 9);
    private static final String menuOptionsText = "1) Afficher" + System.lineSeparator()
                                                + "2) Importer un fichier" + System.lineSeparator()
                                                + "3) Trier par ID" + System.lineSeparator()
                                                + "4) Trier par Rue" + System.lineSeparator()
                                                + "5) Trier par Ville" + System.lineSeparator()
                                                + "9) Quitter" + System.lineSeparator()
                                                + "Votre choix : ";

    public static void main(String[] args) {
        int choice;

        do {
            choice = menu();

            switch (choice) {
                case 1: displayMetroStops(); break;
                case 2: readFile(); break;
                case 3: sortMetroStopByID(); break;
                case 4: sortMetroStopByRoad(); break;
                case 5: sortMetroStopByCity(); break;
            }
        } while (choice != 9);
    }

    private static int menu() {
        int choice;

        System.out.println("********");
        System.out.println("* MENU *");
        System.out.println("********");

        do {
            choice = InputFormatter.getInputPositiveInt(scanner, menuOptionsText);
        } while (!menuOptions.contains(choice));

        return choice;
    }

    private static void displayMetroStops() {
        for (MetroStop metroStop : metroStops) {
            System.out.println(metroStop);
        }
    }

    private static void readFile() {
        String fileName = InputFormatter.getInputString(scanner, "Chemin du fichier : ");
        String separator = InputFormatter.getInputString(scanner, "Séparateur : ");

        metroStops = Files.read(fileName, separator);
    }

    private static void sortMetroStopByID() {
        Sorter.sortMetroStopByID(metroStops);
    }

    private static void sortMetroStopByRoad() {
        Sorter.sortMetroStopByRoad(metroStops);
    }

    private static void sortMetroStopByCity() {
        Sorter.sortMetroStopByCity(metroStops);
    }
}
