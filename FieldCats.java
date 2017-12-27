import javafx.scene.image.*;

import java.util.Random;

public class FieldCats {
	int ID, baseline, maxhealth, health, knockbacks, knockbackcount, animation, damage, backswing, cooldown, framedata, cooldownframedata, range, damageframedata, knockbackframedata, price;
	double speed, xpos, ypos, yposoffset;
	boolean area, massivedamage, resistance, doublecash, idle, immunity, knockbackimmunity, alive;
	Image idleimage, animationimage, attackimage, backswingimage;
	ImageView fieldimage;
	
	FieldCats(int ID, int baseline, int maxhealth, int health, int knockbacks, int knockbackcount, int animation, int damage, int backswing, int cooldown, int framedata, int cooldownframedata, int range, int damageframedata, int knockbackframedata, double speed, double xpos, double ypos, double yposoffset, int price, boolean area, boolean massivedamage, boolean resistance, boolean doublecash, boolean idle, boolean immunity, boolean knockbackimmunity, boolean alive, Image idleimage, Image animationimage, Image attackimage, Image backswingimage, ImageView fieldimage) {
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
		this.damageframedata = damageframedata;
		this.knockbackframedata = knockbackframedata;
		this.price = price;
		this.speed = speed;
		this.xpos = xpos;
		this.ypos = ypos;
		this.yposoffset = yposoffset;
		this.area = area;
		this.massivedamage = massivedamage;
		this.resistance = resistance;
		this.doublecash = doublecash;
		this.idle = idle;
		this.immunity = immunity;
		this.knockbackimmunity = knockbackimmunity;
		this.alive = alive;
		this.idleimage = idleimage;
		this.animationimage = animationimage;
		this.attackimage = attackimage;
		this.backswingimage = backswingimage;
		this.fieldimage = fieldimage;
	}
	
	public void setAttributes(int catID, boolean firstrow1) {
		Random random = new Random();
		ID = catID;
		xpos = 1375;
		yposoffset = random.nextInt(21) - 10;
		idle = true;
		alive = true;
		
		switch (catID) {
			case 1:
				baseline = 60;
				maxhealth = health = 6290;
				knockbacks = 3;
				animation = 46;
				damage = 925;
				backswing = 44;
				cooldown = 64;
				range = 60;
				speed = 0.6667;
				price = 510;
				area = false;
				massivedamage = false;
				resistance = false;
				doublecash = false;
				break;
			case 2:
				baseline = 40;
				maxhealth = health = 11900;
				knockbacks = 3;
				animation = 32;
				damage = 3400;
				backswing = 32;
				cooldown = 88;
				range = 35;
				speed = 0.5333;
				price = 1125;
				area = true;
				massivedamage = false;
				resistance = false;
				doublecash = false;
				break;
			case 3:
				baseline = 110;
				maxhealth = health = 8500;
				knockbacks = 5;
				animation = 4;
				damage = 595;
				backswing = 28;
				cooldown = 32;
				range = 60;
				speed = 3.1333;
				price = /*825*/0;
				area = true;
				massivedamage = false;
				resistance = false;
				doublecash = false;
				break;
			case 4:
				baseline = 0;
				maxhealth = health = 15300;
				knockbacks = 2;
				animation = 24;
				damage = 2176;
				backswing = 76;
				cooldown = 80;
				range = 160;
				speed = 0.5333;
				price = 630;
				area = true;
				massivedamage = false;
				resistance = false;
				doublecash = false;
				break;
			case 5:
				baseline = 30;
				maxhealth = health = 17850;
				knockbacks = 1;
				animation = 10;
				damage = 1020;
				backswing = 10;
				cooldown = 0;
				range = 20;
				speed = 0.5333;
				price = 315;
				area = false;
				massivedamage = false;
				resistance = true;
				doublecash = false;
				break;
			case 6:
				baseline = 0;
				maxhealth = health = 9435;
				knockbacks = 4;
				animation = 24;
				damage = 3400;
				backswing = 71;
				cooldown = 64;
				range = 200;
				speed = 1.4;
				price = 525;
				area = false;
				massivedamage = true;
				resistance = false;
				doublecash = false;
				break;
			case 7:
				baseline = 28;
				maxhealth = health = 15300;
				knockbacks = 1;
				animation = 1;
				damage = 25500;
				backswing = 400;
				cooldown = 0;
				range = 14;
				speed = 5;
				price = 750;
				area = false;
				massivedamage = false;
				resistance = false;
				doublecash = true;
				break;
			case 8:
				baseline = 0;
				maxhealth = health = 26100;
				knockbacks = 5;
				animation = 12;
				damage = 682;
				backswing = 8;
				cooldown = 0;
				range = 140;
				speed = 4.4;
				price = 750;
				area = true;
				massivedamage = false;
				resistance = false;
				doublecash = false;
				break;
			case 9:
				baseline = 95;
				maxhealth = health = 15660;
				knockbacks = 4;
				animation = 40;
				damage = 10497;
				backswing = 42;
				cooldown = 80;
				range = 85;
				speed = 0.6667;
				price = 975;
				area = true;
				massivedamage = false;
				resistance = false;
				doublecash = false;
				break;
			case 10:
				baseline = 0;
				maxhealth = health = 25500;
				knockbacks = 6;
				animation = 0;
				damage = 85000;
				backswing = 0;
				cooldown = 0;
				range = 200;
				speed = 4;
				price = 4500;
				area = true;
				massivedamage = false;
				resistance = false;
				doublecash = false;
				break;
		}
		
		try {
			idleimage = new Image("Units/Unit_" + catID + "/" + catID + ".png");
		} catch (IllegalArgumentException iae) {
			idleimage = new Image("Units/Unit_0/0.png");
		}
		
		try {
			animationimage = new Image("Units/Unit_" + catID + "/" + catID + "_Animation.png");
		} catch (IllegalArgumentException iae) {
			animationimage = new Image("Units/Unit_0/0_Animation.png");
		}
		
		try {
			attackimage = new Image("Units/Unit_" + catID + "/" + catID + "_Attack.png");
		} catch (IllegalArgumentException iae) {
			attackimage = new Image("Units/Unit_0/0_Attack.png");
		}
		
		try {
			backswingimage = new Image("Units/Unit_" + catID + "/" + catID + "_Backswing.png");
		} catch (IllegalArgumentException iae) {
			backswingimage = new Image("Units/Unit_0/0_Backswing.png");
		}
		
	}
	
	public boolean stopMovement(FieldEnemies enemy) {
		
		if (enemy.ID != 0 && xpos + baseline - range < enemy.xpos + enemy.baseline && xpos + baseline > enemy.xpos + enemy.baseline &&
			!(enemy.immunity || enemy.cannonimmunity)) {
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
				fieldimage.setRotate(15);
			}
			
		}
		
	}
	
	public boolean checkAndRemove() {
		
		if (ID != 0 && !alive && !(immunity || knockbackimmunity)) {
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
		fieldimage.relocate(xpos + backgroundpicturexpos, 275 - ypos - idleimage.getHeight() + yposoffset);
	}
	
	public void reset() {
		ID = 0;
		knockbackcount = 0;
		framedata = 0;
		cooldownframedata = 0;
		damageframedata = 0;
		knockbackframedata = 0;
		immunity = false;
		knockbackimmunity = false;
		fieldimage.setImage(idleimage);
		fieldimage.setRotate(0);
	}
	
}