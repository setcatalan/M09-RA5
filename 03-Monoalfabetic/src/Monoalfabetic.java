import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
    static final char[] abecedari = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    static final char[] abecedariPermutat = permutaAlfabet(abecedari);

    public static void main(String[] args){
        String [] textos = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String [] textosXifrats = new String[textos.length];

        System.out.println("Xifrat");
        System.out.println("-------");

        int posiscio = 0;
        for (String text: textos){
            char[] cadena = text.toCharArray();
            String textXifrat = xifraMonoAlfa(cadena);
            textosXifrats[posiscio] = textXifrat;
            System.out.println(text + "  =>  " + textXifrat);
            posiscio += 1;
        }

        System.out.println("\n" + "Desxifrat");
        System.out.println("----------");

        for (String textXifrat: textosXifrats){
            char[] cadena = textXifrat.toCharArray();
            String textDesxifrat = desxifraMonoAlfa(cadena);
            System.out.println(textXifrat + "  =>  " + textDesxifrat);
        }

        System.out.println("\nCorrespondència");
        System.out.println("----------------");

        for (char lletra: abecedari){
            System.out.print(lletra + " ");
        }
        System.out.println();
        for (int i = 0; i < abecedari.length; i++){
            System.out.print("| ");
        }
        System.out.println();
        for (char lletra: abecedariPermutat){
            System.out.print(lletra + " ");
        }
        
    }

    public static char[] permutaAlfabet(char[] alfabet){
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

    public static String xifraMonoAlfa(char[] cadena){
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

    public static String desxifraMonoAlfa(char[] cadena){
        String text = "";
        for (char caracter: cadena){
            boolean lletraCorrecta = false;
            for (int i = 0; i < abecedariPermutat.length; i++){
                if (caracter == abecedariPermutat[i]){
                    text += abecedari[i];
                    lletraCorrecta = true;
                    break;
                }
            }
            if (lletraCorrecta) continue;
            for (int j = 0; j < abecedariPermutat.length; j++){
                char lletra = Character.toLowerCase(abecedariPermutat[j]);
                if (caracter == lletra){
                    text += Character.toLowerCase(abecedari[j]);
                    lletraCorrecta = true;
                    break;
                }
            }
            if (lletraCorrecta) continue;
            text += caracter;
        }
        return text;
    }
}
