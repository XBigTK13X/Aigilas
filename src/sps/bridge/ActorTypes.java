package sps.bridge;

import sps.core.RNG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActorTypes {

    private static ActorTypes instance;

    public static ActorType get(String name) {
        return instance.resolve(name);
    }

    public static void add(ActorType ActorType) {
        if (instance == null) {
            instance = new ActorTypes();
        }
        instance.put(ActorType);
    }

    private Map<String, ActorType> actorTypes = new HashMap<String, ActorType>();

    private ActorTypes() {
    }

    public ActorType resolve(String name) {
        return actorTypes.get(name);
    }

    public void put(ActorType actorType) {
        actorTypes.put(actorType.Name, actorType);
        if (actorType.Generatable) {
            __randoms.add(actorType);
        }
    }

    private static List<ActorType> __randoms = new ArrayList<ActorType>();

    public static ActorType getRandomGeneratable() {
        return __randoms.get(RNG.next(0, __randoms.size()));
    }
}
