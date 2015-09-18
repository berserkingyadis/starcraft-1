package GUI;

import java.awt.event.MouseEvent;

/**
 * @author jsoriano
 * @author kjacob
 */
public class LoadGameButton extends AbstractCustomButton
{
	private static final long serialVersionUID = 1L;
	private StartWindow startWin;
	/**
	 * Creates the load game button.
	 * @param s
	 */
	public LoadGameButton(StartWindow s)
	{
		super("Images\\Buttons\\loadgame.png", "Images\\Buttons\\loadgameselected.png", "Images\\Buttons\\loadgamehighlight.png");
		startWin = s;
	}
	@Override
	
	/** 
	 * what occurs when the mouse is released.
	 */
	public void mouseReleased(MouseEvent arg0)
	{
		super.mouseReleased(arg0);
		startWin.makeLoadMenuWindow();
	}
}
