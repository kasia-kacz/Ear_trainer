package piano.components;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel piano;



    public Window() {

        setBackground(SystemColor.inactiveCaption);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        setBounds(0, 0, width, height - 50);
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
