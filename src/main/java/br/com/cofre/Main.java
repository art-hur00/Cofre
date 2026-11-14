package br.com.cofre;

import br.com.cofre.infrastructure.DatabaseConnection;
import br.com.cofre.infrastructure.DatabaseInitializer;
import br.com.cofre.repository.SQLiteCredencialRepository;
import br.com.cofre.security.GeradorDeSenhas;
import br.com.cofre.service.CredencialService;
import br.com.cofre.ui.MenuTerminal;

import java.sql.SQLException;

public class Main {
    static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        DatabaseInitializer databaseInitializer =
                new DatabaseInitializer(databaseConnection);

        try {
            databaseInitializer.inicializar();
            System.out.println("Banco inicializado com sucesso!");
        } catch (SQLException e) {
            System.out.println(
                    "Não foi possível inicializar o banco: " + e.getMessage()
            );
            return;
        }

        SQLiteCredencialRepository repository =
                new SQLiteCredencialRepository(databaseConnection);

        CredencialService credencialService =
                new CredencialService(repository);

        GeradorDeSenhas geradorDeSenhas = new GeradorDeSenhas();

        MenuTerminal menuTerminal =
                new MenuTerminal(credencialService, geradorDeSenhas);

        menuTerminal.exibeMenu();
    }
}