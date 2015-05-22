import org.lwjgl.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	public Image playnow;
	public Image quit;
	public Image controls;
	public Image cool_background;
	public String musn;

	
	public Menu(int state) {
	
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		playnow = new Image("res/playnow.png");
		controls = new Image("res/controls.png");
		quit = new Image("res/quit.png");
		cool_background = new Image("res/cool_background.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(cool_background, 0, 0);
		g.drawImage(playnow, 400, 230);
		g.drawImage(controls, 400, 290);
		g.drawImage(quit, 400, 350);
		
		//g.drawString(musn, 50, 50);
		
	}	
	
	/**
	 * Draw all the buttons and make them click-able.
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();

		int xpos = Mouse.getX();
		int ypos = Math.abs(gc.getHeight() - Mouse.getY());
		if((xpos>400&&xpos<524)&&(ypos>230&&ypos<265)) {
			if(input.isMouseButtonDown(0)) {
				sbg.enterState(2);
			}
		}
		if((xpos>400&&xpos<524)&&(ypos>290&&ypos<325)) {
			if(input.isMouseButtonDown(0)) {
				sbg.enterState(3);
			}
		}
		if((xpos>400&&xpos<524)&&(ypos>350&&ypos<385)) {
			if(input.isMouseButtonDown(0)) {
				gc.exit();
			}
		}
		
	}
	public int getID() {
		return 1;
	}
}
