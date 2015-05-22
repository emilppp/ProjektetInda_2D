import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Cloud extends Unit {

	public Cloud(Image image, GameContainer gc, int direction) {
		super.gc = gc;
		setUp(image.getScaledCopy(r.nextInt(3) + 1), false);
		this.direction = direction;
		velocity = (r.nextInt(2) + 1) * 0.1f;

		xpos = r.nextInt(gc.getWidth() + 50) - 50;
		ypos = r.nextInt(gc.getHeight());
	}


	/**
	 * Move the cloud according to its direction. Should the cloud reach a certain point outside the screen, send it to the
	 * opposite side and let it continue in this cycle. The purpose of this is to add a little bit of immersion.
	 */
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

		if (xpos > gc.getWidth() + image.getWidth()) {
			xpos = -image.getWidth();
		}
		if (xpos < -image.getWidth()) {
			xpos = gc.getWidth();
		}
		if (ypos > gc.getHeight() + image.getHeight()) {
			ypos = -image.getHeight();
		}
		if (ypos < -image.getHeight()) {
			ypos = gc.getHeight() + image.getHeight();
					}
	}
	public void setUp(Image image, boolean imageOrSprite) {
		this.image = image;
		sprite = image;
	}
}
