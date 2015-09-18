package exceptions;


/**
 * thrown when the selected cell doesn't attack<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class SelectedCellCantAttackException extends CellException
{
	private static final long serialVersionUID = 1L;
	public static final String MESSAGE = "That cell can not attack";
	/**
	 * default contructor <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 */
	public SelectedCellCantAttackException()
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
	public SelectedCellCantAttackException(int x, int y)
	{
		super(x, y, MESSAGE);
	}
}