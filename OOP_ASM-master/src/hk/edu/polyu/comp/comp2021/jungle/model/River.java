package hk.edu.polyu.comp.comp2021.jungle.model;

import java.io.Serializable;

public class River extends Enviroment implements Serializable {
    public Chess c;

    @Override
    public String toString() {
        if(c==null){
            return "~";
        }else{
            return c.toString();
        }
    }

    @Override
    public String getSite() {
        return c.getSite();
    }

    public boolean haveChess(){
        if(c!=null){
            return true;
        }
        return false;
    }

    public String getType(){
        return "river";
    }

    @Override
    public void setChess(Chess c){this.c=c;}
    public void removeChess(){this.c=null;}

    @Override
    public Chess getChess() {
        return this.c;
    }
}
