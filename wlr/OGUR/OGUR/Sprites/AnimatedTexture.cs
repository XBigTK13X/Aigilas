using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework;
using OGUR.Collision;
using OGUR.Management;

namespace OGUR.Sprites
{
    public class AnimatedTexture
    {
        private const string m_assetPath = @"GameplaySheet";
        private const int m_ANIMATE_SPEED = 20;

        private int m_currentFrame;
        private SpriteInfo m_spriteInfo;
        private Rectangle m_currentCell;
        private int m_animationTimer;
        private Color m_color = Color.White;

        protected Vector2 m_position = Vector2.Zero;

        public void LoadContent(SpriteType assetName)
        {
            m_spriteInfo = SpriteSheetManager.GetSpriteInfo(assetName);
            m_currentFrame = 0;
            m_animationTimer = m_ANIMATE_SPEED;
        }

        public void Draw()
        {
            UpdateAnimation();
            m_currentCell = new Rectangle((m_currentFrame * m_spriteInfo.X) + (m_currentFrame+1), (m_spriteInfo.SpriteIndex * m_spriteInfo.Y) + (m_spriteInfo.SpriteIndex+1),
                                          m_spriteInfo.X, m_spriteInfo.Y);
            var target = new Vector2(m_position.X, m_position.Y);
            XnaManager.Renderer.Draw(XnaManager.GetAsset(m_assetPath), target, m_currentCell, m_color);
            
        }

        private void UpdateAnimation()
        {
            if (m_spriteInfo.MaxFrame != 1)
            {
                m_animationTimer--;
                if (m_animationTimer <= 0)
                {
                    m_currentFrame = (m_currentFrame + 1)%m_spriteInfo.MaxFrame;
                    m_animationTimer = m_ANIMATE_SPEED;
                }
            }
        }

        public void SetSpriteInfo(SpriteInfo sprite)
        {
            if (m_spriteInfo != sprite)
            {
                m_spriteInfo = sprite;
                m_currentFrame = 0;
            }
        }

        public void SetPosition(float x, float y)
        {
            m_position.X = (int)x;
            m_position.Y = (int)y;
        }

        public void SetPosition(Point2 position)
        {
            m_position = new Vector2(position.PosX,position.PosY);
        }

        public void SetColor(Color color)
        {
            m_color = color;
        }

        public void SetAlpha(float alpha)
        {
            m_color = new Color(m_color.R, m_color.G, m_color.B,alpha);
        }
    }
}