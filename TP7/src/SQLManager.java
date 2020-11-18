import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SQLManager {
    private Connection connection;
    private Statement statement;

    public SQLManager(String name) throws SQLException {
        connection = DriverManager.getConnection(name);
        statement = connection.createStatement();
        statement.setQueryTimeout(30);

        createTableIfNotExist();
    }

    public void createTableIfNotExist() throws SQLException {
        statement.executeUpdate("create table if not exists etudiants (ID integer, firstName string, lastName string, birthDay Timestamp, birthPlace string, description string)");
    }

    public void insert(Etudiant etudiant) throws Exception {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO etudiants values(?, ?, ?, ?, ?, ?)");

        int i = 0;
        ps.setInt(++i, etudiant.ID);
        ps.setString(++i, etudiant.firstName);
        ps.setString(++i, etudiant.lastName);
        ps.setTimestamp(++i, new Timestamp(etudiant.birthDay.getTime()));
        ps.setString(++i, etudiant.birthPlace);
        ps.setString(++i, etudiant.description);

        ps.executeUpdate();
    }

    public void insertAll(Collection<Etudiant> etudiants) throws Exception {
        connection.setAutoCommit(false);
        try {
            for (Etudiant etudiant : etudiants) {
                insert(etudiant);
            }
            connection.commit();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public List<Etudiant> selectAll() throws SQLException {
        ResultSet rs = statement.executeQuery("select * from etudiants order by ID");
        List<Etudiant> cities = new LinkedList<>();

        while (rs.next()) {
            cities.add(convertToEtudiant(rs));
        }

        return cities;
    }

    public void deleteAll() throws SQLException {
        statement.execute("delete from etudiants");
    }

    public void dropTable() throws SQLException {
        statement.execute("drop table etudiants");
    }

    public void dispose() {
        try {
            dropTable();
        } catch (SQLException ignored) {}

        try {
            statement.close();
        } catch (SQLException ignored) {}

        try {
            connection.close();
        } catch (SQLException ignored) {}
    }

    private Etudiant convertToEtudiant(ResultSet rs) throws SQLException {
        return new Etudiant(rs.getInt("ID"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getDate("birthDay"),
                rs.getString("birthPlace"),
                rs.getString("description"));
    }
}
