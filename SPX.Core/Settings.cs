using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace SPX.Core
{
    public class Settings
    {
        private const string __configPath = "settings.cfg";

        private static Settings __instance;
        public static Settings Get()
        {
            if (__instance == null)
            {
                __instance = new Settings();
            }
            return __instance;
        }

        private Dictionary<string, string> _values = new Dictionary<string, string>();
        private int _port;
        private string _serverIp;
        private bool _clientVerbose;
        private bool _serverVerbose;
        private bool _messageContentsVerbose;
        private Settings()
        {
            foreach (var line in File.ReadAllLines(__configPath))
            {
                string key = line.Split('=')[0];
                string value = line.Split('=')[1];
                _values.Add(key,value);
                switch(key)
                {
                    case "server_ip":
                        _serverIp = value;
                        break;
                    case "socket_port":
                        _port = Int32.Parse(value);
                        break;
                    case "server_log_verbose":
                        _serverVerbose = IsTrue(value);
                        break;
                    case "client_log_verbose":
                        _clientVerbose = IsTrue(value);
                        break;
                    case "message_contents_log_verbose":
                        _messageContentsVerbose = IsTrue(value);
                        break;
                    default:
                        break;
                }
            }
        }

        private bool IsTrue(string value)
        {
            return String.Compare(value, "true", true) == 0;
        }

        public string Get(string key)
        {
            return _values[key];
        }

        public string GetIp()
        {
            return _serverIp;
        }

        public int GetPort()
        {
            return _port;
        }

        public bool GetServerVerbose()
        {
            return _serverVerbose;
        }

        public bool GetClientVerbose()
        {
            return _clientVerbose;
        }

        public bool GetMessageContentsVerbose()
        {
            return _messageContentsVerbose;
        }
    }
}
