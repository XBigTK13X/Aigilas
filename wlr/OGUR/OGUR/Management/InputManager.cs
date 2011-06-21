using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Input;

namespace OGUR.Management
{
    public class InputManager
    {
        public enum Commands
        {
            MoveUp,
            MoveDown,
            MoveLeft,
            MoveRight,
            Confirm,
            Inventory,
            InventoryLeft,
            InventoryRight,
            InventoryUp,
            InventoryDown,
            Cancel,
            Start,
            Back
        }
        private static List<CommandLock> s_locks = new List<CommandLock>(); 

        private static List<Commands> s_lockOnPress = new List<Commands>()
                                                          {
                                                              Commands.Confirm,
                                                              Commands.Inventory,
                                                              Commands.InventoryLeft,
                                                              Commands.InventoryRight,
                                                              Commands.InventoryDown,
                                                              Commands.InventoryUp,
                                                              Commands.Cancel,
                                                              Commands.Start,
                                                              Commands.Back
                                                          }; 

        private static readonly List<string> m_playerInputDevices = new List<string>()
                                                                        {
                                                                            "KEYBOARD",
                                                                            "KEYBOARD"
                                                                        };

        private static readonly Dictionary<Commands, Keys> m_keyboardMapping = new Dictionary<Commands, Keys>()
                                                                                   {
                                                                                       {Commands.MoveUp, Keys.Up},
                                                                                       {Commands.MoveDown, Keys.Down},
                                                                                       {Commands.MoveRight, Keys.Right},
                                                                                       {Commands.MoveLeft, Keys.Left},
                                                                                       {Commands.Confirm, Keys.Space},
                                                                                       {Commands.Inventory, Keys.W},
                                                                                       {Commands.InventoryLeft, Keys.S},
                                                                                       {Commands.InventoryRight, Keys.F},
                                                                                       {Commands.InventoryDown, Keys.D},
                                                                                       {Commands.InventoryUp,Keys.E},
                                                                                       {Commands.Cancel,Keys.R},
                                                                                       {Commands.Start,Keys.Enter},
                                                                                       {Commands.Back,Keys.Back}
                                                                                   };

        private static readonly Dictionary<Commands, Buttons> m_gamePadMapping = new Dictionary<Commands, Buttons>()
                                                                                     {
                                                                                         {Commands.MoveUp,Buttons.DPadUp},
                                                                                         {Commands.MoveDown,Buttons.DPadDown},
                                                                                         {Commands.MoveRight,Buttons.DPadRight},
                                                                                         {Commands.MoveLeft,Buttons.DPadLeft},
                                                                                         {Commands.Confirm,Buttons.LeftShoulder},
                                                                                         {Commands.Inventory,Buttons.RightThumbstickDown},
                                                                                         {Commands.InventoryLeft,Buttons.DPadLeft},
                                                                                         {Commands.InventoryRight,Buttons.DPadRight},
                                                                                         {Commands.InventoryDown,Buttons.DPadDown},
                                                                                         {Commands.InventoryUp,Buttons.DPadUp},
                                                                                         {Commands.Cancel,Buttons.X},
                                                                                         {Commands.Start,Buttons.Start},
                                                                                         {Commands.Back,Buttons.Back}
                                                                                     };

        private static readonly List<PlayerIndex> m_playerIndex = new List<PlayerIndex>()
                                                                      {
                                                                          PlayerIndex.One,
                                                                          PlayerIndex.Two,
                                                                          PlayerIndex.Three,
                                                                          PlayerIndex.Four
                                                                      };

        public static bool IsPressed(Commands command, int playerIndex,bool failIfLocked=true)
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

            if(!isInputActive)
            {
                if (s_lockOnPress.Contains(command))
                {
                    Unlock(command, playerIndex);
                }
            }

            if (IsLocked(command, playerIndex) && failIfLocked)
            {
                return false;
            }
            
            if(isInputActive)
            {
                if(s_lockOnPress.Contains(command))
                {
                    Lock(command,playerIndex);
                }
            }
            
            return isInputActive;
        }

        public static bool IsLocked(Commands command,int playerIndex)
        {
            return s_locks.Where(o => o.Command == command && o.PlayerIndex == playerIndex).Count() > 0;
        }
        public static void Lock(Commands command,int playerIndex)
        {
            s_locks.Add(new CommandLock(command,playerIndex));
        }
        public static void Unlock(Commands command, int playerIndex)
        {
            for(int ii = 0;ii<s_locks.Count();ii++)
            {
                if(s_locks[ii].Command==command && s_locks[ii].PlayerIndex==playerIndex)
                {
                    s_locks.Remove(s_locks[ii]);
                    ii--;
                }
            }
        }
    }

    public class CommandLock
    {
        public CommandLock(InputManager.Commands command, int playerIndex)
        {
            Command = command;
            PlayerIndex = playerIndex;
        }
        public InputManager.Commands Command;
        public int PlayerIndex{ get; set; }
    }
}