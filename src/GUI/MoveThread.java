package GUI;
import model.obstacle.Unit;

/**
 * @author jsoriano
 * @author kjacob
 */
public class MoveThread extends Thread
{
	private GUIUnit unit;
	private int hspaces;
	private int hdir;
	private int vdir;
	private int vspaces;
	
	/**
	 * creates a thread for movement.
	 * @param unit
	 * @param hspaces
	 * @param hdir
	 * @param vspaces
	 * @param vdir
	 */
	public MoveThread(GUIUnit unit, int hspaces, int hdir, int vspaces, int vdir)
	{
		this.unit = unit;
		this.hspaces = hspaces;
		this.vspaces = vspaces;
		this.vdir = vdir;
		this.hdir = hdir;
	}
	
	/**
	 * what occurs when the thread is started
	 */
	public void run()
	{
		
		unit.move(hspaces, hdir);
		unit.move(vspaces, vdir);
	}
}	
