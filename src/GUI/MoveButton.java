package GUI;

import java.awt.event.MouseEvent;

/**
 * @author jsoriano
 * @author kjacob
 */
public class MoveButton extends AbstractCustomButton
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * sets the images for the move button.
	 */
	public MoveButton()
	{
		super("Images\\Buttons\\movebutton.png", "Images\\Buttons\\movebuttonselected.png","Images\\Buttons\\movebuttonhighlighted.png" );
	}
}
