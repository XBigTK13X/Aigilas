using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Input;

namespace welikerogues.Management
{
    class InputManager
    {
        public enum Commands
        {
            MoveUp,
            MoveDown,
            MoveLeft,
            MoveRight,
            Confirm
        }
        private static readonly List<string> m_playerInputDevices = new List<string>()
        {
            "KEYBOARD",
            "GAMEPAD"
        };
        private static readonly Dictionary<Commands, Keys> m_keyboardMapping = new Dictionary<Commands, Keys>()
        {
            {Commands.MoveUp,Keys.Up},
            {Commands.MoveDown,Keys.Down},
            {Commands.MoveRight,Keys.Right},
            {Commands.MoveLeft,Keys.Left},
            {Commands.Confirm,Keys.Q}
        };

        private static readonly Dictionary<Commands, Buttons> m_gamePadMapping = new Dictionary<Commands, Buttons>()
        {
            {Commands.MoveUp,Buttons.DPadUp},
            {Commands.MoveDown,Buttons.DPadDown},
            {Commands.MoveRight,Buttons.DPadRight},
            {Commands.MoveLeft,Buttons.DPadLeft},
            {Commands.Confirm,Buttons.LeftShoulder}
        };

        private static readonly List<PlayerIndex> m_playerIndex = new List<PlayerIndex>()
        {
            PlayerIndex.One,
            PlayerIndex.Two,
            PlayerIndex.Three,
            PlayerIndex.Four
        };

        public static bool IsPressed(Commands command,int playerIndex)
        {
            string inputMechanism = m_playerInputDevices[playerIndex];
            bool isInputActive = false;
            switch (inputMechanism)
            {
                case "KEYBOARD":
                    isInputActive = Keyboard.GetState().IsKeyDown(m_keyboardMapping[command]);
                    break;
                case "GAMEPAD":
                    isInputActive = GamePad.GetState(m_playerIndex[playerIndex]).IsButtonDown(m_gamePadMapping[command]);
                    break;
                default:
                    throw new Exception("What were you smoking that brought up this error?");
            }
            return isInputActive;
        }
    }
}
