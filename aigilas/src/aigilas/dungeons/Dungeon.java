package aigilas.dungeons;

import aigilas.creatures.CreatureFactory;
import aigilas.creatures.Player;
import aigilas.entities.*;
import aigilas.gods.GodId;
import aigilas.items.ItemFactory;
import sps.bridge.ActorType;
import sps.bridge.EntityType;
import sps.core.Point2;
import sps.core.RNG;
import sps.core.Settings;
import sps.entities.Entity;
import sps.entities.EntityManager;
import sps.entities.IActor;
import sps.entities.IEntity;
import sps.net.Client;

import java.util.ArrayList;
import java.util.List;

public class Dungeon {
    private static final int _blocksHigh = Settings.get().tileMapHeight;
    private static final int _blocksWide = Settings.get().tileMapWidth;

    private static int enemyCapModifier = 0;
    private static int enemyBaseModifier = 0;
    private final int playerCount = Client.get().getPlayerCount();

    private final List<Room> _rooms = new ArrayList<>();
    private List<IEntity> _contents = new ArrayList<>();
    private final IEntity[][] dungeon = new IEntity[_blocksWide][_blocksHigh];
    private final Point2 _upSpawnLocation = new Point2(0, 0);
    private final Point2 _downSpawnLocation = new Point2(0, 0);

    public static void reset() {
        enemyBaseModifier = 0;
        enemyCapModifier = 0;
    }

    private static final int startY = 10;

    public Dungeon() {
        init();
        convertRoomsToWalls();
        placeAltars();
        placeStairs();
        placeFloor();
        transferDungeonState();
    }

    public Dungeon(Point2 upstairsSpawn) {
        enemyCapModifier++;
        enemyBaseModifier = Settings.get().enemyBase + enemyCapModifier / 5;
        EntityManager.get().clear();
        init();
        placeRooms();
        convertRoomsToWalls();
        _upSpawnLocation.copy(upstairsSpawn);
        placeStairs();
        int enemiesToPlace = RNG.next(Settings.get().enemyBase + enemyBaseModifier, Settings.get().enemyCap + enemyCapModifier);
        if (enemiesToPlace <= 0) {
            enemiesToPlace = 1;
        }
        placeCreatures(enemiesToPlace);
        placeItems(RNG.next(Settings.get().itemBase, Settings.get().itemCap));
        placeFloor();
        transferDungeonState();
    }

    private void placeAltars() {
        int startX = 8;
        for (GodId god : GodId.values()) {
            dungeon[startX][startY] = new Altar(new Point2(startX, startY), god);
            startX += 2;
        }
    }

    private List<IEntity> playerCache;

    public void loadTiles(boolean goingUp) {
        EntityManager.get().clear();
        placeFloor();
        playerCache = DungeonFactory.flushCache();
        Point2 spawn = goingUp ? _downSpawnLocation : _upSpawnLocation;
        List<Point2> neighbors = spawn.getNeighbors();
        for (IEntity player : playerCache) {
            Player pl = ((Player) player);
            pl.setLocation(getRandomNeighbor(neighbors));
            _contents.add(pl);
        }
        for (IEntity item : _contents) {
            EntityManager.get().addObject(item);
        }
    }

    public void cacheContents() {
        for (IActor player : EntityManager.get().getActors(ActorType.PLAYER)) {
            DungeonFactory.addToCache((Entity) player);
            EntityManager.get().removeObject(player);
        }
        _contents = new ArrayList<>(EntityManager.get().getEntitiesToCache());
    }

    private void init() {
        _rooms.add(new Room(_blocksHigh, _blocksWide, 0, 0));
    }

    private void transferDungeonState() {
        for (IEntity[] row : dungeon) {
            for (IEntity tile : row) {
                if (tile != null) {
                    if (tile.getEntityType() != EntityType.FLOOR) {
                        _contents.add(tile);
                    }
                    EntityManager.get().addObject(tile);
                }
            }
        }

        List<IEntity> cache = DungeonFactory.flushCache();
        List<Point2> neighbors = _upSpawnLocation.getNeighbors();

        if (cache.size() == 0) {
            for (int ii = 0; ii < playerCount; ii++) {
                _contents.add(CreatureFactory.create(ActorType.PLAYER, getRandomNeighbor(neighbors)));
            }
        }
        else {
            for (IEntity player : cache) {
                ((Entity) player).setLocation(getRandomNeighbor(neighbors));
            }
            EntityManager.get().addObjects(cache);
            _contents.addAll(cache);
        }
    }

    Point2 neighborTemp = new Point2(0, 0);

    private Point2 getRandomNeighbor(List<Point2> neighbors) {
        while (neighbors.size() > 0) {
            int neighborIndex = RNG.next(0, neighbors.size());
            neighborTemp = neighbors.get(neighborIndex);
            neighbors.remove(neighborIndex);
            if (!dungeon[neighborTemp.GridX][neighborTemp.GridY].isBlocking()) {
                return neighborTemp;
            }
        }
        return null;
    }

    private void placeItems(int amountToPlace) {
        while (amountToPlace > 0) {
            amountToPlace--;
            Point2 randomPoint = findRandomFreeTile();
            dungeon[randomPoint.GridX][randomPoint.GridY] = ItemFactory.createRandomPlain(randomPoint);
        }
    }

    private void placeFloor() {
        for (int ii = 1; ii < _blocksWide - 1; ii++) {
            for (int jj = 1; jj < _blocksHigh - 1; jj++) {
                EntityManager.get().addObject(new Floor(new Point2(ii, jj)));
            }
        }
    }

