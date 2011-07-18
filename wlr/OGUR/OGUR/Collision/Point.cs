namespace OGUR.Collision
{
    public class Point
    {
        public int X, Y,Weight;

        public Point(int x, int y,int weight = 0)
        {
            X = x;
            Y = y;
            Weight = weight;
        }
    }

    public class Point2
    {
        public float X, Y, Weight;

        public Point2(float x, float y, int weight = 0)
        {
            X = x;
            Y = y;
            Weight = weight;
        }

        public Point2 Multiply(float factor)
        {
            return new Point2(X*factor,Y*factor);
        }
    }
}