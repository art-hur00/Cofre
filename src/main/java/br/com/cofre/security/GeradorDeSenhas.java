package br.com.cofre.security;

import java.security.SecureRandom;

public class GeradorDeSenhas {

    private final SecureRandom random = new SecureRandom();

    private static final String MAIUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMEROS = "0123456789";
    private static final String SIMBOLOS = "!@#$%&*()-_=+?";

    private static final String CARACTERES = MAIUSCULAS + MINUSCULAS + NUMEROS + SIMBOLOS;

    public String gerar (int tamanho) {

        if (tamanho < 4) {
            throw new IllegalArgumentException("ERRO! \nTamanho mínimo de 4 caracteres!");
        }

        StringBuilder senha;

        do {

            senha = new StringBuilder();

            int contador = tamanho;

            while (contador > 0) {
                int indice = random.nextInt(CARACTERES.length());
                senha.append(CARACTERES.charAt(indice));

                contador--;
            }

        } while (!atendeCategorias(senha.toString()));

        return senha.toString();
    }


    private boolean atendeCategorias(String senha) {

        boolean temMaiuscula = senha.chars().anyMatch(caractere -> MAIUSCULAS.indexOf(caractere) >= 0);
        boolean temMinuscula = senha.chars().anyMatch(caractere -> MINUSCULAS.indexOf(caractere) >= 0);
        boolean temNumeros = senha.chars().anyMatch(caractere -> NUMEROS.indexOf(caractere) >= 0);
        boolean temSimbolos = senha.chars().anyMatch(caractere -> SIMBOLOS.indexOf(caractere) >= 0);

        return temMaiuscula && temMinuscula && temNumeros && temSimbolos;
    }
}