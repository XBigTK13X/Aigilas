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
        public GameplayState()
        {
            EntityManager.Reset();
            CreatureFactory.ResetPlayerCount();
            DungeonFactory.Start();
            Console.WriteLine(Input.GetPlayerCount());
        }
        public void Update()
        {
            EntityManager.Update();
        }
        public void LoadContent()
        {
            EntityManager.LoadContent();
        }
        public void Draw()
        {
            EntityManager.Draw();
        }
    }
}