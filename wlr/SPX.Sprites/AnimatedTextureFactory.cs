namespace SPX.Sprites
{
    public class AnimatedTextureFactory
    {
        public static AnimatedTexture Create(int type, int x, int y)
        {
            var sprite = new AnimatedTexture();
            sprite.LoadContent(type);
            sprite.SetPosition(x, y);
            return sprite;
        }
    }
}