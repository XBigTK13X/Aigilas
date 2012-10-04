package sps.entities;

import sps.bridge.ActorType;
import sps.bridge.EntityType;
import sps.core.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityManager {

    public static EntityManager __instance;

    public static EntityManager get() {
        if (__instance == null) {
            __instance = new EntityManager();
        }
        return __instance;
    }

    public static void reset() {
        __instance = new EntityManager();
    }

    private EntityManager(){}

    private List<IEntity> _contents = new ArrayList<IEntity>();
    private HashMap<Point2, List<IEntity>> _gridContents = new HashMap<Point2,List<IEntity>>();

    public IEntity addObject(IEntity Entity) {
        Entity.loadContent();
        _contents.add(Entity);
        addToGrid(Entity);
        return Entity;
    }

    public IEntity getObject(EntityType type) {
        if (_contents != null) {
            for (IEntity entity : _contents) {
                if (entity.getEntityType() == type) {
                    return entity;
                }
            }
        }
        return null;
    }

    private final List<IEntity> _gopResults = new ArrayList<IEntity>();

    public List<IEntity> getEntities(EntityType type, Point2 target)

    {
        if (_contents != null) {
            _goResults.clear();
            _goResults.addAll(getObjects(type));
            _gopResults.clear();
            for (IEntity goResult : _goResults) {
                if (goResult.contains(target)) {
                    _gopResults.add(goResult);
                }
            }
            return _gopResults;
        }
        return null;
    }

    private final List<IEntity> _goResults = new ArrayList<IEntity>();

    public List<IEntity> getObjects(EntityType type) {
        _goResults.clear();
        for (IEntity _content : _contents) {
            if (_content.getEntityType() == type) {
                _goResults.add(_content);
            }
        }
        return _goResults;
    }

    private final List<IActor> _creatures = new ArrayList<IActor>();

    public List<IActor> getActors(ActorType type) {
        _creatures.clear();
        if (type != ActorType.NONPLAYER) {
            for (IEntity elem : _contents) {
                if (elem.getEntityType() == EntityType.ACTOR) {
                    if (((IActor) elem).getActorType() == type) {
                        _creatures.add(((IActor) elem));
                    }
                }
            }
        } else {
            for (IEntity elem : _contents) {
                if (elem.getEntityType() == EntityType.ACTOR) {
                    if (((IActor) elem).getActorType() != ActorType.PLAYER) {
                        _creatures.add(((IActor) elem));
                    }
                }
            }
        }
        return _creatures;
    }

    private IActor _nextResult;

    public List<IActor> getActorsAt(Point2 target, ActorType actorType) {
        _creatures.clear();
        for (IEntity elem : _gridContents.get(target)) {
            if (elem.getEntityType() == EntityType.ACTOR) {
                _nextResult = (IActor) elem;
                if (actorType == null || _nextResult.getActorType() == actorType || (actorType == ActorType.NONPLAYER && _nextResult.getActorType() != ActorType.PLAYER)) {
                    _creatures.add(_nextResult);
                }
            }
        }
        return _creatures;
    }

    public List<IActor> getActorsAt(Point2 target) {
        return getActorsAt(target, null);
    }

    private final List<IActor> _creatures2 = new ArrayList<IActor>();

    public List<IActor> getActorsSurrounding(Point2 target, int distance)

    {
        _creatures2.clear();
        for (int ii = -distance; ii < distance + 1; ii++) {
            for (int jj = -distance; jj < distance + 1; jj++) {
                if (ii != 0 || jj != 0) {
                    for (IActor creature : getActorsAt(target.add(new Point2(ii, jj)), null)) {
                        _creatures2.add(creature);
                    }
                }
            }
        }
        return _creatures2;
    }

    public boolean isLocationBlocked(Point2 location) {
        for (IEntity elem : _gridContents.get(location)) {
            if (elem.isBlocking()) {
                return true;
            }
        }
        return false;
    }

    public IActor getNearestPlayer(IEntity target) {
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

    public void addObjects(List<IEntity> cache) {
        _contents.addAll(cache);
    }

    public boolean anyContains(Point2 target, EntityType type) {
        for (IEntity entity : _gridContents.get(target)) {
            if (entity.getEntityType() == type) {
                return true;
            }
        }
        return false;
    }

    public void removeObject(IEntity target) {
        _contents.remove(target);
    }

    public void clear() {
        _contents = new ArrayList<IEntity>();
        _gridContents = new HashMap<Point2, List<IEntity>>();
    }

    public void update() {
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

    public void draw() {
        if (SpxManager.Renderer != null) {
            for (IEntity component : _contents) {
                component.draw();
            }
        }
    }

    public void loadContent() {
        if (SpxManager.Renderer != null) {
            for (IEntity component : _contents) {
                component.loadContent();
            }
        }
    }

    private void addToGrid(IEntity entity) {
        if (!_gridContents.containsKey(entity.getLocation())) {
            _gridContents.put(entity.getLocation(), new ArrayList<IEntity>());
        }
        _gridContents.get(entity.getLocation()).add(entity);
    }

    public void updateGridLocation(IEntity Entity, Point2 oldLocation) {
        if (_gridContents != null && oldLocation != null) {
            _gridContents.get(oldLocation).remove(Entity);
            addToGrid(Entity);
        }
    }

    private final List<IActor> _players = new ArrayList<IActor>();

    public List<IActor> getPlayers() {
        _players.clear();
        for (IEntity tile : _contents) {
            if (tile.getEntityType() == EntityType.ACTOR && ((IActor) tile).getActorType() == ActorType.PLAYER) {
                _players.add((IActor) tile);
            }
        }
        return _players;
    }

    public Point2 getEmptyLocation() {
        List<Point2> emptyLocations = new ArrayList<Point2>();
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

    public List<IEntity> getEntitiesToCache() {
        List<IEntity> results = new ArrayList<IEntity>();
        for (IEntity _content : _contents) {
            if (_content.getEntityType() != EntityType.FLOOR) {
                results.add(_content);
            }
        }
        return results;
    }

    public IActor getTouchingCreature(IEntity entity) {
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
