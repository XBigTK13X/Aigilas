using System;
using Agilas.Creatures;
using Agilas.Items;
using SPX.Util;
using SPX.Core;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Agilas.Management;
using System.Collections.Generic;

namespace Agilas.HUD
{
    public class HotkeyHud:IHud
    {
        public HotkeyHud(ICreature owner)
            : base(owner, XnaManager.WindowWidth / 2, XnaManager.WindowHeight / 2)
        {
            _isVisible = true;
        }

        public void Draw()
        {
        }

        private static readonly ICollection<int> _hotSkills = new List<int>()
        {
            Commands.HotSkill1,
            Commands.HotSkill2,
            Commands.HotSkill3
        };

        public void Update(GenericItem item, bool refresh)
        {
            if (Input.IsPressed(Commands.LockSkill, _parent.GetPlayerIndex(), false))
            {
                foreach (var hotSkill in _hotSkills)
                {
                    if (Input.IsPressed(hotSkill, _parent.GetPlayerIndex(), false))
                    {
                        _parent.MarkHotSkill(hotSkill);
                    }
                }
            };
        }
    }
}
