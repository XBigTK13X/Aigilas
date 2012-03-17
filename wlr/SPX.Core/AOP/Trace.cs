using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using PostSharp.Aspects;
using System.Diagnostics;
using System.IO;
using System.Security.Cryptography;

namespace SPX
{
    /// <summary>
    /// Aspect that, when applied on a method, emits a trace message before and
    /// after the method execution.
    /// </summary>
    [Serializable]
    public class TraceAttribute : OnMethodBoundaryAspect
    {
        private static TextWriterTraceListener Writer;

        /// <summary>
        /// Method invoked after failure of the method to which the current
        /// aspect is applied.
        /// </summary>
        /// <param name="args">Information about the method being executed.</param>
        public override void OnException(MethodExecutionArgs args)
        {
            try
            {
                var log = String.Format("\nException: {0}\nMethod: {1}\nMessage: {2}\nStack Trace: \n{3}",
                                    args.Method.DeclaringType.FullName,
                                    args.Method.Name,
                                    args.Exception.Message,
                                    args.Exception.StackTrace);
                // step 1, calculate MD5 hash from input
                MD5 md5 = System.Security.Cryptography.MD5.Create();
                byte[] inputBytes = System.Text.Encoding.ASCII.GetBytes(log);
                byte[] hash = md5.ComputeHash(inputBytes);

                // step 2, convert byte array to hex string
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < hash.Length; i++)
                {
                    sb.Append(hash[i].ToString("X2"));
                }
                var hashString = sb.ToString();

                var path = @"c:\Users\kretst\Desktop\uniquelog\" + hashString + ".log";
                if (!File.Exists(path))
                {
                    if (Writer == null)
                    {
                        Writer = new TextWriterTraceListener(path);
                        Trace.Listeners.Clear();
                        Trace.Listeners.Add(Writer);
                    }

                    Trace.TraceInformation(log);
                    Trace.Flush();
                }
            }
            catch (Exception swallow){}
            Process ogur = new Process();
            ogur.StartInfo.FileName = @"C:\z\dev\git\ogur\wlr\OGUR\OGUR\bin\x86\Debug\OGUR.exe";
            ogur.Start();
            Environment.Exit(0);
        }
    }
}
