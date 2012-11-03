package sps.text;

public class NewTexts {

    private static NewTexts instance;
    public static NewTexts get(){
        if(instance == null){
            instance = new NewTexts();
        }
        return instance;
    }

    private NewTexts(){

    }
}
