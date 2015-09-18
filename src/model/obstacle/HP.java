package model.obstacle;


/**
 * Any obstacle that has Hit Points extends this class<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public abstract class HP extends Obstacle
{
	private static final long serialVersionUID = 1L;
	//public abstract static final int POINTS;
	protected int POINTS = 0;
	protected int BASE_HP = 0;
	protected int hp;

	/**
	 * getter for the hp <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return
	 */
	public int getHp()
	{
		return hp;
	}

	/**
	 * sets the HP <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param hp - the new HP
	 */
	public void setHp(int hp)
	{
		this.hp = hp;
	}

	/**
	 * Gets the amount of Points this obstacle is worth <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the points this obstacle is worth
	 */
	public int getPOINTS()
	{
		return POINTS;
	}

	/**
	 * gets the base hp of the HP <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the base HP
	 */
	public abstract int getBASE_HP();
	
}
