package model.terrain;

import java.util.Random;

import javax.swing.ImageIcon;

/**
 * A Building<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class Building extends Terrain
{
	private static final long serialVersionUID = 1L;
	public static final String DESCRIPTION = "These can be captured by occuping them.";
	String[] buildingPictures = {
			"Images\\Terrain\\CityGifs\\city.gif"
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
	public Building()
	{
		statAvantage = 1.5;
		Random gen = new Random();
		int pic = gen.nextInt(buildingPictures.length);
		displayImage = new ImageIcon(buildingPictures[pic]);
	}
	/**
	 * overridden toString <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the letter "B"
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "B";
	}
}
