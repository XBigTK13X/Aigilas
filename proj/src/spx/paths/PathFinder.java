package spx.paths;

import spx.core.Point2;
import spx.core.RNG;
import spx.entities.CoordVerifier;
import spx.entities.HitTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PathFinder {
    private static PriorityQueue<Path> queue = new PriorityQueue<Path>(100, new Comparator<Path>() {

        @Override
        public int compare(Path o1, Path o2) {
            return (int) (o1.getCost() - o2.getCost());
        }
    });
    private static Point2 node = new Point2(0, 0);
    private static Path path;
    private static List<Point2> neighbors = new ArrayList<Point2>();

    public static Path findNextMove(Point2 start, Point2 destination, boolean nextMoveOnly) {
        queue.clear();
        start.reset(start.GridX, start.GridY);
        destination.reset(destination.GridX, destination.GridY);
        queue.add(PathFactory.create(start, destination));
        while (!queue.isEmpty()) {
            path = queue.peek();
            queue.remove(queue.peek());
            if (path.isDone()) {
                return path;
            }
            neighbors.clear();
            neighbors = path.getNeighbors();
            if (neighbors.size() == 0) {
                continue;
            }
            for (Point2 neighbor : neighbors) {
                neighbor.setWeight(HitTest.getDistanceSquare(neighbor, destination));
            }

            while (neighbors.size() > 0) {
                node = neighbors.get(RNG.next(0, neighbors.size()));
                for (Point2 neighbor : neighbors) {
                    if (neighbor.Weight < node.Weight) {
                        node = neighbor;
                    }
                }
                neighbors.remove(node);
                if (!CoordVerifier.isBlocked(node) || node.isSameSpot(destination)) {
                    node.setWeight(Point2.calculateDistanceSquared(node, path.getLastStep()));
                    Path newPath = PathFactory.create(path);
                    if (newPath.add(node)) {
                        queue.add(newPath);
                        break;
                    }
                }
            }
        }
        return null;
    }

    public static Path findNextMove(Point2 start, Point2 destination) {
        return findNextMove(start, destination, true);
    }
}
