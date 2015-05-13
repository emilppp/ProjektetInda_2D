import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class Character extends Unit {

	private ArrayList<Projectile> fireballs;
	//fireball cooldown
	private float cooldown;

	public Character(Image image, boolean imageOrSprite, GameContainer gc) {
		fireballs = new ArrayList<Projectile>();
		xpos = 320;
		ypos = 320;
		super.velocity = 1;
		setUp(image, imageOrSprite);
		cooldown = 0;
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
		if (Keyboard.isKeyDown(Input.KEY_P)) {
			if (cooldown >= 30) {
				cooldown = 0;
				try {
					shoot(gc);
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		}
		idle();
		cooldown++;
	}

	public ArrayList<Projectile> getFireballs() {
		return fireballs;
	}

	public void shoot(GameContainer gc) throws SlickException {
		Image fireball = new Image("res/fireball.png").getScaledCopy((float) 0.2);
		switch(direction) {
		case 1:
			fireball = fireball.getFlippedCopy(true, false);
			break;
		case 2:
			float rotate = -90;
			fireball.rotate(rotate);
			break;
		case 3:
			break;
		case 4:
			float rotate2 = 90;
			fireball.rotate(rotate2);
		}
		fireballs.add(new Projectile(fireball, gc, xpos, ypos, direction));
	}
}