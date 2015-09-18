package controller;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import javax.swing.JOptionPane;

import model.Cell;
import model.Game;
import model.Map;
import model.Point;
import model.StatsHolder;
import model.obstacle.ObstacleType;
import model.terrain.TerrainType;
import GUI.AttackButton;
import GUI.GUIMap;
import GUI.GameWindow;
import GUI.JLabelCell;
import GUI.MoveButton;
import GUI.OptionsMenuWindow;
import GUI.StartWindow;
import SaveAndLoad.Persistance;
import exceptions.CantMoveThereException;
import exceptions.InactiveCellException;
import exceptions.SelectedCellCantAttackException;
import exceptions.SelectedCellCantMoveException;
import exceptions.SelectedCellDoesNotBelongToCurrentPlayerException;
import exceptions.SelectedCellHasAlreadyAttackedException;
import exceptions.SelectedCellHasAlreadyMovedException;

/**
 * 
 * @author jsoriano
 * @author kjacob
 * @author gwatson
 */
public class Controller implements MouseListener
{

	private GameWindow gameWin;
	private Game game;
	// 0 = select, 1 = move, 2 = attack
	private int action = 0;
	private int[][] marineLoc;
	private int[][] baseLoc;
	private int[][] zerglingLoc;
	private int[][] zealotLoc;
	private static final int MARINE = 1;
	private static final int ZERGLING = 2;
	private static final int ZEALOT = 3;
	private static final int BASE = 4;
	public static final int SIZESQUARED = 25;
	private int teamNum1;
	private int teamNum2;
	private OptionsMenuWindow op;
	private GUIMap guiMap;
	private int selectedX;
	private int selectedY;
	private Set<Point> moves;
	private Set<Point> attacks;
	private static final int LEFT = 1;
	private static final int DOWN = 2;
	private static final int RIGHT = 3;
	private static final int UP = 4;

	/**
	 * default constructor
	 */
	public Controller()
	{
		makeStartWindow();
	}

	/**
	 * allows the user to save the game into a file.
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void save(File file) throws FileNotFoundException, IOException
	{
		Persistance.save(game, file);
	}

	/**
	 * Creates the start window.
	 */
	private void makeStartWindow()
	{
		new StartWindow(this);
	}

	/**
	 * Creates the option menu window.
	 */
	public void makeOptionsMenuWindow()
	{
		op = new OptionsMenuWindow(this);
	}

	// public void makeLoadMenuWindow()
	// {
	// new LoadMenuWindow(this);
	// }
	
	/**
	 * Creates the game window.
	 */
	public void makeGameWindow()
	{
		this.teamNum1 = op.getPlayer1();
		this.teamNum2 = op.getPlayer2();
		// have a default game
		defaultGame();
		gameWin = new GameWindow(this);
		guiMap = new GUIMap(this, gameWin);

	}

	/**
	 * Creates the game window
	 * @param g
	 */
	public void makeGameWindow(Game g)
	{
		this.game = g;
		getLociFromObsArray();
		gameWin = new GameWindow(this);
		guiMap = new GUIMap(this, gameWin);
	}

	/**
	 * gets the size of the map
	 * @return
	 */
	public Dimension getMapSize()
	{
		return new Dimension(game.getMap().getWidth(), game.getMap().getHeight());
	}

	/**
	 * gets the cell at the given locaiton
	 * @param row
	 * @param col
	 * @return
	 */
	public Cell getCellAt(int row, int col)
	{
		// switching because Rows are Y-Axis and Columns are X-Axis
		return game.getCellAt(col, row);
	}

	/**
	 * ends the turn for the current user
	 */
	public void endTurn()
	{
		game.endTurn();
		gameWin.changeTurnText("Player "+ (game.getGameState().getTurn()+1) + "'s turn.");
	}
	
	/**
	 * gets the score for the winner
	 * @return
	 */
	public int getWinnerScore()
	{
		return game.getGameState().getScore(game.getGameState().getWinner());
	}

