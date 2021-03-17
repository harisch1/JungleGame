package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.Application;
import hk.edu.polyu.comp.comp2021.jungle.Command.Command;
import hk.edu.polyu.comp.comp2021.jungle.Command.MoveCommand;
import hk.edu.polyu.comp.comp2021.jungle.Command.LoadCommand;
import hk.edu.polyu.comp.comp2021.jungle.Command.SaveCommand;
import hk.edu.polyu.comp.comp2021.jungle.Expection.InputException;
import hk.edu.polyu.comp.comp2021.jungle.Expection.siteException;
import hk.edu.polyu.comp.comp2021.jungle.model.JungleGame;
import jdk.internal.org.objectweb.asm.tree.InnerClassNode;
import org.junit.Test;



public class JungleGameTest {
    JungleGame game = new JungleGame();

    Command[] commandlist = new Command[100];
    public void testcases(Command[] commandlist){
        boolean success = true;
        try {
            int i = 0;
            while (commandlist[i] != null) {
                Command c = commandlist[i];
                game.printBoard();
                if (c.isVaild()) {
                    c.execute();

                }
                i++;
            }
            game.printBoard();
            System.out.println(game.gameEnd());

        } catch (InputException e) {
            success = false;
            e.printStackTrace();
        } catch (siteException e) {
            e.printStackTrace();
        } finally {
            assert success;
        }

    }

    public void testCasesFail(Command[] commandlist){
        boolean success = false;
        try {
            int i = 0;
            while (commandlist[i] != null) {
                Command c = commandlist[i];
                game.printBoard();
                if (c.isVaild()) {
                    c.execute();

                }
                i++;
            }
            game.printBoard();
            System.out.println(game.gameEnd());

        } catch (InputException e) {
            success = true;
            e.printStackTrace();
        } catch (siteException e) {
            e.printStackTrace();
        } finally {
            assert success;
        }


    }

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @Test
    public void testJungleGameConstructor() {
        JungleGame game = new JungleGame();
        assert true;
    }
    @Test
    public void testTrap()throws InputException{
        commandlist[0] = new MoveCommand("e3", "d3", "x", game.board);
        commandlist[1] = new MoveCommand("d3", "d4", "x", game.board);
        commandlist[2] = new MoveCommand("d4", "d5", "x", game.board);
        commandlist[3] = new MoveCommand("d5", "d6", "x", game.board);
        commandlist[4] = new MoveCommand("d6", "d7", "x", game.board);
        commandlist[5] = new MoveCommand("d7", "d8", "x", game.board);
        commandlist[6] = new MoveCommand("e7", "e8", "y", game.board);
        commandlist[7] = new MoveCommand("e8", "d8", "y", game.board);
        testcases(commandlist);
    }
    @Test
    public void testOwnTrap()throws InputException{
        commandlist[0] = new MoveCommand("c3", "d3", "x", game.board);
        commandlist[1] = new MoveCommand("d3", "d2", "x", game.board);
        testCasesFail(commandlist);
        System.out.println("Cannot Enter own trap.");
    }
    @Test
    public void testRiver()throws InputException{
        commandlist[0] = new MoveCommand("c3", "c4", "x", game.board);
        testCasesFail(commandlist);
        System.out.println("Cannot Enter the River.");
    }
    @Test
    public void testRiverFail()throws InputException{
        commandlist[0] = new MoveCommand("e3", "e4", "x", game.board);
        testCasesFail(commandlist);
    }
    @Test
    public void testEat() throws InputException{
        commandlist[0] = new MoveCommand("c7", "d7", "y", game.board);
        commandlist[1] = new MoveCommand("d7", "d6", "y", game.board);
        commandlist[2] = new MoveCommand("d6", "d5", "y", game.board);
        commandlist[3] = new MoveCommand("d5", "d4", "y", game.board);
        commandlist[4] = new MoveCommand("d4", "d3", "y", game.board);
        commandlist[5] = new MoveCommand("d3", "c3", "y", game.board);
        testcases(commandlist);

    }
    @Test
    public void ratRiver()throws InputException{
        commandlist[0] = new MoveCommand("g3", "f3", "x", game.board);
        commandlist[1] = new MoveCommand("f3", "f4", "x", game.board);
        testcases(commandlist);
    }
    @Test
    public void ratRiverEat()throws InputException{
        commandlist[0] = new MoveCommand("g3", "f3", "x", game.board);
        commandlist[1] = new MoveCommand("f3", "f4", "x", game.board);
        commandlist[2] = new MoveCommand("g7", "g6", "y", game.board);
        commandlist[3] = new MoveCommand("g6", "g5", "y", game.board);
        commandlist[4] = new MoveCommand("g5", "g4", "y", game.board);
        commandlist[5] = new MoveCommand("f4", "g4", "x", game.board);
        testCasesFail(commandlist);
        System.out.println("Cannot Eat from the River.");
    }

