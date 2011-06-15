using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.GameObjects;
using OGUR.Sprites;

namespace OGUR.Factory
{
    internal class GameplayObjectFactory
    {
        public static GameplayObject Create(GameObjectType type, int x, int y)
        {
            switch (type)
            {
                case GameObjectType.FLOOR:
                    return new Floor(x, y);
                case GameObjectType.SPIKE:
                    return new Spike(x, y);
                case GameObjectType.WALL:
                    return new Wall(x, y);
                case GameObjectType.DOWNSTAIRS:
                    return new Downstairs(x, y);
                case GameObjectType.UPSTAIRS:
                    return new Upstairs(x, y);
                default:
                    throw new Exception("An undefined GameObjectType case was passed into the GameplayObjectFactory.");
            }
        }
    }
}