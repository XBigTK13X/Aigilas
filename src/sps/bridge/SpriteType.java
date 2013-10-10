package sps.bridge;

public class SpriteType {
    public final int Index;
    public final int Frames;
    public final String Name;

    public SpriteType(String name, int index) {
        this(name, index, 1);
    }

    public SpriteType(String name, int index, int frames) {
        Name = name;
        Frames = frames;
        Index = index;
    }
}
