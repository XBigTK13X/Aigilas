using System.Collections.Generic;
namespace OGUR.Creatures
{
    /*
    /public enum StatType
    {
        HEALTH,
        MANA,
        STRENGTH,
        WISDOM,
        DEFENSE,
        LUCK,
        AGE,
        HEIGHT,
        WEIGHT,
        MOVE_COOL_DOWN,
        PIETY
    }
     * */
    public class StatType
    {
        public const string HEALTH = "Health";
        public const string MANA = "Mana";
        public const string STRENGTH = "Strength";
        public const string WISDOM = "Wisdom";
        public const string DEFENSE = "Defense";
        public const string LUCK = "Luck";
        public const string AGE = "Age";
        public const string HEIGHT = "Height";
        public const string WEIGHT = "Weight";
        public const string MOVE_COOL_DOWN = "MoveCoolDown";
        public const string PIETY = "Piety";

        public static readonly List<string> Values = new List<string>(){
            HEALTH, 
            MANA, 
            STRENGTH, 
            WISDOM, 
            DEFENSE, 
            LUCK, 
            AGE, 
            HEIGHT, 
            WEIGHT, 
            MOVE_COOL_DOWN, 
            PIETY
        };
    }
}