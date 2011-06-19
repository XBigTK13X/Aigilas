using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Creatures;
using OGUR.GameObjects;
using OGUR.Items;
using OGUR.Management;
using OGUR.Text;

namespace OGUR.HUD
{

    class Inventory
    {
        List<GenericItem> m_contents = new List<GenericItem>();
        private ICreature m_target;
        private Vector2 m_position;
        private bool m_isVisible = true;
        private int startingItem = 0;
        private int endingItem = 4;
        private int m_currentClass = 1;

        public Inventory(int playerIndex)
        {
            var player = GameplayObjectManager.GetObjects(CreatureType.PLAYER).Where(o => o.GetPlayerIndex() == playerIndex).FirstOrDefault();
            m_contents = new List<GenericItem>(player.GetInventory());
            m_target = player;
            m_position = new Vector2(playerIndex,playerIndex);
        }

        public bool IsVisible()
        {
            return m_isVisible;
        }

        public List<GenericItem> GetItems()
        {
            return m_contents;
        }

        public void UpdateTargetInventory()
        {
            m_target.SetInventory(m_contents);
        }
        
        public int GetPlayerId()
        {
            return m_target.GetPlayerIndex();
        }

        public void Draw()
        {
            SpriteBatch target = XnaManager.GetRenderTarget();
            //target.Begin();
            target.Begin(SpriteSortMode.BackToFront,
                         BlendState.AlphaBlend,
                         null,
                         null,
                         null,
                         null,
                         XnaManager.GetCamera().GetTransformation(XnaManager.GetGraphicsDevice().GraphicsDevice));
            var tempPosition = new Vector2(0, 0);
            target.Draw(InventoryScreensManager.GetMenuBase(), tempPosition, new Rectangle(0,0,1,1),Color.White,0f,new Vector2(0,0),new Vector2(XnaManager.WindowWidth/2,XnaManager.WindowHeight/2),SpriteEffects.None,0f);
            target.End();
        }

        public void Update()
        {
            if(InputManager.IsPressed(InputManager.Commands.InventoryLeft,m_target.GetPlayerIndex()))
            {
                m_currentClass--;
                if(m_currentClass<=0)
                {
                    m_currentClass = Enum.GetValues(typeof (ItemClass)).Length-1;
                }
                TextManager.Clear();
            }

            if (InputManager.IsPressed(InputManager.Commands.InventoryRight, m_target.GetPlayerIndex()))
            {
                m_currentClass++;
                if (m_currentClass > Enum.GetValues(typeof(ItemClass)).Length - 1)
                {
                    m_currentClass = 1;
                }
                TextManager.Clear();
            }

            if(InputManager.IsPressed(InputManager.Commands.InventoryDown,m_target.GetPlayerIndex()))
            {
                if (startingItem < m_contents.Count() - 1)
                {
                    startingItem++;
                    endingItem++;
                }
                TextManager.Clear();
            }

            if (InputManager.IsPressed(InputManager.Commands.InventoryUp, m_target.GetPlayerIndex()))
            {
                if (startingItem > 0)
                {
                    startingItem--;
                    endingItem--;
                }
                TextManager.Clear();
            }

            var currentClass = (ItemClass)Enum.GetValues(typeof(ItemClass)).GetValue(m_currentClass);
            TextManager.Add(new InventoryItemsText(currentClass.ToString().Replace("_", " "), 140, 30, m_target.GetPlayerIndex()));
            var invClassItems = m_contents.Where(o => o.GetItemClass() == currentClass).ToList();
            

            if(InputManager.IsPressed(InputManager.Commands.Confirm,m_target.GetPlayerIndex()))
            {
                m_target.Equip(invClassItems[startingItem]);
            }

            for(int ii = startingItem;ii<endingItem&&ii<invClassItems.Count();ii++)
            {
                TextManager.Add(new InventoryItemsText(invClassItems[ii].Name, 50, 60 + 25 * ii-startingItem, m_target.GetPlayerIndex()));
            }
        }
    }
}
