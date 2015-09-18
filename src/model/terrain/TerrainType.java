package model.terrain;

import java.util.Random;

/**
 * The possible TerrainTypes<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public enum TerrainType 
{
	Forest,
	BigMountain,
	SmallMountain,
	Building,
	Street,
	Plain;
	
	/**
	 * Gets and random TerrainType <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - a random TerrainType
	 */
	public static TerrainType getRandom()
	{
		TerrainType type = TerrainType.Forest;
		Random gen = new Random();
		switch(gen.nextInt(5))
		{
		case 0:
			type = TerrainType.Forest;
			break;
		case 1:
			type = TerrainType.BigMountain;
			break;
		case 2:
			type = TerrainType.SmallMountain;
			break;
		case 3:
			type = TerrainType.Building;
			break;
		case 4:
			type = TerrainType.Street;
			break;
		case 5:
			type = TerrainType.Plain;
			break;
		}
		return type;
	}
}
