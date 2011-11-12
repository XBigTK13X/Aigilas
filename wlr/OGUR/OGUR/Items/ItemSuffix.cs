using System.Collections.Generic;
namespace OGUR.Items
{
    public class ItemSuffix
    {
        public const string NULL = "NULL";
        public const string Blight = "Blight";
        public const string Confusion = "Confusion";
        public const string Tragedy = "Tragedy";
        public const string Comedy = "Comedy";

        public static readonly List<string> Values = new List<string>()
        {
            NULL,
            Blight,
            Confusion,
            Tragedy,
            Comedy
        };
    }
}
