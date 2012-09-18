package spx.entities;

import spx.bridge.ActorType;
import spx.bridge.EntityType;
import spx.core.Point2;
import spx.core.RNG;
import spx.core.Settings;
import spx.core.SpxManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityManager {
    private static List<IEntity> _contents = new ArrayList<>();
    private static HashMap<Point2, List<IEntity>> _gridContents = new HashMap<>();

    public static IEntity addObject(IEntity Entity) {
        Entity.loadContent();
        _contents.add(Entity);
        addToGrid(Entity);
        return Entity;
    }

    public static IEntity getObject(EntityType type) {
        if (_contents != null) {
            for (IEntity entity : _contents) {
                if (entity.getEntityType() == type) {
                    return entity;
                }
            }
        }
        return null;
    }

    private static final List<IEntity> gopResults = new ArrayList<>();

    public static List<IEntity> getEntities(EntityType type, Point2 target)

    {
        if (_contents != null) {
            goResults.clear();
            goResults.addAll(getObjects(type));
            gopResults.clear();
            for (IEntity goResult : goResults) {
                if (goResult.contains(target)) {
                    gopResults.add(goResult);
                }
            }
            return gopResults;
        }
        return null;
    }

    private static final List<IEntity> goResults = new ArrayList<>();

    public static List<IEntity> getObjects(EntityType type) {
        goResults.clear();
        for (IEntity _content : _contents) {
            if (_content.getEntityType() == type) {
                goResults.add(_content);
            }
        }
        return goResults;
    }

    private static final List<IActor> creatures = new ArrayList<>();

    public static List<IActor> getActors(ActorType type) {
        creatures.clear();
        if (type != ActorType.NONPLAYER) {
            for (IEntity elem : _contents) {
                if (elem.getEntityType() == EntityType.ACTOR) {
                    if (((IActor) elem).getActorType() == type) {
                        creatures.add(((IActor) elem));
                    }
                }
            }
        } else {
            for (IEntity elem : _contents) {
                if (elem.getEntityType() == EntityType.ACTOR) {
                    if (((IActor) elem).getActorType() != ActorType.PLAYER) {
                        creatures.add(((IActor) elem));
                    }
                }
            }
        }
        return creatures;
    }

    private static IActor _nextResult;

    public static List<IActor> getActorsAt(Point2 target, ActorType actorType) {
        creatures.clear();
        for (IEntity elem : _gridContents.get(target)) {
            if (elem.getEntityType() == EntityType.ACTOR) {
                _nextResult = (IActor) elem;
                if (actorType == null || _nextResult.getActorType() == actorType || (actorType == ActorType.NONPLAYER && _nextResult.getActorType() != ActorType.PLAYER)) {
                    creatures.add(_nextResult);
                }
            }
        }
        return creatures;
    }

    public static List<IActor> getActorsAt(Point2 target) {
        return getActorsAt(target, null);
    }

    private static final List<IActor> creatures2 = new ArrayList<>();

    public static List<IActor> getActorsSurrounding(Point2 target, int distance)

    {
        creatures2.clear();
        for (int ii = -distance; ii < distance + 1; ii++) {
            for (int jj = -distance; jj < distance + 1; jj++) {
                if (ii != 0 || jj != 0) {
                    for (IActor creature : getActorsAt(target.add(new Point2(ii, jj)), null)) {
                        creatures2.add(creature);
                    }
                }
            }
        }
        return creatures2;
    }

    public static boolean isLocationBlocked(Point2 location) {
        for (IEntity elem : _gridContents.get(location)) {
            if (elem.isBlocking()) {
                return true;
            }
        }
        return false;
    }

    private static List<IEntity> gotcResults;

    public static List<IEntity> getObjectsToCache() {
        gotcResults.clear();
        for (IEntity _content : _contents) {
            if (_content.getEntityType() != EntityType.FLOOR) {
                gotcResults.add(_content);
            }
        }
        return gotcResults;
    }

    public static IActor getNearestPlayer(IEntity target) {
        List<IActor> actors = getActors(ActorType.PLAYER);
        if (actors.size() > 0) {
            IEntity closest = actors.get(0);
            IEntity player;
            for (IActor actor : actors) {
                player = actor;
                if (HitTest.getDistanceSquare(target, player) < HitTest.getDistanceSquare(target, closest)) {
                    closest = player;
                }
            }
            return (IActor) closest;
        }
        return null;
    }

    public static IActor getNearestPlayer(IActor target)

    {
        return getNearestPlayer((IEntity) target);
    }

    public static void addObjects(List<IEntity> cache) {
        _contents.addAll(cache);
    }

    public static boolean anyContains(Point2 target, EntityType type) {
        for (IEntity entity : _gridContents.get(target)) {
            if (entity.getEntityType() == type) {
                return true;
            }
        }
        return false;
    }

    public static void removeObject(IEntity target) {
        _contents.remove(target);
    }

    public static void clear() {
        _contents = new ArrayList<>();
        _gridContents = new HashMap<>();
    }

    public static void reset() {
        _contents = new ArrayList<>();
        _gridContents = new HashMap<>();
        creatures.clear();
        loadContent();
    }

    public static void update() {
        for (int ii = 0; ii < _contents.size(); ii++) {
            if (ii >= _contents.size()) {
                return;
            }
            if (!_contents.get(ii).isActive()) {
                _gridContents.get(_contents.get(ii).getLocation()).remove(_contents.get(ii));
                _contents.remove(_contents.get(ii));
                ii--;
                continue;
            }
            _contents.get(ii).update();
        }
    }

    public static void draw() {
        if (SpxManager.Renderer != null) {
            for (IEntity component : _contents) {
                component.draw();
            }
        }
    }

    public static void loadContent() {
        if (SpxManager.Renderer != null) {
            for (IEntity component : _contents) {
                component.loadContent();
            }
        }
    }

    private static void addToGrid(IEntity entity) {
        if (!_gridContents.containsKey(entity.getLocation())) {
            _gridContents.put(entity.getLocation(), new ArrayList<IEntity>());
        }
        _gridContents.get(entity.getLocation()).add(entity);
    }

    public static void updateGridLocation(IEntity Entity, Point2 oldLocation) {
        if (_gridContents != null && oldLocation != null) {
            _gridContents.get(oldLocation).remove(Entity);
            addToGrid(Entity);
        }
    }

    private static final List<IActor> _players = new ArrayList<>();

    public static List<IActor> getPlayers() {
        _players.clear();
        for (IEntity tile : _contents) {
            if (tile.getEntityType() == EntityType.ACTOR && ((IActor) tile).getActorType() == ActorType.PLAYER) {
                _players.add((IActor) tile);
            }
        }
        return _players;
    }

    public static Point2 getEmptyLocation() {
        List<Point2> emptyLocations = new ArrayList<>();
        for (Point2 location : _gridContents.keySet()) {
            if (location.GridX > 0 && location.GridY > 0 && location.GridX < Settings.get().tileMapWidth - 1 && location.GridY < Settings.get().tileMapHeight - 1) {
                boolean exclude = false;
                for (int ii = 0; ii < _gridContents.get(location).size(); ii++) {
                    if (_gridContents.get(location).get(ii).getEntityType() == EntityType.ACTOR) {
                        exclude = true;
                    }
                }
                if (!exclude) {
                    emptyLocations.add(location);
                }
            }
        }
        return emptyLocations.get(RNG.next(0, emptyLocations.size()));
    }

    public static List<IEntity> getEntitiesToCache() {
        List<IEntity> results = new ArrayList<>();
        for (IEntity _content : _contents) {
            if (_content.getEntityType() != EntityType.FLOOR) {
                results.add(_content);
            }
        }
        return results;
    }

    public static IActor getTouchingCreature(IEntity entity) {
        for (IEntity _content : _contents) {
            if (_content.getEntityType() == EntityType.ACTOR) {
                if (_content.contains(entity.getLocation())) {
                    return (IActor) _content;
                }
            }
        }
        return null;
    }
}
