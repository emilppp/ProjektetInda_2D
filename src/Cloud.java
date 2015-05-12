import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class Cloud extends Unit {

	public Cloud(Image image, GameContainer gc) {
		super.gc = gc;
		setUp(image.getScaledCopy(r.nextInt(2) + 1), false);
		setRandomDirection();
		velocity = r.nextInt(3) + 1;
		
		xpos = r.nextInt(gc.getWidth() + 50) - 50;
		ypos = r.nextInt(gc.getHeight()/2); 
	}
	
	public void move() {
		xpos += 0.05 * xDirection * velocity;
		
		if(xpos > gc.getWidth() + image.getWidth()) {
			xpos = -image.getWidth();
		}
		if(xpos < -image.getWidth()) {
			xpos = gc.getWidth();
		}
	}
}
