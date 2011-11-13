using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using OGUR.Collision;

namespace OGUR.Paths
{

    public class Path
    {
        public Point2 Finish = new Point2(0, 0);
        private List<Point2> m_steps = new List<Point2>();
        private Dictionary<Point2,Point2> m_stepLookup = new Dictionary<Point2,Point2>();
        private float m_totalWeight = 0;

        public Path(){}

        public Path Reset(Point2 start, Point2 finish)
        {
            m_steps.Clear();
            m_stepLookup.Clear();
            m_totalWeight = 0;
            moveIndex = -1;
            Finish.Copy(finish);
            Add(start);
            return this;
        }

        public Path Copy(Path source)
        {
            if (source != null)
            {
                m_stepLookup = StepLookup.Copy(source.m_stepLookup);
                m_steps = Walk.Copy(source.m_steps);
                m_totalWeight = source.m_totalWeight;
                Finish.Copy(source.Finish);
            }
            moveIndex = -1;
            return this;
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

        private int moveIndex = -1;
        public bool HasMoves()
        {
            return moveIndex < m_steps.Count();
        }
        public Point2 GetNextMove()
        {
            moveIndex++;
            if (moveIndex >= m_steps.Count())
            {
                return null;
            }
            if (m_steps.Count() == 0)
            {
                return null;
            }
            return m_steps.Count == 1 ? m_steps[0] : m_steps[moveIndex];
        }

        public bool IsDone()
        {
            return m_stepLookup.ContainsKey(Finish);
        }
        public Point2 GetLastStep()
        {
            return m_steps[m_steps.Count - 1];
        }

        public List<Point2> GetNeighbors()
        {
           return GetLastStep().GetNeighbors();
        }

        public int Length()
        {
            return m_steps.Count;
        }
    }
}
