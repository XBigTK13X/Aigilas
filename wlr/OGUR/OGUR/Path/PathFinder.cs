using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Collision;
using OGUR.Dungeons;
using OGUR.GameObjects;

namespace OGUR.Path
{    
    public static class PathFinder
    {
        private static PriorityQueue queue = new PriorityQueue();
        private static Point2 node = new Point2(0, 0);
        private static float min;
        private static Path path;
        private static List<Point2> neighbors = new List<Point2>();

        public static Point2 FindNextMove(Point2 start,Point2 destination,bool nextMoveOnly = true)
        {
            queue.Clear();
            start.Reset(start.GridX,start.GridY);
            destination.Reset(destination.GridX, destination.GridY);
            queue.Enqueue(0, PathFactory.Create(start,destination));
            while (!queue.IsEmpty)
            {
                path = queue.Dequeue();
                if (path.IsDone())
                {
                    return path.GetNextMove();
                }
                neighbors.Clear();
                neighbors = path.GetNeighbors();
                if(neighbors.Count==0)
                {
                    continue;
                }
                foreach(var neighbor in neighbors)
                {
                    neighbor.SetWeight(HitTest.GetDistanceSquare(neighbor, destination));
                }
                while(neighbors.Count>0)
                {
                    node = neighbors[0];
                    foreach(var neighbor in neighbors)
                    {
                        if (neighbor.Weight < node.Weight)
                        {
                            node = neighbor;
                        }
                    }
                    neighbors.Remove(node);
                    if (!CoordVerifier.IsBlocked(node) || (node.GridX == destination.GridX && node.GridY == destination.GridY))
                    {
                        node.SetWeight(Point2.CalculateDistanceSquared(node, path.GetLastStep()));
                        var newPath = PathFactory.Create(path);
                        if(newPath.Add(node))
                        {
                            queue.Enqueue(newPath.GetCost(), newPath);
                            break;    
                        }
                    }
                }
            }
            return null;
        }
    }
}