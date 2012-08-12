package com.aigilas.states;import com.xna.wrapper.*;import java.util.*;import com.spx.entities.*;import com.aigilas.creatures.*;import com.aigilas.dungeons.*;import com.spx.core.*;import com.spx.io.*;import com.spx.states.State;
    public class GameplayState  implements  State
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
