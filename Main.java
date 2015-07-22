public class Main {

	public static void main(String[] args) {
		String sMusicLoc = "/piano/piano-ff-0";
		boolean bKeepGoing = true;
		boolean bMutex;
		int iFileNum = 30;
		String sFileNum = "30";
		firstTest soundTest = new firstTest();
		System.out.println(sMusicLoc + sFileNum   + ".wav");
		while(bKeepGoing){
			iFileNum++;
			sFileNum = iFileNum+"";
			bMutex =soundTest.playSound(sMusicLoc + sFileNum   + ".wav");
			System.out.println("out of playsound");
			
		}
	}
}	



