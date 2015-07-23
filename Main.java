import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;

public class Main {

	public static void main(String[] args) throws LineUnavailableException, IOException {
		String sMusicLoc = "/piano/piano-ff-0";
		boolean bKeepGoing = true;
		ArrayList<SoundPlayer> PlayerList = new ArrayList<SoundPlayer>();
		boolean bRelease = false;
		boolean bMutex = true;
		System.out.println(PlayerList.size());
		int iFileNum = 1;
		String sFileNum = "30";
		SoundPlayer soundTest = new SoundPlayer();
		soundTest.loadSound(sMusicLoc);
		System.out.println(sMusicLoc + sFileNum   + ".wav");
		soundTest.playSound(iFileNum);
		while(bKeepGoing){
			
		//	System.out.println("In");
			if(bMutex){
				System.out.println(iFileNum);
				bRelease=soundTest.playSound(iFileNum);
				bMutex = false;
			}
			//System.out.println(soundTest.isNotActive());
			if(soundTest.isNotActive()){
				
				bMutex = true;
				if (bRelease){
					iFileNum++;
					sFileNum = iFileNum+"";
				}
				//bKeepGoing = false;
			}
		}
	}
}	



