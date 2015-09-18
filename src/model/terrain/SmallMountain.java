package model.terrain;

import java.util.Random;

import javax.swing.ImageIcon;

/**
 * A small Mountain<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class SmallMountain extends Mountain
{
	private static final long serialVersionUID = 1L;
	public static final String DESCRIPTION = "Provides a small advantage to units when attacking. Large units cannot move onto mountatin";
	String[] smallMPictures = {
			"Images\\Terrain\\MountainGifs\\MountainA.gif",
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
	public SmallMountain()
	{
		statAvantage = 1.7;
		Random gen = new Random();
		int pic = gen.nextInt(smallMPictures.length);
		displayImage = new ImageIcon(smallMPictures[pic]);
	}
	
	/**
	 * overridden toString <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the letter "T"
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "T";
	}
}
