import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<MetroStop> parse(Reader reader, String separator) throws IOException {
        List<MetroStop> metroStops = new ArrayList<>();
        BufferedReader br = new BufferedReader(reader);
        String line;

        while ((line = br.readLine()) != null) {
            metroStops.add(new MetroStop(line.split(separator)));
        }
        br.close();

        return metroStops;
    }
}
