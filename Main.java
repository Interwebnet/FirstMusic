import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;

public class Main {

	public static void main(String[] args) throws LineUnavailableException, IOException {
		String sMusicLoc = "/piano/piano-ff-0";
		ArrayList<SoundPlayer> PlayerList = new ArrayList<SoundPlayer>();
		boolean bRelease = false;
		boolean bMutex = true;
		System.out.println(PlayerList.size());
		SoundPlayer soundTest = new SoundPlayer();
		soundTest.loadSound(sMusicLoc);
		//soundTest.playSound(iFileNum);
		for(int iFileNum = 1;iFileNum<=30;){
			if(bMutex){
				System.out.println(iFileNum);
				bMutex = false;
				bRelease=false;
				bRelease=soundTest.playSound(iFileNum);
			}
			if(soundTest.isNotActive()){
				if (bRelease){
					
					bMutex = true;
					bRelease=false;
					iFileNum++;
				}
			}
		}
	}
}	



