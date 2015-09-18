package GUI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * @author jsoriano
 * @author kjacob
 */
public class JLabelCell extends JLabel
{
	private static final long serialVersionUID = 3919347800964766667L;
	private int xPos;
	private int yPos;
	/**
	 * Creates a JLabel cell.
	 * @param icon
	 * @param x
	 * @param y
	 */
	public JLabelCell(ImageIcon icon, int x, int y)
	{
		super(icon);
		xPos = x;
		yPos = y;
	}
	
	/**
	 * returns the Xposition of the cell.
	 * @return
	 */
	public int getXPos()
	{
		return xPos;
	}
	
	/**
	 * returns the YPosition of the cell.
	 * @return
	 */
	public int getYPos()
	{
		return yPos;
	}
}
