import javafx.scene.image.*;

public class FieldEnemies {
	int ID, baseline, maxhealth, health, knockbacks, knockbackcount, animation, damage, backswing, cooldown, framedata, cooldownframedata, range, aggrorange, minrange, damageframedata, cannonframedata, pricedrop, knockbackchance;
	double speed, xpos, ypos;
	boolean area, idle, immunity, cannonimmunity, alive;
	Image idleimage, animationimage, attackimage, backswingimage;
	ImageView fieldimage;
	
	FieldEnemies(int ID, int baseline, int maxhealth, int health, int knockbacks, int knockbackcount, int animation, int damage, int backswing, int cooldown, int framedata, int cooldownframedata, int range, int aggrorange, int minrange, int damageframedata, int cannonframedata, double speed, double xpos, double ypos, int pricedrop, int knockbackchance, boolean area, boolean idle, boolean immunity, boolean cannonimmunity, boolean alive, Image idleimage, Image animationimage, Image attackimage, Image backswingimage, ImageView fieldimage) {
		this.ID = ID;
		this.baseline = baseline;
		this.maxhealth = maxhealth;
		this.health = health;
		this.knockbacks = knockbacks;
		this.knockbackcount = knockbackcount;
		this.animation = animation;
		this.damage = damage;
		this.backswing = backswing;
		this.cooldown = cooldown;
		this.framedata = framedata;
		this.cooldownframedata = cooldownframedata;
		this.range = range;
		this.aggrorange = aggrorange;
		this.minrange = minrange;
		this.damageframedata = damageframedata;
		this.cannonframedata = cannonframedata;
		this.speed = speed;
		this.xpos = xpos;
		this.ypos = ypos;
		this.pricedrop = pricedrop;
		this.knockbackchance = knockbackchance;
		this.area = area;
		this.idle = idle;
		this.immunity = immunity;
		this.cannonimmunity = cannonimmunity;
		this.alive = alive;
		this.idleimage = idleimage;
		this.animationimage = animationimage;
		this.attackimage = attackimage;
		this.backswingimage = backswingimage;
		this.fieldimage = fieldimage;
	}
	
	public void setAttributes(int enemyID) {
		ID = ++enemyID;
		idle = true;
		alive = true;
		
		switch (enemyID) {
			case 1:
				baseline = 125;
				maxhealth = health = 2400000;
				knockbacks = 4;
				animation = 120;
				damage = 32000;
				backswing = 120;
				cooldown = 0;
				range = 355;
				aggrorange = 200;
				minrange = 150;
				speed = 0.2667;
				xpos = 75;
				pricedrop = 19750;
				knockbackchance = 0;
				area = true;
				break;
			case 2:
				baseline = 0;
				maxhealth = health = 363000;
				knockbacks = 2;
				animation = 15;
				damage = 2247;
				backswing = 15;
				cooldown = 0;
				range = 186;
				aggrorange = 186;
				minrange = 0;
				speed = 0.3333;
				xpos = 150;
				pricedrop = 3575;
				knockbackchance = 15;
				area = true;
				break;
			case 3:
				baseline = 0;
				maxhealth = health = 196000;
				knockbacks = 6;
				animation = 10;
				damage = 11134;
				backswing = 10;
				cooldown = 0;
				range = 83;
				aggrorange = 83;
				minrange = 0;
				speed = 2.1333;
				xpos = 150;
				pricedrop = 1975;
				knockbackchance = 0;
				area = true;
				break;
			case 4:
				baseline = 25;
				maxhealth = health = 500;
				knockbacks = 1;
				animation = 12;
				damage = 200;
				backswing = 18;
				cooldown = 30;
				range = 40;
				aggrorange = 40;
				minrange = 0;
				speed = 1.3333;
				xpos = 150;
				pricedrop = 296;
				knockbackchance = 15;
				area = true;
				break;
		}
		
		try {
			idleimage = new Image("Enemies/Enemy_" + enemyID + "/" + enemyID + ".png");
		} catch (IllegalArgumentException iae) {
			idleimage = new Image("Enemies/Enemy_0/0.png");
		}
		
		try {
			animationimage = new Image("Enemies/Enemy_" + enemyID + "/" + enemyID + "_Animation.png");
		} catch (IllegalArgumentException iae) {
			animationimage = new Image("Enemies/Enemy_0/0_Animation.png");
		}
		
		try {
			attackimage = new Image("Enemies/Enemy_" + enemyID + "/" + enemyID + "_Attack.png");
		} catch (IllegalArgumentException iae) {
			attackimage = new Image("Enemies/Enemy_0/0_Attack.png");
		}
		
		try {
			backswingimage = new Image("Enemies/Enemy_" + enemyID + "/" + enemyID + "_Backswing.png");
		} catch (IllegalArgumentException iae) {
			backswingimage = new Image("Enemies/Enemy_0/0_Backswing.png");
		}
		
	}
	
	public boolean stopMovement(FieldCats cat) {
		
		if (cat.ID != 0 && xpos + baseline + aggrorange > cat.xpos + cat.baseline && xpos + baseline < cat.xpos + cat.baseline &&
			!(cat.immunity || cat.knockbackimmunity)) {
			return true;
		}
		
		return false;
	}
	
	public void determineKnockback() {
		
		for (int i = knockbacks - knockbackcount - 1; i >= 0; i--) {
			
			if ((double) health < i * ((double) maxhealth / (double) knockbacks)) {
				immunity = true;
				knockbackcount++;
				fieldimage.setImage(idleimage);
				fieldimage.setRotate(-15);
			}
			
		}
		
	}
	
	public boolean checkAndRemove() {
		
		if (ID != 0 && !alive && !(immunity || cannonimmunity)) {
			reset();
			return true;
		}
		
		return false;
	}
	
	public void render() {
		fieldimage.setImage(framedata > animation + 8 ? backswingimage :
							framedata >= animation ? attackimage :
							framedata != 0 ? animationimage :
							idleimage);
		fieldimage.setVisible(true);
	}
	
	public void position(double backgroundpicturexpos) {
		fieldimage.relocate(xpos + backgroundpicturexpos, 275 - ypos - idleimage.getHeight());
	}
	
	public void reset() {
		ID = 0;
		knockbackcount = 0;
		framedata = 0;
		cooldownframedata = 0;
		damageframedata = 0;
		cannonframedata = 0;
		immunity = false;
		cannonimmunity = false;
		fieldimage.setImage(idleimage);
		fieldimage.setRotate(0);
	}
	
}