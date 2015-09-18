package model.obstacle;


/**
 * A unit obstacle<br>
 * Units can move and attacks
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public abstract class Unit extends HP
{
	protected int speed,
				attack,
				accuracy,
				maxRange,
				minRange,
				defense;
	
	private static final int LEFT = 1;
	private static final int DOWN = 2;
	private static final int RIGHT = 3;
	private static final int UP = 4;
	
	protected boolean active;
	
	/**
	 * default constructor <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 */
	public Unit()
	{
		super();
		active = true;
	}
	
	/**
	 * moves the unit <br>
	 * @author Joesph 
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param cells - how many cells to move
	 * @param dir - the direction to move
	 */
//	public void move(int cells, int dir)
//	{
//		switch (dir)
//		{
//			case LEFT:
//				moveLeft(cells);
//				break;
//			case DOWN:
//				moveDown(cells);
//				break;
//			case RIGHT:
//				moveRight(cells);
//				break;
//			case UP:
//				moveUp(cells);
//				break;
//		}
//	}
//	
//	public abstract void moveLeft(int cells);
//	public abstract void moveDown(int cells);
//	public abstract void moveRight(int cells);
//	public abstract void moveUp(int cells);
	
	public int getSpeed()
	{
		return speed;
	}

	public int getAttack()
	{
		return attack;
	}

	public int getAccuracy()
	{
		return accuracy;
	}

	public int getMaxRange()
	{
		return maxRange;
	}
	
	public int getMinRange()
	{
		return minRange;
	}
	
	public int getDefense()
	{
		return defense;
	}
	public boolean isActive()
	{
		return active;
	}
	public void setActive(boolean active)
	{
		this.active = active;
	}

	public boolean getActive()
	{
		return active;
		
	}
	
		
}
