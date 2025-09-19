/*
 * Una classe amb funcions per xifrar y desxifrar amb Rot13
 */

public class Rot13 {
    public static char[] abecedari = {'a','á','à','b','c','ç','d','e','é','è','f','g','h','i','í','ì','ï',
    'j','k','l','m','n','ñ','o','ó','ò','p','q','r','s','t','u','ú','ù','ü','v','w','x','y','z'};
    public static void main(String[] args){
        String [] textos = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String [] textosXifrats = new String[textos.length];

        System.out.println("Xifrat");
        System.out.println("--------");

        int posiscio = 0;
        for (String text: textos){
            char[] cadena = cadenaDeCaracters(text);
            String textXifrat = xifraRot13(cadena);
            textosXifrats[posiscio] = textXifrat;
            System.out.println(text + "  =>  " + textXifrat);
            posiscio += 1;
        }

        System.out.println("\n" + "Desxifrat");
        System.out.println("----------");

        for (String textXifrat: textosXifrats){
            char[] cadena = cadenaDeCaracters(textXifrat);
            String textDesxifrat = desxifraRot13(cadena);
            System.out.println(textXifrat + "  =>  " + textDesxifrat);
        }

    }

    public static char[] cadenaDeCaracters(String text){
        char[] cadena = new char[text.length()];
        for (int i=0; i < text.length(); i++){
            char caracter = text.charAt(i);
            cadena[i] = caracter;
        }
        return cadena;
    }
    
    public static String xifraRot13(char[] cadena){
        String textXifrat = "";
        for (int i=0; i < cadena.length; i++){
            char caracter = cadena[i];
            if (Character.isLetter(caracter)){
                if (Character.isLowerCase(caracter)){
                    for (int j=0; j < abecedari.length; j++){
                        char lletra = abecedari[j];
                        if (caracter == lletra){
                            textXifrat += abecedari[(j + 13) % abecedari.length];
                            break;
                        }
                    }
                } else {
                    caracter = Character.toLowerCase(caracter);
                    for (int j=0; j < abecedari.length; j++){
                        char lletra = abecedari[j];
                        if (caracter == lletra){
                            textXifrat += Character.toUpperCase(abecedari[(j + 13) % abecedari.length]);
                            break;
                        }
                    }
                }
            } else {
                textXifrat += caracter;
            }
        }
        return textXifrat;
    }

    public static String desxifraRot13(char[] cadena){
        String textDesxifrat = "";
        for (int i=0; i < cadena.length; i++){
            char caracter = cadena[i];
            if (Character.isLetter(caracter)){
                if (Character.isLowerCase(caracter)){
                    for (int j=0; j < abecedari.length; j++){
                        char lletra = abecedari[j];
                        if (caracter == lletra){
                            if (j < 13){
                                j += abecedari.length;
                            }
                            textDesxifrat += abecedari[(j - 13)];
                            break;
                        }
                    }
                } else {
                    caracter = Character.toLowerCase(caracter);
                    for (int j=0; j < abecedari.length; j++){
                        char lletra = abecedari[j];
                        if (caracter == lletra){
                            if (j < 13){
                                j += abecedari.length;
                            }
                            textDesxifrat += Character.toUpperCase(abecedari[(j - 13)]);
                            break;
                        }
                    }
                }
            } else {
                textDesxifrat += caracter;
            }
        }
        return textDesxifrat;
    }
}
