package model;

import java.io.Serializable;

import model.obstacle.Obstacle;
import model.obstacle.ObstacleType;
import model.obstacle.Unit;
import model.terrain.Terrain;
import model.terrain.TerrainType;

/**
 * A grid of Cells<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class Map implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Cell[][] cells;
	private int[][] playerPieces;
	
	/**
	 * Creates a new map <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param terArray - the terrains on the map
	 * @param obsArray - the obstacles on the map
	 * @param pieces - who owns what piece
	 */
	public Map(TerrainType[][] terArray, ObstacleType[][] obsArray, int[][] pieces)
	{
		cells = new Cell[terArray.length][terArray[0].length];
		playerPieces = new int[terArray.length][terArray[0].length];
		for(int x=0; x<terArray.length; x++)
		{
			for(int y=0; y<terArray[0].length; y++)
			{
				cells[x][y] = new Cell(terArray[x][y], obsArray[x][y]);
//				playerPieces[x][y] = pieces[x][y];
			}
		}
		playerPieces = pieces;
	}
//	public ObstacleType[][] getObstacles()
//	{
//		return 
//	}
	/**
	 * Moves a cell's obstacle <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param fromX - the x-coord to move from
	 * @param fromY - the y-coord to move from
	 * @param toX - the x-coord to move to
	 * @param toY - the y-coord to move to
	 */
	public void moveCell(int fromX, int fromY, int toX, int toY)
	{
		playerPieces[toX][toY] = playerPieces[fromX][fromY];
		playerPieces[fromX][fromY] = -1;
		cells[toX][toY].setObstacle(cells[fromX][fromY].getObstacle());
		cells[fromX][fromY].setObstacle(null);
	}
	/**
	 * gets the cell at the corrds <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param row - the row of the cell
	 * @param col -the column of the cell
	 * @return - the cell at the specified coord
	 */
	public Cell getCellAt(int row, int col)
	{
		return cells[row][col];
	}
	/**
	 * gets the terrain at that cell <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param row - y-coord
	 * @param col - x-coord
	 * @return - the Terrain at that cell
	 */
	public Terrain getTerrainAt(int row, int col)
	{
		return cells[row][col].getTerrain();
	}
	/**
	 * gets the obstacles at that cell <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param row - the y-coord
	 * @param col - x-coord
	 * @return - the obstacle at that cell, null if there isn't one
	 */
	public Obstacle getObstacleAt(int row, int col)
	{
		return cells[row][col].getObstacle();
	}
	/**
	 * gets the width of the map <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the width of the map
	 */
	public int getWidth()
	{
		return cells.length;
	}
	/**
	 * gets the height of the map <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - the height of the map
	 */
	public int getHeight()
	{
		return cells[0].length;
	}
	/**
	 * gets who owns what pieces <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - a 2-Dimensional int array of who owns what piece
	 */
	public int[][] getPlayerPieces()
	{
		return playerPieces;
	}
	/**
	 * Sets the next active cells to true <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param turn - who's turn is next
	 */
	public void endTurn(int turn)
	{
		for(int x=0; x<cells.length; x++)
		{
			for(int y=0; y<cells[0].length; y++)
			{
				if(turn == playerPieces[x][y] && cells[x][y].getObstacle() instanceof Unit)
				{
					((Unit) cells[x][y].getObstacle()).setActive(true);
				}
			}
		}
	}
	/**
	 * deletes a player's units <br>
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param eliminatedPlayer - the eliminated player's number
	 */
	public void playerEliminated(int eliminatedPlayer)
	{
		for(int x=0; x<getWidth(); x++)
		{
			for(int y=0; y<getHeight(); y++)
			{
				if(playerPieces[x][y] == eliminatedPlayer)
				{
					playerPieces[x][y] = -1;
					cells[x][y].setObstacle(null);
				}
			}
		}
	}
	/**
	 * overridden toString <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @return - 3 maps of the obstacles, terrain, and players
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuilder columnHeader = new StringBuilder();
		for(int i=0; i<cells.length; i++)
		{
			columnHeader.append("   ");
			columnHeader.append(i);
		}
		columnHeader.append("\n");
		
		StringBuilder sb = new StringBuilder();
		sb.append("-------------------MAP-------------------\n");
		sb.append("                Terrain\n");
		sb.append(columnHeader.toString());
		for( int i=0; i<cells.length; i++)
		{
			sb.append(i+" ");
			for( int j=0; j<cells[i].length; j++)
			{
				sb.append("[");
				sb.append(cells[i][j].getTerrain());
				sb.append("] ");
			}
			sb.append("\n");
		}
		sb.append(columnHeader.toString());
		sb.append("                 Players\n");
		sb.append("   0   1   2   3   4   5   6   7   8   9\n");
		for( int i=0; i<playerPieces.length; i++)
		{
			sb.append(i+" ");
			for( int j=0; j<playerPieces[i].length; j++)
			{
				sb.append("[");
				if(playerPieces[i][j] == -1)
					sb.append(" ");
				else
					sb.append(playerPieces[i][j]);
				sb.append("] ");
			}
			sb.append("\n");
		}
		sb.append(columnHeader.toString());
		sb.append("                Obstacles\n");
		sb.append("   0   1   2   3   4   5   6   7   8   9\n");
		for( int i=0; i<cells.length; i++)
		{
			sb.append(i+" ");
			for( int j=0; j<cells[i].length; j++)
			{
				sb.append("[");
				if(cells[i][j].getObstacle() == null)
					sb.append(" ");
				else
					sb.append(cells[i][j].getObstacle());
				sb.append("] ");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
