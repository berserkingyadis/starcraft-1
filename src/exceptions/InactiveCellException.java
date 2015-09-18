package exceptions;



/**
 * thrown when a player tries to perform an action on an inactive cell<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class InactiveCellException extends CellException
{
	private static final long serialVersionUID = 1L;
	public static final String MESSAGE = "That unit has already moved";
	/**
	 * default constructor <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 */
	public InactiveCellException()
	{
		super(MESSAGE);
	}
	/**
	 * constructor <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param x - the selected x-coord
	 * @param y - the selected y-coord
	 */
	public InactiveCellException(int x, int y)
	{
		super(x, y, MESSAGE);
	}
}
