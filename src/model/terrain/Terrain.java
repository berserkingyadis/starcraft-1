package model.terrain;

import java.io.IOException;
import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * An abstract Terrain<br>
 * 
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * 
 * @author Glen Watson
 */
public abstract class Terrain implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected ImageIcon displayImage;
	protected double statAvantage;
	

	
	/**
	 * gets the displayable image representing the terrain <br>
	 * 
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 * 
	 * <hr>
	 * 
	 * @return - an ImageIcon of the terrain
	 */
	public ImageIcon getImage()
	{
		return displayImage;
	}
	
	/**
	 * gets the statAvantage of this terrain <br>
	 * 
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 * 
	 * <hr>
	 * 
	 * @return - the stat advantage
	 */
	public double getStatAdvantage()
	{
		return statAvantage;
	}
	
}
