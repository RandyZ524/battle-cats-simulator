import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;
//Importing my stuff

class FieldCats {
	int ID, baseline, maxhealth, health, knockbacks, knockbackcount, animation, damage, backswing, cooldown, framedata, cooldownframedata, range, damageframedata, knockbackframedata, price;
	double speed, xpos, ypos;
	boolean area, massivedamage, resistance, doublecash, idle, immunity, knockbackimmunity, alive;
	Image idleimage, animationimage, attackimage, backswingimage;
	
	FieldCats(int ID, int baseline, int maxhealth, int health, int knockbacks, int knockbackcount, int animation, int damage, int backswing, int cooldown, int framedata, int cooldownframedata, int range, int damageframedata, int knockbackframedata, double speed, double xpos, double ypos, int price, boolean area, boolean massivedamage, boolean resistance, boolean doublecash, boolean idle, boolean immunity, boolean knockbackimmunity, boolean alive, Image idleimage, Image animationimage, Image attackimage, Image backswingimage) {
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
		this.speed = speed;
		this.xpos = xpos;
		this.ypos = ypos;
		this.price = price;
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
	}
	
	public boolean allowMovement(double enemyxpos, int enemybaseline, boolean enemyimmunity, boolean enemycannonimmunity) {
		return !(xpos + baseline - range < enemyxpos + enemybaseline && xpos + baseline > enemyxpos + enemybaseline && !(enemyimmunity || enemycannonimmunity));
	}
	
	public void render(ImageView tempimageview) {
		tempimageview.setImage(framedata > animation + 8 ? backswingimage :
								framedata >= animation ? attackimage :
								framedata != 0 ? animationimage :
								idleimage);
		return;
	}
	
	public void position(ImageView tempimageview, double backgroundpicturexpos) {
		tempimageview.relocate(xpos + backgroundpicturexpos, 275 - ypos - idleimage.getHeight());
		return;
	}
	
}

class FieldEnemies {
	int ID, baseline, maxhealth, health, knockbacks, knockbackcount, animation, damage, backswing, cooldown, framedata, cooldownframedata, range, aggrorange, minrange, damageframedata, cannonframedata, pricedrop, knockbackchance;
	double speed, xpos, ypos;
	boolean area, idle, immunity, cannonimmunity, alive;
	Image idleimage, animationimage, attackimage, backswingimage;
	
	FieldEnemies(int ID, int baseline, int maxhealth, int health, int knockbacks, int knockbackcount, int animation, int damage, int backswing, int cooldown, int framedata, int cooldownframedata, int range, int aggrorange, int minrange, int damageframedata, int cannonframedata, double speed, double xpos, double ypos, int pricedrop, int knockbackchance, boolean area, boolean idle, boolean immunity, boolean cannonimmunity, boolean alive, Image idleimage, Image animationimage, Image attackimage, Image backswingimage) {
		this.ID = ID;
		this.baseline = baseline;
		this.maxhealth = maxhealth;
		this.health = health;
		this.knockbacks = knockbacks;
		this.knockbackcount = knockbackcount;
		this.damage = damage;
		this.animation = animation;
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
	}
	
	public void render(ImageView tempimageview) {
		tempimageview.setImage(framedata > animation + 8 ? backswingimage :
								framedata >= animation ? attackimage :
								framedata != 0 ? animationimage :
								idleimage);
		return;
	}
	
	public void position(ImageView tempimageview, double backgroundpicturexpos) {
		tempimageview.relocate(xpos + backgroundpicturexpos, 275 - ypos - idleimage.getHeight());
		return;
	}
	
}

class DeploySlots {
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
				maxrecharge = 5800;
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
		
		return;
	}
	
}

class EnemySchematics {
	int ID, spawns, firstspawn, nextspawnslower, nextspawnsupper, nextspawnrandom, nextspawnframes;
	
	EnemySchematics(int ID, int spawns, int firstspawn, int nextspawnslower, int nextspawnsupper, int nextspawnrandom, int nextspawnframes) {
		this.ID = ID;
		this.spawns = spawns;
		this.firstspawn = firstspawn;
		this.nextspawnslower = nextspawnslower;
		this.nextspawnsupper = nextspawnsupper;
		this.nextspawnrandom = nextspawnrandom;
		this.nextspawnframes = nextspawnframes;
	}
	
}

public class noplanA extends Application {
	
 public static void main(String[] args) {
	Application.launch(args); //Launches application
 }
 
	int i, j, k, catdeployed, deleteunit, money, success;
	int truewavexpos = -40;
	int numofcats, numofenemies, tempnumofenemies, cannonrecharge, linenumber = 0;
	int lowestunit = -1;
	double unitxpos, unitypos, lowestxpos = 0;
	int maxmoney = 6000;
	int workercat = 1;
	int catbasehealth = 120000;
	int enemybasehealth = 750000;
	double truemoney, wavexpos, tempround1, tempround2, tempround3, tempround4 = 0;
	long globaltimer, delaytimer = 0;
	boolean inthelevel, leftkey, rightkey, allowmove, firstrow, canaffordupgrade, activecannon;
	boolean godmode, winner, loser, paused, stagecomplete, getcatway, firsttimewinner, tonextbox = false;
	
	FieldCats catarray[] = new FieldCats[50];
	FieldEnemies enemyarray[] = new FieldEnemies[12];
	DeploySlots rechargearray[] = new DeploySlots[10];
	EnemySchematics spawninstructions[] = new EnemySchematics[100];
	
	ImageView slotarray[] = new ImageView[50];
	ImageView limitarray[] = new ImageView[12];
	Text textinstruct[] = new Text[5];
	Image slotpictures[] = new Image[11];
	Image slotpicturesidle[] = new Image[11];
	ImageView slotdisplay[] = new ImageView[10];
	Image catcannonicons[] = new Image[5];
	Rectangle rechargerects[] = new Rectangle[10];
	
	ImageView startbackground = new ImageView();
	ImageView selectstart = new ImageView();
	ImageView selecthelp = new ImageView();
	ImageView whitebackground = new ImageView();
	ImageView toMenu = new ImageView();
	ImageView backgroundpicture = new ImageView();
	ImageView workercaticon = new ImageView();
	ImageView cannonicon = new ImageView();
	ImageView catcannonwave = new ImageView();
	ImageView pausemenu = new ImageView();
	ImageView selectpausehelp = new ImageView();
	ImageView catwayunlock = new ImageView();
	ImageView firsttimereward = new ImageView();
	ImageView levelfinish = new ImageView();
	Line cannonlaser = new Line();
	
	Text moneydisplay = new Text();
	Text workercatlevel = new Text();
	Text workercatcost = new Text();
	Text catbasehealthdisplay = new Text();
	Text enemybasehealthdisplay = new Text();
	Text bosspercentage = new Text();
	Text victorytext = new Text();
	Text catwaytext = new Text();
	Text firstcatfoodtext = new Text();
	Text firstenergytext = new Text();
	
	Color idle = Color.rgb(195, 195, 195);
	Color active = Color.rgb(255, 242, 0);
	Color purpleflash = Color.rgb(239, 60, 200);
	Color yellowflash = Color.rgb(243, 228, 51);
	
	byte inbuf[] = new byte[20500];     //Input buffer for the whole file
	String str = new String();
	
	Random random = new Random();
	
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("A Battle Cats simulator");
		
