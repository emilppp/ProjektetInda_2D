import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class Character extends Unit {

	public Character(Image image, boolean imageOrSprite, GameContainer gc) {
		xpos = 320;
		ypos = 320;
		super.velocity = 1;
		setUp(image, imageOrSprite);
	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		image = new Image("res/Gnome_child_chathead.png");

	}

	public void pollInput() {

		if (Keyboard.isKeyDown(Input.KEY_A)) {
			move(-1, 0);
			xDirection = -1;
		}
		if (Keyboard.isKeyDown(Input.KEY_D)) {
			move(1, 0);
			xDirection = 1;
		}
		if (Keyboard.isKeyDown(Input.KEY_W)) {
			move(0, -1);
		}
		if (Keyboard.isKeyDown(Input.KEY_S)) {
			move(0, 1);
		}
		idle();
	}

	public void move(int x, int y) {
		xpos += x * 2 * velocity;
		ypos += y * 2 * velocity;
		spriteCounter++;
		if (imageOrSprite) {
			if (spriteCounter > 7) {
				spriteCounter = 1;
			}
			sprite = running.getSprite(spriteCounter, 0);
			idleCounter = 0;
		}
	}
}