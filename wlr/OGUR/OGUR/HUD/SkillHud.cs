using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Creatures;
using SPX.Util;
using SPX.Core;

namespace OGUR.HUD
{
    public class SkillHud:IHud
    {
        private static string __separator = "|";
        
        private Vector2 _manaPosition = new Vector2();
        
        public SkillHud(ICreature owner) : base(owner,GameManager.SpriteWidth,XnaManager.WindowHeight / 4) 
        {
            _manaPosition = new Vector2(GetHudOrigin().X, GetHudOrigin().Y + XnaManager.WindowHeight / 4);
        }      

        private Vector2 CalculateHeight(string statType)
        {
            return new Vector2(_dimensions.X,(_parent.Get(statType) / _parent.GetMax(statType)) * _dimensions.Y);
        }

        private string GetSkillStrings()
        {
            var result = "";
            foreach(var hotSkill in _parent.GetHotSkillNames())
            {
                result += hotSkill + __separator;
            }
            result += _parent.GetActiveSkillName();
            return result;
        }

        public void Update()
        {
            if (_isVisible)
            {
                _textHandler.Update();
                _textHandler.Clear();
                _textHandler.WriteDefault(GetSkillStrings(),GameManager.SpriteWidth, 0, GetHudOrigin());
            }
        }

        public void Draw()
        {
            if (!_isVisible) return;

            XnaManager.Renderer.Draw(_menuBase, GetHudOrigin(), new Rectangle(0, 0, 1, 1), Color.Green, 0f, Vector2.Zero, CalculateHeight(StatType.HEALTH), SpriteEffects.None, ZDepth.HudBG);
            XnaManager.Renderer.Draw(_menuBase, _manaPosition, new Rectangle(0, 0, 1, 1), Color.Blue, 0f, Vector2.Zero, CalculateHeight(StatType.MANA), SpriteEffects.None, ZDepth.HudBG);

            _textHandler.Draw();
        }
    }
}
