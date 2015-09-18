package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Game;

import SaveAndLoad.Persistance;

import controller.Controller;

/**
 * @author jsoriano
 * @author kjacob
 */
public class LoadMenuWindow// extends JFrame
{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the loadmenuwindow class.
	 * @param ctrl
	 * @param startWin
	 */
	public LoadMenuWindow(Controller ctrl, StartWindow startWin) {
//		super("Load Saved Game");
//		setSize(100, 300);
//		setLocationRelativeTo(null);
//		
//		
//		JPanel panel1 = new JPanel();
//		panel1.setBounds(0, 0, 400, 600);
//		panel1.setLayout(new BorderLayout());
//		this.ctrl=ctrl;

		// this j textarea is just for fun we need to populate this scrollpane
		// later with saved games

		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = fc.showOpenDialog(startWin);
		if (returnVal == JFileChooser.APPROVE_OPTION) 
		{
			File dir = fc.getSelectedFile();
			Game g;
			try
			{
				g = Persistance.load(dir);
				ctrl.makeGameWindow(g);
				startWin.setVisible(false);
				startWin.dispose();
			} catch (FileNotFoundException e)
			{
				JOptionPane.showMessageDialog(startWin, "We couldn't find that file.");
			} catch (IOException e)
			{
				JOptionPane.showMessageDialog(startWin, "There was an error reading from the file.");
			} catch (ClassNotFoundException e)
			{
				JOptionPane.showMessageDialog(startWin, "Couldn't find the class to load the file into. Try reinstalling.");
			}
		}

		
//		JList lstGameFiles = new JList(/*TODO make list model*/);
//		JScrollPane pane = new JScrollPane(lstGameFiles); 
//		pane.setBounds(0,0,390,590);
//		panel1.add(pane,BorderLayout.CENTER);
//
//		JButton continueGameButton = new JButton("CONTINUE");
//		continueGameButton.setBounds(0, 50, 50, 50);
//		panel1.add(continueGameButton , BorderLayout.SOUTH);
//		continueGameButton.addActionListener(new continueGameBtnListener());
//		this.add(panel1);
//		setVisible(true);
	}
//	private class continueGameBtnListener implements ActionListener
//	{
//
//		@Override
//		public void actionPerformed(ActionEvent arg0)
//		{
//			ctrl.makeGameWindow(null);
//			//StatWindow stats = new StatWindow();
////			LoadMenuWindow.this.setVisible(false); 
//		}
//		
//	}
}
