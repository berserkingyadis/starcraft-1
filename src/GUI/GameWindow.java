package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import model.Cell;
import model.Game;
import model.StatsHolder;
import SaveAndLoad.Database;
import controller.Controller;

/**
 * @author jsoriano
 * @author kjacob
 */
public class GameWindow extends JFrame implements WindowListener, MouseMotionListener, MouseListener
{
	private static final long serialVersionUID = 1L;
	public final static Color GRAY = new Color(102, 153, 204);
	public final static Color BLUE = new Color(51, 51, 204);

	private JPanel pnlGrid;
	private JPanel pnlCenterHolder;

	private JProgressBar hpBar;
	private JProgressBar spBar;
	private JProgressBar atBar;
	private JProgressBar accBar;
	private JProgressBar rgBar;
	private JProgressBar dfBar;
	private JLabel turn;
	private JLabel turnIcon;
	private ImageIcon red;
	private ImageIcon blue;

	private Controller control;
	private JPopupMenu popup;
	private MouseListener popupListener;

	public static final int X_BUFFER = 100;
	public static final int Y_BUFFER = 100;
	public static final int SCROLL_SPEED = 5;
	public static final int DELAY = 10;

	// 0=NONE, 1=UP\LEFT, 2=UP, 3=UP\RIGHT, 4=LEFT, 5=RIGHT, 6=DOWN\LEFT,
	// 7=DOWN, 8=DOWN\RIGHT
	private int scrollDirection = 0;

	/**
	 * creates all the basic modules of the game window, such as the board, the control panel, the stats bar, the menu bar, etc.
	 * @param ctrl
	 */
	public GameWindow(Controller ctrl)
	{
		super("Game Window");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setBounds(400, 200, 1000, 1000);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.addWindowListener(this);
		// //taken from http://www.rgagnon.com/javadetails/java-0222.html
		// GraphicsDevice device;
		// device =
		// GraphicsEnvironment.
		// getLocalGraphicsEnvironment().
		// getDefaultScreenDevice();
		// if ( device.isFullScreenSupported() ) {
		// device.setFullScreenWindow(this);
		// }
		// else {
		// System.err.println("Full screen not supported");
		// }
		control = ctrl;

		popupListener = new PopupListener();
		initMenuBar();
		initBoard();
		initControlPanel();
		initPopupMenu();

		this.setVisible(true);

		MouseScrollThread scrollTestThread = new MouseScrollThread("GameWindowMouseScroll", pnlCenterHolder);
		// if(!Game.DEBUG)
		scrollTestThread.start();
	}

