package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador{
    static final char[] abecedari = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    char[] abecedariPermutat;
    static private Random rnd;

    public void initRandom(long CLAU_SECRETA){
        abecedariPermutat = abecedari;
        rnd = new Random(CLAU_SECRETA);
    }

    public String xifraPoliAlfa(String msg){
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

    public String desxifraPoliAlfa(String msgXifrat){
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

    public void permutaAlfabet(){
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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            initRandom(Long.parseLong(clau));
        } catch (NumberFormatException e){
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
        byte[] bmsg = xifraPoliAlfa(msg).getBytes();
        TextXifrat textXifrat = new TextXifrat(bmsg);
        return textXifrat;
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            initRandom(Long.parseLong(clau));
        } catch (NumberFormatException e){
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
        String msgXifrat = xifrat.toString();
        return desxifraPoliAlfa(msgXifrat);
    }
}
