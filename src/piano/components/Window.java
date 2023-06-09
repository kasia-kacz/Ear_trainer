package piano.components;

import piano.ExerciseHandler;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private JPanel mainPanel;
    private JPanel piano;
    private JButton repeat_exercise;
    private JButton new_exercise;
    private JToggleButton mode;
    private ButtonGroup levels;
    private JRadioButton easy;
    private JRadioButton medium;
    private JRadioButton difficult;
    public static Rectangle position;

    private ExerciseHandler handler;


    public Window() {

        setBackground(SystemColor.inactiveCaption);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight() - 40;
        position = new Rectangle(0, 0, width, height);
        setBounds(position);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Jaka to melodia?");
        handler = new ExerciseHandler();

        setup_panel();
        setVisible(true);

    }

    private void setup_panel() {
        mainPanel = new JPanel();
        piano = new Piano(handler);
        mainPanel.setBackground(new Color(240, 255, 240));
        add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(null);
        mainPanel.add(piano);
        mainPanel.setVisible(true);
        piano.setVisible(true);

        setup_buttons();
    }

    private void setup_buttons() {

        Rectangle pos_new = new Rectangle((int) (position.getWidth() * 0.8) + 10, (int) (position.getHeight() * 0.5), 100, 50);
        create_new_exercise_button(pos_new);
        pos_new.setLocation((int) pos_new.getX(), (int) (pos_new.getY() + 60));
        create_repeat_exercise_button(pos_new);
        mainPanel.add(new_exercise);
        mainPanel.add(repeat_exercise);

        levels = new ButtonGroup();
        easy = new JRadioButton("Poziom łatwy");
        pos_new.setLocation((int) pos_new.getX(), (int) pos_new.getY() + 80);
        pos_new.setSize(140, 20);
        setup_level(easy, pos_new);
        easy.setSelected(true);
        handler.read_exercise_folder("easy");

        medium = new JRadioButton("Poziom średni");
        pos_new.setLocation((int) pos_new.getX(), (int) (pos_new.getY() + 30));
        setup_level(medium, pos_new);

        difficult = new JRadioButton("Poziom trudny");
        pos_new.setLocation((int) pos_new.getX(), (int) (pos_new.getY() + 30));
        setup_level(difficult, pos_new);

        Rectangle mode_pos = new Rectangle((int) (position.getWidth() * 0.8) + 10, (int) (position.getHeight() * 0.35), 150, 80);
        create_mode_button(mode_pos);
        activate_buttons();
    }

    private void activate_buttons() {
        easy.addActionListener(e -> {
            handler.clear_exercises();
            handler.read_exercise_folder("easy");
        });

        medium.addActionListener(e -> {
            handler.clear_exercises();
            handler.read_exercise_folder("medium");
        });

        difficult.addActionListener(e -> {
            handler.clear_exercises();
            handler.read_exercise_folder("difficult");
        });

        new_exercise.addActionListener(e -> {
            handler.exercise_rand();
            handler.play_exercise();
        });

        repeat_exercise.addActionListener(e -> {
            handler.play_exercise();
        });
    }

    private void setup_level(JRadioButton rb, Rectangle pos) {
        levels.add(rb);
        mainPanel.add(rb);
        rb.setFont(new Font("Tahoma", Font.PLAIN, 15));
        rb.setBounds(pos);
    }

    /**
     * Creates a button for repeating an exercise.
     */
    private void create_repeat_exercise_button(Rectangle pos) {
        repeat_exercise = new JButton("Powtórz");
        repeat_exercise.setFont(new Font("Tahoma", Font.PLAIN, 15));
        repeat_exercise.setBounds(pos);
        mainPanel.add(repeat_exercise);
    }

    /**
     * Creates a button for running a new exercise.
     *
     * @param pos represents position of created button
     */
    private void create_new_exercise_button(Rectangle pos) {
        new_exercise = new JButton("Następny");
        new_exercise.setFont(new Font("Tahoma", Font.PLAIN, 15));
        new_exercise.setBounds(pos);
        mainPanel.add(new_exercise);
    }

    /**
     * Creates mode button, which changes the game mode when clicked. It also sets all necessary properties.
     *
     * @param pos represents the position of new button
     */
    private void create_mode_button(Rectangle pos) {
        mode = new JToggleButton();
        mode.addActionListener(e -> {
            if (mode.isSelected()) {
                mode.setText("Piano");
                set_components_visibility(false);
            } else {
                mode.setText("Trening");
                set_components_visibility(true);
            }
            handler.set_check_note(0);
            handler.set_active_mode(mode.getText());
        });
        mode.doClick();
        mode.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        mode.setBackground(new Color(143, 188, 143));
        mode.setBounds(pos);
        mainPanel.add(mode);
    }

    /**
     * Changes the visibility of components depanding on the chosen mode.
     *
     * @param b if true, components should be visible, if false - not
     */
    private void set_components_visibility(Boolean b) {
        new_exercise.setVisible(b);
        repeat_exercise.setVisible(b);
        easy.setVisible(b);
        medium.setVisible(b);
        difficult.setVisible(b);
    }


}
