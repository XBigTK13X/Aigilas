using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Input;

namespace SPX.Core
{
    public interface IInputInitializer
    {
        ICollection<CommandDefinition> GetCommands();
    }
    public class Contexts
    {
        public const int All = 0;
        public const int Nonfree = 1;
        public const int Free = 2;
        public const int Inventory = 3;
    }
    public class CommandDefinition
    {
        public CommandDefinition(int command, Keys key, Buttons button, int lockContext)
        {
            Command = command;
            Gamepad = button;
            Keyboard = key;
            LockContext = lockContext;
        }
        public int Command{get;set;}
        public Buttons Gamepad{get;set;}
        public Keys Keyboard{get;set;}
        public int LockContext { get; set; }
    }
    public class Input
    {        
        //Maps a playerId to a context
        private static readonly Dictionary<int, int> m_contexts = new Dictionary<int, int>()
                                                                  {
                                                                      {0, Contexts.Free},
                                                                      {1, Contexts.Free},
                                                                      {2, Contexts.Free},
                                                                      {3, Contexts.Free}
                                                                  };
        //Lists what commands are locked for a given player
        private static readonly List<CommandLock> s_locks = new List<CommandLock>(); 

        //The commands that cannot be used by simply holding down the command's input depending on the given context
        private static readonly Dictionary<int, int> s_lockOnPress = new Dictionary<int, int>();

        private static readonly Dictionary<int, Keys> m_keyboardMapping = new Dictionary<int, Keys>();

        private static readonly Dictionary<int, Buttons> m_gamePadMapping = new Dictionary<int, Buttons>();

        private static readonly List<PlayerIndex> m_playerIndex = new List<PlayerIndex>()
                                                                      {
                                                                          PlayerIndex.One,
                                                                          PlayerIndex.Two,
                                                                          PlayerIndex.Three,
                                                                          PlayerIndex.Four
                                                                      };
        private static readonly Dictionary<int, bool> s_inputs = new Dictionary<int, bool>();
        private static bool s_isInputActive = false;
        private static bool s_isDown = false;

        public static void Setup(IInputInitializer initializer)
        {
            foreach (var command in initializer.GetCommands())
            {
                m_keyboardMapping.Add(command.Command, command.Keyboard);
                m_gamePadMapping.Add(command.Command, command.Gamepad);
                if (command.LockContext >= 0)
                {
                    s_lockOnPress.Add(command.Command, command.LockContext);
                }
            }
        }

        private static bool IsDown(int command, int playerIndex)
        {
            if (!s_inputs.ContainsKey(playerIndex))
            {
                s_inputs.Add(playerIndex, GamePad.GetState(m_playerIndex[playerIndex]).IsConnected);
            } 
            else
            {
                if (s_inputs[playerIndex])
                {
                    s_isDown = GamePad.GetState(m_playerIndex[playerIndex]).IsButtonDown(m_gamePadMapping[command]);
                }
                else
                {
                    s_isDown = Keyboard.GetState().IsKeyDown(m_keyboardMapping[command]);
                }
            }
            return s_isDown;
        }

        public static bool IsPressed(int command, int playerIndex,bool failIfLocked=true)
        {
            
            s_isInputActive = IsDown(command,playerIndex);
            
            if (!s_isInputActive)
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

            if (s_isInputActive)
            {
                if(ShouldLock(command,playerIndex))
                {
                    Lock(command,playerIndex);
                }
            }

            return s_isInputActive;
        }

        private static bool ShouldLock(int command,int playerIndex)
        {
            foreach(var key in s_lockOnPress.Keys)
            {
                if (key == command)
                {
                    if (s_lockOnPress[key] == m_contexts[playerIndex] ||
                        (s_lockOnPress[key] == Contexts.Nonfree && m_contexts[playerIndex] != Contexts.Free) ||
                        s_lockOnPress[key] == Contexts.All)
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        public static void SetContext(int context,int playerIndex)
        {
            m_contexts[playerIndex] = context;
        }
        public static bool IsContext(int context,int playerIndex)
        {
            return m_contexts[playerIndex] == context;
        }
        public static bool IsLocked(int command,int playerIndex)
        {
            foreach (var pair in s_locks)
            {
                if (pair.Command == command && pair.PlayerIndex == playerIndex)
                {
                    return true;
                }
            }
            return false;
        }
        public static void Lock(int command,int playerIndex)
        {
            s_locks.Add(new CommandLock(command,playerIndex));
        }
        public static void Unlock(int command, int playerIndex)
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

        public static void Update()
        {
            for(int ii = 0;ii<s_locks.Count();ii++)
            {
                if (!IsDown(s_locks[ii].Command, s_locks[ii].PlayerIndex))
                {
                    s_locks.Remove(s_locks[ii]);
                    ii--;
                }
            }
        }
        private class CommandLock
        {
            public CommandLock(int command, int playerIndex)
            {
                Command = command;
                PlayerIndex = playerIndex;
            }
            public int Command;
            public int PlayerIndex { get; set; }
        }
    }
}