package br.com.cofre.repository;

import br.com.cofre.model.Credencial;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryCredencialRepository {

    private final List<Credencial> credenciais = new ArrayList<>();

    public void adicionar(Credencial credencial){
        credenciais.add(credencial);
    }

    public List<Credencial> buscaPorSite(String site){
        return  credenciais.stream().filter(credencial -> credencial.getSite().equalsIgnoreCase(site)).collect(Collectors.toList());
    }

    public boolean excluirPorId(long id) {
       return credenciais.removeIf(n -> n.getId() == id);
    }

}

