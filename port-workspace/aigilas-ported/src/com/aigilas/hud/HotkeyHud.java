package com.aigilas.hud;import com.xna.wrapper.*;import java.util.*;import com.aigilas.creatures.*;import com.aigilas.items.*;import com.spx.util.*;import com.spx.core.*;import com.aigilas.management.*;import com.spx.io.*;
    public class HotkeyHud extends IHud
    {
        public HotkeyHud(ICreature owner)
            {        	super(owner, XnaManager.WindowWidth / 2, XnaManager.WindowHeight / 2);            _isVisible = true;
        }

        public void Draw()
        {
        }

        private static List<Integer> _hotSkills = Arrays.asList(
            Commands.HotSkill1,
            Commands.HotSkill2,
            Commands.HotSkill3);

        public void Update(GenericItem item, boolean refresh)
        {
            if (Input.IsActive(Commands.LockSkill, _parent.GetPlayerIndex(), false))
            {
                for (Integer hotSkill:_hotSkills)
                {
                    if (Input.IsActive(hotSkill, _parent.GetPlayerIndex(), false))
                    {
                        _parent.MarkHotSkill(hotSkill);
                    }
                }
            };
        }
    }
