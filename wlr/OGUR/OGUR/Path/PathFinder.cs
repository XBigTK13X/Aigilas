using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Collision;
using OGUR.GameObjects;

namespace OGUR.Path
{
    public interface IHasNeighbours<N>
    {
        IEnumerable<N> AccessibleNeighbours { get; }
    }
    
    public static class PathFinder
    {
        public static Point2 FindNextMove(Point2 start,Point2 destination,bool nextMoveOnly=true)
        {
            var queue = new PriorityQueue<double, Path>();
            var closed = new List<Point2>();
            queue.Enqueue(0, new Path(start,destination));
            while (!queue.IsEmpty)
            {
                var path = queue.Dequeue();
                if (path.IsDone())
                {
                    return path.GetNextMove();
                }
                
                var neighbors = path.GetNeighbors();
                if(!neighbors.Any())
                {
                    continue;
                }
                foreach(var neighbor in neighbors)
                {
                    neighbor.SetWeight(HitTest.GetDistanceSquare(neighbor, destination));
                }
                var node = neighbors.Where(p => p.Weight == neighbors.Min(o => o.Weight)).First();
                neighbors.Remove(node);
                if (!CoordVerifier.Contains(node,GameObjectType.WALL) || (node.GridX == destination.GridX && node.GridY == destination.GridY))
                {
                    node.SetWeight(Point2.CalculateDistanceSquared(node, path.GetLastStep()));
                    var newPath = path.Add(node);
                    queue.Enqueue(newPath.GetCost(), newPath);
                }
            }
            return null;
        }
    }
}