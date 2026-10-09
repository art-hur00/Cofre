package br.com.cofre;

import br.com.cofre.model.Credencial;
import br.com.cofre.repository.InMemoryCredencialRepository;

import java.util.List;

public class Main {
    static void main(String[] args) {

        Credencial teste01 = new Credencial("Siteteste01","userTeste01","Senhateste01");
        Credencial teste02 = new Credencial("Siteteste01","userTeste02","Senhateste02");
        Credencial teste03 = new Credencial("Siteteste03","userTeste03","Senhateste03");


        InMemoryCredencialRepository rep = new InMemoryCredencialRepository();
        rep.adicionar(teste01);
        rep.adicionar(teste02);
        rep.adicionar(teste03);

        List<Credencial> result = rep.buscaPorSite("Siteteste01");
        System.out.println(result.stream().count());

        System.out.println(rep.excluirPorId(2));

        result = rep.buscaPorSite("Siteteste01");
        System.out.println(result.stream().count());

    }
}
