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
    [Serializable]
    public class IActor:Entity
    {
        public virtual int GetActorType() { return Int32.MinValue; }
        public virtual void PerformInteraction() { }
    }
}