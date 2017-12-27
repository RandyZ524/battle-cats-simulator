public class DeploySlots {
	long maxrecharge, lastdeployed;
	boolean ready;
	
	DeploySlots(long maxrecharge, long lastdeployed, boolean ready) {
		this.maxrecharge = maxrecharge;
		this.lastdeployed = lastdeployed;
		this.ready = ready;
	}
	
	public void assignRechargeTimes(int tempcatID) {
		
		switch (tempcatID) {
			case 0:
				maxrecharge = 4467;
				break;
			case 1:
				maxrecharge = 3133;
				break;
			case 2:
				maxrecharge = /*5800*/1;
				break;
			case 3:
				maxrecharge = 8667;
				break;
			case 4:
				maxrecharge = 2467;
				break;
			case 5:
				maxrecharge = 4467;
				break;
			case 6:
				maxrecharge = 17800;
				break;
			case 7:
				maxrecharge = 2800;
				break;
			case 8:
				maxrecharge = 2133;
				break;
			case 9:
				maxrecharge = 96467;
				break;
		}
		
	}
	
	public boolean isRechargeDone() {
		long currenttime = System.currentTimeMillis();
		
		if (currenttime - lastdeployed > maxrecharge)
			ready = true;
		
		return ready;
	}
	
}