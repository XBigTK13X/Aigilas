using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using welikerogues.GameObjects;
using welikerogues.Sprites;
using welikerogues.Factory;
using welikerogues.Management;

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
        private void Generate()
        {
            m_contents = new List<GameplayObject>();
            GameplayObject[,] dungeon = new GameplayObject[width, height];
            //Setup an open floor with a stone wall surrounding it
            for (int ii = 0; ii < width; ii++)
            {
                for (int jj = 0; jj < height; jj++)
                {
                    if (ii == 0 || jj == 0 || ii == width - 1 || jj == height - 1)
                    {
                        m_contents.Add(GameplayObjectFactory.Create(GameObjectType.WALL, ii * SpriteInfo.Width, jj * SpriteInfo.Height));
                        GameplayObjectManager.AddObject(m_contents.Last());
                    }
                    else
                    {
                        m_contents.Add(GameplayObjectFactory.Create(GameObjectType.FLOOR, ii * SpriteInfo.Width, jj * SpriteInfo.Height));
                        GameplayObjectManager.AddObject(m_contents.Last());
                    }
                }
            }
            //Add a player
            dungeon[10, 10] = GameplayObjectFactory.Create(GameObjectType.PLAYER, 10*SpriteInfo.Width, 10*SpriteInfo.Height);
            foreach(GameplayObject tile in dungeon)
            {
                if (tile != null)
                {
                    m_contents.Add(tile);
                    GameplayObjectManager.AddObject(tile);
                }
            }
        }
        public void LoadTiles()
        {
            GameplayObjectManager.Clear();
            foreach (GameplayObject item in m_contents)
            {
                GameplayObjectManager.AddObject(item);
            }
        }
    }
}
