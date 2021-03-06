import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{
	
	public static final String gamename = "SPELETS_NAMN";
	
	// STATES
	public static final int intro = 0;
	public static final int menu = 1;
	public static final int play = 2;
	public static final int controls = 3;
	public static final int death = 4;
	
	public Game(String gamename) {
		super(gamename);
		
		// ADDS THE STATES. WILL BE CLASSES
		this.addState(new Intro(intro));
		this.addState(new Menu(menu));
		this.addState(new Play(play));
		this.addState(new Controls(controls));
		this.addState(new DeathScreen(death));
		this.enterState(intro);
	}
	
	
	public void initStatesList(GameContainer gc) throws SlickException {

	}
	
	public static void main(String[] args) {
		AppGameContainer appgc;
		
		try {
			appgc = new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(960, 640, false);
			appgc.setTargetFrameRate(60);
			appgc.start();
		} 
		catch(SlickException e) {
			e.printStackTrace();
		}
	}
	
	
}
