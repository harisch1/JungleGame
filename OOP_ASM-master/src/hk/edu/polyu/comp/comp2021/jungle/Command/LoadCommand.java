package hk.edu.polyu.comp.comp2021.jungle.Command;

import hk.edu.polyu.comp.comp2021.jungle.Expection.InputException;
import hk.edu.polyu.comp.comp2021.jungle.Expection.siteException;
import hk.edu.polyu.comp.comp2021.jungle.model.JungleGame;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadCommand extends Command {
   private String path;
    public LoadCommand(String path){
        this.path=path;
    }

    @Override
    public void execute() throws InputException {

    }

    public JungleGame executeGame() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(new File(path));
        ObjectInputStream oi = new ObjectInputStream(fi);

        // Read objects
        JungleGame re =(JungleGame) oi.readObject();

        oi.close();
        fi.close();

        return  re;
    }

    @Override
    public boolean isVaild() throws InputException, siteException {
        return true;
    }
}
