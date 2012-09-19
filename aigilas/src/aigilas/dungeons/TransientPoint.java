package aigilas.dungeons;

class TransientPoint {
    public final int X;
    public final int Y;
    private final boolean _isHorizontal;

    public TransientPoint(int x, int y, boolean destroyHorizontal) {
        X = x;
        Y = y;
        _isHorizontal = destroyHorizontal;
    }

    public TransientPoint(int x, int y) {
        this(x, y, false);
    }

    public boolean isHorizontal() {
        return _isHorizontal;
    }
}
