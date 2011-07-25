
using System;
using System.Collections;
using System.Collections.Generic;
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
using OGUR.Storage;
using OGUR.Gods;

namespace OGUR.Creatures
{
    public abstract class ICreature : GameplayObject
    {
        protected IStrategy m_strategy;
        protected MentalState m_mentality = MentalState.NORMAL;

        protected CreatureType m_creatureType;
        protected CreatureClass m_class;
        protected Stats m_stats;
        protected Stats m_maxStats;
        protected God m_god;

        protected SkillPool m_skills;
        protected Point2 m_skillVector;

        protected Inventory m_inventory;
        protected Equipment m_equipment;

        protected InventoryHud m_inventoryHud;
        protected EquipmentHud m_equipmentHud;
        protected DeltasHud m_deltasHud;
        protected SkillHud m_skillHud;

        protected int m_playerIndex = -1;
        protected bool m_isPlaying = true;
        protected int m_currentLevel = 1;
        protected float m_experience = 0;
        protected float m_nextLevelExperience = 100;

        private SpriteType SpriteFromCreature(CreatureType type)
        {
            switch (type)
            {
                case CreatureType.PLAYER:
                    return SpriteType.PLAYER_STAND;
                default:
                    return SpriteType.CREATURE;
            }
        }

        protected void Setup(float x, float y, CreatureType type, Stats stats,CreatureClass creatureClass=null)
        {
            Initialize(x, y, SpriteFromCreature(type), GameObjectType.CREATURE);
            m_inventory = new Inventory(this);
            m_equipment = new Equipment(this);
            if(m_playerIndex>-1)
            {
                m_inventoryHud = new InventoryHud(this);
                m_equipmentHud = new EquipmentHud(this);
                m_deltasHud = new DeltasHud(this);
                m_skillHud = new SkillHud(this);
                m_skillHud.Toggle();
            }
            m_skills = new SkillPool(this);
            m_class = creatureClass ?? new NoClass();
            m_isBlocking = true;
            m_creatureType = type;
            m_skills.Add(m_class.GetLevelSkills(m_currentLevel));
            m_stats = new Stats(stats);
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

        public void  Drop(GenericItem item)
        {
            if (m_inventory.GetItemCount(item)>0)
            {
                GameplayObjectManager.AddObject(new GenericItem(item, GetPosition().X, GetPosition().Y));
                m_inventory.Remove(item);
            }
            else
            {
                if (m_inventory.GetItemCount(item) == 0)
                {
                    m_equipment.Unregister(item);
                    GameplayObjectManager.AddObject(new GenericItem(item, GetPosition().X, GetPosition().Y));
                    m_inventory.Remove(item);
                }
            }
        }

        public void Unequip(GenericItem item)
        {
            if (m_equipment.IsRegistered(item))
            {
                m_equipment.Unregister(item);
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
                if (m_inventoryHud != null)
                {
                    m_inventoryHud.Update();
                    m_equipmentHud.Update();
                    m_deltasHud.Update();
                    m_skillHud.Update();
                }
            }
            m_strategy.Act(this);
        }

        public override void Draw()
        {
            base.Draw();
            if (m_inventoryHud != null)
            {
                m_inventoryHud.Draw();
                m_equipmentHud.Draw();
                m_deltasHud.Draw();
                m_skillHud.Draw();
            }
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
            return m_stats.Get(stat)+CalculateEquipmentBonus(stat)+CalculateInstrinsicBonus(stat);
        }

        private float CalculateInstrinsicBonus(StatType stat)
        {
            return m_class.GetBonus(m_currentLevel, stat);
        }

        public float CalculateEquipmentBonus(StatType stat)
        {
            return m_equipment.CalculateBonus(stat);
        }

        public float GetMax(StatType stat)
        {
            return (int)m_maxStats.Get(stat) + CalculateEquipmentBonus(stat) + CalculateInstrinsicBonus(stat);
        }

        public float Set(StatType stat,float value)
        {
            return m_stats.Set(stat,value);
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
            return Set(stat, Get(stat) + adjustment,adjustMax);
        }

        public void ApplyDamage(float damage)
        {
            damage -= m_stats.Get(StatType.DEFENSE);
            if (damage <= 0)
            {
                damage = 0;
                
            }
            TextManager.Add(new ActionText(damage.ToString(),30, (int)this.GetPosition().X + SpriteInfo.Width / 2,
                                       (int)this.GetPosition().Y + SpriteInfo.Height / 2));
            if(damage>0)
            {
                Adjust(StatType.HEALTH, -damage);
            }
            if (Get(StatType.HEALTH) <= 0)
            {
                m_isActive = false;
            }
        }

        protected float CalculateDamage()
        {
            return Get(StatType.STRENGTH);
        }

        public Inventory GetInventory()
        {
            return m_inventory;
        }

        public Equipment GetEquipment()
        {
            return m_equipment;
        }

        public GenericItem GetCurrentInventorySelection()
        {
            return m_inventoryHud.GetCurrentSelection();
        }

        public GenericItem GetEquipmentIn(ItemSlot slot)
        {
            var result = m_equipment.GetItems().Where(o => o.Key == slot);
            if(result.Count()>0)
            {
                return result.First().Value;
            }
            return null;
        }

        public bool ToggleInventoryVisibility()
        {
            if (m_inventoryHud != null)
            {
                m_inventoryHud.Toggle();
                m_equipmentHud.Toggle();
                m_deltasHud.Toggle();
                m_skillHud.Toggle();
                return m_inventoryHud.IsVisible();
            }
            return false;
        }

        public void MoveIfPossible(float xVel, float yVel)
        {
            if ((xVel != 0 || yVel != 0) && Get(StatType.MOVE_COOL_DOWN) <= 0)
            {
                var target = new Point2(xVel + GetPosition().X + SpriteInfo.Width / 2, yVel + GetPosition().Y + SpriteInfo.Height / 2);
                if (!CoordVerifier.IsBlocked(target.X,target.Y))
                {
                    Move(xVel, yVel);
                    Set(StatType.MOVE_COOL_DOWN, GetMax(StatType.MOVE_COOL_DOWN));
                }
                else
                {
                    var creatures = GameplayObjectManager.GetObjects(GameObjectType.CREATURE,target).Cast<ICreature>().Where(creature => creature!=this).ToList();
                    if(creatures.Count()>0)
                    {
                        foreach (var creature in creatures)
                        {
                            creature.ApplyDamage(CalculateDamage());
                            if(!creature.IsActive())
                            {
                                AddExperience(creature.CalculateExperience());
                            }
                        }
                        Set(StatType.MOVE_COOL_DOWN, GetMax(StatType.MOVE_COOL_DOWN));    
                    }
                }
            }
        }

        public Point2 GetSkillVector()
        {
            return new Point2(m_skillVector);
        }

        public void AddExperience(float amount)
        {
            m_experience += amount;
            if(m_experience>m_nextLevelExperience)
            {
                m_nextLevelExperience += 100;
                m_experience = 0;
                m_currentLevel++;
                TextManager.Add(new ActionText("LEVEL UP!",30,(int)GetPosition().X,(int)GetPosition().Y));
            }
        }

        public float CalculateExperience()
        {
            return m_currentLevel + m_stats.GetSum();
        }
        public bool IsEquipped(GenericItem item)
        {
            return m_equipment.IsRegistered(item);
        }

        public Vector2 GetHudOrigin()
        {
            switch(m_playerIndex)
            {
                case 0:
                    return new Vector2(0, 0);
                case 1:
                    return new Vector2(XnaManager.GetCenter().X, 0);
                case 2:
                    return new Vector2(0,XnaManager.GetCenter().Y);
                case 3:
                    return XnaManager.GetCenter();
                default:
                    throw new Exception("The given player index is outside the range of players allowed in the game!");
            }
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
            return m_strategy.GetTargets();
        }

        public void SetSkillVector(Point2 skillVector)
        {
            m_skillVector = new Point2(skillVector);
        }

        private StatType GetLowestStat()
        {
            var result = StatType.AGE;
            var min = float.MaxValue;
            foreach (var stat in Enum.GetValues(typeof(StatType)).Cast<StatType>().Where(stat => Get(stat)<min && stat != StatType.AGE && stat!= StatType.MOVE_COOL_DOWN && stat != StatType.PIETY))
            {
                result = stat;
                min = Get(stat);
            }
            return result;
        }

        public void Sacrifice(God god, GenericItem sacrifice)
        {
            AssignGod(god);
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
    }
}