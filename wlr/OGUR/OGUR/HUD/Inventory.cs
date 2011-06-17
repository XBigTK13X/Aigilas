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
        }
    }
}
