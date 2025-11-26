import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    private int npass = 0;

    public String getSHA512AmbSalt(String pw, String salt) throws Exception{
        String pwSalt = pw + salt;

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hash = md.digest(pwSalt.getBytes());
            
        HexFormat hex = HexFormat.of();
        String hashStr = hex.formatHex(hash);
            
        return hashStr;
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception{
        KeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), 10000, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hash = factory.generateSecret(spec).getEncoded();
            
        HexFormat hex = HexFormat.of();
        String hashStr = hex.formatHex(hash);
            
        return hashStr;
    }

    public String forcaBruta(String alg, String hash, String salt) throws Exception{
        String charset = "abcdefABCDEF1234567890!";
        npass = 0;
        if (alg.equals("SHA-512")){
            for (int i = 0; i < charset.length(); i++){
                char c = charset.charAt(i);
                String pw = "" + c;
                npass++;
                if (getSHA512AmbSalt(pw, salt).equals(hash)){
                    return pw;
                } else {
                    for (int j = 0; j < charset.length(); j++){
                        c = charset.charAt(j);
                        npass++;
                        String pw2 = pw + c;
                        if (getSHA512AmbSalt(pw2, salt).equals(hash)){
                            return pw2;
                        } else {
                            for (int k = 0; k < charset.length(); k++){
                                c = charset.charAt(k);
                                npass++;
                                String pw3 = pw2 + c;
                                if (getSHA512AmbSalt(pw3, salt).equals(hash)){
                                    return pw3;
                                } else {
                                    for (int l = 0; l < charset.length(); l++){
                                        c = charset.charAt(l);
                                        npass++;
                                        String pw4 = pw3 + c;
                                        if (getSHA512AmbSalt(pw4, salt).equals(hash)){
                                            return pw4;
                                        } else {
                                            for (int m = 0; m < charset.length(); m++){
                                                c = charset.charAt(m);
                                                npass++;
                                                String pw5 = pw4 + c;
                                                if (getSHA512AmbSalt(pw5, salt).equals(hash)){
                                                    return pw5;
                                                } else {
                                                    for (int n = 0; n < charset.length(); n++){
                                                        c = charset.charAt(n);
                                                        npass++;
                                                        String pw6 = pw5 + c;
                                                        if (getSHA512AmbSalt(pw6, salt).equals(hash)){
                                                            return pw6;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (alg.equals("PBKDF2")){
            for (int i = 0; i < charset.length(); i++){
                char c = charset.charAt(i);
                String pw = "" + c;
                npass++;
                if (getPBKDF2AmbSalt(pw, salt).equals(hash)){
                    return pw;
                } else {
                    for (int j = 0; j < charset.length(); j++){
                        c = charset.charAt(j);
                        npass++;
                        String pw2 = pw + c;
                        if (getPBKDF2AmbSalt(pw2, salt).equals(hash)){
                            return pw2;
                        } else {
                            for (int k = 0; k < charset.length(); k++){
                                c = charset.charAt(k);
                                npass++;
                                String pw3 = pw2 + c;
                                if (getPBKDF2AmbSalt(pw3, salt).equals(hash)){
                                    return pw3;
                                } else {
                                    for (int l = 0; l < charset.length(); l++){
                                        c = charset.charAt(l);
                                        npass++;
                                        String pw4 = pw3 + c;
                                        if (getPBKDF2AmbSalt(pw4, salt).equals(hash)){
                                            return pw4;
                                        } else {
                                            for (int m = 0; m < charset.length(); m++){
                                                c = charset.charAt(m);
                                                npass++;
                                                String pw5 = pw4 + c;
                                                if (getPBKDF2AmbSalt(pw5, salt).equals(hash)){
                                                    return pw5;
                                                } else {
                                                    for (int n = 0; n < charset.length(); n++){
                                                        c = charset.charAt(n);
                                                        npass++;
                                                        String pw6 = pw5 + c;
                                                        if (getPBKDF2AmbSalt(pw6, salt).equals(hash)){
                                                            return pw6;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    public String getInterval(long t1, long t2){
        long millis = t2 - t1;
        long segons = millis / 1000;
        long minuts = segons / 60;
        long hores = minuts / 60;
        long dies = hores / 24;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", dies, hores%24, minuts%60, segons%60, millis%1000);
    }

    public static void main(String[] args) throws Exception{

        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();

        String[] aHashes = {h.getSHA512AmbSalt(pw, salt), h.getPBKDF2AmbSalt(pw, salt)};

        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for (int i = 0; i < aHashes.length; i++){
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash:\n%s\n", aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Passwd  : %s\n", pwTrobat);
            System.out.printf("Provats : %d\n", h.npass);
            System.out.printf("Temps   : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n");
        }
    }
}