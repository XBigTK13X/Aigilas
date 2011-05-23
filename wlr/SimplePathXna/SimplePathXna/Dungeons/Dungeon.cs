using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using welikerogues.GameObjects;
using welikerogues.Sprites;
using welikerogues.Factory;

namespace welikerogues.Dungeons
{
    class Dungeon
    {
        static private int width = 30;
        static private int height = 20;
        List<GameplayObject> m_contents;

        public Dungeon()
        {
            Generate();
        }
        public List<GameplayObject> GetContents()
        {
            return m_contents;
        }
        private List<GameplayObject> Generate()
        {
            m_contents = new List<GameplayObject>();
            GameplayObject[,] dungeon = new GameplayObject[width, height];
            //Setup a completely blocked dungeon
            for (int ii = 0; ii < width; ii++)
            {
                for (int jj = 0; jj < height; jj++)
                {
                    dungeon[ii, jj] = GameplayObjectFactory.Create(GameObjectType.WALL,ii * SpriteInfo.Width, jj * SpriteInfo.Height);
                }
            }
            //Add a player
            dungeon[10, 10] = GameplayObjectFactory.Create(GameObjectType.PLAYER, 10*SpriteInfo.Width, 10*SpriteInfo.Height);
            foreach(GameplayObject tile in dungeon)
            {
                m_contents.Add(tile);
            }
            return m_contents;
        }
    }
}
