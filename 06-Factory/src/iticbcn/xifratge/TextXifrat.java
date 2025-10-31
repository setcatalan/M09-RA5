package iticbcn.xifratge;

public class TextXifrat {

    private byte[] arrayBytes;

    public TextXifrat(byte[] arrayBytes){
        this.arrayBytes = arrayBytes;
    }

    public byte[] getBytes(){
        return arrayBytes;
    }

    @Override
    public String toString(){
        return new String(arrayBytes);
    }
}
