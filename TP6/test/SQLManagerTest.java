import junit.framework.TestCase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SQLManagerTest extends TestCase {
    private SQLManager init() throws SQLException, IOException {
        SQLManager sqlManager = new SQLManager("jdbc:sqlite:db/TP6_test.db");

        sqlManager.createTableIfNotExist();
        sqlManager.insert(WeatherAPI.getWeather("Plauzat"));
        sqlManager.insert(WeatherAPI.getWeather("Clermont-Ferrand"));

        return sqlManager;
    }

    private void end(SQLManager sqlManager) throws SQLException {
        sqlManager.deleteAll();
        sqlManager.dropTable();
        sqlManager.dispose();
    }

    private void verify(int[] expectedIds, String[] expectedNames, List<City> cities) {
        assertEquals(expectedIds.length, cities.size());
        for (int i=0; i < cities.size(); ++i) {
            assertEquals(expectedIds[i], cities.get(i).getId());
            assertEquals(expectedNames[i], cities.get(i).getName());
        }
    }

    public void testSelectAll() {
        // Arrange
        int[] expectedIds = {2986827, 3024634};
        String[] expectedNames = {"Plauzat", "Arrondissement de Clermont-Ferrand"};

        try {
            SQLManager sqlManager = init();

            // Act
            List<City> cities = sqlManager.selectAll();

            // Assert
            verify(expectedIds, expectedNames, cities);

            end(sqlManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testSelectAllSortByName() {
        // Arrange
        int[] expectedIds = {3024634, 2986827};
        String[] expectedNames = {"Arrondissement de Clermont-Ferrand", "Plauzat"};

        try {
            SQLManager sqlManager = init();

            // Act
            List<City> cities = sqlManager.selectAllSortByName();

            // Assert
            verify(expectedIds, expectedNames, cities);

            end(sqlManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
