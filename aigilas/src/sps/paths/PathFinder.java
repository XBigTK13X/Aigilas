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
    private static Point2 node = new Point2(0, 0);
    private static List<Point2> neighbors = new ArrayList<>();

    private static PriorityQueue<Point2> open = new PriorityQueue<>();
    private static ArrayList<Point2> closed = new ArrayList<>();
    private static Path result = new Path();

    public static Path findNextMove(Point2 start, Point2 destination) {
        open.clear();
        closed.clear();
        node.reset(0,0);
        open.add(start);
        while(!node.isSameSpot(destination)){
            node = open.peek();
            open.remove();
            if(node.isSameSpot(destination)){
                return new Path(closed);
            }
            closed.add(node);
            for(Point2 neighbor:node.getNeighbors()){
                neighbor.setWeight(node.Weight + 1);
            }
        }

        while (we have not reached our goal) {
            consider the best node in the open list (the node with the lowest f value)
            if (this node is the goal) {
                then we're done
            }
            else {
                move the current node to the closed list and consider all of its neighbors
                for (each neighbor) {
                    if (this neighbor is in the closed list and our current g value is lower) {
                        update the neighbor with the new, lower, g value
                        change the neighbor's parent to our current node
                    }
                    else if (this neighbor is in the open list and our current g value is lower) {
                        update the neighbor with the new, lower, g value
                        change the neighbor's parent to our current node
                    }
                    else this neighbor is not in either the open or closed list {
                        add the neighbor to the open list and set its g value
                    }
                }
            }
        }


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
