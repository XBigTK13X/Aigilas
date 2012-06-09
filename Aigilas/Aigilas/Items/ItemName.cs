using System.Collections.Generic;
namespace Agilas.Items
{
    public class ItemName
    {
        public const int NULL = 0;
        public const int Sword = 1;
        public const int Pants = 2;
        public const int Dagger = 3;
        public const int Shield = 4;
        public const int Bow = 5;
        public const int Arrow = 6;
        public const int Staff = 7;
        public const int Hood = 8;
        public const int Shirt = 9;
        public const int Flak = 10;

        public static readonly int[] Values =
        {
            NULL,
            Sword,
            Pants,
            Dagger,
            Shield,
            Bow,
            Arrow,
            Staff,
            Hood,
            Shirt,
            Flak
        };

        public static readonly Dictionary<int, string> Names = new Dictionary<int, string>()
        {
            {NULL,"NONE"},
            {Sword,"Sword"},
            {Pants,"Pants"},
            {Dagger,"Dagger"},
            {Shield,"Shield"},
            {Bow,"Bow"},
            {Arrow,"Arrow"},
            {Staff,"Staff"},
            {Hood,"Hood"},
            {Shirt,"Shirt"},
            {Flak,"Flak"}
        };
    }
}
