package br.com.cofre.repository;

import br.com.cofre.model.Credencial;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class InMemoryCredencialRepository {



    private final List<Credencial> credenciais = new ArrayList<>();
    private long nextID =  1;


    public Credencial adicionar(String site,String usuario, String senha ){
        Credencial credencial = new Credencial(nextID, site, usuario, senha);
        credenciais.add(credencial);
        nextID ++;
        return credencial;
    }

    public List<Credencial> buscaPorSite(String site){
        return  credenciais.stream().filter(credencial -> credencial.getSite().equalsIgnoreCase(site)).collect(Collectors.toList());
    }

    public boolean excluirPorId(long id) {
        return credenciais.removeIf(n -> n.getId() == id);
    }

}

