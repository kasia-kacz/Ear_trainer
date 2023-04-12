package piano.components;

import piano.WindowController;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Key extends JButton {

    private String name;
    private String color;
    private Rectangle position;

    public int blackHeight;
    public int whiteHeight;


    public Key(String n, String c, Rectangle pianoSize, int x, int y) {

        name = n;
        color = c;

        setText(name);
        int size = 0;
        String button;
        if(Objects.equals(color, "black")){
            size = 7;
            setBackground(SystemColor.textText);
            setForeground(SystemColor.info);
            setSizeBlack(pianoSize, x, y);
            button = name.substring(name.length()-2);
        } else {
            button = name;
            if (Objects.equals(color, "white")) {
                size = 15;
                setBackground(SystemColor.info);
                setForeground(SystemColor.textText);
                setSizeWhite(pianoSize, x, y);
            }
            else {
                System.err.println("Wrong key color");
            }
        }
        setFont(new Font("Tahoma", Font.BOLD, size));
        setVerticalAlignment(SwingConstants.BOTTOM);
        setBounds(position);
        setVisible(true);

        addActionListener(e -> {
            WindowController.play_note(button);
            //	WindowController.check_played_note(name);
        });
    }

    public void setSizeWhite(Rectangle pS, int pX, int pY){
        int width = (int) (pS.getWidth()/10);
        whiteHeight = (int) (pS.getHeight()*0.3);
        blackHeight = (int) (pS.getHeight()*0.4);
        position = new Rectangle(pX, pY, width, whiteHeight);
    }

    public void setSizeBlack(Rectangle pS, int pX, int pY){
        int width = (int) pS.getWidth()/15;
        whiteHeight = (int) (pS.getHeight()*0.3);
        blackHeight = (int) (pS.getHeight()*0.4);
        position = new Rectangle(pX, pY, width, blackHeight);
    }
}
