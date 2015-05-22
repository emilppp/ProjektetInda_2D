import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class Character extends Unit {

	public Character(Image image, boolean imageOrSprite, GameContainer gc) {
		xpos = 480;
		ypos = 320;

		super.velocity = 3;
		setUp(image, false);
	}

	/**
	 * Poll and see if any of the keyboards keys are pressed. Should that be the case, act accordingly to the specific key, and then move.
	 */
	public void pollInput() {
		direction = 0;
		if (Keyboard.isKeyDown(Input.KEY_A)) {
			direction = 1;
		}
		if (Keyboard.isKeyDown(Input.KEY_D)) {
			direction = 3;
		}
		if (Keyboard.isKeyDown(Input.KEY_W)) {
			direction = 2;
		}
		if (Keyboard.isKeyDown(Input.KEY_S)) {
			direction = 4;
		}
		if (Keyboard.isKeyDown(Input.KEY_S) && Keyboard.isKeyDown(Input.KEY_A)) {
			direction = 5;
		}
		if (Keyboard.isKeyDown(Input.KEY_A) && Keyboard.isKeyDown(Input.KEY_W)) {
			direction = 6;
		}
		if (Keyboard.isKeyDown(Input.KEY_W) && Keyboard.isKeyDown(Input.KEY_D)) {
			direction = 7;
		}
		if (Keyboard.isKeyDown(Input.KEY_S) && Keyboard.isKeyDown(Input.KEY_D)) {
			direction = 8;
		}
		if (direction != 0) {
			move();
		}
	}

	/**
	 * Set up this unit's image as well as the midvalue x and y positions.
	 * @param image
	 * @param imageOrSprite
	 */
	public void setUp(Image image, boolean imageOrSprite) {
		this.image = image;
		sprite = image;
		centerX = xpos + image.getWidth() / 2;
		centerY = ypos + image.getHeight() / 2;
	}
}