	/**
	 * handles the mouse events on the gameWindow <br>        
	 *
	 * <hr>
	 * Date created: Jun 8, 2010 <br>
	 * Date last modified: Jun 8, 2010 <br>
	 *
	 * <hr>
	 * @param me
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent me)
	{
		// switched the x/col and y/row to match up with the model
		if (me.getSource() instanceof JLabelCell)
		{
			switch (action)
			{
			case 0:
				select(me);
				break;
			case 1:
				try
				{
					guiMap.removePanels();
					move(me);
					guiMap.removePanels();
				} catch (SelectedCellCantMoveException e1)
				{
					JOptionPane.showMessageDialog(gameWin, "Selected cell is not a unit");

				} catch (InactiveCellException e1)
				{
					JOptionPane.showMessageDialog(gameWin, "Unit is inactive");
				} catch (SelectedCellHasAlreadyMovedException e1)
				{
					JOptionPane.showMessageDialog(gameWin, "Unit has already moved");
				} catch (SelectedCellDoesNotBelongToCurrentPlayerException e1)
				{
					JOptionPane.showMessageDialog(gameWin, "Unit is not under your control");
				} catch (CantMoveThereException e)
				{
					JOptionPane.showMessageDialog(gameWin, "Selected unit cannot move that far");
				}
				action = 0;
				break;
			case 2:
				try
				{
					guiMap.removePanels();

					attack(me);
				} catch (SelectedCellCantAttackException e)
				{
					JOptionPane.showMessageDialog(gameWin, "Selected cell cannot attack.");
				} catch (InactiveCellException e)
				{
					JOptionPane.showMessageDialog(gameWin, "Unit is inactive");
				} catch (SelectedCellHasAlreadyAttackedException e)
				{
					JOptionPane.showMessageDialog(gameWin, "Unit has already attacked");
				} catch (SelectedCellDoesNotBelongToCurrentPlayerException e)
				{
					JOptionPane.showMessageDialog(gameWin, "Unit is not under your control");

				}
				action = 0;
				break;
			}

		} else if (me.getSource() instanceof MoveButton)
		{
			try
			{
				guiMap.removePanels();
				moves = game.getAvailableMoves();
				for (Point p : moves)
				{
					guiMap.setMovePanels(p.getX(), p.getY());
				}

			} catch (SelectedCellCantMoveException e)
			{
				JOptionPane.showMessageDialog(gameWin, "That cell is not able to move");
			}
			action = 1;
		} else if (me.getSource() instanceof AttackButton)
		{
			try
			{
				guiMap.removePanels();
				attacks = game.getAttackableUnits();
				for (Point p : attacks)
				{
					guiMap.setAttackPanels(p.getX(), p.getY());
				}
			} catch (SelectedCellCantAttackException e)
			{
				JOptionPane.showMessageDialog(gameWin, "That cell is not able to attack");
			}
			action = 2;
		} else
		{
		}
	}

	/**
	 * selects the object that sends the mouse event.
	 * @param me
	 */
	private void select(MouseEvent me)
	{
		guiMap.removePanels();
		game.setSelected(((JLabelCell) me.getSource()).getXPos(), ((JLabelCell) me.getSource()).getYPos());
		selectedX = game.getGameState().getSelectedCellX();
		selectedY = game.getGameState().getSelectedCellY();

		StatsHolder holder;
		if (game.getCellAt(selectedX, selectedY).getObstacle() == null)
			holder = new StatsHolder(game.getCellAt(selectedX, selectedY).getTerrain());
		else
			holder = new StatsHolder(game.getCellAt(selectedX, selectedY).getObstacle());
		gameWin.updateStats(holder);
		guiMap.addSelectPanels(selectedX, selectedY);

	}

	/**
	 * moves the object that sends the mouse event, if possible.
	 * @param me
	 * @throws SelectedCellCantMoveException
	 * @throws InactiveCellException
	 * @throws SelectedCellHasAlreadyMovedException
	 * @throws SelectedCellDoesNotBelongToCurrentPlayerException
	 * @throws CantMoveThereException
	 */
	private void move(MouseEvent me) throws SelectedCellCantMoveException, InactiveCellException, SelectedCellHasAlreadyMovedException, SelectedCellDoesNotBelongToCurrentPlayerException, CantMoveThereException
	{
		game.getAvailableMoves();
		int newX = ((JLabelCell) me.getSource()).getXPos();
		int newY = ((JLabelCell) me.getSource()).getYPos();

		game.moveUnit(newX, newY);
		int movex = this.selectedX - newX;
		int movey = this.selectedY - newY;
		int hdir;
		int vdir;
		if (movex > 0)
		{
			hdir = LEFT;
		} else
		{
			hdir = RIGHT;
		}
		if (movey > 0)
		{
			vdir = UP;
		} else
		{
			vdir = DOWN;
		}

		guiMap.move(selectedX, selectedY, movex, hdir, movey, vdir);

	}

