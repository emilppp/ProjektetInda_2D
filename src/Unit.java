import java.util.Random;

import org.newdawn.slick.*;

public class Unit {
	
	protected GameContainer gc;
	
	protected static Random r = new Random();
	
	protected float xpos;
	protected float ypos;
		
	protected float velocity;
	
	protected Image image;
	protected Image sprite;
	protected int spriteCounter;
	protected int idleCounter;
	
	protected int direction;
	
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
	
	public int getDirection() {
		return direction;
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
	
	public void move() {
		switch (direction) {
		case 1:
			xpos -= velocity;
			break;
		case 2:
			ypos -= velocity;
			break;
		case 3:
			xpos += velocity;
			break;
		case 4:
			ypos += velocity;
			break;
		}
	}

}