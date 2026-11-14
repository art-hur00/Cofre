package br.com.cofre.infrastructure;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public Connection abrirConexao() throws SQLException {

        return DriverManager.getConnection("jdbc:sqlite:cofre.db");



    }
}
