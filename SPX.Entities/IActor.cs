using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using SPX.Core;

namespace SPX.Entities
{
    public interface IActor:IEntity
    {
        int GetActorType();
        void PerformInteraction();
    }
}