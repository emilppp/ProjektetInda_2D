import java.awt.geom.AffineTransform;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Foe extends Unit {

	private static Random r = new Random();

	/**
	 * The constructor for the customizable foe. This constructor allows for a the foe to be spawned at a given location,
	 * as well as with a given speed.
	 * @param image
	 * @param imageOrSprite
	 * @param gc
	 * @param x
	 * @param y
	 * @param speed
	 */
	public Foe(Image image, boolean imageOrSprite, GameContainer gc, int x,
			int y, int speed) {
		direction = 1;
		super.gc = gc;
		super.velocity = speed;
		super.xpos = x;
		super.ypos = y;

		setUp(image, imageOrSprite);
	}

	/**
	 * The standard constructor for the foe. This constructor allows only for a speed to be assigned to the foe.
	 * @param image
	 * @param imageOrSprite
	 * @param gc
	 * @param speed
	 */
	public Foe(Image image, boolean imageOrSprite, GameContainer gc, int speed) {
		direction = 1;
		super.gc = gc;
		super.velocity = r.nextInt(speed) + 1;
		setRandomSpawn();
		setUp(image, imageOrSprite);
	}

	/**
	 * The chase methods calculates the distance between the foe and the character, and determines how the foe should move.
	 * Should this foe have a sprite sheet, also iterate through the sprite sheet and 
	 * @param character
	 */
	public void chase(Character character) {

		float dx = centerX - character.getCenterX();
		float dy = centerY - character.getCenterY();

		direction = 0;
		if (dx > 0 && dy == 0) {
			direction = 1;
		} else if (dx == 0 && dy > 0) {
			direction = 2;
		} else if (dx < 0 && dy == 0) {
			direction = 3;
		} else if (dx == 0 && dy < 0) {
			direction = 4;
		} else if (dx > 0 && dy < 0) {
			direction = 5;
		} else if (dx > 0 && dy > 0) {
			direction = 6;
		} else if (dx < 0 && dy > 0) {
			direction = 7;
		} else if (dx < 0 && dy < 0) {
			direction = 8;
		}
		if (direction != 0) {
			move();
		}
		if (imageOrSprite && spriteCounter % 10 == 0) {
			sprite = spriteSheet.getSprite((spriteCounter/10 -1), 0);
			if (spriteCounter == 60) {
				spriteCounter = 9;
			}
		}
		spriteCounter++;
	}
	
	/**
	 * Move this foe according to the player's current position.
	 */
	public void move() {
		float diagonalMove = (float) (Math.abs(velocity/(Math.sqrt(2))));
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
		case 5:
			ypos += diagonalMove;
			xpos -= diagonalMove;
			break;
		case 6:
			ypos -= diagonalMove;
			xpos -= diagonalMove;
			break;
		case 7:
			ypos -= diagonalMove;
			xpos += diagonalMove;
			break;
		case 8:
			ypos += diagonalMove;
			xpos += diagonalMove;
			break;
		}
		centerX = xpos + sprite.getWidth()/2;
		centerY = ypos + sprite.getHeight()/2;
	}
	
	/**
	 * Set this foes spawn point, randomly just outside the screen, so that the spawning is not visible to the player.
	 */
	public void setRandomSpawn() {
		int spawn_direction = r.nextInt(4);
		switch (spawn_direction) {
		case 0:
			xpos = -50;
			ypos = r.nextInt(gc.getHeight() + 100) - 50;
			break;
		case 1:
			xpos = r.nextInt(gc.getWidth() + 100) - 50;
			ypos = -50;
			break;
		case 2:
			xpos = gc.getWidth() + 50;
			ypos = r.nextInt(gc.getHeight() + 100) - 50;
			break;
		case 3:
			xpos = r.nextInt(gc.getWidth() + 100) - 50;
			ypos = gc.getHeight() + 50;
			break;
		}
	}

	public void setUp(Image image, boolean imageOrSprite) {
		this.imageOrSprite = imageOrSprite;

		spriteCounter = 1;

		this.image = image;
		sprite = image;

		if (imageOrSprite) {
			spriteSheet = new SpriteSheet(image, image.getWidth() / 6,
					image.getHeight());
			sprite = spriteSheet.getSprite(0, 0);
		}
		centerX = xpos + sprite.getWidth() / 2;
		centerY = ypos + sprite.getHeight() / 2;
	}
}