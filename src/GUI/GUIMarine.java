package GUI;

import java.applet.Applet;
import java.awt.Color;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * @author jsoriano
 * @author kjacob
 */
public class GUIMarine extends GUIUnit
{


	private String[] imageURLS =
	{ "Images\\Obstacles\\UnitImages\\Terran\\MarineStanding.png", "Images\\Obstacles\\UnitImages\\Terran\\MarineMoveHorizontalLeft.gif", "Images\\Obstacles\\UnitImages\\Terran\\MarineMoveVerticalDown.gif", "Images\\Obstacles\\UnitImages\\Terran\\MarineMoveHorizontalRight.gif", "Images\\Obstacles\\UnitImages\\Terran\\MarineMoveVerticalUp.gif", "Images\\Obstacles\\UnitImages\\Terran\\MarineShootLeft.gif", "Images\\Obstacles\\UnitImages\\Terran\\MarineShootDown.gif", "Images\\Obstacles\\UnitImages\\Terran\\MarineShootRight.gif", "Images\\Obstacles\\UnitImages\\Terran\\MarineShootUp.gif", "Images\\Obstacles\\UnitImages\\Terran\\MarineUsedpng", "Images\\Obstacles\\UnitImages\\unitdeath.gif" };
	private String[] audioURLS =
	{ "Audio\\Attack\\MarineFire1943.au", "Audio\\Attack\\MarineDeath.au" };

	/**
	 * default constructor for the GUIMarine.
	 */
	public GUIMarine()
	{
	}

	/**
	 * Constructor that gets the cells for location and creates the labels/frame for the JPanel of the unit.
	 * @param cellx
	 * @param celly
	 */
	public GUIMarine(int cellx, int celly)
	{
		this.cellx = cellx;
		this.celly = celly;

		makeLabels();
		Frame();

	}
	
	/**
	 * method for testing movement.
	 */
	public void moveEm()
	{
		move(2, RIGHT);
		attack(LEFT);

	}

	/**
	 * makes the unit move left.
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
				this.setBounds(x - i, y, 50, 50);
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
	 * makes the unit move downwards.
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
				this.setBounds(x, y + i, 50, 50);
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

	/**
	 * makes the unit move right.
	 */
	public void moveRight(int cells)
	{
		try
		{
			Thread.sleep(200);
			labels[0].setVisible(false);
			labels[3].setVisible(true);

			for (int i = 0; i < cells * 50; i++)
			{
				this.setBounds(x + (i), y, 50, 50);
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
	 * makes the unit move up.
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
				this.setBounds(x, y - i, 50, 50);
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
	 * makes the unit attack left.
	 */
	public void attackLeft()
	{
		attacking.play();
		try
		{
			labels[0].setVisible(false);
			labels[5].setVisible(true);
			Thread.sleep(1800);
			labels[5].setVisible(false);
			labels[0].setVisible(true);
		} catch (InterruptedException e)
		{

		}
	}

	/**
	 * makes the unit attack down.
	 */
	public void attackDown()
	{
		attacking.play();

		try
		{
			labels[0].setVisible(false);
			labels[6].setVisible(true);
			Thread.sleep(1800);
			labels[6].setVisible(false);
			labels[0].setVisible(true);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * makes the unit attack right.
	 */
	public void attackRight()
	{
		attacking.play();
		try
		{
			labels[0].setVisible(false);
			labels[7].setVisible(true);
			Thread.sleep(1800);
			labels[7].setVisible(false);
			labels[0].setVisible(true);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * makes the unit attack up.
	 */
	public void attackUp()
	{
		attacking.play();

		try
		{
			labels[0].setVisible(false);
			labels[8].setVisible(true);
			Thread.sleep(1800);
			labels[8].setVisible(false);
			labels[0].setVisible(true);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * simulates death for the unit.
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
	 * creates the labels for the unit's images.
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

		x = cellx * 50;
		y = celly * 50;
	}

//	public static void main(String[] args)
//	{
//		JFrame frame = new JFrame();
//		frame.setBounds(100, 100, 1000, 1000);
//		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		frame.setLayout(null);
//		frame.setVisible(true);
//		frame.setBackground(Color.RED);
//		GUIMarine a = new GUIMarine(0, 0);
//		GUIMarine b = new GUIMarine(3, 3);
//		GUIMarine c = new GUIMarine(5, 5);
//		GUIMarine d = new GUIMarine(7, 7);
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
//	}

}
