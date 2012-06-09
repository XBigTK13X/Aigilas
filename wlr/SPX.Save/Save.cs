using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Storage;
using System.IO;
using System.Xml.Serialization;
using System.Diagnostics;
using Microsoft.Xna.Framework;
using System.Runtime.Serialization.Formatters.Binary;
using System.Runtime.Serialization;

// http://stackoverflow.com/questions/3723287/good-example-of-xna-4-0-to-save-game-data

namespace SPX.Saves
{
    public static class Save<T>
    {
        private enum SavingState
        {
            NotSaving,
            ReadyToSelectStorageDevice,
            SelectingStorageDevice,
            ReadyToOpenStorageContainer,
            OpeningStorageContainer,
            ReadyToSave
        }

        private static SavingState savingState = SavingState.NotSaving;
        private static IAsyncResult asyncResult;
        private static StorageDevice storageDevice;
        private static StorageContainer storageContainer;
        private static string fileName = @"./ogur.sav";
        private static T gameData;

        public static T Read()
        {
            T target;
            using (Stream stream = File.Open(fileName, FileMode.Open))
            {
                BinaryFormatter formatter = new BinaryFormatter();
                target = (T)formatter.Deserialize(stream);
            }
            return target;
        }

        private static void DeleteExisting()
        {
            if (storageContainer.FileExists(fileName))
            {
                storageContainer.DeleteFile(fileName);
            }
        }

        private static void Write()
        {
            if (gameData != null)
            {
                using (Stream stream = File.Create(fileName))
                {
                    BinaryFormatter formatter = new BinaryFormatter();
                    formatter.Serialize(stream, gameData);
                }
            }
        }

        public static bool AnyExist()
        {
            return File.Exists(fileName);
        }

        public static void Init(T saveGameData)
        {
            if (savingState == SavingState.NotSaving)
            {
                gameData = saveGameData;
                savingState = SavingState.ReadyToOpenStorageContainer;
            }
        }

        public static void Update()
        {
            switch (savingState)
            {
                case SavingState.ReadyToSelectStorageDevice:
#if XBOX
                    if (!Guide.IsVisible)
#endif
                    {
                        asyncResult = StorageDevice.BeginShowSelector(PlayerIndex.One, null, null);
                        savingState = SavingState.SelectingStorageDevice;
                    }
                    break;

                case SavingState.SelectingStorageDevice:
                    if (asyncResult.IsCompleted)
                    {
                        storageDevice = StorageDevice.EndShowSelector(asyncResult);
                        savingState = SavingState.ReadyToOpenStorageContainer;
                    }
                    break;

                case SavingState.ReadyToOpenStorageContainer:
                    if (storageDevice == null || !storageDevice.IsConnected)
                    {
                        savingState = SavingState.ReadyToSelectStorageDevice;
                    }
                    else
                    {
                        asyncResult = storageDevice.BeginOpenContainer("Game1StorageContainer", null, null);
                        savingState = SavingState.OpeningStorageContainer;
                    }
                    break;

                case SavingState.OpeningStorageContainer:
                    if (asyncResult.IsCompleted)
                    {
                        storageContainer = storageDevice.EndOpenContainer(asyncResult);
                        savingState = SavingState.ReadyToSave;
                    }
                    break;

                case SavingState.ReadyToSave:
                    if (storageContainer == null)
                    {
                        savingState = SavingState.ReadyToOpenStorageContainer;
                    }
                    else
                    {
                        try
                        {
                            DeleteExisting();
                            Write();
                        }
                        catch (IOException e)
                        {
                            Debug.WriteLine(e.Message);
                        }
                        finally
                        {
                            storageContainer.Dispose();
                            storageContainer = null;
                            savingState = SavingState.NotSaving;
                        }
                    }
                    break;
                case SavingState.NotSaving:
                    break;
            }
        }
    }
}
