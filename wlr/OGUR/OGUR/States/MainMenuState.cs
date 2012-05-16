using SPX.States;
using SPX.Entities;
using OGUR.Creatures;
using OGUR.Dungeons;
using SPX.Text;
using SPX.Util;
using SPX.Core;
using OGUR.Management;
using System;

namespace OGUR.States
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
            Input.SetContext(Contexts.Nonfree, 0);
        }
        public void Update()
        {
            _text.WriteAction(PlayText, 1, 300, 100);
            _text.WriteAction(OptionsText, 1, 300, 200);
            _text.WriteAction(QuitText, 1, 300, 300);


            _selection += (Input.IsPressed(Commands.MoveDown, 0) ? 1 : 0)
                + (Input.IsPressed(Commands.MoveUp, 0) ? -1 : 0);
            _selection %= 3;

            if (Input.IsPressed(Commands.Confirm, 0))
            {
                switch (_selection)
                {
                    case 0:
                        Input.SetContext(Contexts.Free, 0);
                        StateManager.LoadState(new GameplayState());
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