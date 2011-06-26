using System;
using System.Collections.Generic;
using System.Linq;
using OGUR.GameObjects;
using OGUR.Items;
using OGUR.Sprites;
using OGUR.Management;
using Microsoft.Xna.Framework;
using OGUR.Creatures;
namespace OGUR.Dungeons
{
    public class Dungeon
    {
        private readonly List<Room> m_rooms = new List<Room>();
        private List<GameplayObject> m_contents = new List<GameplayObject>();
        private readonly GameplayObject[,] dungeon = new GameplayObject[DungeonManager.BlocksWide,DungeonManager.BlocksHigh];
        private Point downSpawnLocation = new Point(0, 0);
        private Point upSpawnLocation = new Point(0, 0);

        public Dungeon()
        {
            Generate();
        }

        public void LoadTiles(bool goingUp)
        {
            GameplayObjectManager.Clear();
            PlaceFloor();
            m_contents.AddRange(DungeonManager.FlushCache());
            var spawn = goingUp ? upSpawnLocation : downSpawnLocation;
            foreach (
                ICreature player in
                    m_contents.Where(tile => tile.GetObjectType() == GameObjectType.CREATURE).Where(
                        creature => ((ICreature) creature).GetCreatureType() == CreatureType.PLAYER))
            {
                player.SetPosition(spawn.X*SpriteInfo.Width, spawn.Y*SpriteInfo.Height);
            }
            foreach (GameplayObject item in m_contents)
            {
                GameplayObjectManager.AddObject(item);
            }
        }

        public void CacheContents()
        {
            foreach (var player in m_contents.Where(o => o.GetObjectType() == GameObjectType.CREATURE).Cast<ICreature>().Where(o => o.GetCreatureType() == CreatureType.PLAYER))
            {
                DungeonManager.AddToCache(player);
                GameplayObjectManager.RemoveObject(player);
            }
            m_contents = new List<GameplayObject>(GameplayObjectManager.GetObjects().Where(o => o.GetObjectType() != GameObjectType.FLOOR).ToList());
        }

        private void Generate()
        {
            GameplayObjectManager.Clear();

            m_rooms.Add(new Room(DungeonManager.BlocksHigh, DungeonManager.BlocksWide, 0, 0));
            PlaceRooms();
            ConvertRoomsToWalls();
            PlaceStairs();
            PlaceCreatures(new Random().Next(3, 10));
            PlaceItems(new Random().Next(0, 5));
            PlaceFloor();
            foreach (GameplayObject tile in dungeon)
            {
                if (tile != null && tile.GetObjectType()!=GameObjectType.FLOOR)
                {
                    m_contents.Add(tile);
                    GameplayObjectManager.AddObject(tile);
                }
            }
            var cache = DungeonManager.FlushCache();
            if (cache.Count() == 0)
            {
                m_contents.Add(CreatureFactory.Create(CreatureType.PLAYER, downSpawnLocation.X*SpriteInfo.Width,
                                                      downSpawnLocation.Y*SpriteInfo.Height));
            }
            else
            {
                foreach(var tile in cache)
                {
                    var player = (ICreature) tile;
                    player.SetPosition(downSpawnLocation.X,downSpawnLocation.Y);
                }
                GameplayObjectManager.AddObjects(cache);
                m_contents.AddRange(cache);
            }
            
            for (int ii = 0; ii < 40;ii++)
            {
                GameplayObjectManager.GetObjects(CreatureType.PLAYER).ElementAt(0).PickupItem(ItemFactory.CreateRandomPlain());
            }
        }

        private void PlaceItems(int amountToPlace)
        {
            while (amountToPlace > 0)
            {
                var rand = new Random();
                amountToPlace--;
                var randomPoint = FindRandomFreeTile();
                dungeon[randomPoint.X, randomPoint.Y] = ItemFactory.CreateRandomPlain(randomPoint.X*SpriteInfo.Width,
                                                                                      randomPoint.Y*SpriteInfo.Height);
            }
        }

        private void PlaceFloor()
        {
            for (int ii = 0; ii < DungeonManager.BlocksWide; ii++)
            {
                for(int jj = 0;jj<DungeonManager.BlocksHigh;jj++)
                {
                    GameplayObjectManager.AddObject(new Floor(ii * SpriteInfo.Width, jj * SpriteInfo.Height));
                }
            }
        }

