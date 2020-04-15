package dev.epchao.snakegame.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {
	private Clip clip;
	private String wav;
	
	public SoundManager() {
		
	}
	
	public void setFile(String soundFile) {
	
		try {
			wav = soundFile;
			AudioInputStream sound = AudioSystem.getAudioInputStream(SoundManager.class.getClassLoader().getResource(soundFile));
			clip = AudioSystem.getClip();
			clip.open(sound);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void tick(String soundFile) {
		if(wav == soundFile) {
			clip.stop();
			clip.setFramePosition(0);
			clip.start();
		}
	}
}
