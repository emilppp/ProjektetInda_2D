import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Bird extends Unit {

	public Bird(Image image, GameContainer gc, int direction) {
		super.gc = gc;
		image = image.getScaledCopy((float) 1.5);
		setUp(image, true);
		this.direction = direction;
		velocity = (float) (r.nextInt(2) + 1 * 0.8);

		xpos = r.nextInt(gc.getWidth() + 50) - 50;
		ypos = r.nextInt(gc.getHeight());
	}

	public void setUp(Image image, boolean imageOrSprite) {
		spriteCounter = 0;

		int width = image.getWidth() / 4;
		int height = image.getHeight() / 4;

		spriteSheet = new SpriteSheet(image, width, height);
		sprite = spriteSheet.getSprite(0, 0);
	}

	/**
	 * Move the bird according to its direction. Should the bird reach a certain point outside the screen, send it to the
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

		//Based on the direction of this bird, choose the corresponding sprite sheet accordingly.
		int spriteChooser = 0;

		switch (direction) {
		case 1:
			spriteChooser = 1;
			break;
		case 2:
			spriteChooser = 3;
			break;
		case 3:
			spriteChooser = 2;
			break;
		case 4:
			spriteChooser = 0;
			break;
		}
		
		spriteCounter++;
		if (spriteCounter / 10 > 3) {
			spriteCounter = 0;
		}
		
		sprite = spriteSheet.getSprite(spriteCounter / 10, spriteChooser);

		if (xpos > gc.getWidth() + 200) {
			xpos = -200;
		}
		if (xpos < -200) {
			xpos = gc.getWidth() + 200;
		}
		if (ypos > gc.getHeight() + 200) {
			ypos = -200;
		}
		if (ypos < -200) {
			ypos = gc.getHeight() + 200;
		}

	}
}
