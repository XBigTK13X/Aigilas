package com.aigilas;import com.xna.wrapper.*;import java.util.*;import com.spx.core.*;import com.spx.io.*;
    public class EntryPoint
    {
        public static void main(String[] args)
        {
            Thread server = new Thread(new ServerThread());
            server.run();
            try {				Thread.sleep(100);			} catch (InterruptedException e) {				// TODO Auto-generated catch block				e.printStackTrace();			}            Game game = new Game();            game.Initialize();            while(game.IsRunning())            {
            	game.Update();                          }
            Server.Get().Close();
            Client.Get().Close();
            Console.WriteLine("Finished being a client");            Environment.Exit();
        }
    }
