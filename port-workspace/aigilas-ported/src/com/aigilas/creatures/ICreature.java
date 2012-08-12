package com.aigilas.creatures;import com.xna.wrapper.*;import java.util.*;import com.aigilas.classes.*;import com.aigilas.gods.*;import com.aigilas.hud.*;import com.aigilas.items.*;import com.aigilas.reactions.*;import com.aigilas.skills.*;import com.aigilas.statuses.*;import com.aigilas.strategies.*;import com.spx.core.*;import com.spx.entities.*;import com.spx.sprites.*;import com.spx.text.*;import com.spx.util.*;import com.aigilas.management.*;import com.aigilas.entities.*;import com.spx.io.*;import com.spx.devtools.*;
    public abstract class ICreature  extends  Entity implements IActor
    {
        protected IStrategy _strategy;

        protected CreatureClass _class;
        protected Stats _baseStats;
        protected Stats _maxStats;
        protected God _god;
        protected ICreature _master;
        protected List<Integer> _composition = new ArrayList<Integer>() { };

        protected SkillPool _skills;
        protected Point2 _skillVector = new Point2(0,0);
        protected ComboMeter _combo;
        protected StatusPool _statuses = new StatusPool();

        protected Inventory _inventory;
        protected Equipment _equipment;

        protected HudManager _hudManager;
        protected ActionTextHandler _damageText = new ActionTextHandler();

        protected int _playerIndex = -1;
        protected boolean _isPlaying = true;
        protected int _currentLevel = 1;
        protected float _experience;
        protected static final float __levelUpAmonut = 50;
        protected float _nextLevelExperience = __levelUpAmonut;

        protected int _actorType;

        private int SpriteFromCreature(int type)
        {
            switch (type)
            {
                case AigilasActorType.MINION: return SpriteType.MINION;
                case AigilasActorType.PLAYER:return SpriteType.PLAYER_STAND;
                case AigilasActorType.ZORB:return SpriteType.ZORB;
                case AigilasActorType.WRATH: return SpriteType.WRATH;
                case AigilasActorType.HAND: return SpriteType.HAND;
                case AigilasActorType.ENVY: return SpriteType.ENVY;
                case AigilasActorType.PRIDE: return SpriteType.PRIDE;
                case AigilasActorType.SLOTH: return SpriteType.SLOTH;
                case AigilasActorType.GREED: return SpriteType.GREED;
                case AigilasActorType.GLUTTONY: return SpriteType.GLUTTONY;
                case AigilasActorType.LUST: return SpriteType.LUST;
                case AigilasActorType.BREAKING_WHEEL: return SpriteType.WHEEL;
                case AigilasActorType.SERPENT: return SpriteType.SLOTH;
                default:return SpriteType.CREATURE;
            }
        }

        protected void Setup(Point2 location, int type, Stats stats, CreatureClass creatureClass,boolean setClass)
        {
            _entityType = com.aigilas.EntityType.ACTOR;
            Initialize(location, SpriteFromCreature(type), com.aigilas.EntityType.ACTOR,com.aigilas.Depth.Creature);
            Init(type,stats,creatureClass,setClass);
        }        protected void Setup(Point2 location, int type, Stats stats, CreatureClass creatureClass)        {            Setup(location,type,stats,creatureClass,true);        }        protected void Setup(Point2 location, int type, Stats stats)        {            Setup(location,type,stats,null,true);        }
        protected void SetClass(CreatureClass cClass)
        {
            if (_class != cClass || cClass == null || cClass == CreatureClass.NULL)
            {
                _class = (cClass==null)?CreatureClass.NULL:cClass;
                if (_skills == null)
                {
                    _skills = new SkillPool(this);
                }
                for(String skillId:SkillFactory.GetElementalSkills(GetActorType(),_composition))
                {
                    _skills.add(skillId);
                }
                _skills.add(_class.GetLevelSkills(_currentLevel));                
            }
        }

        private void Init(int type, Stats stats, CreatureClass creatureClass,boolean setClass)
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
            if(_isBlocking == null)
                _isBlocking = true;
            _actorType = type;
            _baseStats = new Stats(stats);
            _maxStats = new Stats(_baseStats);
        }
        private void Init(int type, Stats stats, CreatureClass creatureClass)        {            Init(type,stats,creatureClass,true);        }                private void Init(int type, Stats stats)        {        	Init(type,stats,null,true);        }        
        public void PickupItem(GenericItem item)
        {
            _inventory.add(item);
            EntityManager.removeObject(item);
        }

        public void Equip(GenericItem item)
        {
            if (_inventory.GetItemCount(item) > 0 && !_equipment.IsRegistered(item))
            {
                _equipment.Register(item);
                _inventory.remove(item);
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
                    EntityManager.addObject(new GenericItem(item, GetLocation()));
                    _inventory.remove(item);
                }
                else
                {
                    if (_inventory.GetItemCount(item) == 0)
                    {
                        _equipment.Unregister(item);
                        EntityManager.addObject(new GenericItem(item, GetLocation()));
                        _inventory.remove(item);
                    }
                }
            }
        }

        public GenericItem DestroyRandomItemFromInventory()
        {
            var item = _inventory.GetNonZeroEntry();
            if (item != null)
            {
                _inventory.remove(item);
            }
            return item;
        }

        public boolean IsCooledDown()
        {
            return Get(StatType.MOVE_COOL_DOWN) >= GetMax(StatType.MOVE_COOL_DOWN);
        }

