package spx.particles;import spx.bridge.DrawDepth;import spx.core.Point2;import spx.core.RNG;import spx.core.SpxManager;import spx.entities.IEntity;import spx.util.MathHelper;import com.badlogic.gdx.graphics.Color;import com.badlogic.gdx.graphics.g2d.Sprite;public class Particle2 extends PEComponent {	public static final int DefaultLife = 100;	public float Height = 1;	public float Width = 1;	private float _life = DefaultLife;	private final Sprite _texture = SpxManager.GetParticleAsset();	private Color _color = Color.WHITE;	public float MoveSpeed = 5f;	public IEntity Entity;	public Point2 Position = new Point2(0, 0);	public Point2 Origin = new Point2(0, 0);	public double Angle;	public float Radius;	public boolean Toggle;	private ParticleBehavior _behavior;	public Particle2(Point2 position) {	}	public void Draw() {		if (IsActive) {			SpxManager.Renderer.Draw(_texture, Position, DrawDepth.Particle, _color, Width, Height);		}	}	public void Reset(ParticleBehavior behavior, Point2 position, Color baseColor) {		Init(behavior, position, null, baseColor);	}	public void Reset(ParticleBehavior behavior, IEntity entity, Color baseColor) {		Init(behavior, null, entity, baseColor);	}	private void Init(ParticleBehavior behavior, Point2 position, IEntity entity, Color baseColor) {		_behavior = behavior;		if (position != null) {			Origin.Reset(position.X, position.Y);			Position.Reset(position.X, position.Y);		}		if (entity != null) {			Entity = entity;			Origin.Reset(Entity.GetLocation().PosX, Entity.GetLocation().PosY);			Position.Reset(Entity.GetLocation().PosX, Entity.GetLocation().PosY);		}		if (baseColor != null) {			_color = Darken(baseColor, (RNG.Next(10, 50)) / 100f);		}		else {			_color = new Color(RNG.Next(60, 190) / 255f, RNG.Next(60, 190) / 255f, RNG.Next(60, 190) / 255f, 1f);		}		Angle = RNG.Angle();		IsActive = true;		Radius = 0;		SetMoveSpeed();	}	private void SetMoveSpeed() {		MoveSpeed = 15f - (10f * ((Height + Width) / 30f));	}	private static Color Darken(Color inColor, float inAmount) {		return new Color(MathHelper.Clamp((inColor.r) * 255 - 255 * inAmount, 0, 255), MathHelper.Clamp((inColor.g) * 255 - 255 * inAmount, 0, 255), MathHelper.Clamp((inColor.b) * 255 - 255 * inAmount, 0, 255), 255);	}	public void SetSize(float height, float width) {		Height = height;		Width = width;		SetMoveSpeed();	}	public void Update() {		_life *= .85f;		_color.a *= .999f;		if ((_life <= .001 && Entity == null) || (Entity != null && !Entity.IsActive())) {			IsActive = false;		}		_behavior.Update(this);	}}