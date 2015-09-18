package GUI;
import javax.swing.JOptionPane;

import model.obstacle.Unit;
/**
 * @author jsoriano
 * @author kjacob
 */

public class AttackThread extends Thread
{
	private GUIUnit unit;
	private int dir;
	
	/**
	 * Creates a thread for attacking.
	 * @param unit
	 * @param dir
	 */
	public AttackThread(GUIUnit unit, int dir)
	{
		this.unit = unit;
		this.dir = dir;
	}
	
	public void run()
	{
		try{
		unit.attack(dir);
		}
		catch(NullPointerException e)
		{
		}
		
	}
}	
