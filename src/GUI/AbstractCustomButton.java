package GUI;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.Game;

/**
 * @author jsoriano
 * @author kjacob
 */

public abstract class AbstractCustomButton extends JLabel implements MouseListener
{

	private static final long serialVersionUID = 1L;
	private ImageIcon img1;
	private ImageIcon img2;
	private ImageIcon img3;
	private File over;
	private File click;
	private AudioClip overed;
	private AudioClip clicked;
	private String[] audioURLS= {"Audio\\Buttons\\mousedown2.au",
			"Audio\\Buttons\\mouseover.au"};
	
	/**
	 * Constructor for the Abstract Custom Buttom class which all our custom buttons are based on.
	 * @param fileImage1
	 * @param fileImage2
	 * @param fileImage3
	 */
	public AbstractCustomButton(String fileImage1, String fileImage2, String fileImage3)
	{
		super(new ImageIcon(fileImage1));
		img1 = new ImageIcon(fileImage1);
		img2 = new ImageIcon(fileImage2);
		img3 = new ImageIcon(fileImage3);
		over = new File(audioURLS[1]);
		click = new File(audioURLS[0]);
		
		try
		{
			overed = Applet.newAudioClip(over.toURI().toURL());
			clicked = Applet.newAudioClip(click.toURI().toURL());
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addMouseListener(this);
		frame();
	}
	
	/**
	 * sets the frame for the images of the buttons.
	 */
	public void frame()
	{
		this.setBounds(0,0,300,50);
		this.setLayout(null);
		this.setOpaque(false);
		this.setVisible(true);
	}
	
	/**
	 * what the button does when it is mouse overed.
	 */
	public void mouseEntered(MouseEvent arg0)
	{
		setIcon(img2);
			overed.play();
	}
	
	/**
	 * what the button does when it is mouse exited
	 */
	public void mouseExited(MouseEvent arg0)
	{
		setIcon(img1);
	}
	
	/**
	 * what the button does when it is pressed.
	 */
	public void mousePressed(MouseEvent arg0)
	{
		setIcon(img3);
		clicked.play();
	}
	
	/**
	 * what the button does when it is released.
	 */
	public void mouseReleased(MouseEvent arg0)
	{
		setIcon(img2);
	}
	
	/**
	 * what the button does when it is clicked.
	 */
	public void mouseClicked(MouseEvent arg0) {}

}
