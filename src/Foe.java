import java.awt.geom.AffineTransform;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class Foe extends Unit {

	private static Random r = new Random();
	private int width;
	private int height;

	public Foe(Image image, boolean imageOrSprite, GameContainer gc, int x,
			int y, int speed) {
		direction = 1;
		width = image.getWidth() / 9;
		height = image.getHeight();
		super.gc = gc;
		super.velocity = speed;
		super.xpos = x;
		super.ypos = y;

		setUp(image, imageOrSprite);
	}

	public Foe(Image image, boolean imageOrSprite, GameContainer gc) {
		this(image, imageOrSprite, gc, r.nextInt(gc.getWidth()
				- image.getWidth() / 9), r.nextInt(gc.getHeight()
				- image.getHeight()), r.nextInt(2) + 1);
	}

	public void chase(Character character) {
		if (character.getX() > xpos) {
			xpos += velocity;
			direction = 3;
		}
		if (character.getX() < xpos) {
			xpos -= velocity;
			direction = 1;
		}
		if (character.getY() < ypos) {
			ypos -= velocity;
			direction = 2;
		}
		if (character.getY() > ypos) {
			ypos += velocity;
			direction = 4;
		}
		
		spriteCounter++;
		if (imageOrSprite) {
			if (spriteCounter / 5 > 7) {
				spriteCounter = 1;
			}
			sprite = running.getSprite(spriteCounter / 5, 0);
			idleCounter = 0;
			idle();
		}

	}
}