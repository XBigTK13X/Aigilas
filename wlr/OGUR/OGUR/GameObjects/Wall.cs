using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Sprites;
using OGUR.Management;
using OGUR.Creatures;

namespace OGUR.GameObjects
{
    class Wall:GameplayObject
    {
        public Wall(int x, int y)
        {
            Initialize(x, y, SpriteType.WALL, GameObjectType.WALL);
        }
        public override void Update()
        {
            foreach (ICreature player in GameplayObjectManager.GetObjects(CreatureType.PLAYER))
            {
                if (Collision.HitTest.IsTouching(player, this))
                {
                    int xVel = ((InputManager.IsPressed(InputManager.Commands.MoveLeft, player.GetPlayerIndex())) ? -player.GetInt(Stat.MOVE_SPEED) : 0) + ((InputManager.IsPressed(InputManager.Commands.MoveRight, player.GetPlayerIndex())) ? player.GetInt(Stat.MOVE_SPEED) : 0);
                    int yVel = ((InputManager.IsPressed(InputManager.Commands.MoveDown, player.GetPlayerIndex())) ? player.GetInt(Stat.MOVE_SPEED) : 0) + ((InputManager.IsPressed(InputManager.Commands.MoveUp, player.GetPlayerIndex())) ? -player.GetInt(Stat.MOVE_SPEED) : 0);
                    xVel *= -1;
                    yVel *= -1;
                    player.Move(xVel, yVel);
                }
            }
        }
    }
}
