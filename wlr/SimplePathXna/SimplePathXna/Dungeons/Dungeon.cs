using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using WeLikeRogues.GameObjects;
using WeLikeRogues.Sprites;
using WeLikeRogues.Factory;
using WeLikeRogues.Management;
using Microsoft.Xna.Framework;

namespace WeLikeRogues.Dungeons
{
    class Dungeon
    {
        List<Room> m_rooms = new List<Room>();
        List<GameplayObject> m_contents = new List<GameplayObject>();

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
            GameplayObject[,] dungeon = new GameplayObject[DungeonManager.BlocksWide, DungeonManager.BlocksHigh];
            //Setup an open floor with a stone wall surrounding it
            m_rooms.Add(new Room(DungeonManager.BlocksHigh,DungeonManager.BlocksWide,0,0));
            PlaceRooms();
            ConvertRoomsToWalls();

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

        private void PlaceRooms()
        {
            var newRooms = new List<Room>();
            Random rand = new Random();
            int maxRoomCount = 15;
            int minRoomCount = 5;
            int roomsToPlace = 3 + rand.Next(0,maxRoomCount);
            int startWidth = 3 + rand.Next(0, 3);
            int startHeight = 3 + rand.Next(0, 3);
            int attemptCount = 0;
            while (attemptCount < 1000 && roomsToPlace > 0)
            {
                attemptCount++;
                int startX = rand.Next(0, DungeonManager.BlocksWide-5);
                int startY = rand.Next(0, DungeonManager.BlocksHigh-5);
                startWidth = 5 + rand.Next(0, 2);
                startHeight = 5 + rand.Next(0, 2);
                roomsToPlace--;
                var nextRoom = new Room(startHeight,startWidth,startX,startY);
                bool collides = false;
                foreach (Room room in newRooms)
                {
                    if (room.Collides(nextRoom))
                    {
                        collides = true;
                    }
                }
                if (!collides && !nextRoom.IsBad())
                {
                    newRooms.Add(nextRoom);
                }
            }
            foreach (Room room in newRooms)
            {
                m_rooms.Add(room);
            }
        }

        private void ConvertRoomsToWalls()
        {
            foreach (Room room in m_rooms)
            {
                int rightSide = room.Width + room.X;
                int bottomSide = room.Y + room.Height;
                for (int ii = room.X; ii < rightSide; ii++)
                {
                    for (int jj = room.Y; jj < bottomSide; jj++)
                    {
                        if (ii == room.X || jj == room.Y || ii == rightSide-1 || jj == bottomSide-1)
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
