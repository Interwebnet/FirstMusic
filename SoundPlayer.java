import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import javax.sound.sampled.*;

public class SoundPlayer  {
	private Semaphore mutex = new Semaphore(1);
	private Clip clip;
	static private ArrayList<AudioInputStream> audioList = new ArrayList<AudioInputStream>();
	
	public SoundPlayer(){
		try {
			clip = AudioSystem.getClip();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public boolean isNotActive(){
		return (!clip.isActive());
	}
	public boolean isLocked(){
		return (mutex.availablePermits()==0);
	}
		
	public void loadSound(String args) {

				try {
					System.out.println(args+ " asdasdasd");
					if(args == "/piano/piano-ff-0"){
						for(int i = 30;i<=60;i++){
						System.out.println(args +i);
							AudioInputStream inputStream;
							inputStream = AudioSystem.getAudioInputStream(		    
									SoundPlayer.class.getResourceAsStream(args+i+".wav"));
							audioList.add(inputStream);
							//System.out.println(audioList.size() + " asdasdasd");
							}
					}
					
					
					} catch (Exception e) {
			    	  System.err.println(e.getMessage());
					}
				System.out.println("done loading in files.");
				}

	

	public boolean playSound(int args) throws InterruptedException {
	
		class Player implements Runnable {
			//Semaphore mutex = new Semaphore(1);
			public void run() {
				
				//System.out.println(audioList.size()+ " "+ args +" " + clip.isActive());
				try {
				//	System.out.println(mutex.availablePermits() + " MUTEX");
					
					System.out.println("In mutex " +args);
					clip.open(audioList.get(args));
					
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(0.0f);
				
				clip.start(); 
				clip.drain();
				
				while(clip.isActive()){
					clip.drain();
				}
				
				clip.stop();
				clip.close();
				
				mutex.release();
	
			}
		}

		if(audioList.size() > args & mutex.availablePermits()==1){
			//System.out.println(mutex.availablePermits());
			mutex.acquire();
			Player player = new Player();
			Thread play = new Thread(player);
			play.start();

			return true;
		}
		

			
		
		return false;
		
	}
}	


