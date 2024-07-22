package repositorios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.Driver;

public class DriverRepository {
    private String jdbcUrl = "jdbc:mysql://localhost/formula1";
    private String user = "root";
    private String password = "";

    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(jdbcUrl, user, password)) {
            String sql = "SELECT * FROM drivers";  
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int driverId = rs.getInt("driverId");
                String driverRef = ""; // Asignar valores predeterminados si es necesario
                int number = 0;
                String code = "";
                String forename = rs.getString("forename");
                String surname = rs.getString("surname");
                Date dob = null;
                String nationality = "";
                String url = "";
        
                int totalPoints = rs.getInt("totalPoints");

                Driver driver = new Driver(driverId, driverRef, number, code, forename, surname, dob, nationality, url, totalPoints);
                drivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drivers;
    }

    // Método para obtener los conductores ordenados por puntos
    public List<Driver> getDriversOrderedByPoints() {
        List<Driver> drivers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(jdbcUrl, user, password)) {
            String sql = "SELECT d.driverId, d.forename, d.surname, SUM(r.points) as total_points FROM results r JOIN drivers d ON r.driverId = d.driverId GROUP BY d.driverId, d.forename, d.surname ORDER BY total_points DESC";  
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int driverId = rs.getInt("driverId");
                String driverRef = ""; // Asignar valores predeterminados si es necesario
                int number = 0;
                String code = "";
                String forename = rs.getString("forename");
                String surname = rs.getString("surname");
                Date dob = null;
                String nationality = "";
                String url = "";
                int totalPoints = rs.getInt("total_points");

                Driver driver = new Driver(driverId, driverRef, number, code, forename, surname, dob, nationality, url, totalPoints);
                drivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drivers;
    }

   
}
