package piano.components;

import piano.ExerciseHandler;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Class representing one key on the keyboard.
 */

public class Key extends JButton {

    private String name;
    private String color;
    private Rectangle position;

    public int blackHeight;
    public int whiteHeight;

    private ExerciseHandler handler;


    public Key(String n, String c, Rectangle pianoSize, int x, int y, ExerciseHandler h) {

        handler = h;
        name = n;
        color = c;

        setText(name);
        int size = 0;
        String button;
        if (Objects.equals(color, "black")) {
            size = 7;
            setBackground(SystemColor.textText);
            setForeground(SystemColor.info);
            setSizeBlack(pianoSize, x, y);
            button = name.substring(name.length() - 2);
        } else {
            button = name;
            if (Objects.equals(color, "white")) {
                size = 15;
                setBackground(SystemColor.info);
                setForeground(SystemColor.textText);
                setSizeWhite(pianoSize, x, y);
            } else {
                System.err.println("Wrong key color");
            }
        }
        setFont(new Font("Tahoma", Font.BOLD, size));
        setVerticalAlignment(SwingConstants.BOTTOM);
        setBounds(position);
        setVisible(true);

        addActionListener(e -> {
            play_note(button);
            handler.check_played_note(button);
        });
    }

    public void setSizeWhite(Rectangle pS, int pX, int pY) {
        int width = (int) (pS.getWidth() / 10);
        whiteHeight = (int) (pS.getHeight() * 0.3);
        blackHeight = (int) (pS.getHeight() * 0.4);
        position = new Rectangle(pX, pY, width, whiteHeight);
    }

    public void setSizeBlack(Rectangle pS, int pX, int pY) {
        int width = (int) pS.getWidth() / 15;
        whiteHeight = (int) (pS.getHeight() * 0.3);
        blackHeight = (int) (pS.getHeight() * 0.4);
        position = new Rectangle(pX, pY, width, blackHeight);
    }

    /**
     * Plays the right piano note depending on the clicked note by user.
     *
     * @param note name of the note, that should be played
     */
    public static void play_note(String note) {
        try {
            String path_name = "./src/piano/notes/" + note + ".wav";
            File file = new File(path_name);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            //Thread.sleep(2000);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
            e1.printStackTrace();
        }
    }
}
