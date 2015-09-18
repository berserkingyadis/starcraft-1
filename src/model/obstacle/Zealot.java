package model.obstacle;

import java.util.Random;

import javax.swing.ImageIcon;

/**
 * The basic protoss unit<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class Zealot extends Protoss
{
	private static final long serialVersionUID = 1L;
	String[] zealotPictures = {
			"Images\\Obstacles\\Units\\Protoss\\Zealot\\ZealotMoveLeft.gif"
			};
	/**
	 * default constructor <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 */
	public Zealot()
	{
		POINTS = 50;
		Random gen = new Random();
		int pic = gen.nextInt(zealotPictures.length);
		displayImage = new ImageIcon(zealotPictures[pic]);
	}
	/**
	 * overridden toString <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the letter "E"
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "E";
	}
	
}