        private void PlaceCreatures(int amountOfCreatures)
        {
            while(amountOfCreatures>0)
            {
                var rand = new Random();
                amountOfCreatures--;
                var randomPoint = FindRandomFreeTile();
                dungeon[randomPoint.X,randomPoint.Y] = CreatureFactory.Create((CreatureType) rand.Next(1, Enum.GetValues(typeof (CreatureType)).Length-1),
                                           randomPoint.X * SpriteInfo.Width, randomPoint.Y*SpriteInfo.Width);
            }
        }

        private void PlaceRooms()
        {
            var newRooms = new List<Room>();
            var rand = new Random();
            const int maxRoomCount = 15;
            var roomsToPlace = 3 + rand.Next(0, maxRoomCount);
            var attemptCount = 0;
            while (attemptCount < 1000 && roomsToPlace > 0)
            {
                attemptCount++;
                int startX = rand.Next(0, DungeonManager.BlocksWide - 5);
                int startY = rand.Next(0, DungeonManager.BlocksHigh - 5);
                int startWidth = 5 + rand.Next(0, 2);
                int startHeight = 5 + rand.Next(0, 2);
                roomsToPlace--;
                var nextRoom = new Room(startHeight, startWidth, startX, startY);
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

        private Point FindRandomFreeTile()
        {
            var rand = new Random();
            while (true)
            {
                var x = rand.Next(0, DungeonManager.BlocksWide);
                var y = rand.Next(0, DungeonManager.BlocksHigh);
                if (dungeon[x, y].GetObjectType() == GameObjectType.FLOOR)
                {
                    return new Point(x, y);
                }
            }
        }

        private void PlaceStairs()
        {
            downSpawnLocation = FindRandomFreeTile();
            dungeon[downSpawnLocation.X, downSpawnLocation.Y] = new Upstairs(downSpawnLocation.X * SpriteInfo.Width, downSpawnLocation.Y * SpriteInfo.Height);

            upSpawnLocation = FindRandomFreeTile();
            dungeon[upSpawnLocation.X, upSpawnLocation.Y] = new Downstairs(upSpawnLocation.X * SpriteInfo.Width, upSpawnLocation.Y * SpriteInfo.Height);
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
                        if (ii == room.X || jj == room.Y || ii == room.RightSide - 1 || jj == room.BottomSide - 1)
                        {
                            if (!room.Corners.Contains(new Collision.Point(ii, jj)))
                            {
                                if ((ii == room.X && ii > 0) || (ii == room.RightSide && ii < DungeonManager.BlocksWide))
                                {
                                    if (IsFloor(ii - 1, jj) && IsFloor(ii + 1, jj))
                                    {
                                        entrances.Add(new Point(ii, jj));
                                    }
                                }
                                if ((jj == room.Y && jj > 0) ||
                                    (jj == room.BottomSide && jj < DungeonManager.BlocksHigh))
                                {
                                    if (IsFloor(ii, jj - 1) && IsFloor(ii, jj + 1))
                                    {
                                        entrances.Add(new Point(ii, jj));
                                    }
                                }
                            }
                            dungeon[ii, jj] = new Wall(ii*SpriteInfo.Width, jj*SpriteInfo.Height);
                        }
                        else
                        {
                            dungeon[ii, jj] = new Floor(ii*SpriteInfo.Width, jj*SpriteInfo.Height);
                        }
                    }
                }
                if (roomCount > 0 && entrances.Count() > 0)
                {
                    int index = rand.Next(0, entrances.Count() - 1);
                    var entrance = entrances[index];
                    if (dungeon[entrance.X, entrance.Y].GetObjectType() == GameObjectType.WALL)
                    {
                        dungeon[entrance.X, entrance.Y] = new Floor(entrance.X*SpriteInfo.Width,
                                                                    entrance.Y*SpriteInfo.Height);
                    }
                }
                roomCount++;
            }
        }

        private bool IsFloor(int x, int y)
        {
            return dungeon[x, y].GetObjectType() == GameObjectType.FLOOR;
        }
    }
}