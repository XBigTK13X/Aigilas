using System;
using System.Linq;
using Microsoft.Xna.Framework;
using OGUR.Classes;
using OGUR.Items;
using OGUR.Management;
using OGUR.Skills;
using OGUR.Strategies;
using OGUR.GameObjects;
using OGUR.Sprites;
using OGUR.Collision;
using OGUR.Text;
using OGUR.Gods;
using OGUR.Reactions;
using System.Collections.Generic;
using OGUR.HUD;

namespace OGUR.Creatures
{
    public abstract class ICreature : GameplayObject
    {
        protected IStrategy m_strategy;

        protected CreatureType m_creatureType;
        protected CreatureClass m_class;
        protected Stats m_baseStats;
        protected Stats m_maxStats;
        protected God m_god;
        protected ICreature m_master;

        protected SkillPool m_skills;
        protected Point2 m_skillVector = new Point2(0,0);
        protected ComboMeter m_combo;

        protected Inventory m_inventory;
        protected Equipment m_equipment;

        protected HudManager m_hudManager;

        protected int m_playerIndex = -1;
        protected bool m_isPlaying = true;
        protected int m_currentLevel = 1;
        protected float m_experience;
        protected float m_nextLevelExperience = 100;

        private SpriteType SpriteFromCreature(CreatureType type)
        {
            switch (type)
            {
                case CreatureType.PLAYER:
                    return SpriteType.PLAYER_STAND;
                case CreatureType.ZORB:
                    return SpriteType.ZORB;
                case CreatureType.MINION:
                    return SpriteType.MINION;
                default:
                    return SpriteType.CREATURE;
            }
        }

        protected void Setup(Point2 location, CreatureType type, Stats stats, CreatureClass creatureClass = null)
        {
            Initialize(location, SpriteFromCreature(type), GameObjectType.CREATURE,.5f);
            Init(type,stats,creatureClass);
        }

        private void Init(CreatureType type, Stats stats, CreatureClass creatureClass = null)
        {
            m_inventory = new Inventory(this);
            m_equipment = new Equipment(this);
            m_combo = new ComboMeter(this);
            if (m_playerIndex > -1)
            {
                m_hudManager = new HudManager(this,m_inventory,m_equipment);
            }
            
            m_class = creatureClass ?? new NoClass();
            m_isBlocking = true;
            m_creatureType = type;
            if (m_skills == null)
            {
                m_skills = new SkillPool(this);
                m_skills.Add(m_class.GetLevelSkills(m_currentLevel));
            }
            m_baseStats = new Stats(stats);
            m_maxStats = new Stats(stats);

        }

        public void PickupItem(GenericItem item)
        {
            m_inventory.Add(item);
        }

        public void Equip(GenericItem item)
        {
            if (m_inventory.GetItemCount(item) > 0 && !m_equipment.IsRegistered(item))
            {
                m_equipment.Register(item);
                m_inventory.Remove(item);
            }
            else
            {
                if (m_equipment.IsRegistered(item))
                {
                    m_equipment.Unregister(item);
                }
            }
        }

        public void Drop(GenericItem item)
        {
            if (m_inventory.GetItemCount(item) > 0)
            {
                GameplayObjectManager.AddObject(new GenericItem(item, GetLocation()));
                m_inventory.Remove(item);
            }
            else
            {
                if (m_inventory.GetItemCount(item) == 0)
                {
                    m_equipment.Unregister(item);
                    GameplayObjectManager.AddObject(new GenericItem(item, GetLocation()));
                    m_inventory.Remove(item);
                }
            }
        }       

        public override void Update()
        {
            if (m_isPlaying)
            {
                if(Get(StatType.MOVE_COOL_DOWN)>0)
                {
                    Adjust(StatType.MOVE_COOL_DOWN, -1);    
                }
                if (m_hudManager != null)
                {
                    m_hudManager.Update();
                }
                Regenerate();
            }
            if (m_strategy != null)
            {
                m_strategy.Act(this);
                m_combo.Update();
            }
        }
        private void Regenerate()
        {
            foreach (StatType stat in OGUR.Util.EnumUtil.GetValues(typeof(StatType)))
            {
                if (stat != StatType.MOVE_COOL_DOWN)
                {
                    if (m_baseStats.GetRaw(stat) < m_maxStats.GetRaw(stat))
                    {
                        Adjust(stat, m_baseStats.Get(StatType.STRENGTH) / 50);
                    }
                }
            }
        }
        public override void Draw()
        {
            base.Draw();
            if (m_hudManager != null)
            {
                m_hudManager.Draw();
            }
        }

