package sps.states;

public interface State {
    void draw();

    void update();

    void asyncUpdate();

    void load();

    void unload();

    String getName();

    void resize(int width, int height);
}
