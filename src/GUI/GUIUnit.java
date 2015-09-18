package GUI;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * @author jsoriano
 * @author kjacob
 */
public abstract class GUIUnit extends JPanel
{
	protected static final int LEFT = 1;
	protected static final int DOWN = 2;
	protected static final int RIGHT = 3;
	protected static final int UP = 4;
	protected AudioClip attacking;
	protected AudioClip dying;
	private String[] audioURLS;
	private String[] imageURLS;
	protected ImageIcon[] images;
	protected JLabel[] labels;
	protected File attack;
	protected File death;
	protected int cellx;
	protected int celly;
	protected int x;
	protected int y;
	
	/**
	 * constructor for the GUI unit.
	 */
	public GUIUnit()
	{
		
	}
	
	/**
	 * creates the frame for the GUIUnit
	 */
	public void Frame()
	{
		cellx = cellx*50;
		celly = celly*50;
		if(cellx == 0)
		{
			cellx = 0;
		}
		if(celly == 0)
		{
			celly = 0;
		}
		this.setBounds(cellx, celly, 50, 50);
		this.setLayout(null);
		this.setOpaque(false);
		this.setVisible(true);
	}
	
	/**
	 * move method for the GUIUnit.
	 */
	public void move(int cells, int dir)
	{
		switch (dir)
		{
			case LEFT:
				moveLeft(cells);
				break;
			case DOWN:
				moveDown(cells);
				break;
			case RIGHT:
				moveRight(cells);
				break;
			case UP:
				moveUp(cells);
				break;
		}
	}
	
	public void attack(int dir)
	{
		attacking.play();

			switch (dir)
			{
			case 1:
				attackLeft();
				break;
			case 2:
				attackDown();
				break;
			case 3:
				attackRight();
				break;
			case 4:
				attackUp();
				break;
			}

	}
	
	public void moveLeft(int cells){}
	public void moveDown(int cells){}
	public void moveRight(int cells){}
	public void moveUp(int cells){}

	public void attackLeft(){}
	public void attackDown(){}
	public void attackRight(){}
	public void attackUp(){}

	public void dead(){}

	


}
