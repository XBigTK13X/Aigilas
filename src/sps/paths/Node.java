package sps.paths;

import sps.core.Point2;

public class Node implements Comparable {
    public Point2 Point = new Point2(0, 0);
    public float Weight;
    public Node Parent;

    public Node(Point2 point) {
        Point.copy(point);
        Weight = point.Weight;
    }

    @Override
    public int compareTo(Object o) {
        Node n = (Node) o;
        return Math.round(Weight - n.Weight);
    }

    @Override
    public String toString() {
        return Weight + ": " + Point.toString();
    }
}
