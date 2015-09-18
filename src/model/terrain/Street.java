package model.terrain;

import java.util.Random;

import javax.swing.ImageIcon;

/**
 * A Street<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class Street extends Terrain
{
	private static final long serialVersionUID = 1L;
	public static final String DESCRIPTION = "Units can move farther on streets.";
	private String picturesDirectory = "Images\\Terrain\\StreetGifs";
	private String[] streetPictures = { "\\street01.gif", "\\street02.gif",
			"\\street03.gif", "\\street04.gif", "\\street05.gif", "\\street06.gif",
			"\\street07.gif", "\\street08.gif", "\\street09.gif", "\\street10.gif",
			"\\street11.gif", "\\street12.gif",
			"\\street13.gif", "\\street14.gif", "\\street15.gif", "\\street16.gif",
			"\\street17.gif", "\\street18.gif", "\\street19.gif", "\\street20.gif",
			"\\street21.gif", "\\street22.gif",
			"\\street23.gif", "\\street24.gif", "\\street25.gif", "\\street26.gif",
			"\\street27.gif", "\\street28.gif", "\\street29.gif", "\\street30.gif",
			"\\street31.gif", "\\street32.gif",
			"\\street33.gif", "\\street34.gif", "\\street35.gif", "\\street36.gif",
			"\\street37.gif", "\\street38.gif", "\\street39.gif", "\\street40.gif",
			
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
	public Street()
	{
		statAvantage = 1.1;
		Random gen = new Random();
		int picIndex = gen.nextInt(streetPictures.length);
		if (!new java.io.File(picturesDirectory + streetPictures[picIndex]).isFile())
			System.out.println("Plain image is not a file");
		displayImage = new ImageIcon(picturesDirectory + streetPictures[picIndex]);
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
		return "S";
	}
}
