import java.util.Random;

import org.newdawn.slick.*;

public class Unit {
	
	protected GameContainer gc;
	
	protected static Random r = new Random();
	
	//The units x and y positions.
	protected float xpos;
	protected float ypos;
	
	//The units midvalue position, based on the size of the image. Used for collision with foes as well as the projectiles.
	protected float centerX;
	protected float centerY;
		
	//The velocity of this specific unit.
	protected float velocity;
	
	//The image of this specific unit. Note that this is not the image drawn on the field, just the image that the unit is initiated with.
	protected Image image;
	
	//This is the image of the unit that will be drawn on the field. Should the unit have a sprite, a sprite is picked from the sprite sheet below
	//and stored in this variable.
	protected Image sprite;
	protected SpriteSheet spriteSheet;
	protected int spriteCounter;
	
	//The amount of frames this particular unit has existed.
	protected int framesExisted = 0;
	
	//The direction that of which this unit is facing. The directions are
	// 1 = west, 2 = north, 3 = east, 4 = south, 5 = south west, 6 = north west, 7 = north east, 8 = south east
	protected int direction;
		
	//True if this unit uses a sprite sheet, false if the unit has a non-dynamic image.
	protected boolean imageOrSprite;
	
	public Unit() {
	}
	
	/**
	 * Return the sprite of this image. The returned sprite will then be drawn on the field.
	 * @return
	 */
	public Image getSprite() {
		return sprite;
	}
	
	/**
	 * Get the current x position of this unit.
	 * @return
	 */
	public float getX() {
		return xpos;
	}
	
	/**
	 * Get the current y position of this unit.
	 * @return
	 */
	public float getY() {
		return ypos;
	}
	
	/**
	 * Return the direction that this unit is facing.
	 * @return
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * Move this unit. See the different directions in the field declarations in the top.
	 * The sprite of this unit is adjusted based on the direction.
	 * Should the direction be diagonal, use pythagora's theorem and move the character diagonally by a speed of velocity * 1/sqrt(2).
	 * Then updates the characters x and y position, as well as the characters midvalue position.
	 */
	public void move() {
		float diagonalMove = (float) (Math.abs(velocity/(Math.sqrt(2))));
		switch (direction) {
		case 1:
			sprite.setRotation(180);
			xpos -= velocity;
			break;
		case 2:
			sprite.setRotation(-90);
			ypos -= velocity;
			break;
		case 3:
			sprite.setRotation(0);
			xpos += velocity;
			break;
		case 4:
			sprite.setRotation(90);
			ypos += velocity;
			break;
		case 5:
			sprite.setRotation(135);
			ypos += diagonalMove;
			xpos -= diagonalMove;
			break;
		case 6:
			sprite.setRotation(-135);
			ypos -= diagonalMove;
			xpos -= diagonalMove;
			break;
		case 7:
			sprite.setRotation(-45);
			ypos -= diagonalMove;
			xpos += diagonalMove;
			break;
		case 8:
			sprite.setRotation(45);
			ypos += diagonalMove;
			xpos += diagonalMove;
			break;
		}
		framesExisted++;
		centerX = xpos + sprite.getWidth()/2;
		centerY = ypos + sprite.getHeight()/2;
	}
	
	/**
	 * Return the midvalue x value of this unit.
	 * @return
	 */
	public float getCenterX() {
		return centerX;
	}
	
	/**
	 * Return the midvalue y of this unit.
	 * @return
	 */
	public float getCenterY() {
		return centerY;
	}
	
	/**
	 * Return the speed of this unit.
	 * @return
	 */
	public float getSpeed() {
		return velocity;
	}

}