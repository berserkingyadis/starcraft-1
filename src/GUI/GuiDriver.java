//i am setting up later it will do other thing i assume
// This class runs the gui class that 
package GUI;

import java.awt.event.MouseListener;

import controller.Controller;

/**
 * @author jsoriano
 * @author kjacob
 */
public class GuiDriver 
{
	/**
	 * starts up the GUI
	 * @param ctrl
	 */
	public GuiDriver(Controller ctrl)
	{
		new StartWindow(ctrl);
	}
	
	public static void main(String[] args) 
	{ 
//		new Ui();
	}

}