    private void placeCreatures(int amountOfCreatures) {
        // $$$ Easiest way to test specific bosses
        // = Point2 random = new Point2(findRandomFreeTile());
        // = dungeon[random.GridX][random.GridY] =
        // = CreatureFactory.create(ActorType.ENVY, random);
        // = return;
        if (DungeonFactory.getFloorCount() % Settings.get().bossLevelMod == 1 && CreatureFactory.bossesRemaining() > 0) {
            Point2 randomPoint = new Point2(findRandomFreeTile());
            dungeon[randomPoint.GridX][randomPoint.GridY] = CreatureFactory.createNextBoss(randomPoint);
        }
        else {
            while (amountOfCreatures > 0) {
                amountOfCreatures--;
                Point2 randomPoint = new Point2(findRandomFreeTile());
                dungeon[randomPoint.GridX][randomPoint.GridY] = CreatureFactory.createRandom(randomPoint);
            }
        }
    }

    private void placeRooms() {
        ArrayList<Room> newRooms = new ArrayList<>();
        int roomsToPlace = 3 + RNG.next(0, Settings.get().maxRoomCount);
        int attemptCount = 0;
        while (attemptCount < 1000 && roomsToPlace > 0) {
            attemptCount++;
            int startX = RNG.next(0, _blocksWide - 5);
            int startY = RNG.next(0, _blocksHigh - 5);
            int startWidth = 5 + RNG.next(0, 2);
            int startHeight = 5 + RNG.next(0, 2);
            roomsToPlace--;
            Room nextRoom = new Room(startHeight, startWidth, startX, startY);
            boolean collides = false;
            for (Room room : newRooms) {
                if (room.collides(nextRoom)) {
                    collides = true;
                }
            }
            if (!collides && !nextRoom.isBad()) {
                newRooms.add(nextRoom);
            }
        }
        for (Room room : newRooms) {
            _rooms.add(room);
        }
    }

    private Point2 findRandomFreeTile() {
        while (true) {
            int x = RNG.next(0, _blocksWide);
            int y = RNG.next(0, _blocksHigh);
            if (dungeon[x][y].getEntityType() == EntityType.FLOOR) {
                return new Point2(x, y);
            }
        }
    }

    private void placeStairs() {
        placeUpstairs();
        placeDownstairs();
    }

    private void placeDownstairs() {
        _downSpawnLocation.copy(findRandomFreeTile());
        dungeon[_downSpawnLocation.GridX][_downSpawnLocation.GridY] = new Downstairs(_downSpawnLocation);
    }

    private void placeUpstairs() {
        if (_upSpawnLocation.isZero()) {
            _upSpawnLocation.copy(findRandomFreeTile());
        }
        dungeon[_upSpawnLocation.GridX][_upSpawnLocation.GridY] = new Upstairs(_upSpawnLocation);
    }

    private void convertRoomsToWalls() {
        int roomCount = 0;
        ArrayList<TransientPoint> dungeonEntrances = new ArrayList<>();
        for (Room room : _rooms) {
            ArrayList<TransientPoint> entrances = new ArrayList<>();
            for (int ii = room.X; ii < room.RightSide; ii++) {
                for (int jj = room.Y; jj < room.BottomSide; jj++) {
                    if (ii == room.X || jj == room.Y || ii == room.RightSide - 1 || jj == room.BottomSide - 1) {
                        if (!room.Corners.contains(new Point2(ii, jj))) {
                            if ((ii == room.X && ii > 0) || (ii == room.RightSide && ii < _blocksWide)) {
                                if (isFloor(ii - 1, jj) && isFloor(ii + 1, jj)) {
                                    entrances.add(new TransientPoint(ii, jj, true));
                                }
                            }
                            if ((jj == room.Y && jj > 0) || (jj == room.BottomSide && jj < _blocksHigh)) {
                                if (isFloor(ii, jj - 1) && isFloor(ii, jj + 1)) {
                                    entrances.add(new TransientPoint(ii, jj));
                                }
                            }
                        }
                        dungeon[ii][jj] = EntityFactory.create(EntityType.WALL, new Point2(ii, jj));
                    }
                    else {
                        dungeon[ii][jj] = EntityFactory.create(EntityType.FLOOR, new Point2(ii, jj));
                    }
                }
            }
            if (roomCount > 0 && entrances.size() > 0) {
                int index = RNG.next(0, entrances.size() - 1);
                TransientPoint entrance = entrances.get(index);
                if (dungeon[entrance.X][entrance.Y].getEntityType() != EntityType.FLOOR) {
                    dungeonEntrances.add(entrance);
                }
            }
            roomCount++;
        }
        for (TransientPoint entrance : dungeonEntrances) {
            if (entrance.isHorizontal()) {
                for (int ii = 1; ii < _blocksWide - 1; ii++) {
                    Point2 currentTarget = new Point2(ii, entrance.Y);
                    if (dungeon[currentTarget.GridX][currentTarget.GridY].getEntityType() == EntityType.WALL) {
                        dungeon[currentTarget.GridX][currentTarget.GridY] = EntityFactory.create(EntityType.FLOOR, currentTarget);
                    }
                }
            }
            else {
                for (int ii = 1; ii < _blocksHigh - 1; ii++) {
                    Point2 currentTarget = new Point2(entrance.X, ii);
                    if (dungeon[currentTarget.GridX][currentTarget.GridY].getEntityType() == EntityType.WALL) {
                        dungeon[currentTarget.GridX][currentTarget.GridY] = EntityFactory.create(EntityType.FLOOR, currentTarget);
                    }
                }
            }
        }
    }

    private boolean isFloor(int x, int y) {
        return dungeon[x][y].getEntityType() == EntityType.FLOOR;
    }

    public Point2 getDownstairsLocation() {
        return _downSpawnLocation;
    }
}
