package br.com.cofre.security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class CryptoService {

    private final SecureRandom random = new SecureRandom();


    public CryptoService() throws NoSuchPaddingException, NoSuchAlgorithmException {
    }

    public SecretKey gerarChaveTemporaria() throws NoSuchAlgorithmException {

        KeyGenerator gerador = KeyGenerator.getInstance("AES");
        gerador.init(256);

        return gerador.generateKey();
    }


    private byte[] gerarNonce(){
        byte[] array = new byte[12];
        random.nextBytes(array);

        return array;
    }

    public byte[] criptografar(String texto, SecretKey chave) throws GeneralSecurityException {

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        byte[] nonce = gerarNonce();

        GCMParameterSpec parametros = new GCMParameterSpec(128, nonce);
        cipher.init(Cipher.ENCRYPT_MODE, chave, parametros);

        byte[] textoEmBytes = texto.getBytes(StandardCharsets.UTF_8);
        byte[] textoCifrado = cipher.doFinal(textoEmBytes);

        return ByteBuffer.allocate(nonce.length + textoCifrado.length)
                .put(nonce)
                .put(textoCifrado)
                .array();
    }
    public String descriptografar(byte[] dados, SecretKey chave) throws GeneralSecurityException {
        if(dados == null || dados.length < 28){
            throw new IllegalArgumentException();
        }

        ByteBuffer buffer = ByteBuffer.wrap(dados);

        byte[] nonce = new byte[12];
        buffer.get(nonce);

        byte[] textoCifrado = new byte[buffer.remaining()];
        buffer.get(textoCifrado);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        GCMParameterSpec parametros = new GCMParameterSpec(128, nonce);
        cipher.init(Cipher.DECRYPT_MODE, chave, parametros);

        byte[] textoOriginal = cipher.doFinal(textoCifrado);

        return new String(textoOriginal, StandardCharsets.UTF_8);
    }
}