import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Color;

import java.util.Random;
import java.util.Timer;
import java.util.Iterator;
import java.util.Random;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Play extends BasicGameState {

	private Image brick, cloud, img1, img2;

	private Random r;

	private ArrayList<Unit> moving_scenery;
	private ArrayList<Foe> foes;
	private ArrayList<Projectile> fireballs;
	private int killCount;
	private Timer tid;
	private int respawnTimer;

	Character character;
	// fireball cooldown
	private float cooldown;

	float menuX = 200;
	float menuY = 200;

	public Play(int state) {
		character = null;
		cooldown = 0;
		killCount = 0;
		foes = new ArrayList<Foe>();
		moving_scenery = new ArrayList<Unit>();
		fireballs = new ArrayList<Projectile>();
		tid = new Timer();
		respawnTimer = 0;
		r = new Random();
	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		initiateWorld(gc);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		drawWorld(gc, g, sbg);
		drawInterface(gc, g);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		moveEntities(gc, sbg);
		respawn(gc);
	}

	private void initiateWorld(GameContainer gc) throws SlickException {
		brick = new Image("res/brick_block.png");
		cloud = new Image("res/cloud.png");
		img1 = new Image("res/Gnome_child_chathead.png");
		img2 = new Image("res/dogedoge.png");
		img1 = img1.getScaledCopy((float) 0.5);

		// Add the character
		character = new Character(img1, false, gc);

		// Add the foes
		for (int i = 0; i < 5; i++) {
			foes.add(new Foe(img2, true, gc));
		}

		// Add the clouds
		int cloudDirection = r.nextInt(4) + 1;
		for (int i = 0; i < 30; i++) {

			moving_scenery.add(new Cloud(cloud, gc, cloudDirection));
		}
	}

	private void drawWorld(GameContainer gc, Graphics g, StateBasedGame sbg) {
		g.setBackground(Color.white);

		if (!moving_scenery.isEmpty()) {
			for (Unit u : moving_scenery) {
				g.drawImage(u.getImage(), u.getX(), u.getY());
			}
		}

		// Draw the foes
		if (!foes.isEmpty()) {
			for (Unit f : foes) {
				int direcConstant = f.getDirection();
				Image charImg = f.getImage();
				if (direcConstant == 1) {
					charImg = charImg.getFlippedCopy(true, false);
				}
				g.drawImage(charImg, f.getX(), f.getY());
			}
		}
		// Draw the character
		if (character != null) {
			int direcConstant = character.getDirection();
			Image charImg = character.getImage();
			if (direcConstant == 1) {
				charImg = charImg.getFlippedCopy(true, false);
			}
			g.drawImage(charImg, character.getX(), character.getY());
		}

		if (Keyboard.isKeyDown(Input.KEY_P)) {
			if (cooldown >= 30) {
				cooldown = 0;
				try {
					shoot(gc, character.getX(), character.getY(),
							character.getDirection());
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		}
		cooldown++;

		if (!fireballs.isEmpty()) {
			for (Projectile f : fireballs) {
				g.drawImage(f.getImage(), f.getX(), f.getY());
			}
		}
	}

	private void moveEntities(GameContainer gc, StateBasedGame sbg) {
		if (character != null) {
			character.pollInput();
		}
		if (!foes.isEmpty()) {
			float midCharX = character.getX() + character.getImage().getWidth()
					/ 2;
			float midCharY = character.getY()
					+ character.getImage().getHeight() / 2;
			for (Foe f : foes) {
				f.chase(character);
				if (midCharX > f.getX()
						&& (midCharX < f.getX() + f.getImage().getWidth())
						&& midCharY > f.getY()
						&& midCharY < f.getY() + f.getImage().getHeight()) {
					sbg.enterState(4);
				}
			}
		}
		if (!moving_scenery.isEmpty()) {
			for (Unit u : moving_scenery) {
				u.move();
			}
		}

		if (!fireballs.isEmpty()) {
			checkFireballs(gc, sbg);
			for (Projectile f : fireballs) {
				f.move();
			}
		}
	}

	public void checkFireballs(GameContainer gc, StateBasedGame sbg) {
		Iterator<Projectile> iterBalls = fireballs.iterator();
		while (iterBalls.hasNext()) {
			Projectile fb = iterBalls.next();
			if (fb.getX() > gc.getWidth()
					|| fb.getX() < -fb.getImage().getWidth()
					|| fb.getY() > gc.getHeight()
					|| fb.getY() < -fb.getImage().getHeight()) {
				iterBalls.remove();
			}
		}

		// Check the doges if collision has occurred
		Iterator<Foe> iterFoe = foes.iterator();
		while (iterFoe.hasNext()) {
			Foe f = iterFoe.next();
			for (Projectile fb : fireballs) {
				float midX = fb.getX() + fb.getImage().getWidth() / 2;
				float midY = fb.getY() + fb.getImage().getHeight() / 2;
				if (midX < f.getX() + f.getImage().getWidth()
						&& midX > f.getX() && midY > f.getY()
						&& midY < f.getY() + f.getImage().getHeight()) {
					iterFoe.remove();
					killCount++;
					System.out.println("COLLISiON ALERT! COLLISION ALERT!");
				}
			}
		}

	}

	public int getID() {
		return 2;
	}

	public void shoot(GameContainer gc, float x, float y, int direction)
			throws SlickException {
		Image fireball = new Image("res/fireball.png")
				.getScaledCopy((float) 0.2);
		switch (direction) {
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
		fireballs.add(new Projectile(fireball, gc, x, y, direction));
	}

	public void drawInterface(GameContainer gc, Graphics g) {
		g.setColor(Color.black);
		g.drawString("KEEL COUNT = " + killCount, 800, 20);

	}

	public void respawn(GameContainer gc) {
		if (respawnTimer > (1000 / (killCount + 1))) {
			foes.add(new Foe(img2, true, gc));
			respawnTimer = 0;
		}
		respawnTimer++;
	}
}
