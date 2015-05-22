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

	//The image
	private Image overworld, cloud, img1, img2, bird, missiles, terrain;
	
	//The arraylist storing the different terrain images. Before each new game, a random terrain is selected.
	private ArrayList<Image> terrains;

	private Random r;

	//The arraylist storing all the moving scenery on the map; clouds and birds. None of these are removed during gameplay, but will
	//just fly in specific patterns.
	private ArrayList<Unit> moving_scenery;
	
	//The arraylist storing all the foes currently spawned. When a foe dies, it is removed from this arraylist.
	private ArrayList<Foe> foes;
	
	//The arraylist storing all the projectiles fired by the player.
	private ArrayList<Projectile> projectiles;
	
	//The number of enemies the character has defeated.
	public static int killCount;
	
	//The timer that checks when the foes may spawn. When the respawn timer reaches the value of the cooldown, a new foe will spawn, after which
	//the timer will reset to zero.
	private int respawnCD = 300;
	private int respawnTimer = 0;

	Character character;
	// projectile cooldown and timer. When the timer reaches the value of the projectile cooldown, a new projectile is ready
	//to be fired. After firing, the cooldown timer will reset to zero.
	private float projectileCD = 25;
	private float projectileTimer = 0;

	public Play(int state) {

		character = null;
		killCount = 0;
		foes = new ArrayList<Foe>();
		moving_scenery = new ArrayList<Unit>();
		terrains = new ArrayList<Image>();
		projectiles = new ArrayList<Projectile>();
		r = new Random();
	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		if (foes != null) {
			reset();
		}
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
		spawnFoes(gc);
	}

	/**
	 * Prepare the world, loading all the images as well as creating the character, foes and scenery.
	 * @param gc
	 * @throws SlickException
	 */
	private void initiateWorld(GameContainer gc) throws SlickException {
		// images
		overworld = new Image("res/overworld.png");
		cloud = new Image("res/cloud.png");
		img1 = new Image("res/starship.png");
		img2 = new Image("res/ufo.png");
		bird = new Image("res/birds.png");
		missiles = new Image("res/missiles.png");
		terrains.add(new Image("res/overworld.png"));
		terrains.add(new Image("res/overworld_desert.png"));
		terrains.add(new Image("res/overworld_ocean.png"));
		terrains.add(new Image("res/overworld_city.png"));
		terrains.add(new Image("res/overworld_canyon.png"));
		terrain = terrains.get(r.nextInt(terrains.size()));

		img1 = img1.getScaledCopy(0.3f);
		//img2 = img2.getScaledCopy(0.3f);

		// Add the character
		character = new Character(img1, false, gc);

		// Add the clouds
		int cloudDirection = r.nextInt(4) + 1;
		for (int i = 0; i < 20; i++) {
			moving_scenery.add(new Cloud(cloud, gc, cloudDirection));
		}
		// Add the birds
		for (int i = 0; i < 10; i++) {
			moving_scenery.add(new Bird(bird, gc, cloudDirection));
		}
	}

	/**
	 * Draws the world and everything in it, like the player and the foes as well as the scenery, based on their respective locations and values.
	 * NOTE: This method also contains the fire command (if the correct key is pressed, call the shoot() function.
	 * @param gc
	 * @param g
	 * @param sbg
	 */
	private void drawWorld(GameContainer gc, Graphics g, StateBasedGame sbg) {
		g.drawImage(terrain, 0, 0);

		if (!moving_scenery.isEmpty()) {
			for (Unit u : moving_scenery) {
				g.drawImage(u.getSprite(), u.getX(), u.getY());
			}
		}

		// Draw the foes
		if (!foes.isEmpty()) {
			for (Unit f : foes) {
				g.drawImage(f.getSprite(), f.getX(), f.getY());
			}
		}
		// Draw the character
		if (character != null) {
			g.drawImage(character.getSprite(), character.getX(),
					character.getY());
		}
		if (Keyboard.isKeyDown(Input.KEY_P)) {
			if (projectileTimer >= projectileCD) {
				//The angle of the projectiles, based on the characters current direction.
				int projectileAngle = Math.round(character.getSprite().getRotation());
				int projectileDirection= character.getDirection();
				switch(projectileAngle) {
				case 180:
					projectileDirection = 1;
					break;
				case -90:
					projectileDirection = 2;
					break;
				case 0:
					projectileDirection = 3;
					break;
				case 90:
					projectileDirection = 4;
					break;
				case 135:
					projectileDirection = 5;
					break;
				case -135:
					projectileDirection = 6;
					break;
				case -45:
					projectileDirection = 7;
					break;
				case 45:
					projectileDirection = 8;
					break;
				}
				try {
					shoot(gc, character.getCenterX(), character.getCenterY(),
							projectileDirection);
					projectileTimer = 0;
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		}
		projectileTimer++;

		if (!projectiles.isEmpty()) {
			for (Projectile f : projectiles) {
				g.drawImage(f.getSprite(), f.getX(), f.getY());
			}
		}
	}

	/**
	 * Moves all the entities on the field. The entities covers all the objects inheriting from the Unit class, eg. the character, the foes as
	 * well as the clouds and birds.
	 * @param gc
	 * @param sbg
	 */
	private void moveEntities(GameContainer gc, StateBasedGame sbg) {
		if (character != null) {
			character.pollInput();
		}
		if (!foes.isEmpty()) {
			float midCharX = character.getCenterX();
			float midCharY = character.getCenterY();
			for (Foe f : foes) {
				f.chase(character);
				if (midCharX > f.getX()
						&& (midCharX < f.getX() + f.getSprite().getWidth())
						&& midCharY > f.getY()
						&& midCharY < f.getY() + f.getSprite().getHeight()) {
					sbg.enterState(4);
				}
			}
		}
		if (!moving_scenery.isEmpty()) {
			for (Unit u : moving_scenery) {
				u.move();
			}
		}

		if (!projectiles.isEmpty()) {
			checkProjectiles(gc, sbg);
			for (Projectile f : projectiles) {
				f.move();
			}
		}
	}

	/**
	 * Check to see if the fireballs has hit a target. Should that be the case, erase the target.
	 * @param gc
	 * @param sbg
	 */
	public void checkProjectiles(GameContainer gc, StateBasedGame sbg) {
		Iterator<Projectile> iterBalls = projectiles.iterator();
		while (iterBalls.hasNext()) {
			Projectile fb = iterBalls.next();
			if (fb.getX() > gc.getWidth()
					|| fb.getX() < -fb.getSprite().getWidth()
					|| fb.getY() > gc.getHeight()
					|| fb.getY() < -fb.getSprite().getHeight()) {
				iterBalls.remove();
			}
		}

		// Check the doges if collision has occurred
		Iterator<Foe> iterFoe = foes.iterator();
		while (iterFoe.hasNext()) {
			Foe f = iterFoe.next();
			for (Projectile fb : projectiles) {
				float midX = fb.getX() + fb.getSprite().getWidth() / 2;
				float midY = fb.getY() + fb.getSprite().getHeight() / 2;
				if (midX < f.getX() + f.getSprite().getWidth()
						&& midX > f.getX() && midY > f.getY()
						&& midY < f.getY() + f.getSprite().getHeight()) {
					iterFoe.remove();
					killCount++;
				}
			}
		}
	}

	public int getID() {
		return 2;
	}

	/**
	 * Reset this game, resetting the arrays and clearing the field of all foes and scenery.
	 */
	public void reset() {
		foes.clear();
		projectiles.clear();
		killCount = 0;
		moving_scenery.clear();

	}

	/*
	 * The shoot method. This is called whenever the button P is pressed, and creates a 
	 */
	public void shoot(GameContainer gc, float x, float y, int direction)
			throws SlickException {
		Image fireball = missiles.getScaledCopy(0.3f);
		projectiles.add(new Projectile(fireball, gc, x, y, direction, character.getSpeed()));
	}

	/**
	 * Draw the interface with text signifying the kill count as well as the number of enemies on the field.
	 * @param gc
	 * @param g
	 */
	public void drawInterface(GameContainer gc, Graphics g) {
		g.setColor(Color.white);
		g.drawString("Kill count: " + killCount, 10, 25);
		g.drawString("Enemies: " + foes.size(), 10, 40);

	}

	/**
	 * Returns the kill count.
	 * @return
	 */
	public int getKillCount() {
		return killCount;
	}

	/**
	 * After a set time has passed, proceed with spawning new foes. The spawn rate is proportional to the characters kill count.
	 * @param gc
	 */
	public void spawnFoes(GameContainer gc) {
		if (respawnTimer > respawnCD/Math.abs(Math.sqrt(killCount+1))) {
			int foeSpeed = 1;
			if(killCount > 10) {
				foeSpeed = Math.round(killCount / 10);
			}
			foes.add(new Foe(img2, true, gc, foeSpeed));
			respawnTimer = 0;
		}
		respawnTimer++;
	}

	/**
	 * The number of foes the character has defeated.
	 * @return
	 */
	public int killCount() {
		return killCount;
	}
}
