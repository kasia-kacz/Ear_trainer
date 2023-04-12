package piano;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

/**
 * MainFrame class creates the main frame window with all components and functionalities.
 * 
 * @author Katarzyna Kaczorowska
 * @version 1.1.2023
 */
public class MainFrame {
	
	/**
	 * Main window.
	 */
	private JFrame frame;
	
	/**
	 * Main panel in the "frame" window. Contains piano and all buttons.
	 */
	private JPanel panel;

	private JButton C1;
	private JButton D1;
	private JButton E1;
	private JButton F1;
	private JButton G1;
	private JButton A1;
	private JButton H1;
	private JButton C2;
	private JButton Db;
	private JButton Eb;
	private JButton Gb;
	private JButton Ab;
	private JButton Bb;
	
	/**
	 * Button for changing the mode (training or piano mode).
	 */
	JToggleButton mode;
	
	/**
	 * Button group for selecting level.
	 */
	private ButtonGroup buttonGroup;
	private JRadioButton easy;
	private JRadioButton medium;
	private JRadioButton difficult;
	
	/**
	 * Button for running new exercise.
	 */
	private JButton new_exercise;
	/**
	 * Button for repeating an exercise.
	 */
	private JButton repeat_exercise;
	
	/**
	 * List of all exercises available in chosen level folder.
	 */
	public List<List<String>> exercises;
	private int check_note = 0;
	private int exercise_num;
	private String active_mode;


	public MainFrame() {
		create_view();
		activate();
		frame.setVisible(true);
	}
	
	/**
	 * Creates the application window view and initialize all components.
	 */
	
	private void create_view() {
		frame = new JFrame();
		frame.setBackground(SystemColor.inactiveCaption);
		frame.setBounds(100, 100, 1113, 912);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Jaka to melodia?");
		MainFrame.centreWindow(frame);
		
		panel = new JPanel();
		panel.setBackground(new Color(240, 255, 240));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel line_3 = new JPanel();
		line_3.setBounds(90, 140, 2, 190);
		panel.add(line_3);
		line_3.setBackground(SystemColor.activeCaptionBorder);
		
		JPanel line_1 = new JPanel();
		line_1.setBounds(395, 140, 2, 190);
		panel.add(line_1);
		line_1.setBackground(SystemColor.activeCaptionBorder);
		
		JPanel line_4 = new JPanel();
		line_4.setBounds(807, 140, 2, 190);
		panel.add(line_4);
		line_4.setBackground(SystemColor.activeCaptionBorder);
		
		JPanel line_2 = new JPanel();
		line_2.setBounds(907, 140, 2, 190);
		panel.add(line_2);
		line_2.setBackground(SystemColor.activeCaptionBorder);
		
		JPanel piano = new JPanel();
		piano.setBackground(SystemColor.info);
		piano.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Piano", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.windowText));
		piano.setBounds(90, 74, 821, 445);
		panel.add(piano);
		piano.setLayout(null);
		
		create_piano_buttons();
		piano.add(Db);
		piano.add(Eb);
		piano.add(Gb);
		piano.add(Ab);
		piano.add(Bb);
		piano.add(D1);
		piano.add(C1);
		piano.add(E1);
		piano.add(F1);
		piano.add(G1);
		piano.add(A1);
		piano.add(H1);
		piano.add(C2);
		
		//mode button
		this.create_mode_button();
		panel.add(mode);
		
		//new exercise button
		this.create_new_exercise_button();
		panel.add(new_exercise);
		
		//repeat exercise button
		this.create_repeat_exercise_button();
		panel.add(repeat_exercise);
		
		exercises = new ArrayList<List<String>>();
		
		// selecting the level of exercises
		this.create_level_menu();
		panel.add(easy);
		panel.add(medium);
		panel.add(difficult);
		
	}
	
	/**
	 * Creates a menu for selecting the level of difficulty.
	 */
	private void create_level_menu() {
		buttonGroup = new ButtonGroup();
		
		easy = new JRadioButton("Poziom łatwy");
		buttonGroup.add(easy);
		easy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		easy.setBounds(260, 551, 137, 21);
		easy.setSelected(true);
		this.read_exercise_folder("easy");
		
		medium = new JRadioButton("Poziom średni");
		buttonGroup.add(medium);
		medium.setFont(new Font("Tahoma", Font.PLAIN, 15));
		medium.setBounds(260, 582, 137, 21);
		
		difficult = new JRadioButton("Poziom trudny");
		buttonGroup.add(difficult);
		difficult.setFont(new Font("Tahoma", Font.PLAIN, 15));
		difficult.setBounds(260, 616, 137, 21);

	}
	