		Image logo = new Image("Battle_Cats_Logo.png");
		Image startbutton = new Image("Start_Button.png");
		Image helpbutton = new Image("Help_Button.png");
		Image whitescreen = new Image("White_Screen.png");
		Image exithelpbutton = new Image("Exit_Help_Button.png");
		Image background = new Image("Background_Picture.png");
		Image workercatidle = new Image("Worker_Cat_Idle.png");
		Image workercatactive = new Image("Worker_Cat_Active.png");
		Image wave = new Image("Cat_Cannon_Wave.png");
		Image defeatscreen = new Image("Defeat_Screen.png");
		Image pause = new Image("Pause_Menu.png");
		Image pausehelpbutton = new Image("Pause_Help_Button.png");
		Image catway = new Image("Unlocked_Catway_Box.png");
		Image firsttime = new Image("First_Time_Box.png");
		Image ok = new Image("Ok_Button.png");
		
		startbackground.setImage(logo);
		
		selectstart.setImage(startbutton);
		selectstart.setLayoutX(250);
		selectstart.setLayoutY(250);
		
		selecthelp.setImage(helpbutton);
		selecthelp.setLayoutX(250);
		selecthelp.setLayoutY(310);
		
		whitebackground.setImage(whitescreen);
		whitebackground.setVisible(false);
		
		toMenu.setImage(exithelpbutton);
		toMenu.setLayoutX(750);
		toMenu.setLayoutY(330);
		toMenu.setVisible(false);
		
		backgroundpicture.setImage(background);
		backgroundpicture.setLayoutX(-800);
		backgroundpicture.setLayoutY(-380);
		backgroundpicture.setVisible(false);
		
		workercaticon.setImage(workercatidle);
		workercaticon.setLayoutX(0);
		workercaticon.setLayoutY(295);
		workercaticon.setVisible(false);
		
		moneydisplay.setLayoutX(590);
		moneydisplay.setLayoutY(25);
		moneydisplay.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
		moneydisplay.setStroke(Color.BLACK);
		moneydisplay.setFill(Color.rgb(250, 203, 1));
		moneydisplay.setVisible(false);
		
		workercatlevel.setText("Level 1");
		workercatlevel.setLayoutX(2);
		workercatlevel.setLayoutY(310);
		workercatlevel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		workercatlevel.setStroke(Color.BLACK);
		workercatlevel.setFill(idle);
		workercatlevel.setVisible(false);
		
		workercatcost.setText("560$");
		workercatcost.setLayoutX(2);
		workercatcost.setLayoutY(375);
		workercatcost.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		workercatcost.setStroke(Color.BLACK);
		workercatcost.setFill(Color.rgb(232, 220, 0));
		workercatcost.setVisible(false);
		
		cannonicon.setImage(catcannonicons[0]);
		cannonicon.setLayoutX(700);
		cannonicon.setLayoutY(300);
		cannonicon.setVisible(false);
		
		catcannonwave.setImage(wave);
		catcannonwave.setLayoutX(800);
		catcannonwave.setLayoutY(220);
		catcannonwave.setVisible(false);
		
		cannonlaser.setStroke(Color.rgb(255, 174, 201));
		cannonlaser.setStrokeWidth(5);
		
		catbasehealthdisplay.setText(Integer.toString(catbasehealth) + "/120000");
		catbasehealthdisplay.setLayoutX(800);
		catbasehealthdisplay.setLayoutY(105);
		catbasehealthdisplay.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		catbasehealthdisplay.setStroke(Color.BLACK);
		catbasehealthdisplay.setFill(Color.WHITE);
		
		enemybasehealthdisplay.setText(Integer.toString(enemybasehealth) + "/750000");
		enemybasehealthdisplay.setLayoutX(800);
		enemybasehealthdisplay.setLayoutY(105);
		enemybasehealthdisplay.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		enemybasehealthdisplay.setStroke(Color.BLACK);
		enemybasehealthdisplay.setFill(Color.WHITE);
		
		bosspercentage.setLayoutX(425);
		bosspercentage.setLayoutY(323);
		bosspercentage.setFont(Font.font("Verdana", 20));
		bosspercentage.setFill(Color.WHITE);
		bosspercentage.setVisible(false);
		
		pausemenu.setImage(pause);
		pausemenu.setLayoutX(225);
		pausemenu.setLayoutY(40);
		pausemenu.setVisible(false);
		
		selectpausehelp.setImage(pausehelpbutton);
		selectpausehelp.setLayoutX(280);
		selectpausehelp.setLayoutY(225);
		selectpausehelp.setVisible(false);
		
		victorytext.setText("Victory!!!");
		victorytext.setLayoutX(290);
		victorytext.setLayoutY(120);
		victorytext.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		victorytext.setStroke(Color.BLACK);
		victorytext.setStrokeWidth(3);
		victorytext.setFill(Color.WHITE);
		victorytext.setVisible(false);
		
		catwayunlock.setImage(catway);
		catwayunlock.setLayoutX(141);
		catwayunlock.setLayoutY(230);
		catwayunlock.setVisible(false);
		
		catwaytext.setText("You can't get it from the Upgrades screen.");
		catwaytext.setLayoutX(190);
		catwaytext.setLayoutY(320);
		catwaytext.setFont(Font.font("Verdana", 20));
		catwaytext.setVisible(false);
		
		firsttimereward.setImage(firsttime);
		firsttimereward.setLayoutX(141);
		firsttimereward.setLayoutY(230);
		firsttimereward.setVisible(false);
		
		firstcatfoodtext.setText("30 Cat Food!!");
		firstcatfoodtext.setLayoutX(378);
		firstcatfoodtext.setLayoutY(295);
		firstcatfoodtext.setFont(Font.font("Verdana", 20));
		firstcatfoodtext.setVisible(false);
		
		firstenergytext.setText("fully recovered!!");
		firstenergytext.setLayoutX(415);
		firstenergytext.setLayoutY(320);
		firstenergytext.setFont(Font.font("Verdana", 20));
		firstenergytext.setVisible(false);
		
		levelfinish.setImage(ok);
		levelfinish.setLayoutX(253);
		levelfinish.setLayoutY(300);
		levelfinish.setVisible(false);
		
		Group root = new Group(startbackground, selectstart, selecthelp, whitebackground, toMenu, backgroundpicture, moneydisplay, workercaticon, workercatlevel, workercatcost, cannonicon, catcannonwave, cannonlaser, catbasehealthdisplay, enemybasehealthdisplay, bosspercentage, pausemenu, selectpausehelp, victorytext, catwayunlock, catwaytext, firsttimereward, firstcatfoodtext, firstenergytext, levelfinish);
		Scene scene = new Scene(root, 800, 380);
		
	//Filling arrays used in application
		
		if (true) {
			
			for (i = 0; i < 50; i++) {
				catarray[i] = new FieldCats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, false, false, false, new Image("Unit_ID_0.png"), new Image("Unit_ID_0_Animation.png"), new Image("Unit_ID_0_Attack.png"), new Image("Unit_ID_0_Backswing.png"));
				
				slotarray[i] = new ImageView();
				slotarray[i].setVisible(false);
				root.getChildren().add(slotarray[i]);
			}
			
			for (i = 0; i < 12; i++) {
				enemyarray[i] = new FieldEnemies(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, new Image("Enemy_ID_0.png"), new Image("Enemy_ID_0_Animation.png"), new Image("Enemy_ID_0_Attack.png"), new Image("Enemy_ID_0_Backswing.png"));
				
				limitarray[i] = new ImageView();
				limitarray[i].setVisible(false);
				root.getChildren().add(limitarray[i]);
			}
			
			for (i = 0; i < 10; i++) {
				rechargearray[i] = new DeploySlots(0, 0, true);
				rechargearray[i].assignRechargeTimes(i);
				
				rechargerects[i] = new Rectangle(132 + ((i % 5) * 110), 356, 0, 10);
				rechargerects[i].setFill(Color.rgb(81, 255, 220));
				rechargerects[i].setVisible(false);
				root.getChildren().add(rechargerects[i]);
			}
			
