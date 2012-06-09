using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SPX.States
{
    public interface State
    {
        void Draw();
        void Update();
        void LoadContent();
    }
}