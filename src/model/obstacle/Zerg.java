package model.obstacle;

public abstract class Zerg extends Unit
{
	private static final int BASE_SPEED=5,
							BASE_ATTACK=70,
							BASE_ACCURACY=90,
							BASE_MAX_RANGE=1,
							BASE_MIN_RANGE=1,
							BASE_DEFENSE=30;
	/**
	 * default constructor <br>
	 * sets the default stats <br>      
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 */
	public Zerg()
	{
		super();
		BASE_HP = 80;
		hp = BASE_HP;
		speed = BASE_SPEED;
		attack = BASE_ATTACK;
		accuracy = BASE_ACCURACY;
		maxRange = BASE_MAX_RANGE;
		minRange = BASE_MIN_RANGE;
		defense = BASE_DEFENSE;
	}
	/**
	 * gets the base HP <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the base HP
	 * @see model.obstacle.HP#getBASE_HP()
	 */
	@Override
	public int getBASE_HP()
	{
		return BASE_HP;
	}
}
