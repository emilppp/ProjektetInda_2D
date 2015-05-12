import org.lwjgl.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	public Image playnow;
	public Image quit;
	public Image controls;
	public String musn;

	
	public Menu(int state) {
	
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		playnow = new Image("res/playnow.png");
		controls = new Image("res/controls.png");
		quit = new Image("res/quit.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(playnow, 240, 230);
		g.drawImage(controls, 240, 290);
		g.drawImage(quit, 240, 350);
		
		g.drawString(musn, 50, 50);
		
	}	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();

		int xpos = Mouse.getX();
		int ypos = Math.abs(gc.getHeight() - Mouse.getY());
		if((xpos>241&&xpos<363)&&(ypos>230&&ypos<265)) {
			if(input.isMouseButtonDown(0)) {
				sbg.enterState(2);
			}
		}
		if((xpos>241&&xpos<363)&&(ypos>290&&ypos<325)) {
			if(input.isMouseButtonDown(0)) {
				sbg.enterState(3);
			}
		}
		if((xpos>241&&xpos<363)&&(ypos>350&&ypos<385)) {
			if(input.isMouseButtonDown(0)) {
				gc.exit();
			}
		}
		musn = "X= " + xpos + "\nY= " + ypos;


		
	}
	public int getID() {
		return 1;
	}
}
