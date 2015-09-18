package GUI;

import java.awt.event.MouseEvent;

/**
 * @author jsoriano
 * @author kjacob
 */
public class AttackButton extends AbstractCustomButton
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * creates the attack button.
	 */
	public AttackButton()
	{
		super("Images\\Buttons\\attackbutton.png", "Images\\Buttons\\attackbuttonselected.png", "Images\\Buttons\\attackbuttonhighlight.png");
	}
}
