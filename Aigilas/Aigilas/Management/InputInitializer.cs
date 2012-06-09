using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SPX.Core;
using Microsoft.Xna.Framework.Input;

namespace Agilas.Management
{
    public class Commands
    {
        public const int MoveUp = 0;
        public const int MoveDown = 1;
        public const int MoveLeft = 2;
        public const int MoveRight = 3;
        public const int Confirm = 4;
        public const int Inventory = 5;
        public const int Skill = 6;
        public const int CycleLeft = 7;
        public const int CycleRight = 8;
        public const int Cancel = 9;
        public const int Start = 10;
        public const int Back = 11;
        public const int LockSkill = 12;
        public const int HotSkill1 = 13;
        public const int HotSkill2 = 14;
        public const int HotSkill3 = 15;
    }
    public class InputInitializer:IInputInitializer
    {

        private CommandDefinition Make(int command, Keys key, Buttons button, int lockContext)
        {
            return new CommandDefinition(command, key, button, lockContext);
        }

        public ICollection<CommandDefinition> GetCommands()
        {
            return new List<CommandDefinition>()
            {
                Make(Commands.MoveUp,Keys.Up,Buttons.LeftThumbstickUp,Contexts.Nonfree),
                Make(Commands.MoveDown,Keys.Down,Buttons.LeftThumbstickDown,Contexts.Nonfree),
                Make(Commands.MoveLeft,Keys.Left,Buttons.LeftThumbstickLeft,Contexts.Nonfree),
                Make(Commands.MoveRight,Keys.Right,Buttons.LeftThumbstickRight,Contexts.Nonfree),
                Make(Commands.Confirm,Keys.Space,Buttons.RightTrigger,Contexts.All),
                Make(Commands.Inventory,Keys.E,Buttons.DPadUp,Contexts.All),
                Make(Commands.Cancel,Keys.R,Buttons.X,Contexts.All),
                Make(Commands.Start,Keys.Enter,Buttons.Start,Contexts.All),
                Make(Commands.Back,Keys.Back,Buttons.Back,Contexts.All),
                Make(Commands.CycleRight,Keys.D,Buttons.RightShoulder,Contexts.All),
                Make(Commands.CycleLeft,Keys.A,Buttons.LeftShoulder,Contexts.All),
                Make(Commands.Skill,Keys.S,Buttons.A,Contexts.All),
                Make(Commands.LockSkill,Keys.RightControl,Buttons.LeftTrigger,Contexts.All),
                Make(Commands.HotSkill1,Keys.D1,Buttons.X,Contexts.All),
                Make(Commands.HotSkill2,Keys.D2,Buttons.Y,Contexts.All),
                Make(Commands.HotSkill3,Keys.D3,Buttons.B,Contexts.All)
            };
        }
    }
}
