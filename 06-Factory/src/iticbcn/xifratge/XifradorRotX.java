package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    static final char[] abecedariMin = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz".toCharArray();
    static final char[] abecedariMaj = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();

    public String xifraRotX(char[] cadena, int rot){
        String textXifrat = xifraDesxifra(cadena, rot, true);
        return textXifrat;
    }

    public String desxifraRotX(char[] cadena, int rot){
        String textDesxifrat = xifraDesxifra(cadena, rot, false);
        return textDesxifrat;
    }

    public String[] forcaBrutaRotX(char[] cadenaXifrada){
        String[] forcaBruta = new String[abecedariMaj.length];
        for (int i = 0; i < abecedariMaj.length; i++){
            String textDesxifrat = desxifraRotX(cadenaXifrada, i);
            forcaBruta[i] = "(" + i + ")->" + textDesxifrat;
        }
        return forcaBruta;
    }

    public String xifraDesxifra(char[] cadena, int rot, boolean xifra){
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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int clauInt;
        TextXifrat textXifrat = null;
        try {
            clauInt = Integer.parseInt(clau);
            if (clauInt >= 0 && clauInt <= 40){
                byte[] bmsg = xifraRotX(msg.toCharArray(), clauInt).getBytes();
                textXifrat = new TextXifrat(bmsg);
            } else {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
        } catch (Exception e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        
        return textXifrat;
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int clauInt;
        String msg = null;
        try {
            clauInt = Integer.parseInt(clau);
            if (clauInt >= 0 && clauInt <= 40){
                String msgXifrat = xifrat.toString();
                msg = desxifraRotX(msgXifrat.toCharArray(), clauInt);
            } else {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
        } catch (Exception e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        
        return msg;
    }
}
