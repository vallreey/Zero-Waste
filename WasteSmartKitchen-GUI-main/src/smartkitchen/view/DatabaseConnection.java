/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartkitchen.view;

/**
 *
 * @author ACER
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    protected Connection conn;

    public DatabaseConnection() {
        connectDatabase();
    }

    protected void connectDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/smartkitchen?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
            String user = "root";
            String pass = "";
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal koneksi database:\n" + e.getMessage());
            System.exit(0);
        }
    }

    public Connection getConnection() {
        return conn;
    }
}

