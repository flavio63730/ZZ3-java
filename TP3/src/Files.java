import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Files {
    public static List<MetroStop> read(String fileName, String separator) {
        try (FileReader fileReader = new FileReader(fileName)) {
            return Parser.parse(fileReader, separator);
        } catch (IOException e) {
            Exceptions.displayError(e);
        }

        return new ArrayList<>();
    }
}
