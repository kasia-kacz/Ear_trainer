package piano.components;

import piano.ExerciseHandler;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Piano extends JPanel {

    private Rectangle position;
    private Key C1;
    private Key D1;
    private Key E1;
    private Key F1;
    private Key G1;
    private Key A1;
    private Key H1;
    private Key C2;
    private Key Db;
    private Key Eb;
    private Key Gb;
    private Key Ab;
    private Key Bb;
    private ExerciseHandler handler;

    public Piano(ExerciseHandler h) {
        handler = h;
        setBackground(SystemColor.info);
        setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Piano", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.windowText));
        setPosition();
        setBounds(position);
        setLayout(null);
        setVisible(true);
        create_piano_buttons();
        add_borders();
    }

    private void setPosition() {
        int x = (int) (Window.position.getX() + Window.position.getWidth() * 0.2);
        int y = (int) (Window.position.getY() + Window.position.getHeight() * 0.2);
        int width = (int) (Window.position.getWidth() * 0.6);
        int height = (int) (Window.position.getHeight() * 0.6);
        position = new Rectangle(x, y, width, height);
    }

    private void add_borders() {
        JPanel line_1 = new JPanel();
        line_1.setBounds(C1.getX() - 1, Db.getY(), 2, Db.getHeight());
        add(line_1);
        line_1.setBackground(SystemColor.activeCaptionBorder);

        JPanel line_2 = new JPanel();
        line_2.setBounds(F1.getX() - 1, Eb.getY(), 2, Db.getHeight());
        add(line_2);
        line_2.setBackground(SystemColor.activeCaptionBorder);

        JPanel line_3 = new JPanel();
        line_3.setBounds(C2.getX() - 1, Bb.getY(), 2, Bb.getHeight());
        add(line_3);
        line_3.setBackground(SystemColor.activeCaptionBorder);

        JPanel line_4 = new JPanel();
        line_4.setBounds(C2.getX() + C2.getWidth() - 2, Bb.getY(), 2, Bb.getHeight());
        add(line_4);
        line_4.setBackground(SystemColor.activeCaptionBorder);

        JPanel line_5 = new JPanel();
        line_5.setBounds(C1.getX(), Db.getY(), C1.getWidth() * 8, 2);
        add(line_5);
        line_5.setBackground(SystemColor.activeCaptionBorder);
    }


    private void create_piano_buttons() {

        int x;
        int y;

        x = (int) (position.getWidth() * 0.1);
        y = (int) (position.getHeight() * 0.65);

        C1 = new Key("C1", "white", position, x, y, handler);
        add(C1);

        x += C1.getWidth();
        D1 = new Key("D1", "white", position, x, y, handler);
        add(D1);

        x += D1.getWidth();
        E1 = new Key("E1", "white", position, x, y, handler);
        add(E1);

        x += E1.getWidth();
        F1 = new Key("F1", "white", position, x, y, handler);
        add(F1);

        x += F1.getWidth();
        G1 = new Key("G1", "white", position, x, y, handler);
        add(G1);

        x += G1.getWidth();
        A1 = new Key("A1", "white", position, x, y, handler);
        add(A1);

        x += A1.getWidth();
        H1 = new Key("H1", "white", position, x, y, handler);
        add(H1);

        x += H1.getWidth();
        C2 = new Key("C2", "white", position, x, y, handler);
        add(C2);

        x = (int) (C1.getX() + 2 * C1.getWidth() / 3);
        y = (int) (C1.getY() - C1.blackHeight);

        Db = new Key("C#/Db", "black", position, x, y, handler);
        add(Db);

        x = (int) (D1.getX() + 2 * D1.getWidth() / 3);
        Eb = new Key("D#/Eb", "black", position, x, y, handler);
        add(Eb);

        x = (int) (F1.getX() + 2 * F1.getWidth() / 3);
        Gb = new Key("F#/Gb", "black", position, x, y, handler);
        add(Gb);

        x = (int) (G1.getX() + 2 * G1.getWidth() / 3);
        Ab = new Key("G#/Ab", "black", position, x, y, handler);
        add(Ab);

        x = (int) (A1.getX() + 2 * A1.getWidth() / 3);
        Bb = new Key("A#/Bb", "black", position, x, y, handler);
        add(Bb);
    }
}
