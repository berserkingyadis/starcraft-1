package model;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class ImageLayerTest
{
	
	/**
	 * Enter method description here <br>        
	 *
	 * <hr>
	 * Date created: May 9, 2010 <br>
	 * Date last modified: May 9, 2010 <br>
	 *
	 * <hr>
	 * @param args
	 */
	
	public static void main(String[] args)
	{
		ImageIcon UnitImg1;
		ImageIcon TerrainImg2;
		ImageIcon CombinedImg3;
		JLabel lbl1;
		JLabel lbl2;
		JLabel lbl3;
		
		lbl1 = new JLabel();
		lbl1.setBounds(0, 0, 300, 300);
		lbl2 = new JLabel();
		lbl2.setBounds(0, 300, 500, 500);
		lbl3 = new JLabel();
		lbl3.setBounds(600, 300, 500, 500);
		
		UnitImg1 = new ImageIcon("C:\\Users\\gwatson\\Pictures\\ha.png");
		lbl1.setIcon(UnitImg1);
		TerrainImg2 = new ImageIcon("C:\\Users\\gwatson\\Pictures\\d551fa03974730_full_jpg-2.jpg");
		lbl2.setIcon(TerrainImg2);
		
		BufferedImage buffImg = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = buffImg.createGraphics();
		g2.drawImage(TerrainImg2.getImage(), 0,0, TerrainImg2.getImageObserver());
//		g2.drawImage(img1.getImage(), new AffineTransform(), img1.getImageObserver());
		g2.drawImage(UnitImg1.getImage(),200,150,UnitImg1.getImageObserver());
		
		
		CombinedImg3 = new ImageIcon(buffImg);
		
		lbl3.setIcon(CombinedImg3);
		
		
		
		//window
		JFrame win = new JFrame("image combination");
		win.setBounds(0, 0, 1000, 1000);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setLayout(null);
		
		Container pane = win.getContentPane();
		pane.add(lbl1);
		pane.add(lbl2);
		pane.add(lbl3);
		win.setVisible(true);
	}
	
	
}
