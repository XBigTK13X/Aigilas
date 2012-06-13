using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SPX.Core;

namespace SPX.Particles
{
    class Emitter: PEComponent
    {
        private Particle2[] _particles = new Particle2[100];
        private int _index = 0;
        ParticleBehavior _behavior;

        public Emitter(){}

        public void Update()
        {
            if (IsActive)
            {
                IsActive = false;
                for (int ii = 0; ii < _index; ii++)
                {
                    if (_particles[ii].IsActive)
                    {
                        IsActive = true;
                        _particles[ii].Update();
                    }
                }
            }
        }

        public void Draw()
        {
            if (IsActive)
            {
                for (int ii = 0; ii < _index; ii++)
                {
                    _particles[ii].Draw();
                }
            }
        }

        public void Reset(ParticleBehavior behavior, Point2 position)
        {
            IsActive = true;
            _behavior = behavior;
            _index = 0;
            while (_index < behavior.GetParticleCount())
            {
                _particles[_index] = ParticleEngine.CreateParticle(behavior,position);
                _index++;
            }
        }
    }
}