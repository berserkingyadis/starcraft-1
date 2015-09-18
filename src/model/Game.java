package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import model.obstacle.Base;
import model.obstacle.HP;
import model.obstacle.Unit;
import model.terrain.Forest;
import model.terrain.Terrain;
import exceptions.CantMoveThereException;
import exceptions.InactiveCellException;
import exceptions.SelectedCellCantAttackException;
import exceptions.SelectedCellCantMoveException;
import exceptions.SelectedCellDoesNotBelongToCurrentPlayerException;
import exceptions.SelectedCellHasAlreadyAttackedException;
import exceptions.SelectedCellHasAlreadyMovedException;

/**
 * A Starcraft Tatics Game<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class Game implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final boolean DEBUG = false;
	
	private Map map;
	private GameState gameState;
	private int diedX = -1;
	private int diedY = -1;
	
	/**
	 * copy constructor <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param g - the game to copy
	 */
	public Game(Game g)
	{
		map = g.map;
		gameState = g.gameState;
	}
	/**
	 * map contructor <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param m - the map to use
	 */
	public Game(Map m, int numPlayers)
	{
		map = m;
		gameState = new GameState(numPlayers);
	}
	/**
	 * returns true if the number of players is 1 <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - if the game is over
	 */
	public boolean isOver()
	{
		if(gameState.getNumOfPlayers() == 1)
		{
			return true;
		}
		return false;
	}
	/**
	 * sets the selected coords <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param x - x-coord selected
	 * @param y - y-coord selected
	 */
	public void setSelected(int x, int y)
	{
		if(gameState.getSelectedCellX() != x || gameState.getSelectedCellY() != y)
		{
			if(map.getCellAt(gameState.getSelectedCellX(), gameState.getSelectedCellY()).getObstacle() != null &&
					map.getCellAt(gameState.getSelectedCellX(), gameState.getSelectedCellY()).getObstacle() instanceof Unit)
			{
				if(gameState.selectedHasAttacked() || gameState.selectedHasMoved())
				{
					((Unit) map.getCellAt(gameState.getSelectedCellX(), gameState.getSelectedCellY()).getObstacle()).setActive(false);
				}
			}
			if(map.getCellAt(x, y).getObstacle() != null && map.getCellAt(x, y).getObstacle() instanceof Unit)
			{
				if(((Unit) map.getCellAt(x, y).getObstacle()).isActive())
				{
					gameState.setSelectedHasMoved(false);
					gameState.setSelectedHasAttacked(false);
				}
				else
				{
					gameState.setSelectedHasMoved(true);
					gameState.setSelectedHasAttacked(true);
				}
			}
			gameState.setSelectedCell(x, y);
			diedX = -1;
			diedY = -1;
		}
		
	}
	/**
	 * gets the cell at the specified coord <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param row - the row of the cell
	 * @param col - the columns of the cell
	 * @return - the cell at that coord
	 */
	public Cell getCellAt(int row, int col)
	{
		return map.getCellAt(row, col);
	}
	/**
	 * getter for the map <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the map being used
	 */
	public Map getMap()
	{
		return map;
	}
	
	/**
	 * setter for the map <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param map - the map to use
	 */
	public void setMap(Map map)
	{
		this.map = map;
	}
	/**
	 * getter for the gamestate <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the gamestate being used
	 */
	public GameState getGameState()
	{
		return gameState;
	}
	/**
	 * setter for the gameState <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param gameState - the GameState to use
	 */
	public void setGameState(GameState gameState)
	{
		this.gameState = gameState;
	}
	/**
	 * calls the maps and gamestates toString <br>        
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
		return map.toString() + gameState.toString();
	}
	/**
	 * returns the available moves of the selected cell <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - a set of available moves
	 * @throws SelectedCellCantMoveException - if the selected cell isn't an unit
	 */
	public Set<Point> getAvailableMoves() throws SelectedCellCantMoveException
	{
		if(!(map.getCellAt(gameState.getSelectedCellX(), gameState.getSelectedCellY()).getObstacle() instanceof Unit))
		{
			throw new SelectedCellCantMoveException();
		}
		//store all of the cells the unit can move in a set
		Set<Point> moveableCells = new HashSet<Point>();
		
		for( int x=0; x<map.getWidth(); x++)
		{
			for( int y=0; y<map.getHeight(); y++)
			{
				//does the calculations for movement
				int moveRange = ((Unit) map.getCellAt(gameState.getSelectedCellX(), gameState.getSelectedCellY()).getObstacle()).getSpeed();
//				int moveRange = 3;
				if(Math.sqrt(Math.pow(x - gameState.getSelectedCellX(),2) + Math.pow(y - gameState.getSelectedCellY(),2)) <= moveRange)
				{
					if(map.getCellAt(x, y).getObstacle() == null)
					{
						moveableCells.add(new Point(x,y));
					}
				}
			}
		}
		
//		System.out.println("List of cells unit can move to:");
//		for (Point point : moveableCells)
//		{
//			System.out.println(point.getX()+", "+point.getY());
//		}
//		System.out.println();
		return moveableCells;
	}
	/**
	 * returns the available attack possibilities of the selected cell <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - a set of available attacks
	 * @throws SelectedCellCantMoveException - if the selected cell isn't an unit
	 */
	public Set<Point> getAttackableUnits() throws SelectedCellCantAttackException
	{
		if(!(getCellAt(getGameState().getSelectedCellX(), getGameState().getSelectedCellY()).getObstacle() instanceof Unit))
		{
			throw new SelectedCellCantAttackException();
		}
		//store the attackable units in a set
		Set<Point> attackableUnits = new HashSet<Point>();
		
		for( int x=0; x<map.getWidth(); x++)
		{
			for( int y=0; y<map.getHeight(); y++)
			{
				
				//does the calculations for attack range
				int maxRange = ((Unit) map.getCellAt(gameState.getSelectedCellX(), gameState.getSelectedCellY()).getObstacle()).getMaxRange();
				int minRange = ((Unit) map.getCellAt(gameState.getSelectedCellX(), gameState.getSelectedCellY()).getObstacle()).getMinRange();
				
				
				if(Math.sqrt(Math.pow(x - gameState.getSelectedCellX(),2) + Math.pow(y - gameState.getSelectedCellY(),2)) <= maxRange && 
						Math.sqrt(Math.pow(x - gameState.getSelectedCellX(),2) + Math.pow(y - gameState.getSelectedCellY(),2)) >= minRange)
				{
					//add to the set if it's an enemy unit
					if(map.getPlayerPieces()[x][y] != gameState.getTurn() && map.getPlayerPieces()[x][y] != -1)
					{
						attackableUnits.add(new Point(x,y));
					}
				}
				
			}
		}
		
//		System.out.println("List of attackable cells(enemy units):");
//		for (Point point : attackableUnits)
//		{
//			System.out.println(point.getX()+", "+point.getY());
//		}
//		System.out.println();
		return attackableUnits;
	}
	/**
	 * trys to move the unit on the map <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param xMove - the x-coord to move to
	 * @param yMove - the y-coord to move to
	 * @throws SelectedCellCantMoveException - if the selected cell can't move
	 * @throws InactiveCellException - if the selected cell has already had it's turn
	 * @throws SelectedCellHasAlreadyMovedException - if the selected cell as already moved for that turn
	 * @throws SelectedCellDoesNotBelongToCurrentPlayerException - if the selected cell isn't that's palyer's unit
	 * @throws CantMoveThereException - if the move is out of the unit's movement range
	 */
	public void moveUnit( int xMove, int yMove ) throws SelectedCellCantMoveException, InactiveCellException, SelectedCellHasAlreadyMovedException, SelectedCellDoesNotBelongToCurrentPlayerException, CantMoveThereException
	{	
		if(!(map.getCellAt(gameState.getSelectedCellX(), gameState.getSelectedCellY()).getObstacle() instanceof Unit))
		{
			System.out.println("X: "+gameState.getSelectedCellX()+" Y: "+gameState.getSelectedCellY());
			System.out.println(map.getCellAt(gameState.getSelectedCellX(), gameState.getSelectedCellY()).getObstacle());
			System.out.println(map);
			throw new SelectedCellCantMoveException();
		}
		if(!((Unit) map.getCellAt(gameState.getSelectedCellX(), gameState.getSelectedCellY()).getObstacle()).isActive())
		{
			throw new InactiveCellException();
		}
		if(map.getPlayerPieces()[gameState.getSelectedCellX()][gameState.getSelectedCellY()] != gameState.getTurn())
		{
			throw new SelectedCellDoesNotBelongToCurrentPlayerException();
		}
		if(gameState.selectedHasMoved()==true)
		{
			throw new SelectedCellHasAlreadyMovedException();
		}
		boolean hasMoved = false;
		for (Point point : getAvailableMoves())
		{
			if(point.getX() == xMove && point.getY() == yMove)
			{
				
				map.moveCell(gameState.getSelectedCellX(), gameState.getSelectedCellY(), xMove, yMove);
				gameState.setSelectedCell(xMove, yMove);
				gameState.setSelectedHasMoved(true);
				hasMoved = true;
				break;
			}
		}
		if(!hasMoved)
		{
			throw new CantMoveThereException();
		}
	}
	/**
	 * trys to attack a cell <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param xMove - the x-coord to move to
	 * @param yMove - the y-coord to move to
	 * @throws InactiveCellException - if the selected cell has already had it's turn
	 * @throws SelectedCellCantAttackException - if the selected cell isn't a unit
	 * @throws SelectedCellHasAlreadyAttackedException - if the cell has already attacked for that turn
	 * @throws CantMoveThereException - if the move is out of the unit's movement range
	 */
	public void attackUnit(int xAttack, int yAttack) throws SelectedCellCantAttackException, InactiveCellException, SelectedCellHasAlreadyAttackedException, SelectedCellDoesNotBelongToCurrentPlayerException
	{
		if(!(map.getCellAt(gameState.getSelectedCellX(), gameState.getSelectedCellY()).getObstacle() instanceof Unit))
		{
			throw new SelectedCellCantAttackException();
		}
		if(map.getPlayerPieces()[gameState.getSelectedCellX()][gameState.getSelectedCellY()] != gameState.getTurn())
		{
			throw new SelectedCellDoesNotBelongToCurrentPlayerException();
		}
		
		Unit selectedUnit = (Unit) map.getCellAt(gameState.getSelectedCellX(), gameState.getSelectedCellY()).getObstacle();
//		HP attackedUnit = (HP) map.getCellAt(xAttack, yAttack).getObstacle();
		if(! selectedUnit.isActive())
		{
			throw new InactiveCellException();
		}
		if(gameState.selectedHasAttacked()==true)
		{
			throw new SelectedCellHasAlreadyAttackedException();
		}
		
		for (Point point : getAttackableUnits())
		{
			if(point.getX() == xAttack && point.getY() == yAttack)
			{
				calcAttack(gameState.getSelectedCellX(), gameState.getSelectedCellY(), xAttack, yAttack);
				gameState.setSelectedHasAttacked(true);
				break;
			}
		}
	}
	/**
	 * Calculates the attack damage of the exchange <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param xSelected - the attacking cell's x-coord
	 * @param ySelected - the attacking cell's y-coord
	 * @param xAttack - the defending cell's x-coord
	 * @param yAttack - the defending cell's y-coord
	 */
	private void calcAttack(int xSelected, int ySelected, int xAttack, int yAttack)
	{
		Unit attacker = (Unit) map.getCellAt(xSelected, ySelected).getObstacle();
		HP defender = (HP) map.getCellAt(xAttack, yAttack).getObstacle();
		Terrain attackerTerrain = map.getCellAt(xSelected, ySelected).getTerrain();
		Terrain defenderTerrain = map.getCellAt(xAttack, yAttack).getTerrain();
		
		int accOutOf = 100;
		if(defenderTerrain instanceof Forest)
		{
			accOutOf = 120;
		}
		if(new Random().nextInt(accOutOf) < attacker.getAccuracy())
		{
			int defendersDefense = 0;
			if(defender instanceof Unit)
				defendersDefense = (int) (((Unit) defender).getDefense()*defenderTerrain.getStatAdvantage());
			
			int attackersAttack = (int) (attacker.getAttack()*attackerTerrain.getStatAdvantage());
			int damageDone = attackersAttack - defendersDefense;
			//if the damage is negative
			if(damageDone>0)
				defender.setHp(defender.getHp() - damageDone);
if(DEBUG)
{
System.out.println("HIT!");
System.out.println("defender's HP: "+defender.getHp());
}
			if(defender.getHp() <= 0)
			{
if(DEBUG)
System.out.println("KILLED!");
				gameState.addToScore(gameState.getTurn(), defender.getPOINTS());
				diedX = xAttack; diedY = yAttack;
				if(defender instanceof Base)
					playerEliminated(xAttack, yAttack);
				map.getCellAt(xAttack, yAttack).setObstacle(null);
				map.getPlayerPieces()[xAttack][yAttack] = -1;
			}
		} else
		{
			if(DEBUG)
			System.out.println("MISSED!");
		}
	}
	/**
	 * eliminates a player from the game <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param xAttack - the player eliminated x-coord
	 * @param yAttack - the player eliminated y-coord
	 */
	private void playerEliminated(int xAttack, int yAttack)
	{
		int eliminatedPlayer = map.getPlayerPieces()[xAttack][yAttack];
		
		map.playerEliminated(eliminatedPlayer);
		gameState.playerEliminated(eliminatedPlayer);
if(DEBUG)
System.out.println("Player "+eliminatedPlayer+" eliminated!");
		
	}
	/**
	 * returns true if there is one player left <br>
	 *
	 * <hr>
	 * Date created: Jun 7, 2010 <br>
	 * Date last modified: Jun 7, 2010 <br>
	 *
	 * <hr>
	 * @return - if the game is over or not
	 */
	public boolean isGameOver()
	{
		if(gameState.getNumOfPlayers() == 1)
		{
			return true;
		}
		return false;
	}
	/**
	 * adds 500 to the winner's score <br>
	 * notifies the view <br>
	 *
	 * <hr>
	 * Date created: Jun 7, 2010 <br>
	 * Date last modified: Jun 7, 2010 <br>
	 *
	 * <hr>
	 */
	public void gameOver()
	{
		gameState.addToScore(0, 500);
	}
	/**
	 * Ends the current turn <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 */
	public void endTurn()
	{
		map.endTurn(gameState.getTurn());
		gameState.endTurn();
	}
	/**
	 * Gets the x-coord of the killed unit <br>
	 *
	 * <hr>
	 * Date created: Jun 8, 2010 <br>
	 * Date last modified: Jun 8, 2010 <br>
	 *
	 * <hr>
	 * @return - the x-coord of the killed unit
	 */
	public int getDiedX()
	{
		return diedX;
	}
	/**
	 * Gets the y-coord of the killed unit <br>
	 *
	 * <hr>
	 * Date created: Jun 8, 2010 <br>
	 * Date last modified: Jun 8, 2010 <br>
	 *
	 * <hr>
	 * @return - the y-coord of the killed unit
	 */
	public int getDiedY()
	{
		return diedY;
	}
	/**
	 * if a HP (Unit/Base) died last turn <br>
	 *
	 * <hr>
	 * Date created: Jun 8, 2010 <br>
	 * Date last modified: Jun 8, 2010 <br>
	 *
	 * <hr>
	 * @return - true if both diedX and diedY both equal -1
	 */
	public boolean didHpDie()
	{
		if(diedX != -1 && diedY != -1)
		{
			return true;
		}
		return false;
	}
}
