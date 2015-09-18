package GUI;

/**
 * @author jsoriano
 * @author kjacob
 */
public class DeadThread extends Thread
{
	private GUIUnit unit;
	private GUIBase base;

	/**
	 * Thread made when a unit dies.
	 * @param unit
	 */
	public DeadThread(GUIUnit unit)
	{
		this.unit = unit;
	}

	/**
	 * constructor for when a base dies.
	 * @param base
	 */
	public DeadThread(GUIBase base)
	{
		this.base = base;
	}

	/**
	 * what is run when the thread is started.
	 */
	public void run()
	{
		try
		{
			unit.dead();
		} catch (NullPointerException e)
		{
		}
	}
}