package esi.g55019.atl.SameGame.DPCommand;

public interface Command {
    /**
     * Interface for the Command DP
     * @author Cotton Ian | g55019
     */

    /**
     * execute the command
     */
    public void execute();

    /**
     * cancel the previous command. Can only be used if isReversible() is true
     */
    public void unexecute();

    /**
     * say if the command isReversible
     * @return boolean
     */
    public boolean isReversible();
}