        public bool ToggleInventoryVisibility()
        {
            if (m_hudManager != null)
            {
                return m_hudManager.ToggleInventory();
            }
            return false;
        }

        public void SetPlaying(bool isPlaying)
        {
            m_isPlaying = isPlaying;
        }

        public bool IsPlaying()
        {
            return m_isPlaying;
        }

        public float Get(StatType stat)
        {
            return m_baseStats.Get(stat)+CalculateEquipmentBonus(stat)+CalculateInstrinsicBonus(stat);
        }

        private float GetRaw(StatType stat,bool isMax=false)
        {
            return isMax ? m_maxStats.GetRaw(stat) : m_baseStats.GetRaw(stat);
        }

        private float CalculateInstrinsicBonus(StatType stat)
        {
            if (m_class == null)
            {
                return 0;
            }
            return m_class.GetBonus(m_currentLevel, stat);
        }

        private float CalculateEquipmentBonus(StatType stat)
        {
            if (m_equipment != null)
            {
                return m_equipment.CalculateBonus(stat);
            }
            return 0;
        }

        public float GetMax(StatType stat)
        {
            return (int)m_maxStats.Get(stat) + CalculateEquipmentBonus(stat) + CalculateInstrinsicBonus(stat);
        }

        public float Set(StatType stat,float value)
        {
            return m_baseStats.Set(stat,value);
        }

        public float SetMax(StatType stat,float value)
        {
            return m_maxStats.Set(stat,value);
        }

        public float Set(StatType stat,float value,bool setMax=false)
        {
            return setMax ? SetMax(stat, value) : Set(stat, value);
        }

        public int GetPlayerIndex()
        {
            return m_playerIndex;
        }

        public CreatureType GetCreatureType()
        {
            return m_creatureType;
        }

        protected float Adjust(StatType stat, float adjustment,bool adjustMax = false)
        {
            var result = GetRaw(stat) + adjustment;
            if (!adjustMax)
            {
                if (result > GetRaw(stat, true))
                {
                    result = GetRaw(stat, true);
                }
            }
            return Set(stat, (result),adjustMax);
        }

        public void ApplyDamage(float damage,ICreature attacker=null,bool showDamage = true)
        {
            damage -= m_baseStats.Get(StatType.DEFENSE);
            if (damage <= 0)
            {
                damage = 0;
                
            }
            if (showDamage)
            {
                TextManager.Add(new ActionText(damage.ToString(), 30, (int)GetLocation().PosCenterX, (int)GetLocation().PosCenterY));
            }
            if(damage>0)
            {
                Adjust(StatType.HEALTH, -damage);
            }
            if (Get(StatType.HEALTH) <= 0)
            {
                m_isActive = false;
                if (attacker != null)
                {
                    attacker.AddExperience(CalculateExperience());
                }
            }
        }

        public bool LowerStat(StatType stat, float amount)
        {
            if (amount != 0)
            {
                if (Get(stat) >= amount)
                {
                    Adjust(stat, -amount);
                    return true;
                }
            }
            return false;
        }

        public void AddBuff(StatBuff buff)
        {
            m_baseStats.AddBuff(buff);
        }

        protected float CalculateDamage()
        {
            return Get(StatType.STRENGTH);
        }

        private Point2 target = new Point2(0, 0);
        private IEnumerable<ICreature> creatures;
        public void MoveIfPossible(float xVel, float yVel)
        {
            if ((xVel != 0 || yVel != 0) && Get(StatType.MOVE_COOL_DOWN) <= 0)
            {
                target.Reset(xVel + GetLocation().PosX , yVel + GetLocation().PosY);
                if (!CoordVerifier.IsBlocked(target))
                {
                    Move(xVel, yVel);
                    Set(StatType.MOVE_COOL_DOWN, GetMax(StatType.MOVE_COOL_DOWN));
                }
                else
                {
                    creatures = GameplayObjectManager.GetObjects(GameObjectType.CREATURE, target).Cast<ICreature>();
                    if(creatures.Count()>0)
                    {
                        foreach (var creature in creatures)
                        {
                            if (creature != this)
                            {
                                creature.ApplyDamage(CalculateDamage(), this);
                                if (!creature.IsActive())
                                {
                                    AddExperience(creature.CalculateExperience());
                                }
                            }
                        }
                        Set(StatType.MOVE_COOL_DOWN, GetMax(StatType.MOVE_COOL_DOWN));    
                    }
                }
            }
        }

