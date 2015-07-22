public class Main {

	public static void main(String[] args) {
		//boolean keepGoing = true;
		//int counter = 0;
		firstTest soundTest = new firstTest();
		//while(keepGoing){
		//if (counter%1000000000 == 0){
		soundTest.playSound("/piano/39165__jobro__piano-ff-018.wav");
		//System.out.println(counter%1000000000+ " " + counter);
		//counter = 0;
		//	}
		//counter++;
		//}
//		new Thread(new Runnable() {
//		  // The wrapper thread is unnecessary, unless it blocks on the
//		  // Clip finishing; see comments.
//			public void run() {
//				try {
//
//		    	  Clip clip = AudioSystem.getClip();
//		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(		    
//		        		firstTest.class.getResourceAsStream("/piano/39148__jobro__piano-ff-001.wav"));
//		        clip.open(inputStream);
//		        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//		        gainControl.setValue(2.0f);
//		        clip.start(); 
//		        System.out.println("in");
//		        clip.drain();
//		        System.out.println("drain");
//		        clip.stop();
//		        System.out.println("close");
//		      } catch (Exception e) {
//		    	  System.err.println(e.getMessage());
//		      }
//			}
//		}
//				).start();
	}
}	



