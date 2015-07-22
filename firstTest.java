import javax.sound.sampled.*;

public class firstTest {
	
	private Clip clip;
	public firstTest(){
		try {
			clip = AudioSystem.getClip();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
		
	public boolean playSound(String args) {
		boolean unlock = false;
//		new Thread(new Runnable() {
			  // The wrapper thread is unnecessary, unless it blocks on the
			  // Clip finishing; see comments.
			// this is used to make a new thread.  Ie if we wanted to play a lot of things.
//				public void run() {
					try {
						System.out.println(args);
						AudioInputStream inputStream = AudioSystem.getAudioInputStream(		    
								firstTest.class.getResourceAsStream(args));
						System.out.println("wat2");
						this.clip.open(inputStream);
						//FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
						//gainControl.setValue(2.0f);
						this.clip.start(); 
						System.out.println("s");
						while(clip.isActive()){
							clip.drain();
						}
						System.out.println("s");
						clip.stop();
						clip.close();					
					
			      } catch (Exception e) {
			    	  System.err.println(e.getMessage());
			      }
					return unlock;
				}
//			}
//					).start();
		
//		}
	}	