        public Point2 GetSkillVector()
        {
            if(null == m_skillVector)
            {
                return null;
            }
            return m_skillVector;
        }
        
        public void SetSkillVector(Point2 skillVector)
        {
            m_skillVector.SetX(skillVector.X);
            m_skillVector.SetY(skillVector.Y);
        }

        public void AddExperience(float amount)
        {
            
            while (amount > 0)
            {
                var diff = amount;
                if (amount > m_nextLevelExperience)
                {
                    diff = m_nextLevelExperience;
                    amount -= m_nextLevelExperience;
                }
                else
                {
                    amount = 0;
                }
                m_experience += diff;
                if (m_experience > m_nextLevelExperience)
                {
                    m_nextLevelExperience += 100;
                    m_experience = 0;
                    m_currentLevel++;
                    TextManager.Add(new ActionText("LEVEL UP!", 30, (int)GetLocation().PosX, (int)GetLocation().PosY));
                }
            }
        }

        public float CalculateExperience()
        {
            return m_currentLevel + m_baseStats.GetSum();
        }

        public void CycleActiveSkill(int velocity)
        {
            m_skills.Cycle(velocity);
        }

        public string GetActiveSkillName()
        {
            return m_skills.GetActiveName();
        }

        public void UseActiveSkill()
        {
            m_skills.UseActive();
        }

        public TargetSet GetTargets()
        {
            if (m_strategy == null)
            {
                return m_master.GetTargets();
            }
            return m_strategy.GetTargets();
        }

        

        private StatType GetLowestStat()
        {
            var result = StatType.AGE;
            var min = float.MaxValue;
            foreach (var stat in OGUR.Util.EnumUtil.GetValues(typeof(StatType)).Cast<StatType>().Where(stat => Get(stat)<min && stat != StatType.AGE && stat!= StatType.MOVE_COOL_DOWN && stat != StatType.PIETY))
            {
                result = stat;
                min = Get(stat);
            }
            return result;
        }

        public void Sacrifice(God god, GenericItem sacrifice)
        {
            AssignGod(god);
            Adjust(StatType.PIETY, sacrifice.Modifers.GetSum() * ((m_god.IsGoodSacrifice(sacrifice.GetItemClass())) ? 3 : 1) * ((m_god.IsBadSacrifice(sacrifice.GetItemClass())) ? -2 : 1), true);
            Adjust(StatType.PIETY,sacrifice.Modifers.GetSum() * ((m_god.IsGoodSacrifice(sacrifice.GetItemClass())) ? 3 : 1) * ((m_god.IsBadSacrifice(sacrifice.GetItemClass())) ? -2 : 1));
            sacrifice.SetInactive();
        }

        public void Pray(God god)
        {
            AssignGod(god);
            const int pietyCost = 500;
            if (Get(StatType.PIETY) >= pietyCost)
            {
                var lowest = GetLowestStat();
                var adjustment = (Get(StatType.PIETY)/100);
                Set(lowest, GetMax(lowest) + adjustment);
                Set(lowest, adjustment, true);
                Adjust(StatType.PIETY, -pietyCost);
                if (Get(StatType.PIETY) < 0)
                {
                    Set(StatType.PIETY, 0);
                }
            }
            PerformInteraction();
        }

        private void AssignGod(God god)
        {
            if (m_god != god && m_god != null)
            {
                ApplyDamage(Get(StatType.PIETY));
                Set(StatType.PIETY, 0);
            }
            m_god = god;
        }

        public void MoveTo(Point2 targetPosition)
        {
            MoveIfPossible(targetPosition.PosX-GetLocation().PosX,targetPosition.PosY-GetLocation().PosY);
        }

        public void Combo(List<Elements> attack)
        {
            foreach(var element in attack)
            {
                m_combo.Add(element);
            }
        }
    }
}