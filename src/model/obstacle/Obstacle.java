package model.obstacle;

import java.io.IOException;
import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * An obstacle in the game<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public abstract class Obstacle implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected ImageIcon displayImage;
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException
	{
		System.out.println(displayImage.toString());
		out.writeObject(displayImage.toString());
	}
	
	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException
	{
		String file = (String)in.readObject();
		displayImage = new ImageIcon(file);
	}
	/**
	 * gets the displayable image for the obstacle <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - an ImageIcon of the obstacle
	 */
	public ImageIcon getImage()
	{
		return displayImage;
	}
}
