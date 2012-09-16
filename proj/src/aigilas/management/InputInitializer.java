package aigilas.management;

import spx.io.*;

import java.util.ArrayList;
import java.util.List;

public class InputInitializer implements IInputInitializer {
    private CommandDefinition make(Commands command, Keys key, Buttons button, Contexts lockContext) {
        return new CommandDefinition(command, key, button, lockContext);
    }

    @Override
    public List<CommandDefinition> getCommands() {
        ArrayList<CommandDefinition> result = new ArrayList<CommandDefinition>();
        result.add(make(Commands.MoveUp, Keys.Up, Buttons.LeftThumbstickUp, Contexts.Nonfree));
        result.add(make(Commands.MoveDown, Keys.Down, Buttons.LeftThumbstickDown, Contexts.Nonfree));
        result.add(make(Commands.MoveLeft, Keys.Left, Buttons.LeftThumbstickLeft, Contexts.Nonfree));
        result.add(make(Commands.MoveRight, Keys.Right, Buttons.LeftThumbstickRight, Contexts.Nonfree));
        result.add(make(Commands.Confirm, Keys.Space, Buttons.RightTrigger, Contexts.All));
        result.add(make(Commands.Inventory, Keys.E, Buttons.DPadUp, Contexts.All));
        result.add(make(Commands.Cancel, Keys.R, Buttons.X, Contexts.All));
        result.add(make(Commands.Start, Keys.Enter, Buttons.Start, Contexts.All));
        result.add(make(Commands.Back, Keys.Back, Buttons.Back, Contexts.All));
        result.add(make(Commands.CycleRight, Keys.D, Buttons.RightShoulder, Contexts.All));
        result.add(make(Commands.CycleLeft, Keys.A, Buttons.LeftShoulder, Contexts.All));
        result.add(make(Commands.Skill, Keys.S, Buttons.A, Contexts.All));
        result.add(make(Commands.LockSkill, Keys.RightControl, Buttons.LeftTrigger, Contexts.All));
        result.add(make(Commands.HotSkill1, Keys.D1, Buttons.X, Contexts.All));
        result.add(make(Commands.HotSkill2, Keys.D2, Buttons.Y, Contexts.All));
        result.add(make(Commands.HotSkill3, Keys.D3, Buttons.B, Contexts.All));
        result.add(make(Commands.ToggleDevConsole, Keys.OemTilde, Buttons.DPadDown, Contexts.All));
        return result;
    }
}
