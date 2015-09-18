package GUI;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import controller.Controller;

/**
 * @author jsoriano
 * @author kjacob
 */
public class StartButton extends AbstractCustomButton
{
	private static final long serialVersionUID = 1L;
	private Controller ctrl;
	private OptionsMenuWindow optionWin;

	/**
	 * constructor for the start button class.
	 * @param s
	 * @param c
	 */
	
	public StartButton(OptionsMenuWindow s, Controller c)
	{
		super("Images\\Buttons\\startgame.png", "Images\\Buttons\\startgameselected.png", "Images\\Buttons\\startgamehighlight");
		ctrl = c;
		optionWin = s;
	}

	@Override
	
	/**
	 * what occurs when the mouse is released.
	 */
	public void mouseReleased(MouseEvent arg0)
	{
		super.mouseReleased(arg0);
		if (optionWin.getPlayer2() == 0 && optionWin.getMap() == 0)
		{
			JOptionPane.showMessageDialog(this, "Please select a map and teams for players 1 and 2");
		}
		else
		{
			//get the map from the OptionsWindows
			ctrl.makeGameWindow();
			optionWin.setVisible(false);
			optionWin.dispose();
		}
	}
}
