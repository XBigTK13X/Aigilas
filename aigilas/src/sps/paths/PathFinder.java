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
    private static Node node;
    private static PriorityQueue<Node> opened = new PriorityQueue<>();
    private static ArrayList<Node> closed = new ArrayList<>();
    private static Path result = new Path();


    public static Path findNextMove(Point2 start, Point2 destination) {
        Node nStart = new Node(start);
        opened.clear();
        closed.clear();
        node = null;
        opened.add(nStart);

        while(node == null || !node.Point.isSameSpot(destination)){
            node = opened.peek();
            opened.remove();
            if(node.Point.isSameSpot(destination)){
                return new Path(node);
            }
            closed.add(node);

            for(Point2 neighbor:node.Point.getNeighbors()){
                neighbor.setWeight(node.Weight + 1 + ((CoordVerifier.isBlocked(node.Point))?10:0) + HitTest.getDistanceSquare(neighbor,destination));

                boolean found = false;
                for(Node close:closed){
                    if(close.Point.isSameSpot(neighbor) && close.Weight > neighbor.Weight){
                        close.Point.setWeight(neighbor.Weight);
                        close.Parent = node;
                        found = true;
                    }
                }
                if(!found){
                    for(Node open:opened){
                        if(open.Point.isSameSpot(neighbor) && open.Weight > neighbor.Weight){
                            open.Point.setWeight(neighbor.Weight);
                            open.Parent = node;
                            found = true;
                        }
                    }
                }
                if(!found){
                    Node n = new Node(neighbor);
                    n.Parent = opened.peek();
                    opened.add(n);
                }
            }
        }
        return null;

        /*while (!queue.isEmpty()) {
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
        return null;*/
    }
}
