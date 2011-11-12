using System.Collections.Generic;
namespace OGUR.Items
{
    public class ItemPrefix
    {
        public const string NULL = "NULL";
        public const string Silver = "Silver";
        public const string Solid = "Solid";
        public const string Permuted = "Permuted";

        public static readonly List<string> Values = new List<string>()
        {
            NULL,
            Silver,
            Solid,
            Permuted
        };
    }
}
