public class EnemySchematics {
	int ID, spawns, firstspawn, nextspawnslower, nextspawnsupper, nextspawnrandom, nextspawnframes;
	boolean onespawn;
	
	EnemySchematics(int ID, int spawns, int firstspawn, int nextspawnslower, int nextspawnsupper, int nextspawnrandom, int nextspawnframes, boolean onespawn) {
		this.ID = ID;
		this.spawns = spawns;
		this.firstspawn = firstspawn;
		this.nextspawnslower = nextspawnslower;
		this.nextspawnsupper = nextspawnsupper;
		this.nextspawnrandom = nextspawnrandom;
		this.nextspawnframes = nextspawnframes;
		this.onespawn = onespawn;
	}
	
}