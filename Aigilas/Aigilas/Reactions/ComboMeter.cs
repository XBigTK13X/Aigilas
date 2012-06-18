using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Aigilas.Creatures;
using Aigilas.Entities;
using SPX.Text;

namespace Aigilas.Reactions
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
        private List<ComboMarker> _markers = new List<ComboMarker>();
        private const int _maxTimer = 120;
        private int _reactionTimer = _maxTimer;
        

        public ComboMeter(ICreature parent)
        {
            _parent = parent;
        }

        private void ResetComboDisplay()
        {
            _reactionTimer = _maxTimer;
            for (int ii = 0; ii < _markers.Count(); ii++)
            {
                _markers[ii].SetInactive();
            }
            _markers.Clear();
            int jj = 0;
            foreach (var element in _elements)
            {
                _markers.Add(new ComboMarker(_parent,element,jj));
                jj++;
                _markers.Last().LoadContent();
            }
        }

        public void Draw()
        {
            foreach (var marker in _markers)
            {
                marker.Draw();
            }
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
                ResetComboDisplay();
            }
        }

        private IReaction reaction;
        public void Update()
        {
            foreach (var marker in _markers)
            {
                marker.Update();
            }
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
            if (_elements.Count() == 1)
            {
                React((int)_elements[0]);
            }
        }
        private void React(int reactionId)
        {
            _reactionTimer--;
            if (_reactionTimer <= 0)
            {
                if (__reactions.Keys.Contains(reactionId))
                {
                    reaction = ReactionFactory.Create(__reactions[reactionId]);
                    if (reaction != null)
                    {
                        reaction.Affect(_parent);
                        TextManager.Add(new ActionText(ReactionId.Names[__reactions[reactionId]], 10, (int)_parent.GetLocation().PosX, (int)_parent.GetLocation().PosY));
                    }
                }
                _elements.Clear();
                ResetComboDisplay();
                _reactionTimer = _maxTimer;
            }
        }
    }
}
