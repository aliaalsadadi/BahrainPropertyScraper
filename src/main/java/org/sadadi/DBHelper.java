package org.sadadi;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelper {
    private static final Dotenv dotenv = Dotenv.load();

    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    public static void addProperty(Property property) {
        String sql = "INSERT INTO properties (title, price, location, rent_or_sale, property_type, number_of_beds, number_of_baths, sqm_area) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, property.title());
            stmt.setInt(2, property.price());
            stmt.setString(3, property.location());
            stmt.setString(4, property.RentOrSale());
            stmt.setString(5, property.propertyType());
            stmt.setInt(6, property.numberOfBeds());
            stmt.setInt(7, property.numberOfBaths());
            stmt.setInt(8, property.sqmArea());

            stmt.executeUpdate();
            System.out.println("Property added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
