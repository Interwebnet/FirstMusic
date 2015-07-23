import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.*;

public class SoundPlayer implements Runnable {

	private Clip clip;
	private AudioInputStream inputStream;
	ArrayList<AudioInputStream> audioList = new ArrayList<AudioInputStream>();
	
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
		
	public void loadSound(String args) {

				try {
					System.out.println(args+ " asdasdasd");
					if(args == "/piano/piano-ff-0"){
						for(int i = 30;i<60;i++){
						System.out.println(args +i);
							AudioInputStream inputStream;
							inputStream = AudioSystem.getAudioInputStream(		    
									SoundPlayer.class.getResourceAsStream(args+i+".wav"));
							audioList.add(inputStream);
							System.out.println(audioList.size() + " asdasdasd");
							}
					}
					
					
					} catch (Exception e) {
			    	  System.err.println(e.getMessage());
					}
				}

	
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("new thread");
		
	}
	public boolean playSound(int args) {
		class Player implements Runnable {
			public void run() {
				System.out.println(audioList.size()+ " "+ args +" " + clip.isActive());
				try {
					clip.open(audioList.get(args));
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					//FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					//gainControl.setValue(2.0f);
				clip.start(); 
				System.out.println("s");
				//clip.drain();
				while(clip.isActive()){
					clip.drain();
				}
				clip.stop();
				clip.close();
				System.out.println("s");
				
			}
		}
		if(audioList.size() > args){
			Player player = new Player();
			Thread play = new Thread(player);
			play.start();
			while(play.isAlive());
		}
		return true;
	}
}	


