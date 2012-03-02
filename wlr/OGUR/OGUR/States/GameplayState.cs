using SPX.States;
using SPX.Entities;

namespace OGUR.States
{
    public class GameplayState : State
    {
        public GameplayState()
        {
            EntityManager.Reset();
        }
        public void Update()
        {
            EntityManager.Update();
        }
        public void LoadContent()
        {
            EntityManager.LoadContent();
        }
        public void Draw()
        {
            EntityManager.Draw();
        }
    }
}