package model.obstacle;

import java.util.Random;

import javax.swing.ImageIcon;

/**
 * The basic unit for a Terran<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class Marine extends Terran 
{
	private String picturesDirectory = "Images\\Obstacles\\UnitImages\\Terran";
	private String[] marinePictures = {
			"\\MarineMoveHorizontalLeft.gif",
			"\\MarineMoveHorizontalRight.gif",
			"\\MarineMoveVerticalDown.gif",
			"\\MarineMoveVerticalUp.gif"};

	/**
	 * Default Constructor <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 */
	public Marine() 
	{
		POINTS = 50;
		Random gen = new Random();
		int picIndex = gen.nextInt(marinePictures.length);
		if (!new java.io.File(picturesDirectory+marinePictures[picIndex]).isFile())
			System.out.println("Marine image: "+picturesDirectory+marinePictures[picIndex]+" is not a file");
		displayImage = new ImageIcon(picturesDirectory+marinePictures[picIndex]);
	}

	/**
	 * overridden toString <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the letter "M"
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return "M";
	}

}
