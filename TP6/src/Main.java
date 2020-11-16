import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] argv) {
        if (argv.length != 1) {
            System.err.println("Bad arguments");
            throw new IllegalArgumentException();
        }

        try {
            SQLManager sqlManager = new SQLManager("jdbc:sqlite:db/TP6.db");

            List<City> cities = sqlManager.selectAllSortByTemp();
            for (int i=0; i < cities.size(); ++i) {
                System.out.println(cities.get(i).display("======== N°" + i + " ========"));
            }

            City city = sqlManager.selectFirstByName(argv[0]);
            System.out.println(city.display("======== "+ argv[0] +" ========"));

            sqlManager.dispose();
        } catch(SQLException | IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
