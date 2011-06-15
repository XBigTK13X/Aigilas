using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.GameObjects;
using OGUR.Sprites;
using OGUR.Factory;
using OGUR.Management;
using Microsoft.Xna.Framework;
using OGUR.Creatures;

namespace OGUR.Dungeons
{
    class Dungeon
    {
        private List<Room> m_rooms = new List<Room>();
        private List<GameplayObject> m_contents = new List<GameplayObject>();
        private GameplayObject[,] dungeon = new GameplayObject[DungeonManager.BlocksWide, DungeonManager.BlocksHigh];
        private Point downSpawnLocation = new Point(0, 0);
        private Point upSpawnLocation = new Point(0, 0);

        public Dungeon()
        {
            Generate();
        }
        public void LoadTiles(bool goingUp)
        {
            GameplayObjectManager.Clear();
            var spawn = goingUp ? upSpawnLocation : downSpawnLocation;
            foreach (ICreature player in m_contents.Where(tile => tile.GetObjectType() == GameObjectType.CREATURE).Where(creature => ((ICreature)creature).GetCreatureType() == CreatureType.PLAYER))
            {
                
                player.SetPosition(spawn.X * SpriteInfo.Width, spawn.Y * SpriteInfo.Height);
            }
            foreach (GameplayObject item in m_contents)
            {
                GameplayObjectManager.AddObject(item);
            }
        }
        private void Generate()
        {
            GameplayObjectManager.Clear();

            m_rooms.Add(new Room(DungeonManager.BlocksHigh,DungeonManager.BlocksWide,0,0));
            PlaceRooms();
            ConvertRoomsToWalls();
            PlaceStairs();

            
            foreach(GameplayObject tile in dungeon)
            {
                if (tile != null)
                {
                    m_contents.Add(tile);
                    GameplayObjectManager.AddObject(tile);
                }
            }
            m_contents.Add(CreatureFactory.Create(CreatureType.PLAYER, downSpawnLocation.X * SpriteInfo.Width, downSpawnLocation.Y * SpriteInfo.Height));
        }

        private void PlaceRooms()
        {
            var newRooms = new List<Room>();
            var rand = new Random();
            const int maxRoomCount = 15;
            var roomsToPlace = 3 + rand.Next(0,maxRoomCount);
            var attemptCount = 0;
            while (attemptCount < 1000 && roomsToPlace > 0)
            {
                attemptCount++;
                int startX = rand.Next(0, DungeonManager.BlocksWide-5);
                int startY = rand.Next(0, DungeonManager.BlocksHigh-5);
                int startWidth = 5 + rand.Next(0, 2);
                int startHeight = 5 + rand.Next(0, 2);
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

        private void PlaceStairs()
        {
            var rand = new Random();
            while (true)
            {
                int x = rand.Next(0, DungeonManager.BlocksWide);
                int y = rand.Next(0, DungeonManager.BlocksHigh);
                if (dungeon[x,y].GetObjectType()==GameObjectType.FLOOR)
                {
                    dungeon[x, y] = new Upstairs(x * SpriteInfo.Width, y * SpriteInfo.Height);
                    PlaceSpawnPointNearStairs(ref downSpawnLocation,x,y);
                    break;
                }
            }
            while (true)
            {
                int x = rand.Next(0, DungeonManager.BlocksWide);
                int y = rand.Next(0, DungeonManager.BlocksHigh);
                if (dungeon[x, y].GetObjectType() == GameObjectType.FLOOR)
                {
                    dungeon[x, y] = new Downstairs(x * SpriteInfo.Width, y * SpriteInfo.Height);
                    PlaceSpawnPointNearStairs(ref upSpawnLocation, x, y);
                    break;
                }
            }
        }

        private void PlaceSpawnPointNearStairs(ref Point spawn,int x,int y)
        {
            bool playerPlaced = false;
            var rand = new Random();
            int ii = rand.Next(0, 2) - 1;
            int jj = rand.Next(0, 2) - 1;
            while(spawn.X==0&&spawn.Y==0)
            {
                try
                {
                    if (dungeon[x + ii, y + jj].GetObjectType() == GameObjectType.FLOOR)
                    {
                        spawn.X = x + ii;
                        spawn.Y = y + jj;
                        playerPlaced = true;
                    }
                }
                catch (Exception)
                { };
                if (playerPlaced)
                {
                    break;
                }
                
            }
        }

        private void ConvertRoomsToWalls()
        {
            var rand = new Random();
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
                            dungeon[ii,jj] = new Wall(ii * SpriteInfo.Width, jj * SpriteInfo.Height);
                        }
                        else
                        {
                            dungeon[ii,jj] = new Floor(ii * SpriteInfo.Width, jj * SpriteInfo.Height);
                        }
                    }
                }
                if (roomCount > 0 && entrances.Count()>0)
                {
                    int index = rand.Next(0, entrances.Count() - 1);
                    var entrance = entrances[index];
                    if(dungeon[entrance.X, entrance.Y].GetObjectType() == GameObjectType.WALL)
                    {
                        dungeon[entrance.X, entrance.Y] = new Floor(entrance.X * SpriteInfo.Width, entrance.Y*SpriteInfo.Height);
                    }
                }
                roomCount++;
            }
        }

        private bool IsFloor(int x, int y)
        {
            return dungeon[x,y].GetObjectType() == GameObjectType.FLOOR;
        }

    }
}
