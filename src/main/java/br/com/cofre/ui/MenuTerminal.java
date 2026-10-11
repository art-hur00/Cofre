package br.com.cofre.ui;

import br.com.cofre.service.CredencialService;

import java.util.Scanner;

public class MenuTerminal {

    public final CredencialService credencialService;

    public MenuTerminal(CredencialService credencialService) {
        this.credencialService = credencialService;
    }


    public void exibeMenu() {

        Scanner scanner = new Scanner(System.in);

        String escolhaMenu;
        boolean rodarMenu = true;

        while (rodarMenu) {
            System.out.println("1-Cadastrar\n2-Consultar\n3-Excluir\n0-Sair");
            escolhaMenu = scanner.nextLine();
            switch (escolhaMenu) {
                case "1":
                    System.out.println("Você selecionou: Cadastrar");
                    break;
                case "2":
                    System.out.println("Você selecionou: Consultar");
                    break;
                case "3":
                    System.out.println("Você selecionou: Excluir");
                    break;
                case "0":
                    System.out.println("Você selecionou: Sair");
                    rodarMenu = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }

    }
}
