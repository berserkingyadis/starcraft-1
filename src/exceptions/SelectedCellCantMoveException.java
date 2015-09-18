package exceptions;


/**
 * thrown when the selected cell is not movable<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class SelectedCellCantMoveException extends CellException
{
	private static final long serialVersionUID = 1L;
	public static final String MESSAGE = "That cell can not move";
	
	/**
	 * constructor <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 * @param cellX - the x-coord of the selected cell
	 * @param cellY - the y-coord of the selected cell
	 */
	public SelectedCellCantMoveException(int cellX, int cellY)
	{
		super(cellX, cellY, MESSAGE);
	}
	/**
	 * default constructor <br>        
	 *
	 * <hr>
	 * Date created: Jun 6, 2010 <br>
	 * Date last modified: Jun 6, 2010 <br>
	 *
	 * <hr>
	 */
	public SelectedCellCantMoveException()
	{
		super(MESSAGE);
	}
	
}
