using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SPX.States
{
    public class State
    {
        public virtual void Draw() { }
        public virtual void Update() { }
        public virtual void LoadContent() { }
    }
}