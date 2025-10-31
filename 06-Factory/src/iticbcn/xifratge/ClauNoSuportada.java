package iticbcn.xifratge;

public class ClauNoSuportada extends Exception {

    String msgError;

    public ClauNoSuportada(String msgError){
        this.msgError = msgError;
    }

    @Override
    public String getLocalizedMessage(){
        return msgError;
    }
}
