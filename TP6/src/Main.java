import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] argv) {
        if (argv.length != 1) {
            System.err.println("Bad arguments");
            throw new IllegalArgumentException();
        }

        SQLManager sqlManager = null;

        try {
            sqlManager = new SQLManager("jdbc:sqlite:db/TP6.db");
            sqlManager.selectFirstByName(argv[0]);
        } catch(SQLException | IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if (sqlManager != null) {
                sqlManager.dispose();
            }
        }
    }
}
