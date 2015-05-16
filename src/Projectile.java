import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class Projectile extends Unit {

	public Projectile(Image image, GameContainer gc, float xpos, float ypos, int direction) {
		super.gc = gc;
		
		setUp(image, imageOrSprite);
		super.velocity = 6;
		this.direction = direction;
		this.xpos = xpos - image.getWidth() / 3;
		this.ypos = ypos + 5;
	}
}
