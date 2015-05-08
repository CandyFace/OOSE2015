package example;

import java.awt.Rectangle;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class ProjectileObj {// should hold angle and position and timer
	
	public Image projectileSprite;
	
	public Vector2f pos;
	private ProjectileObj nextObj;
	private ProjectileObj prevObj;
	// public
    public int timer;
	protected Vector2f position, displacement;
	protected float scale;
	protected float rotation;// rotation is always zero unless a or d is
								// pressed... thus it is an increment to the
								// sprite's rotation!
	// means that rotation is added to the sprites current rotation!
	// but in projectile class it is the absolute rotation
	protected float speed;

	// constructors ///
	public ProjectileObj(ProjectileObj nextObj) throws SlickException {
		
		projectileSprite = new Image("fire.png");
		scale = 0.8f;

		projectileSprite.setCenterOfRotation(projectileSprite.getWidth()
				* scale / 2, projectileSprite.getHeight() * scale / 2); // Set
		
		this.nextObj = nextObj;

		position = new Vector2f();
		displacement = new Vector2f();
		rotation = 0;
		scale = 0.8f;
		speed = 3;
		
		timer = 0;
	}

	public ProjectileObj() throws SlickException {
		
		projectileSprite = new Image("fire.png");
		scale = 0.8f;

		projectileSprite.setCenterOfRotation(projectileSprite.getWidth()
				* scale / 2, projectileSprite.getHeight() * scale / 2); // Set
		
		displacement = new Vector2f();
		position = new Vector2f();
		rotation = 0;
		scale = 0.8f;
		speed = 3;

		timer = 0;
	}

	// ///////// methods /////////////
	
	
	Rectangle getCollisionBox(Image sprite, int offsetX, int offsetY, int offsetWidth, int offsetHeight) {
		return new Rectangle((int)position.x+offsetX, (int)position.y+offsetY, sprite.getWidth()+offsetWidth, sprite.getHeight()+offsetHeight);
	}
	
	public void setPos(Vector2f playerPos) {
		float speed = 5;
		float delta = 2;
		// idea is for projectiles to not start in the middle of the player!
		// displacement.x = (float) Math.cos(Math.toRadians(rotation - 90))
		// * speed * delta;
		// displacement.y = (float) Math.sin(Math.toRadians(rotation - 90))
		// * speed * delta;

		// position.x = displacement.x + playerPos.x;// should displace
		// based on player angle!!!!
		// position.y = displacement.x + playerPos.y;
		position.x = playerPos.x;
		
		position.y = playerPos.y;
	}

	public void setRotation(float playerRotation) {
		rotation = playerRotation;// only set when created
	}

	public ProjectileObj getNext() {
		return nextObj;

	}
	public ProjectileObj getPrev(){
		return prevObj;
	}
	public void setPrev(ProjectileObj prevObj){
		this.prevObj = prevObj;
	}
	public void setNext(ProjectileObj nextObj){
		this.nextObj = nextObj;
	}

	public void update() {// moves projectiles
//check if timer has run out....
		
		wrapper();

		float speed = 5;
		float delta = 1;

		displacement.x = (float) Math.cos(Math.toRadians(rotation - 90))
				* speed * delta;
		displacement.y = (float) Math.sin(Math.toRadians(rotation - 90))
				* speed * delta;

		position.x += displacement.x;
		position.y += displacement.y;

	    timer++;
	}

	void wrapper() {
		Image sprite = projectileSprite;
		
		
		// Screen wrap
		if (position.y < 0 - (sprite.getHeight() * scale))
			position.y = Main.HEIGHT;// resets position when
												// exiting frame
		if (position.y > Main.HEIGHT)
			position.y = 0 - (sprite.getHeight() * scale);
		if (position.x < 0 - (sprite.getWidth() * scale))
			position.x = Main.WIDTH;
		if (position.x > Main.WIDTH)
			position.x = 0 - (sprite.getWidth() * scale);
	}

	/**
	 * @return void Used for variables which are to be used for rendering
	 */
	public void render() {
		Image sprite = projectileSprite;
		sprite.setRotation(rotation);
		sprite.draw(position.x, position.y, scale);
		
	//System.out.println(sprite);
	}
}

