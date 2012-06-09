using SPX.States;
using SPX.Entities;
using Agilas.Creatures;
using Agilas.Dungeons;
using SPX.Core;
using System;

namespace Agilas.States
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