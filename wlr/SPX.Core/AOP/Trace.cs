using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using PostSharp.Aspects;
using System.Diagnostics;
using System.IO;

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
            if (Writer == null)
            {
                Writer = new TextWriterTraceListener(@"c:\Users\kretst\Desktop\log\"+DateTime.Now.ToString("ddMMyyyyHHmmss")+"_"+Guid.NewGuid().ToString()+".log");
                Trace.Listeners.Clear();
                Trace.Listeners.Add(Writer);
                Trace.TraceInformation("START: " + DateTime.Now.ToString());
            }
            Trace.TraceInformation("Exception: \n{0}\n Method: \n{1}\n Message: \n\n{2}\nStack Trace: {3}",
                                    args.Method.DeclaringType.FullName, 
                                    args.Method.Name,
                                    args.Exception.Message,
                                    args.Exception.StackTrace);
            Trace.Flush();
            Process notePad = new Process();
            notePad.StartInfo.FileName = @"C:\z\dev\git\ogur\wlr\OGUR\OGUR\bin\x86\Debug\OGUR.exe";
            notePad.Start();
            Environment.Exit(0);
        }
    }
}
