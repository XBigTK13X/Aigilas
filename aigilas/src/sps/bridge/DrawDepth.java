package sps.bridge;

public enum DrawDepth {
    Default(0),
    AnimatedTexture(0),
    Floor(0),
    Wall(10),
    Altar(20),
    Stairs(40),
    Item(50),
    Creature(60),
    Player(70),
    BaseSkillEffect(80),
    SkillEffect(90),
    ComboMarker(100),
    HudBG(950),
    DefaultHudText(960),
    ActionTextBG(980),
    ActionText(990),
    DevConsole(1000),
    DevConsoleText(1010),
    Particle(1020),
    Debug(1030);

    public final int DrawDepth;

    private DrawDepth(int DrawDepth) {
        this.DrawDepth = DrawDepth;
    }
}