@Override        public  void Update()
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
                    else
                    {
                        _statuses.Act();
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
                for (String stat:StatType.Values)
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
@Override        public  void Draw()
        {
            super.Draw();
            if (_hudManager != null)
            {
                _hudManager.Draw();                
            }       
            _combo.Draw();
            _damageText.Draw();
        }

        public void Write(String text)
        {
            _damageText.WriteAction(text, 30, IntStorage.Get(GetLocation().PosCenterX), IntStorage.Get(GetLocation().PosCenterY));
        }

        public boolean ToggleInventoryVisibility()
        {
            if (_hudManager != null)
            {
                return _hudManager.ToggleInventory();
            }
            return false;
        }

        public void SetPlaying(boolean isPlaying)
        {
            _isPlaying = isPlaying;
        }

        public boolean IsPlaying()
        {
            return _isPlaying;
        }

        public float Get(String stat)
        {
            return _baseStats.Get(stat)+CalculateEquipmentBonus(stat)+CalculateInstrinsicBonus(stat);
        }

        private float GetRaw(String stat,boolean isMax=false)
        {
            return isMax ? _maxStats.GetRaw(stat) : _baseStats.GetRaw(stat);
        }

        private float CalculateInstrinsicBonus(String stat)
        {
            if (_class == null)
            {
                return 0;
            }
            return _class.GetBonus(_currentLevel, stat);
        }

        private float CalculateEquipmentBonus(String stat)
        {
            if (_equipment != null)
            {
                return _equipment.CalculateBonus(stat);
            }
            return 0;
        }

        public float GetMax(String stat)
        {
            return (int)_maxStats.Get(stat) + CalculateEquipmentBonus(stat) + CalculateInstrinsicBonus(stat);
        }

        public float Set(String stat,float value)
        {
            return _baseStats.Set(stat,value);
        }

        public float SetMax(String stat,float value)
        {
            return _maxStats.Set(stat,value);
        }

        public float Set(String stat,float value,boolean setMax=false)
        {
            return setMax ? SetMax(stat, value) : Set(stat, value);
        }

        protected void InitStat(String stat, float value)
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

        protected float Adjust(String stat, float adjustment,boolean adjustMax = false)
        {
            var result = GetRaw(stat) + adjustment;
            if (!adjustMax)
            {
                if (result > GetMax(stat))
                {
                    result = GetMax(stat);
                }
            }
            return Set(stat, (result),adjustMax);
        }

        public void ApplyDamage(float damage,ICreature attacker=null,boolean showDamage = true,String statType = null)
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
                    attacker.addExperience(CalculateExperience());
                    attacker.PassOn(attacker, StatusComponent.KillReward);
                }
            }
        }

        public boolean LowerStat(String stat, float amount)
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

        public void AddBuff(StatBuff buff,boolean applyToMax = false)
        {
            if (!applyToMax)
            {
                _baseStats.addBuff(buff);
            }
            else
            {
                _maxStats.addBuff(buff);
            }
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
                    if(_statuses.Allows(OAction.Attacking))
                    {
                        creatures = EntityManager.GetActorsAt(target).Select(a=>a as ICreature).ToList();
                        if (creatures.length > 0)
                        {
                            for (var creature:creatures)
                            {
                                if (creature != this)
                                {
                                    if ((creature.GetActorType() != AigilasActorType.PLAYER && _actorType == AigilasActorType.PLAYER)
                                        ||
                                        (creature.GetActorType() == AigilasActorType.PLAYER && _actorType != AigilasActorType.PLAYER)
                                        || 
                                        !_statuses.Allows(OAction.WontHitNonTargets))
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

        public void MoveTo(Point2 targetPosition)
;;        {
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
                    if (_class != null)
                    {
                        _skills.add(_class.GetLevelSkills(_currentLevel));
                    }
                    TextManager.add(new ActionText("LEVEL UP!", 100, (int)GetLocation().PosX, (int)GetLocation().PosY));
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

        public String GetActiveSkillName()
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
;;        {
            if (_strategy == null)
            {
                return _master.GetTargets();
            }
            return _strategy.GetTargets();
        }

        

        private String GetLowestStat()
        {
            var result = StatType.AGE;
            var min = float.MaxValue;
            for (var stat:StatType.Values.Where(stat => Get(stat)<min && stat != StatType.AGE && stat!= StatType.MOVE_COOL_DOWN && stat != StatType.PIETY))
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
            static final int pietyCost = 500;
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

        public void Combo(List<Integer> attack)
        {
            for(int element:attack)
            {
                _combo.add(element);
            }
        }

        public void AddStatus(IStatus status)
        {
            _statuses.add(status);
        }

        public void PassOn(ICreature target,StatusComponent componentType)
;;        {
            _statuses.PassOn(target, componentType);
        }


        public void SetStrategy(IStrategy strategy)
        {
            _strategy = strategy;
        }

        public int GetStrategyId()
        {
            return _strategy.GetId();
        }

        public float GetInventoryCount()
        {
            return _inventory.NonZeroCount();
        }

        public void RemoveLeastUsedSkill()
        {
            _skills.removeLeastUsed();
        }

        public void React(String skillId)
        {
            if (_actorType == AigilasActorType.PLAYER && skillId != SkillId.FORGET_SKILL && _god.NameText == GodId.Names[GodId.GLUTTONY])
            {
                if (_skills.length < _currentLevel)
                {
                    _skills.add(skillId);
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

        public boolean SetHotSkillActive(int hotkey)
        {
            return _skills.SetHotSkillsActive(hotkey);
        }

        internal object GetHotSkillName(int hotSkillSlot)
        {
            return _skills.GetHotSkillName(hotSkillSlot);
        }

        public List<Integer> GetTargetActorTypes()
;;        {
            return _strategy.GetTargetActorTypes();
        }

        public float GetCurrentSkillCost()
        {
            return _skills.GetCurrentCost();
        }
    }
