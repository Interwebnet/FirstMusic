import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import javax.sound.sampled.*;

public class SoundPlayer  {
	private Semaphore mutex;
	private Clip clip;
	static private ArrayList<AudioInputStream> audioList = new ArrayList<AudioInputStream>();
	

	public SoundPlayer(){
		mutex = new Semaphore(1);
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
		return (mutex.availablePermits()< 1);
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
			public void run() {
				
				//System.out.println(audioList.size()+ " "+ args +" " + clip.isActive());
				try {
				//	System.out.println(mutex.availablePermits() + " MUTEX");

					clip.open(audioList.get(args));
					
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
//					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//					gainControl.setValue(0.0f);
				
				clip.start(); 
				double time= System.currentTimeMillis();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				clip.drain();
				System.out.println("Playing: "+args+", in thread.");
				while(clip.isActive()){
					clip.drain();
				}
				double time2= System.currentTimeMillis();
				System.out.println(time2 - time + " playing:" + args);
				
				clip.stop();
				clip.close();
				
				mutex.release();
	
			}
		}

		if(audioList.size() > args & mutex.availablePermits()>0){
			//System.out.println(mutex.availablePermits()+ " Permits");
			mutex.acquire();
			Player player = new Player();
			Thread play = new Thread(player);
			play.start();

			return true;
		}
		

			
		
		return false;
		
	}
}	


