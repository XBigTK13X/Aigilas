using System;
using System.Collections.Generic;
using System.Linq;
using OGUR.Creatures;
using OGUR.Entities;
using OGUR.Gods;
using OGUR.Items;
using SPX.Core;
using SPX.Entities;

namespace OGUR.Dungeons
{
    public class Dungeon
    {
        private static readonly int _blocksHigh = DungeonFactory.BlocksHigh;
        private static readonly int _blocksWide = DungeonFactory.BlocksWide;

        //Top level game config
        private const int playerCount = 1;
        private const int enemyCap = 5;
        private const int enemyBase = 2;
        private const int itemCap = 4;
        private const int itemBase = 1;
        private const int startingItemAmount = 55;

        private static int enemyCapModifier = 0;
        private static int enemyBaseModifier = 0;

        private readonly List<Room> _rooms = new List<Room>();
        private List<IEntity> _contents = new List<IEntity>();
        private readonly IEntity[,] dungeon = new IEntity[_blocksWide,_blocksHigh];
        private Point2 downSpawnLocation = new Point2(0, 0);
        private Point2 upSpawnLocation = new Point2(0, 0);
        private static readonly Random rand = new Random();

        public Dungeon()
        {
            Generate();
        }

        public Dungeon(int target)
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
            enemyCapModifier++;
            enemyBaseModifier = enemyBase + (int)(enemyCapModifier / 5);
            EntityManager.Clear();
            Init();
            PlaceRooms();
            ConvertRoomsToWalls();
            PlaceStairs();
            PlaceCreatures(rand.Next(enemyBase+enemyBaseModifier, enemyCap+enemyCapModifier));
            PlaceItems(rand.Next(itemBase, itemCap));
            PlaceFloor();
            TransferDungeonState();
        }

        private void PlaceAltars()
        {
            var startX = 8;
            const int startY = 10;
            foreach(var god in GodId.Values)
            {
                dungeon[startX,startY] = new Altar(new Point2(startX,startY),god);
                startX += 2;
            }
        }

        private List<Entity> playerCache;
        public void LoadTiles(bool goingUp)
        {
            EntityManager.Clear();
            PlaceFloor();
            playerCache = DungeonFactory.FlushCache();
            var spawn = goingUp ? upSpawnLocation : downSpawnLocation;
            var neighbors = spawn.GetNeighbors();
            foreach (ICreature player in playerCache)
            {
                player.SetLocation(GetRandomNeighbor(ref neighbors));
                _contents.Add(player);
            }
            foreach (var item in _contents)
            {
                EntityManager.AddObject(item);
            }
        }

        public void CacheContents()
        {
            foreach (var player in EntityManager.GetActors(OgurActorType.PLAYER))
            {
                DungeonFactory.AddToCache(player as Entity);
                EntityManager.RemoveObject(player);
            }
            _contents = new List<IEntity>(EntityManager.GetEntitiesToCache());
        }

        private void Init()
        {
            _rooms.Add(new Room(_blocksHigh, _blocksWide, 0, 0));
        }

        private void TransferDungeonState()
        {
            foreach (var tile in dungeon)
            {
                if (tile != null)
                {
                    if (tile.GetEntityType() != OGUR.EntityType.FLOOR)
                    {
                        _contents.Add(tile);
                    }
                    EntityManager.AddObject(tile);
                }
            }

            var cache = DungeonFactory.FlushCache();
            var neighbors = downSpawnLocation.GetNeighbors();
            
            if (cache.Count() == 0)
            {
                for (int ii = 0; ii < playerCount; ii++)
                {
                    _contents.Add(CreatureFactory.Create(OgurActorType.PLAYER, GetRandomNeighbor(ref neighbors)));
                }
            }
            else
            {
                foreach (var player in cache.Cast<ICreature>())
                {
                    player.SetLocation(GetRandomNeighbor(ref neighbors));
                }
                EntityManager.AddObjects(cache);
                _contents.AddRange(cache);
            }
            
            //Give player random objects
            for (int ii = 0; ii < startingItemAmount; ii++)
            {
                (EntityManager.GetActors(OgurActorType.PLAYER).ElementAt(rand.Next(playerCount)) as ICreature).PickupItem(ItemFactory.CreateRandomPlain());
            }
           
        }

        Point2 neighborTemp = new Point2(0, 0);
        private Point2 GetRandomNeighbor(ref List<Point2> neighbors)
        {
            while (neighbors.Count() > 0)
            {
                int neighborIndex = rand.Next(0, neighbors.Count());
                neighborTemp = neighbors[neighborIndex];
                neighbors.RemoveAt(neighborIndex);
                if (!dungeon[neighborTemp.GridX,neighborTemp.GridY].IsBlocking())
                {
                    return neighborTemp;
                }
            }
            return null;
        }

        private void PlaceItems(int amountToPlace)
        {
            while (amountToPlace > 0)
            {
                amountToPlace--;
                var randomPoint = FindRandomFreeTile();
                dungeon[randomPoint.GridX, randomPoint.GridY] = ItemFactory.CreateRandomPlain(randomPoint);
            }
        }

