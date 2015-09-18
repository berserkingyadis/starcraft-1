package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

/**
 * @author jsoriano
 * @author kjacob
 */
public class GUIMap extends JPanel
{
	public Controller control;
	public GameWindow window;
	private int[][] marineLoc;
	private int[][] baseLoc;
	private int[][] zerglingLoc;
	private int[][] zealotLoc;
	private GUIUnit[][] units;
	private GUIBase[][] bases;
	private static final int MARINE = 1;
	private static final int ZERGLING = 2;
	private static final int ZEALOT = 3;
	private static final int BASE = 4;
	protected static final int LEFT = 1;
	protected static final int DOWN = 2;
	protected static final int RIGHT = 3;
	protected static final int UP = 4;
	private ArrayList<JLabel> hightlightPanels;
	private JLabel highlightPanel;
	private ImageIcon blue;
	private ImageIcon white;
	private ImageIcon red;

	/**
	 * Constructor for the GUIMap class.
	 * @param control
	 * @param window
	 */
	public GUIMap(Controller control, GameWindow window)
	{
		this.control = control;
		this.window = window;
		hightlightPanels = new ArrayList<JLabel>();
		getUnitLoc();
		makeMarines();
		makeZealots();
		makePBase();
	}

	/**
	 * gets the unit locations from the controller.
	 */
	public void getUnitLoc()
	{
		marineLoc = control.getMarines();
		baseLoc = control.getBases();
		zerglingLoc = control.getZergling();
		zealotLoc = control.getZealot();
	}

	/**
	 * creates the marines on the map.
	 */
	public void makeMarines()
	{
		units = new GUIUnit[marineLoc.length][marineLoc.length];
		for (int i = 0; i < marineLoc.length; i++)
		{
			for (int j = 0; j < marineLoc.length; j++)
			{
				if (marineLoc[i][j] == MARINE)
				{
					units[i][j] = new GUIMarine(i, j);
					window.getPnlGrid().add(units[i][j], 2);
				}

			}
		}
	}

	/**
	 * creates the zealots on the map.
	 */
	public void makeZealots()
	{
		// units = new GUIUnit[zealotLoc.length][zealotLoc.length];
		for (int i = 0; i < zealotLoc.length; i++)
		{
			for (int j = 0; j < zealotLoc.length; j++)
			{
				if (zealotLoc[i][j] == ZEALOT)
				{
					units[i][j] = new GUIZealot(i, j);
					window.getPnlGrid().add(units[i][j], 2);
				}

			}
		}
	}

	/**
	 * creates the protoss base on the map.
	 */
	public void makePBase()
	{
		bases = new GUIBase[baseLoc.length][baseLoc.length];
		for (int i = 0; i < baseLoc.length; i++)
		{
			for (int j = 0; j < baseLoc.length; j++)
			{
				if (baseLoc[i][j] == BASE)
				{
					bases[i][j] = new GUITBase(i, j);
					window.getPnlGrid().add(bases[i][j], 2);
				}

			}
		}
	}

	/**
	 * tells the selected unit to move.
	 * @param a
	 * @param b
	 * @param hspaces
	 * @param hdir
	 * @param vspaces
	 * @param vdir
	 */
	public void move(int a, int b, int hspaces, int hdir, int vspaces, int vdir)
	{
		new MoveThread(units[a][b], Math.abs(hspaces), hdir, Math.abs(vspaces), vdir).start();
		units[a - hspaces][b - vspaces] = units[a][b];
		units[a][b] = null;
	}

	/**
	 * tells the selected unit to attack.
	 * @param a
	 * @param b
	 * @param dir
	 */
	public void attack(int a, int b, int dir)
	{
		new AttackThread(units[a][b], dir).start();
	}

	/**
	 * gets the controller that is being used.
	 * @return
	 */
	public Controller getController()
	{
		return control;
	}

	/**
	 * Shows the possible locations for movement with blue panels.
	 * @param x
	 * @param y
	 */
	public void setMovePanels(int x, int y)
	{
		blue = new ImageIcon("Images\\misc\\movepanels.png");
		highlightPanel = new JLabel(blue);
		highlightPanel.setBounds((x * 50) + 2, 2 + (y * 50), 45, 45);
		highlightPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		hightlightPanels.add(highlightPanel);
		window.getPnlGrid().add(highlightPanel, 1);
	}

	/**
	 * shows the selected panel.
	 * @param x
	 * @param y
	 */
	public void addSelectPanels(int x, int y)
	{
		white = new ImageIcon("Images\\misc\\selectedpanels.png");
		highlightPanel = new JLabel(white);
		highlightPanel.setBounds((x * 50) + 2, 2 + (y * 50), 45, 45);
		highlightPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		hightlightPanels.add(highlightPanel);
		window.getPnlGrid().add(highlightPanel, 1);
	}

	/**
	 * shows the possible locations for attacking with red panels.
	 * @param x
	 * @param y
	 */
	public void setAttackPanels(int x, int y)
	{
		red = new ImageIcon("Images\\misc\\attackpanels.png");
		highlightPanel = new JLabel(red);
		highlightPanel.setBounds((x * 50) + 2, 2 + (y * 50), 45, 45);
		highlightPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		hightlightPanels.add(highlightPanel);
		window.getPnlGrid().add(highlightPanel, 1);
	}

	/**
	 * removes all panels from the map.
	 */
	public void removePanels()
	{
		for (JLabel pnl : hightlightPanels)
		{
			window.getPnlGrid().remove(pnl);
		}
		hightlightPanels.clear();
	}

	public static void main(String[] args)
	{

	}

	/**
	 * sets the unit to be dead
	 * @param x
	 * @param y
	 */
	public void setDead(int x, int y)
	{
		try
		{
			new DeadThread(units[x][y]).start();
		} catch (NullPointerException e)
		{
			new DeadThread(bases[x][y]).start();
		}
	}

}
