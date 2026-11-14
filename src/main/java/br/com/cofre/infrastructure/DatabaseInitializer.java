package br.com.cofre.infrastructure;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private final DatabaseConnection databaseConnection;

    public DatabaseInitializer(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;

    }
    public void inicializar() throws SQLException{
        String sql = """
                CREATE TABLE IF NOT EXISTS credenciais (
                                id INTEGER PRIMARY KEY AUTOINCREMENT,
                        site TEXT NOT NULL,
                        usuario TEXT NOT NULL,
                        senha TEXT NOT NULL       
                );
                
                """;

        try(Connection conexao = databaseConnection.abrirConexao();
            Statement statement = conexao.createStatement()){

            statement.executeUpdate(sql);
        }
    }
}
