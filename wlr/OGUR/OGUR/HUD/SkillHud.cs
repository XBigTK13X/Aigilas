using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Creatures;
using SPX.Util;
using SPX.Core;
using OGUR.Management;
using System;

namespace OGUR.HUD
{
    [Serializable]
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

        private Vector2 CostOfCurrentSkill()
        {
            return new Vector2(_dimensions.X * .5f, _parent.GetCurrentSkillCost() / _parent.GetMax(StatType.MANA) * _dimensions.Y);
        }

        private string GetSkillStrings()
        {
            return "A:" + _parent.GetActiveSkillName() + __separator +
                     "X:" + _parent.GetHotSkillName(Commands.HotSkill1) + __separator +
                     "Y:" + _parent.GetHotSkillName(Commands.HotSkill2) + __separator +
                     "B:" + _parent.GetHotSkillName(Commands.HotSkill3) + __separator;
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
            XnaManager.Renderer.Draw(_menuBase, _manaPosition, new Rectangle(0, 0, 1, 1), Color.Yellow, 0f, Vector2.Zero, CostOfCurrentSkill(), SpriteEffects.None, ZDepth.HudBG);
            XnaManager.Renderer.Draw(_menuBase, _manaPosition, new Rectangle(0, 0, 1, 1), Color.Blue, 0f, Vector2.Zero, CalculateHeight(StatType.MANA), SpriteEffects.None, ZDepth.HudBG);

            _textHandler.Draw();
        }
    }
}
