package exceptions;



/**
 * thrown when the cell selected is not on the map<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class InvalidCellException extends CellException
{
	private static final long serialVersionUID = 1L;
	public static final String MESSAGE = "That cell is not on the map";
	/**
	 * Default constructor <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 */
	public InvalidCellException()
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
	public InvalidCellException(int x, int y)
	{
		super(x, y, MESSAGE);
	}
}