        private void PlaceFloor()
        {
            for (var ii = 1; ii < _blocksWide-1; ii++)
            {
                for(var jj = 1;jj<_blocksHigh-1;jj++)
                {
                    EntityManager.AddObject(new Floor(new Point2(ii,jj)));
                }
            }
        }

        private void PlaceCreatures(int amountOfCreatures)
        {
            while(amountOfCreatures>0)
            {
                amountOfCreatures--;
                var randomPoint = new Point2(FindRandomFreeTile());
                dungeon[randomPoint.GridX, randomPoint.GridY] = CreatureFactory.CreateRandom(randomPoint);
            }
        }

        private void PlaceRooms()
        {
            var newRooms = new List<Room>();
            const int maxRoomCount = 15;
            var roomsToPlace = 3 + rand.Next(0, maxRoomCount);
            var attemptCount = 0;
            while (attemptCount < 1000 && roomsToPlace > 0)
            {
                attemptCount++;
                var startX = rand.Next(0, _blocksWide - 5);
                var startY = rand.Next(0, _blocksHigh - 5);
                var startWidth = 5 + rand.Next(0, 2);
                var startHeight = 5 + rand.Next(0, 2);
                roomsToPlace--;
                var nextRoom = new Room(startHeight, startWidth, startX, startY);
                var collides = false;
                if(newRooms.Any(room => room.Collides(nextRoom)))
                {
                    collides = true;
                }
                if (!collides && !nextRoom.IsBad())
                {
                    newRooms.Add(nextRoom);
                }
            }
            foreach (var room in newRooms)
            {
                _rooms.Add(room);
            }
        }

        private Point2 FindRandomFreeTile()
        {
            while (true)
            {
                var x = rand.Next(0, _blocksWide);
                var y = rand.Next(0, _blocksHigh);
                if (dungeon[x, y].GetEntityType() == OGUR.EntityType.FLOOR)
                {
                    return new Point2(x, y);
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
            upSpawnLocation.Copy(FindRandomFreeTile());
            dungeon[upSpawnLocation.GridX, upSpawnLocation.GridY] = new Downstairs(upSpawnLocation);
        }

        private void PlaceUpstairs()
        {
            downSpawnLocation.Copy(FindRandomFreeTile());
            dungeon[downSpawnLocation.GridX, downSpawnLocation.GridY] = new Upstairs(downSpawnLocation);
        }

         private void ConvertRoomsToWalls()
        {
            var roomCount = 0;
            var dungeonEntrances = new List<PointPoint>();
            foreach (var room in _rooms)
            {
                var entrances = new List<PointPoint>();
                for (var ii = room.X; ii < room.RightSide; ii++)
                {
                    for (var jj = room.Y; jj < room.BottomSide; jj++)
                    {
                        if (ii == room.X || jj == room.Y || ii == room.RightSide - 1 || jj == room.BottomSide - 1)
                        {
                            if (!room.Corners.Contains(new Point2(ii, jj)))
                            {
                                if ((ii == room.X && ii > 0) || (ii == room.RightSide && ii < _blocksWide))
                                {
                                    if (IsFloor(ii - 1, jj) && IsFloor(ii + 1, jj))
                                    {
                                        entrances.Add(new PointPoint(ii, jj,true));
                                    }
                                }
                                if ((jj == room.Y && jj > 0) ||
                                    (jj == room.BottomSide && jj < _blocksHigh))
                                {
                                    if (IsFloor(ii, jj - 1) && IsFloor(ii, jj + 1))
                                    {
                                        entrances.Add(new PointPoint(ii, jj));
                                    }
                                }
                            }
                            dungeon[ii, jj] = EntityFactory.Create(OGUR.EntityType.WALL, new Point2(ii, jj));
                        }
                        else
                        {
                            dungeon[ii, jj] = EntityFactory.Create(OGUR.EntityType.FLOOR, new Point2(ii, jj));
                        }
                    }
                }
                if (roomCount > 0 && entrances.Count() > 0)
                {
                    var index = new Random().Next(0, entrances.Count() - 1);
                    var entrance = entrances[index];
                    if (dungeon[entrance.X, entrance.Y].GetEntityType() != OGUR.EntityType.FLOOR)
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
                    for(var ii = 1;ii<_blocksWide-1;ii++)
                    {
                        var currentTarget = new Point2(ii, entrance.Y);
                        if(dungeon[currentTarget.GridX,currentTarget.GridY].GetEntityType()==OGUR.EntityType.WALL)
                        {
                            dungeon[currentTarget.GridX, currentTarget.GridY] = EntityFactory.Create(OGUR.EntityType.FLOOR, currentTarget);
                        }
                    }
                }
                else
                {
                    for (var ii = 1; ii < _blocksHigh - 1; ii++)
                    {
                        var currentTarget = new Point2(entrance.X, ii);
                        if (dungeon[currentTarget.GridX, currentTarget.GridY].GetEntityType() == OGUR.EntityType.WALL)
                        {
                            dungeon[currentTarget.GridX, currentTarget.GridY] = EntityFactory.Create(OGUR.EntityType.FLOOR, currentTarget);
                        }
                    }
                }
            }
        }

        private bool IsFloor(int x, int y)
        {
            return dungeon[x, y].GetEntityType() == OGUR.EntityType.FLOOR;
        }
    }
}