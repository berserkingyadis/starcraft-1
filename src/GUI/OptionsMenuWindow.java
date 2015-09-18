package GUI;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import model.Game;

import controller.Controller;


/**
 * @author jsoriano
 * @author kjacob
 */
public class OptionsMenuWindow extends JFrame implements MouseListener
{
	private Controller ctrl;
	private ImageIcon icon;
	private static final int TERRAN = 1;
	private static final int PROTOSS = 3;
	private static final int ZERG = 2;
	private String[] audioURL = {"Audio\\Buttons\\swishin.au", "Audio\\Buttons\\mouseover.au", "Audio\\Buttons\\mousedown2.au","Audio\\Buttons\\pzerdy00.au","Audio\\Buttons\\tmawht02.au","Audio\\Buttons\\zzeyes02.au"};
	private AudioClip enter;
	private AudioClip over;
	private AudioClip click;
	private AudioClip pSound;
	private AudioClip zSound;
	private AudioClip tSound;
	private JLabel mapOne;
	private JLabel mapTwo;
	private JLabel mapThree;
	private JLabel mapClick;
	private JLabel unitClick1;
	private JLabel unitClick2;
	private ImageIcon map1;
	private ImageIcon map2;
	private ImageIcon map3;
	private ImageIcon map1high;
	private ImageIcon map2high;
	private ImageIcon map3high;
	private ImageIcon terranIcon;
	private ImageIcon terranHigh;
	private ImageIcon terranClicked1;
	private ImageIcon terranClicked2;
	private ImageIcon zergIcon;
	private ImageIcon zergHigh;
	private ImageIcon zergClicked1;
	private ImageIcon zergClicked2;
	private ImageIcon protossIcon;
	private ImageIcon protossHigh;
	private ImageIcon protossClicked1;
	private ImageIcon protossClicked2;
	private JLabel terran;
	private JLabel zerg;
	private JLabel protoss;
	private JLabel background;
	private JPanel mapPanel;
	private JPanel teamPanel;
	private JPanel button;
	private boolean player2;
	private int mapNum;
	private int teamNum1;
	private int teamNum2;

	/**
	 * constructor for the options menu window.
	 * @param ctrl
	 */
	public OptionsMenuWindow(Controller ctrl)
	{
		//basic JFrame stuff
		super("OPTIONS");
		setFrameProperties(ctrl);
		addMapChoices();
		addUnitChoices();
		addStartButton(ctrl);
		addAudio();
		//creates background
		setBackground();
		makeIcons();
		finalSteps();

	}

	/**
	 * conducts the final steps for creating the Options Menu
	 */
	private void finalSteps()
	{
		teamPanel.add(unitClick1, 1);
		teamPanel.add(unitClick2, 1);
		mapClick.setVisible(false);
		mapPanel.add(mapClick, 1);
		repaint();
		player2 = true;
	}

	/**
	 * sets the background of the Options Menu
	 */
	private void setBackground()
	{
		icon = new ImageIcon("Images\\misc\\optionbackground.gif");
		background = new JLabel(icon);
		background.setBounds(0, 0, 850, 700);
		this.add(background);
	}

	/**
	 * adds the start button to the Options Menu
	 * @param ctrl
	 */
	private void addStartButton(Controller ctrl)
	{
		//button holder
		button = new JPanel();
		button.setBounds(275, 550, 300, 150);
		button.setLayout(null);
		this.add(button);

		//Start button
		StartButton startButton = new StartButton(this, ctrl);
		startButton.setLocation(0, 0);
		button.add(startButton);
		button.setOpaque(false);
		this.add(button);
		this.setVisible(true);
	}

