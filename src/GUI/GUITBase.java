package GUI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * @author jsoriano
 * @author kjacob
 */
public class GUITBase extends GUIBase
{
	private String[] imageURLS = {"Images\\Obstacles\\bases\\terranBase.png", "Images\\Obstacles\\bases\\baseDestroyed.png"};
	
	/**
	 * Constructor for the terran base.
	 * @param cellx
	 * @param celly
	 */
	public GUITBase(int cellx, int celly)
	{
		this.cellx = cellx;
		this.celly = celly;
		makeLabels();
		Frame();
	}
	
	/**
	 * Creates labels.
	 */
	public void makeLabels()
	{
		images = new ImageIcon[2];
		labels = new JLabel[2];
		for (int i = 0; i < 2; i++)
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
		x = cellx * 50;
		y = celly * 50;
	}
	
	/**
	 * kills the base.
	 */
	public void deadBase()
	{
		labels[1].setVisible(true);
		this.remove(labels[0]);
		this.add(labels[1]);
	}
}
