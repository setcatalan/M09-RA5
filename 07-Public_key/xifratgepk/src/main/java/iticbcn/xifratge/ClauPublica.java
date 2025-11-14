package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.Cipher;

public class ClauPublica {

    public KeyPair generaParellClausRSA() throws Exception{
        SecureRandom rnd = new SecureRandom();
        KeyPairGenerator generadorClaus = KeyPairGenerator.getInstance("RSA");
        generadorClaus.initialize(1024, rnd);
        KeyPair parellClaus = generadorClaus.generateKeyPair();

        return parellClaus;
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception{
        Cipher rsaCipher = Cipher.getInstance("RSA");

        rsaCipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        byte[] msgXifrat = rsaCipher.doFinal(msg.getBytes());

        return msgXifrat;
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception{
        Cipher rsaCipher = Cipher.getInstance("RSA");

        rsaCipher.init(Cipher.DECRYPT_MODE, clauPrivada);
        String msgDesxifrat = new String(rsaCipher.doFinal(msgXifrat));

        return msgDesxifrat;
    }
}