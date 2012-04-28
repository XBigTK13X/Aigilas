using System;
using System.Collections.Generic;
using System.Linq;
using OGUR.Classes;
using OGUR.Gods;
using OGUR.HUD;
using OGUR.Items;
using OGUR.Reactions;
using OGUR.Skills;
using OGUR.Statuses;
using OGUR.Strategies;
using SPX.Core;
using SPX.Entities;
using SPX.Sprites;
using SPX.Text;
using SPX.Util;
using OGUR.Management;

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
        public const int AttackSelf = 9;
    }

    public abstract class ICreature : Entity,IActor
    {
        protected IStrategy _strategy;

        protected CreatureClass _class;
        protected Stats _baseStats;
        protected Stats _maxStats;
        protected God _god;
        protected ICreature _master;
        protected List<int> _composition = new List<int>() { };

        protected SkillPool _skills;
        protected Point2 _skillVector = new Point2(0,0);
        protected ComboMeter _combo;
        protected StatusPool _statuses = new StatusPool();

        protected Inventory _inventory;
        protected Equipment _equipment;

        protected HudManager _hudManager;
        protected readonly ActionTextHandler _damageText = new ActionTextHandler();

        protected int _playerIndex = -1;
        protected bool _isPlaying = true;
        protected int _currentLevel = 1;
        protected float _experience;
        protected const float __levelUpAmonut = 50;
        protected float _nextLevelExperience = __levelUpAmonut;

        protected int _actorType;

        private int SpriteFromCreature(int type)
        {
            switch (type)
            {
                case OgurActorType.MINION: return SpriteType.MINION;
                case OgurActorType.PLAYER:return SpriteType.PLAYER_STAND;
                case OgurActorType.ZORB:return SpriteType.ZORB;
                case OgurActorType.WRATH: return SpriteType.WRATH;
                case OgurActorType.HAND: return SpriteType.HAND;
                default:return SpriteType.CREATURE;
            }
        }

        protected void Setup(Point2 location, int type, Stats stats, CreatureClass creatureClass = null,bool setClass = true)
        {
            _entityType = OGUR.EntityType.ACTOR;
            Initialize(location, SpriteFromCreature(type), OGUR.EntityType.ACTOR,OGUR.ZDepth.Creature);
            Init(type,stats,creatureClass,setClass);
        }
        protected void SetClass(CreatureClass cClass)
        {
            if (_class != cClass || cClass == null || cClass == CreatureClass.NULL)
            {
                _class = cClass ?? CreatureClass.NULL;
                if (_skills == null)
                {
                    _skills = new SkillPool(this);
                }
                _skills.Add(_class.GetLevelSkills(_currentLevel));
                foreach (var elem in _composition)
                {
                    _skills.Add(SkillFactory.GetElementalSkill(elem));
                }
            }
        }

        private void Init(int type, Stats stats, CreatureClass creatureClass = null,bool setClass = true)
        {
            if (setClass)
            {
                SetClass(creatureClass);
            }
            _inventory = new Inventory(this);
            _equipment = new Equipment(this);
            _combo = new ComboMeter(this);
            if (_playerIndex > -1)
            {
                _hudManager = new HudManager(this,_inventory,_equipment);
            }
            _isBlocking = true;
            _actorType = type;
            _baseStats = new Stats(stats);
            _maxStats = new Stats(_baseStats);
        }

        public void PickupItem(GenericItem item)
        {
            _inventory.Add(item);
            EntityManager.RemoveObject(item);
        }

        public void Equip(GenericItem item)
        {
            if (_inventory.GetItemCount(item) > 0 && !_equipment.IsRegistered(item))
            {
                _equipment.Register(item);
                _inventory.Remove(item);
            }
            else
            {
                if (_equipment.IsRegistered(item))
                {
                    _equipment.Unregister(item);
                }
            }
        }

        public void Drop(GenericItem item)
        {
            if (item != null)
            {

                if (_inventory.GetItemCount(item) > 0)
                {
                    EntityManager.AddObject(new GenericItem(item, GetLocation()));
                    _inventory.Remove(item);
                }
                else
                {
                    if (_inventory.GetItemCount(item) == 0)
                    {
                        _equipment.Unregister(item);
                        EntityManager.AddObject(new GenericItem(item, GetLocation()));
                        _inventory.Remove(item);
                    }
                }
            }
        }

        public GenericItem DestroyRandomItemFromInventory()
        {
            var item = _inventory.GetNonZeroEntry();
            if (item != null)
            {
                _inventory.Remove(item);
            }
            return item;
        }

        public bool IsCooledDown()
        {
            return Get(StatType.MOVE_COOL_DOWN) >= GetMax(StatType.MOVE_COOL_DOWN);
        }

        public override void Update()
        {
            _statuses.Update();
            if (Get(StatType.HEALTH) <= 0)
            {
                _isActive = false;
            }
            if (_statuses.Allows(OAction.Movement))
            {
                if (_isPlaying)
                {
                    if (!IsCooledDown())
                    {
                        Adjust(StatType.MOVE_COOL_DOWN, 1);
                    }
                    Regenerate();
                }
                if (_strategy != null)
                {
                    _strategy.Act();
                    _combo.Update();
                }
            }
            if (_hudManager != null)
            {
                _hudManager.Update();
            }
            _damageText.Update();
        }
        private void Regenerate()
        {
            if (_statuses.Allows(OAction.Regeneration))
            {
                foreach (string stat in StatType.Values)
                {
                    if (stat != StatType.MOVE_COOL_DOWN && stat != StatType.REGEN)
                    {
                        if (_baseStats.GetRaw(stat) < _maxStats.GetRaw(stat))
                        {
                            Adjust(stat, _baseStats.Get(StatType.REGEN) / 50);
                        }
                    }
                }
            }
        }
        public override void Draw()
        {
            base.Draw();
            if (_hudManager != null)
            {
                _hudManager.Draw();                
            }
            _combo.Draw();
            _damageText.Draw();
        }

        public bool ToggleInventoryVisibility()
        {
            if (_hudManager != null)
            {
                return _hudManager.ToggleInventory();
            }
            return false;
        }

        public void SetPlaying(bool isPlaying)
        {
            _isPlaying = isPlaying;
        }

        public bool IsPlaying()
        {
            return _isPlaying;
        }

        public float Get(string stat)
        {
            return _baseStats.Get(stat)+CalculateEquipmentBonus(stat)+CalculateInstrinsicBonus(stat);
        }

        private float GetRaw(string stat,bool isMax=false)
        {
            return isMax ? _maxStats.GetRaw(stat) : _baseStats.GetRaw(stat);
        }

        private float CalculateInstrinsicBonus(string stat)
        {
            if (_class == null)
            {
                return 0;
            }
            return _class.GetBonus(_currentLevel, stat);
        }

        private float CalculateEquipmentBonus(string stat)
        {
            if (_equipment != null)
            {
                return _equipment.CalculateBonus(stat);
            }
            return 0;
        }

        public float GetMax(string stat)
        {
            return (int)_maxStats.Get(stat) + CalculateEquipmentBonus(stat) + CalculateInstrinsicBonus(stat);
        }

        public float Set(string stat,float value)
        {
            return _baseStats.Set(stat,value);
        }

        public float SetMax(string stat,float value)
        {
            return _maxStats.Set(stat,value);
        }

        public float Set(string stat,float value,bool setMax=false)
        {
            return setMax ? SetMax(stat, value) : Set(stat, value);
        }

        protected void InitStat(string stat, float value)
        {
            _maxStats.Set(stat, value);
            _baseStats.Set(stat, value);
        }

        public int GetPlayerIndex()
        {
            return _playerIndex;
        }

        public int GetActorType()
        {
            return _actorType;
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
                damage -= _baseStats.Get(StatType.DEFENSE);
            }
            if (damage <= 0 && statType==null)
            {
                damage = 0;                
            }
            if (showDamage)
            {
                _damageText.WriteAction(StringStorage.Get(damage), 30, IntStorage.Get(GetLocation().PosCenterX), IntStorage.Get(GetLocation().PosCenterY));
            }
            if(damage>0 && statType==null && _statuses.Allows(OAction.ReceiveHealing))
            {
                Adjust(statType??StatType.HEALTH, -damage);
            }
            if (Get(StatType.HEALTH) <= 0)
            {
                _isActive = false;
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
            _baseStats.AddBuff(buff);
        }

        protected float CalculateDamage()
        {
            return Get(StatType.STRENGTH);
        }

        private Point2 target = new Point2(0, 0);
        private List<ICreature> creatures;
        public void MoveIfPossible(float xVel, float yVel)
        {
            if (_statuses.Allows(OAction.Movement))
            {
                if ((xVel != 0 || yVel != 0) && IsCooledDown())
                {
                    target.Reset(xVel + GetLocation().PosX, yVel + GetLocation().PosY);
                    if (!IsBlocking() || !CoordVerifier.IsBlocked(target))
                    {
                        Move(xVel, yVel);
                        Set(StatType.MOVE_COOL_DOWN, 0);
                    }
                    else
                    {
                        if(_statuses.Allows(OAction.Attacking))
                        {
                            creatures = EntityManager.GetActorsAt(target).Select(a=>a as ICreature).ToList();
                            if (creatures.Count() > 0)
                            {
                                foreach (var creature in creatures)
                                {
                                    if (creature != this)
                                    {
                                        if ((creature.GetActorType() != OgurActorType.PLAYER && _actorType == OgurActorType.PLAYER)
                                            ||
                                            (creature.GetActorType() == OgurActorType.PLAYER && _actorType != OgurActorType.PLAYER)
                                            || !_statuses.Allows(OAction.WontHitNonTargets))
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
            if(null == _skillVector)
            {
                return null;
            }
            return _skillVector;
        }
        
        public void SetSkillVector(Point2 skillVector)
        {
            _skillVector.SetX(skillVector.X);
            _skillVector.SetY(skillVector.Y);
        }

        public void AddExperience(float amount)
        {
            
            while (amount > 0)
            {
                var diff = amount;
                if (amount > _nextLevelExperience)
                {
                    diff = _nextLevelExperience;
                    amount -= _nextLevelExperience;
                }
                else
                {
                    amount = 0;
                }
                _experience += diff;
                if (_experience > _nextLevelExperience)
                {
                    _nextLevelExperience += __levelUpAmonut;
                    _experience = 0;
                    _currentLevel++;
                    _skills.Add(_class.GetLevelSkills(_currentLevel));
                    TextManager.Add(new ActionText("LEVEL UP!", 100, (int)GetLocation().PosX, (int)GetLocation().PosY));
                }
            }
        }

        public float CalculateExperience()
        {
            return _currentLevel + _baseStats.GetSum();
        }

        public void CycleActiveSkill(int velocity)
        {
            if (_statuses.Allows(OAction.SkillCycle))
            {
                _skills.Cycle(velocity);
            }
        }

        public string GetActiveSkillName()
        {
            return _skills.GetActiveName();
        }

        float lastSum = 0;
        public void UseActiveSkill()
        {
            if (_statuses.Allows(OAction.SkillUsage))
            {
                lastSum = _baseStats.GetSum();
                _skills.UseActive();
                if (lastSum != _baseStats.GetSum())
                {
                    _damageText.WriteAction(GetActiveSkillName(), 40, IntStorage.Get(GetLocation().PosCenterX), IntStorage.Get(GetLocation().PosY));
                }
            }
        }

        public TargetSet GetTargets()
        {
            if (_strategy == null)
            {
                return _master.GetTargets();
            }
            return _strategy.GetTargets();
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
            Adjust(StatType.PIETY, sacrifice.Modifers.GetSum() * ((_god.IsGoodSacrifice(sacrifice.GetItemClass())) ? 3 : 1) * ((_god.IsBadSacrifice(sacrifice.GetItemClass())) ? -2 : 1), true);
            Adjust(StatType.PIETY,sacrifice.Modifers.GetSum() * ((_god.IsGoodSacrifice(sacrifice.GetItemClass())) ? 3 : 1) * ((_god.IsBadSacrifice(sacrifice.GetItemClass())) ? -2 : 1));
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
            if (_god != god && _god != null)
            {
                ApplyDamage(Get(StatType.PIETY));
                Set(StatType.PIETY, 0);
                SetClass(god.GetClass());
            }
            _god = god;
        }

        public void Combo(List<int> attack)
        {
            foreach(var element in attack)
            {
                _combo.Add(element);
            }
        }

        public void AddStatus(IStatus status)
        {
            _statuses.Add(status);
        }

        public void PassOn(ICreature target,StatusComponent componentType)
        {
            _statuses.PassOn(target, componentType);
        }


        public void SetStrategy(IStrategy strategy)
        {
            _strategy = strategy;
        }

        public Type GetStrategyType()
        {
            return _strategy.GetType();
        }

        public float GetInventoryCount()
        {
            return _inventory.NonZeroCount();
        }

        public void RemoveLeastUsedSkill()
        {
            _skills.RemoveLeastUsed();
        }

        public void React(string skillId)
        {
            if (_actorType == OgurActorType.PLAYER && skillId != SkillId.FORGET_SKILL && _god.NameText == GodId.Names[GodId.GLUTTONY])
            {
                if (_skills.Count() < _currentLevel)
                {
                    _skills.Add(skillId);
                }
            }
        }

        public void PerformInteraction()
        {
            SetInteracting(false);
            Input.Lock(Commands.Confirm, GetPlayerIndex());
        }

        public void MarkHotSkill(int hotSkillSlot)
        {
            _skills.MakeActiveSkillHot(hotSkillSlot);
        }

        public bool SetHotSkillActive(int hotkey)
        {
            return _skills.SetHotSkillsActive(hotkey);
        }

        internal object GetHotSkillName(int hotSkillSlot)
        {
            return _skills.GetHotSkillName(hotSkillSlot);
        }
    }
}