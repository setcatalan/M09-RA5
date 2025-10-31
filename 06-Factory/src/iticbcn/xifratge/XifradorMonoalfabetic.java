package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador{
    static final char[] abecedari = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    final char[] abecedariPermutat = permutaAlfabet(abecedari);

    public char[] permutaAlfabet(char[] alfabet){
        List<Character> alfabetList = new ArrayList<Character>();
        for (int i = 0; i < alfabet.length; i++){
            char lletra = alfabet[i];
            alfabetList.add(lletra);
        }
        Collections.shuffle(alfabetList);
        
        char[] alfabetPermutat = new char[alfabet.length];
        for (int i = 0; i < alfabetList.size(); i++){
            char lletra = alfabetList.get(i);
            alfabetPermutat[i] = lletra;
        }
        return alfabetPermutat;
    }

    public String xifraMonoAlfa(char[] cadena){
        String text = xifraDesxifra(cadena, abecedari, abecedariPermutat);
        return text;
    }

    public String desxifraMonoAlfa(char[] cadena){
        String text = xifraDesxifra(cadena, abecedariPermutat, abecedari);
        return text;
    }

    public String xifraDesxifra(char[] cadena, char[] abecedari, char[] abecedariPermutat){
        String text = "";
        for (char caracter: cadena){
            boolean lletraCorrecta = false;
            for (int i = 0; i < abecedari.length; i++){
                if (caracter == abecedari[i]){
                    text += abecedariPermutat[i];
                    lletraCorrecta = true;
                    break;
                }
            }
            if (lletraCorrecta) continue;
            for (int j = 0; j < abecedari.length; j++){
                char lletra = Character.toLowerCase(abecedari[j]);
                if (caracter == lletra){
                    text += Character.toLowerCase(abecedariPermutat[j]);
                    lletraCorrecta = true;
                    break;
                }
            }
            if (lletraCorrecta) continue;
            text += caracter;
        }
        return text;
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if(clau != null){
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        byte[] bmsg = xifraMonoAlfa(msg.toCharArray()).getBytes();
        TextXifrat textXifrat = new TextXifrat(bmsg);
        return textXifrat;
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if(clau != null){
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String msgXifrat = xifrat.toString();
        return desxifraMonoAlfa(msgXifrat.toCharArray());
    }
}
