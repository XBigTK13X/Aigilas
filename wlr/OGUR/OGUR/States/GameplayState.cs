using SPX.States;
using SPX.Entities;
using OGUR.Creatures;
using OGUR.Dungeons;
using SPX.Core;
using System;

namespace OGUR.States
{
    public class GameplayState : State
    {
        public GameplayState(OgurSave saveData = null)
        {
            EntityManager.Reset();
            CreatureFactory.ResetPlayerCount();
            if (saveData == null)
            {
                DungeonFactory.Start();
            }
            else
            {
                DungeonFactory.Start(saveData);
            }
            Console.WriteLine(Input.GetPlayerCount());
        }
        public override void Update()
        {
            EntityManager.Update();
        }
        public override void LoadContent()
        {
            EntityManager.LoadContent();
        }
        public override void Draw()
        {
            EntityManager.Draw();
        }
    }
}