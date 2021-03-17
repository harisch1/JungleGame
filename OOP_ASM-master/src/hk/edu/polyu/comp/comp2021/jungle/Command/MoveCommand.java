package hk.edu.polyu.comp.comp2021.jungle.Command;

import hk.edu.polyu.comp.comp2021.jungle.Expection.*;
import hk.edu.polyu.comp.comp2021.jungle.model.Chess;
import hk.edu.polyu.comp.comp2021.jungle.model.Enviroment;
import hk.edu.polyu.comp.comp2021.jungle.model.River;
import hk.edu.polyu.comp.comp2021.jungle.model.Trap;

public class MoveCommand extends Command {

    public String s1,s2;
    public int s1x,s1y,s2x,s2y;
    public Enviroment[][] board;
    public String site;

    public MoveCommand(String s1, String s2,String site, Enviroment[][] board) throws InputException {
        this.s1=s1;
        this.s2=s2;
        this.board=board;
        this.site=site;
//97-103 , 65-71
         s1y=s1.charAt(0);
         s1x=Character.getNumericValue(s1.charAt(1));
         s2y=s2.charAt(0);
         s2x=Character.getNumericValue(s2.charAt(1));
        if(s1y>=97&&s1y<=103){
            s1y=6-(103-s1y);
        }else if((s1y>=65&&s1y<=71)){
            s1y=6-(71-s1y);
        }else{
            throw new InputException();
        }
        if(s2y>=97&&s2y<=103){
            s2y=6-(103-s2y);
        }else if((s2y>=65&&s2y<=71)){
            s2y=6-(71-s2y);
        }else{
            throw new InputException();
        }
        s1x=9-s1x;
        s2x=9-s2x;

    }


    public void execute() throws InputException {
        Chess a;
        if(!board[s1x][s1y].getType().equals("chess")){
           a= board[s1x][s1y].getChess();
        }else{
           a= (Chess)board[s1x][s1y];
        }
        Enviroment b= board[s2x][s2y];

        if(b==null){
            String cas=board[s1x][s1y].getType();
            switch (cas){
               case "chess":
                   board[s2x][s2y]=board[s1x][s1y];
                   board[s1x][s1y]=null;
                break;

                case "river":
                    board[s2x][s2y]=board[s1x][s1y].getChess();
                    board[s1x][s1y].removeChess();
                    break;

                case "trap":
                    board[s2x][s2y]=board[s1x][s1y].getChess();
                    board[s1x][s1y].removeChess();
                    break;
            }
        }else {
            if(b.getType().equals("den")){
                if(!board[s1x][s1y].getChess().getSite().equals(b.getSite())){
                b.setChess((Chess)board[s1x][s1y].getChess());
                board[s1x][s1y].removeChess();}else{throw new InputException("Cannot go into your own den.");}
            }else if(b.getType().equals("trap")){
                b.setChess((Chess)board[s1x][s1y]);
                board[s1x][s1y] = null;
            }else if (board[s1x][s1y].getType().equals("chess") && b.getType().equals("chess")) {
                if (a.checkRank((Chess)b)) {
                    board[s2x][s2y] = a;
                    board[s1x][s1y] = null;
                }
            } else if (board[s1x][s1y].getType().equals("chess") && b.getType().equals("river")) {
                if(a.getRank()==6||a.getRank()==7){
                    String move;
                    if(s1x-s2x!=0){
                        if(Math.max(s1x,s2x)==s1x){
                            move="up";
                        }else{move="down";}
                    }else {
                        if(Math.max(s1y,s2y)==s1y){
                            move="left";
                        }else{move="right";}
                    }
                    switch (move){
                        case "up":
                            board[s2x-3][s2y]=a;
                            board[s1x][s1y] = null;
                            return;

                        case "down":
                            board[s2x+3][s2y]=a;
                            board[s1x][s1y] = null;
                            return;

                        case"left":
                            board[s2x][s2y-2]=a;
                            board[s1x][s1y] = null;
                            return;

                        case"right":
                            board[s2x][s2y+3]=a;
                            board[s1x][s1y] = null;
                            return;

                    }
                }

                if(b.haveChess()){
                    throw new InputException("Cannot attack when fo into water");
                }
                b.setChess(a);
                board[s1x][s1y] = null;
            } else if (board[s1x][s1y].getType().equals("river") && b.getType().equals("river")) {
                b.setChess(a);
                board[s1x][s1y].removeChess();
            } else if (board[s1x][s1y].getType().equals("river") && b.getType().equals("chess")) {
                if(b.haveChess()){
                    throw new InputException("Cannot attack on landing");
                }
                if(a.checkRank((Chess)b)){
                board[s2x][s2y] = a;
                board[s1x][s1y].removeChess();
                }
            }
        }
    }

