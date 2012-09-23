package sps.paths;

import sps.core.Point2;
import sps.core.RNG;
import sps.entities.CoordVerifier;
import sps.entities.HitTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PathFinder {
    private static final PriorityQueue<Path> queue = new PriorityQueue<>(100, new Comparator<Path>() {
        @Override
        public int compare(Path o1, Path o2) {
            return (int) (o1.getCost() - o2.getCost());
        }
    });
    private static Point2 node = new Point2(0, 0);
    private static Path path;
    private static List<Point2> neighbors = new ArrayList<>();

    public static Path findNextMove(Point2 start, Point2 destination) {
        queue.clear();
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
                return null;
            }
            for (Point2 neighbor : neighbors) {
                neighbor.setWeight(HitTest.getDistanceSquare(neighbor, destination));
            }

            while (neighbors.size() > 0) {
                node = neighbors.get(0);
                for (Point2 neighbor : neighbors) {
                    if (neighbor.Weight < node.Weight) {
                        node = neighbor;
                    }
                }
                neighbors.remove(node);
                if (!CoordVerifier.isBlocked(node) || node.isSameSpot(destination)) {
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
}
