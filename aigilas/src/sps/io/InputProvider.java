package sps.io;

import sps.bridge.Command;
import sps.bridge.Context;

/**
 * Created with IntelliJ IDEA.
 * User: kretst
 * Date: 2/11/13
 * Time: 6:41 PM
 * To change this template use File | SpsConfig | File Templates.
 */
public interface InputProvider {
    void setup(StateProvider stateProvider);

    boolean detectState(Command command, int playerIndex);

    boolean isActive(Command command, int playerIndex, boolean failIfLocked);

    public boolean isActive(Command command, int playerIndex);

    void setContext(Context context, int playerIndex);

    boolean isContext(Context context, int playerIndex);

    boolean isLocked(Command command, int playerIndex);

    void lock(Command command, int playerIndex);

    void unlock(Command command, int playerIndex);

    void update();
}
