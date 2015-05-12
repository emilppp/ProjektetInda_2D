import java.util.Random;

import org.newdawn.slick.*;

public class Unit {
	
	protected GameContainer gc;
	
	protected static Random r = new Random();
	
	protected float xpos;
	protected float ypos;
	
	protected int health;
	
	protected int velocity;
	
	protected Image image;
	protected Image sprite;
	protected int spriteCounter;
	protected int idleCounter;
	
	protected int yDirection;
	protected int xDirection;
	
	protected SpriteSheet running;
	protected Image idleImage;
	
	protected boolean imageOrSprite; // true if sprite, false if only image
	
	public Unit() {
	}
	
	public Image getImage() {
		return sprite;
	}
	
	public float getX() {
		return xpos;
	}
	
	public float getY() {
		return ypos;
	}
	
	public int getYDirection() {
		return yDirection;
	}
	
	public int getXDirection() {
		return xDirection;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setUp(Image image, boolean imageOrSprite) {
		this.imageOrSprite = imageOrSprite;

		spriteCounter = 1;
		idleCounter = 0;

		this.image = image;
		sprite = image;

		if (imageOrSprite) {
			Image runImage = image.getSubImage(64, 0, 512, 64);
			running = new SpriteSheet(runImage, 64, 64);

			idleImage = image.getSubImage(0, 0, 64, 64);

			sprite = running.getSprite(0, 0);
		}
	}
	
	public void idle() {
		idleCounter++;
		if (idleCounter > 1) {
			if(imageOrSprite) {
				sprite = idleImage;
			}
		}
	}
	
	public void setRandomDirection() {
		int yDirec = r.nextInt(2);
		int xDirec = r.nextInt(2);
		if(yDirec == 1) {
			yDirection = 1;
		}
		else {
			yDirection = -1;
		}
		if(xDirec == 1) {
			xDirection = 1;
		}
		else {
			xDirection = -1;
					
		}
	}
	
	public void switchYDirection() {
		yDirection = -yDirection;
	}
	
	public void switchXDirection() {
		xDirection = -xDirection;
	}
	
	public void move() {
		
	}

}