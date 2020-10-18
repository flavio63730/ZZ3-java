import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;

public class Parser {
    public static ArrayList<MetroStop> parse(Reader reader, String separator) throws Exception {
        ArrayList<MetroStop> metroStops = new ArrayList<>();
        BufferedReader br = new BufferedReader(reader);
        String line;

        while ((line = br.readLine()) != null) {
            metroStops.add(new MetroStop(line.split(separator)));
        }
        br.close();

        return metroStops;
    }
}
