package hk.edu.polyu.comp.comp2021.jungle;

import hk.edu.polyu.comp.comp2021.jungle.Command.*;
import hk.edu.polyu.comp.comp2021.jungle.Expection.InputException;
import hk.edu.polyu.comp.comp2021.jungle.Expection.siteException;
import hk.edu.polyu.comp.comp2021.jungle.model.JungleGame;

import java.io.IOException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        JungleGame game;
        Scanner in =new Scanner(System.in);
       while (true) {
           System.out.println("Start new game or load file ?(new/load) ");
           String option = in.nextLine();
           if (option.equals("new")) {
               game = new JungleGame();
               System.out.println("Player X please input your name.");
               game.setX(in.nextLine());
               System.out.println("Player Y please input your name.");
                game.setY(in.nextLine());
               break;
           } else if (option.equals("load")) {
               System.out.println("Please insert the file path.");
               String path=in.nextLine();
               LoadCommand c1 = new LoadCommand(path);
               game = c1.executeGame();
               break;
           } else{
               continue;
           }
       }
        String site="x";
        game.printBoard();
        body:while (!game.gameEnd()){
            try{
            System.out.println("Player "+site+": Please input command. (save / open/ move)");
            String comman=in.nextLine();
            String[] com=comman.split(" ");
           // System.out.println(com[0]);
            switch(com[0]){
                case "move":
                    String s1=com[1];
                    String s2=com[2];
                    Command c=new MoveCommand(s1,s2,site,game.board);
                    if(c.isVaild()){
                        c.execute();
                    }
                    site=(site=="x")? "y":"x";
                    break;
                case "open":
                    System.out.println("Do you want to save current game? (1/0)");
                    int action=Integer.parseInt(in.nextLine());
                    if(action==1){
                        System.out.println("Please select the file path.");
                        String pathh=in.nextLine();
                        Command c2= new SaveCommand(game,pathh);
                        if(c2.isVaild()){
                            c2.execute();
                        }
                    }

                        System.out.println("Please insert the file path.");
                        String path2=in.nextLine();
                        LoadCommand c1 = new LoadCommand(path2);
                        game=c1.executeGame();
                        site=(site=="x")? "y":"x";
                        System.out.println("OPEN GAME.");

                    break;

                case "save":
                    System.out.println("Please select the file path.");
                    String path=in.nextLine();
                    Command c2= new SaveCommand(game,path);
                    if(c2.isVaild()){
                        c2.execute();
                    }
                    System.out.println("SAVE GAME.");
                    break body;
                default:
                    break;
            }
            game.printBoard();

        }catch(InputException e){
                System.out.println(e.toString());
                continue body;
        } catch (siteException e) {
                System.out.println(e.toString());
                continue body;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
