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
        private List<Point2> m_steps = new List<Point2>();
        private HashSet<Point2> m_stepLookup = new HashSet<Point2>();
        IList<Point2> m_neighbors;
        private float m_totalWeight = 0;

        public Path(Point2 start, Point2 finish)
        {
            Finish = new Point2(finish);
            m_steps.Add(start);
            m_stepLookup.Add(start);
            m_totalWeight += start.Weight;
        }
        private Path(Point2 step, IEnumerable<Point2> steps,Point2 finish,float weight)
        {
            if (steps != null)
            {
                m_steps = new List<Point2>(steps) {step};
                m_stepLookup = new HashSet<Point2>(steps) {step};
            }
            m_totalWeight += weight+step.Weight;
            Finish = finish;
        }
        public Path Add(Point2 step)
        {
            return new Path(step, m_steps,Finish,m_totalWeight);
        }
        public float GetCost()
        {
            return m_totalWeight;
        }

        public Point2 GetNextMove()
        {
            return m_steps.Count == 1 ? m_steps.FirstOrDefault() : m_steps[1];
        }

        public bool IsDone()
        {
            return m_stepLookup.Contains(Finish);
        }
        public Point2 GetLastStep()
        {
            return m_steps[m_steps.Count - 1];
        }

        public IList<Point2> GetNeighbors()
        {
            if (m_neighbors == null)
            {
                var neighbors = GetLastStep().GetNeighbors();
                m_neighbors = new List<Point2>();
                foreach (var t in neighbors.Where(t => !m_stepLookup.Contains(t)))
                {
                    Add(t);
                }
            }
            return m_neighbors;
        }
    }
}
