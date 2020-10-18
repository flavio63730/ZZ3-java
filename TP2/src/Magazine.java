import java.time.LocalDate;
import java.util.Scanner;

public class Magazine extends Book {
    public int periodicity;

    public Magazine(String title, float price, LocalDate releaseDate, String category, float readersRating, int periodicity) {
        super (title, price, releaseDate, category, readersRating);

        this.periodicity = periodicity;
    }

    public Magazine(Book book, int periodicity) {
        this(book.title, book.price, book.releaseDate, book.category, book.readersRating, periodicity);
    }

    public static Magazine create(Scanner scanner) {
        Book book = Book.create(scanner);
        int periodicity = InputFormatter.getInputPositiveInt(scanner, "Périodicité : ");

        return new Magazine(book, periodicity);
    }

    @Override
    public String toString() {
        return "Magazine :"
                + System.lineSeparator() + "- Titre : " + title
                + System.lineSeparator() + "- Prix : " + price
                + System.lineSeparator() + "- Date de sortie : " + releaseDate
                + System.lineSeparator() + "- Catégorie : " + category
                + System.lineSeparator() + "- Note des lecteurs : " + readersRating
                + System.lineSeparator() + "- Périodicité : " + periodicity;
    }
}
