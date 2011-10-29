using System;
//From Eric Lippert's blog: http://blogs.msdn.com/ericlippert/archive/2007/10/08/path-finding-using-a-in-c-3-0-part-three.aspx
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OGUR.Path
{
    using System.Collections.Generic;
    using System.Linq;
    
    class PriorityQueue<P, V>
    {
        private Dictionary<P, Queue<V>> list = new Dictionary<P, Queue<V>>();
        public void Enqueue(P priority, V value)
        {
            Queue<V> q;
            if (!list.TryGetValue(priority, out q))
            {
                q = new Queue<V>();
                list.Add(priority, q);
            }
            q.Enqueue(value);
        }
        public V Dequeue()
        {
            // will throw if there isn’t any first element!
            var pair = list.First();
            var v = pair.Value.Dequeue();
            if (pair.Value.Count == 0) // nothing left of the top priority.
                list.Remove(pair.Key);
            return v;
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