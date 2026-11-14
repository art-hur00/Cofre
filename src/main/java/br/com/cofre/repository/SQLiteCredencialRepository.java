package br.com.cofre.repository;

import br.com.cofre.infrastructure.DatabaseConnection;
import br.com.cofre.model.Credencial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLiteCredencialRepository implements CredencialRepository {
    private final DatabaseConnection databaseConnection;

    public SQLiteCredencialRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Credencial adicionar(String site, String usuario, String senha) {

        String sql = """
                INSERT INTO credenciais (site, usuario, senha)
                VALUES(?, ?, ?)
                RETURNING id;    
                """;
        try (Connection conexao = databaseConnection.abrirConexao();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setString(1, site);
            statement.setString(2, usuario);
            statement.setString(3, senha);

            try (ResultSet resultado = statement.executeQuery()) {
                if (resultado.next()) {
                    Credencial credencial = new Credencial(resultado.getLong("id"), site, usuario, senha);
                    return credencial;
                }

            }
            throw new IllegalStateException("Banco de dados não retornou ID");

        } catch (SQLException e) {
            throw new IllegalStateException("Nao foi possível cadastrar a credencial", e);
        }
    }

    @Override
    public List<Credencial> buscaPorSite(String site) {

        String sql = """
                SELECT id, site, usuario, senha
                FROM credenciais
                WHERE site = ? COLLATE NOCASE
                ORDER BY id;
                """;

        ArrayList<Credencial> credenciais = new ArrayList<>();

        try (Connection conexao = databaseConnection.abrirConexao();

             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setString(1, site);

            try (ResultSet resultado = statement.executeQuery()) {

                while (resultado.next()) {
                    Credencial credencial = new Credencial(
                            resultado.getLong("id"),
                            resultado.getString("site"),
                            resultado.getString("usuario"),
                            resultado.getString("senha")
                    );
                    credenciais.add(credencial);
                }
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Nao foi possível realizar a consulta", e);

        }
        return credenciais;
    }

    @Override
    public boolean excluirPorId(long id) {

        String sql = """
                DELETE FROM credenciais
                WHERE id = ?;
                """;

        try (Connection conexao = databaseConnection.abrirConexao();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setLong(1, id);
          return statement.executeUpdate() == 1;

        } catch (SQLException e) {

            throw new IllegalStateException("Nao foi possível excluir a conta", e);

        }
    }
}
