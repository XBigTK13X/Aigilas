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
            Skill,
            CycleLeft,
            CycleRight,
            Cancel,
            Start,
            Back
        }

        public enum Contexts
        {
            All,
            Nonfree,
            Free,
            Inventory
        }

        private static readonly List<string> m_playerInputDevices = new List<string>()
                                                                        {
                                                                            "KEYBOARD",
                                                                            "GAMEPAD"
                                                                        };

        private static readonly Dictionary<int, Contexts> m_contexts = new Dictionary<int, Contexts>()
                                                                  {
                                                                      {0, Contexts.Free},
                                                                      {1, Contexts.Free},
                                                                      {2, Contexts.Free},
                                                                      {3, Contexts.Free}

                                                                  };
        private static readonly List<CommandLock> s_locks = new List<CommandLock>(); 

        private static readonly Dictionary<Commands,Contexts> s_lockOnPress = new Dictionary<Commands,Contexts>()
                                                          {
                                                              {Commands.Confirm,Contexts.All},
                                                              {Commands.Inventory,Contexts.All},
                                                              {Commands.Cancel,Contexts.All},
                                                              {Commands.Start,Contexts.All},
                                                              {Commands.Back,Contexts.All},
                                                              {Commands.MoveRight,Contexts.Nonfree},
                                                              {Commands.MoveLeft,Contexts.Nonfree},
                                                              {Commands.MoveUp,Contexts.Nonfree},
                                                              {Commands.MoveDown,Contexts.Nonfree},
                                                              {Commands.CycleLeft,Contexts.All},
                                                              {Commands.CycleRight,Contexts.All},
                                                              {Commands.Skill,Contexts.All}
                                                          }; 

        private static readonly Dictionary<Commands, Keys> m_keyboardMapping = new Dictionary<Commands, Keys>()
                                                                                   {
                                                                                       {Commands.MoveUp, Keys.Up},
                                                                                       {Commands.MoveDown, Keys.Down},
                                                                                       {Commands.MoveRight, Keys.Right},
                                                                                       {Commands.MoveLeft, Keys.Left},
                                                                                       {Commands.Inventory,Keys.E},
                                                                                       {Commands.Confirm, Keys.Space},
                                                                                       {Commands.Cancel,Keys.R},
                                                                                       {Commands.Start,Keys.Enter},
                                                                                       {Commands.Back,Keys.Back},
                                                                                       {Commands.CycleLeft,Keys.A},
                                                                                       {Commands.CycleRight,Keys.D},
                                                                                       {Commands.Skill,Keys.S}
                                                                                   };

        private static readonly Dictionary<Commands, Buttons> m_gamePadMapping = new Dictionary<Commands, Buttons>()
                                                                                     {
                                                                                         {Commands.MoveUp,Buttons.DPadUp},
                                                                                         {Commands.MoveDown,Buttons.DPadDown},
                                                                                         {Commands.MoveRight,Buttons.DPadRight},
                                                                                         {Commands.MoveLeft,Buttons.DPadLeft},
                                                                                         {Commands.Confirm,Buttons.A},
                                                                                         {Commands.Inventory,Buttons.Y},
                                                                                         {Commands.Cancel,Buttons.X},
                                                                                         {Commands.Start,Buttons.Start},
                                                                                         {Commands.Back,Buttons.Back},
                                                                                         {Commands.CycleLeft,Buttons.LeftShoulder},
                                                                                         {Commands.CycleRight,Buttons.RightShoulder},
                                                                                         {Commands.Skill,Buttons.RightTrigger}
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
                if (ShouldLock(command,playerIndex))
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
                if(ShouldLock(command,playerIndex))
                {
                    Lock(command,playerIndex);
                }
            }
            
            return isInputActive;
        }

        private static bool ShouldLock(Commands command,int playerIndex)
        {
            return s_lockOnPress.Where(o => o.Key == command).Where(
                o => o.Value == m_contexts[playerIndex] || (o.Value == Contexts.Nonfree && m_contexts[playerIndex] != Contexts.Free) || o.Value == Contexts.All).Count() > 0;
        }

        public static void SetContext(Contexts context,int playerIndex)
        {
            m_contexts[playerIndex] = context;
        }
        public static bool IsContext(Contexts context,int playerIndex)
        {
            return m_contexts[playerIndex] == context;
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