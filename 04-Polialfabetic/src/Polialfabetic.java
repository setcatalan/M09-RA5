import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {
    static final char[] abecedari = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    static char[] abecedariPermutat;
    private static Random rnd;
    private static long CLAU_SECRETA = 1234;

    public static void main(String[] args){
        String msgs[] = {"Test 01 àrbitre, coixí, Perímetre",
                "Test 02 Taüll, Día, año",
                "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge\n---------");
        for (int i = 0; i < msgs.length; i++){
            initRandom(CLAU_SECRETA);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge\n------------");
        for (int i = 0; i < msgs.length; i++){
            initRandom(CLAU_SECRETA);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

    public static void initRandom(long CLAU_SECRETA){
        abecedariPermutat = abecedari;
        rnd = new Random(CLAU_SECRETA);
    }

    public static String xifraPoliAlfa(String msg){
        String msgXifrat = "";
        for (int i = 0; i < msg.length(); i++){
            permutaAlfabet();
            char c = msg.charAt(i);
            boolean lletraCorrecta = false;
            for (int j = 0; j < abecedari.length; j++){
                if (c == abecedari[j]){
                    msgXifrat += abecedariPermutat[j];
                    lletraCorrecta = true;
                    break;
                }
            }
            if (lletraCorrecta) continue;
            for (int j = 0; j < abecedari.length; j++){
                if (c == Character.toLowerCase(abecedari[j])){
                    msgXifrat += Character.toLowerCase(abecedariPermutat[j]);
                    lletraCorrecta = true;
                    break;
                }
            }
            if (lletraCorrecta) continue;
            msgXifrat += c;
        }
        return msgXifrat;
    }

    public static String desxifraPoliAlfa(String msgXifrat){
        String msg = "";
        for (int i = 0; i < msgXifrat.length(); i++){
            permutaAlfabet();
            char c = msgXifrat.charAt(i);
            boolean lletraCorrecta = false;
            for (int j = 0; j < abecedari.length; j++){
                if (c == abecedariPermutat[j]){
                    msg += abecedari[j];
                    lletraCorrecta = true;
                    break;
                }
            }
            if (lletraCorrecta) continue;
            for (int j = 0; j < abecedari.length; j++){
                if (c == Character.toLowerCase(abecedariPermutat[j])){
                    msg += Character.toLowerCase(abecedari[j]);
                    lletraCorrecta = true;
                    break;
                }
            }
            if (lletraCorrecta) continue;
            msg += c;
        }
        return msg;
    }

    public static void permutaAlfabet(){
        List<Character> alfabetList = new ArrayList<Character>();
        for (int i = 0; i < abecedariPermutat.length; i++){
            char lletra = abecedariPermutat[i];
            alfabetList.add(lletra);
        }
        Collections.shuffle(alfabetList, rnd);
        
        char[] alfabetPermutat = new char[abecedariPermutat.length];
        for (int i = 0; i < alfabetList.size(); i++){
            char lletra = alfabetList.get(i);
            alfabetPermutat[i] = lletra;
        }
        abecedariPermutat = alfabetPermutat;
    }
}
