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
using OGUR.Util;
using OGUR.Statuses;

namespace OGUR.Creatures
{
    public abstract class Strategy
    {
        public const int AttackPlayers = 0;
        public const int ControlledByPlayer = 1;
        public const int Confused = 2;
        public const int MinionRotate = 3;
        public const int MinionFire = 4;
        public const int MinionExplode = 5;
        public const int MinionCloud = 6;
        public const int Mutiny = 7;
        public const int Flee = 8;

        public static readonly int[] Values =
        {
            AttackPlayers,
            ControlledByPlayer,
            Confused,
            MinionRotate,
            MinionFire,
            MinionExplode,
            MinionCloud,
            Mutiny,
            Flee
        };
    }

    public abstract class ICreature : GameplayObject
    {
        protected IStrategy m_strategy;

        protected int m_creatureType;
        protected CreatureClass m_class;
        protected Stats m_baseStats;
        protected Stats m_maxStats;
        protected God m_god;
        protected ICreature m_master;

        protected SkillPool m_skills;
        protected Point2 m_skillVector = new Point2(0,0);
        protected ComboMeter m_combo;
        protected StatusPool m_statuses = new StatusPool();

        protected Inventory m_inventory;
        protected Equipment m_equipment;

        protected HudManager m_hudManager;
        protected readonly ActionTextHandler m_damageText = new ActionTextHandler();

        protected int m_playerIndex = -1;
        protected bool m_isPlaying = true;
        protected int m_currentLevel = 1;
        protected float m_experience;
        protected const float s_levelUpAmonut = 50;
        protected float m_nextLevelExperience = s_levelUpAmonut;

        private int SpriteFromCreature(int type)
        {
            switch (type)
            {
                case CreatureType.MINION: return SpriteType.MINION;
                case CreatureType.PLAYER:return SpriteType.PLAYER_STAND;
                case CreatureType.ZORB:return SpriteType.ZORB;
                default:return SpriteType.CREATURE;
            }
        }

        protected void Setup(Point2 location, int type, Stats stats, CreatureClass creatureClass = null,bool setClass = true)
        {
            Initialize(location, SpriteFromCreature(type), GameObjectType.CREATURE,Depth.Creature);
            Init(type,stats,creatureClass,setClass);
        }
        protected void SetClass(CreatureClass cClass)
        {
            if (m_class != cClass || cClass == null || cClass == CreatureClass.NULL)
            {
                m_class = cClass ?? CreatureClass.NULL;
                m_skills = new SkillPool(this);
                m_skills.Add(m_class.GetLevelSkills(m_currentLevel));
                foreach (var elem in m_composition)
                {
                    m_skills.Add(SkillFactory.GetElementalSkill(elem));
                }
            }
        }

        private void Init(int type, Stats stats, CreatureClass creatureClass = null,bool setClass = true)
        {
            if (setClass)
            {
                SetClass(creatureClass);
            }
            m_inventory = new Inventory(this);
            m_equipment = new Equipment(this);
            m_combo = new ComboMeter(this);
            if (m_playerIndex > -1)
            {
                m_hudManager = new HudManager(this,m_inventory,m_equipment);
            }
            m_isBlocking = true;
            m_creatureType = type;
            m_baseStats = new Stats(stats);
            m_maxStats = new Stats(m_baseStats);
        }

        public void PickupItem(GenericItem item)
        {
            m_inventory.Add(item);
            GameplayObjectManager.RemoveObject(item);
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
            if (item != null)
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
        }

        public GenericItem DestroyRandomItemFromInventory()
        {
            var item = m_inventory.GetNonZeroEntry();
            if (item != null)
            {
                m_inventory.Remove(item);
            }
            return item;
        }

