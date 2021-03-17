package hk.edu.polyu.comp.comp2021.jungle.model;

import java.io.Serializable;

public class Den extends Enviroment implements Serializable {
    public String site;
    public Chess c;
    public Den(String site){
        this.site=site;
    }

    @Override
    public String toString() {
        if(c==null){
            return "#";
        }else{
            return c.toString();
        }
    }

    @Override
    public String getSite() {
        return this.site;
    }

    public String getType(){
        return "den";
    }

    @Override
    public void setChess(Chess a) {
        this.c=a;
    }

    public void removeChess() {
        this.c=null;
    }

    @Override
    public Chess getChess() {
        return this.c;
    }

    public boolean haveChess(){
        if(c!=null){
            return true;
        }
        return false;
    }
}