	/**
	 * adds the different choices of units
	 */
	private void addUnitChoices()
	{
		//creates unit team panels
		teamPanel = new JPanel();
		teamPanel.setBounds(0, 375, 850, 200);
		teamPanel.setOpaque(false);
		teamPanel.setLayout(null);
		this.add(teamPanel);

		//creates terran icons
		terranIcon = new ImageIcon("Images\\misc\\TerranIcon.png");
		terran = new JLabel(terranIcon);
		terran.setBounds(100, 0, 170, 170);
		terran.addMouseListener(this);
		teamPanel.add(terran);

		//creates zerg icons
		zergIcon = new ImageIcon("Images\\misc\\zergIcon.png");
		zerg = new JLabel(zergIcon);
		zerg.setBounds(325, 0, 170, 170);
		zerg.addMouseListener(this);
		teamPanel.add(zerg);

		//creates protoss icons
		protossIcon = new ImageIcon("Images\\misc\\protossIcon.png");
		protoss = new JLabel(protossIcon);
		protoss.setBounds(550, 0, 170, 180);
		protoss.addMouseListener(this);
		teamPanel.add(protoss);
	}

	/**
	 * adds the different choices of maps
	 */
	private void addMapChoices()
	{
		//creates map panels
		mapPanel = new JPanel();
		mapPanel.setBounds(0, 120, 850, 170);
		mapPanel.setOpaque(false);
		mapPanel.setLayout(null);
		this.add(mapPanel);

		//creates first map
		map1 = new ImageIcon("Images\\misc\\Map1.png");
		mapOne = new JLabel(map1);
		mapOne.addMouseListener(this);
		mapOne.setBounds(100, 0, 170, 170);
		mapPanel.add(mapOne);

		//creates second map
		map2 = new ImageIcon("Images\\misc\\Map1.png");
		mapTwo = new JLabel(map2);
		mapTwo.setBounds(325, 0, 170, 170);
		mapTwo.addMouseListener(this);
		mapPanel.add(mapTwo);

		//creates third map
		map3 = new ImageIcon("Images\\misc\\Map1.png");
		mapThree = new JLabel(map3);
		mapThree.setBounds(550, 0, 170, 170);
		mapThree.addMouseListener(this);
		mapPanel.add(mapThree);
	}

