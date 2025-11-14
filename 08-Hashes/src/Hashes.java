public class Hashes {

    private int npass = 0;

    public String getSHA512AmbSalt(String pw, String salt){

        return null;
    }

    public String getPBKDF2AmbSalt(String pw, String salt){

        return null;
    }

    public String forcaBruta(String alg, String hash, String salt){

        return null;
    }

    public String getInterval(long t1, long t2){

        return null;
    }

    public static void main(String[] args) {

        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();

        String[] aHashes = {h.getSHA512AmbSalt(pw, salt), h.getPBKDF2AmbSalt(pw, salt)};

        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for (int i = 0; i < aHashes.length; i++){
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Passwd  : %s", pwTrobat);
            System.out.printf("Provats : %d", h.npass);
            System.out.printf("Temps   : %s", h.getInterval(t1, t2));
        }
    }
}