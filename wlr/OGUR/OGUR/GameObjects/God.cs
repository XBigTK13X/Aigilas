using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using OGUR.Creatures;
using OGUR.Items;

namespace OGUR.Gods
{
    public class God
    { 
        public enum Name
        {
            LUST,
            GREED,
            SLOTH,
            ENVY,
            WRATH,
            GLUTTONY,
            PRIDE
        }
            
        public static God Get(Name name)
        {
            return s_gods.Where(o => o.m_name == name).FirstOrDefault();
        }


        private static List<God> s_gods = new List<God>()
                                       {
                                           new God(Color.Pink,Name.LUST,ItemClass.Leggings,ItemClass.Melee_Weapon),
                                           new God(Color.Gold,Name.GREED,ItemClass.Head_Gear,ItemClass.Gloves),
                                           new God(Color.Black,Name.SLOTH,ItemClass.Shield,ItemClass.Head_Gear),
                                           new God(Color.Green,Name.ENVY,ItemClass.Torso_Garb,ItemClass.Ranged_Weapon),
                                           new God(Color.Red,Name.WRATH,ItemClass.Melee_Weapon,ItemClass.Ranged_Ammo),
                                           new God(Color.YellowGreen,Name.GLUTTONY,ItemClass.Gloves,ItemClass.Torso_Garb),
                                           new God(Color.Indigo,Name.PRIDE,ItemClass.Ring,ItemClass.Feet)
                                       };

        static public void Reset()
        {
            foreach(God god in s_gods)
            {
                god.m_pietyTracker = new Dictionary<ICreature, decimal>();
            }

        }

        private Color m_color;
        private Name m_name;
        private ItemClass m_goodSacrificeClass;
        private ItemClass m_badSacrificeClass;

        private Dictionary<ICreature, decimal> m_pietyTracker = new Dictionary<ICreature, decimal>();

        protected God(Color color, Name name,ItemClass goodSacrifice,ItemClass badSacrifice)
        {
            m_color = color;
            m_name = name;
            m_goodSacrificeClass = goodSacrifice;
            m_badSacrificeClass = badSacrifice;
        }

        public Color GetColor()
        {
            return m_color;
        }

        public override string ToString()
        {
            return m_name.ToString().ToUpperInvariant();
        }

        public void AcceptSacrifice(ICreature servant,GenericItem sacrifice)
        {
            RejectAdulturer(servant);
            RememberCreature(servant);
            m_pietyTracker[servant] += sacrifice.Modifers.GetSum() * ((sacrifice.GetItemClass() == m_goodSacrificeClass) ? 3 : 1) * ((sacrifice.GetItemClass() == m_badSacrificeClass) ? -2 : 1);
            sacrifice.SetInactive();
        }

        public void AnswerPrayer(ICreature player)
        {
            RejectAdulturer(player);
            var knewServant = RememberCreature(player);
            var lowest = player.GetLowestStat();
            var adjustment = (m_pietyTracker[player]/100);
            player.Bless(lowest,adjustment);
            if(knewServant)
            {
                m_pietyTracker[player] -= 500;    
            }
        }

        public void Reject(ICreature servant)
        {
            var piety = m_pietyTracker[servant];
            m_pietyTracker.Remove(servant);
            servant.ApplyDamage(piety);
        }

        private bool RememberCreature(ICreature servant)
        {
            if (!m_pietyTracker.ContainsKey(servant))
            {
                m_pietyTracker.Add(servant, 0);
                return false;
            }
            return true;
        }

        private void RejectAdulturer(ICreature player)
        {
            var otherGod = s_gods.Where(o => o.m_pietyTracker.Keys.Contains(player) && o != this).FirstOrDefault();
            if (otherGod != null)
            {
                otherGod.Reject(player);
            }
        }

        private string GetPietyString()
        {
            return m_pietyTracker.Keys.Aggregate("", (current, servant) => current + m_pietyTracker[servant]+"|");
        }

        public static string GetDebugString()
        {
            return s_gods.Aggregate("", (current, god) => current + god.GetPietyString());
        }
    }
}   