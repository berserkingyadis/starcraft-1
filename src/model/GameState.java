package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jws.soap.SOAPBinding;

public class GameState implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int turn,
				selectedCellX,
				selectedCellY;
	private boolean selectedHasMoved,
					selectedHasAttacked;
	private List<Integer> playerNums;
	private HashMap<Integer, Integer> scores;
	
	/**
	 * constructor <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param numPlayers - the number of players in this game
	 */
	public GameState(int numPlayers)
	{
		turn = 0;
		selectedCellX = 0;
		selectedCellY = 0;
		selectedHasMoved = false;
		selectedHasAttacked = false;
		
		playerNums = new ArrayList<Integer>();
		scores = new HashMap<Integer, Integer>();
		for(int i=0; i<numPlayers; i++)
		{
			playerNums.add(i);
			scores.put(i, 0);
		}
	}
	
	/**
	 * returns the winner <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return
	 */
	public int getWinner()
	{
		if(playerNums.size() == 1)
		{
			return playerNums.get(0);
		} else
		{
			
		}
		return 0;
	}
	/**
	 * ends the current turn <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 */
	public void endTurn()
	{
		selectedHasAttacked = false;
		selectedHasMoved = false;
		
		turn = (turn + 1) % playerNums.size();
	}
	/**
	 * Gets the scores <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the player's scores
	 */
	public HashMap<Integer, Integer> getScores()
	{
		return scores;
	}
	/**
	 * gets a specific player's score <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param player - which player's score to get
	 * @return - the specific player's score
	 */
	public int getScore(int player)
	{
		return scores.get(player);
	}
	/**
	 * Adds a number to a player's score <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param player - the player to add to
	 * @param score - the number to increase their score by
	 */
	public void addToScore(int player, int score)
	{
		scores.put(player, scores.get(player) + score);
	}
	/**
	 * gets the number of players in the game <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the number of players
	 */
	public int getNumOfPlayers()
	{
		return playerNums.size();
	}
	/**
	 * removes a player from the game but keeps their score <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param eliminatedPlayer - the player number elminated
	 */
	public void playerEliminated(int eliminatedPlayer)
	{
		playerNums.remove(eliminatedPlayer);
	}
	/**
	 * returns who's turn it is <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - player number
	 */
	public int getTurn()
	{				
		return playerNums.get(turn);
	}
	/**
	 * getter for the selected x-coord <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the x-coord of the selected cell
	 */
	public int getSelectedCellX()
	{
		return selectedCellX;
	}
	/**
	 * setter for the selected x-coord <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param selectedCellX - sets the selected x-coord
	 */
	public void setSelectedCellX(int selectedCellX)
	{
		this.selectedCellX = selectedCellX;
	}
	/**
	 * getter for the selected y-coord <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the y-coord of the selected cell
	 */
	public int getSelectedCellY()
	{
		return selectedCellY;
	}
	/**
	 * setter for the selected y-coord <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param selectedCellX - sets the selected y-coord
	 */
	public void setSelectedCellY(int selectedCellY)
	{
		this.selectedCellY = selectedCellY;
	}
	/**
	 * Convenience method for setting the selected x and y <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param x - the new selected x-coord
	 * @param y - the new selected y-coord
	 */
	public void setSelectedCell(int x, int y)
	{
		this.selectedCellX = x;
		this.selectedCellY = y;
	}
	/**
	 * gets the selected cell as a point <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - a Point reqresenting the selected cell
	 */
	public Point getSelected()
	{
		return new Point(selectedCellX, selectedCellY);
	}
	/**
	 * getter for the hasMoved boolean <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - if the selected cell has moved
	 */
	public boolean selectedHasMoved()
	{
		return selectedHasMoved;
	}
	/**
	 * setter for the selectedHasMoved boolean  <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param selectedHasMoved - the boolean to set to
	 */
	public void setSelectedHasMoved(boolean selectedHasMoved)
	{
		this.selectedHasMoved = selectedHasMoved;
	}
	/**
	 * getter for the selectedHasAttacked; boolean <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - if the selected cell has attacked
	 */
	public boolean selectedHasAttacked()
	{
		return selectedHasAttacked;
	}
	/**
	 * setter for the selectedHasAttacked boolean  <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param selectedHasMoved - the boolean to set to
	 */
	public void setSelectedHasAttacked(boolean selectedHasAttacked)
	{
		this.selectedHasAttacked = selectedHasAttacked;
	}
//	public void resetSelectedAttr()
//	{
//		this.selectedHasAttacked = false;
//		this.selectedHasMoved = false;
//	}
	/**
	 * over ridden toString <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the values of the instance variables
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "turn: "+playerNums.get(turn)+" selectX: "+selectedCellX+" selectY: "+selectedCellY+" selectedHasMoved "+selectedHasMoved+" selectedHasAttacked "+selectedHasAttacked;
	}
	
}
