package model;

/**
 * A point of integers<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class Point
{
	private int x;
	private int y;
	/**
	 * The only contructor <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param x - the x-coord
	 * @param y - the y-coord
	 */
	public Point(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
	}
	/**
	 * x's getter <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the stored X
	 */
	public int getX()
	{
		return x;
	}
	/**
	 * X's setter <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param x - the new X
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	/**
	 * Y's getter <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the stored Y
	 */
	public int getY()
	{
		return y;
	}
	/**
	 * Y's setter <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param y - the new Y
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	
}
