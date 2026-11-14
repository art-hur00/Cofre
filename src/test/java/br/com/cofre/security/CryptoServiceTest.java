package br.com.cofre.security;

import org.junit.jupiter.api.Test;

import javax.crypto.AEADBadTagException;
import javax.crypto.SecretKey;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CryptoServiceTest {

    @Test
    void deveRecuperarTextoOriginal() throws GeneralSecurityException {
        CryptoService cryptoService = new CryptoService();

        SecretKey chave = cryptoService.gerarChaveTemporaria();
        String textoFicticio = "Teste de dadosCifrados";
        byte[] dadosCifrados = cryptoService.criptografar(textoFicticio, chave);

        String textoRecuperado = cryptoService.descriptografar(dadosCifrados, chave);

        assertEquals(textoFicticio, textoRecuperado);

    }

    @Test
    void deveRejeitarChaveDiferente() throws GeneralSecurityException{
        CryptoService cryptoService = new CryptoService();

        SecretKey chaveOriginal = cryptoService.gerarChaveTemporaria();
        SecretKey chaveDiferente = cryptoService.gerarChaveTemporaria();

        String textoChaveOriginal = "Teste tEste";

        byte[] dadosCriptografados = cryptoService.criptografar(textoChaveOriginal, chaveOriginal);

        assertThrows(AEADBadTagException.class, () -> cryptoService.descriptografar(dadosCriptografados, chaveDiferente));
    }

    @Test
    void deveRejeitarDadosAdulterados() throws GeneralSecurityException{
        CryptoService cryptoService = new CryptoService();

        SecretKey chave = cryptoService.gerarChaveTemporaria();
        String texto = "Texto teste";

        byte[] dadosCriptografados = cryptoService.criptografar(texto, chave);
        dadosCriptografados[dadosCriptografados.length - 1] ^= 1;

        assertThrows(AEADBadTagException.class, () -> cryptoService.descriptografar(dadosCriptografados, chave));

    }

}