import javax.sound.sampled.*;

public class firstTest {
	
	
	public void playSound(String args) {
	//	new Thread(new Runnable() {
			  // The wrapper thread is unnecessary, unless it blocks on the
			  // Clip finishing; see comments.
			//	public void run() {
					try {
						Clip clip = AudioSystem.getClip();
						AudioInputStream inputStream = AudioSystem.getAudioInputStream(		    
								firstTest.class.getResourceAsStream(args));
						clip.open(inputStream);
						//FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
						//gainControl.setValue(2.0f);
						clip.start(); 
						clip.drain();
						System.out.println("s");
						//clip.stop();
						//clip.close();
			      } catch (Exception e) {
			    	  System.err.println(e.getMessage());
			      }
				}
			//}
		//			).start();
		//}
	}	