	/**
	 * sets the properties for the frames
	 * @param ctrl
	 */
	private void setFrameProperties(Controller ctrl)
	{
		this.setSize(850, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.ctrl = ctrl;
	}

	/**
	 * sets all the values for the Image Icons
	 */
	public void makeIcons()
	{
		map1high = new ImageIcon("Images\\misc\\Map1highlight.png");
		map3high = new ImageIcon("Images\\misc\\MapLocked.png");
		map2high = new ImageIcon("Images\\misc\\Map1highlight.png");
		zergHigh = new ImageIcon("Images\\misc\\zergHigh.png");
		zergClicked1 = new ImageIcon("Images\\misc\\zergClick1.png");
		zergClicked2 = new ImageIcon("Images\\misc\\zergClick2.png");
		terranHigh = new ImageIcon("Images\\misc\\terranHigh.png");
		terranClicked1 = new ImageIcon("Images\\misc\\terranClick1.png");
		terranClicked2 = new ImageIcon("Images\\misc\\terranClick2.png");
		protossHigh = new ImageIcon("Images\\misc\\protossHigh.png");
		protossClicked1 = new ImageIcon("Images\\misc\\protossClick1.png");
		protossClicked2 = new ImageIcon("Images\\misc\\protossClick2.png");
		mapClick = new JLabel(map1high);
		mapClick.setBounds(0, 0, 170, 170);
		unitClick1 = new JLabel();
		unitClick1.setBounds(0, 0, 170, 170);
		unitClick2 = new JLabel();
		unitClick2.setBounds(0,0,170,170);
	}

	/**
	 * adds audio
	 */
	public void addAudio()
	{
		try
		{

			File entering = new File(audioURL[0]);
			File overing = new File(audioURL[1]);
			File clicking = new File(audioURL[2]);
			File protossSound = new File(audioURL[3]);
			File terranSound = new File(audioURL[4]);
			File zergSound = new File(audioURL[5]);
			
			enter = Applet.newAudioClip(entering.toURI().toURL());
			click = Applet.newAudioClip(clicking.toURI().toURL());
			over = Applet.newAudioClip(overing.toURI().toURL());
			pSound = Applet.newAudioClip(protossSound.toURI().toURL());
			tSound = Applet.newAudioClip(terranSound.toURI().toURL());
			zSound = Applet.newAudioClip(zergSound.toURI().toURL());
			
		} catch (MalformedURLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		enter.play();
	}

	@Override
	public void mouseClicked(MouseEvent arg0){}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		if (e.getSource() == mapOne)
		{
			mapOne.setIcon(map1high);
			over.play();
		} else if (e.getSource() == mapTwo)
		{
			mapTwo.setIcon(map3high);
			over.play();

		} else if (e.getSource() == mapThree)
		{
			mapThree.setIcon(map3high);
			over.play();

		} else if (e.getSource() == zerg)
		{
			zerg.setIcon(zergHigh);
			over.play();

		} else if (e.getSource() == terran)
		{
			terran.setIcon(terranHigh);
			over.play();

		} else if (e.getSource() == protoss)
		{
			protoss.setIcon(protossHigh);
			over.play();
		}
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		if (e.getSource() == mapOne)
		{
			mapOne.setIcon(map1);
		} else if (e.getSource() == mapTwo)
		{
			mapTwo.setIcon(map2);
		} else if (e.getSource() == mapThree)
		{
			mapThree.setIcon(map3);
		} else if (e.getSource() == zerg)
		{
			zerg.setIcon(zergIcon);
		} else if (e.getSource() == terran)
		{
			terran.setIcon(terranIcon);
		} else if (e.getSource() == protoss)
		{
			protoss.setIcon(protossIcon);
		}
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		if (e.getSource() == mapOne)
		{
			mapClick.setVisible(true);

			mapClick.setLocation(mapOne.getX(), mapOne.getY());
			mapClick.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			mapOne.setVisible(false);
			mapTwo.setVisible(true);
			mapTwo.setBorder(null);
			mapThree.setBorder(null);
			click.play();

		} //else if (e.getSource() == mapTwo)
//		{
//			mapClick.setVisible(true);
//			mapClick.setLocation(mapTwo.getX(), mapTwo.getY());
//			mapClick.setBorder(BorderFactory.createLineBorder(Color.WHITE));
//			mapOne.setVisible(true);
//			mapTwo.setVisible(false);
//			mapOne.setBorder(null);
//			mapThree.setBorder(null);
//			click.play();
//
////		} else if (e.getSource() == zerg)
//		{
//			if (player2)
//			{
//				unitClick1.setLocation(zerg.getX(), zerg.getY());
//				unitClick1.setIcon(zergClicked1);
//				teamNum1 = ZERG;
//				player2 = false;
//				
//			} else
//			{
//				unitClick2.setLocation(zerg.getX(), zerg.getY());
//				unitClick2.setIcon(zergClicked2);
//				teamNum2 = ZERG;
//				player2 = true;
//			}
//			zSound.play();
//
//		} 
		else if (e.getSource() == terran)
		{
			if (player2)
			{
				unitClick1.setIcon(terranClicked1);
				unitClick1.setLocation(terran.getX(), terran.getY());
				teamNum1 = TERRAN;
				player2 = false;
			} else
			{
				unitClick2.setIcon(terranClicked2);
				unitClick2.setLocation(terran.getX(), terran.getY());
				teamNum2 = TERRAN;
				player2 = true;

			}
			tSound.play();

		} else if (e.getSource() == protoss)
		{
			if (player2)
			{
				unitClick1.setLocation(protoss.getX(), protoss.getY() + 5);
				unitClick1.setIcon(protossClicked1);
				teamNum1 = PROTOSS;
				player2 = false;
			} else
			{
				unitClick2.setLocation(protoss.getX(), protoss.getY() + 5);
				unitClick2.setIcon(protossClicked2);
				teamNum2 = PROTOSS;
				player2 = true;

			}
			pSound.play();

		}

	}
	
	/**
	 * gets the team for player1.
	 * @return
	 */
	public int getPlayer1()
	{
		return teamNum1;
	}
	
	/**
	 * gets the team for player 2.
	 * @return
	 */
	public int getPlayer2()
	{
		return teamNum2;
	}

	/**
	 * gets the map that is going to be used.
	 * @return
	 */
	public int getMap()
	{
		return mapNum;
	}
	@Override
	public void mouseReleased(MouseEvent arg0){}
}
