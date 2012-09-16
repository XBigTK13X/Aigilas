package aigilas.dungeons;

class PointPoint {
    public int X;
    public int Y;
    private boolean _isHorizontal;

    public PointPoint(int x, int y, boolean destroyHorizontal) {
        X = x;
        Y = y;
        _isHorizontal = destroyHorizontal;
    }

    public PointPoint(int x, int y) {
        this(x, y, false);
    }

    public boolean isHorizontal() {
        return _isHorizontal;
    }
}
