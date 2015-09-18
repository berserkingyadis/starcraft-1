package GUI;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author jsoriano
 * @author kjacob
 */
public class GUIBase extends JPanel
{
	protected ImageIcon[] images;
	protected JLabel[] labels;
	protected boolean active;
	protected int cellx;
	protected int celly;
	protected int x;
	protected int y;

	/**
	 * default consttructor for the base.
	 */
	public GUIBase()
	{
		
	}
	
	/**
	 * sets the frame for the base.
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
	 * what occurs when the base dies.
	 */
	public void dead()
	{
		
	}
	
}
