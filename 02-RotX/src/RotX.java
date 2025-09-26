public class RotX {
    static final char[] abecedariMin = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz".toCharArray();
    static final char[] abecedariMaj = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();

    public static void main(String[] args){
        String [] textos = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        int[] rot = {0, 2, 4, 6};
        String [] textosXifrats = new String[textos.length];

        System.out.println("Xifrat");
        System.out.println("--------");

        int posiscio = 0;
        for (String text: textos){
            char[] cadena = text.toCharArray();
            String textXifrat = xifraRotX(cadena, rot[posiscio]);
            textosXifrats[posiscio] = textXifrat;
            System.out.println("(" + rot[posiscio] + ") " + text + "  =>  " + textXifrat);
            posiscio += 1;
        }

        System.out.println("\n" + "Desxifrat");
        System.out.println("----------");

        posiscio = 0;
        for (String textXifrat: textosXifrats){
            char[] cadena = textXifrat.toCharArray();
            String textDesxifrat = desxifraRotX(cadena, rot[posiscio]);
            System.out.println("(" + rot[posiscio] + ") " + textXifrat + "  =>  " + textDesxifrat);
            posiscio += 1;
        }

        System.out.println("\nMissatge xifrat: " + textosXifrats[3]);
        System.out.println("----------------");

        String[] forcaBruta = forcaBrutaRotX(textosXifrats[3].toCharArray());

        for(String textDesxifrat: forcaBruta){
            System.out.println(textDesxifrat);
        }

    }

    public static String xifraRotX(char[] cadena, int rot){
        String textXifrat = xifraDesxifra(cadena, rot, true);
        return textXifrat;
    }

    public static String desxifraRotX(char[] cadena, int rot){
        String textDesxifrat = xifraDesxifra(cadena, rot, false);
        return textDesxifrat;
    }

    public static String[] forcaBrutaRotX(char[] cadenaXifrada){
        String[] forcaBruta = new String[abecedariMaj.length];
        for (int i = 0; i < abecedariMaj.length; i++){
            String textDesxifrat = desxifraRotX(cadenaXifrada, i);
            forcaBruta[i] = "(" + i + ")->" + textDesxifrat;
        }
        return forcaBruta;
    }

    public static String xifraDesxifra(char[] cadena, int rot, boolean xifra){
        String text = "";
        if (xifra){
            rot = rot * -1;
        }
        for (char caracter: cadena){
            boolean lletraCorrecta = false;
            for (int i = 0; i < abecedariMin.length; i++){
                char lletraMin = abecedariMin[i];
                if (caracter == lletraMin){
                    text += abecedariMin[((i + abecedariMin.length) - rot) % abecedariMin.length];
                    lletraCorrecta = true;
                    break;
                }
            }
            if (lletraCorrecta){
                continue;
            }
            for (int j = 0; j < abecedariMaj.length; j++){
                char lletraMaj = abecedariMaj[j];
                if (caracter == lletraMaj){
                    text += abecedariMaj[((j + abecedariMaj.length) - rot) % abecedariMaj.length];
                    lletraCorrecta = true;
                    break;
                }
            }
            if (!lletraCorrecta){
                text += caracter;
            }
        }
        return text;
    }
}
