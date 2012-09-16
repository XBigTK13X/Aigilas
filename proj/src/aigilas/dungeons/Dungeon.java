package aigilas.dungeons;

import aigilas.creatures.CreatureFactory;
import aigilas.creatures.Player;
import aigilas.entities.*;
import aigilas.gods.GodId;
import aigilas.items.ItemFactory;
import spx.bridge.ActorType;
import spx.bridge.EntityType;
import spx.core.Point2;
import spx.core.RNG;
import spx.core.Settings;
import spx.entities.Entity;
import spx.entities.EntityManager;
import spx.entities.IActor;
import spx.entities.IEntity;
import spx.net.Client;

import java.util.ArrayList;
import java.util.List;

public class Dungeon {
    private static int _blocksHigh = Settings.Get().tileMapHeight;
    private static int _blocksWide = Settings.Get().tileMapWidth;

    private static int enemyCapModifier = 0;
    private static int enemyBaseModifier = 0;
    private int playerCount = Client.Get().GetPlayerCount();

    private List<Room> _rooms = new ArrayList<Room>();
    private List<IEntity> _contents = new ArrayList<IEntity>();
    private IEntity[][] dungeon = new IEntity[_blocksWide][_blocksHigh];
    private Point2 _upSpawnLocation = new Point2(0, 0);
    private Point2 _downSpawnLocation = new Point2(0, 0);

    private static final int startY = 10;

    public Dungeon() {
        Init();
        ConvertRoomsToWalls();
        PlaceAltars();
        PlaceStairs();
        PlaceFloor();
        TransferDungeonState();
    }

    public Dungeon(Point2 upstairsSpawn) {
        enemyCapModifier++;
        enemyBaseModifier = Settings.Get().enemyBase + enemyCapModifier / 5;
        EntityManager.Clear();
        Init();
        PlaceRooms();
        ConvertRoomsToWalls();
        _upSpawnLocation.Copy(upstairsSpawn);
        PlaceStairs();
        PlaceCreatures(RNG.Next(Settings.Get().enemyBase + enemyBaseModifier, Settings.Get().enemyCap + enemyCapModifier));
        PlaceItems(RNG.Next(Settings.Get().itemBase, Settings.Get().itemCap));
        PlaceFloor();
        TransferDungeonState();
    }

    private void PlaceAltars() {
        int startX = 8;
        for (GodId god : GodId.values()) {
            dungeon[startX][startY] = new Altar(new Point2(startX, startY), god);
            startX += 2;
        }
    }

    private List<IEntity> playerCache;

    public void LoadTiles(boolean goingUp) {
        EntityManager.Clear();
        PlaceFloor();
        playerCache = DungeonFactory.FlushCache();
        Point2 spawn = goingUp ? _downSpawnLocation : _upSpawnLocation;
        List<Point2> neighbors = spawn.GetNeighbors();
        for (IEntity player : playerCache) {
            Player pl = ((Player) player);
            pl.SetLocation(GetRandomNeighbor(neighbors));
            _contents.add(pl);
        }
        for (IEntity item : _contents) {
            EntityManager.addObject(item);
        }
    }

    public void CacheContents() {
        for (IActor player : EntityManager.GetActors(ActorType.PLAYER)) {
            DungeonFactory.AddToCache((Entity) player);
            EntityManager.RemoveObject(player);
        }
        _contents = new ArrayList<IEntity>(EntityManager.GetEntitiesToCache());
    }

    private void Init() {
        _rooms.add(new Room(_blocksHigh, _blocksWide, 0, 0));
    }

    private void TransferDungeonState() {
        for (IEntity[] row : dungeon) {
            for (IEntity tile : row) {
                if (tile != null) {
                    if (tile.GetEntityType() != EntityType.FLOOR) {
                        _contents.add(tile);
                    }
                    EntityManager.addObject(tile);
                }
            }
        }

        List<IEntity> cache = DungeonFactory.FlushCache();
        List<Point2> neighbors = _upSpawnLocation.GetNeighbors();

        if (cache.size() == 0) {
            for (int ii = 0; ii < playerCount; ii++) {
                _contents.add(CreatureFactory.Create(ActorType.PLAYER, GetRandomNeighbor(neighbors)));
            }
        } else {
            for (IEntity player : cache) {
                ((Entity) player).SetLocation(GetRandomNeighbor(neighbors));
            }
            EntityManager.addObjects(cache);
            _contents.addAll(cache);
        }
    }

    Point2 neighborTemp = new Point2(0, 0);

    private Point2 GetRandomNeighbor(List<Point2> neighbors) {
        while (neighbors.size() > 0) {
            int neighborIndex = RNG.Next(0, neighbors.size());
            neighborTemp = neighbors.get(neighborIndex);
            neighbors.remove(neighborIndex);
            if (!dungeon[neighborTemp.GridX][neighborTemp.GridY].IsBlocking()) {
                return neighborTemp;
            }
        }
        return null;
    }

    private void PlaceItems(int amountToPlace) {
        while (amountToPlace > 0) {
            amountToPlace--;
            Point2 randomPoint = FindRandomFreeTile();
            dungeon[randomPoint.GridX][randomPoint.GridY] = ItemFactory.CreateRandomPlain(randomPoint);
        }
    }

    private void PlaceFloor() {
        for (int ii = 1; ii < _blocksWide - 1; ii++) {
            for (int jj = 1; jj < _blocksHigh - 1; jj++) {
                EntityManager.addObject(new Floor(new Point2(ii, jj)));
            }
        }
    }

