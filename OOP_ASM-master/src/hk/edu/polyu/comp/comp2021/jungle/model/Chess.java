package hk.edu.polyu.comp.comp2021.jungle.model;

import java.io.Serializable;

public class Chess extends Enviroment implements Serializable {
    public rank rank;
    public String name;
    public String site;

    public Chess(String s,String name){
        this.site=s;
        this.name=name;
        switch (name){
            case "Rat":
                this.rank=rank.Rat;
                break;
            case "Cat":
                this.rank=rank.Cat;
                break;
            case "Dog":
                this.rank=rank.Dog;
                break;
            case "Wolf":
                this.rank=rank.Wolf;
                break;
            case "Leopard":
                this.rank=rank.Leopard;
                break;
            case "Tiger":
                this.rank=rank.Tiger;
                break;
            case "Lion":
                this.rank=rank.Lion;
                break;
            case "Elephant":
                this.rank=rank.Elephant;
                break;

        }

        }
    public String toString(){
        if(this.name.equals("Leopard")){
            return "l";
        }else{
        return name.charAt(0)+"";
        }
    }
    public String getSite(){return this.site;}
    public int getRank(){return this.rank.getRank();}
    public String getType(){return "chess";}


    public void setChess(Chess a) { }

    public void removeChess() { }

    public boolean checkRank(Chess other){
        if(this.getRank()==8&&other.getRank()==1){
            return false;
        }
        if(this.getRank()==1&&other.getRank()==8){
            return true;
        }
        if(this.getRank()>=other.getRank()){
            return true;
        }else return false;
    }

    @Override
    public Chess getChess() {
        return null;
    }

    public boolean haveChess() {
        return true;
    }
}
