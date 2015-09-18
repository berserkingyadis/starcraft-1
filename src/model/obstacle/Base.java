package model.obstacle;


/**
 * A Base<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class Base extends HP
{
	private static final long serialVersionUID = 1L;
	public static final String DESCRIPTION = "Bases can be captured by attacking them.";
	public static final int MAX_HP = 500;
	/**
	 * default constructor <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 */
	public Base()
	{
		POINTS = 200;
		hp = MAX_HP;
	}
	/**
	 * toString method <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the letter "B" for base
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "B";
	}
	/**
	 * Gets the Maximum HP of a base <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the max HP of a base
	 * @see model.obstacle.HP#getBASE_HP()
	 */
	@Override
	public int getBASE_HP()
	{
		return MAX_HP;
	}
}
