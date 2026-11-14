package br.com.cofre.ui;

import br.com.cofre.model.Credencial;
import br.com.cofre.security.GeradorDeSenhas;
import br.com.cofre.service.CredencialService;

import java.util.List;
import java.util.Scanner;

public class MenuTerminal {

    Scanner scanner = new Scanner(System.in);

    private final CredencialService credencialService;
    private final GeradorDeSenhas geradorDeSenhas;

    public MenuTerminal(CredencialService credencialService, GeradorDeSenhas geradorDeSenhas) {
        this.credencialService = credencialService;
        this.geradorDeSenhas = geradorDeSenhas;
    }


    public void exibeMenu() {


        String escolhaMenu;
        boolean rodarMenu = true;

        while (rodarMenu) {
            System.out.println("\n1-Cadastrar\n2-Consultar\n3-Excluir\n0-Sair\n:");
            escolhaMenu = scanner.nextLine();
            switch (escolhaMenu) {
                case "1":
                    System.out.println("Você selecionou: Cadastrar");
                    cadastrar();
                    break;
                case "2":
                    System.out.println("Você selecionou: Consultar");
                    System.out.println("Digite o nome do Site que deseja consultar: ");
                    consultar(scanner.nextLine());
                    break;
                case "3":
                    System.out.println("Você selecionou: Excluir");
                    System.out.println("Informe o nome do site: ");
                    excluir(scanner.nextLine());
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

    private void cadastrar() {
        System.out.println("Informe o Site: ");
        String site = scanner.nextLine();

        System.out.println("Informe o Usuário: ");
        String user = scanner.nextLine();

        try {
            String senha;

            while (true) {
                System.out.println("Deseja usar o gerador de senha?\n1 - Sim\n2 - Não (Digitar uma senha)");
                String escolhaGeradorSenha = scanner.nextLine();
                if (escolhaGeradorSenha.equals("1")) {

                    System.out.println("Quantos caracteres deseja que a senha tenha?");
                    int tamanho = Integer.parseInt(scanner.nextLine().trim());
                    senha = geradorDeSenhas.gerar(tamanho);
                    break;
                } else if (escolhaGeradorSenha.equals("2")) {
                    System.out.println("Informe a senha: ");
                    senha = scanner.nextLine();
                    break;
                } else {
                    System.out.println("Opção inválida! Escolha 1 ou 2.");
                }
            }
                credencialService.cadastrar(site, user, senha);
                System.out.println("Cadastramento feito com sucesso!");
            } catch(IllegalArgumentException e){
                System.out.println("Erro: " + e.getMessage());
            }
    }

    private void consultar(String nomeSite) {
        try {
            List<Credencial> resultados = credencialService.buscarPorSite(nomeSite);
            if (resultados.isEmpty()) {
                System.out.println("Nenhuma conta encontrada");
            }
            resultados.forEach(credencial -> System.out.println("Site: " + credencial.getSite() + "\nUsuário: " + credencial.getUsuario() + "\nSenha: " + credencial.getSenha()));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void excluir(String nomeSite) {
        try {
            List<Credencial> resultados = credencialService.buscarPorSite(nomeSite);
            if (resultados.isEmpty()) {
                System.out.println("Nenhuma conta encontrada.");
                return;
            }
            Credencial selecionada;
            if (resultados.size() == 1) {
                selecionada = resultados.get(0);
            } else {
                System.out.println("Qual conta deseja excluir?");
                for (int i = 0; i < resultados.size(); i++) {
                    System.out.println((i + 1) + " - " + resultados.get(i).getUsuario());
                }
                int opcao = Integer.parseInt(scanner.nextLine().trim());
                if (opcao < 1 || opcao > resultados.size()) {
                    System.out.println("Opção fora da lista.");
                    return;
                }

                selecionada = resultados.get(opcao - 1);
            }
            System.out.println("Conta selecionada: " + selecionada.getUsuario()
                    + " | Site: " + selecionada.getSite());
            while (true) {
                System.out.println("Tem certeza que deseja exclui-la? (s/n): ");
                String confirmacaoDeExclusao = scanner.nextLine();
                if (confirmacaoDeExclusao.trim().equalsIgnoreCase("s")) {
                    if (credencialService.excluirPorId(selecionada.getId())) {
                        System.out.println("Conta excluida com sucesso!");
                    } else {
                        System.out.println("Não foi possível excluir sua conta.");

                    }
                    return;

                } else if (confirmacaoDeExclusao.trim().equalsIgnoreCase("n")) {
                    System.out.println("Ação cancelada!");
                    return;
                } else {
                    System.out.println("Opção inválida!");

                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Digite o número de uma das opções.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

}
