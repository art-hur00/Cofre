package br.com.cofre.service;


import br.com.cofre.model.Credencial;
import br.com.cofre.repository.InMemoryCredencialRepository;

import java.util.List;
import java.util.Optional;

public class CredencialService {
    private final InMemoryCredencialRepository repository;

    public CredencialService( InMemoryCredencialRepository repository){
        this.repository = repository;
    }
    public Credencial cadastrar(String site, String user, String senha){
        if(Optional.ofNullable(site).map(String::isBlank).orElse(true)){
            throw new IllegalArgumentException("Nome do site não pode estar vazio.");
        }
        else if(Optional.ofNullable(user).map(String::isBlank).orElse(true)){
            throw new IllegalArgumentException("Nome do usuário não pode estar vazio.");
        }
        else if ( Optional.ofNullable(senha).map(String::isBlank).orElse(true)){
            throw new IllegalArgumentException("Senha não pode ser vazia.");

        }else {
          return repository.adicionar(site.trim(), user.trim(), senha);
        }
    }
    public List<Credencial> buscarPorSite(String site) {
        if (Optional.ofNullable(site).map(String::isBlank).orElse(true)) {
            throw new IllegalArgumentException("Nome do site inválido.");
        }else {
            return repository.buscaPorSite(site.trim());
        }
    }
    public boolean excluirPorId(long id) {
           if(id <= 0){
               throw new IllegalArgumentException();
           }
        return repository.excluirPorId(id);
    }
}