	/**
	 * Creates a button for repeating an exercise.
	 */
	private void create_repeat_exercise_button() {
		repeat_exercise = new JButton("Powtórz");
		repeat_exercise.setFont(new Font("Tahoma", Font.PLAIN, 15));
		repeat_exercise.setBounds(451, 603, 104, 34);
	}
	
	/**
	 * Creates a button for running a new exercise.
	 */
	private void create_new_exercise_button() {
		new_exercise = new JButton("Następny");
		new_exercise.setFont(new Font("Tahoma", Font.PLAIN, 15));
		new_exercise.setBounds(451, 551, 104, 34);
	}
	
	/**
	 * Creates mode button, which changes the game mode when clicked. It also sets all necessary properties.
	 */
	private void create_mode_button() {
		mode = new JToggleButton("Trening/Piano");
		this.active_mode = "trening";
		mode.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		mode.setSelected(false);
		mode.setBackground(new Color(143, 188, 143));
		mode.setBounds(90, 551, 164, 68);
	}
	
	/**
	 * Creates all piano keys as buttons.
	 */
	private void create_piano_buttons() {
		
		//black
		Db = new JButton("C#/Db");
		Db.setBounds(75, 66, 67, 190);
		this.set_piano_black_button(this.Db, "Db");
		
		Eb = new JButton("D#/Eb");
		Eb.setBounds(175, 66, 67, 190);
		this.set_piano_black_button(this.Eb, "Eb");
		
		Gb = new JButton("F#/Gb");
		Gb.setBounds(377, 66, 67, 190);
		this.set_piano_black_button(this.Gb, "Gb");
		
		Ab = new JButton("G#/Ab");
		Ab.setBounds(479, 66, 67, 190);
		this.set_piano_black_button(this.Ab, "Ab");
		
		Bb = new JButton("A#/Bb");
		Bb.setBounds(583, 66, 67, 190);
		this.set_piano_black_button(this.Bb, "Bb");
		
		//white
		D1 = new JButton("D1");
		D1.setBounds(100, 255, 106, 190);
		this.set_piano_white_button(this.D1, "D1");
		
		C1 = new JButton("C1");
		C1.setBounds(0, 255, 106, 190);
		this.set_piano_white_button(this.C1, "C1");
		
		E1 = new JButton("E1");
		E1.setBounds(201, 255, 106, 190);
		this.set_piano_white_button(this.E1, "E1");
		
		F1 = new JButton("F1");
		F1.setBounds(305, 255, 106, 190);
		this.set_piano_white_button(this.F1, "F1");
		
		G1 = new JButton("G1");
		G1.setBounds(407, 255, 106, 190);
		this.set_piano_white_button(this.G1, "G1");
		
		A1 = new JButton("A1");
		A1.setBounds(509, 255, 106, 190);
		this.set_piano_white_button(this.A1, "A1");
		
		H1 = new JButton("H1");
		H1.setBounds(613, 255, 106, 190);
		this.set_piano_white_button(this.H1, "H1");
		
		C2 = new JButton("C2");
		C2.setBounds(714, 255, 106, 190);
		this.set_piano_white_button(this.C2, "C2");
		
	}
	
	/**
	 * Checks clicked notes whether they are the same as in exercise.
	 * 
	 * @param name	name of clicked note (C1, D1, Bb, etc.)
	 */
	private void check_played_note(String name) {
		if (MainFrame.this.active_mode == "trening") {
			String check = MainFrame.this.exercises.get(MainFrame.this.exercise_num).get(MainFrame.this.check_note);
			if (check.equals(name)) {
				MainFrame.this.check_note += 1;
				if (MainFrame.this.check_note == MainFrame.this.exercises.get(MainFrame.this.exercise_num).size()) {
					CongratulationWindow cg =new CongratulationWindow();
					MainFrame.this.check_note = 0;
				}
			}
			else {
				MainFrame.this.check_note = 0;
			}
		}
	}
	
