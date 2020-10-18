import java.util.*;

public class Main {
    private static final List<Book> books = new ArrayList<>();
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
                case 1: display(); break;
                case 2: displayBook(); break;
                case 3: displayMagazine(); break;
                case 4: addBook(); break;
                case 5: addMagazine(); break;
                case 6: sortAscByTitle(); break;
                case 7: sortDescByReadersRating(); break;
                case 8: sortDescByReleaseDate(); break;
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

    private static void display() {
        System.out.println();
        System.out.println("*************");
        System.out.println("* Affichage *");
        System.out.println("*************");

        for (Book book : books) {
            System.out.println(book);
            System.out.println("---------------");
        }

        System.out.println();
    }

    private static void displayBook() {
        System.out.println();
        System.out.println("************************");
        System.out.println("* Affichage des livres *");
        System.out.println("************************");

        for (Book book : books) {
            if (book.getClass().getName().equals("Book")) {
                System.out.println(book);
                System.out.println("---------------");
            }
        }

        System.out.println();
    }

    private static void displayMagazine() {
        System.out.println();
        System.out.println("***************************");
        System.out.println("* Affichage des magazines *");
        System.out.println("***************************");

        for (Book book : books) {
            if (book.getClass().getName().equals("Magazine")) {
                System.out.println(book);
                System.out.println("---------------");
            }
        }

        System.out.println();
    }

    private static void addBook() {
        books.add(Book.create(scanner));
    }

    private static void addMagazine() {
        books.add(Magazine.create(scanner));
    }

    private static void sortAscByTitle() {
        Collections.sort(books, Comparator.comparing(o -> o.title));
    }

    private static void sortDescByReadersRating() {
        Collections.sort(books, (o1, o2) -> Float.compare(o2.readersRating, o1.readersRating));
    }

    private static void sortDescByReleaseDate() {
        Collections.sort(books, (o1, o2) -> o2.releaseDate.compareTo(o1.releaseDate) );
    }
}