using System;
using System.Collections.Generic;
using System.Linq;
using OGUR.GameObjects;
using OGUR.Items;
using OGUR.Sprites;
using Microsoft.Xna.Framework;
using OGUR.Creatures;

namespace OGUR.Dungeons
{
    public class Dungeon
    {
        private class PointPoint
        {
            public readonly int X;
            public readonly int Y;
            private readonly bool m_isHorizontal;

            public PointPoint(int x, int y, bool destroyHorizontal = false)
            {
                X = x;
                Y = y;
                m_isHorizontal = destroyHorizontal;
            }
            public bool isHorizontal()
            {
                return m_isHorizontal;
            }
        }

        private static readonly int m_blocksHigh = DungeonFactory.BlocksHigh;
        private static readonly int m_blocksWide = DungeonFactory.BlocksWide;

        private readonly List<Room> m_rooms = new List<Room>();
        private List<GameplayObject> m_contents = new List<GameplayObject>();
        private readonly GameplayObject[,] dungeon = new GameplayObject[m_blocksWide,m_blocksHigh];
        private Point downSpawnLocation = new Point(0, 0);
        private Point upSpawnLocation = new Point(0, 0);

        public Dungeon()
        {
            Generate();
        }

        public Dungeon(Location target)
        {
            Init();
            ConvertRoomsToWalls();
            PlaceAltars();
            PlaceStairs();
            PlaceFloor();
            TransferDungeonState();
        }

        private void Generate()
        { 
            GameplayObjectManager.Clear();
            Init();
            PlaceRooms();
            ConvertRoomsToWalls();
            PlaceStairs();
            PlaceCreatures(new Random().Next(3, 10));
            PlaceItems(new Random().Next(0, 5));
            PlaceFloor();
            TransferDungeonState();
        }

        private void PlaceAltars()
        {
            int startX = 8;
            int startY = 10;
            foreach(GodName god in Enum.GetValues(typeof(GodName)))
            {
                dungeon[startX,startY] = new Altar(startX*SpriteInfo.Width,startY*SpriteInfo.Height,god);
                startX += 2;
            }
        }

        public void LoadTiles(bool goingUp)
        {
            GameplayObjectManager.Clear();
            PlaceFloor();
            m_contents.AddRange(DungeonFactory.FlushCache());
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
                DungeonFactory.AddToCache(player);
                GameplayObjectManager.RemoveObject(player);
            }
            m_contents = new List<GameplayObject>(GameplayObjectManager.GetObjects().Where(o => o.GetObjectType() != GameObjectType.FLOOR).ToList());
        }

        private void Init()
        {
            m_rooms.Add(new Room(m_blocksHigh, m_blocksWide, 0, 0));
        }

        private void TransferDungeonState()
        {
            foreach (GameplayObject tile in dungeon)
            {
                if (tile != null)
                {
                    if (tile.GetObjectType() != GameObjectType.FLOOR)
                    {
                        m_contents.Add(tile);
                    }
                    GameplayObjectManager.AddObject(tile);
                }
            }

            var cache = DungeonFactory.FlushCache();
            if (cache.Count() == 0)
            {
                m_contents.Add(CreatureFactory.Create(CreatureType.PLAYER, downSpawnLocation.X * SpriteInfo.Width,
                                                      downSpawnLocation.Y * SpriteInfo.Height));
            }
            else
            {
                foreach (var player in cache.Cast<ICreature>())
                {
                    player.SetPosition(downSpawnLocation.X, downSpawnLocation.Y);
                }
                GameplayObjectManager.AddObjects(cache);
                m_contents.AddRange(cache);
            }

            for (int ii = 0; ii < 40; ii++)
            {
                GameplayObjectManager.GetObjects(CreatureType.PLAYER).ElementAt(0).PickupItem(ItemFactory.CreateRandomPlain());
            }
        }

        private void PlaceItems(int amountToPlace)
        {
            while (amountToPlace > 0)
            {
                amountToPlace--;
                var randomPoint = FindRandomFreeTile();
                dungeon[randomPoint.X, randomPoint.Y] = ItemFactory.CreateRandomPlain(randomPoint.X*SpriteInfo.Width,
                                                                                      randomPoint.Y*SpriteInfo.Height);
            }
        }

