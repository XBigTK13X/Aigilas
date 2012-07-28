using SPX.States;
using SPX.Entities;
using Aigilas.Creatures;
using Aigilas.Dungeons;
using SPX.Core;
using System;
using SPX.IO;

namespace Aigilas.States
{
    public class GameplayState : State
    {
        public GameplayState()
        {
            EntityManager.Reset();
            DungeonFactory.Start();
        }
        public void Update()
        {
            Client.Get().NextTurn();
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