			for (i = 1; i < 12; i++) {
				slotpictures[i - 1] = new Image("Slot_Image_" + i + ".png");
				slotpicturesidle[i - 1] = new Image("Slot_Image_" + i + "_Idle.png");
			}
			
			for (i = 5; i < 10; i++) {
				slotdisplay[i] = new ImageView();
				slotdisplay[i].setImage(slotpictures[i]);
				slotdisplay[i].setVisible(false);
				slotdisplay[i].setLayoutX(110 + ((i - 5) * 110));
				slotdisplay[i].setLayoutY(300);
				root.getChildren().add(slotdisplay[i]);
			}
			
			for (i = 0; i < 5; i++) {
				slotdisplay[i] = new ImageView();
				slotdisplay[i].setImage(slotpictures[i]);
				slotdisplay[i].setVisible(false);
				slotdisplay[i].setLayoutX(130 + (i * 110));
				slotdisplay[i].setLayoutY(320);
				root.getChildren().add(slotdisplay[i]);
			}
			
			for (i = 1; i < 6; i++) {
				catcannonicons[i - 1] = new Image("Cat_Cannon_Icon_" + i + ".png");
			}
			
		}
		
	//Filling arrays used in application end
		
		//Setting up variables (buttons, scene, group)
		
		LineNumberReader lnr = new LineNumberReader(new FileReader(new File("Enemy_Spawn_Schematics.txt")));
		lnr.skip(Long.MAX_VALUE);
		linenumber = lnr.getLineNumber();
		//Getting the number of lines to simplify file reader and spawn instructions
		
		int readID, readspawncount, readfirstspawn, readnextspawnslower = 0, readnextspawnsupper = 0;
		
		//file reader that pulls the level schematics information from the text file "Enemy_Spawn_Schematics.txt"
		try (DataInputStream dataIn = new DataInputStream(new FileInputStream("Enemy_Spawn_Schematics.txt"))) {
			success = dataIn.read(inbuf);

			if (success != -1) {
				
				for (i = 0; i < success; i++) {
					str += (char) inbuf[i];
				}
			
			}
			//Adding to a megastring while the file has not ended
			
			for (i = 0; i < linenumber; i++) {
				readID = Integer.parseInt(str.substring(0, str.indexOf(",")));
				str = str.substring(str.indexOf(" ") + 1, str.length());
				
				readspawncount = Integer.parseInt(str.substring(0, str.indexOf(",")));
				str = str.substring(str.indexOf(" ") + 1, str.length());
				
				readfirstspawn = Integer.parseInt(str.substring(0, str.indexOf(",")));
				str = str.substring(str.indexOf(" ") + 1, str.length());
				
				if (readspawncount != 1) {
					readnextspawnslower = Integer.parseInt(str.substring(0, str.indexOf(",")));
					str = str.substring(str.indexOf(" ") + 1, str.length());
					
					readnextspawnsupper = Integer.parseInt(str.substring(0, str.indexOf(",")));
					str = str.substring(str.indexOf(" ") + 1, str.length());
				}
				
				str = str.substring(str.indexOf('\n') + 1, str.length());
				//Parsing each line of text and assigning temporary variables
				
				spawninstructions[i] = new EnemySchematics(readID, readspawncount, readfirstspawn, readnextspawnslower, readnextspawnsupper, 0, 0);
				//Adding a new object to represent one line of text for use in the spawn instructions
			} //while
			
		} catch (IOException exc) {
			System.out.println("Read error.");
			return;
		}
		
	//Image action handling
		
		//action for clicking on the help button
		selecthelp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				Instructions(whitebackground, toMenu, textinstruct, paused);
				root.getChildren().addAll(textinstruct[0], textinstruct[1], textinstruct[2], textinstruct[3], textinstruct[4]);
				event.consume();
			}
			
		});
		
		//action for clicking on the start button
		selectstart.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
				if (godmode) {
					slotpictures[5] = slotpictures[10];
					slotpicturesidle[5] = slotpicturesidle[10];
					slotdisplay[5].setImage(slotpictures[5]);
				}
				
				setupLevel(startbackground, selectstart, selecthelp, backgroundpicture, moneydisplay, workercaticon, workercatlevel, workercatcost, cannonicon, slotdisplay, catbasehealthdisplay, enemybasehealthdisplay);
				inthelevel = true;
				firstrow = true;
				canaffordupgrade = false;
				activecannon = false;
				event.consume();
			}
			
		});
		
		selectpausehelp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
				if (paused) {
					Instructions(whitebackground, toMenu, textinstruct, paused);
					root.getChildren().addAll(textinstruct[0], textinstruct[1], textinstruct[2], textinstruct[3], textinstruct[4]);
					event.consume();
				}
				
			}
			
		});
		
		backgroundpicture.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
				if (loser) {
					backgroundpicture.setImage(background);
					backgroundpicture.relocate(-800, -380);
					backgroundpicture.setVisible(false);
					moneydisplay.setVisible(false);
					workercaticon.setVisible(false);
					workercatlevel.setVisible(false);
					workercatcost.setVisible(false);
					cannonicon.setVisible(false);
					catbasehealthdisplay.setVisible(false);
					enemybasehealthdisplay.setVisible(false);
					bosspercentage.setVisible(false);
					
					for (i = 0; i < 10; i++) {
						slotdisplay[i].setVisible(false);
						rechargerects[i].setVisible(false);
						rechargearray[i].lastdeployed = 0;
					}
					
					for (i = 0; i < 50; i++) {
						catarray[i] = new FieldCats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, false, false, false, new Image("Unit_ID_0.png"), new Image("Unit_ID_0_Animation.png"), new Image("Unit_ID_0_Attack.png"), new Image("Unit_ID_0_Backswing.png"));
						slotarray[i].setVisible(false);
					}
					
					for (i = 0; i < 12; i++) {
						enemyarray[i] = new FieldEnemies(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, new Image("Enemy_ID_0.png"), new Image("Enemy_ID_0_Animation.png"), new Image("Enemy_ID_0_Attack.png"), new Image("Enemy_ID_0_Backswing.png"));
						limitarray[i].setVisible(false);
					}
					
					startbackground.setVisible(true);
					selectstart.setVisible(true);
					selecthelp.setVisible(true);
					
					inthelevel = false;
					loser = false;
					
					numofcats = 0;
					numofenemies = 0;
					catbasehealth = 120000;
					enemybasehealth = 750000;
					globaltimer = 0;
				}
				
				event.consume();
			}
			
		});
		
		catwayunlock.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
				if (delaytimer >= 90) {
					catwayunlock.setVisible(false);
					catwaytext.setVisible(false);
					
					delaytimer = 90;
					tonextbox = true;
				}
				
			}
			
		});
		
		catwaytext.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
				if (delaytimer >= 90) {
					catwayunlock.setVisible(false);
					catwaytext.setVisible(false);
					
					delaytimer = 90;
					tonextbox = true;
				}
				
			}
			
		});
		
		firsttimereward.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
				if (delaytimer >= 90) {
					firsttimereward.setVisible(false);
					firstcatfoodtext.setVisible(false);
					firstenergytext.setVisible(false);
					
					delaytimer = 0;
					tonextbox = true;
				}
				
			}
			
		});
		
		firstcatfoodtext.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
				if (delaytimer >= 90) {
					firsttimereward.setVisible(false);
					firstcatfoodtext.setVisible(false);
					firstenergytext.setVisible(false);
					
					delaytimer = 0;
					tonextbox = true;
				}
				
			}
			
		});
		
		firstenergytext.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
				if (delaytimer >= 90) {
					firsttimereward.setVisible(false);
					firstcatfoodtext.setVisible(false);
					firstenergytext.setVisible(false);
					
					delaytimer = 0;
					tonextbox = true;
				}
				
			}
			
		});
		
		levelfinish.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
			}
			
		});
		
	//Image action handling end
	
	//Keyboard action handling
		
		//action for pressing a key
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			
			public void handle(KeyEvent event) {
				
				if (inthelevel && !(paused || winner)) {
					
					switch (event.getCode()) {
						case A:
							leftkey = true;
							break;
						case D:
							rightkey = true;
							break;
						case UP:
							
							if (workercat < 8 && truemoney >= workercat * 560) {
								truemoney = UpgradeWallet(truemoney, workercat, workercatlevel, workercatcost);
								workercat++;
								maxmoney += 1500;
							}
							
							break;
						case ENTER:
							
							if (cannonrecharge == 3000) {
								cannonicon.setImage(catcannonicons[0]);
								cannonrecharge = 0;
								activecannon = true;
								cannonlaser.setVisible(true);
								catcannonwave.setVisible(true);
								
								for (i = 0; i < numofcats; i++) {
									catarray[i].knockbackimmunity = true;
								}
								
							}
							
							break;
					}
				
				}
				
				if (inthelevel && !winner) {
					
					switch (event.getCode()) {
						case P:
							paused = !paused;
							pausemenu.setVisible(paused);
							pausemenu.toFront();
							selectpausehelp.setVisible(paused);
							selectpausehelp.toFront();
							break;
					}
					
				} else {
					
					switch (event.getCode()) {
						case G:
							
							if (!godmode) {
								godmode = true;
							}
							
							break;
					}
				}
				
			}
			
		});
		
		//action for releasing a key
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			
			public void handle(KeyEvent event) {
				
				if (inthelevel && !winner) {
					
					switch (event.getCode()) {
						case A:
							leftkey = false;
							break;
						case D:
							rightkey = false;
							break;
						case TAB:
							firstrow = !firstrow;
						
							if (firstrow) {
								
								for (i = 0; i < 5; i++) {
									slotdisplay[i].relocate(130 + (i * 110), 320);
									slotdisplay[i].toFront();
									
									rechargerects[i].toFront();
								}
								
								for (i = 5; i < 10; i++) {
									slotdisplay[i].relocate(110 + ((i - 5) * 110), 300);
								}
								
							} else {
								
								for (i = 0; i < 5; i++) {
									slotdisplay[i].relocate(110 + (i * 110), 300);
								}
								
								for (i = 5; i < 10; i++) {
									slotdisplay[i].relocate(130 + ((i - 5) * 110), 320);
									slotdisplay[i].toFront();
									
									rechargerects[i].toFront();
								}
								
							}
							
							break;
					}
					
					if ((event.getText().equals("1") || event.getText().equals("2") || event.getText().equals("3") || event.getText().equals("4") || event.getText().equals("5")) && numofcats < 50) {
						catdeployed = Integer.parseInt(event.getText());
						
						if (isRechargeDone(rechargearray, firstrow, catdeployed)) {
							catarray = addCat(catarray, firstrow, catdeployed, numofcats);
							
							if (catarray[numofcats].price < truemoney) {
								truemoney -= catarray[numofcats].price;
								numofcats++;
								
								if (!firstrow) {
									catdeployed += 5;
								}
								
								rechargearray[catdeployed - 1].lastdeployed = System.currentTimeMillis();
								rechargearray[catdeployed - 1].ready = false;
								slotdisplay[catdeployed - 1].setImage(slotpicturesidle[catdeployed - 1]);
								rechargerects[catdeployed - 1].setVisible(true);
							} else {
								catarray[numofcats] = new FieldCats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, false, false, false, new Image("Unit_ID_0.png"), new Image("Unit_ID_0_Animation.png"), new Image("Unit_ID_0_Attack.png"), new Image("Unit_ID_0_Backswing.png"));
							}
							
						}
						
					}
					
				}
				
			}
			
		});
		
	//Keyboard action handling end
		
		enemyarray[0] = new FieldEnemies(1, 125, 2400000, 2400000, 4, 0, 120, 120000, 120, 0, 0, 0, 355, 200, 150, 0, 0, 0.2667, 75, 0, 19750, 0, true, true, false, false, true, new Image("Enemy_ID_1.png"), new Image("Enemy_ID_1_Animation.png"), new Image("Enemy_ID_1_Attack.png"), new Image("Enemy_ID_1_Backswing.png"));
		numofenemies++;
		
		AnimationTimer timer = new AnimationTimer() {
            @Override
			
            public void handle(long now) {
				
				if (inthelevel && !loser && !paused) {
					backgroundpicture = moveLR(backgroundpicture, leftkey, rightkey);
					globaltimer++;
					
					if (cannonrecharge < 3000) {
						cannonrecharge = 3000;
					}
					
					//spawning enemies schematics
					/*
					if (!winner) {
					
						for (i = 0; i < linenumber; i++) {
							
							if (spawninstructions[i].firstspawn == globaltimer) {
								enemyarray = addEnemy(enemyarray, numofenemies, spawninstructions[i].ID);
								numofenemies++;
								
								if (spawninstructions[i].spawns != -1) {
									spawninstructions[i].spawns--;
								}
								
							}
							
							if (spawninstructions[i].spawns != 0 && spawninstructions[i].firstspawn < globaltimer) {
							
								if (spawninstructions[i].nextspawnrandom == 0) {
									spawninstructions[i].nextspawnrandom = random.nextInt(spawninstructions[i].nextspawnsupper - spawninstructions[i].nextspawnslower + 1) + spawninstructions[i].nextspawnslower;
								} else if (spawninstructions[i].nextspawnframes == spawninstructions[i].nextspawnrandom && numofenemies < 12) {
									enemyarray = addEnemy(enemyarray, numofenemies, spawninstructions[i].ID);
									numofenemies++;
									spawninstructions[i].nextspawnframes = 0;
									spawninstructions[i].nextspawnrandom = 0;
									
									if (spawninstructions[i].spawns != -1) {
										spawninstructions[i].spawns--;
									}
									
								} else if (numofenemies < 12) {
									spawninstructions[i].nextspawnframes++;
								}
							
							}
							
						}
						
					}
					*/
					
					//cat unit logic
					for (i = 0; i < numofcats; i++) {
						
						if (catarray[i].health < 0) {
							catarray[i].alive = false;
						}
						
						//damage knockback animation
						if (catarray[i].immunity) {
							catarray[i].damageframedata++;
							
							if (catarray[i].damageframedata > 60) {
								catarray[i].immunity = false;
								catarray[i].damageframedata = 0;
								catarray[i].ypos = 0;
								
								if (!catarray[i].alive) {
									catarray = removeCat(catarray, numofcats, i);
									numofcats--;
								}
								
							} else {
								tempround1 = 0.0000375 * Math.pow(catarray[i].damageframedata, 4);
								tempround2 = -0.00263 * Math.pow(catarray[i].damageframedata, 3);
								tempround3 = -0.0225 * Math.pow(catarray[i].damageframedata, 2);
								tempround4 = 2.7 * catarray[i].damageframedata;
								
								catarray[i].xpos++;
								catarray[i].ypos = Math.abs(tempround1 + tempround2 + tempround3 + tempround4);
							}
							
						}
						
						//proc knockback animation
						if (catarray[i].knockbackimmunity) {
							catarray[i].knockbackframedata++;
							
							if (catarray[i].knockbackframedata > 30) {
								catarray[i].knockbackimmunity = false;
								catarray[i].knockbackframedata = 0;
							} else {
								tempround1 = Math.pow(catarray[i].knockbackframedata - 30, 3);
								tempround2 = 6750;
								
								catarray[i].xpos = catarray[i].xpos - (tempround1 / tempround2);
								
								if (catarray[i].framedata != 0) {
									catarray[i].framedata = 0;
								}
								
							}
							
						}
						
						//stops when in range of enemy base
						if (catarray[i].xpos + catarray[i].baseline - catarray[i].range <= 180 && catarray[i].idle) {
							catarray[i].idle = false;
						}
						
						if (catarray[i].framedata > catarray[i].animation || catarray[i].framedata == 0) {
							catarray[i].cooldownframedata++;
						}
						
						//at each frame, checks all enemies to see if the cat should stop moving
						if (catarray[i].idle && catarray[i].alive && !(catarray[i].immunity || catarray[i].knockbackimmunity)) {
							allowmove = true;
							
							for (j = 0; j < numofenemies && allowmove; j++) {
								
								if (allowmove) {
									allowmove = catarray[i].allowMovement(enemyarray[j].xpos, enemyarray[j].baseline, enemyarray[j].immunity, enemyarray[j].cannonimmunity);
								}
								
							}
							
							if (allowmove) {
								catarray[i].xpos -= catarray[i].speed;
							} else {
								catarray[i].idle = false;
							}
						
						//otherwise, increment framedata and see where in the attack cycle the cat is
						} else if (catarray[i].alive && !(catarray[i].immunity || catarray[i].knockbackimmunity) && (catarray[i].cooldownframedata >= catarray[i].cooldown || catarray[i].framedata >= catarray[i].animation)) {
							catarray[i].framedata++;
							
							if (catarray[i].framedata == catarray[i].animation) {
								
								for (j = 0; j < numofenemies; j++) {
									
									if (catarray[i].xpos + catarray[i].baseline - catarray[i].range <= enemyarray[j].xpos + enemyarray[j].baseline &&
										!(enemyarray[j].immunity || enemyarray[j].cannonimmunity)) {
										
										if (catarray[i].area) {	
											enemyarray[j].health -= catarray[i].damage;
										} else if (lowestunit == -1) {
											lowestunit = j;
											
											if (j == numofenemies - 1) {
												enemyarray[lowestunit].health -= catarray[i].damage;
											}
											
										} else if (enemyarray[j].xpos + enemyarray[j].baseline > enemyarray[lowestunit].xpos + enemyarray[lowestunit].baseline) {
											lowestunit = j;
											
											if (j == numofenemies - 1) {
												enemyarray[lowestunit].health -= catarray[i].damage;
											}
											
										}
										
										for (k = enemyarray[j].knockbacks - enemyarray[j].knockbackcount - 1; k >= 0; k--) {
											
											if (enemyarray[j].health < k * ((double) enemyarray[j].maxhealth / (double) enemyarray[j].knockbacks)) {
												enemyarray[j].immunity = true;
												enemyarray[j].knockbackcount++;
											}
											
										}
									
									}
									
								}
								
								if (catarray[i].xpos + catarray[i].baseline - catarray[i].range <= 180) {
									enemybasehealth -= catarray[i].damage;
								}
								
								lowestunit = -1;
								catarray[i].cooldownframedata = 0;
							} else if (catarray[i].framedata == catarray[i].animation + catarray[i].backswing) {
								catarray[i].framedata = 0;
								catarray[i].idle = true;
							}
							
						} else if (catarray[i].alive) {
							catarray[i].framedata = 0;
							catarray[i].idle = true;
						}
						
					}
					
					//enemy unit logic
					for (i = 0; i < numofenemies; i++) {
						
						if (enemyarray[i].health < 0) {
							enemyarray[i].alive = false;
						}
						
						//damage knockback animation
						if (enemyarray[i].immunity) {
							enemyarray[i].damageframedata++;
							
							if (enemyarray[i].damageframedata > 60) {
								enemyarray[i].immunity = false;
								enemyarray[i].damageframedata = 0;
								enemyarray[i].ypos = 0;
								
								if (!enemyarray[i].alive) {
									enemyarray = removeEnemy(enemyarray, numofenemies, i);
									numofenemies--;
								}
								
							} else {
								
								if (enemyarray[i].xpos > 0) {
									enemyarray[i].xpos--;
								}
								
								tempround1 = 0.0000375 * Math.pow(enemyarray[i].damageframedata, 4);
								tempround2 = -0.00263 * Math.pow(enemyarray[i].damageframedata, 3);
								tempround3 = -0.0225 * Math.pow(enemyarray[i].damageframedata, 2);
								tempround4 = 2.7 * enemyarray[i].damageframedata;
								enemyarray[i].ypos = Math.abs(tempround1 + tempround2 + tempround3 + tempround4);
							}
							
						}
						
						//cannon knockback animation
						if (activecannon && ((enemyarray[i].xpos > 1200 - (wavexpos * 50) && enemyarray[i].xpos < 1300 - (wavexpos * 50)) || enemyarray[i].cannonimmunity) && (!enemyarray[i].cannonimmunity || enemyarray[i].cannonframedata != 0) && !enemyarray[i].immunity) {
							
							for (j = enemyarray[i].knockbacks - enemyarray[i].knockbackcount - 1; j >= 0 && !enemyarray[i].immunity; j--) {
							
								if (enemyarray[i].health - 2399999 < j * ((double) enemyarray[i].maxhealth / (double) enemyarray[i].knockbacks)) {
									enemyarray[i].health -= 2399999;
									enemyarray[i].immunity = true;
								}
								
							}
							
							if (!enemyarray[i].immunity) {
								enemyarray[i].cannonimmunity = true;
								enemyarray[i].cannonframedata++;
								
								if (enemyarray[i].cannonframedata > 14) {
									enemyarray[i].cannonimmunity = false;
									enemyarray[i].cannonframedata = 0;
									enemyarray[i].ypos = 0;
									enemyarray[i].health -= 2399999;
								} else {
									
									if (enemyarray[i].xpos > 0) {
										enemyarray[i].xpos -= enemyarray[i].speed + 1;
									}
									
									tempround1 = -0.267 * Math.pow(enemyarray[i].cannonframedata, 2);
									tempround2 = 4 * enemyarray[i].cannonframedata;
									enemyarray[i].ypos = tempround1 + tempround2;
								}
								
							}
							
						}
						
						//stops when in range of cat base
						if (enemyarray[i].xpos + enemyarray[i].baseline + enemyarray[i].minrange >= 1375 && enemyarray[i].idle) {
							enemyarray[i].idle = false;
						}
						
						//at each frame, checks all cats to see if the enemy should stop moving
						if (enemyarray[i].idle && enemyarray[i].alive && !(enemyarray[i].immunity || enemyarray[i].cannonimmunity)) {
							allowmove = true;
							
							for (j = 0; j < numofcats && allowmove; j++) {
								
								if (enemyarray[i].xpos + enemyarray[i].baseline + enemyarray[i].aggrorange > catarray[j].xpos + catarray[j].baseline &&
									enemyarray[i].xpos + enemyarray[i].baseline < catarray[j].xpos + catarray[j].baseline && allowmove) {
									allowmove = false;
								}
								
							}
							
							if (allowmove) {
								enemyarray[i].xpos += enemyarray[i].speed;
							} else {
								enemyarray[i].idle = false;
							}
							
						//otherwise, increment framedata and see where in the attack cycle the enemy is
						} else if (enemyarray[i].alive && !(enemyarray[i].immunity || enemyarray[i].cannonimmunity)) {
							enemyarray[i].framedata++;
							
							if (enemyarray[i].framedata == enemyarray[i].animation) {
								
								for (j = 0; j < numofcats; j++) {
									
									if (enemyarray[i].xpos + enemyarray[i].baseline + enemyarray[i].range > catarray[j].xpos + catarray[j].baseline &&
										enemyarray[i].xpos + enemyarray[i].baseline + enemyarray[i].minrange < catarray[j].xpos + catarray[j].baseline &&
										!(catarray[j].immunity || catarray[j].knockbackimmunity)) {
										catarray[j].health -= enemyarray[i].damage;
										
										if (enemyarray[i].knockbackchance > 0 && random.nextInt(100) < enemyarray[i].knockbackchance) {
											catarray[j].knockbackimmunity = true;
										}
										
										for (k = catarray[j].knockbacks - catarray[j].knockbackcount; k > 0; k--) {
											
											if (catarray[j].health < k * ((double) catarray[j].maxhealth / (double) catarray[j].knockbacks)) {
												catarray[j].immunity = true;
												catarray[j].knockbackcount++;
											}
											
										}
										
									}
									
								}
								
								if (enemyarray[i].xpos + enemyarray[i].baseline + enemyarray[i].range >= 1375) {
									catbasehealth -= enemyarray[i].damage;
								}
								
							} else if (enemyarray[i].framedata == enemyarray[i].animation + enemyarray[i].backswing) {
								enemyarray[i].framedata = 0;
								enemyarray[i].idle = true;
							}
							
						} else if (enemyarray[i].alive) {
							enemyarray[i].framedata = 0;
							enemyarray[i].idle = true;
						}
						
					}
					
					//renders cat images
					for (i = 0; i < 50; i++) {
						
						if (catarray[i].ID == 0) {
							slotarray[i].setVisible(false);
						} else {
							catarray[i].render(slotarray[i]);
							slotarray[i].setVisible(true);
						}
						
					}
					
					//positions cat images
					for (i = 0; i < numofcats; i++) {
						catarray[i].position(slotarray[i], backgroundpicture.getLayoutX());
					}
					
					//renders enemy images
					for (i = 0; i < 12; i++) {
						
						if (enemyarray[i].ID == 0) {
							limitarray[i].setVisible(false);
						} else {
							enemyarray[i].render(limitarray[i]);
							limitarray[i].setVisible(true);
						}
						
					}
					
					//positions enemy images
					for (i = 0; i < numofenemies; i++) {
						enemyarray[i].position(limitarray[i], backgroundpicture.getLayoutX());
					}
					
					//adds money to the wallet when applicable
					if (truemoney != maxmoney) {
						truemoney = IncomeGeneration(truemoney, maxmoney, workercat);
						money = (int) Math.round(truemoney);
					}
					
					moneydisplay.setText(money + "/" + maxmoney + "$");
					
					//displays whether or not the worker cat can be upgraded
					if (workercat < 8) {
						canaffordupgrade = ChangeWorkerCatIcon(canaffordupgrade, truemoney, workercat, workercaticon, workercatactive, workercatidle, workercatlevel, active, idle);
					}
					
					//displays approppriate recharge rectangles for recently spawned cats
					if (firstrow) {
					
						for (i = 0; i < 5; i++) {
							rechargerects[i].setWidth(96 * (System.currentTimeMillis() - rechargearray[i].lastdeployed) / rechargearray[i].maxrecharge);
							rechargerects[i].toFront();
							
							if (System.currentTimeMillis() - rechargearray[i].lastdeployed > rechargearray[i].maxrecharge) {
								rechargerects[i].setVisible(false);
								slotdisplay[i].setImage(slotpictures[i]);
							}
							
						}
						
					} else {
						
						for (i = 5; i < 10; i++) {
							rechargerects[i].setWidth(96 * (System.currentTimeMillis() - rechargearray[i].lastdeployed) / rechargearray[i].maxrecharge);
							rechargerects[i].toFront();
							
							if (System.currentTimeMillis() - rechargearray[i].lastdeployed > rechargearray[i].maxrecharge) {
								rechargerects[i].setVisible(false);
								slotdisplay[i].setImage(slotpictures[i]);
							}
							
						}
						
					}
					
					//logic for the cat cannon wave image
					if (activecannon) {
						truewavexpos++;
						
						if (truewavexpos <= 0) {
							cannonlaser.setStartX(backgroundpicture.getLayoutX() + 1420);
							cannonlaser.setStartY(150);
							cannonlaser.setEndX(backgroundpicture.getLayoutX() - (truewavexpos * 40) - 400);
							cannonlaser.setEndY(270);
						} else if (truewavexpos % 3 == 0) {
							cannonlaser.setVisible(false);
							wavexpos++;
							catcannonwave.setLayoutX(backgroundpicture.getLayoutX() - (wavexpos * 50) + 1200);
						} else if (truewavexpos > 96) {
							truewavexpos = -40;
							wavexpos = 0;
							activecannon = false;
						}
						
					}
					
					//displays the cat cannon charge in distinct steps
					for (i = 4; i >= 0; i--) {
						
						if (cannonrecharge >= i * 750) {
							cannonicon.setImage(catcannonicons[i]);
							break;
						}
						
					}
					
					catbasehealthdisplay.setText(Integer.toString(catbasehealth) + "/120000");
					catbasehealthdisplay.setLayoutX(backgroundpicture.getLayoutX() + 1375);
					enemybasehealthdisplay.setText(Integer.toString(enemybasehealth) + "/750000");
					enemybasehealthdisplay.setLayoutX(backgroundpicture.getLayoutX() + 75);
					
					if (catbasehealth <= 0) {
						backgroundpicture = DefeatScreen(backgroundpicture, defeatscreen, bosspercentage, enemyarray[0].health);
						loser = true;
					} else if (enemybasehealth <= 0) {
						
						if (!firsttimewinner) {
							winner = true;
							firsttimewinner = true;
							leftkey = false;
							rightkey = false;
							tempnumofenemies = numofenemies;
							
							for (i = 0; i < tempnumofenemies; i++) {
								enemyarray = removeEnemy(enemyarray, numofenemies, i);
								numofenemies--;
							}
							
							for (i = 0; i < 10; i++) {
								slotdisplay[i].setVisible(false);
								rechargerects[i].setVisible(false);
							}
							
							catbasehealthdisplay.setVisible(false);
							enemybasehealthdisplay.setVisible(false);
							workercaticon.setVisible(false);
							workercatlevel.setVisible(false);
							workercatcost.setVisible(false);
							cannonicon.setVisible(false);
							moneydisplay.setVisible(false);
							victorytext.setVisible(true);
							
							if (getcatway || stagecomplete) {
								tonextbox = true;
							}
							
						}
						
						delaytimer++;
						
						if (delaytimer == 120 && !getcatway) {
							getcatway = 100 > random.nextInt(100);
							
							if (getcatway) {
								getcatway = true;
								catwayunlock.setVisible(true);
								catwaytext.setVisible(true);
								catwayunlock.toFront();
								catwaytext.toFront();
								
								delaytimer = 0;
							}
							
						} else if (delaytimer == 120 && !stagecomplete && tonextbox) {
							stagecomplete = true;
							firsttimereward.setVisible(true);
							firstcatfoodtext.setVisible(true);
							firstenergytext.setVisible(true);
							firsttimereward.toFront();
							firstcatfoodtext.toFront();
							firstenergytext.toFront();
							
							delaytimer = 0;
							tonextbox = false;
						} else if (delaytimer == 15 && tonextbox) {
							levelfinish.setVisible(true);
							levelfinish.toFront();
						}
						
						catwaytext.setFill(delaytimer % 10 < 5 ? purpleflash : yellowflash);
						firstcatfoodtext.setFill(delaytimer % 10 < 5 ? purpleflash : yellowflash);
						firstenergytext.setFill(delaytimer % 10 < 5 ? purpleflash : yellowflash);
					}
					
					if (winner && backgroundpicture.getLayoutX() <= -40) {
						backgroundpicture.setLayoutX(backgroundpicture.getLayoutX() + 40);
						
						if (backgroundpicture.getLayoutX() > 0) {
							backgroundpicture.setLayoutX(0);
						}
						
					}
					
				}
				
			}
			
		};
		timer.start();
		
		primaryStage.setScene(scene);
		primaryStage.show();
		//Adding everything to the groups and scenes
		
	}
	
	private static void Instructions(ImageView whitebackground1, ImageView toMenu1, Text textinstruct1[], boolean paused1) {
		whitebackground1.setVisible(true);
		whitebackground1.toFront();
		toMenu1.setVisible(true);
		toMenu1.toFront();
		
		textinstruct1[0] = new Text("Use 1 - 5 to deploy units in those slots, and tab to switch to the next line of slots.");
		textinstruct1[1] = new Text("Use A/D to move left/right and W/D to zoom in/out.");
		textinstruct1[2] = new Text("To upgrade your worker cat level, press the up arrow.");
		textinstruct1[3] = new Text("Pause/unpause the game while in battle by pressing P.");
		textinstruct1[4] = new Text("Finally, use Enter to fire the cat cannon.");
		
		for (int i1 = 0; i1 < 5; i1++) {
			textinstruct1[i1].setX(10);
			textinstruct1[i1].setY((30 * i1) + 20);
			textinstruct1[i1].setFont(Font.font(16));
		}
		
		toMenu1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
				for (int i1 = 0; i1 < 5; i1++) {
					textinstruct1[i1].setVisible(false);
				}
				
				whitebackground1.setVisible(false);
				toMenu1.setVisible(false);
				
				event.consume();
				return;
			}
			
		});
		
	}
	
	private static void setupLevel(ImageView startbackground1, ImageView selectstart1, ImageView selecthelp1, ImageView backgroundpicture1, Text moneydisplay1, ImageView workercaticon1, Text workercatlevel1, Text workercatcost1, ImageView cannonicon1, ImageView[] slotdisplay1, Text catbasehealthdisplay1, Text enemybasehealthdisplay1) {
		startbackground1.setVisible(false);
		selectstart1.setVisible(false);
		selecthelp1.setVisible(false);
		backgroundpicture1.setVisible(true);
		backgroundpicture1.toBack();
		moneydisplay1.setVisible(true);
		workercaticon1.setVisible(true);
		workercatlevel1.setVisible(true);
		workercatcost1.setVisible(true);
		cannonicon1.setVisible(true);
		catbasehealthdisplay1.setVisible(true);
		enemybasehealthdisplay1.setVisible(true);
		
		for (int i1 = 0; i1 < 10; i1++) {
			slotdisplay1[i1].setVisible(true);
		}
		
		return;
	}
	
	private static double UpgradeWallet(double truemoney1, int workercat1, Text workercatlevel1, Text workercatcost1) {
		truemoney1 -= workercat1 * 560;
		workercat1++;
		workercatlevel1.setText("Level " + workercat1);
		workercatcost1.setText(workercat1 < 8 ? (workercat1 * 560) + "$" : "MAX");
		
		return truemoney1;
	}
	
	private static ImageView moveLR(ImageView backgroundpicture1, boolean leftkey1, boolean rightkey1) {
		
		if (leftkey1 && backgroundpicture1.getLayoutX() < 0) {
			backgroundpicture1.relocate(backgroundpicture1.getLayoutX() + 10, -380);
		}
		
		if (rightkey1 && backgroundpicture1.getLayoutX() > -800) {
			backgroundpicture1.relocate(backgroundpicture1.getLayoutX() - 10, -380);
		}
		
		return backgroundpicture1;
	}
	
	private static FieldEnemies[] addEnemy(FieldEnemies[] enemyarray1, int numofenemies1, int spawninstructionsID1) {
		
		switch (spawninstructionsID1) {
			case 0:
				enemyarray1[numofenemies1] = new FieldEnemies(1, 125, 2400000, 2400000, 4, 0, 120, 32000, 120, 0, 0, 0, 355, 200, 150, 0, 0, 0.2667, 75, 0, 19750, 0, true, true, false, false, true, new Image("Enemy_ID_0.png"), new Image("Enemy_ID_0_Animation.png"), new Image("Enemy_ID_0_Attack.png"), new Image("Enemy_ID_0_Backswing.png"));
				break;
			case 1:
				enemyarray1[numofenemies1] = new FieldEnemies(2, 0, 363000, 363000, 2, 0, 15, 2247, 15, 0, 0, 0, 186, 186, 0, 0, 0, 0.3333, 150, 0, 3575, 15, true, true, false, false, true, new Image("Enemy_ID_0.png"), new Image("Enemy_ID_0_Animation.png"), new Image("Enemy_ID_0_Attack.png"), new Image("Enemy_ID_0_Backswing.png"));
				break;
			case 2:
				enemyarray1[numofenemies1] = new FieldEnemies(3, 0, 196000, 196000, 6, 0, 10, 11134, 10, 0, 0, 0, 83, 83, 0, 0, 0, 2.1333, 150, 0, 1975, 0, true, true, false, false, true, new Image("Enemy_ID_0.png"), new Image("Enemy_ID_0_Animation.png"), new Image("Enemy_ID_0_Attack.png"), new Image("Enemy_ID_0_Backswing.png"));
				break;
			case 3:
				enemyarray1[numofenemies1] = new FieldEnemies(4, 0, 500, 500, 1, 0, 15, 200, 5, 0, 0, 0, 110, 110, 0, 0, 0, 1.3333, 150, 0, 296, 15, true, true, false, false, true, new Image("Enemy_ID_0.png"), new Image("Enemy_ID_0_Animation.png"), new Image("Enemy_ID_0_Attack.png"), new Image("Enemy_ID_0_Backswing.png"));
				break;
		}
		
		enemyarray1[numofenemies1].idleimage = new Image("Enemy_ID_" + (spawninstructionsID1 + 1) + ".png");
		enemyarray1[numofenemies1].animationimage = new Image("Enemy_ID_" + (spawninstructionsID1 + 1) + "_Animation.png");
		enemyarray1[numofenemies1].attackimage = new Image("Enemy_ID_" + (spawninstructionsID1 + 1) + "_Attack.png");
		enemyarray1[numofenemies1].backswingimage = new Image("Enemy_ID_" + (spawninstructionsID1 + 1) + "_Backswing.png");
		
		return enemyarray1;
	}
	
	private static boolean isRechargeDone(DeploySlots[] rechargearray1, boolean firstrow1, int i1) {
		long currenttime = System.currentTimeMillis();
		i1--;
		
		if (!firstrow1) {
			i1 += 5;
		}
		
		if (currenttime - rechargearray1[i1].lastdeployed > rechargearray1[i1].maxrecharge) {
			rechargearray1[i1].ready = true;
		}
		
		return rechargearray1[i1].ready;
	}
	
	private static FieldCats[] addCat(FieldCats[] catarray1, boolean firstrow1, int catdeployed1, int numofcats1) {
		
		if (firstrow1) {
		
			switch (catdeployed1) {
				case 1:
					catarray1[numofcats1] = new FieldCats(1, 60, 6290, 6290, 3, 0, 46, 925, 44, 64, 0, 0, 60, 0, 0, 0.6667, 1375, 0, 510, false, false, false, false, true, false, false, true, new Image("Unit_ID_0.png"), new Image("Unit_ID_0_Animation.png"), new Image("Unit_ID_0_Attack.png"), new Image("Unit_ID_0_Backswing.png"));
					break;
				case 2:
					catarray1[numofcats1] = new FieldCats(2, 40, 11900, 11900, 3, 0, 32, 3400, 32, 88, 0, 0, 35, 0, 0, 0.5333, 1375, 0, 1125, true, false, false, false, true, false, false, true, new Image("Unit_ID_0.png"), new Image("Unit_ID_0_Animation.png"), new Image("Unit_ID_0_Attack.png"), new Image("Unit_ID_0_Backswing.png"));
					break;
				case 3:
					catarray1[numofcats1] = new FieldCats(3, 110, 8500, 8500, 5, 0, 4, 595, 28, 32, 0, 0, 60, 0, 0, 3.1333, 1375, 0, 825, true, false, false, false, true, false, false, true, new Image("Unit_ID_0.png"), new Image("Unit_ID_0_Animation.png"), new Image("Unit_ID_0_Attack.png"), new Image("Unit_ID_0_Backswing.png"));
					break;
				case 4:
					catarray1[numofcats1] = new FieldCats(4, 0, 15300, 15300, 2, 0, 24, 2176, 76, 80, 0, 0, 160, 0, 0, 0.5333, 1375, 0, 630, true, false, false, false, true, false, false, true, new Image("Unit_ID_0.png"), new Image("Unit_ID_0_Animation.png"), new Image("Unit_ID_0_Attack.png"), new Image("Unit_ID_0_Backswing.png"));
					break;
				case 5:
					catarray1[numofcats1] = new FieldCats(5, 75, 17850, 17850, 1, 0, 10, 1020, 10, 0, 0, 0, 65, 0, 0, 0.5333, 1375, 0, 315, false, false, true, false, true, false, false, true, new Image("Unit_ID_0.png"), new Image("Unit_ID_0_Animation.png"), new Image("Unit_ID_0_Attack.png"), new Image("Unit_ID_0_Backswing.png"));
					break;
			}
			
			catarray1[numofcats1].idleimage = new Image("Unit_ID_" + catdeployed1 + ".png");
			catarray1[numofcats1].animationimage = new Image("Unit_ID_" + catdeployed1 + "_Animation.png");
			catarray1[numofcats1].attackimage = new Image("Unit_ID_" + catdeployed1 + "_Attack.png");
			catarray1[numofcats1].backswingimage = new Image("Unit_ID_" + catdeployed1 + "_Backswing.png");
		} else {
			
			switch (catdeployed1) {
				case 1:
					catarray1[numofcats1] = new FieldCats(6, 0, 9435, 9435, 4, 0, 24, 3400, 71, 64, 0, 0, 200, 0, 0, 1.4, 1375, 0, 525, false, true, false, false, true, false, false, true, new Image("Unit_ID_0.png"), new Image("Unit_ID_0_Animation.png"), new Image("Unit_ID_0_Attack.png"), new Image("Unit_ID_0_Backswing.png"));
					break;
				case 2:
					catarray1[numofcats1] = new FieldCats(7, 28, 15300, 15300, 1, 0, 1, 25500, /*400*/ 1, 0, 0, 0, 14, 0, 0, 5, 1375, 0, 750, false, false, false, true, true, false, false, true, new Image("Unit_ID_0.png"), new Image("Unit_ID_0_Animation.png"), new Image("Unit_ID_0_Attack.png"), new Image("Unit_ID_0_Backswing.png"));
					break;
				case 3:
					catarray1[numofcats1] = new FieldCats(8, 0, 26100, 26100, 5, 0, 12, 682, 8, 0, 0, 0, 140, 0, 0, 4.4, 1375, 0, 750, true, false, false, false, true, false, false, true, new Image("Unit_ID_0.png"), new Image("Unit_ID_0_Animation.png"), new Image("Unit_ID_0_Attack.png"), new Image("Unit_ID_0_Backswing.png"));
					break;
				case 4:
					catarray1[numofcats1] = new FieldCats(9, 95, 15660, 15660, 4, 0, 40, 10497, 42, 80, 0, 0, 85, 0, 0, 0.6667, 1375, 0, 975, true, false, false, false, true, false, false, true, new Image("Unit_ID_0.png"), new Image("Unit_ID_0_Animation.png"), new Image("Unit_ID_0_Attack.png"), new Image("Unit_ID_0_Backswing.png"));
					break;
				case 5:
					catarray1[numofcats1] = new FieldCats(10, 0, 25500, 25500, 6, 0, 0, 85000, 0, 0, 0, 0, 200, 0, 0, 4, 1375, 0, 4500, true, false, false, false, true, false, false, true, new Image("Unit_ID_0.png"), new Image("Unit_ID_0_Animation.png"), new Image("Unit_ID_0_Attack.png"), new Image("Unit_ID_0_Backswing.png"));
					break;
			}
			
			catarray1[numofcats1].idleimage = new Image("Unit_ID_" + (catdeployed1 + 5) + ".png");
			catarray1[numofcats1].animationimage = new Image("Unit_ID_" + (catdeployed1 + 5) + "_Animation.png");
			catarray1[numofcats1].attackimage = new Image("Unit_ID_" + (catdeployed1 + 5) + "_Attack.png");
			catarray1[numofcats1].backswingimage = new Image("Unit_ID_" + (catdeployed1 + 5) + "_Backswing.png");
		}
		
		return catarray1;
	}
	
	private static FieldCats[] removeCat(FieldCats[] catarray1, int numofcats1, int i1) {
		
		for (int j1 = i1; j1 < numofcats1 - 1; j1++) {
			catarray1[j1] = catarray1[j1 + 1];
		}
		
		catarray1[numofcats1 - 1] = new FieldCats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, false, false, false, new Image("Unit_ID_0.png"), new Image("Unit_ID_0_Animation.png"), new Image("Unit_ID_0_Attack.png"), new Image("Unit_ID_0_Backswing.png"));
		return catarray1;
	}
	
	private static FieldEnemies[] removeEnemy(FieldEnemies[] enemyarray1, int numofenemies1, int i1) {
		
		for (int j1 = i1; j1 < numofenemies1 - 1; j1++) {
			enemyarray1[j1] = enemyarray1[j1 + 1];
		}
		
		enemyarray1[numofenemies1 - 1] = new FieldEnemies(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, new Image("Enemy_ID_0.png"), new Image("Enemy_ID_0_Animation.png"), new Image("Enemy_ID_0_Attack.png"), new Image("Enemy_ID_0_Backswing.png"));
		return enemyarray1;
	}
	
	private static double IncomeGeneration(double truemoney1, int maxmoney1, int workercat1) {
		return (truemoney1 = truemoney1 + ((workercat1 * 9.44286) + 175.007) / 6 <= maxmoney1 ? truemoney1 + ((workercat1 * 9.44286) + 175.007) / 6 : maxmoney1);
	}
	
	private static boolean ChangeWorkerCatIcon(boolean canaffordupgrade1, double truemoney1, int workercat1, ImageView workercaticon1, Image workercatactive1, Image workercatidle1, Text workercatlevel1, Color active1, Color idle1) {
		
		if (!canaffordupgrade1 && truemoney1 > workercat1 * 560) {
			canaffordupgrade1 = true;
			workercaticon1.setImage(workercatactive1);
			workercatlevel1.setFill(active1);
		} else if (canaffordupgrade1 && truemoney1 < workercat1 * 560) {
			canaffordupgrade1 = false;
			workercaticon1.setImage(workercatidle1);
			workercatlevel1.setFill(idle1);
		}
		
		return canaffordupgrade1;
	}
	
	private static ImageView DefeatScreen(ImageView backgroundpicture1, Image defeatscreen1, Text bosspercentage1, int clionelhealth1) {
		backgroundpicture1.setImage(defeatscreen1);
		backgroundpicture1.relocate(0, 0);
		backgroundpicture1.toFront();
		
		bosspercentage1.setText(clionelhealth1 / 24000 < 1 ? "1%" : Integer.toString(Math.round(clionelhealth1 / 24000)) + "%");
		bosspercentage1.setVisible(true);
		bosspercentage1.toFront();
		
		return backgroundpicture1;
	}
	
}