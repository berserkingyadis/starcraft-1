package model.terrain;

import java.util.Random;

import javax.swing.ImageIcon;

/**
 * A Big Mountain<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class BigMountain extends Mountain
{
	private static final long serialVersionUID = 1L;
	public static final String DESCRIPTION = "Provides a large advantage to units when attacking. Large units cannot move onto mountatin";
	String[] bigMPictures = {
			"Images\\Terrain\\MountainGifs\\MountainB.gif",
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
	public BigMountain()
	{
		statAvantage = 1.9;
		Random gen = new Random();
		int pic = gen.nextInt(bigMPictures.length);
		displayImage = new ImageIcon(bigMPictures[pic]);
	}
	/**
	 * overridden toString <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the letter "L"
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "L";
	}
}
