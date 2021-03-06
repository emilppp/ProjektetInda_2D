import org.lwjgl.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Controls extends BasicGameState{
	public Image back;

	
	public Controls(int state) {
	
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		back = new Image("res/back.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.pink);
		g.drawString("Movement: W A S D\nShoot: P", 402, 80);
		g.drawImage(back, 400, 430);
		
		
		
	}	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();

		int xpos = Mouse.getX();
		int ypos = Math.abs(gc.getHeight() - Mouse.getY());
	
		if((xpos>400&&xpos<524)&&(ypos>430&&ypos<565)) {
			if(input.isMouseButtonDown(0)) {
				sbg.enterState(1);
			}
		}


		
	}
	public int getID() {
		return 3;
	}
}