    public boolean isVaild() throws InputException, siteException {
        int move1, move2;
        move1 = s1x + s1y;
        move2 = s2x + s2y;
        int position;
        Enviroment a = board[s1x][s1y];
        Enviroment b = board[s2x][s2y];
        Chess a1, a2;
        position = (Math.max(move1, move2) == move1) ? move1 - move2 : move2 - move1;
        if (position > 1) {
            throw new InputException("Position Error.");
        } else if (s1x > 8 || s1x < 0 || s1y < 0 || s1y > 7 || s2x > 8 || s2x < 0 || s2y < 0 || s2y > 7) {
            throw new InputException();
        } else if (a != null) {
            if (!a.getType().equals("chess")) {
                if (!a.haveChess()) {
                    throw new InputException("Please select a chess.");
                } else {
                    a1 = a.getChess();
                }
            } else {
                a1 = (Chess) a;
            }
            if (!a1.site.equals(this.site)) {
                throw new siteException();
            }
        } else {
            throw new InputException("Please select a chess.");
        }


        Chess b1 = null;
        if (b != null) {
            if (b.getType().equals("chess")) {
                b1 = (Chess) b;
            } else {
                if (b.getType().equals("river") && a1.getRank() != 1) {
                    if(a1.getRank()==6||a1.getRank()==7){
                        String move;
                        if(s1x-s2x!=0){
                            if(Math.max(s1x,s2x)==s1x){
                                move="up";
                            }else{move="down";}
                        }else {
                            if(Math.max(s1y,s2y)==s1y){
                                move="left";
                            }else{move="right";}
                        }
                        switch (move){
                            case "up":
                                if(board[s2x][s2y].haveChess()==false&&board[s2x-1][s2y].haveChess()==false){
                                    if(board[s2x-3][s2y]!=null){
                                        if(!a1.checkRank((Chess)board[s2x-3][s2y])){
                                            throw new InputException("low rank, cannot cross river");
                                        }
                                    }
                                    return true;
                                }else throw new InputException("Cannot cross river");

                            case"down":
                                if(board[s2x][s2y].haveChess()==false&&board[s2x+1][s2y].haveChess()==false){
                                    if(board[s2x+3][s2y]!=null){
                                        if(!a1.checkRank((Chess)board[s2x+3][s2y])){
                                            throw new InputException("low rank, cannot cross river");
                                        }
                                    }
                                    return true;
                                }else throw new InputException("Cannot cross river");

                            case"left":
                                if(board[s2x][s2y].haveChess()==false&&board[s2x][s2y-1].haveChess()==false){
                                    if(board[s2x][s2y-2]!=null){
                                        if(!a1.checkRank((Chess)board[s2x][s2y-2])){
                                            throw new InputException("low rank, cannot cross river");
                                        }
                                    }
                                    return true;
                                }else throw new InputException("Cannot cross river");

                            case"right":
                                if(board[s2x][s2y].haveChess()==false&&board[s2x][s2y+1].haveChess()==false){
                                    if(board[s2x][s2y+2]!=null){
                                        if(!a1.checkRank((Chess)board[s2x][s2y+2])){
                                            throw new InputException("low rank, cannot cross river");
                                        }
                                    }
                                    return true;
                                }else throw new InputException("Cannot cross river");
                        }
                    }
                    else throw new InputException("Only rat get into river.");
                }else if(b.getType().equals("trap")){
                    if(a1.getSite().equals(b.getSite())&&b.haveChess()==false){
                        throw new InputException("Cannot go into your own trap.");
                    }
                }
            if (b.haveChess()) {
                b1 = b.getChess();
            }
        }
        if (b1 != null) {
            if (b1.getSite().equals(a1.getSite())) {
                throw new InputException("Already have chess.");
            }
            if(b.getType().equals("trap")){

            }else{
            if (!a1.checkRank(b1)) {
                throw new InputException("low rank.");
            }}

        }
    }

        return true;
    }

}
