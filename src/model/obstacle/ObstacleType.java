package model.obstacle;

import java.util.Random;

/**
 * A list of the possible obstacles<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public enum ObstacleType 
{
	Zergling,
	Marine,
	Zealot,
	Base;
	
	/**
	 * gets a random ObstacleType <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - a radom ObstacleType
	 */
	public static ObstacleType getType(Obstacle obs)
	{
		ObstacleType obsType = null;
		if(obs instanceof Zergling)
			obsType = Zergling;
		else if (obs instanceof Marine)
			obsType = Marine;
		else if (obs instanceof Zealot)
			obsType = Zealot;
		else if(obs instanceof Base)
			obsType = Base;
		
		return obsType;
	}
	public static ObstacleType getRandom()
	{
		ObstacleType type = ObstacleType.Zergling;
		Random gen = new Random();
		switch(gen.nextInt(4))
		{
		case 0:
			type = ObstacleType.Zergling;
			break;
		case 1:
			type = ObstacleType.Marine;
			break;
		case 2:
			type = ObstacleType.Zealot;
			break;
		case 3:
			type = ObstacleType.Base;
			break;
		}
		return type;
	}
	/**
	 * gets a random unit <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - a random Unit
	 */
	public static ObstacleType getRandomUnit()
	{
		ObstacleType type = ObstacleType.Zergling;
		Random gen = new Random();
		switch(gen.nextInt(3))
		{
		case 0:
			type = ObstacleType.Zergling;
			break;
		case 1:
			type = ObstacleType.Marine;
			break;
		case 2:
			type = ObstacleType.Zealot;
			break;
		}
		return type;
	}
}
