package sps.states;

public interface State {
    void draw();

    void update();

    void asyncUpdate();

    void load();

    void unload();

    String getName();
}
