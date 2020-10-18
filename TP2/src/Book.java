import java.time.LocalDate;
import java.util.Scanner;

public class Book {
    public String title;
    public float price;
    public LocalDate releaseDate;
    public String category;
    public float readersRating;

    public Book(String title, float price, LocalDate releaseDate, String category, float readersRating) {
        this.title = title;
        this.price = price;
        this.releaseDate = releaseDate;
        this.category = category;
        this.readersRating = readersRating;
    }

    public static Book create(Scanner scanner) {
        String title = InputFormatter.getInputString(scanner, "Titre : ");
        float price = InputFormatter.getInputPositiveFloat(scanner, "Prix : ");
        LocalDate releaseDate = InputFormatter.getInputLocalDate(scanner, "Date de sortie : ");
        String category = InputFormatter.getInputString(scanner, "Catégorie : ");
        float readersRating = InputFormatter.getInputPositiveFloat(scanner, "Note des lecteurs : ");

        return new Book(title, price, releaseDate, category, readersRating);
    }

    @Override
    public String toString() {
        return "Book :"
                + System.lineSeparator() + "- Titre : " + title
                + System.lineSeparator() + "- Prix : " + price
                + System.lineSeparator() + "- Date de sortie : " + releaseDate
                + System.lineSeparator() + "- Catégorie : " + category
                + System.lineSeparator() + "- Note des lecteurs : " + readersRating;
    }
}
