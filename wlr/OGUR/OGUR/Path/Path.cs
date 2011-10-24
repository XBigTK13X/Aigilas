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
        private Dictionary<Point2,Point2> m_stepLookup = new Dictionary<Point2,Point2>();
        private float m_totalWeight = 0;

        public Path(Point2 start, Point2 finish)
        {
            Finish = new Point2(finish);
            Add(start);
            
        }

        public Path(Path source)
        {
            m_stepLookup = source.m_stepLookup;
            m_steps = source.m_steps;
            m_totalWeight = source.m_totalWeight;
            Finish = source.Finish;
        }

        public bool Add(Point2 step)
        {
            if(!m_stepLookup.ContainsKey(step))
            {
                m_stepLookup.Add(step, step);
                m_steps.Add(step);
                m_totalWeight += step.Weight;
                return true;
            }
            return false;
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
            return m_stepLookup.ContainsKey(Finish);
        }
        public Point2 GetLastStep()
        {
            return m_steps[m_steps.Count - 1];
        }

        public IList<Point2> GetNeighbors()
        {
            var neighbors = GetLastStep().GetNeighbors();
            return neighbors;
        }

        public int Length()
        {
            return m_steps.Count;
        }
    }
}
