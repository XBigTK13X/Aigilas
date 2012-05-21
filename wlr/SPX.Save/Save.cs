using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Storage;

//http://stackoverflow.com/questions/3723287/good-example-of-xna-4-0-to-save-game-data

namespace SPX.Saves
{
    public static class Save<T>
    {
        public static void Write(string fileName,T serializableSaveObject)
        {

        }

        public static T Read(string fileName)
        {
            return (T)new Object();
        }
    }
}
