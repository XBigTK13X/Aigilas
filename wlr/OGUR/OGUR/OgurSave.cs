namespace OGUR
{
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Text;
    using System.Runtime.Serialization;

    [Serializable()]
    public class OgurSave: ISerializable
    {
        public string Example;
        public OgurSave()
        {
            Example = "TEST";
        }

        public OgurSave(SerializationInfo info, StreamingContext context)
        {
            Example = (string)info.GetValue("Example", typeof(string));
        }

        public void GetObjectData(SerializationInfo info, StreamingContext context)
        {
            info.AddValue("Example", Example);
        }
    }
}
