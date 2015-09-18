package model;

import java.awt.Container;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import model.obstacle.ObstacleType;
import model.obstacle.Protoss;
import model.obstacle.Unit;
import model.obstacle.Zerg;
import model.terrain.TerrainType;



/**
 * A test game class<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class GameTest
{
	public static final int WIDTH=10,
							HEIGHT=10,
							UNIT_SELECTED=2,
							MAX_RANGE=7,
							MIN_RANGE=5,
							SIGHT=6,
							UNIT_1_X=4,
							UNIT_1_Y=0,
							UNIT_2_X=9,
							UNIT_2_Y=9;
	private TestCell [] [] board;
	private Game game;
	
	public GameTest()
	{
//		initGameTest();
		
		TerrainType[][] terArray = new TerrainType[WIDTH][HEIGHT];
//		ObstacleType[][] obsArray = new ObstacleType[WIDTH][HEIGHT];
		ObstacleType[][] obsArray = 
		{{ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine},
		{ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling},
		{ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot},
		{ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine},
		{ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling},
		{ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine,ObstacleType.Marine},
		{ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot},
		{ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling},
		{ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot,ObstacleType.Zealot},
		{ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling,ObstacleType.Zergling}};

		for( int x=0; x<terArray.length; x++)
		{
			for( int y=0; y<terArray[0].length; y++)
			{
				terArray[x][y] = TerrainType.Plain;
//				obsArray[x][y] = ObstacleType.Marine;
			}
		}
		
		game = new Game(new Map(terArray, obsArray, new int[WIDTH][HEIGHT]), 3);
		System.out.println("Current game:");
		System.out.println(game);
		
		
		JFrame win = new JFrame("map test");
		win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		win.setBounds(100, 100, 800, 800);
		win.setLayout(null);
		
JFrame cellDisplay = new JFrame("cellDisplay");
cellDisplay.setBounds(0, 0, 100, 100);
cellDisplay.setVisible(true);
		
		Container pane = win.getContentPane();
		JLabel[][] lblGrid = new JLabel[WIDTH][HEIGHT];
		for( int x=0; x<lblGrid.length; x++)
		{
			for( int y=0; y<lblGrid[0].length; y++)
			{
if(x==0&&y==0&&Game.DEBUG)
System.out.println("stop");
				lblGrid[x][y] = new JLabel(game.getCellAt(x, y).getDisplay());
				lblGrid[x][y].setBounds(x*50, y*50, 50, 50);
				pane.add(lblGrid[x][y]);
if(Game.DEBUG)
{
cellDisplay.getContentPane().add(new JLabel(game.getCellAt(x, y).getDisplay()));
cellDisplay.repaint();
}
			}
		}
		
		win.setVisible(true);		
		
		clickOnCell();
		clickMove();
		clickAttack();
	}
	private void clickMove()
	{
		int clickX = 5;
		int clickY = 5;
		
		//store all of the cells the unit can move in a set
		Set<Point> moveableCells = new HashSet<Point>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("Movement of unit at "+clickX+", "+clickY);
		sb.append("\n");
		for( int x=0; x<WIDTH; x++)
		{
			for( int y=0; y<HEIGHT; y++)
			{
				sb.append("[");
				
				//does the calculations for attack range
//				int moveRange = ((Unit) map.getCellAt(clickX, clickY).getObstacle()).getSpeed();
				int moveRange = 3;
				if(Math.sqrt(Math.pow(x - clickX,2) + Math.pow(y - clickY,2)) <= moveRange)
				{
					sb.append("#");
					if(true)
					{
						moveableCells.add(new Point(x,y));
					}
				}
				else
					sb.append(game.getCellAt(x, y));
				
				
				
				
				sb.append("] ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
		System.out.println("List of cells unit can move to:");
		for (Point point : moveableCells)
		{
			System.out.println(point.getX()+", "+point.getY());
		}
		System.out.println();
	}
	public void clickAttack()
	{
		int clickX = 5;
		int clickY = 5;
		
		//store the attackable units in a set
		Set<Point> attackableUnits = new HashSet<Point>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("Attackable cells of unit at "+clickX+", "+clickY);
		sb.append("\n");
		for( int x=0; x<WIDTH; x++)
		{
			for( int y=0; y<HEIGHT; y++)
			{
				
				sb.append("[");
				//does the calculations for attack range
//				int maxRange = ((Unit) map.getCellAt(clickX, clickY).getObstacle()).getMaxRange();
//				int minRange = ((Unit) map.getCellAt(clickX, clickY).getObstacle()).getMinRange();
				int maxRange = 4;
				int minRange = 2;
				
				
				if(Math.sqrt(Math.pow(x - clickX,2) + Math.pow(y - clickY,2)) <= maxRange && 
						Math.sqrt(Math.pow(x - clickX,2) + Math.pow(y - clickY,2)) >= minRange)
				{
					sb.append("#");
					//add to the set if it's an enemy unit
					if(game.getCellAt(x, y).getObstacle() instanceof Zerg ||
							game.getCellAt(x, y).getObstacle() instanceof Protoss)
					{
						attackableUnits.add(new Point(x,y));
					}
				} else
					sb.append(game.getCellAt(x, y));
				
				
				
				
				sb.append("] ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		System.out.println("List of attackable cells(enemy units):");
		for (Point point : attackableUnits)
		{
			System.out.println(point.getX()+", "+point.getY());
		}
		System.out.println();
	}
	public void clickOnCell()
	{
		int clickX = 5;
		int clickY = 5;
		
		if(game.getCellAt(clickX, clickY).getObstacle() == null)
			System.out.println("Stats of terrain at "+clickX+", "+clickY+"\n"+game.getCellAt(clickX, clickY).getTerrain());
			
		if(game.getCellAt(clickX, clickY).getObstacle() instanceof Unit)
		{
			Unit unit = (Unit)game.getCellAt(clickX, clickY).getObstacle();
			System.out.println("Stats of unit at "+clickX+", "+clickY +
					"\nAck: "+unit.getAttack() +
					" Acc: "+unit.getAccuracy() +
					" Def: "+unit.getDefense() +
					" HP: "+unit.getHp() +
					" +Ran: "+unit.getMaxRange() +
					" -Ran: "+unit.getMinRange() +
					" Spd: "+unit.getSpeed() +"\n");
		}
			
	}
//-----------------------------------LEGACY CODE-----------------------------------//
	private void initGameTest()
	{
		board = new TestCell[WIDTH][HEIGHT];
		//displays the board
		for( int i=0; i<board.length; i++)
		{
			for( int j=0; j<board[i].length; j++)
			{
				board[i][j] = new TestCell();
//				System.out.println(board[i][j].getUnit());
			}
		}
		
		board[UNIT_1_X][UNIT_1_Y].setUnit(1);
		board[UNIT_2_X][UNIT_2_Y].setUnit(2);
		board[7][UNIT_2_Y].setUnit(3);
		
		
		
		System.out.println(this);
		
//		display1AvailableMoves();
		displaySight();
	}
	private void displaySight()
	{
		//gets the position of the unit
		int unitXpos = -1;
		int unitYpos = -1;
		for( int i=0; i<board.length; i++)
		{
			for( int j=0; j<board[i].length; j++)
			{
				if(board[i][j].getUnit() == UNIT_SELECTED)
				{
					unitXpos = i;
					unitYpos = j;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("Sight of unit ");
		sb.append(UNIT_SELECTED);
		sb.append("\n");
		for( int i=0; i<board.length; i++)
		{
			for( int j=0; j<board[i].length; j++)
			{
				sb.append("[");
				
				//does the calculations for sight
				if(Math.sqrt(Math.pow(i - unitXpos,2) + Math.pow(j - unitYpos,2)) <= SIGHT &&
						!isSightBlocked(unitXpos, unitYpos, i, j))
						sb.append("#");
				
				else
					sb.append(board[i][j]);
				
				
				
				
				sb.append("] ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private boolean isSightBlocked(int unitXpos, int unitYpos, int selectedX, int selectedY)
	{
		boolean blocked = false;
		
		double angle = Math.acos(selectedY - unitYpos);
		if( Math.asin(selectedX - unitXpos) < 0)
			angle += Math.PI;
		System.out.println("angle:"+angle+"   sX:"+selectedX+"   sY:"+selectedY);
		
//		if(selectedX - unitXpos != 0)
//		{
//			System.out.println(Math.atan((selectedY - unitYpos)/(selectedX - unitXpos)));
////			blocked = true;
//		} else
//		{
//			System.out.println(unitXpos+" "+unitYpos+" "+selectedX+" "+selectedY);
//		}
		
		return blocked;
	}
	
	
	
	private void display1AvailableMoves()
	{
		StringBuilder sb = new StringBuilder();
		
		//gets the position of the unit
		int unitXpos = -1;
		int unitYpos = -1;
		for( int i=0; i<board.length; i++)
		{
			for( int j=0; j<board[i].length; j++)
			{
				if(board[i][j].getUnit() == UNIT_SELECTED)
				{
					unitXpos = i;
					unitYpos = j;
				}
			}
		}
		
		//displays the board
		sb.append("Range of unit ");
		sb.append(UNIT_SELECTED);
		sb.append("\n");
		for( int i=0; i<board.length; i++)
		{
			for( int j=0; j<board[i].length; j++)
			{
				sb.append("[");
				
				//does the calculations for range
				if(Math.sqrt(Math.pow(i - unitXpos,2) + Math.pow(j - unitYpos,2)) <= MAX_RANGE && 
						Math.sqrt(Math.pow(i - unitXpos,2) + Math.pow(j - unitYpos,2)) >= MIN_RANGE)
					sb.append("#");
				else
					sb.append(board[i][j]);
				
				
				
				
				sb.append("] ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		for( int i=0; i<board.length; i++)
		{
			for( int j=0; j<board[i].length; j++)
			{
				sb.append("[");
				sb.append(board[i][j]);
				sb.append("] ");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	public static void main(String[] args)
	{
		new GameTest();
	}
	
}
