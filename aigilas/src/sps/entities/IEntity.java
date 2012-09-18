package sps.entities;

import sps.bridge.EntityType;
import sps.core.Point2;

public interface IEntity {
    boolean contains(Point2 target);

    void draw();

    void update();

    void loadContent();

    Point2 getLocation();

    EntityType getEntityType();

    boolean isActive();

    boolean isBlocking();

    void setInactive();
}
