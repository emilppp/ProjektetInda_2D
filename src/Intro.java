import org.lwjgl.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Intro extends BasicGameState{
	public Image continuepic;
	public Image intro;
	public String musn = "";
	
	public Intro(int state) {
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		continuepic = new Image("res/continue.png");
		intro = new Image("res/tumblr_napyo4LF891solpxqo1_500.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	
		g.drawString("Welcome!", 440, 80);
		g.drawImage(intro, 230, 300);
		g.drawImage(continuepic, 420, 200);
		
		g.drawString(musn, 500, 500);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//Hello
		Input input = gc.getInput();
		
		int xpos = Mouse.getX();
		int ypos = Math.abs(gc.getHeight() - Mouse.getY());
		if((xpos>420&&xpos<539)&&(ypos>200&&ypos<235)) {
			if(input.isMouseButtonDown(0)) {
				sbg.enterState(1);
			}
		}
		
		musn = "X= " + xpos + "\nY= " + ypos;

		
	}
	
	public int getID() {
		return 0;
	}
}
