package br.com.cofre.repository;

import br.com.cofre.model.Credencial;

import java.util.List;

public interface CredencialRepository {

     Credencial adicionar(String site, String usuario, String senha );

     List<Credencial> buscaPorSite(String site);


     boolean excluirPorId(long id);
}
