package piano.components;

import piano.WindowController;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Piano extends JPanel {

    private JPanel container;
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

    public Piano() {
		setBackground(SystemColor.info);
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Piano", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.windowText));
		setBounds(90, 74, 821, 445);
		setLayout(null);
        setVisible(true);
        add_lines();
		create_piano_buttons();
    }

    private void add_lines() {
        JPanel line_3 = new JPanel();
        line_3.setBounds(255, 255, 2, 190);
        add(line_3);
        line_3.setBackground(SystemColor.activeCaptionBorder);

        JPanel line_1 = new JPanel();
        line_1.setBounds(395, 140, 2, 190);
        add(line_1);
        line_1.setBackground(SystemColor.activeCaptionBorder);

        JPanel line_4 = new JPanel();
        line_4.setBounds(807, 140, 2, 190);
        add(line_4);
        line_4.setBackground(SystemColor.activeCaptionBorder);

        JPanel line_2 = new JPanel();
        line_2.setBounds(907, 140, 2, 190);
        add(line_2);
        line_2.setBackground(SystemColor.activeCaptionBorder);
    }

    private void create_piano_buttons() {

		//black
		Db = new JButton("C#/Db");
		Db.setBounds(75, 66, 67, 190);
		set_piano_black_button(Db, "Db");

		Eb = new JButton("D#/Eb");
		Eb.setBounds(175, 66, 67, 190);
		set_piano_black_button(Eb, "Eb");

		Gb = new JButton("F#/Gb");
		Gb.setBounds(377, 66, 67, 190);
		set_piano_black_button(Gb, "Gb");

		Ab = new JButton("G#/Ab");
		Ab.setBounds(479, 66, 67, 190);
		set_piano_black_button(Ab, "Ab");

		Bb = new JButton("A#/Bb");
		Bb.setBounds(583, 66, 67, 190);
		set_piano_black_button(Bb, "Bb");

		//white
		D1 = new JButton("D1");
		D1.setBounds(100, 255, 106, 190);
		set_piano_white_button(D1, "D1");

		C1 = new JButton("C1");
		C1.setBounds(0, 255, 106, 190);
		set_piano_white_button(C1, "C1");

		E1 = new JButton("E1");
		E1.setBounds(201, 255, 106, 190);
		set_piano_white_button(E1, "E1");

		F1 = new JButton("F1");
		F1.setBounds(305, 255, 106, 190);
		set_piano_white_button(F1, "F1");

		G1 = new JButton("G1");
		G1.setBounds(407, 255, 106, 190);
		set_piano_white_button(G1, "G1");

		A1 = new JButton("A1");
		A1.setBounds(509, 255, 106, 190);
		set_piano_white_button(A1, "A1");

		H1 = new JButton("H1");
		H1.setBounds(613, 255, 106, 190);
		set_piano_white_button(H1, "H1");

		C2 = new JButton("C2");
		C2.setBounds(714, 255, 106, 190);
		set_piano_white_button(C2, "C2");
	}

    /**
	 * Sets properties for black piano keys (background, font, etc.).
	 *
	 * @param pb	piano key [JButton]
	 * @param name	name which represents the black piano key
	 */
	private void set_piano_black_button(JButton pb, String name) {
		pb.addActionListener(e -> {
			WindowController.play_note(name);
		//	WindowController.check_played_note(name);
		});
		pb.setBackground(SystemColor.textText);
		pb.setForeground(SystemColor.info);
		pb.setFont(new Font("Tahoma", Font.BOLD, 7));
		pb.setVerticalAlignment(SwingConstants.BOTTOM);
		add(pb);
	}

	/**
	 * Sets properties for white piano keys (background, font, etc.).
	 *
	 * @param pb	piano key [JButton]
	 * @param name	name which represents the white piano key
	 */
	private void set_piano_white_button(JButton pb, String name) {
		pb.addActionListener(e -> {
			WindowController.play_note(name);
		//	WindowController.check_played_note(name);
		});
		pb.setBackground(SystemColor.info);
		pb.setFont(new Font("Tahoma", Font.BOLD, 15));
		pb.setVerticalAlignment(SwingConstants.BOTTOM);
		add(pb);
	}
}
