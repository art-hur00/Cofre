package br.com.cofre.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GeradorDeSenhaTest {

    @Test
    void deveRejeitarNumeroMenorQueQuatro(){
            GeradorDeSenhas gerador = new GeradorDeSenhas();
        assertThrows(
                IllegalArgumentException.class,
                () -> gerador.gerar(3)
        );
        }
    @Test
    void deveGerarSenhaComTamanhoSolicitado(){

        GeradorDeSenhas gerador = new GeradorDeSenhas();
        String senha = gerador.gerar(16);

        assertEquals(16,senha.length());
    }
    @Test
    void deveGerarSenhaMinimaComTodasAsCategorias() {
        GeradorDeSenhas gerador = new GeradorDeSenhas();

        String senha = gerador.gerar(4);

        assertAll(
                () -> assertEquals(4, senha.length()),
                () -> assertTrue(
                        senha.chars().anyMatch(Character::isUpperCase),
                        "Deve conter uma letra maiúscula"
                ),
                () -> assertTrue(
                        senha.chars().anyMatch(Character::isLowerCase),
                        "Deve conter uma letra minúscula"
                ),
                () -> assertTrue(
                        senha.chars().anyMatch(Character::isDigit),
                        "Deve conter um número"
                ),
                () -> assertTrue(
                        senha.chars().anyMatch(c -> "!@#$%&*()-_=+?".indexOf(c) >= 0),
                        "Deve conter um símbolo permitido"
                )
        );
    }


}
