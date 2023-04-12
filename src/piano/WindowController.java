package piano;

import piano.components.Window;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class WindowController {

	public Window window;

	public WindowController(){
		window = new Window();
	}

    /**
	 * Plays the right piano note depending on the clicked note by user.
	 *
	 * @param note	name of the note, that should be played
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

		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
	}

}
