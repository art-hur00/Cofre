package br.com.cofre.model;

import br.com.cofre.repository.InMemoryCredencialRepository;

public class Credencial {
    private  long id;
    private final String site;
    private final String usuario;
    private final String senha;

    public Credencial(long id, String site, String usuario, String senha){
       this.id = id;
        this.site = site;
        this.usuario = usuario;
        this.senha = senha;
    }

    public long getId() {
        return id;
    }

    public String getSite() {
        return site;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }
}