	/**
	 * Makes the object that sends the mouse event attack, if possible.
	 * @param me
	 * @throws SelectedCellCantAttackException
	 * @throws InactiveCellException
	 * @throws SelectedCellHasAlreadyAttackedException
	 * @throws SelectedCellDoesNotBelongToCurrentPlayerException
	 */
	private void attack(MouseEvent me) throws SelectedCellCantAttackException, InactiveCellException, SelectedCellHasAlreadyAttackedException, SelectedCellDoesNotBelongToCurrentPlayerException
	{
		game.attackUnit(((JLabelCell) me.getSource()).getXPos(), ((JLabelCell) me.getSource()).getYPos());
		int dir = 0;
		int newX = ((JLabelCell) me.getSource()).getXPos();
		int newY = ((JLabelCell) me.getSource()).getYPos();
		if (newX != selectedX)
		{
			if (newX < selectedX)
			{
				dir = LEFT;
			} else if (newX > selectedX)
			{
				dir = RIGHT;
			}
		} else if (newY != selectedY)
		{
			if (newY < selectedY)
			{
				dir = UP;
			} else if (newY > selectedY)
			{
				dir = DOWN;
			}
		}
		guiMap.attack(selectedX, selectedY, dir);
		
		gameOver();
	}
	
	/**
	 * Overriding.
	 */
	public void mouseClicked(MouseEvent me){}
	
	/**
	 * Overriding.
	 */	public void mouseEntered(MouseEvent arg0)
	{/* System.out.println("controller says: entered"); */
	}

	 /**
		 * Overriding.
		 */	public void mouseExited(MouseEvent arg0)
	{/* System.out.println("controller says: exited"); */
	}

