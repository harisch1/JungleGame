package hk.edu.polyu.comp.comp2021.jungle.Command;

import hk.edu.polyu.comp.comp2021.jungle.Expection.InputException;
import hk.edu.polyu.comp.comp2021.jungle.Expection.siteException;

public abstract class Command {
    public abstract void execute() throws InputException;
    public abstract boolean isVaild() throws InputException, siteException;
}
