package GUI;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import model.Cell;

/**
 * @author jsoriano
 * @author kjacob
 */
public class GUIZealot extends GUIUnit
{
	



	private String[] imageURLS =
	{ "Images\\Obstacles\\UnitImages\\Protoss\\ZealotStanding.gif", "Images\\Obstacles\\UnitImages\\Protoss\\ZealotMoveLeft.gif", "Images\\Obstacles\\UnitImages\\Protoss\\ZealotMoveDown.gif", "Images\\Obstacles\\UnitImages\\Protoss\\ZealotMoveRight.gif", "Images\\Obstacles\\UnitImages\\Protoss\\ZealotMoveUp.gif", "Images\\Obstacles\\UnitImages\\Protoss\\ZealotAttackLeft.gif", "Images\\Obstacles\\UnitImages\\Protoss\\ZealotAttackDown.gif", "Images\\Obstacles\\UnitImages\\Protoss\\ZealotAttackRight.gif", "Images\\Obstacles\\UnitImages\\Protoss\\ZealotAttackUp.gif", "Images\\Obstacles\\UnitImages\\Terran\\MarineUsed.gif", "Images\\Obstacles\\UnitImages\\UnitDeath.gif" };
	private String[] audioURLS =
	{ "Audio\\Attack\\ZealotAttack747.au","Audio\\Attack\\ZealotDeath.au"  };
	
	/**
	 * default constructor for the GUIZealot.
	 */
	public GUIZealot(){}
	
	/**
	 * Constructor for GUIZealot that sets the location of the zealot.
	 * @param cellx
	 * @param celly
	 */
	public GUIZealot(int cellx, int celly)
	{
		this.cellx = cellx;
		this.celly = celly;
		
		makeLabels();
		Frame();
		

	}

	/**
	 * testing for the unit.
	 */
	public void moveEm()
	{
//		move(4,DOWN);
//		move(4,UP);
//		move(4,LEFT);
//		move(4,UP);
		attack(UP);
		dead();

	}

	/**
	 * moves the unit left.
	 */
	public void moveLeft(int cells)
	{
		try
		{
			Thread.sleep(200);
			labels[0].setVisible(false);
			labels[1].setVisible(true);

			for (int i = 0; i < cells * 50; i++)
			{
				this.setBounds(x - i, y,50,50);
				Thread.sleep(5);

			}
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		labels[1].setVisible(false);
		labels[0].setVisible(true);
		x = this.getX();
		y = this.getY();

	}

	/**
	 * moves the unit down.
	 */
	public void moveDown(int cells)
	{
		try
		{
			Thread.sleep(200);
			labels[0].setVisible(false);
			labels[2].setVisible(true);

			for (int i = 0; i < cells * 50; i++)
			{
				this.setBounds(x, y + i, 50,50);
				Thread.sleep(5);
			}
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		labels[2].setVisible(false);
		labels[0].setVisible(true);
		x = this.getX();
		y = this.getY();
	}

	public void moveRight(int cells)
	{
		try
		{
			Thread.sleep(200);
			labels[0].setVisible(false);
			labels[3].setVisible(true);

			for (int i = 0; i < cells * 50; i++)
			{
				this.setBounds(x + (i), y,50,50);
				Thread.sleep(5);

			}
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		labels[3].setVisible(false);
		labels[0].setVisible(true);
		x = this.getX();
		y = this.getY();
	}

	/**
	 * moves the unit up.
	 */
	public void moveUp(int cells)
	{
		try
		{

			Thread.sleep(200);
			labels[0].setVisible(false);
			labels[4].setVisible(true);
			for (int i = 0; i < cells * 50; i++)
			{
				this.setBounds(x, y - i,50,50);
				Thread.sleep(5);

			}
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		labels[4].setVisible(false);
		labels[0].setVisible(true);
		x = this.getX();
		y = this.getY();
	}

	/**
	 * makes the unit attack based on an integer that is passed into the parameters.
	 */
	public void attack(int dir)
	{
		try
		{
			attacking.play();
			if (dir == RIGHT)
			{
				labels[0].setVisible(false);
				labels[7].setVisible(true);
				Thread.sleep(747);
				labels[7].setVisible(false);
				labels[0].setVisible(true);
			} else if (dir == LEFT)
			{
				labels[0].setVisible(false);
				labels[5].setVisible(true);
				Thread.sleep(747);
				labels[5].setVisible(false);
				labels[0].setVisible(true);
			} else if (dir == DOWN)
			{
				labels[0].setVisible(false);
				labels[6].setVisible(true);
				Thread.sleep(747);
				labels[6].setVisible(false);
				labels[0].setVisible(true);
			} else if (dir == UP)
			{
				labels[0].setVisible(false);
				labels[8].setVisible(true);
				Thread.sleep(747);
				labels[8].setVisible(false);
				labels[0].setVisible(true);
			}

		} catch (InterruptedException e)
		{
			System.out.println("You royally messed up.");
		}
	}

	/**
	 * Simulates death for the unit.
	 */
	public void dead()
	{
		labels[0].setVisible(false);
		labels[10].setVisible(true);
		dying.play();
		try
		{
			Thread.sleep(800);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		labels[10].setVisible(false);
	}

	/**
	 * creates the labels for the units.
	 */
	public void makeLabels()
	{
		images = new ImageIcon[11];
		labels = new JLabel[11];
		for (int i = 0; i < 11; i++)
		{
			images[i] = new ImageIcon(imageURLS[i]);
			labels[i] = new JLabel(images[i]);
			labels[i].setSize(50, 50);
			this.add(labels[i]);
			labels[i].setVisible(false);
		}

		labels[0].setSize(50, 50);
		labels[0].setVisible(true);
		
		this.add(labels[0]);
		
		attack = new File(audioURLS[0]);
		death = new File(audioURLS[1]);
		try
		{
			attacking = Applet.newAudioClip(attack.toURI().toURL());
			dying = Applet.newAudioClip(death.toURI().toURL());
			
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		x = cellx*50;
		y = celly*50;
	}
//	public static void main(String[] args)
//	{
//		JFrame frame = new JFrame();
//		frame.setBounds(100,100,1000,1000);
//		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		frame.setLayout(null);
//		frame.setVisible(true);
//		frame.setBackground(Color.RED);
//		GUIZealot a = new GUIZealot(0, 0);
//		GUIZealot b = new GUIZealot(3, 3);
//		GUIZealot c = new GUIZealot(5, 5);
//		GUIZealot d = new GUIZealot(7, 7);
//		frame.add(a);
//		frame.add(b);
//		frame.add(c);
//		frame.add(d);
//		frame.repaint();
//		
//		a.moveEm();
//		b.moveEm();
//		c.moveEm();		
//		d.moveEm();
//		
//		
//	}

}