        private void PlaceFloor()
        {
            for (int ii = 0; ii < m_blocksWide; ii++)
            {
                for(int jj = 0;jj<m_blocksHigh;jj++)
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
                int startX = rand.Next(0, m_blocksWide - 5);
                int startY = rand.Next(0, m_blocksHigh - 5);
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
                var x = rand.Next(0, m_blocksWide);
                var y = rand.Next(0, m_blocksHigh);
                if (dungeon[x, y].GetObjectType() == GameObjectType.FLOOR)
                {
                    return new Point(x, y);
                }
            }
        }

        private void PlaceStairs()
        {
            PlaceUpstairs();
            PlaceDownstairs();
        }

        private void PlaceDownstairs()
        {
            upSpawnLocation = FindRandomFreeTile();
            dungeon[upSpawnLocation.X, upSpawnLocation.Y] = new Downstairs(upSpawnLocation.X * SpriteInfo.Width, upSpawnLocation.Y * SpriteInfo.Height);
        }

        private void PlaceUpstairs()
        {
            downSpawnLocation = FindRandomFreeTile();
            dungeon[downSpawnLocation.X, downSpawnLocation.Y] = new Upstairs(downSpawnLocation.X * SpriteInfo.Width, downSpawnLocation.Y * SpriteInfo.Height);
        }

        private void ConvertRoomsToWalls()
        {
            int roomCount = 0;
            var dungeonEntrances = new List<PointPoint>();
            foreach (Room room in m_rooms)
            {
                var entrances = new List<PointPoint>();
                for (int ii = room.X; ii < room.RightSide; ii++)
                {
                    for (int jj = room.Y; jj < room.BottomSide; jj++)
                    {
                        if (ii == room.X || jj == room.Y || ii == room.RightSide - 1 || jj == room.BottomSide - 1)
                        {
                            if (!room.Corners.Contains(new Collision.Point(ii, jj)))
                            {
                                if ((ii == room.X && ii > 0) || (ii == room.RightSide && ii < m_blocksWide))
                                {
                                    if (IsFloor(ii - 1, jj) && IsFloor(ii + 1, jj))
                                    {
                                        entrances.Add(new PointPoint(ii, jj,true));
                                    }
                                }
                                if ((jj == room.Y && jj > 0) ||
                                    (jj == room.BottomSide && jj < m_blocksHigh))
                                {
                                    if (IsFloor(ii, jj - 1) && IsFloor(ii, jj + 1))
                                    {
                                        entrances.Add(new PointPoint(ii, jj));
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
                    int index = new Random().Next(0, entrances.Count() - 1);
                    var entrance = entrances[index];
                    if (dungeon[entrance.X, entrance.Y].GetObjectType() != GameObjectType.FLOOR)
                    {
                        dungeonEntrances.Add(entrance);
                    }
                }
                roomCount++;
            }
            foreach (var entrance in dungeonEntrances)
            {
                if (entrance.isHorizontal())
                {
                    for(int ii = 1;ii<m_blocksWide-1;ii++)
                    {
                        var currentTarget = new Point(ii, entrance.Y);
                        if(dungeon[currentTarget.X,currentTarget.Y].GetObjectType()==GameObjectType.WALL)
                        {
                            dungeon[currentTarget.X,currentTarget.Y]=new Floor(currentTarget.X*SpriteInfo.Width,currentTarget.Y*SpriteInfo.Height);
                        }
                    }
                }
                else
                {
                    for (int ii = 1; ii < m_blocksHigh - 1; ii++)
                    {
                        var currentTarget = new Point(entrance.X, ii);
                        if (dungeon[currentTarget.X, currentTarget.Y].GetObjectType() == GameObjectType.WALL)
                        {
                            dungeon[currentTarget.X, currentTarget.Y] = new Floor(currentTarget.X, currentTarget.Y);
                        }
                    }
                }
            }
        }

        private bool IsFloor(int x, int y)
        {
            return dungeon[x, y].GetObjectType() == GameObjectType.FLOOR;
        }
    }
}