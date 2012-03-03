using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Creatures;
using SPX.Util;
using SPX.Core;

namespace OGUR.HUD
{
    public class SkillHud:IHud
    {
        public SkillHud(ICreature owner) : base(owner,200,100) { }
        private const string __separator = "|";
        private const string __newline = "\n";

        public void Draw()
        {
            if (!_isVisible) return;
            XnaManager.Renderer.Draw(_menuBase, GetHudOrigin(), new Rectangle(0, 0, 1, 1), new Color(0f, 0f, 0f, .4f), 0f, new Vector2(0, 0), _dimensions, SpriteEffects.None, ZDepth.HudBG);
            _textHandler.Draw();
        }


        private string skillName = "";
        private readonly Stats _displayStats = new Stats(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f);
        private string statString = "";

        private string GetStatString()
        {
            if (_parent.Get(StatType.HEALTH) == _displayStats.Get(StatType.HEALTH) &&
                _parent.Get(StatType.MANA) == _displayStats.Get(StatType.MANA) &&
                _parent.Get(StatType.STRENGTH) == _displayStats.Get(StatType.STRENGTH) &&
                _parent.Get(StatType.DEFENSE) == _displayStats.Get(StatType.DEFENSE) &&
                _parent.Get(StatType.WISDOM) == _displayStats.Get(StatType.WISDOM) &&
                _parent.Get(StatType.AGE) == _displayStats.Get(StatType.AGE) &&
                _parent.Get(StatType.PIETY) == _displayStats.Get(StatType.PIETY) &&
                _parent.Get(StatType.LUCK) == _displayStats.Get(StatType.LUCK))
            {
                return statString;
            }
            else
            {
                _displayStats.Set(StatType.HEALTH,_parent.Get(StatType.HEALTH));
                _displayStats.Set(StatType.MANA,_parent.Get(StatType.MANA));
                _displayStats.Set(StatType.STRENGTH,_parent.Get(StatType.STRENGTH));
                _displayStats.Set(StatType.DEFENSE,_parent.Get(StatType.DEFENSE));
                _displayStats.Set(StatType.WISDOM, _parent.Get(StatType.WISDOM));
                _displayStats.Set(StatType.AGE, _parent.Get(StatType.AGE));
                _displayStats.Set(StatType.PIETY, _parent.Get(StatType.PIETY));
                _displayStats.Set(StatType.LUCK, _parent.Get(StatType.LUCK));

                statString = StringSquisher.Build(
                    StringStorage.Get(_parent.Get(StatType.HEALTH)), __separator,
                    StringStorage.Get(_parent.Get(StatType.MANA)), __separator,
                    StringStorage.Get(_parent.Get(StatType.STRENGTH)), __separator,
                    StringStorage.Get(_parent.Get(StatType.DEFENSE)),__newline,
                    StringStorage.Get(_parent.Get(StatType.WEIGHT)),__separator,
                    StringStorage.Get(_parent.Get(StatType.WISDOM)),__separator,
                    StringStorage.Get(_parent.Get(StatType.AGE)),__separator,
                    StringStorage.Get(_parent.Get(StatType.PIETY)),__separator,
                    StringStorage.Get(_parent.Get(StatType.LUCK)));
            }
            return statString;
        }

        public void Update()
        {
            if (_isVisible)
            {
                _textHandler.Update();
                _textHandler.Clear();
                if (_parent.GetActiveSkillName() != skillName)
                {
                    skillName = _parent.GetActiveSkillName();
                }
                _textHandler.WriteDefault(skillName, 40, 5, GetHudOrigin());
                _textHandler.WriteDefault(GetStatString(), 20, 35, GetHudOrigin());
            }
        }
    }
}
