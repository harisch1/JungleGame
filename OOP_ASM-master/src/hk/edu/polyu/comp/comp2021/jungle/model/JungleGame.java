package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.Command.SaveCommand;

import java.io.Serializable;

public class JungleGame implements Serializable {

    public Enviroment[][] board;
    public String[] nameList = {"Lion", "Tiger", "Dog", "Cat", "Rat", "Leopard", "Wolf", "Elephant"};
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public String playerX,playerY;

    public JungleGame() {
        int w = 0;
        int pos = 0;
        board = new Enviroment[9][7];
        board[0][3]=new Den("y");
        board[8][3]=new Den("x");
        for(int t =3;t<6;t++){
            for(int r=0;r<7;r++){
                if(r==0||r==3||r==6){
                    continue;
                }else{
                    board[t][r]=new River();
                }
            }
        }
        for (int q = 0; q < 3; q++) {
            while (w < 7) {
                if ((q == 0 && w == 2) || (q == 0 && w == 4) || (q == 1 && w == 3)) {
                    board[q][w]=new Trap("y");
                } else {

                    board[q][w] = new Chess("y", nameList[pos]);
                    pos++;
                }
                w += 2;
            }
            w = 0;
            if (q == 0) {
                w = 1;
            }
        }
        w=6;
        pos=0;
        for(int q=8;q>5;q--){
            while(w>-1){
                if((q==8&&w==4)||(q==8&&w==2)||(q==7&&w==3)){
                    board[q][w]=new Trap("x");
                }else{
                //System.out.println("w:"+w+" q:"+q+ " Chess: "+nameList[pos]);
                board[q][w]=new Chess("x",nameList[pos]);
                    pos++;
                }
                w-=2;
            }
            w=6;
            if(q==8){
                w=5;
            }
        }

    }

    public void setX(String nm){
        this.playerX=nm;
    }

    public void setY(String nm){
        this.playerY=nm;
    }

    public void printBoard() {
       int index=9;
       System.out.println("              Y");
       System.out.println("  +  -  -  -  -  -  -  -  +");
        for (Enviroment[] e : board) {
            System.out.print(index+" |  ");
            for (Enviroment x : e) {
                if (x == null) {
                    System.out.print("   ");
                } else {
                    if(x.getType().equals("chess")&& x.getSite().equals("x")){

                        System.out.print(ANSI_RED+x.toString()+"  "+ANSI_RESET);
                    }else if(x.getType().equals("chess")&&x.getSite().equals("y")){
                        System.out.print(ANSI_BLUE+x.toString()+"  "+ANSI_RESET);
                    }else if((x.getType().equals("river")||x.getType().equals("trap"))&&x.haveChess()){
                        if(x.getChess().getSite().equals("x")){
                            System.out.print(ANSI_RED+x.toString()+"  "+ANSI_RESET);
                        }else{
                            System.out.print(ANSI_BLUE+x.toString()+"  "+ANSI_RESET);
                        }
                    }else
                    System.out.print(x.toString()+"  ");
                }
            }
            System.out.println("|");
            index--;
        }
        System.out.println("  +  -  -  -  -  -  -  -  +");
        System.out.println("     A  B  C  D  E  F  G  ");
        System.out.println("              X");
        System.out.println();
    }

    public boolean  gameEnd(){
       int x=0;
       int y=0;
       for(Enviroment[] b1 : board){
           for(Enviroment e : b1){
               if(e!=null){
                   if(e.getType().equals("chess")){
                       if (e.getSite().equals("x")) {
                           x++;
                       } else {
                           y++;
                       }
                   }else if(e.getType().equals("river")){
                       if(e.haveChess()){
                           if(e.getChess().getSite().equals("x")){
                               x++;
                           }else{
                               y++;
                           }
                       }
                   }
               }
           }
       }
        //System.out.println("x: "+x+" y: "+y);
       if(this.board[0][3].haveChess()||this.board[8][3].haveChess()){
           if(this.board[0][3].haveChess()){
               System.out.println("Winner is "+playerX+"!!" );
           }else if(this.board[8][3].haveChess()){
               System.out.println("Winner is "+playerY+"!!" );
           }
           return true;
       }
       if(x==0||y==0){
           if(x==0){
               System.out.println("Winner is "+playerY+"!!" );
           }else if(y==0){
               System.out.println("Winner is "+playerX+"!!" );
           }
           return true;
       }
       return false;
    }
}




