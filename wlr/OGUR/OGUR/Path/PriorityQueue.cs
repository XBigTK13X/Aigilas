using System;
//From Eric Lippert's blog: http://blogs.msdn.com/ericlippert/archive/2007/10/08/path-finding-using-a-in-c-3-0-part-three.aspx
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OGUR.Path
{
    using System.Collections.Generic;
    using System.Linq;
    
    class PriorityQueue
    {
        private Dictionary<double, Queue<Path>> list = new Dictionary<double, Queue<Path>>();


        public void Enqueue(double priority, Path value)
        {
            Queue<Path> q = QueueFactory.Create();
            if (!list.ContainsValue(q))
            {
                list.Add(priority, q);
            }
            q.Enqueue(value);
        }
        private double tempKey;
        private Queue<Path> temp;
        private Path result;
        public Path Dequeue()
        {
            foreach (var key in list.Keys)
            {
                tempKey = key;
                break;
            }
            temp = list[tempKey];
            result = temp.Dequeue();
            if (temp.Count() == 0)
            {
                list.Remove(tempKey);
            }
            return result;
        }
        public bool IsEmpty
        {
            get { return list.Keys.Count()==0; }
        }

        public void Clear()
        {
            list.Clear();
        }
    }
}