    private void PlaceCreatures(int amountOfCreatures) {
        // = Point2 random = new Point2(FindRandomFreeTile());
        // = dungeon[random.GridX][random.GridY] =
        // = CreatureFactory.Create(ActorType.ENVY, random);
        // = return;
        if (DungeonFactory.GetFloorCount() % Settings.Get().bossLevelMod == 1 && CreatureFactory.BossesRemaining() > 0) {
            Point2 randomPoint = new Point2(FindRandomFreeTile());
            dungeon[randomPoint.GridX][randomPoint.GridY] = CreatureFactory.CreateNextBoss(randomPoint);
        } else {
            while (amountOfCreatures > 0) {
                amountOfCreatures--;
                Point2 randomPoint = new Point2(FindRandomFreeTile());
                dungeon[randomPoint.GridX][randomPoint.GridY] = CreatureFactory.CreateRandom(randomPoint);
            }
        }
    }

    private void PlaceRooms() {
        ArrayList<Room> newRooms = new ArrayList<Room>();
        int roomsToPlace = 3 + RNG.Next(0, Settings.Get().maxRoomCount);
        int attemptCount = 0;
        while (attemptCount < 1000 && roomsToPlace > 0) {
            attemptCount++;
            int startX = RNG.Next(0, _blocksWide - 5);
            int startY = RNG.Next(0, _blocksHigh - 5);
            int startWidth = 5 + RNG.Next(0, 2);
            int startHeight = 5 + RNG.Next(0, 2);
            roomsToPlace--;
            Room nextRoom = new Room(startHeight, startWidth, startX, startY);
            boolean collides = false;
            for (Room room : newRooms) {
                if (room.Collides(nextRoom)) {
                    collides = true;
                }
            }
            if (!collides && !nextRoom.IsBad()) {
                newRooms.add(nextRoom);
            }
        }
        for (Room room : newRooms) {
            _rooms.add(room);
        }
    }

    private Point2 FindRandomFreeTile() {
        while (true) {
            int x = RNG.Next(0, _blocksWide);
            int y = RNG.Next(0, _blocksHigh);
            if (dungeon[x][y].GetEntityType() == EntityType.FLOOR) {
                return new Point2(x, y);
            }
        }
    }

    private void PlaceStairs() {
        PlaceUpstairs();
        PlaceDownstairs();
    }

    private void PlaceDownstairs() {
        _downSpawnLocation.Copy(FindRandomFreeTile());
        dungeon[_downSpawnLocation.GridX][_downSpawnLocation.GridY] = new Downstairs(_downSpawnLocation);
    }

    private void PlaceUpstairs() {
        if (_upSpawnLocation.IsZero()) {
            _upSpawnLocation.Copy(FindRandomFreeTile());
        }
        dungeon[_upSpawnLocation.GridX][_upSpawnLocation.GridY] = new Upstairs(_upSpawnLocation);
    }

    private void ConvertRoomsToWalls() {
        int roomCount = 0;
        ArrayList<PointPoint> dungeonEntrances = new ArrayList<PointPoint>();
        for (Room room : _rooms) {
            ArrayList<PointPoint> entrances = new ArrayList<PointPoint>();
            for (int ii = room.X; ii < room.RightSide; ii++) {
                for (int jj = room.Y; jj < room.BottomSide; jj++) {
                    if (ii == room.X || jj == room.Y || ii == room.RightSide - 1 || jj == room.BottomSide - 1) {
                        if (!room.Corners.contains(new Point2(ii, jj))) {
                            if ((ii == room.X && ii > 0) || (ii == room.RightSide && ii < _blocksWide)) {
                                if (IsFloor(ii - 1, jj) && IsFloor(ii + 1, jj)) {
                                    entrances.add(new PointPoint(ii, jj, true));
                                }
                            }
                            if ((jj == room.Y && jj > 0) || (jj == room.BottomSide && jj < _blocksHigh)) {
                                if (IsFloor(ii, jj - 1) && IsFloor(ii, jj + 1)) {
                                    entrances.add(new PointPoint(ii, jj));
                                }
                            }
                        }
                        dungeon[ii][jj] = EntityFactory.Create(EntityType.WALL, new Point2(ii, jj));
                    } else {
                        dungeon[ii][jj] = EntityFactory.Create(EntityType.FLOOR, new Point2(ii, jj));
                    }
                }
            }
            if (roomCount > 0 && entrances.size() > 0) {
                int index = RNG.Next(0, entrances.size() - 1);
                PointPoint entrance = entrances.get(index);
                if (dungeon[entrance.X][entrance.Y].GetEntityType() != EntityType.FLOOR) {
                    dungeonEntrances.add(entrance);
                }
            }
            roomCount++;
        }
        for (PointPoint entrance : dungeonEntrances) {
            if (entrance.isHorizontal()) {
                for (int ii = 1; ii < _blocksWide - 1; ii++) {
                    Point2 currentTarget = new Point2(ii, entrance.Y);
                    if (dungeon[currentTarget.GridX][currentTarget.GridY].GetEntityType() == EntityType.WALL) {
                        dungeon[currentTarget.GridX][currentTarget.GridY] = EntityFactory.Create(EntityType.FLOOR, currentTarget);
                    }
                }
            } else {
                for (int ii = 1; ii < _blocksHigh - 1; ii++) {
                    Point2 currentTarget = new Point2(entrance.X, ii);
                    if (dungeon[currentTarget.GridX][currentTarget.GridY].GetEntityType() == EntityType.WALL) {
                        dungeon[currentTarget.GridX][currentTarget.GridY] = EntityFactory.Create(EntityType.FLOOR, currentTarget);
                    }
                }
            }
        }
    }

    private boolean IsFloor(int x, int y) {
        return dungeon[x][y].GetEntityType() == EntityType.FLOOR;
    }

    public Point2 getDownstairsLocation() {
        return _downSpawnLocation;
    }
}
