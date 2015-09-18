package model.obstacle;

import java.util.Random;

import javax.swing.ImageIcon;

/**
 * The basic Zerg unit<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class Zergling extends Zerg
{
	String[] zerglingPictures = {
			"Images\\Obstacles\\Units\\Zerg\\Zergling\\ZealotMoveLeft.gif"
			};
	/**
	 * default contructor <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 */
	public Zergling()
	{
		POINTS = 50;
		Random gen = new Random();
		int pic = gen.nextInt(zerglingPictures.length);
		displayImage = new ImageIcon(zerglingPictures[pic]);
	}
	/**
	 * overridden toString <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the letter "Z"
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "Z";
	}
}
