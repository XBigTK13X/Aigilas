using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SPX.Core;

namespace SPX.Entities
{
    public interface IEntity
    {
        bool Contains(Point2 target);
        void Draw();
        void Update();
        void LoadContent();
        Point2 GetLocation();
        int EntityType();
        bool IsActive();
        bool IsBlocking();
        void SetInactive();
    }
}
