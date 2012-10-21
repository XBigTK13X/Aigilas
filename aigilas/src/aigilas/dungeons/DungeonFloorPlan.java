package aigilas.dungeons;

import sps.bridge.EntityType;
import sps.core.Point2;
import sps.core.RNG;
import sps.core.Settings;

import java.util.ArrayList;
import java.util.List;

public class DungeonFloorPlan {
    private List<Room> rooms;
    private EntityType[][] tiles;

    public DungeonFloorPlan(boolean altarRoom) {
        rooms = new ArrayList<Room>();
        tiles = new EntityType[Settings.get().tileMapWidth][Settings.get().tileMapHeight];

        rooms.add(new Room(Settings.get().tileMapHeight, Settings.get().tileMapWidth, 0, 0));
        if (!altarRoom) {
            int roomsToPlace = 3 + RNG.next(0, Settings.get().maxRoomCount);
            int attemptCount = 0;
            while (attemptCount < 1000 && roomsToPlace > 0) {
                attemptCount++;
                int startX = RNG.next(0, Settings.get().tileMapWidth - 5);
                int startY = RNG.next(0, Settings.get().tileMapHeight - 5);
                int startWidth = 5 + RNG.next(0, 2);
                int startHeight = 5 + RNG.next(0, 2);
                roomsToPlace--;
                Room nextRoom = new Room(startHeight, startWidth, startX, startY);
                boolean collides = false;
                for (int ii = 1; ii < rooms.size(); ii++) {
                    Room room = rooms.get(ii);
                    if (room.collides(nextRoom)) {
                        collides = true;
                    }
                }
                if (!collides && !nextRoom.isBad()) {
                    rooms.add(nextRoom);
                }
            }
        }
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Tile> getTiles() {
        int roomCount = 0;
        ArrayList<TransientPoint> dungeonEntrances = new ArrayList<TransientPoint>();
        for (Room room : rooms) {
            ArrayList<TransientPoint> entrances = new ArrayList<TransientPoint>();
            for (int ii = room.X; ii < room.RightSide; ii++) {
                for (int jj = room.Y; jj < room.BottomSide; jj++) {
                    if (ii == room.X || jj == room.Y || ii == room.RightSide - 1 || jj == room.BottomSide - 1) {
                        if (!room.Corners.contains(new Point2(ii, jj))) {
                            if ((ii == room.X && ii > 0) || (ii == room.RightSide && ii < Settings.get().tileMapWidth)) {
                                if (is(ii - 1, jj, EntityType.Floor) && is(ii + 1, jj, EntityType.Floor)) {
                                    entrances.add(new TransientPoint(ii, jj, true));
                                }
                            }
                            if ((jj == room.Y && jj > 0) || (jj == room.BottomSide && jj < Settings.get().tileMapHeight)) {
                                if (is(ii, jj - 1, EntityType.Floor) && is(ii, jj + 1, EntityType.Floor)) {
                                    entrances.add(new TransientPoint(ii, jj));
                                }
                            }
                        }
                        tiles[ii][jj] = EntityType.Wall;
                    }
                    else {
                        tiles[ii][jj] = EntityType.Floor;
                    }
                }
            }
            if (roomCount > 0 && entrances.size() > 0) {
                int index = RNG.next(0, entrances.size() - 1);
                TransientPoint entrance = entrances.get(index);
                if (!is(entrance.X, entrance.Y, EntityType.Floor)) {
                    dungeonEntrances.add(entrance);
                }
            }
            roomCount++;
        }
        for (TransientPoint entrance : dungeonEntrances) {
            if (entrance.isHorizontal()) {
                for (int ii = 1; ii < Settings.get().tileMapWidth - 1; ii++) {
                    Point2 currentTarget = new Point2(ii, entrance.Y);
                    if (is(currentTarget.GridX, currentTarget.GridY, EntityType.Wall)) {
                        tiles[currentTarget.GridX][currentTarget.GridY] = EntityType.Floor;
                    }
                }
            }
            else {
                for (int ii = 1; ii < Settings.get().tileMapHeight - 1; ii++) {
                    Point2 currentTarget = new Point2(entrance.X, ii);
                    if (is(currentTarget.GridX, currentTarget.GridY, EntityType.Wall)) {
                        tiles[currentTarget.GridX][currentTarget.GridY] = EntityType.Floor;
                    }
                }
            }
        }

        List<Tile> walls = new ArrayList<Tile>();
        for (int ii = 0; ii < Settings.get().tileMapWidth; ii++) {
            for (int jj = 0; jj < Settings.get().tileMapHeight; jj++) {
                walls.add(new Tile(tiles[ii][jj], ii, jj));
            }
        }
        return walls;
    }

    private boolean is(int x, int y, EntityType type) {
        if (tiles[x][y] == null) {
            return false;
        }
        return tiles[x][y] == type;
    }
}
