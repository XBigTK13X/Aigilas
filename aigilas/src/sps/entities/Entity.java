package sps.entities;

import aigilas.energygement.SpriteType;
import sps.bridge.DrawDepth;
import sps.bridge.EntityType;
import sps.core.Point2;
import sps.core.Settings;
import sps.graphics.AnimatedTexture;
import sps.graphics.SpriteInfo;

public class Entity implements Comparable {
    protected final AnimatedTexture _graphic = new AnimatedTexture();

    protected boolean _isActive = true;
    protected Boolean _isBlocking;
    protected SpriteType _assetName;
    protected boolean _isOnBoard = true;
    private boolean _isInteracting = false;
    protected final Point2 _location = new Point2(0, 0);
    protected EntityType _entityType;

    public void loadContent() {
        _graphic.loadContent(_assetName);
    }

    public void draw() {
        if (_isOnBoard && _isActive) {
            _graphic.draw();
        }
    }

    public void hide() {
        _isOnBoard = false;
    }

    public void show() {
        _isOnBoard = true;
    }

    protected void initialize(Point2 location, SpriteType spriteType, EntityType entityType, DrawDepth depth) {
        _assetName = spriteType;
        _entityType = entityType;
        _location.copy(location);
        _graphic.setPosition(_location);
        _graphic.setDrawDepth(depth);
    }

    public void update() {
    }

    public void setLocation(Point2 location) {
        _graphic.setPosition(location);
        _location.copy(location);
    }

    private final Point2 oldLocation = new Point2(0, 0);

    public void updateLocation(Point2 location) {
        oldLocation.copy(_location);
        _graphic.setPosition(location);
        _location.copy(location);
        EntityManager.get().updateGridLocation(this, oldLocation);
    }

    private final Point2 target = new Point2(0, 0);

    public boolean move(float amountX, float amountY) {
        amountX = normalizeDistance(amountX);
        amountY = normalizeDistance(amountY);
        target.reset(_location.PosX + amountX, _location.PosY + amountY);
        if (CoordVerifier.isValid(target)) {
            updateLocation(target);
            return true;
        }
        return false;
    }

    private static int isNeg = 1;
    private static int factorsOfSpriteHeight = 0;

    private static float normalizeDistance(float amount) {
        isNeg = (amount < 0) ? -1 : 1;
        amount = Math.abs(amount);
        factorsOfSpriteHeight = (int) Math.floor(amount / Settings.get().spriteHeight);
        factorsOfSpriteHeight = (factorsOfSpriteHeight == 0 && amount != 0) ? 1 : factorsOfSpriteHeight;
        return (Settings.get().spriteHeight * factorsOfSpriteHeight * isNeg);
    }

    public boolean isActive() {
        return _isActive;
    }

    public void setInactive() {
        _isActive = false;
    }

    public boolean isBlocking() {
        if (_isBlocking == null) {
            return false;
        }
        return _isBlocking;
    }

    public Point2 getLocation() {
        return _location;
    }

    public boolean isGraphicLoaded() {
        return (_graphic != null);
    }

    protected void setSpriteInfo(SpriteInfo sprite) {
        _graphic.setSpriteInfo(sprite);
    }

    public DrawDepth getDepth() {
        return _graphic.getDepth();
    }

    public boolean contains(Point2 target)

    {
        return target.GridX == getLocation().GridX && target.GridY == getLocation().GridY;
    }

    public void setInteraction(boolean isInteracting) {
        _isInteracting = isInteracting;
    }

    public boolean isInteracting() {
        return _isInteracting;
    }

    public void setInteracting(boolean isInteracting) {
        _isInteracting = isInteracting;
    }

    public EntityType getEntityType() {
        return _entityType;
    }

    @Override
    public int compareTo(Object o) {
        Entity e = (Entity) o;
        return getDepth().DrawDepth - e.getDepth().DrawDepth;
    }
}
