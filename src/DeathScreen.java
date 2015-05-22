import org.lwjgl.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class DeathScreen extends BasicGameState{
	public Image playagain;
	public String musn;

	public DeathScreen(int state) {
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		playagain = new Image("res/playagain.png");

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(Color.black);
		g.setColor(Color.white);
		g.drawString("kills = " + Play.killCount, 400, 130);
		g.drawImage(playagain, 400, 230);
		g.drawString(musn, 50, 50);
		
	}	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();

		int xpos = Mouse.getX();
		int ypos = Math.abs(gc.getHeight() - Mouse.getY());
		
		if((xpos>400&&xpos<524)&&(ypos>230&&ypos<265)) {
			if(input.isMouseButtonDown(0)) {
				gc.reinit();
				sbg.enterState(2);
			}
		}
		musn = "X= " + xpos + "\nY= " + ypos;
		
	}
	public int getID() {
		return 4;
	}
}
