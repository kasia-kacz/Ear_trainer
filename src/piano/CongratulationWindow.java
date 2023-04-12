package piano;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * CongratulationWindow class creates the frame window with the congratulation message. It shows after finding the proper order of notes.
 * 
 * @author Katarzyna Kaczorowska
 * @version 1.1.2023
 */
public class CongratulationWindow {
	
	/**
	 * Congratulation frame.
	 */
	private JFrame frame;
	
	/**
	 * Congratulation text.
	 */
	private JLabel label;
	
	/**
	 * Constructor.
	 */
	public CongratulationWindow() {
		
		label = new JLabel();
		label.setText("Gratulacje! Udało Ci się odgadnąć melodię! :)");
		
		ImageIcon imageIcon = new ImageIcon("./src/piano/icons/frog_4.png");
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);  
		imageIcon = new ImageIcon(newimg);  
		JLabel imageLabel = new JLabel(imageIcon);
		
		frame = new JFrame("Gratulacje!");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(label, BorderLayout.CENTER);
		frame.getContentPane().add(imageLabel, BorderLayout.PAGE_END);
		frame.pack();
		MainFrame.centreWindow(frame);
		frame.setVisible(true);
		
	}

}
