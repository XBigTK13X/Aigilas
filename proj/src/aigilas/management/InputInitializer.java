package aigilas.management;

import java.util.ArrayList;
import java.util.List;

import spx.io.Buttons;
import spx.io.CommandDefinition;
import spx.io.Contexts;
import spx.io.IInputInitializer;
import spx.io.Keys;

public class InputInitializer implements IInputInitializer {
	private CommandDefinition Make(Commands command, Keys key, Buttons button, Contexts lockContext) {
		return new CommandDefinition(command, key, button, lockContext);
	}

	@Override
	public List<CommandDefinition> GetCommands() {
		ArrayList<CommandDefinition> result = new ArrayList<CommandDefinition>();
		result.add(Make(Commands.MoveUp, Keys.Up, Buttons.LeftThumbstickUp, Contexts.Nonfree));
		result.add(Make(Commands.MoveDown, Keys.Down, Buttons.LeftThumbstickDown, Contexts.Nonfree));
		result.add(Make(Commands.MoveLeft, Keys.Left, Buttons.LeftThumbstickLeft, Contexts.Nonfree));
		result.add(Make(Commands.MoveRight, Keys.Right, Buttons.LeftThumbstickRight, Contexts.Nonfree));
		result.add(Make(Commands.Confirm, Keys.Space, Buttons.RightTrigger, Contexts.All));
		result.add(Make(Commands.Inventory, Keys.E, Buttons.DPadUp, Contexts.All));
		result.add(Make(Commands.Cancel, Keys.R, Buttons.X, Contexts.All));
		result.add(Make(Commands.Start, Keys.Enter, Buttons.Start, Contexts.All));
		result.add(Make(Commands.Back, Keys.Back, Buttons.Back, Contexts.All));
		result.add(Make(Commands.CycleRight, Keys.D, Buttons.RightShoulder, Contexts.All));
		result.add(Make(Commands.CycleLeft, Keys.A, Buttons.LeftShoulder, Contexts.All));
		result.add(Make(Commands.Skill, Keys.S, Buttons.A, Contexts.All));
		result.add(Make(Commands.LockSkill, Keys.RightControl, Buttons.LeftTrigger, Contexts.All));
		result.add(Make(Commands.HotSkill1, Keys.D1, Buttons.X, Contexts.All));
		result.add(Make(Commands.HotSkill2, Keys.D2, Buttons.Y, Contexts.All));
		result.add(Make(Commands.HotSkill3, Keys.D3, Buttons.B, Contexts.All));
		result.add(Make(Commands.ToggleDevConsole, Keys.OemTilde, Buttons.DPadDown, Contexts.All));
		return result;
	}
}
