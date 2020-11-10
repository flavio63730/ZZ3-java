import java.io.IOException;
import java.sql.*;
import java.util.Calendar;

public class SQLManager {
    private static int expiredTimeSecond = 10 * 60;
    Connection connection;
    Statement statement;

    public SQLManager(String name) throws SQLException {
        connection = DriverManager.getConnection(name);
        statement = connection.createStatement();
        statement.setQueryTimeout(30);

        createTableIfNotExist();
    }

    public SQLManager(String name, int expiredTimeSecond) throws SQLException {
        connection = DriverManager.getConnection(name);
        statement = connection.createStatement();
        statement.setQueryTimeout(30);
        SQLManager.expiredTimeSecond = expiredTimeSecond;

        createTableIfNotExist();
    }

    public void createTableIfNotExist() throws SQLException {
        statement.executeUpdate("create table if not exists meteo (ID integer PRIMARY KEY, date Timestamp, name string, temp float, temp_min float, temp_max float, humidity float)");
    }

    public void insert(City city) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT OR REPLACE INTO meteo values(?, ?, ?, ?, ?, ?, ?)");

        ps.setFloat(1 , city.getId());
        ps.setTimestamp(2, new Timestamp(new java.util.Date().getTime()));
        ps.setString(3, city.getName());
        ps.setFloat(4, city.getTemp());
        ps.setFloat(5, city.getTempMin());
        ps.setFloat(6, city.getTempMax());
        ps.setFloat(7, city.getHumidity());

        ps.executeUpdate();
    }

    public void selectAll() throws SQLException {
        displayLoopQuery("select * from meteo order by ID");
    }

    public void selectAllSortByName() throws SQLException {
        displayLoopQuery("select * from meteo order by name");
    }

    public void selectAllSortByTemp() throws SQLException {
        displayLoopQuery("select * from meteo order by temp");
    }

    public void selectFirstByName(String name) throws SQLException, IOException {
        ResultSet rs = statement.executeQuery("select * from meteo where name like '%" + name + "%' limit 1");

        if (rs.next() && isValid(rs)) {
            convertToCity(rs).display("======== "+ name +" ========");
        }
        else {
            City city = WeatherAPI.getWeather(name);
            insert(city);
            city.display("======== NEW ========");
        }
    }

    public void dispose() {
        try {
            if(connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayLoopQuery(String query) throws SQLException {
        ResultSet rs = statement.executeQuery(query);

        for (int i=1; rs.next(); ++i) {
            convertToCity(rs).display("======== N°" + i + " ========");
        }
    }

    private City convertToCity(ResultSet rs) throws SQLException {
        return new City(rs.getInt("ID"), rs.getString("name"), rs.getFloat("temp"), rs.getFloat("temp_min"), rs.getFloat("temp_max"), rs.getFloat("humidity"));
    }

    private boolean isValid(ResultSet rs) throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add(Calendar.MINUTE, -SQLManager.expiredTimeSecond);

        return rs.getTimestamp("date").getTime() > calendar.getTime().getTime();
    }
}
