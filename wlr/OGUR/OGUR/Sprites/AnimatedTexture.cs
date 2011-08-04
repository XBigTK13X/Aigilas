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
        private readonly string m_assetPath = @"GameplaySheet";
        private readonly int m_ANIMATE_SPEED = 20;

        private int m_currentFrame;
        private SpriteInfo m_spriteInfo;
        private Rectangle m_currentCell;
        private Texture2D m_graphic;
        private int m_animationTimer;
        private Color m_color = Color.White;

        protected Vector2 m_position = Vector2.Zero;

        public void LoadContent(SpriteType assetName)
        {
            m_graphic = XnaManager.GetContentManager().Load<Texture2D>(m_assetPath);
            m_spriteInfo = SpriteSheetManager.GetSpriteInfo(assetName);
            m_currentFrame = 0;
            m_animationTimer = m_ANIMATE_SPEED;
        }

        public void Draw()
        {
            UpdateAnimation();
            SpriteBatch target = XnaManager.GetRenderTarget();
            //target.Begin();
            target.Begin(SpriteSortMode.BackToFront,
                         BlendState.AlphaBlend,
                         null,
                         null,
                         null,
                         null,
                         XnaManager.GetCamera().GetTransformation(XnaManager.GetGraphicsDevice().GraphicsDevice));
            m_currentCell = new Rectangle(m_currentFrame*m_spriteInfo.X, m_spriteInfo.SpriteIndex*m_spriteInfo.Y,
                                          m_spriteInfo.X, m_spriteInfo.Y);
            Vector2 tempPosition = new Vector2(m_position.X, m_position.Y);
            target.Draw(m_graphic, tempPosition, m_currentCell, m_color);
            target.End();
        }

        private void UpdateAnimation()
        {
            try
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
            catch (Exception e)
            {
                Console.WriteLine("AnimatedTexture.UpdateAnimation() threw an exception.");
                Console.WriteLine(e.Message);
                Console.WriteLine(e.StackTrace);
                Console.WriteLine(e.InnerException);
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

        public Vector2 GetPosition()
        {
            return m_position;
        }

        public void SetPosition(float x, float y)
        {
            m_position.X = (int)x;
            m_position.Y = (int)y;
        }

        public void SetPosition(Point2 position)
        {
            m_position = new Vector2(position.X,position.Y);
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