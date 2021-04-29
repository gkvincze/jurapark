package hu.nive.ujratervezes.jurassic;

import java.sql.*;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            System.err.println("Database not reachable.");
        }
        return connection;
    }

    public String checkOverpopulation() {
        String overpopulatedSpecies = "";
        String query = "SELECT breed FROM dinosaur WHERE actual > expected;";

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                overpopulatedSpecies = resultSet.getString("breed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return overpopulatedSpecies;
    }

}
