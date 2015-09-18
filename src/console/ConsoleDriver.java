package console;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import javax.jws.soap.SOAPBinding;

import exceptions.CantMoveThereException;
import exceptions.InactiveCellException;
import exceptions.SelectedCellCantAttackException;
import exceptions.SelectedCellCantMoveException;
import exceptions.SelectedCellDoesNotBelongToCurrentPlayerException;
import exceptions.SelectedCellHasAlreadyAttackedException;
import exceptions.SelectedCellHasAlreadyMovedException;

import SaveAndLoad.Persistance;

import model.Game;
import model.Map;
import model.Point;
import model.StatsHolder;
import model.obstacle.ObstacleType;
import model.terrain.TerrainType;

public class ConsoleDriver
{
	private static final int WIDTH=10;
	private static final int HEIGHT=10;
	private BufferedReader bufferedReader;
	private Game game;
	/**
	 * Enter method description here <br>        
	 *
	 * <hr>
	 * Date created: May 19, 2010 <br>
	 * Date last modified: May 19, 2010 <br>
	 *
	 * <hr>
	 * @param args
	 */
	public ConsoleDriver()
	{
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		//creates the map
//		TerrainType[][] terArray = new TerrainType[WIDTH][HEIGHT];
//		ObstacleType[][] obsArray = new ObstacleType[WIDTH][HEIGHT];
		TerrainType[][] terArray = 
				{{TerrainType.Plain,TerrainType.Plain,TerrainType.Plain,TerrainType.Plain,TerrainType.Plain,TerrainType.Plain,TerrainType.Plain,TerrainType.BigMountain,TerrainType.BigMountain,TerrainType.SmallMountain},
				{TerrainType.Plain,TerrainType.Plain,TerrainType.Plain,TerrainType.Plain,TerrainType.Plain,TerrainType.BigMountain,TerrainType.BigMountain,TerrainType.BigMountain,TerrainType.Plain,TerrainType.SmallMountain},
				{TerrainType.Plain,TerrainType.Plain,TerrainType.Plain,TerrainType.BigMountain,TerrainType.BigMountain,TerrainType.BigMountain,TerrainType.SmallMountain,TerrainType.SmallMountain,TerrainType.SmallMountain,TerrainType.Plain},
				{TerrainType.SmallMountain,TerrainType.SmallMountain,TerrainType.BigMountain,TerrainType.BigMountain,TerrainType.BigMountain,TerrainType.BigMountain,TerrainType.BigMountain,TerrainType.BigMountain,TerrainType.SmallMountain,TerrainType.Plain},
				{TerrainType.BigMountain,TerrainType.BigMountain,TerrainType.BigMountain,TerrainType.BigMountain,TerrainType.SmallMountain,TerrainType.BigMountain,TerrainType.BigMountain,TerrainType.SmallMountain,TerrainType.Plain,TerrainType.Plain},
				{TerrainType.BigMountain,TerrainType.Building,TerrainType.Street,TerrainType.Street,TerrainType.Street,TerrainType.Street,TerrainType.Street,TerrainType.Street,TerrainType.Street,TerrainType.Building},
				{TerrainType.SmallMountain,TerrainType.Plain,TerrainType.Plain,TerrainType.Building,TerrainType.Forest,TerrainType.Forest,TerrainType.Forest,TerrainType.Forest,TerrainType.Plain,TerrainType.Plain},
				{TerrainType.Plain,TerrainType.Plain,TerrainType.Plain,TerrainType.Forest,TerrainType.Plain,TerrainType.Forest,TerrainType.Forest,TerrainType.Forest,TerrainType.Plain,TerrainType.Plain},
				{TerrainType.Plain,TerrainType.Plain,TerrainType.Plain,TerrainType.Forest,TerrainType.Forest,TerrainType.Forest,TerrainType.Forest,TerrainType.Forest,TerrainType.Plain,TerrainType.Plain},
				{TerrainType.Plain,TerrainType.Plain,TerrainType.Plain,TerrainType.Forest,TerrainType.Plain,TerrainType.Plain,TerrainType.Forest,TerrainType.Plain,TerrainType.Plain,TerrainType.Plain},};
		int[][] pieces = 
				{{-1,-1,-1,-1,0,0,-1,-1,-1,-1},
				{-1,-1,-1,-1,0,-1,-1,-1,0,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,0,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,2,-1,-1,-1,-1,-1,1,-1},
				{2,-1,-1,-1,-1,-1,-1,-1,-1,1},
				{-1,2,-1,-1,-1,-1,-1,-1,-1,1},
				{2,2,-1,-1,-1,-1,-1,1,-1,1}};
		ObstacleType[][] obsArray = 
				{{null,null,null,null,ObstacleType.Base,ObstacleType.Marine,null,null,null,null},
				{null,null,null,null,ObstacleType.Marine,null,null,null,ObstacleType.Marine,null},
				{null,null,null,null,null,null,null,null,null,null},
				{null,null,ObstacleType.Marine,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null,null,null},
				{null,null,ObstacleType.Zergling,null,null,null,null,null,ObstacleType.Zealot,null},
				{ObstacleType.Zergling,null,null,null,null,null,null,null,null,ObstacleType.Zealot},
				{null,ObstacleType.Zergling,null,null,null,null,null,null,null,ObstacleType.Zealot},
				{ObstacleType.Zergling,ObstacleType.Base,null,null,null,null,null,ObstacleType.Zealot,null,ObstacleType.Base}};
//		for( int x=0; x<terArray.length; x++)
//		{
//			for( int y=0; y<terArray[0].length; y++)
//			{
//				pieces[x][y] = 0;
//				terArray[x][y] = TerrainType.Plain;
//commented out because I created a custom unit placing
//				obsArray[x][y] = ObstacleType.Marine;
//			}
//		}
		game = new Game(new Map(terArray, obsArray, pieces), 3);
	}
	public static void main(String[] args)
	{
		ConsoleDriver cd = new ConsoleDriver();
		cd.run();
	}
	public void run()
	{
		int userChoice = -1;
		while( userChoice != MenuChoices.Quit.ordinal() + 1)
		{
			displayChoices();
			userChoice = promptForInt("Enter your selection: ", 1, MenuChoices.values().length);
			handleUserChoice(userChoice);
			if(userChoice == MenuChoices.Quit.ordinal() + 1)
			{
				int sure = promptForInt("Are you sure?(8=yes) ", Integer.MIN_VALUE, Integer.MAX_VALUE);
				if( sure != 8)
					userChoice = -1;
			}
		}
		System.out.println("Thanks for playing.");
	}
	private void handleUserChoice(int userChoice)
	{
		MenuChoices userSelection = MenuChoices.getMenuChoice(userChoice - 1);
		switch(userSelection)
		{
			case SelectUnit:
				selectUnit();
			break;
			case ShowSelected:
				Point selected = game.getGameState().getSelected();
				System.out.println("Cell "+selected.getX()+", "+selected.getY()+" is selected");
			break;
			case MoveUnit:
				try
				{
					moveUnit();
				} catch (SelectedCellCantMoveException e)
				{
					System.out.println("The selected cell can't move");
				} catch (InactiveCellException e)
				{
					System.out.println("That unit has already moved/attacked");
				} catch (SelectedCellHasAlreadyMovedException e)
				{
					System.out.println("Selected Cell has already moved");
				} catch (SelectedCellDoesNotBelongToCurrentPlayerException e)
				{
					System.out.println("That cell does not belong to you.");
				} catch (CantMoveThereException e)
				{
					System.out.println("You can't move there.");
				}
			break;
			case AttackUnit:
				try
				{
					attackUnit();
				} catch (SelectedCellCantAttackException e)
				{
					System.out.println("The selected cell can't attack");
				} catch (InactiveCellException e)
				{
					System.out.println("The selected cell has already moved/attacked");
				} catch (SelectedCellHasAlreadyAttackedException e)
				{
					System.out.println("Selected Cell has already attacked");
				} catch (SelectedCellDoesNotBelongToCurrentPlayerException e)
				{
					System.out.println("That cell does not belong to you.");
				}
			break;
			case DisplayBoard:
				displayBoard();
			break;
			case ShowTurn:
				System.out.println("It is player "+game.getGameState().getTurn()+"'s turn.");
			break;
			case EndTurn:
				endTurn();
			break;
			case Quit:
				//not handled here
			break;
			case SaveGame:
				String saveFileName = promptForString("Enter the file name to save to: ");
				try
				{
					Persistance.save(game, new File(saveFileName));
				} catch (FileNotFoundException f)
				{
					System.out.println("That file counldn't be found");
				} catch (IOException e)
				{
					System.out.println("Error writing the file");
				} 
			break;
			case LoadGame:
				String loadFileName = promptForString("Enter the file name to load from: ");
				try
				{
					game = Persistance.load(new File(loadFileName));
				} catch (FileNotFoundException f)
				{
					System.out.println("That file counldn't be found");
				} catch (IOException e)
				{
					System.out.println("Error reading the file");
				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				}
			break;
			case ShowScores:
				HashMap<Integer, Integer> scores = game.getGameState().getScores();
				Set<Entry<Integer, Integer>> scoresSet = scores.entrySet();
				for (Entry<Integer, Integer> entry : scoresSet)
				{
					System.out.println("Player "+entry.getKey()+"'s score is "+entry.getValue());
				}
			break;
			
		}
	}
	private void selectUnit()
	{
		int colSelect = promptForInt("Enter Column Selection: ", 0, WIDTH);
		int rowSelect = promptForInt("Enter Row Selection: ", 0, HEIGHT);
		game.setSelected(rowSelect, colSelect);
		int selectedX = game.getGameState().getSelectedCellX();
		int selectedY = game.getGameState().getSelectedCellY();
		displayStats(selectedX, selectedY);
	}
	private void displayStats(int selectedX, int selectedY)
	{
		StatsHolder holder;
		if(game.getCellAt(selectedX, selectedY).getObstacle() == null)
			holder = new StatsHolder(game.getCellAt(selectedX, selectedY).getTerrain());
		else 
			holder = new StatsHolder(game.getCellAt(selectedX, selectedY).getObstacle());
		System.out.println(holder);
	}
	private void moveUnit() throws SelectedCellCantMoveException, InactiveCellException, SelectedCellHasAlreadyMovedException, SelectedCellDoesNotBelongToCurrentPlayerException, CantMoveThereException
	{
		//show available moves
		Set<Point> moves = game.getAvailableMoves();
		
		StringBuilder sb = new StringBuilder();
		sb.append("Available movement of unit at ");
		sb.append(game.getGameState().getSelectedCellX());
		sb.append(", ");
		sb.append(game.getGameState().getSelectedCellY());
		sb.append("\n");
		sb.append("   0   1   2   3   4   5   6   7   8   9\n");
		for( int y=0; y<game.getMap().getWidth(); y++)
		{
			sb.append(y+" ");
			for( int x=0; x<game.getMap().getHeight(); x++)
			{
				sb.append("[");
				boolean cellFound = false;
				for (Point point : moves)
				{
					if(point.getX() == y && point.getY() == x && !cellFound)
					{
						sb.append("#");
						cellFound = true;
					}
				}
				if(!cellFound)
				{
					sb.append(" ");
				}
				sb.append("] ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

		
		int colMove = promptForInt("Enter Column to move to: ", 0, HEIGHT-1);
		int rowMove = promptForInt("Enter Row to move to: ", 0, WIDTH-1);
		game.moveUnit( rowMove, colMove );
	}
	private void attackUnit() throws SelectedCellCantAttackException, InactiveCellException, SelectedCellHasAlreadyAttackedException, SelectedCellDoesNotBelongToCurrentPlayerException
	{
		//set of available attacks
		Set<Point> attacks = game.getAttackableUnits();
		
		StringBuilder sb = new StringBuilder();
		sb.append("Attackable cells of unit at ");
		sb.append(game.getGameState().getSelectedCellX());
		sb.append(", ");
		sb.append(game.getGameState().getSelectedCellY());
		sb.append("\n");
		
		sb.append("   0   1   2   3   4   5   6   7   8   9\n");
		for( int y=0; y<game.getMap().getWidth(); y++)
		{
			sb.append(y+" ");
			for( int x=0; x<game.getMap().getHeight(); x++)
			{
				sb.append("[");
				boolean cellFound = false;
				for (Point point : attacks)
				{
					if(point.getX() == y && point.getY() == x && !cellFound)
					{
						sb.append("#");
						cellFound = true;
					}
				}
				if(!cellFound)
				{
					sb.append(" ");
				}
				sb.append("] ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
		
//		System.out.println("List of attackable cells(enemy units):");
//		for (Point point : attacks)
//		{
//			System.out.println(point.getX()+", "+point.getY());
//		}
//		System.out.println();
		
		int colAttack = promptForInt("Enter column to attack: ", 0, HEIGHT);
		int rowAttack = promptForInt("Enter row to attack: ", 0, WIDTH);
		game.attackUnit(rowAttack, colAttack);
	}
	private void displayBoard()
	{
		System.out.println(game.getMap());
	}
	private void endTurn()
	{
		game.getGameState().endTurn();
		System.out.println("It is now player "+game.getGameState().getTurn()+"'s turn.");
	}
	public void displayChoices()
	{
		StringBuilder menuDisplay = new StringBuilder();
		
		menuDisplay.append( "\n" );
		for( MenuChoices m : MenuChoices.values())
		{
			menuDisplay.append( "\t");
			menuDisplay.append(m.ordinal() + 1);
			menuDisplay.append( ". " );
			menuDisplay.append(m.toString());
			menuDisplay.append( "\n" );
		}
		
		System.out.println(menuDisplay);
	}
	/**
	 * prompts the user for an int using the parameters passed <br>     
	 * @author Glen Watson   
	 *
	 * <hr>
	 * Date created: Feb 24, 2010 <br>
	 * Date last modified: Feb 24, 2010 <br>
	 *
	 * <hr>
	 * @param message - the message ask the user
	 * @param max - the highest number the user can choose
	 * @param min - the lowest number the user can choose
	 * @return - the number the user choose
	 */
	private int promptForInt(String message, int min, int max)
	{
		System.out.print(message);
		int userNum = 0;
		boolean validNum = false;
		while (!validNum)
		{
			validNum = true;
			try
			{
				userNum = Integer.parseInt(bufferedReader.readLine());
				if (userNum > max || userNum < min)
				{
					System.out.println("That number is not a choice");
					validNum = false;
				}
				
			} catch (NumberFormatException e)
			{
				validNum = false; 
				System.out.println("That was not a number. Try Again.");
			} catch (IOException e)
			{
				validNum = false;
				System.out.println("Error getting input. Try Again.");
			}
		}
		return userNum;
	}
	/**
	 * Enter method description here <br>    
	 * @author Glen Watson    
	 *
	 * <hr>
	 * Date created: Feb 24, 2010 <br>
	 * Date last modified: Feb 24, 2010 <br>
	 *
	 * <hr>
	 * @param message - the message to ask the user
	 * @return - what the user typed
	 */
	public String promptForString(String message)
	{
		System.out.print(message);
		String userStr = "";
		boolean validNum = false;
		while (!validNum)
		{
			validNum = true;
			try
			{
				userStr = bufferedReader.readLine();
				if(userStr.equals(""))
				{
					System.out.println("Please enter a string. Try Again.");
					validNum = false;
				}
				
			} catch (IOException e)
			{
				validNum = false;
				System.out.println("Error getting input. Try Again.");
				e.printStackTrace();
			}
		}
		return userStr;
	}
}
enum MenuChoices
{
	SelectUnit,
	ShowSelected,
	MoveUnit,
	AttackUnit,
	DisplayBoard,
	ShowTurn,
	EndTurn,
	Quit,
	SaveGame,
	LoadGame,
	ShowScores;
	
	public static MenuChoices getMenuChoice(int choice)
	{
		MenuChoices ans = MenuChoices.SelectUnit;
		switch(choice)
		{
		case 0:
			ans = SelectUnit;
			break;
		case 1: 
			ans = ShowSelected;
			break;
		case 2:
			ans = MoveUnit;
			break;
		case 3:
			ans = AttackUnit;
			break;
		case 4:
			ans = DisplayBoard;
			break;
		case 5:
			ans = ShowTurn;
			break;
		case 6:
			ans = EndTurn;
			break;
		case 7:
			ans = Quit;
			break;
		case 8:
			ans = SaveGame;
			break;
		case 9:
			ans = LoadGame;
			break;
		case 10:
			ans = ShowScores;
			break;
		}
		return ans;
	}
}
