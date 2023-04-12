package piano.components;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private JPanel mainPanel;
    private JPanel piano;
    public static Rectangle position;



    public Window() {

        setBackground(SystemColor.inactiveCaption);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight() - 40;
        position = new Rectangle(0, 0, width, height);
        setBounds(position);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Jaka to melodia?");

        setup_panel();
        setVisible(true);
    }

    private void setup_panel(){
        mainPanel = new JPanel();
        piano = new Piano();
        mainPanel.setBackground(new Color(240, 255, 240));
        add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(null);
        mainPanel.add(piano);

        mainPanel.setVisible(true);
        piano.setVisible(true);
    }

}
