import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.security.KeyStore.SecretKeyEntry;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public static void main(String[] args){
        String msgs[] = {"Lorem ipsum dicet",
                    "Hola Andrés cómo está tu cuñado",
                    "Àgora ïlla Òtto"};

        for (int i = 0; i < msgs.length; i++){
            String msg = msgs[i];

            byte[] bXifrat = null;
            String desxifrat = "";
            try {
                bXifrat = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrat, CLAU);
            }  catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }
            System.out.println("------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrat));
            System.out.println("Dec: " + desxifrat);
        }
    }

    private static byte[] xifraAES(String msg, String clau) throws Exception {
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

    private static String desxifraAES(byte[] bXifrat, String clau) throws Exception{
        //Extreure l'IV

        //Extreure la part xifrada

        //Desxifra

        //return String desxifrat
        return null;
    }
}
