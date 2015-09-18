package GUI;

import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * @author jsoriano
 * @author kjacob
 */
public class StatWindow extends JFrame
{
	/**
	 * constructor for the stat window class.
	 * @param ml
	 */
	public StatWindow(MouseListener ml)
	{
		super("STATS");
		this.setBounds(825, 600, 600, 300);
		this.setLayout(new GridLayout(1,2));
		
		JPanel pnlStats = new JPanel();
		pnlStats.setLayout(new BoxLayout(pnlStats,BoxLayout.Y_AXIS));
		this.add(pnlStats);
		
		JLabel hpLabel  = new  JLabel("HP");
		pnlStats.add(hpLabel);
		JLabel spLabel  = new  JLabel("SPEED");
		pnlStats.add(spLabel);
		JLabel atLabel  = new  JLabel("ATTACK");
		pnlStats.add(atLabel);
		JLabel accLabel  = new  JLabel("ACCURACY");
		pnlStats.add(accLabel);
		JLabel rgLabel  = new  JLabel("RANGE");
		pnlStats.add(rgLabel);
		JLabel dfLabel  = new  JLabel("DEFENSE");
		pnlStats.add(dfLabel);
		
		JPanel pnlBars = new JPanel();
		pnlBars.setLayout(new BoxLayout(pnlBars,BoxLayout.Y_AXIS));
		this.add(pnlBars);
		
		JProgressBar hpBar = new JProgressBar();
		pnlBars.add(hpBar);
		JProgressBar spBar = new JProgressBar();
		pnlBars.add(spBar);
		JProgressBar atBar = new JProgressBar();
		pnlBars.add(atBar);
		JProgressBar accBar = new JProgressBar();
		pnlBars.add(accBar);
		JProgressBar rgBar = new JProgressBar();
		pnlBars.add(rgBar);
		JProgressBar dfBar = new JProgressBar();
		pnlBars.add(dfBar);
		
		
		
	/*	•	HP (Determines unit health)
		•	Speed (Determines unit movement)
		•	Attack (Determines unit damage)
		•	Accuracy (Determines unit’s chance of hitting enemy)
		•	Range (Determines unit’s minimum distance to hit enemy)
		•	Defense 
*/
//		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
}
