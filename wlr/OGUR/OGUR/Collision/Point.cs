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
        public double X, Y, Weight;

        public Point2(double x, double y, int weight = 0)
        {
            X = x;
            Y = y;
            Weight = weight;
        }
    }
}