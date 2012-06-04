namespace OGUR
{
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Text;
    using OGUR.Dungeons;
    using SPX.Entities;

    [Serializable]
    public class OgurSave
    {
        public Dictionary<int,DungeonSet> World = new Dictionary<int, DungeonSet>();
        public List<Entity> Cache = new List<Entity>();
        public int FloorCount;

        public OgurSave(Dictionary<int,DungeonSet> world, List<Entity> cache,int floorCount)
        {
            World = world;
            Cache = cache;
            FloorCount = floorCount;
        }
    }
}
