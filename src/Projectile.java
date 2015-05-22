import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Projectile extends Unit {

	public Projectile(Image image, GameContainer gc, float xpos, float ypos, int direction, float speed) {
		super.gc = gc;
		
		setUp(image, imageOrSprite);
		super.velocity = speed+3;
		this.direction = direction;
		
		//Placing the image with it's midpoint in in line with that of the character.
		this.xpos = xpos - image.getWidth()/2;
		this.ypos = ypos - image.getHeight()/2;
	}
	
	public void setUp(Image image, boolean imageOrSprite) {
		this.image = image;
		sprite = image;
	}
}
