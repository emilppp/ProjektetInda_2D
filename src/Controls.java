import org.lwjgl.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Controls extends BasicGameState{
	public Image back;
	public String musn;

	
	public Controls(int state) {
	
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		back = new Image("res/back.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("Movement: W A S D\nAttack: A", 200, 80);
		g.drawImage(back, 240, 430);
		g.drawString(musn, 50, 50);
		
		g.setColor(Color.red);
		g.drawString("wow", 100, 130);
		g.setColor(Color.green);
		g.drawString("much malice", 130, 450);
		g.setColor(Color.yellow);
		g.drawString("so slayer", 400, 470);
		g.setColor(Color.pink);
		g.drawString("wow", 320, 500);
		
	}	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();

		int xpos = Mouse.getX();
		int ypos = Math.abs(gc.getHeight() - Mouse.getY());
	
		if((xpos>241&&xpos<363)&&(ypos>430&&ypos<565)) {
			if(input.isMouseButtonDown(0)) {
				sbg.enterState(1);
			}
		}
		musn = "X= " + xpos + "\nY= " + ypos;


		
	}
	public int getID() {
		return 3;
	}
}
