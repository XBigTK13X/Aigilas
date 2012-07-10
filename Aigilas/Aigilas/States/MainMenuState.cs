using SPX.States;
using SPX.Entities;
using Aigilas.Creatures;
using Aigilas.Dungeons;
using SPX.Text;
using SPX.Util;
using SPX.Core;
using Aigilas.Management;
using System;
using Aigilas.IO;
using SPX.IO;

namespace Aigilas.States
{
    public class MainMenuState : State
    {
        private ActionTextHandler _text = new ActionTextHandler();

        private const string PlayText = "Play Game";
        private const string OptionsText = "Options";
        private const string QuitText = "Quit Game";
        private const string SelectionText = "--<";

        private int _selection;

        public MainMenuState()
        {
            Input.SetContext(Contexts.Nonfree, Client.Get().GetFirstPlayerIndex());
        }
        public void Update()
        {
            _text.WriteAction(PlayText, 1, 300, 100);
            _text.WriteAction(OptionsText, 1, 300, 200);
            _text.WriteAction(QuitText, 1, 300, 300);

            _selection += (Input.IsActive(Commands.MoveDown, Client.Get().GetFirstPlayerIndex()) ? 1 : 0)
                + (Input.IsActive(Commands.MoveUp, Client.Get().GetFirstPlayerIndex()) ? -1 : 0);
            _selection %= 3;
            if (_selection < 0)
            {
                _selection = 0;
            }

            if (Client.Get().IsGameStarting())
            {
                for (int ii = 0; ii < Client.Get().GetPlayerCount(); ii++)
                {
                    Input.SetContext(Contexts.Free, ii);
                }            
                StateManager.LoadState(new GameplayState());
            }
            else
            {
                if (Input.IsActive(Commands.Confirm, Client.Get().GetFirstPlayerIndex()))
                {
                    switch (_selection)
                    {
                        case 0:
                            Client.Get().StartGame();
                            return;
                        case 1:
                            StateManager.LoadState(new OptionsState());
                            return;
                        case 2:
                            Environment.Exit(0);
                            return;
                        default: break;
                    }
                }
            }

            _text.WriteAction(SelectionText,1,225,100*(_selection+1));
        }
        public void LoadContent()
        {

        }
        public void Draw()
        {
            _text.Draw();
            _text.Clear();
        }
    }
}