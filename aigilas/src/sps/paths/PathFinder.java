package sps.paths;

import sps.core.Point2;
import sps.entities.CoordVerifier;
import sps.entities.HitTest;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class PathFinder {
    public static Path find(Point2 start, Point2 destination) {
        PriorityQueue<Node> opened = new PriorityQueue<Node>();
        ArrayList<Node> closed = new ArrayList<Node>();
        Node node = null;
        opened.add(new Node(start));
        while (node == null || !node.Point.isSameSpot(destination)) {
            node = opened.peek();
            if (node == null) {
                return null;
            }
            opened.remove(node);
            if (node.Point.isSameSpot(destination)) {
                return new Path(node);
            }
            closed.add(node);

            for (Point2 neighbor : node.Point.getNeighbors()) {
                neighbor.setWeight(HitTest.getDistanceSquare(neighbor, destination));

                boolean isClosed = false;
                for (Node c : closed) {
                    if (c.Point.isSameSpot(neighbor)) {
                        isClosed = true;
                    }
                }

                if (!isClosed) {
                    boolean found = false;
                    if (!CoordVerifier.isBlocked(neighbor)) {
                        for (Node o : opened) {
                            if (o.Point.isSameSpot(neighbor)) {
                                if (neighbor.Weight < o.Weight) {
                                    o.Point.copy(neighbor);
                                    o.Parent = node;
                                }
                                found = true;
                            }
                        }
                        if (!found) {
                            Node n = new Node(neighbor);
                            n.Parent = node;
                            opened.add(n);
                        }
                    }
                    else {
                        if (neighbor.isSameSpot(destination)) {
                            Node n = new Node(neighbor);
                            n.Parent = node;
                            return new Path(n);
                        }
                    }
                }
            }
        }
        return null;
    }
}
