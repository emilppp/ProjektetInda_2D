import java.util.ArrayList;

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

	public void pollInput() {

		if (Keyboard.isKeyDown(Input.KEY_A)) {
			move();
			direction = 1;
		}
		if (Keyboard.isKeyDown(Input.KEY_D)) {
			move();
			direction = 3;
		}
		if (Keyboard.isKeyDown(Input.KEY_W)) {
			move();
			direction = 2;
		}
		if (Keyboard.isKeyDown(Input.KEY_S)) {
			move();
			direction = 4;
		}
		idle();
	}
}