    @Test
    public void lionJumpEat()throws InputException{
        commandlist[0] = new MoveCommand("b8", "b9", "y", game.board);
        commandlist[1] = new MoveCommand("a9", "a8", "y", game.board);
        commandlist[2] = new MoveCommand("a8", "b8", "y", game.board);
        commandlist[3] = new MoveCommand("b8", "b7", "y", game.board);
        commandlist[4] = new MoveCommand("c3", "b3", "x", game.board);
        commandlist[5] = new MoveCommand("b7", "b6", "y", game.board);
        testcases(commandlist);
    }
    @Test
    public void tigerJumpEat() throws InputException{
        commandlist[0] = new MoveCommand("b2", "b1", "x", game.board);
        commandlist[1] = new MoveCommand("a1", "a2", "x", game.board);
        commandlist[2] = new MoveCommand("a2", "b2", "x", game.board);
        commandlist[3] = new MoveCommand("b2", "b3", "x", game.board);
        commandlist[4] = new MoveCommand("c7", "b7", "y", game.board);
        commandlist[5] = new MoveCommand("b3", "b4", "x", game.board);
        testcases(commandlist);
    }

    @Test
    public void ratElephant()throws InputException{
        commandlist[0] = new MoveCommand("a7", "a6", "y", game.board);
        commandlist[1] = new MoveCommand("a6", "a5", "y", game.board);
        commandlist[2] = new MoveCommand("a5", "a4", "y", game.board);
        commandlist[3] = new MoveCommand("a4", "a3", "y", game.board);
        testcases(commandlist);
    }

    @Test
    public void testDen()throws InputException{

        commandlist[0] = new MoveCommand("e3", "d3", "x", game.board);
        commandlist[1] = new MoveCommand("d3", "d4", "x", game.board);
        commandlist[2] = new MoveCommand("d4", "d5", "x", game.board);
        commandlist[3] = new MoveCommand("d5", "d6", "x", game.board);
        commandlist[4] = new MoveCommand("d6", "d7", "x", game.board);
        commandlist[5] = new MoveCommand("d7", "d8", "x", game.board);
        commandlist[6] = new MoveCommand("d8", "d9", "x", game.board);
        testcases(commandlist);
    }

    @Test
    public void testOwnDen()throws InputException{
        commandlist[0] = new MoveCommand("b2", "c2", "x", game.board);
        commandlist[1] = new MoveCommand("c2", "d2", "x", game.board);
        commandlist[2] = new MoveCommand("d2", "d1", "x", game.board);
        testCasesFail(commandlist);
    }

    @Test
    public void testName(){
        try {
            game.setX("abc");
            game.setY("xyz");
            System.out.println("Player x: " + game.playerX);
            System.out.println("Player y: " + game.playerY);
        } finally {
            assert true;
        }
    }

    @Test
    public void testSaveOpen(){
        Command c;
        try{
            c = new SaveCommand(game, "myGame.txt");
            if (c.isVaild()) {
                c.execute();
            }
            c = new LoadCommand("myGame.txt");
            if (c.isVaild()) {
                c.execute();
            }
        }catch (InputException e) {
            assert false;
            e.printStackTrace();
        } catch (siteException e) {
            assert false;
            e.printStackTrace();
        } finally {
            assert true;
        }

    }

}
