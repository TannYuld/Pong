package main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {
	
	public static void PlayAudio(SoundEffects effect) {
		String loc = "";
		
		switch(effect) {
			case WALL_BOUNCE:
				loc = "Wall.wav";
				break;
			
			case PLAYER_BOUNCE:
				loc = "Player.wav";
				break;
				
			case LOSE:
				loc = "Lost.wav";
				break;
		}
		
		play(loc);
	}
	
	private static void play(String loc) {
		try{
			File path = new File(loc);
			
			if(!path.exists()) {
				System.out.println("There is no audio file in location "+path);
			}else {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(path);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
			}
		}catch(Exception e) {
			System.out.println("There are a problem about Audio occurred. \n"+e);
		}
	}
}
