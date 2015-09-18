package GUI;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.Controller;

public class StartWindow extends JFrame
{
	Controller ctrl;
	private JLabel background;
	private ImageIcon icon;
	private AudioClip enter;
	private String audioURL = "Audio\\Buttons\\swishin.au";
	
	/**
	 * constructor for  the start window class.
	 * @param ctrl
	 */
	public StartWindow(Controller ctrl)
	{
		super("StarCraft");
		this.ctrl = ctrl;
		this.setBounds(400,200, 850,700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(50,550,850,150);
		panel1.setLayout(null);
		
		
		
		NewGameButton newGameButton = new NewGameButton(this, ctrl);
		newGameButton.setLocation(0,0);
		panel1.add(newGameButton);
		
		
		LoadGameButton loadGameButton = new LoadGameButton(this);
		loadGameButton.setLocation(450,0);
		panel1.add(loadGameButton);
		panel1.setOpaque(false);
		this.add(panel1);
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
		doAudio();
		background = new JLabel(icon);
		background.setBounds(0,0,850,700);
		this.add(background);
		repaint();

		this.validate();
	}
	
	/**
	 * sets up the audio.
	 */
	public void doAudio()
	{
		try
		{
			File entering = new File(audioURL);
			icon = new ImageIcon("Images\\\\misc\\backgroundv2.gif");
			enter = Applet.newAudioClip(entering.toURI().toURL());
		} catch (MalformedURLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		enter.play();
	}
	
	/**
	 * creates a new game.
	 */
	public void newGame()
	{
		ctrl.makeOptionsMenuWindow();
		this.setVisible(false);
		this.dispose();
	}
	
	/**
	 * loads a game.
	 */
	public void loadGame()
	{
		makeLoadMenuWindow();
		this.setVisible(false);
	}

	/** 
	 * creatres a load menu window.
	 */
	void makeLoadMenuWindow()
	{
		new LoadMenuWindow(ctrl, this);
	}
}
//class newGameButtonListener implements ActionListener
//{
//
//	@Override
//	public void actionPerformed(ActionEvent e)
//	{
//		System.out.println("Will make the new options window");	
//		StartWindow.this.setVisible(false);
//		OptionsMenuWindow optionsMenu = new OptionsMenuWindow(al);
//	}
//	
//}
