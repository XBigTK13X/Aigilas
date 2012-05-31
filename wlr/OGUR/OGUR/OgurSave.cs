namespace OGUR
{
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Text;
    using System.Runtime.Serialization;
    using OGUR.Dungeons;
    using SPX.Entities;

    [Serializable()]
    public class OgurSave: ISerializable
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

        public OgurSave(SerializationInfo info, StreamingContext context)
        {
            World = (Dictionary<int,DungeonSet>)info.GetValue("World", typeof(Dictionary<int,DungeonSet>));
            Cache = (List<Entity>)info.GetValue("Cache", typeof(List<Entity>));
            FloorCount = (int)info.GetValue("FloorCount", typeof(int));
        }

        public void GetObjectData(SerializationInfo info, StreamingContext context)
        {
            info.AddValue("World", World);
            info.AddValue("Cache", Cache);
            info.AddValue("FloorCount", FloorCount);
        }
    }
}
