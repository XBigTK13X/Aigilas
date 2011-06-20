
using System.Collections.Generic;
using System.Linq;
using OGUR.Items;
using OGUR.Strategies;
using OGUR.GameObjects;
using OGUR.Sprites;
using OGUR.Collision;
using OGUR.Text;
using OGUR.Storage;

namespace OGUR.Creatures
{
    public abstract class ICreature : GameplayObject
    {
        protected IStrategy m_strategy;
        protected MentalState m_mentality = MentalState.NORMAL;
        protected List<ICreature> m_targets = new List<ICreature>();
        protected Inventory m_inventory;
        protected InventoryHud m_inventoryHud;
        protected Equipment m_equipment;
        protected Stats m_stats;
        protected Stats m_maxStats;
        protected int m_playerIndex = -1;
        protected CreatureType m_creatureType;

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

        protected void Setup(int x, int y, CreatureType type, Stats stats)
        {
            Initialize(x, y, SpriteFromCreature(type), GameObjectType.CREATURE);
            m_inventory = new Inventory(this);
            m_equipment = new Equipment(this);
            if(m_playerIndex>-1)
            {
                m_inventoryHud = new InventoryHud(this);
            }
            m_isBlocking = true;
            m_creatureType = type;
            m_stats = new Stats(stats);
            m_maxStats = new Stats(stats);
        }

        public void PickupItem(GenericItem item)
        {
            m_inventory.Add(item);
        }

        public void Equip(GenericItem item)
        {
            if(m_inventory.Contains(item))
            {
                m_equipment.Register(item);
                m_inventory.Remove(item);
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
            Adjust(StatType.MOVE_COOL_DOWN, -1);
            if (m_inventoryHud != null)
            {
                m_inventoryHud.Update();
            }
            m_strategy.Act(this);
        }

        public override void Draw()
        {
            base.Draw();
            if (m_inventoryHud != null)
            {
                m_inventoryHud.Draw();
            }
        }

        public decimal Get(StatType stat)
        {
            return m_stats.Get(stat);
        }

        public decimal GetMax(StatType stat)
        {
            return (int) m_maxStats.Get(stat);
        }

        public decimal Set(StatType stat,decimal value)
        {
            return m_stats.Set(stat,value);
        }

        public decimal SetMax(StatType stat,decimal value)
        {
            return m_maxStats.Set(stat,value);
        }

        public int GetInt(StatType stat,bool getMax=false)
        {
            return (int) (getMax ? GetMax(stat) : Get(stat));
        }

        public decimal Set(StatType stat,decimal value,bool setMax=false)
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

        protected decimal Adjust(StatType stat, decimal adjustment,bool adjustMax = false)
        {
            return Set(stat, Get(stat) + adjustment,adjustMax);
        }

        public void ApplyDamage(decimal damage)
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

        protected decimal CalculateDamage()
        {
            return m_stats.Get(StatType.STRENGTH);
        }

        public Inventory GetInventory()
        {
            return m_inventory;
        }

        public void ToggleInventoryVisibility()
        {
            if (m_inventoryHud != null)
            {
                m_inventoryHud.Toggle();
            }
        }

        public void MoveIfPossible(int xVel, int yVel)
        {
            if ((xVel != 0 || yVel != 0) && GetInt(StatType.MOVE_COOL_DOWN) <= 0)
            {
                var target = new Point(xVel + (int) GetPosition().X,yVel + (int) GetPosition().Y);
                if (!CoordVerifier.IsBlocked(target.X,target.Y))
                {
                    Move(xVel, yVel);
                    Set(StatType.MOVE_COOL_DOWN, GetMax(StatType.MOVE_COOL_DOWN));
                }
                else
                {
                    var creatures = GameplayObjectManager.GetObjects(GameObjectType.CREATURE,target).Cast<ICreature>().Where(creature => creature!=this);
                    if(creatures.Count()>0)
                    {
                        foreach (var creature in creatures)
                        {
                            creature.ApplyDamage(CalculateDamage());
                        }
                        Set(StatType.MOVE_COOL_DOWN, GetMax(StatType.MOVE_COOL_DOWN));    
                    }
                }
            }
        }

        public bool IsEquipped(GenericItem item)
        {
            return m_equipment.IsRegistered(item);
        }
    }
}