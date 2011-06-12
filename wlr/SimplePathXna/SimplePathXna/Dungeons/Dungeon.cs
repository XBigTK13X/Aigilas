using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.GameObjects;
using OGUR.Sprites;
using OGUR.Factory;
using OGUR.Management;
using Microsoft.Xna.Framework;

namespace OGUR.Dungeons
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
            Random rand = new Random();
            int roomCount = 0;
            foreach (Room room in m_rooms)
            {
                var entrances = new List<Point>();
                for (int ii = room.X; ii < room.RightSide; ii++)
                {
                    for (int jj = room.Y; jj < room.BottomSide; jj++)
                    {
                        if (ii == room.X || jj == room.Y || ii == room.RightSide-1 || jj == room.BottomSide-1)
                        {
                            if(!room.Corners.Contains(new Collision.Point(ii,jj)))
                            {
                                if ((ii == room.X && ii > 0) || (ii == room.RightSide && ii < DungeonManager.BlocksWide))
                                {
                                    if(IsFloor(ii - 1, jj) && IsFloor(ii + 1, jj))
                                    {
                                        entrances.Add(new Point(ii, jj));
                                    }
                                }
                                if ((jj == room.Y && jj > 0) || (jj == room.BottomSide && jj < DungeonManager.BlocksHigh))
                                {
                                    if(IsFloor(ii, jj-1) && IsFloor(ii, jj+1))
                                    {
                                        entrances.Add(new Point(ii, jj));
                                    }
                                }
                            }
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
                if (roomCount > 0&&entrances.Count()>0)
                {
                    int index = rand.Next(0, entrances.Count() - 1);
                    var entrance = entrances[index];
                    var tile = GetTilesAt(entrance.X, entrance.Y).Where(o => o.GetObjectType() == GameObjectType.WALL).First();
                    m_contents.Remove(tile);
                    GameplayObjectManager.RemoveObject(tile);
                }
                roomCount++;
            }
        }

        private bool IsFloor(int x, int y)
        {
            return GetTilesAt(x - 1, y).Where(o => o.GetObjectType() == GameObjectType.FLOOR).Count() > 0;
        }

        private IList<GameplayObject> GetTilesAt(int x,int y)
        {
            return m_contents.Where(o => (o.GetPosition().X / SpriteInfo.Width) == x && (o.GetPosition().Y / SpriteInfo.Height) == y).ToList<GameplayObject>();
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
