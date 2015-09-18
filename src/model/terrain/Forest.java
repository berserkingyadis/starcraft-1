package model.terrain;

import java.util.Random;

import javax.swing.ImageIcon;

/**
 * A Forest<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class Forest extends Terrain
{
	private static final long serialVersionUID = 1L;
	public static final String DESCRIPTION = "Unit's range are shorten when firing, but accuracy is decresed for units firing upon them.";
	private String picturesDirectory = "Images\\Terrain\\ForestGifs";
	private String[] forestPictures =
	{ "\\forest01.gif", "\\forest02.gif", "\\forest03.gif"
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
	public Forest()
	{
		statAvantage = 1.3;
		Random gen = new Random();
		int pic = gen.nextInt(forestPictures.length);
		displayImage = new ImageIcon(picturesDirectory+forestPictures[pic]);
	}
	
	/**
	 * overridden toString <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the letter "F"
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "F";
	}
}
