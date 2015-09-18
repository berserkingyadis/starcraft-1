package model;

import model.obstacle.Base;
import model.obstacle.HP;
import model.obstacle.Obstacle;
import model.obstacle.Unit;
import model.terrain.BigMountain;
import model.terrain.Building;
import model.terrain.Forest;
import model.terrain.Plain;
import model.terrain.SmallMountain;
import model.terrain.Street;
import model.terrain.Terrain;

/**
 * Used to pass the stats of a cell back to the View<br>
 *
 * <hr>
 * Date created: May 16, 2010<br>
 * Date last modified: June 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class StatsHolder
{
	private boolean isObstacle,
					isTerrain,
					isBase,
					isUnit;
	private String info;
	private int hp,
				maxHp,
				accuracy,
				attack,
				defense,
				minRange,
				maxRange,
				speed;
	private double terAdvantage;
	
	/**
	 * constuctor <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param obj - the object to hold
	 */
	public StatsHolder(Object obj)
	{
		init();
		if( obj instanceof Obstacle)
		{
			setObstacle((Obstacle) obj);
		} else if( obj instanceof Terrain)
		{
			setTerrain((Terrain) obj);
		} else
			System.out.println("Object passed was not an obstacle or terrain");
			
	}
	/**
	 * Initalizes the variables <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 */
	private void init()
	{
		info = "Not initalized";
		isObstacle = false;
		isTerrain = false;
		hp = -1;
		maxHp = -1;
		accuracy = -1;
		attack = -1;
		defense = -1;
		minRange = -1;
		maxRange = -1;
		speed = -1;
		terAdvantage = -1;
	}
	/**
	 * sets the instance variables to the statistics of the obstacle <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param obs - the obstacle to set this StatHolder to
	 */
	private void setObstacle(Obstacle obs)
	{
		isObstacle = true;
		hp = ((HP)obs).getHp();
		maxHp = ((HP)obs).getBASE_HP();
		info = Base.DESCRIPTION;
		if(obs instanceof Unit)
		{
			isUnit = true;
			accuracy = ((Unit)obs).getAccuracy();
			attack = ((Unit)obs).getAttack();
			defense =((Unit)obs).getDefense();
			minRange = ((Unit)obs).getMinRange();
			maxRange = ((Unit)obs).getMaxRange();
			speed = ((Unit)obs).getSpeed();
		} else if(obs instanceof Base)
		{
			isBase = true;
		}
	}
	/**
	 * sets the instance variables to the statistics of the terrain <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param ter - the terrain to set this StatHolder to
	 */
	private void setTerrain(Terrain ter)
	{
		isTerrain = true;
		terAdvantage = ter.getStatAdvantage();
		if(ter instanceof Plain)
		{
			info = Plain.DESCRIPTION;
		} else if(ter instanceof BigMountain)
		{
			info = BigMountain.DESCRIPTION;
		} else if(ter instanceof SmallMountain)
		{
			info = SmallMountain.DESCRIPTION;
		} else if(ter instanceof Street)
		{
			info = Street.DESCRIPTION;
		} else if(ter instanceof Forest)
		{
			info = Forest.DESCRIPTION;
		} else if(ter instanceof Building)
		{
			info = Building.DESCRIPTION;
		}
	}
	/**
	 * GETTERS AND SETTERS
	 */
	public boolean isObstacle()
	{
		return isObstacle;
	}
	public boolean isTerrain()
	{
		return isTerrain;
	}
	public boolean isBase()
	{
		return isBase;
	}
	public boolean isUnit()
	{
		return isUnit;
	}
	public String getInfo()
	{
		return info;
	}
	public int getHp()
	{
		return hp;
	}
	public int getAccuracy()
	{
		return accuracy;
	}
	public int getAttack()
	{
		return attack;
	}
	public int getDefense()
	{
		return defense;
	}
	public int getMinRange()
	{
		return minRange;
	}
	public int getMaxRange()
	{
		return maxRange;
	}
	public int getSpeed()
	{
		return speed;
	}
	public int getMaxHp()
	{
		return maxHp;
	}
	public void setMaxHp(int maxHp)
	{
		this.maxHp = maxHp;
	}
	/**
	 * overridden toString method <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the instance variables, formatted
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		if(isObstacle)
		{
			if(isBase)
			{
				sb.append("Base");
				sb.append("--HP: ");
				sb.append(hp);
				sb.append("Max HP: ");
				sb.append(maxHp);
			}
			if(isUnit)
			{
				sb.append("Unit");
				sb.append("--HP: ");
				sb.append(hp);
				sb.append("Max HP: ");
				sb.append(maxHp);
				sb.append("  Acc: ");
				sb.append(accuracy);
				sb.append("  Def: ");
				sb.append(defense);
				sb.append("\n  Min Ran:");
				sb.append(minRange);
				sb.append("  Max Ran: ");
				sb.append(maxRange);
				sb.append("  Spd: ");
				sb.append(speed);
			}
		}
		if(isTerrain)
		{
			sb.append("Terrain");
			sb.append("--Stat Advantage: ");
			sb.append(terAdvantage);
		}
//		sb.append(info);
		return sb.toString();
	}
}
