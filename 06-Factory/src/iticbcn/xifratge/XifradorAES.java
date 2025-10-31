package iticbcn.xifratge;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador{

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];

    private byte[] xifraAES(String msg, String clau) throws Exception {
        //Obtenir els bytes de l'String
        byte[] bmsg = msg.getBytes();

        //Genera IvParameterSpec
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        //Genera hash
        byte[] cadena = clau.getBytes();
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        cadena = md.digest(cadena);
        cadena = Arrays.copyOf(cadena, 16);
        SecretKeySpec secretKeySpec = new SecretKeySpec(cadena, ALGORISME_XIFRAT);

        //Encrypt
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] msgXifrat = cipher.doFinal(bmsg);

        //Combina IV i part xifrada
        msgXifrat = Base64.getEncoder().encode(msgXifrat);

        //return iv+msgxifrat
        return msgXifrat;
    }

    private String desxifraAES(byte[] bXifrat, String clau) throws Exception{
        //Extreure l'IV
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        //Extreure la part xifrada
        bXifrat = Base64.getDecoder().decode(bXifrat);

        //Fer hash de la clau
        byte[] cadena = clau.getBytes();
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        cadena = md.digest(cadena);
        cadena = Arrays.copyOf(cadena, 16);
        SecretKeySpec secretKeySpec = new SecretKeySpec(cadena, ALGORISME_XIFRAT);

        //Desxifra
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] msgDesxifrat = cipher.doFinal(bXifrat);

        //return String desxifrat
        return new String(msgDesxifrat);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada{
        TextXifrat textXifrat = null;
        try {
            textXifrat = new TextXifrat(xifraAES(msg, clau));
        } catch (Exception e) {
        }
        return textXifrat;
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            return desxifraAES(xifrat.getBytes(), clau);
        } catch (Exception e) {
        }
        return null;
    }
}
