using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using welikerogues.Sprites;
using welikerogues.Management;

namespace welikerogues.GameObjects
{
    class Wall:GameplayObject
    {
        public Wall(int x, int y)
        {
            Initialize(x, y, SpriteType.WALL, GameObjectType.WALL);
        }
        public override void Update()
        {
            foreach (Player player in GameplayObjectManager.GetObjects(GameObjectType.PLAYER))
            {
                if (Collision.HitTest.IsTouching(player, this))
                {
                    int xVel = ((InputManager.IsPressed(InputManager.Commands.MoveLeft, player.GetIndex())) ? -player.GetMoveSpeed() : 0) + ((InputManager.IsPressed(InputManager.Commands.MoveRight, player.GetIndex())) ? player.GetMoveSpeed() : 0);
                    int yVel = ((InputManager.IsPressed(InputManager.Commands.MoveDown, player.GetIndex())) ? player.GetMoveSpeed() : 0) + ((InputManager.IsPressed(InputManager.Commands.MoveUp, player.GetIndex())) ? -player.GetMoveSpeed() : 0);
                    xVel *= -1;
                    yVel *= -1;
                    player.Move(xVel, yVel);
                }
            }
        }
    }
}
