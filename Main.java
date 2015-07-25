import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import javax.sound.sampled.LineUnavailableException;

public class Main {

	public static void main(String[] args) throws LineUnavailableException, IOException, InterruptedException {
		
		
		String sMusicLoc = "/piano/piano-ff-0";
		ArrayList<SoundPlayer> playerList = new ArrayList<SoundPlayer>();
		
		double channelNum=1;
		boolean[] bRelease = new boolean[(int)channelNum];
		int shouldBeHigh = 0;
		System.out.println(playerList.size());
		SoundPlayer soundLoader = new SoundPlayer();
		soundLoader.loadSound(sMusicLoc);	
		for(int i = 0;i<channelNum;i++){
			SoundPlayer soundTest = new SoundPlayer();	
			playerList.add(soundTest);
			System.out.println(playerList.size());
		}
		

		for(int iFileNum = 1;iFileNum<=30;){
			for(int i=0;i<channelNum;i++){
				shouldBeHigh++;
				bRelease[i]=false;
				bRelease[i]=playerList.get(i).playSound(iFileNum);

				if(playerList.get(i).isLocked()){
					if (bRelease[i]){
						bRelease[i]=false;
						iFileNum++;
					}
				}
			}
		}
		System.out.println("Should be high: "+shouldBeHigh);
	}
}	



