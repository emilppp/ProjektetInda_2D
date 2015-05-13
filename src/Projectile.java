import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class Projectile extends Unit {

	public Projectile(Image image, GameContainer gc, float xpos, float ypos, int direction) {
		super.gc = gc;
		
		setUp(image, imageOrSprite);
		velocity = 6;
		this.direction = direction;
		this.xpos = xpos;
		this.ypos = ypos + 5;
	}
}
