using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using OGUR.Collision;

namespace OGUR.Path
{
    public class Path
    {
        public Point2 Finish { get; private set; }
        private List<Point2> steps = new List<Point2>();
        IList<Point2> m_neighbors;

        public Path(Point2 start, Point2 finish)
        {
            Finish = new Point2(finish);
            steps.Add(start);
        }
        private Path(Point2 step, List<Point2> steps,Point2 finish)
        {
            this.steps = new List<Point2>(steps) {step};
            Finish = finish;
        }
        public Path Add(Point2 step)
        {
            return new Path(step, steps,Finish);
        }
        public void Pop()
        {
            steps.RemoveAt(steps.Count-1);
        }
        public float GetCost()
        {
            return steps.Sum(node => node.Weight);
        }

        public Point2 GetNextMove()
        {
            return steps.Count == 1 ? steps.FirstOrDefault() : steps[1];
        }

        public bool IsDone()
        {
            return steps.Contains(Finish);
        }
        public Point2 GetLastStep()
        {
            return steps[steps.Count - 1];
        }

        public IList<Point2> GetNeighbors()
        {
            
            if (m_neighbors == null)
            {
                var neighbors = GetLastStep().GetNeighbors();
                m_neighbors = new List<Point2>();
                for (int ii = 0; ii < neighbors.Count;ii++)
                {
                    if(!steps.Contains(neighbors[ii]))
                    {
                        m_neighbors.Add(neighbors[ii]);
                    }
                }
            }
            return m_neighbors;
        }
    }
}