	/**
	 * creates the pop up menu that comes up when right click is clicked.
	 */
	private void initPopupMenu()
	{
		// Create the popup menu.
		popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("End Turn");
		menuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				control.endTurn();
			}
		});
		popup.add(menuItem);

		// Add listener to components that can bring up popup menus.
		this.addMouseListener(popupListener);

	}

	/**
	 * creates the menu bar.
	 */
	private void initMenuBar()
	{
		// MenuBar
		JMenuBar menuBar = new JMenuBar();

		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);

		JMenuItem itemSave = new JMenuItem("Save");
		itemSave.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
					try
					{
						saveToFile();
					} catch (FileNotFoundException e1)
					{
						JOptionPane.showMessageDialog(GameWindow.this, "File not found");
					} catch (IOException e1)
					{
						JOptionPane.showMessageDialog(GameWindow.this, "Error Writing File");
					}
		
			}
		});
		menuFile.add(itemSave);

		JMenuItem itemQuit = new JMenuItem("Quit");
		itemQuit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				closeWindow();
			}
		});
		menuFile.add(itemQuit);

		JMenu menuView = new JMenu("View");
		menuBar.add(menuView);

		JMenuItem itemViewScores = new JMenuItem("View Scores");
		itemViewScores.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					Database.viewDatabase();
				} catch (Exception e1)
				{
					JOptionPane.showMessageDialog(GameWindow.this, "Database Error", "Error connecting to the database.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		menuView.add(itemViewScores);

		this.setJMenuBar(menuBar);
		// end MenuBar
	}

	/**
	 * creates the board.
	 */
	private void initBoard()
	{
		Container pane = this.getContentPane();
		pane.setLayout(new BorderLayout());

		// CREATES THE BOARD //
		/* JPanel */pnlCenterHolder = new JPanel();
		pnlCenterHolder.setLayout(null);
		pnlCenterHolder.setBackground(Color.BLUE);
		// pnlCenterHolder.addMouseMotionListener(this);
		// JFrame pnlCenterHolder = new JFrame("TEST");
		// pnlCenterHolder.setBounds(0, 0, 800, 800);
		// pnlCenterHolder.setVisible(true);
		Dimension mapSize = control.getMapSize();

		pnlGrid = new JPanel();
		pnlGrid.setOpaque(true);
		pnlGrid.setBackground(Color.black);
		pnlGrid.setSize(mapSize.width * Cell.CELL_WIDTH, mapSize.height * Cell.CELL_HEIGHT);
		pnlGrid.setLocation(0, 0);
		pnlGrid.setLayout(null);

		// makes a window to display on
		// JFrame win = new JFrame("map test");
		// win.setDefltCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// win.setBounds(100, 100, 800, 800);
		// win.setLayout(null);
		// Container pane = win.getContentPane();

		// PRINTS THE MAP TO A GRID OF LABELS
		JLabel[][] lblGrid = new JLabel[mapSize.width][mapSize.height];
		for (int x = 0; x < lblGrid.length; x++)
		{
			for (int y = 0; y < lblGrid[0].length; y++)
			{
				lblGrid[x][y] = new JLabelCell(control.getCellAt(x, y).getTerrain().getImage(), x, y);
				lblGrid[x][y].setBounds(x * Cell.CELL_WIDTH, (y * Cell.CELL_HEIGHT), Cell.CELL_WIDTH, Cell.CELL_HEIGHT);
				pnlGrid.add(lblGrid[x][y]);
				lblGrid[x][y].addMouseListener(control);
				lblGrid[x][y].addMouseMotionListener(this);
				lblGrid[x][y].addMouseListener(this);
				lblGrid[x][y].addMouseListener(popupListener);

			}
		}
		beginningTurn();
		pnlCenterHolder.add(pnlGrid);

		pane.add(pnlCenterHolder, BorderLayout.CENTER);
		// DONE CREATING THE MAP//
	}

	/**
	 * sets the image in the top right of the screen for the first persons turn.
	 */
	private void beginningTurn()
	{
		turn = new JLabel("Player 1's turn.");
		red = new ImageIcon("Images\\misc\\attackpanels.png");
		blue = new ImageIcon("Images\\misc\\movepanels.png");
		turnIcon = new JLabel(red);
		turnIcon.setBounds(10, 17, 20, 20);
		turnIcon.setBorder(BorderFactory.createLineBorder(Color.RED));
		turn.setForeground(Color.WHITE);
		turn.setVisible(true);
		turn.setBounds(50, 0, 100, 50);
		pnlCenterHolder.add(turnIcon);
		pnlCenterHolder.add(turn);
	}

	/**
	 * switches the turns between the players
	 * @param text
	 */
	public void changeTurnText(String text)
	{
		turn.setText(text);
		if (text.equals("Player 2's turn."))
		{
			turnIcon.setIcon(blue);
			turnIcon.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		} else
		{
			turnIcon.setIcon(red);
			turnIcon.setBorder(BorderFactory.createLineBorder(Color.RED));
		}

	}

	/**
	 * returns the pnlGrid.
	 * @return
	 */
	public JPanel getPnlGrid()
	{
		return this.pnlGrid;
	}

	/**
	 * creates the control panel.
	 */
	private void initControlPanel()
	{
		Container pane = this.getContentPane();

		// CREATING STATS PANEL//
		JPanel pnlStats = new JPanel();
		pnlStats.setSize(600, 300);
		pnlStats.setLayout(new BorderLayout());
		// pnlStats.setLayout(new BoxLayout(pnlStats, BoxLayout.X_AXIS));
		pnlStats.setBorder(BorderFactory.createLineBorder(Color.black));

		initStatsLbls(pnlStats);

		initStatBars(pnlStats);

		// creates and adds the panel for the buttons
		initStatsBtns(pnlStats);

		// DONE CREATING STATS PANEL//

		pane.add(pnlStats, BorderLayout.SOUTH);
	}

	/**
	 * creates the stats panel.
	 * @param pnlStats
	 */
	private void initStatsBtns(JPanel pnlStats)
	{
		JPanel pnlButtons = new JPanel();
		pnlButtons.setBorder(BorderFactory.createEtchedBorder(BLUE, GRAY));
		pnlButtons.setLayout(null);
		pnlButtons.setPreferredSize(new Dimension(310, 80));

		MoveButton movebutton = new MoveButton();
		movebutton.setLocation(5, 5);
		movebutton.addMouseListener(control);
		pnlButtons.add(movebutton);

		AttackButton attackButton = new AttackButton();
		attackButton.setLocation(5, 60);
		attackButton.addMouseListener(control);
		pnlButtons.add(attackButton);

		pnlStats.add(pnlButtons, BorderLayout.EAST);
	}

	/**
	 * creates the stats bars
	 * @param pnlStats
	 */
	private void initStatBars(JPanel pnlStats)
	{
		JPanel pnlBars = new JPanel();
		pnlBars.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10));
		pnlBars.setLayout(new BoxLayout(pnlBars, BoxLayout.Y_AXIS));

		hpBar = new JProgressBar();
		hpBar.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		hpBar.setValue(0);
		hpBar.setMaximum(0);
		hpBar.setString("" + 0);
		hpBar.setStringPainted(true);
		hpBar.setForeground(BLUE);
		pnlBars.add(hpBar);

		spBar = new JProgressBar();
		spBar.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		spBar.setValue(0);
		spBar.setMaximum(100);
		spBar.setString("" + 0);
		spBar.setStringPainted(true);
		spBar.setForeground(GRAY);
		pnlBars.add(spBar);

		atBar = new JProgressBar();
		atBar.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		atBar.setValue(0);
		atBar.setMaximum(100);
		atBar.setString("" + 0);
		atBar.setStringPainted(true);
		atBar.setForeground(BLUE);
		pnlBars.add(atBar);

		accBar = new JProgressBar();
		accBar.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		accBar.setValue(0);
		accBar.setMaximum(100);
		accBar.setString("" + 0);
		accBar.setStringPainted(true);
		accBar.setForeground(GRAY);
		pnlBars.add(accBar);

		rgBar = new JProgressBar();
		rgBar.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		rgBar.setValue(0);
		rgBar.setMaximum(100);
		rgBar.setString("" + 0);
		rgBar.setStringPainted(true);
		rgBar.setForeground(BLUE);
		pnlBars.add(rgBar);

		dfBar = new JProgressBar();
		dfBar.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		dfBar.setValue(0);
		dfBar.setMaximum(100);
		dfBar.setString("" + 0);
		dfBar.setStringPainted(true);
		dfBar.setForeground(GRAY);
		pnlBars.add(dfBar);

		pnlStats.add(pnlBars, BorderLayout.CENTER);
	}

	/**
	 * creates the stats labels.
	 * @param pnlStats
	 */
	private void initStatsLbls(JPanel pnlStats)
	{
		JPanel pnlLabels = new JPanel();
		pnlLabels.setLayout(new BoxLayout(pnlLabels, BoxLayout.Y_AXIS));
		pnlLabels.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5));

		JLabel hpLabel = new JLabel("HP");
		hpLabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 4, 4));
		pnlLabels.add(hpLabel);
		JLabel spLabel = new JLabel("SPEED");
		spLabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 4, 4));
		pnlLabels.add(spLabel);
		JLabel atLabel = new JLabel("ATTACK");
		atLabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 4, 4));
		pnlLabels.add(atLabel);
		JLabel accLabel = new JLabel("ACCURACY");
		accLabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 4, 4));
		pnlLabels.add(accLabel);
		JLabel rgLabel = new JLabel("RANGE");
		rgLabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 4, 4));
		pnlLabels.add(rgLabel);
		JLabel dfLabel = new JLabel("DEFENSE");
		dfLabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 4, 4));
		pnlLabels.add(dfLabel);

		pnlStats.add(pnlLabels, BorderLayout.WEST);
	}

	/**
	 * updates the stats bar based on what unit is selected.
	 * @param s
	 */
	public void updateStats(StatsHolder s)
	{
		if (s.isUnit())
		{
			hpBar.setMaximum(s.getMaxHp());
			hpBar.setValue(s.getHp());
			hpBar.setString(s.getHp() + "");
			spBar.setValue(s.getSpeed());
			spBar.setString(s.getSpeed() + "");
			atBar.setValue(s.getAttack());
			atBar.setString(s.getAttack() + "");
			accBar.setValue(s.getAccuracy());
			accBar.setString(s.getAccuracy() + "");
			rgBar.setValue(s.getMaxRange());
			rgBar.setString(s.getMaxRange() + "");
			dfBar.setValue(s.getDefense());
			dfBar.setString(s.getDefense() + "");
		} else if (s.isBase())
		{
			hpBar.setMaximum(s.getMaxHp());
			hpBar.setValue(s.getHp());
			hpBar.setString(s.getHp() + "");
			spBar.setValue(0);
			spBar.setString("0");
			atBar.setValue(0);
			atBar.setString("0");
			accBar.setValue(0);
			accBar.setString("0");
			rgBar.setValue(0);
			rgBar.setString("0");
			dfBar.setValue(0);
			dfBar.setString("0");
		} else if (s.isTerrain())
		{
			hpBar.setMaximum(0);
			hpBar.setValue(0);
			hpBar.setString("");
			spBar.setValue(0);
			spBar.setString("");
			atBar.setValue(0);
			atBar.setString("");
			accBar.setValue(0);
			accBar.setString("");
			rgBar.setValue(0);
			rgBar.setString("");
			dfBar.setValue(0);
			dfBar.setString("");
		}

	}

	/**
	 * saves the game to a file.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void saveToFile() throws FileNotFoundException, IOException
	{
		JFileChooser jc = new JFileChooser();
		int returnVal = jc.showSaveDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			File file = jc.getSelectedFile();
			control.save(file);
		}
	}

	/**
	 * closes the window.
	 */
	public void closeWindow()
	{
		if (Game.DEBUG)
			System.exit(0);
		int returnVal = JOptionPane.showConfirmDialog(GameWindow.this, "Are you sure?", "Quit?", JOptionPane.WARNING_MESSAGE);
		if (returnVal == JOptionPane.YES_OPTION)
		{
			JOptionPane.showMessageDialog(GameWindow.this, "Thanks for playing!");
			System.exit(0);
		}
	}



	/**
	 * gets the name of the winner.
	 * @return
	 */
	private String getWinnerName()
	{
		JOptionPane.showMessageDialog(this, "                  Victory!");

		return JOptionPane.showInputDialog(this, "Please enter your name:");

	}

	/**
	 * determines when the game is over.
	 */
	public void gameOver()
	{

		try
		{
			Database.newScore(getWinnerName(), control.getWinnerScore());
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, "Database Error", "Error adding your score to the database.", JOptionPane.ERROR_MESSAGE);
		}
	}

	// MouseMotionListener

	/**
	 * Overriding.
	 */
	public void mouseDragged(MouseEvent arg0)
	{
	}

	/**
	 * What occurs when the mouse is moved.
	 */
	public void mouseMoved(MouseEvent me)
	{
		Point pt = new Point(me.getPoint());
		SwingUtilities.convertPointToScreen(pt, (Component) me.getSource());
		// Convert a coordinate on a screen to a coordinate relative to a
		// component's bounds
		SwingUtilities.convertPointFromScreen(pt, pnlCenterHolder);
		// DONT MESS WITH THE ORDER OF THESE!!!
		// (The corners have to be first)
		// SCROLL UP/RIGHT
		if (pt.getY() < Y_BUFFER && pt.getX() > pnlCenterHolder.getWidth() - X_BUFFER)
		{
			scrollDirection = 3;
		} else
		// SCROLL UP/LEFT
		if (pt.getY() < Y_BUFFER && pt.getX() < X_BUFFER)
		{
			scrollDirection = 1;
		} else
		// SCROLL DOWN/LEFT
		if (pt.getY() > pnlCenterHolder.getHeight() - Y_BUFFER && pt.getX() < X_BUFFER)
		{
			scrollDirection = 6;
		} else
		// SCROLL DOWN/RIGHT
		if (pt.getY() > pnlCenterHolder.getHeight() - Y_BUFFER && pt.getX() > pnlCenterHolder.getWidth() - X_BUFFER)
		{
			scrollDirection = 8;
		} else
		// SCROLL RIGHT
		if (pt.getX() > pnlCenterHolder.getWidth() - X_BUFFER)
		{
			scrollDirection = 5;
		} else
		// SCROLL LEFT
		if (pt.getX() < X_BUFFER)
		{
			scrollDirection = 4;
		} else
		// SCROLL DOWN
		if (pt.getY() > pnlCenterHolder.getHeight() - Y_BUFFER)
		{
			scrollDirection = 7;
		} else
		// SCROLL UP
		if (pt.getY() < Y_BUFFER)
		{
			scrollDirection = 2;
		} else
			scrollDirection = 0;
	}

	/**
	 * Overriding.
	 */
	public void mouseClicked(MouseEvent arg0)
	{
	}

	/**
	 * Overriding.
	 */
	public void mouseEntered(MouseEvent me)
	{
	}

	/**
	 * Overriding.
	 */
	public void mouseExited(MouseEvent arg0)
	{
		scrollDirection = 0;
	}


	/**
	 * pups up the end turn option.
	 */
	public void mousePressed(MouseEvent me)
	{
		if (me.isPopupTrigger())
			System.out.println("end turn");
		// control.endTurn();
	}

	/**
	 * overriding.
	 */
	public void mouseReleased(MouseEvent arg0)
	{
	}

	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args)
	{
		new controller.Controller();
	}

	/**
	 * Class for the mouse scrolling in the game window.
	 * @author jsoriano
	 *
	 */
	class MouseScrollThread extends Thread
	{
		private Component parentScrollArea;
		
		/**
		 * constructor for the MouseScrollThread class.
		 * @param name
		 * @param parent
		 */
		public MouseScrollThread(String name, Component parent)
		{
			super(name);
			parentScrollArea = parent;
		}

		/**
		 * whats is run when the thread is started
		 */
		public void run()
		{
			while (true)
			{
				switch (scrollDirection)
				{
				case 1:
					// System.out.println("scroll up/left");
					pnlGrid.setLocation(pnlGrid.getX() + SCROLL_SPEED, pnlGrid.getY() + SCROLL_SPEED);
					break;
				case 2:
					// System.out.println("scroll up");
					pnlGrid.setLocation(pnlGrid.getX(), pnlGrid.getY() + SCROLL_SPEED);
					break;
				case 3:
					// System.out.println("scroll up/right");
					pnlGrid.setLocation(pnlGrid.getX() - SCROLL_SPEED, pnlGrid.getY() + SCROLL_SPEED);
					break;
				case 4:
					// System.out.println("scroll left");
					pnlGrid.setLocation(pnlGrid.getX() + SCROLL_SPEED, pnlGrid.getY());
					break;
				case 5:
					// System.out.println("scroll right");
					pnlGrid.setLocation(pnlGrid.getX() - SCROLL_SPEED, pnlGrid.getY());
					break;
				case 6:
					// System.out.println("scroll down/left");
					pnlGrid.setLocation(pnlGrid.getX() + SCROLL_SPEED, pnlGrid.getY() - SCROLL_SPEED);
					break;
				case 7:
					// System.out.println("scroll down");
					pnlGrid.setLocation(pnlGrid.getX(), pnlGrid.getY() - SCROLL_SPEED);
					break;
				case 8:
					// System.out.println("scroll down/right");
					pnlGrid.setLocation(pnlGrid.getX() - SCROLL_SPEED, pnlGrid.getY() - SCROLL_SPEED);
					break;
				}
				if (pnlGrid.getY() > 0)
					pnlGrid.setLocation(pnlGrid.getX(), 0);
				if (pnlGrid.getY() + pnlGrid.getHeight() < parentScrollArea.getHeight())
					pnlGrid.setLocation(pnlGrid.getX(), parentScrollArea.getHeight() - pnlGrid.getHeight());
				if (pnlGrid.getX() + pnlGrid.getWidth() < parentScrollArea.getWidth())
					pnlGrid.setLocation(parentScrollArea.getWidth() - pnlGrid.getWidth(), pnlGrid.getY());
				if (pnlGrid.getX() > 0)
					pnlGrid.setLocation(0, pnlGrid.getY());
				try
				{
					Thread.sleep(DELAY);
				} catch (InterruptedException e)
				{
					JOptionPane.showMessageDialog(null, "Mouse Scrolling Error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				repaint();
			}
		}
	}

	/**
	 * Class for the listener of the right click.
	 * @author jsoriano
	 *
	 */
	class PopupListener extends MouseAdapter
	{
		public void mousePressed(MouseEvent e)
		{
			possiblyShowPopup(e);
		}

		public void mouseReleased(MouseEvent e)
		{
			possiblyShowPopup(e);
		}

		private void possiblyShowPopup(MouseEvent e)
		{
			if (e.isPopupTrigger())
			{
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	/**
	 * Overriding.
	 */
	public void windowActivated(WindowEvent arg0)
	{
	}

	/**
	 * Overriding.
	 */
	public void windowClosed(WindowEvent arg0)
	{
	}
	
	/**
	 * Overriding.
	 */
	public void windowClosing(WindowEvent arg0)
	{
		closeWindow();
	}


	/**
	 * Overriding.
	 */
	public void windowDeactivated(WindowEvent arg0)
	{
	}

	/**
	 * Overriding.
	 */
	public void windowDeiconified(WindowEvent arg0)
	{
	}

	/**
	 * Overriding.
	 */
	public void windowIconified(WindowEvent arg0)
	{
	}

	/**
	 * Overriding.
	 */
	public void windowOpened(WindowEvent arg0)
	{
	}
}
