import org.lwjgl.*;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Color;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Play extends BasicGameState {

	Image brick, cloud;

	ArrayList<Unit> moving_scenery;
	ArrayList<Foe> foes;

	Character character;

	float menuX = 200;
	float menuY = 200;

	public Play(int state) {
		character = null;
		foes = new ArrayList<Foe>();
		moving_scenery = new ArrayList<Unit>();
	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		initiateWorld(gc);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		drawWorld(gc, g);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		moveEntities();
	}

	private void initiateWorld(GameContainer gc) throws SlickException {
		brick = new Image("res/brick_block.png");
		cloud = new Image("res/cloud.png");
		Image img1 = new Image("res/Gnome_child_chathead.png");
		Image img2 = new Image("res/dogedoge.png");
		img1 = img1.getScaledCopy((float) 0.8);
		//cloud = cloud.getScaledCopy((float) 1.5);
		
		//Add the character
		character = new Character(img1, false, gc);

		//Add the foes
		for (int i = 0; i < 5; i++) {
			foes.add(new Foe(img2, true, gc, 50, 50, 1));
			foes.add(new Foe(img2, true, gc));
		}
		
		//Add the clouds
		for(int i = 0; i < 5; i++) {
			moving_scenery.add(new Cloud(cloud, gc));
		}
	}

	private void drawWorld(GameContainer gc, Graphics g) {
		g.setBackground(Color.white);
		
		
		if(!moving_scenery.isEmpty()) {
			for(Unit u : moving_scenery) {
				g.drawImage(u.getImage(), u.getX(), u.getY());
			}
		}
		
		//Draw the foes
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
		//Draw the character
		if (character != null) {
			int direcConstant = character.getDirection();
			Image charImg = character.getImage();
			if (direcConstant == 1) {
				charImg = charImg.getFlippedCopy(true, false);
			}
			g.drawImage(charImg, character.getX(), character.getY());
		}
		
		if(!character.getFireballs().isEmpty()) {
			for(Projectile f : character.getFireballs()) {
				g.drawImage(f.getImage(), f.getX(), f.getY());
			}
		}
	}
	
	private void moveEntities() {
		if (character != null) {
			character.pollInput();
		}
		if (!foes.isEmpty()) {
			for (Foe f : foes) {
				f.chase(character);
			}
		}
		if(!moving_scenery.isEmpty()) {
			for(Unit u : moving_scenery) {
				u.move();
			}
		}
		
		if(!character.getFireballs().isEmpty()) {
			for(Projectile f : character.getFireballs()) {
				f.move();
			}
		}
	}

	public int getID() {
		return 2;
	}
}
