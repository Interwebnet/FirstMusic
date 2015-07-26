import java.io.IOException;
import java.util.ArrayList;

import java.util.concurrent.Semaphore;

import javax.sound.sampled.*;

public class SoundPlayer  {
	private Semaphore mutex;
//	private Clip clip;
	static private ArrayList<AudioInputStream> audioList = new ArrayList<AudioInputStream>();
	static private ArrayList<Semaphore> mutexList = new ArrayList<Semaphore>();
	static private Semaphore closer = new Semaphore(1);
	public SoundPlayer(){
		mutex = new Semaphore(1);
//		try {
//			clip = AudioSystem.getClip();
//		}catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
	}
//	public boolean isNotActive(){
//		return (!clip.isActive());
//	}
	public boolean isLocked(){
		return (mutex.availablePermits()< 1);
	}
		
	public void loadSound(String args) {

				try {
					System.out.println(args+ " asdasdasd");
					if(args == "/piano/piano-ff-0"){
						String temp;
						for(int i = 1;i<=60;i++){
							if(i<10){
								temp="0";
							}
							else temp ="";
							Semaphore mutex = new Semaphore(1);
							System.out.println(args+ temp+i);
							AudioInputStream inputStream;
							inputStream = AudioSystem.getAudioInputStream(		    
									SoundPlayer.class.getResourceAsStream(args+temp+i+".wav"));
							audioList.add(inputStream);
							mutexList.add(mutex);
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
				synchronized(mutexList.get(args)){
					Clip clip = null;
					
					try {
						clip = AudioSystem.getClip();
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//System.out.println(audioList.size()+ " "+ args +" " + clip.isActive());
					try {
					//	System.out.println(mutex.availablePermits() + " MUTEX");
						//mutexList.get(args).acquire();
						
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


					
					while(closer.availablePermits()>0){
						
					
						clip.setMicrosecondPosition(0);
						clip.start(); 
						double time= System.currentTimeMillis();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						clip.drain();
						//System.out.println("Playing: "+args+", in thread.");
						while(clip.isActive()){
							clip.drain();
						}
						double time2= System.currentTimeMillis();
						//System.out.println(time2 - time + " playing:" + args + " Length: "+audioList.get(args).getFrameLength()+" Object: "+audioList.get(args));
		
						
						clip.stop();
						mutex.release();
						try {
							mutexList.get(args).wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					//System.out.println(clip.getLongFramePosition()+"LINE");
					clip.close();
					
					
					}
				}
			}
		
		if(audioList.size() > args & mutex.availablePermits()>0){
			if(mutexList.get(args).availablePermits()>0){
			//System.out.println(mutexList.get(args).availablePermits()+ " Permits");
			mutex.acquire();
			mutexList.get(args).acquire();
			Player player = new Player();
			Thread play = new Thread(player);
			play.setName("Thread-"+args);
			play.start();
			return true;
			}
			else{
				//System.out.println("In here "+ args);
				mutex.acquire();
				synchronized(mutexList.get(args)){
					mutexList.get(args).notify();
					return true;
				}
			}
		}
		

			
		
		return false;
		
	}
	
}	


