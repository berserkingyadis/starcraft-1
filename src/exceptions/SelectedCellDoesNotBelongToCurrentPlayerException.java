package exceptions;


/**
 * thrown when the current player doesn't own the selected cell and tries to perform an action of that cell<br>
 *
 * <hr>
 * Date created: Jun 6, 2010<br>
 * Date last modified: Jun 6, 2010<br>
 * <hr>
 * @author Glen Watson
 */
public class SelectedCellDoesNotBelongToCurrentPlayerException extends CellException
{
	private static final long serialVersionUID = 1L;
}
