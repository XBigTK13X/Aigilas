package com.spx.core;import com.xna.wrapper.*;import java.io.BufferedReader;import java.io.DataInputStream;import java.io.FileInputStream;import java.io.InputStreamReader;import java.util.*;
    public class Settings
    {
        private static final String __configPath = "settings.cfg";

        private static Settings __instance;
        public static Settings Get()
        {
            if (__instance == null)
            {
                __instance = new Settings();
            }
            return __instance;
        }

        private HashMap<String, String> _values = new HashMap<String, String>();
        private int _port;
        private String _serverIp;
        private boolean _clientVerbose;
        private boolean _serverVerbose;
        private boolean _messageContentsVerbose;
        private Settings()
        {        	  try{        		  FileInputStream fstream = new FileInputStream(__configPath);        		  DataInputStream in = new DataInputStream(fstream);        		  BufferedReader br = new BufferedReader(new InputStreamReader(in));        		  String line;        		  while ((line = br.readLine()) != null)   {        			  String key = line.split("=")[0];                      String value = line.split("=")[1];                      _values.put(key,value);                      switch(key)                      {                          case "server_ip" :                               _serverIp = value;                              break;                          case "socket_port":                               _port = Integer.parseInt(value);                              break;                          case "server_log_verbose":                               _serverVerbose = IsTrue(value);                              break;                          case "client_log_verbose":                               _clientVerbose = IsTrue(value);                              break;                          case "message_contents_log_verbose":                               _messageContentsVerbose = IsTrue(value);                              break;                          default:                               break;                      }        		  }        		  in.close();        		    }catch (Exception e){        		  System.err.println("Error: " + e.getMessage());        		  }
        }

        private boolean IsTrue(String value)
        {
            return value.equalsIgnoreCase("true");
        }

        public String Get(String key)
        {
            return _values.get(key);
        }

        public String GetIp()
        {
            return _serverIp;
        }

        public int GetPort()
        {
            return _port;
        }

        public boolean GetServerVerbose()
        {
            return _serverVerbose;
        }

        public boolean GetClientVerbose()
        {
            return _clientVerbose;
        }

        public boolean GetMessageContentsVerbose()
        {
            return _messageContentsVerbose;
        }
    }
