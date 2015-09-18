package model.terrain;

import java.util.Random;

import javax.swing.ImageIcon;

/**
 * A Plain<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class Plain extends Terrain 
{
	private static final long serialVersionUID = 1L;
	public static final String DESCRIPTION = "Does not affect a unit's ability to move or fire";
	private String picturesDirectory = "Images\\Terrain\\PlainGifs";
	private String[] plainPictures = { "\\plain01.gif", "\\plain02.gif",
			"\\plain03.gif", "\\plain04.gif", "\\plain05.gif", "\\plain06.gif",
			"\\plain07.gif", "\\plain08.gif", "\\plain09.gif", "\\plain10.gif",

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
	public Plain() {
		statAvantage = 1.0;
		Random gen = new Random();
		int picIndex = gen.nextInt(plainPictures.length);
		if (!new java.io.File(picturesDirectory + plainPictures[picIndex]).isFile())
			System.out.println("Plain image is not a file");
		displayImage = new ImageIcon(picturesDirectory + plainPictures[picIndex]);
	}
	
	/**
	 * overridden toString <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the letter "P"
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return "P";
	}
}