        public override void Update()
        {
            m_statuses.Update();
            if (Get(StatType.HEALTH) <= 0)
            {
                m_isActive = false;
            }
            if (m_statuses.CanMove())
            {
                if (m_isPlaying)
                {
                    if (Get(StatType.MOVE_COOL_DOWN) < GetMax(StatType.MOVE_COOL_DOWN))
                    {
                        Adjust(StatType.MOVE_COOL_DOWN, 1);
                    }
                    Regenerate();
                }
                if (m_strategy != null)
                {
                    m_strategy.Act();
                    m_combo.Update();
                }
            }
            if (m_hudManager != null)
            {
                m_hudManager.Update();
            }
            m_damageText.Update();
        }
        private void Regenerate()
        {
            foreach (string stat in StatType.Values)
            {
                if (stat != StatType.MOVE_COOL_DOWN && stat != StatType.REGEN)
                {
                    if (m_baseStats.GetRaw(stat) < m_maxStats.GetRaw(stat))
                    {
                        Adjust(stat, m_baseStats.Get(StatType.REGEN) / 50);
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
            m_damageText.Draw();
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

        public float Get(string stat)
        {
            return m_baseStats.Get(stat)+CalculateEquipmentBonus(stat)+CalculateInstrinsicBonus(stat);
        }

        private float GetRaw(string stat,bool isMax=false)
        {
            return isMax ? m_maxStats.GetRaw(stat) : m_baseStats.GetRaw(stat);
        }

        private float CalculateInstrinsicBonus(string stat)
        {
            if (m_class == null)
            {
                return 0;
            }
            return m_class.GetBonus(m_currentLevel, stat);
        }

        private float CalculateEquipmentBonus(string stat)
        {
            if (m_equipment != null)
            {
                return m_equipment.CalculateBonus(stat);
            }
            return 0;
        }

        public float GetMax(string stat)
        {
            return (int)m_maxStats.Get(stat) + CalculateEquipmentBonus(stat) + CalculateInstrinsicBonus(stat);
        }

        public float Set(string stat,float value)
        {
            return m_baseStats.Set(stat,value);
        }

        public float SetMax(string stat,float value)
        {
            return m_maxStats.Set(stat,value);
        }

        public float Set(string stat,float value,bool setMax=false)
        {
            return setMax ? SetMax(stat, value) : Set(stat, value);
        }

        protected void InitStat(string stat, float value)
        {
            m_maxStats.Set(stat, value);
            m_baseStats.Set(stat, value);
        }

        public int GetPlayerIndex()
        {
            return m_playerIndex;
        }

        public int GetCreatureType()
        {
            return m_creatureType;
        }

        protected float Adjust(string stat, float adjustment,bool adjustMax = false)
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

        public void ApplyDamage(float damage,ICreature attacker=null,bool showDamage = true,string statType = null)
        {
            if (attacker != null)
            {
                attacker.PassOn(this,StatusComponent.Contagion);
                this.PassOn(attacker, StatusComponent.Passive);
            }
            if (statType == null)
            {
                damage -= m_baseStats.Get(StatType.DEFENSE);
            }
            if (damage <= 0 && statType==null)
            {
                damage = 0;                
            }
            if (showDamage)
            {
                m_damageText.WriteAction(StringStorage.Get(damage), 30, IntStorage.Get(GetLocation().PosCenterX), IntStorage.Get(GetLocation().PosCenterY));
            }
            if(damage>0 && statType==null)
            {
                Adjust(statType??StatType.HEALTH, -damage);
            }
            if (Get(StatType.HEALTH) <= 0)
            {
                m_isActive = false;
                if (attacker != null)
                {
                    attacker.AddExperience(CalculateExperience());
                    attacker.PassOn(attacker, StatusComponent.KillReward);
                }
            }
        }

        public bool LowerStat(string stat, float amount)
        {
            if (amount != 0)
            {
                if (Get(stat) >= amount)
                {
                    Adjust(stat, -amount);
                    return true;
                }
                return false;
            }
            return true;
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
        private List<ICreature> creatures;
        public void MoveIfPossible(float xVel, float yVel)
        {
            if (m_statuses.CanMove())
            {
                if ((xVel != 0 || yVel != 0) && Get(StatType.MOVE_COOL_DOWN) >= GetMax(StatType.MOVE_COOL_DOWN))
                {
                    target.Reset(xVel + GetLocation().PosX, yVel + GetLocation().PosY);
                    if (!CoordVerifier.IsBlocked(target))
                    {
                        Move(xVel, yVel);
                        Set(StatType.MOVE_COOL_DOWN, 0);
                    }
                    else
                    {
                        if(m_statuses.CanAttack())
                        {
                            creatures = GameplayObjectManager.GetCreaturesAt(target);
                            if (creatures.Count() > 0)
                            {
                                foreach (var creature in creatures)
                                {
                                    if (creature != this)
                                    {
                                        if ((creature.GetCreatureType() != CreatureType.PLAYER && m_creatureType == CreatureType.PLAYER)
                                            ||
                                            (creature.GetCreatureType() == CreatureType.PLAYER && m_creatureType != CreatureType.PLAYER)
                                            || m_statuses.WillHitAnything())
                                        {
                                            creature.ApplyDamage(CalculateDamage(), this);
                                            if (!creature.IsActive())
                                            {
                                                AddExperience(creature.CalculateExperience());
                                            }
                                        }
                                    }
                                }
                                Set(StatType.MOVE_COOL_DOWN, 0);
                            }
                        }
                    }
                }
            }
        }
        public void MoveTo(Point2 targetPosition)
        {
            MoveIfPossible(targetPosition.PosX - GetLocation().PosX, targetPosition.PosY - GetLocation().PosY);
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
                    m_nextLevelExperience += s_levelUpAmonut;
                    m_experience = 0;
                    m_currentLevel++;
                    m_skills.Add(m_class.GetLevelSkills(m_currentLevel));
                    TextManager.Add(new ActionText("LEVEL UP!", 100, (int)GetLocation().PosX, (int)GetLocation().PosY));
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

        float lastSum = 0;
        public void UseActiveSkill()
        {
            lastSum = m_baseStats.GetSum();
            m_skills.UseActive();
            if (lastSum != m_baseStats.GetSum())
            {
                m_damageText.WriteAction(GetActiveSkillName(), 40, IntStorage.Get(GetLocation().PosCenterX), IntStorage.Get(GetLocation().PosY));
            }
        }

        public TargetSet GetTargets()
        {
            if (m_strategy == null)
            {
                return m_master.GetTargets();
            }
            return m_strategy.GetTargets();
        }

        

        private string GetLowestStat()
        {
            var result = StatType.AGE;
            var min = float.MaxValue;
            foreach (var stat in StatType.Values.Where(stat => Get(stat)<min && stat != StatType.AGE && stat!= StatType.MOVE_COOL_DOWN && stat != StatType.PIETY))
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

        protected void AssignGod(God god)
        {
            if (m_god != god && m_god != null)
            {
                ApplyDamage(Get(StatType.PIETY));
                Set(StatType.PIETY, 0);
                SetClass(god.GetClass());
            }
            m_god = god;
        }

        public void Combo(List<int> attack)
        {
            foreach(var element in attack)
            {
                m_combo.Add(element);
            }
        }

        public void AddStatus(IStatus status)
        {
            m_statuses.Add(status);
        }

        public void PassOn(ICreature target,StatusComponent componentType)
        {
            m_statuses.PassOn(target, componentType);
        }


        public void SetStrategy(IStrategy strategy)
        {
            m_strategy = strategy;
        }

        public Type GetStrategyType()
        {
            return m_strategy.GetType();
        }
    }
}