		 /**
			 * Overriding.
			 */	public void mousePressed(MouseEvent arg0)
	{/* System.out.println("controller says: pressed"); */
	}

	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args)
	{
		new Controller();
	}
	
	/**
	 * declares game over
	 */
	private void gameOver()
	{
		if(game.didHpDie())
		{
			guiMap.setDead(game.getDiedX(), game.getDiedY());
			if(game.isGameOver())
			{
				game.gameOver();
				gameWin.gameOver();
			}
		}
		
	}
	/**
	 * sets up the default game
	 */
	private void defaultGame()
	{
		/*
		 * TerrainType[][] terArray =
		 * {{TerrainType.Plain,TerrainType.Plain,TerrainType
		 * .Plain,TerrainType.Plain
		 * ,TerrainType.Plain,TerrainType.Plain,TerrainType
		 * .Plain,TerrainType.BigMountain
		 * ,TerrainType.BigMountain,TerrainType.SmallMountain},
		 * {TerrainType.Plain
		 * ,TerrainType.Plain,TerrainType.Plain,TerrainType.Plain
		 * ,TerrainType.Plain
		 * ,TerrainType.BigMountain,TerrainType.BigMountain,TerrainType
		 * .BigMountain,TerrainType.Plain,TerrainType.SmallMountain},
		 * {TerrainType
		 * .Plain,TerrainType.Plain,TerrainType.Plain,TerrainType.BigMountain
		 * ,TerrainType
		 * .BigMountain,TerrainType.BigMountain,TerrainType.SmallMountain
		 * ,TerrainType
		 * .SmallMountain,TerrainType.SmallMountain,TerrainType.Plain},
		 * {TerrainType
		 * .SmallMountain,TerrainType.SmallMountain,TerrainType.BigMountain
		 * ,TerrainType
		 * .BigMountain,TerrainType.BigMountain,TerrainType.BigMountain
		 * ,TerrainType
		 * .BigMountain,TerrainType.BigMountain,TerrainType.SmallMountain
		 * ,TerrainType.Plain},
		 * {TerrainType.BigMountain,TerrainType.BigMountain,
		 * TerrainType.BigMountain
		 * ,TerrainType.BigMountain,TerrainType.SmallMountain
		 * ,TerrainType.BigMountain
		 * ,TerrainType.BigMountain,TerrainType.SmallMountain
		 * ,TerrainType.Plain,TerrainType.Plain},
		 * {TerrainType.BigMountain,TerrainType
		 * .Building,TerrainType.Street,TerrainType
		 * .Street,TerrainType.Street,TerrainType
		 * .Street,TerrainType.Street,TerrainType
		 * .Street,TerrainType.Street,TerrainType.Building},
		 * {TerrainType.SmallMountain
		 * ,TerrainType.Plain,TerrainType.Plain,TerrainType
		 * .Building,TerrainType.
		 * Forest,TerrainType.Forest,TerrainType.Forest,TerrainType
		 * .Forest,TerrainType.Plain,TerrainType.Plain},
		 * {TerrainType.Plain,TerrainType
		 * .Plain,TerrainType.Plain,TerrainType.Forest
		 * ,TerrainType.Plain,TerrainType
		 * .Forest,TerrainType.Forest,TerrainType.Forest
		 * ,TerrainType.Plain,TerrainType.Plain},
		 * {TerrainType.Plain,TerrainType.
		 * Plain,TerrainType.Plain,TerrainType.Forest
		 * ,TerrainType.Forest,TerrainType
		 * .Forest,TerrainType.Forest,TerrainType.
		 * Forest,TerrainType.Plain,TerrainType.Plain},
		 * {TerrainType.Plain,TerrainType
		 * .Plain,TerrainType.Plain,TerrainType.Forest
		 * ,TerrainType.Plain,TerrainType
		 * .Plain,TerrainType.Forest,TerrainType.Plain
		 * ,TerrainType.Plain,TerrainType.Plain},}; int[][] pieces =
		 * {{-1,-1,-1,-1,0,0,-1,-1,-1,-1}, {-1,-1,-1,-1,0,-1,-1,-1,0,-1},
		 * {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}, {-1,-1,0,-1,-1,-1,-1,-1,-1,-1},
		 * {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}, {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		 * {-1,-1,2,-1,-1,-1,-1,-1,1,-1}, {2,-1,-1,-1,-1,-1,-1,-1,-1,1},
		 * {-1,2,-1,-1,-1,-1,-1,-1,-1,1}, {2,2,-1,-1,-1,-1,-1,1,-1,1}};
		 * ObstacleType[][] obsArray =
		 * {{null,null,null,null,ObstacleType.Base,ObstacleType
		 * .Marine,null,null,null,null},
		 * {null,null,null,null,ObstacleType.Marine
		 * ,null,null,null,ObstacleType.Marine,null},
		 * {null,null,null,null,null,null,null,null,null,null},
		 * {null,null,ObstacleType.Marine,null,null,null,null,null,null,null},
		 * {null,null,null,null,null,null,null,null,null,null},
		 * {null,null,null,null,null,null,null,null,null,null},
		 * {null,null,ObstacleType
		 * .Zergling,null,null,null,null,null,ObstacleType.Zealot,null},
		 * {ObstacleType
		 * .Zergling,null,null,null,null,null,null,null,null,ObstacleType
		 * .Zealot},
		 * {null,ObstacleType.Zergling,null,null,null,null,null,null,null
		 * ,ObstacleType.Zealot},
		 * {ObstacleType.Zergling,ObstacleType.Base,null,null
		 * ,null,null,null,ObstacleType.Zealot,null,ObstacleType.Base}};
		 */
		TerrainType[][] terArray = new TerrainType[SIZESQUARED][SIZESQUARED];
		for (int i = 0; i < terArray.length; i++)
		{
			for (int j = 0; j < terArray[0].length; j++)
			{
				terArray[i][j] = TerrainType.Plain;
			}
		}

		int[][] pieces = new int[SIZESQUARED][SIZESQUARED];
		for (int i = 0; i < pieces.length; i++)
		{
			for (int j = 0; j < pieces[0].length; j++)
			{
				pieces[i][j] = -1;
			}
		}

		ObstacleType[][] obsArray = new ObstacleType[SIZESQUARED][SIZESQUARED];
		for (int i = 0; i < obsArray.length; i++)
		{
			for (int j = 0; j < obsArray[0].length; j++)
			{
				obsArray[i][j] = null;
			}
		}
		addTerrains(terArray);
		addObstacles(obsArray, pieces);
		game = new Game(new Map(terArray, obsArray, pieces), 2);
		getLociFromObsArray();

	}

	/**
	 * adds all the different terrains to the map.
	 * @param terArray
	 */
	public void addTerrains(TerrainType[][] terArray)
	{
		mountains(terArray);
		mountains2(terArray);
		cities(terArray);
		forests(terArray);
		streets(terArray);
	}

	/**
	 * adds all the different obstacles to the map.
	 * @param obsArray
	 * @param pieces
	 */
	public void addObstacles(ObstacleType[][] obsArray, int[][] pieces)
	{
		switch (teamNum1)
		{
		case 1:
			team1Marines(obsArray, pieces);
			break;
		case 3:
			team1Zealots(obsArray, pieces);
			break;
		}

		switch (teamNum2)
		{
		case 1:
			team2Marines(obsArray, pieces);
			break;
		case 3:
			team2Zealots(obsArray, pieces);
			break;
		}

	}

	/**
	 * adds the marines to the map for team 1
	 * @param obsArray
	 * @param pieces
	 */
	public void team1Marines(ObstacleType[][] obsArray, int[][] pieces)
	{
		obsArray[13][1] = ObstacleType.Base;
		pieces[13][1] = 0;
		for (int i = 0; i < 4; i++)
		{
			obsArray[11][i + 1] = ObstacleType.Marine;
			pieces[11][i + 1] = 0;
			obsArray[14][i + 1] = ObstacleType.Marine;
			pieces[14][i + 1] = 0;

		}
		
		for(int i = 0; i < 2; i++)
		{
			obsArray[10+i][11] = ObstacleType.Marine;
			pieces[10+i][11] = 0;
			obsArray[14+i][11] = ObstacleType.Marine;
			pieces[14+i][11] = 0;
		}
	}

	/**
	 * adds the zealots to the map for team 1
	 * @param obsArray
	 * @param pieces
	 */
	public void team1Zealots(ObstacleType[][] obsArray, int[][] pieces)
	{
		obsArray[13][1] = ObstacleType.Base;
		pieces[13][1] = 0;
		for (int i = 0; i < 4; i++)
		{
			obsArray[11][i + 1] = ObstacleType.Zealot;
			pieces[11][i + 1] = 0;
			obsArray[14][i + 1] = ObstacleType.Zealot;
			pieces[14][i + 1] = 0;

		}
		for(int i = 0; i < 2; i++)
		{
			obsArray[10+i][11] = ObstacleType.Zealot;
			pieces[10+i][11] = 0;
			obsArray[14+i][11] = ObstacleType.Zealot;
			pieces[14+i][11] = 0;
		}
	}
	
	/**
	 * adds the marines to the map for team 2
	 * @param obsArray
	 * @param pieces
	 */

	public void team2Marines(ObstacleType[][] obsArray, int[][] pieces)
	{
		obsArray[12][24] = ObstacleType.Base;
		pieces[12][24] = 1;
		for (int i = 0; i < 4; i++)
		{
			obsArray[11][i + 20] = ObstacleType.Marine;
			pieces[11][i + 20] = 1;
			obsArray[14][i + 20] = ObstacleType.Marine;
			pieces[14][i + 20] = 1;
		}
		
		for(int i = 0; i < 2; i++)
		{
			obsArray[10+i][13] = ObstacleType.Marine;
			pieces[10+i][13] = 1;
			obsArray[14+i][13] = ObstacleType.Marine;
			pieces[14+i][13] = 1;
		}
	}

	/**
	 * adds the zealots to the map for team 2
	 * @param obsArray
	 * @param pieces
	 */
	public void team2Zealots(ObstacleType[][] obsArray, int[][] pieces)
	{
		obsArray[12][23] = ObstacleType.Base;
		pieces[12][23] = 1;
		for (int i = 0; i < 4; i++)
		{
			obsArray[11][i + 20] = ObstacleType.Zealot;
			pieces[11][i + 20] = 1;
			obsArray[14][i + 20] = ObstacleType.Zealot;
			pieces[14][i + 20] = 1;
			
			
		}
		for(int i = 0; i < 2; i++)
		{
			obsArray[10+i][13] = ObstacleType.Zealot;
			pieces[10+i][13] = 1;
			obsArray[14+i][13] = ObstacleType.Zealot;
			pieces[14+i][13] = 1;
		}
	}

	/**
	 * adds the streets to the map.
	 * @param terArray
	 */
	public void streets(TerrainType[][] terArray)
	{
		for(int i = 0; i < 2; i++)
		{
			for(int j = 0; j < 25; j++)
			{
				terArray[j][i+12] = TerrainType.Street;
			}
		}
	}
	
	/**
	 * adds the forests to the map.
	 * @param terArray
	 */
	public void forests(TerrainType[][] terArray)
	{
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 25; j++)
			{
				terArray[i+10][j] = TerrainType.Forest;
			}
		}
	}
	
	/**
	 * adds the cities to the map.
	 * @param terArray
	 */
	public void cities(TerrainType[][] terArray)
	{
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				terArray[j+7][i+8] = TerrainType.Building;
			}
		}
		
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				terArray[j+7][i+14] = TerrainType.Building;
			}
		}
		
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				terArray[j+15][i+8] = TerrainType.Building;
			}
		}
		
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				terArray[j+15][i+14] = TerrainType.Building;
			}
		}
	}
	
	/**
	 * adds the second set of mountains to the map.
	 * @param terArray
	 */
	public void mountains2(TerrainType[][] terArray)
	{
		for (int i = 0; i < 8; i++)
		{
			terArray[24][i] = TerrainType.SmallMountain;
		}

		for (int i = 0; i < 8; i++)
		{
			terArray[24][i + 17] = TerrainType.SmallMountain;
		}

		for (int i = 0; i < 7; i++)
		{
			terArray[i + 18][8] = TerrainType.BigMountain;
		}

		for (int i = 0; i < 7; i++)
		{
			terArray[i + 18][17] = TerrainType.BigMountain;
		}

		for (int i = 0; i < 3; i++)
		{
			terArray[18][i + 9] = TerrainType.BigMountain;
		}

		for (int i = 0; i < 3; i++)
		{
			terArray[18][i + 14] = TerrainType.BigMountain;
		}
	}

	/**
	 * adds the first set of mountains to the map
	 * @param terArray
	 */
	public void mountains(TerrainType[][] terArray)
	{
		for (int i = 17; i < 25; i++)
		{
			terArray[0][i] = TerrainType.SmallMountain;
		}

		for (int i = 0; i < 8; i++)
		{
			terArray[0][i] = TerrainType.SmallMountain;
		}

		for (int i = 0; i < 7; i++)
		{
			terArray[i][8] = TerrainType.BigMountain;
		}

		for (int i = 0; i < 7; i++)
		{
			terArray[i][17] = TerrainType.BigMountain;
		}

		for (int i = 0; i < 3; i++)
		{
			terArray[i][17] = TerrainType.BigMountain;
		}

		for (int i = 0; i < 3; i++)
		{
			terArray[6][i + 14] = TerrainType.BigMountain;
		}

		for (int i = 0; i < 3; i++)
		{
			terArray[6][i + 9] = TerrainType.BigMountain;
		}
	}

	/**
	 * gets the locations of the obstacles from the obstacle array.
	 */
	private void getLociFromObsArray()
	{
		marineLoc = new int[game.getMap().getWidth()][game.getMap().getHeight()];
		baseLoc = new int[game.getMap().getWidth()][game.getMap().getHeight()];
		zerglingLoc = new int[game.getMap().getWidth()][game.getMap().getHeight()];
		zealotLoc = new int[game.getMap().getWidth()][game.getMap().getHeight()];
		for (int i = 0; i < game.getMap().getWidth(); i++)
		{
			for (int j = 0; j < game.getMap().getHeight(); j++)
			{
				if (ObstacleType.getType(game.getCellAt(i, j).getObstacle()) == ObstacleType.Marine)
				{
					marineLoc[i][j] = MARINE;
				} else if (ObstacleType.getType(game.getCellAt(i, j).getObstacle()) == ObstacleType.Base)
				{
					baseLoc[i][j] = BASE;
				} else if (ObstacleType.getType(game.getCellAt(i, j).getObstacle()) == ObstacleType.Zergling)
				{
					zerglingLoc[i][j] = ZERGLING;
				} else if (ObstacleType.getType(game.getCellAt(i, j).getObstacle()) == ObstacleType.Zealot)
				{
					zealotLoc[i][j] = ZEALOT;
				} else if (ObstacleType.getType(game.getCellAt(i, j).getObstacle()) == null)
				{
					marineLoc[i][j] = 0;
					baseLoc[i][j] = 0;
					zerglingLoc[i][j] = 0;
					zealotLoc[i][j] = 0;
				}
			}
		}
	}

	/**
	 * returns the marine locations on the map.
	 * @return
	 */
	public int[][] getMarines()
	{
		return marineLoc;
	}

	/**
	 * returns the base locations on the map.
	 * @return
	 */
	public int[][] getBases()
	{
		return baseLoc;
	}

	/**
	 * returns the zealot locations on the map.
	 * @return
	 */
	public int[][] getZealot()
	{
		return zealotLoc;
	}

	/**
	 * returns the zergling locations on the map.
	 * @return
	 */
	public int[][] getZergling()
	{
		return zerglingLoc;
	}

}
