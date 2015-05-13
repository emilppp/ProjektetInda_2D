import org.lwjgl.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class DeathScreen extends BasicGameState{
	public Image doge;
	public String musn;

	public DeathScreen(int state) {
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		doge = new Image("res/dogeicon.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(Color.black);
		g.setColor(Color.white);
		g.drawString("i cri evrtiem", gc.getWidth() / 2, gc.getHeight() / 2);
		g.drawString("u r dead. was kild by doge", gc.getWidth() / 2, (gc.getHeight() / 2) + 30);
		g.drawImage(doge, 480, 380);
		g.drawString(musn, 50, 50);
		
	}	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();

		int xpos = Mouse.getX();
		int ypos = Math.abs(gc.getHeight() - Mouse.getY());
	
		musn = "X= " + xpos + "\nY= " + ypos;
		
	}
	public int getID() {
		return 4;
	}
}
