package aigilas.dungeons;

class OrientedPoint {
    public final int X;
    public final int Y;
    private final boolean _isHorizontal;

    public OrientedPoint(int x, int y, boolean destroyHorizontal) {
        X = x;
        Y = y;
        _isHorizontal = destroyHorizontal;
    }

    public OrientedPoint(int x, int y) {
        this(x, y, false);
    }

    public boolean isHorizontal() {
        return _isHorizontal;
    }
}
