package hk.edu.polyu.comp.comp2021.jungle.Expection;

public class InputException extends Exception {
    String msg;
    public  InputException(String msg){this.msg=msg;}
    public  InputException(){}
    public String toString(){
        return msg+" Invalid Input. Please try again.";
    }
}
