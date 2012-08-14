package com.aigilas.dungeons;import com.xna.wrapper.*;import java.util.*;import com.aigilas.creatures.*;import com.aigilas.entities.*;import com.aigilas.gods.*;import com.aigilas.items.*;import com.spx.core.*;import com.spx.entities.*;import com.spx.io.*;import com.spx.devtools.*;
    public class Dungeon
    {
        private static int _blocksHigh = DungeonFactory.BlocksHigh;
        private static int _blocksWide = DungeonFactory.BlocksWide;

        //Top level game config
        private int playerCount = Client.Get().GetPlayerCount();
        private static final int enemyCap = 5;
        private static final int enemyBase = 2;
        private static final int itemCap = 4;
        private static final int itemBase = 1;
        private static final int startingItemAmount = 55;
        private static final int bossLevelMod = 2;        private static final int maxRoomCount = 15;

        private static int enemyCapModifier = 0;
        private static int enemyBaseModifier = 0;

        private List<Room> _rooms = new ArrayList<Room>();
        private List<IEntity> _contents = new ArrayList<IEntity>();
        private IEntity[][] dungeon = new IEntity[_blocksWide][_blocksHigh];
        private Point2 downSpawnLocation = new Point2(0, 0);
        private Point2 upSpawnLocation = new Point2(0, 0);

        private static boolean _firstDungeonMade;

        public Dungeon() throws Exception
        {
            if (!_firstDungeonMade)
            {
                Init();
                ConvertRoomsToWalls();
                PlaceAltars();
                PlaceStairs();
                PlaceFloor();
                TransferDungeonState();
                _firstDungeonMade = true;
            }
            else
            {
                enemyCapModifier++;
                enemyBaseModifier = enemyBase + (int)(enemyCapModifier / 5);
                EntityManager.Clear();
                Init();
                PlaceRooms();
                ConvertRoomsToWalls();
                PlaceStairs();
                PlaceCreatures(RNG.Next(enemyBase + enemyBaseModifier, enemyCap + enemyCapModifier));
                PlaceItems(RNG.Next(itemBase, itemCap));
                PlaceFloor();
                TransferDungeonState();                
            }
        }
        static final int startY = 10;
        private void PlaceAltars()
        {
            int startX = 8;         
            for(int god:GodId.Values)
            {
                dungeon[startX][startY] = new Altar(new Point2(startX,startY),god);
                startX += 2;
            }
        }

        private List<IEntity> playerCache;
        public void LoadTiles(boolean goingUp)
        {
            EntityManager.Clear();
            PlaceFloor();
            playerCache = DungeonFactory.FlushCache();
            Point2 spawn = goingUp ? upSpawnLocation : downSpawnLocation;
            List<Point2> neighbors = spawn.GetNeighbors();
            for (IEntity player:playerCache)
            {            	Player pl = ((Player)player);
                pl.SetLocation(GetRandomNeighbor(neighbors));
                _contents.add(pl);
            }
            for (IEntity item:_contents)
            {
                EntityManager.addObject(item);
            }
        }

        public void CacheContents()
        {
            for (IActor player:EntityManager.GetActors(AigilasActorType.PLAYER))
            {
                DungeonFactory.AddToCache((Entity)player);
                EntityManager.RemoveObject(player);
            }
            _contents = new ArrayList<IEntity>(EntityManager.GetEntitiesToCache());
        }

        private void Init()
        {
            _rooms.add(new Room(_blocksHigh, _blocksWide, 0, 0));
        }

        private void TransferDungeonState() throws Exception
        {
            for (IEntity[] row:dungeon)
            {            	for(IEntity tile: row){
	                if (tile != null)
	                {
	                    if (tile.GetEntityType() != com.aigilas.EntityType.FLOOR)
	                    {
	                        _contents.add(tile);
	                    }
	                    EntityManager.addObject(tile);
	                }            	}
            }

            List<IEntity> cache = DungeonFactory.FlushCache();
            List<Point2> neighbors = downSpawnLocation.GetNeighbors();
            
            if (cache.size() == 0)
            {
                for (int ii = 0; ii < playerCount; ii++)
                {
                    _contents.add(CreatureFactory.Create(AigilasActorType.PLAYER, GetRandomNeighbor(neighbors)));
                }
            }
            else
            {
                for (IEntity player:cache)
                {
                    ((Entity) player).SetLocation(GetRandomNeighbor(neighbors));
                }
                EntityManager.addObjects(cache);
                _contents.addAll(cache);
            }           
        }

        Point2 neighborTemp = new Point2(0, 0);
        private Point2 GetRandomNeighbor(List<Point2> neighbors)
        {
            while (neighbors.size() > 0)
            {
                int neighborIndex = RNG.Next(0, neighbors.size());
                neighborTemp = neighbors.get(neighborIndex);
                neighbors.remove(neighborIndex);
                if (!dungeon[neighborTemp.GridX][neighborTemp.GridY].IsBlocking())
                {
                    return neighborTemp;
                }
            }
            return null;
        }

        private void PlaceItems(int amountToPlace) throws Exception
        {
            while (amountToPlace > 0)
            {
                amountToPlace--;
                Point2 randomPoint = FindRandomFreeTile();
                dungeon[randomPoint.GridX][randomPoint.GridY] = ItemFactory.CreateRandomPlain(randomPoint);
            }
        }

        private void PlaceFloor()
        {
            for (int ii = 1; ii < _blocksWide-1; ii++)
            {
                for(int jj = 1;jj<_blocksHigh-1;jj++)
                {
                    EntityManager.addObject((IEntity)new Floor(new Point2(ii,jj)));
                }
            }
        }

        private void PlaceCreatures(int amountOfCreatures) throws Exception
        {
            //Point2 random = new Point2(FindRandomFreeTile());
            //dungeon[random.GridX][random.GridY] = CreatureFactory.Create(AigilasActorType.ENVY, random);
            //return;
            if (DungeonFactory.GetFloorCount() % bossLevelMod == 1 && CreatureFactory.BossesRemaining() > 0)
            {
                Point2 randomPoint = new Point2(FindRandomFreeTile());
                dungeon[randomPoint.GridX][randomPoint.GridY] = CreatureFactory.CreateNextBoss(randomPoint);
            }
            else
            {
                while (amountOfCreatures > 0)
                {
                    amountOfCreatures--;
                    Point2 randomPoint = new Point2(FindRandomFreeTile());
                    dungeon[randomPoint.GridX][randomPoint.GridY] = CreatureFactory.CreateRandom(randomPoint);
                }
            }
        }
        
        private void PlaceRooms()
        {
            ArrayList<Room> newRooms = new ArrayList<Room>();            
            int roomsToPlace = 3 + RNG.Next(0, maxRoomCount);
            int attemptCount = 0;
            while (attemptCount < 1000 && roomsToPlace > 0)
            {
                attemptCount++;
                int startX = RNG.Next(0, _blocksWide - 5);
                int startY = RNG.Next(0, _blocksHigh - 5);
                int startWidth = 5 + RNG.Next(0, 2);
                int startHeight = 5 + RNG.Next(0, 2);
                roomsToPlace--;
                Room nextRoom = new Room(startHeight, startWidth, startX, startY);
                boolean collides = false;                for(Room room:newRooms)                {                	if(room.Collides(nextRoom)){                		collides = true;                	}                }
                if (!collides && !nextRoom.IsBad())
                {
                    newRooms.add(nextRoom);
                }
            }
            for (Room room:newRooms)
            {
                _rooms.add(room);
            }
        }

        private Point2 FindRandomFreeTile()
        {
            while (true)
            {
                int x = RNG.Next(0, _blocksWide);
                int y = RNG.Next(0, _blocksHigh);
                if (dungeon[x][y].GetEntityType() == com.aigilas.EntityType.FLOOR)
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
            dungeon[upSpawnLocation.GridX][upSpawnLocation.GridY] = new Downstairs(upSpawnLocation);
        }

        private void PlaceUpstairs()
        {
            downSpawnLocation.Copy(FindRandomFreeTile());
            dungeon[downSpawnLocation.GridX][downSpawnLocation.GridY] = new Upstairs(downSpawnLocation);
        }

         private void ConvertRoomsToWalls()
        {
            int roomCount = 0;
            ArrayList<PointPoint> dungeonEntrances = new ArrayList<PointPoint>();
            for (Room room:_rooms)
            {
                ArrayList<PointPoint> entrances = new ArrayList<PointPoint>();
                for (int ii = room.X; ii < room.RightSide; ii++)
                {
                    for (int jj = room.Y; jj < room.BottomSide; jj++)
                    {
                        if (ii == room.X || jj == room.Y || ii == room.RightSide - 1 || jj == room.BottomSide - 1)
                        {
                            if (!room.Corners.contains(new Point2(ii, jj)))
                            {
                                if ((ii == room.X && ii > 0) || (ii == room.RightSide && ii < _blocksWide))
                                {
                                    if (IsFloor(ii - 1, jj) && IsFloor(ii + 1, jj))
                                    {
                                        entrances.add(new PointPoint(ii, jj,true));
                                    }
                                }
                                if ((jj == room.Y && jj > 0) ||
                                    (jj == room.BottomSide && jj < _blocksHigh))
                                {
                                    if (IsFloor(ii, jj - 1) && IsFloor(ii, jj + 1))
                                    {
                                        entrances.add(new PointPoint(ii, jj));
                                    }
                                }
                            }
                            dungeon[ii][jj] = EntityFactory.Create(com.aigilas.EntityType.WALL, new Point2(ii, jj));
                        }
                        else
                        {
                            dungeon[ii][jj] = EntityFactory.Create(com.aigilas.EntityType.FLOOR, new Point2(ii, jj));
                        }
                    }
                }
                if (roomCount > 0 && entrances.size() > 0)
                {
                    int index = RNG.Next(0, entrances.size() - 1);
                    PointPoint entrance = entrances.get(index);
                    if (dungeon[entrance.X][entrance.Y].GetEntityType() != com.aigilas.EntityType.FLOOR)
                    {
                        dungeonEntrances.add(entrance);
                    }
                }
                roomCount++;
            }
            for (PointPoint entrance:dungeonEntrances)
            {
                if (entrance.isHorizontal())
                {
                    for(int ii = 1;ii<_blocksWide-1;ii++)
                    {
                        Point2 currentTarget = new Point2(ii, entrance.Y);
                        if(dungeon[currentTarget.GridX][currentTarget.GridY].GetEntityType()==com.aigilas.EntityType.WALL)
                        {
                            dungeon[currentTarget.GridX][currentTarget.GridY] = EntityFactory.Create(com.aigilas.EntityType.FLOOR, currentTarget);
                        }
                    }
                }
                else
                {
                    for (int ii = 1; ii < _blocksHigh - 1; ii++)
                    {
                        Point2 currentTarget = new Point2(entrance.X, ii);
                        if (dungeon[currentTarget.GridX][currentTarget.GridY].GetEntityType() == com.aigilas.EntityType.WALL)
                        {
                            dungeon[currentTarget.GridX][currentTarget.GridY] = EntityFactory.Create(com.aigilas.EntityType.FLOOR, currentTarget);
                        }
                    }
                }
            }
        }

        private boolean IsFloor(int x, int y)
        {
            return dungeon[x][y].GetEntityType() == com.aigilas.EntityType.FLOOR;
        }
    }
