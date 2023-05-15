package piano;

import piano.components.Key;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExerciseHandler {

    /**
     * List of all exercises available in chosen level folder.
     */
    private List<List<String>> exercises;
    /**
     * Iterator over the chain of correct played notes.
     */
    private int check_note;
    /**
     * Current exercise number.
     */
    private int exercise_num;
    /**
     * Current chosen mode.
     */
    private String active_mode;

    public ExerciseHandler() {
        exercises = new ArrayList<List<String>>();
        check_note = 0;
        exercise_num = 0;
        active_mode = "Piano";

    }

    public void set_active_mode(String m) {
        this.active_mode = m;
    }

    /**
     * Reads folder with proper exercises.
     *
     * @param folderName name of the folder with exercises from different levels (folders in "exercise" folder)
     */
    public void read_exercise_folder(String folderName) {
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
                    exercises.add(listOfNotes);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        }
        check_note = 0;
    }

    public void clear_exercises() {
        exercises.clear();
    }

    public void set_check_note(int num) {
        this.check_note = num;
    }

    public void exercise_rand() {
        int counter = exercises.size();
        Random rand = new Random();
        exercise_num = rand.nextInt(counter);
    }

    public List<String> get_notes() {
        return exercises.get(exercise_num);
    }

    /**
     * Method for running the proper exercise.
     */
    public void play_exercise() {
        for (String note : get_notes()) {
            Key.play_note(note);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        check_note = 0;
    }

    /**
     * Checks clicked notes whether they are the same as in exercise.
     *
     * @param name name of clicked note (C1, D1, Bb, etc.)
     */
    public void check_played_note(String name) {
        if (active_mode.equals("Trening")) {
            String check = exercises.get(exercise_num).get(check_note);
            if (check.equals(name)) {
                check_note += 1;
                if (check_note == exercises.get(exercise_num).size()) {
                    CongratulationWindow cg = new CongratulationWindow();
                    check_note = 0;
                }
            } else {
                check_note = 0;
            }
        } else {
            check_note = 0;
        }
    }

}
