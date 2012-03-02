using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.Entities;

namespace OGUR.Reactions
{
    public class ComboMeter
    {
        private static Dictionary<int, int> s_reactions = new Dictionary<int, int>()
        {
            {12,ReactionId.SWEAT},
            {13,ReactionId.MAGMA},
            {14,ReactionId.EXPLOSION}
        };

        private ICreature m_parent;
        private List<int> m_elements = new List<int>();
        
        public ComboMeter(ICreature parent)
        {
            m_parent = parent;
        }

        public void Add(int element)
        {
            if (!m_elements.Contains(element))
            {
                if (m_elements.Count() == 2)
                {
                    if (m_elements[0] > element)
                    {
                        m_elements.Insert(0, element);
                    }
                    else if (m_elements[1] > element)
                    {
                        m_elements.Insert(1, element);
                    }
                    else
                    {
                        m_elements.Add(element);
                    }
                }
                else if (m_elements.Count() == 1)
                {
                    if (m_elements[0] > element)
                    {
                        m_elements.Insert(0, element);
                    }
                    else
                    {
                        m_elements.Add(element);
                    }
                }
                if (m_elements.Count() == 0)
                {
                    m_elements.Add(element);
                }
            }
        }

        private IReaction reaction;
        public void Update()
        {
            int key = 0;
            if (m_elements.Count() == 3)
            {
                key = (int)m_elements[0] * 100 + (int)m_elements[1] * 10 + (int)m_elements[2];
                React(key);
            }
            if (m_elements.Count() == 2)
            {
                key = (int)m_elements[0] * 10 + (int)m_elements[1];
                React(key);
            }
        }
        private void React(int reactionId)
        {
            if (s_reactions.Keys.Contains(reactionId))
            {
                reaction = ReactionFactory.Create(s_reactions[reactionId]);
                if (reaction != null)
                {
                    reaction.Affect(m_parent);
                }
            }
            m_elements.Clear();
        }
    }
}
