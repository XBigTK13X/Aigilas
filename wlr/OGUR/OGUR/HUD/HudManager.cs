using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using Microsoft.Xna.Framework;
using OGUR.Management;
using OGUR.Items;

namespace OGUR.HUD
{
    public class HudManager
    {
        private InventoryHud m_inventory;
        private SkillHud m_skill;
        private ICreature m_parent;

        private static List<Vector2> playerHudPositions = new List<Vector2>()
        {
            new Vector2(0, 0),
            new Vector2(XnaManager.WindowWidth-200, 0),
            new Vector2(0,XnaManager.WindowHeight-100),
            new Vector2(XnaManager.WindowWidth-200,XnaManager.WindowHeight-100)
        };

        public HudManager(ICreature parent,Inventory inventory,Equipment equipment)
        {
            m_parent = parent;
            m_inventory = new InventoryHud(parent, inventory, equipment);
            m_skill = new SkillHud(parent);
            m_skill.Toggle();
        }

        public bool ToggleInventory()
        {
            m_inventory.Toggle();
            m_skill.Toggle();
            return m_inventory.IsVisible();
        }

        public void Update()
        {
            m_inventory.Update();
            m_skill.Update();
        }

        public void Draw()
        {
            m_inventory.Draw();
            m_skill.Draw();
        }
    }
}
