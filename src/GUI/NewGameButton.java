package GUI;

import java.awt.event.MouseEvent;

import controller.Controller;

/**
 * @author jsoriano
 * @author kjacob
 */
public class NewGameButton extends AbstractCustomButton
{
	private static final long serialVersionUID = 1L;
	private Controller ctrl;
	private StartWindow startWin;
	
	/**
	 * creates a new game button.
	 * @param s
	 * @param c
	 */
	public NewGameButton(StartWindow s, Controller c)
	{
		super("Images\\Buttons\\newgame.png", "Images\\Buttons\\newgameselected.png", "Images\\Buttons\\newgamehighlight.png");
		ctrl = c;
		startWin = s;
	}

	/**
	 * what occurs when the mouse is released.
	 */
	public void mouseReleased(MouseEvent arg0)
	{
		super.mouseReleased(arg0);
		startWin.newGame();
	}
}
