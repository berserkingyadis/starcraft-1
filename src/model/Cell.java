package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.swing.ImageIcon;

import model.obstacle.Base;
import model.obstacle.Marine;
import model.obstacle.Obstacle;
import model.obstacle.ObstacleType;
import model.obstacle.Zealot;
import model.obstacle.Zergling;
import model.terrain.BigMountain;
import model.terrain.Building;
import model.terrain.Forest;
import model.terrain.Plain;
import model.terrain.SmallMountain;
import model.terrain.Street;
import model.terrain.Terrain;
import model.terrain.TerrainType;



/**
 * A cell on the map<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class Cell implements Serializable
{
	private static final long serialVersionUID = 1L;
	public static final int CELL_WIDTH=50;
	public static final int CELL_HEIGHT=50;
	
	private Obstacle obstacle;
	private Terrain terrain;
	
	/**
	 * default constuctor <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param ter - the Terrain type on this cell
	 * @param obs - the Obstacle type on this cell
	 */
	public Cell(TerrainType ter, ObstacleType obs)
	{
		switch(ter)
		{
			case Forest:
				terrain = new Forest();
			break;
			case BigMountain:
				terrain = new BigMountain();
			break;
			case SmallMountain:
				terrain = new SmallMountain();
			break;
			case Building:
				terrain = new Building();
			break;
			case Street:
				terrain = new Street();
			break;
			case Plain:
				terrain = new Plain();
			break;
			default:
				System.out.println("This shouldn't happen");
		}
		if(obs==null)
		{
			obstacle = null;
		} else
		{
			switch(obs)
			{
				case Marine:
					obstacle = new Marine();
				break;
				case Zergling:
					obstacle = new Zergling();
				break;
				case Zealot:
					obstacle = new Zealot();
				break;
				case Base:
					obstacle = new Base();
				break;
				default:
					System.out.println("This shouldn't happen");
			}
		}
	}
	/**
	 * Gets the image to display for the cell <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the image to display
	 */
	public ImageIcon getDisplay()
	{
//		return obstacle.getImage();
		ImageIcon terImg = terrain.getImage();
		ImageIcon obsImg = obstacle.getImage();
		if(obsImg == null)
			return terImg;
		else
		{
if(Game.DEBUG)
{
	if(terImg.getIconWidth() < 0)
		System.out.println("terrain width is neg");
	if(terImg.getIconHeight() < 0)
		System.out.println("terrain height is neg");
}
			BufferedImage buffImg = new BufferedImage(CELL_WIDTH, CELL_HEIGHT, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = buffImg.createGraphics();
			g2.drawImage(terImg.getImage(), 0, 0, terImg.getImageObserver());
			g2.drawImage(obsImg.getImage(), 0, 0, obsImg.getImageObserver());
			
			return new ImageIcon(buffImg);
		}
	}
	
	/**
	 * setter for the obstacle <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param obstacle
	 */
	public void setObstacle(Obstacle obstacle)
	{
		this.obstacle = obstacle;
	}
	/**
	 * Obstacles getter <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the cell's obstacle
	 */
	public Obstacle getObstacle()
	{
		return obstacle;
	}
	/**
	 * Terrain's getter <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the cell's terrain
	 */
	public Terrain getTerrain()
	{
		return terrain;
	}
	/**
	 * returns the obstacle or terrain's toString <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		if(obstacle==null)
			return "";
		return obstacle.toString();
//		if(terrain==null)
//			return "";
//		return terrain.toString();
	}
}
