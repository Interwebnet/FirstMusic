import java.io.IOException;
import java.util.ArrayList;


import javax.sound.sampled.LineUnavailableException;

public class Main {

	public static void main(String[] args) throws LineUnavailableException, IOException, InterruptedException {
		
		
		String sMusicLoc = "/piano/piano-ff-0";
		ArrayList<SoundPlayer> playerList = new ArrayList<SoundPlayer>();
		int channelNum=30;
		boolean[] bRelease = new boolean[channelNum];
		double shouldBeHigh = 0;
		double[] iFileNum = new double[channelNum];
		System.out.println(playerList.size());
		SoundPlayer soundLoader = new SoundPlayer();
		soundLoader.loadSound(sMusicLoc);	
		double firstNote = 0;
		int count = 0;
		int tempNum  =1;
		for(int i = 0;i<channelNum;i++){
			SoundPlayer soundTest = new SoundPlayer();	
			playerList.add(soundTest);
			//System.out.println(playerList.size());	
		}
		iFileNum[0]= System.currentTimeMillis();
		for(int i = 0;i<channelNum;i++){
			iFileNum[i] = iFileNum[0] +((1550/channelNum)*i+1);
		}
		
		for(int iPlayNum = 1;iPlayNum<=59;){
			//this area needs work.  A lot of work.
			for(int i=0;i<channelNum;i++){
				shouldBeHigh++;
				if(iFileNum[i]< System.currentTimeMillis()){
					if(i==0){
						firstNote = System.currentTimeMillis();
				}

					bRelease[i]=false;
					bRelease[i]=playerList.get(i).playSound(iPlayNum);
					iFileNum[i] = firstNote + 1550+((1550/channelNum)*i+1);
					
				}
				if(playerList.get(i).isLocked()){
					if (bRelease[i]){
						bRelease[i]=false;
						System.out.println("thread: " + i + ", time:"+ iFileNum[i]+", PlayNum:" + iPlayNum);
						if (count ==channelNum){
							count =0;
							tempNum++;
							iPlayNum=tempNum;
							
						}
						else{
							iPlayNum++;
							count++;
						}
						

					}
				}
			}
		}
		System.out.println("Should be high: "+shouldBeHigh);
	}
}	



