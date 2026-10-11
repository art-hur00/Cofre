package br.com.cofre;

import br.com.cofre.model.Credencial;
import br.com.cofre.repository.InMemoryCredencialRepository;
import br.com.cofre.service.CredencialService;

import java.util.List;

public class Main {
    static void main(String[] args) {

        InMemoryCredencialRepository rep = new InMemoryCredencialRepository();

        CredencialService credencialService = new CredencialService(rep);

        //cadastrando
        try {
            credencialService.cadastrar("Teste03", "User03", "Site03");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getLocalizedMessage());
        }


        //Procurando
        try {
            System.out.println(credencialService.buscarPorSite("Teste03"));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getLocalizedMessage());
        }

        //Excluido
        System.out.println(credencialService.excluirPorId(1));

        //Imprimindo no terminal
        for (Credencial credencial : rep.buscaPorSite("Teste04")) {
            System.out.println(credencial.getId() + " | " + credencial.getSite() + " | " + credencial.getUsuario());
        }
    }
}