	/**
	 * Sets properties for black piano keys (background, font, etc.).
	 * 
	 * @param pb	piano key [JButton]
	 * @param name	name which represents the black piano key
	 */
	private void set_piano_black_button(JButton pb, String name) {
		pb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.play_note(name);
				MainFrame.this.check_played_note(name);
			}
		});
		pb.setBackground(SystemColor.textText);
		pb.setForeground(SystemColor.info);
		pb.setFont(new Font("Tahoma", Font.BOLD, 7));
		pb.setVerticalAlignment(SwingConstants.BOTTOM);
	}
	
	/**
	 * Sets properties for white piano keys (background, font, etc.).
	 * 
	 * @param pb	piano key [JButton]
	 * @param name	name which represents the white piano key
	 */
	private void set_piano_white_button(JButton pb, String name) {
		pb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.play_note(name);
				MainFrame.this.check_played_note(name);
			}
		});
		pb.setBackground(SystemColor.info);
		pb.setFont(new Font("Tahoma", Font.BOLD, 15));
		pb.setVerticalAlignment(SwingConstants.BOTTOM);
	}
	
	/**
	 * Plays the right piano note depending on the clicked note by user.
	 * 
	 * @param note	name of the note, that should be played
	 */
	private void play_note(String note) {
		try {
			String path_name = "./src/piano/notes/" + note + ".wav";
			File file = new File(path_name);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
			//Thread.sleep(2000);
			
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Changes the visibility of components, depending on chosen mode.
	 * 
	 * @param b	boolean value of optional components visibility
	 */
	private void change_visibility_of_optional_components(Boolean b) {
		easy.setVisible(b);
		medium.setVisible(b);
		difficult.setVisible(b);
		new_exercise.setVisible(b);
		repeat_exercise.setVisible(b);
	}
	
	/**
	 * Reads folder with proper exercises.
	 * 
	 * @param folderName	name of the folder with exercises from different levels (folders in "exercise" folder)
	 */
	private void read_exercise_folder(String folderName) {
		String path = "./src/piano/exercises/" + folderName;
		File folder = new File(path);
    	File[] listOfFiles = folder.listFiles();

		assert listOfFiles != null;
		for (File file : listOfFiles) {
    	    if (file.isFile()) {

				try {
					BufferedReader bf = new BufferedReader(new FileReader(file.getAbsolutePath()));
					String line = bf.readLine();
					List<String> listOfNotes = new ArrayList<>();
					while (line != null) {
						listOfNotes.add(line);
						line = bf.readLine();
					}
					bf.close();
					MainFrame.this.exercises.add(listOfNotes);
				
				} catch (IOException e1) {
					e1.printStackTrace();
				} 
				
    	    }
    	}
	}

	/**
	 * Activates all components at the window frame.
	 */
	private void activate() {
		
		mode.addItemListener(new ItemListener() {
			   public void itemStateChanged(ItemEvent ev) {
			      if(ev.getStateChange()==ItemEvent.SELECTED){
			    	  mode.setText("Trening/Piano");
			    	  mode.setBackground(new Color(50, 205, 50));
			    	  MainFrame.this.change_visibility_of_optional_components(false);
			    	  MainFrame.this.active_mode = "piano";
			    	  MainFrame.this.check_note = 0;
			    	  
			    	  
			      } else if(ev.getStateChange()==ItemEvent.DESELECTED){
			    	  mode.setText("Trening/Piano");
			    	  mode.setBackground(new Color(143, 188, 143));
			    	  MainFrame.this.change_visibility_of_optional_components(true);
			    	  MainFrame.this.active_mode = "trening";
			    	  MainFrame.this.check_note = 0;
			      }
			   }
			});
		
		easy.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	MainFrame.this.exercises.clear();
		        	MainFrame.this.read_exercise_folder("easy");
		        	MainFrame.this.check_note = 0;
		        }
		    });
		
		medium.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	MainFrame.this.exercises.clear();
	        	MainFrame.this.read_exercise_folder("medium");
	        	MainFrame.this.check_note = 0;
	        }
	    });
		
		difficult.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	MainFrame.this.exercises.clear();
	        	MainFrame.this.read_exercise_folder("difficult");
	        	MainFrame.this.check_note = 0;
	        }
	    });
	
		new_exercise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int counter = MainFrame.this.exercises.size();
				Random rand = new Random();
				exercise_num = rand.nextInt(counter);
				for (String note : MainFrame.this.exercises.get(exercise_num)) {
					MainFrame.this.play_note(note);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				MainFrame.this.check_note = 0;
				}
		});
		
		repeat_exercise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (String note : MainFrame.this.exercises.get(exercise_num)) {
					MainFrame.this.play_note(note);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				MainFrame.this.check_note = 0;
			}
		});
		
	}
	
	/**
	 * Shows the window in the center of the screen.
	 * 
	 * @param frame the window to centralize
	 */
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 15);
	    frame.setLocation(x, y);
	}
	
}
