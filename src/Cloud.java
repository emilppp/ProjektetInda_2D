import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class Cloud extends Unit {

	public Cloud(Image image, GameContainer gc, int direction) {
		super.gc = gc;
		setUp(image.getScaledCopy(r.nextInt(3) + 1), false);
		this.direction = direction;
		velocity = (float) (r.nextInt(3) + 1 * 0.1);

		xpos = r.nextInt(gc.getWidth() + 50) - 50;
		ypos = r.nextInt(gc.getHeight());
	}

	// TJENARE BENNY

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
	}
//	
	public void setRandomDirection() {
		int direc = r.nextInt(2);
		if(direc == 0) {
			direction = 1;
		}
		else {
			direction = 3;
		}
	}
}
