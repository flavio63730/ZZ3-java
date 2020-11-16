import java.io.IOException;
import java.sql.*;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class SQLManager {
    private int expiredTimeSecond;
    private Connection connection;
    private Statement statement;

    public SQLManager(String name) throws SQLException {
        this(name, 600);
    }

    public SQLManager(String name, int expiredTimeSecond) throws SQLException {
        connection = DriverManager.getConnection(name);
        statement = connection.createStatement();
        statement.setQueryTimeout(30);
        this.expiredTimeSecond = expiredTimeSecond;

        createTableIfNotExist();
    }

    public void createTableIfNotExist() throws SQLException {
        statement.executeUpdate("create table if not exists meteo (ID integer PRIMARY KEY, date Timestamp, name string, temp float, temp_min float, temp_max float, humidity float)");
    }

    public void insert(City city) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT OR REPLACE INTO meteo values(?, ?, ?, ?, ?, ?, ?)");

        int i = 0;
        ps.setFloat(++i, city.getId());
        ps.setTimestamp(++i, new Timestamp(new java.util.Date().getTime()));
        ps.setString(++i, city.getName());
        ps.setFloat(++i, city.getTemp());
        ps.setFloat(++i, city.getTempMin());
        ps.setFloat(++i, city.getTempMax());
        ps.setFloat(++i, city.getHumidity());

        ps.executeUpdate();
    }

    public List<City> selectAll() throws SQLException {
        return displayLoopQuery("ID");
    }

    public List<City> selectAllSortByName() throws SQLException {
        return displayLoopQuery("name");
    }

    public List<City> selectAllSortByTemp() throws SQLException {
        return displayLoopQuery("temp");
    }

    public City selectFirstByName(String name) throws SQLException, IOException {
        ResultSet rs = statement.executeQuery("select * from meteo where name like '%" + name + "%' limit 1");

        if (rs.next() && isValid(rs)) {
            return convertToCity(rs);
        }

        City city = WeatherAPI.getWeather(name);
        insert(city);

        return city;
    }

    public void deleteAll() throws SQLException {
        statement.execute("delete from meteo");
    }

    public void dropTable() throws SQLException {
        statement.execute("drop table meteo");
    }

    public void dispose() throws SQLException {
        statement.close();
        connection.close();
    }

    private List<City> displayLoopQuery(String query) throws SQLException {
        ResultSet rs = statement.executeQuery("select * from meteo order by " + query);
        List<City> cities = new LinkedList<>();

        while (rs.next()) {
            cities.add(convertToCity(rs));
        }

        return cities;
    }

    private City convertToCity(ResultSet rs) throws SQLException {
        return new City(rs.getInt("ID"),
                        rs.getString("name"),
                        rs.getFloat("temp"),
                        rs.getFloat("temp_min"),
                        rs.getFloat("temp_max"),
                        rs.getFloat("humidity"));
    }

    private boolean isValid(ResultSet rs) throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add(Calendar.SECOND, -expiredTimeSecond);

        return rs.getTimestamp("date").getTime() > calendar.getTime().getTime();
    }
}
