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
            Console.WriteLine("Generating the dungeon...");
            EntityManager.Reset();
            DungeonFactory.Start();
            Client.Get().DungeonHasLoaded();
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