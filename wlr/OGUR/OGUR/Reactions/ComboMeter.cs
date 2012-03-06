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
        private static Dictionary<int, int> __reactions = new Dictionary<int, int>()
        {
            {12,ReactionId.ATROPHY},
            {13,ReactionId.BLANK},
            {14,ReactionId.BLIND},
            {15,ReactionId.CONFUSE},
            {16,ReactionId.CRAFTSMAN},
            {17,ReactionId.DRENCH},
            {18,ReactionId.DROWN},
            {23,ReactionId.ECLIPSE},
            {24,ReactionId.ENLIGHTEN},
            {25,ReactionId.EXPLOSION},
            {26,ReactionId.EXPOSE},
            {27,ReactionId.FAST_FORWARD},
            {28,ReactionId.FLASH},
            {34,ReactionId.LACTIC_ACID},
            {35,ReactionId.LOBOTOMY},
            {36,ReactionId.MAGMA},
            {37,ReactionId.METABOLISM},
            {38,ReactionId.MIND_BLOWN},
            {45,ReactionId.NEUROSIS},
            {46,ReactionId.PNEUMONIA},
            {47,ReactionId.PURIFY},
            {48,ReactionId.REFLECT},
            {56,ReactionId.RESPECT},
            {57,ReactionId.RUST},
            {58,ReactionId.SCORCH},
            {67,ReactionId.SWEAT},
            {68,ReactionId.VENT},
            {78,ReactionId.YIN_YANG}
        };

        private ICreature _parent;
        private List<int> _elements = new List<int>();
        
        public ComboMeter(ICreature parent)
        {
            _parent = parent;
        }

        public void Add(int element)
        {
            if (!_elements.Contains(element))
            {
                if (_elements.Count() == 2)
                {
                    if (_elements[0] > element)
                    {
                        _elements.Insert(0, element);
                    }
                    else if (_elements[1] > element)
                    {
                        _elements.Insert(1, element);
                    }
                    else
                    {
                        _elements.Add(element);
                    }
                }
                else if (_elements.Count() == 1)
                {
                    if (_elements[0] > element)
                    {
                        _elements.Insert(0, element);
                    }
                    else
                    {
                        _elements.Add(element);
                    }
                }
                if (_elements.Count() == 0)
                {
                    _elements.Add(element);
                }
            }
        }

        private IReaction reaction;
        public void Update()
        {
            int key = 0;
            if (_elements.Count() == 3)
            {
                key = (int)_elements[0] * 100 + (int)_elements[1] * 10 + (int)_elements[2];
                React(key);
            }
            if (_elements.Count() == 2)
            {
                key = (int)_elements[0] * 10 + (int)_elements[1];
                React(key);
            }
        }
        private void React(int reactionId)
        {
            if (__reactions.Keys.Contains(reactionId))
            {
                reaction = ReactionFactory.Create(__reactions[reactionId]);
                if (reaction != null)
                {
                    reaction.Affect(_parent);
                }
            }
            _elements.Clear();
        }
    }
}
