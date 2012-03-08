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
            {12,ReactionId.SWEAT},
            {13,ReactionId.MAGMA},
            {14,ReactionId.EXPLOSION},
            {15,ReactionId.SCORCH},
            {16,ReactionId.BLIND},
            {17,ReactionId.LACTIC_ACID},
            {18,ReactionId.MIND_BLOWN},
            {23,ReactionId.VENT},
            {24,ReactionId.DROWN},
            {25,ReactionId.REFLECT},
            {26,ReactionId.DRENCH},
            {27,ReactionId.PNEUMONIA},
            {28,ReactionId.LOBOTOMY},
            {34,ReactionId.RUST},
            {35,ReactionId.PURIFY},
            {36,ReactionId.ECLIPSE},
            {37,ReactionId.RESPECT},
            {38,ReactionId.CRAFTSMAN},
            {45,ReactionId.FLASH},
            {46,ReactionId.METABOLISM},
            {47,ReactionId.FAST_FORWARD},
            {48,ReactionId.BLANK},
            {56,ReactionId.YIN_YANG},
            {57,ReactionId.EXPOSE},
            {58,ReactionId.ENLIGHTEN},
            {67,ReactionId.ATROPHY},
            {68,ReactionId.NEUROSIS},
            {78,ReactionId.CONFUSE}
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
