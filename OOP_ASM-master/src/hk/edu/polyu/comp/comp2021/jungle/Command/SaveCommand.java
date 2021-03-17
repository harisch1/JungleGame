package hk.edu.polyu.comp.comp2021.jungle.Command;

import hk.edu.polyu.comp.comp2021.jungle.Expection.InputException;
import hk.edu.polyu.comp.comp2021.jungle.Expection.siteException;
import hk.edu.polyu.comp.comp2021.jungle.model.JungleGame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveCommand extends Command {
    private JungleGame game;
    private String path;

    public SaveCommand(JungleGame game, String path){
        this.game=game;
        this.path=path;
    }

    @Override
    public void execute() throws InputException {
        try {
            FileOutputStream f = new FileOutputStream(new File(path));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(game);

            o.close();
            f.close();


        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

    @Override
    public boolean isVaild() throws InputException, siteException {
        return true;
    }
}
