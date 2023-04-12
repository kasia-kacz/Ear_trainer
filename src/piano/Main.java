package piano;

import piano.components.Window;

import java.awt.EventQueue;

/**
 * Main class, runs the application.
 * 
 * @author Katarzyna Kaczorowska
 * @version 1.1.2023
 */
public class Main {
	
	/**
	 * Main method.
	 * 
	 * @param args
	 */
	public static void main (String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowController app = new WindowController();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
