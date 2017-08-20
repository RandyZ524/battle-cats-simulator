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
import javafx.stage.Stage;

import java.io.*;
import java.util.*;
//Importing my stuff

class FieldCats {
	int ID, baseline, maxhealth, health, knockbacks, knockbackcount, animation, damage, backswing, framedata, range, damageframedata, knockbackframedata, price;
	double speed, xpos, ypos;
	boolean area, massivedamage, resistance, doublecash, idle, immunity, knockbackimmunity, alive;
	
	FieldCats(int ID, int baseline, int maxhealth, int health, int knockbacks, int knockbackcount, int animation, int damage, int backswing, int framedata,
			  int range, int damageframedata, int knockbackframedata, double speed, double xpos, double ypos, int price, boolean area, boolean massivedamage,
			  boolean resistance, boolean doublecash, boolean idle, boolean immunity, boolean knockbackimmunity, boolean alive) {
		this.ID = ID;
		this.baseline = baseline;
		this.maxhealth = maxhealth;
		this.health = health;
		this.knockbacks = knockbacks;
		this.knockbackcount = knockbackcount;
		this.animation = animation;
		this.damage = damage;
		this.backswing = backswing;
		this.framedata = framedata;
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
	}
	
}

class FieldEnemies {
	int ID, baseline, maxhealth, health, knockbacks, knockbackcount, animation, damage, backswing, framedata, range, aggrorange, minrange, damageframedata,
	    cannonframedata, pricedrop, knockbackchance;
	double speed, xpos, ypos;
	boolean area, idle, immunity, cannonimmunity, alive;
	
	FieldEnemies(int ID, int baseline, int maxhealth, int health, int knockbacks, int knockbackcount, int animation, int damage, int backswing, int framedata,
				 int range, int aggrorange, int minrange, int damageframedata, int cannonframedata, double speed, double xpos, double ypos, int pricedrop,
				 int knockbackchance, boolean area, boolean idle, boolean immunity, boolean cannonimmunity, boolean alive) {
		this.ID = ID;
		this.baseline = baseline;
		this.maxhealth = maxhealth;
		this.health = health;
		this.knockbacks = knockbacks;
		this.knockbackcount = knockbackcount;
		this.damage = damage;
		this.animation = animation;
		this.backswing = backswing;
		this.framedata = framedata;
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
 
	int i, j, k, catdeployed, deleteunit, unitID, unitanimation, unitbackswing, unitframedata, money, success;
	int truewavexpos = -40;
	int numofcats, numofenemies, cannonrecharge, linenumber = 0;
	int lowestunit = -1;
	double unitxpos, unitypos, lowestxpos = 0;
	int maxmoney = 6000;
	int workercat = 1;
	int catbasehealth = 120000;
	int enemybasehealth = 750000;
	double truemoney, wavexpos, tempround1, tempround2, tempround3, tempround4 = 0;
	long globaltimer = 0;
	boolean inthelevel, leftkey, rightkey, allowmove, firstrow, canaffordupgrade, activecannon;
	boolean godmode = false;
	
	FieldCats catarray[] = new FieldCats[50];
	FieldEnemies enemyarray[] = new FieldEnemies[12];
	DeploySlots rechargearray[] = new DeploySlots[10];
	EnemySchematics spawninstructions[] = new EnemySchematics[100];
	
	ImageView slotarray[] = new ImageView[50];
	Image catpictures[] = new Image[11];
	Image catpicturesanimation[] = new Image[11];
	Image catpicturesattack[] = new Image[11];
	Image catpicturesbackswing[] = new Image[11];
	ImageView limitarray[] = new ImageView[12];
	Image enemypictures[] = new Image[4];
	Image enemypicturesanimation[] = new Image[4];
	Image enemypicturesattack[] = new Image[4];
	Image enemypicturesbackswing[] = new Image[4];
	Text textinstruct[] = new Text[6];
	Image slotpictures[] = new Image[11];
	Image slotpicturesidle[] = new Image[11];
	ImageView slotdisplay[] = new ImageView[10];
	Image catcannonicons[] = new Image[5];
	Rectangle rechargerects[] = new Rectangle[10];
	
	ImageView startbackground = new ImageView();
	ImageView selectstart = new ImageView();
	ImageView selecthelp = new ImageView();
	ImageView toMenu = new ImageView();
	ImageView backgroundpicture = new ImageView();
	ImageView workercaticon = new ImageView();
	ImageView cannonicon = new ImageView();
	ImageView catcannonwave = new ImageView();
	Line cannonlaser = new Line();
	
	Text moneydisplay = new Text();
	Text workercatlevel = new Text();
	Text workercatcost = new Text();
	Text catbasehealthdisplay = new Text();
	Text enemybasehealthdisplay = new Text();
	
	Color idle = Color.rgb(195, 195, 195);
	Color active = Color.rgb(255, 242, 0);
	
	byte inbuf[] = new byte[20500];     //Input buffer for the whole file
	String str = new String();
	
	Random random = new Random();
	
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("A Battle Cats simulator");
		
		Image logo = new Image("Battle_Cats_Logo.png");
		Image startbutton = new Image("Start_Button.png");
		Image helpbutton = new Image("Help_Button.png");
		Image exithelpbutton = new Image("Exit_Help_Button.png");
		Image background = new Image("Background_Picture.png");
		Image workercatidle = new Image("Worker_Cat_Idle.png");
		Image workercatactive = new Image("Worker_Cat_Active.png");
		Image wave = new Image("Cat_Cannon_Wave.png");
		
		startbackground.setImage(logo);
		
		selectstart.setImage(startbutton);
		selectstart.setLayoutX(250);
		selectstart.setLayoutY(250);
		
		selecthelp.setImage(helpbutton);
		selecthelp.setLayoutX(250);
		selecthelp.setLayoutY(310);
		
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
		moneydisplay.setFill(Color.rgb(250, 203, 1));
		moneydisplay.setVisible(false);
		
		workercatlevel.setText("Level 1");
		workercatlevel.setLayoutX(5);
		workercatlevel.setLayoutY(295);
		workercatlevel.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		workercatlevel.setFill(idle);
		workercatlevel.setVisible(false);
		
		workercatcost.setText("560$");
		workercatcost.setLayoutX(5);
		workercatcost.setLayoutY(372);
		workercatcost.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
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
		catbasehealthdisplay.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		
		enemybasehealthdisplay.setText(Integer.toString(enemybasehealth) + "/750000");
		enemybasehealthdisplay.setLayoutX(800);
		enemybasehealthdisplay.setLayoutY(105);
		enemybasehealthdisplay.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		
		Group root = new Group(startbackground, selectstart, selecthelp, toMenu, backgroundpicture, moneydisplay, workercaticon, workercatlevel, workercatcost,
							   cannonicon, catcannonwave, cannonlaser, catbasehealthdisplay, enemybasehealthdisplay);
		Scene scene = new Scene(root, 800, 380);
		
		for (i = 0; i < 50; i++) {
			catarray[i] = new FieldCats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, false, false, false);
			
			slotarray[i] = new ImageView();
			slotarray[i].setVisible(false);
			root.getChildren().add(slotarray[i]);
		}
		
		for (i = 0; i < 12; i++) {
			enemyarray[i] = new FieldEnemies(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false);
			
			limitarray[i] = new ImageView();
			limitarray[i].setVisible(false);
			root.getChildren().add(limitarray[i]);
		}
		
		for (i = 1; i < 2; i++) {
			catpictures[i - 1] = new Image("Unit_ID_" + i + ".png");
			catpicturesanimation[i - 1] = new Image("Unit_ID_" + i + "_Animation.png");
			catpicturesattack[i - 1] = new Image("Unit_ID_" + i + "_Attack.png");
			catpicturesbackswing[i - 1] = new Image("Unit_ID_" + i + "_Backswing.png");
		}
		
		for (i = 0; i < 10; i++) {
			rechargearray[i] = new DeploySlots(0, 0, true);
		}
		
		for (i = 1; i < 5; i++) {
			enemypictures[i - 1] = new Image("Enemy_ID_" + i + ".png");
		}
		
		for (i = 1; i < 2; i++) {
			enemypicturesanimation[i - 1] = new Image("Enemy_ID_" + i + "_Animation.png");
			enemypicturesattack[i - 1] = new Image("Enemy_ID_" + i + "_Attack.png");
			enemypicturesbackswing[i - 1] = new Image("Enemy_ID_" + i + "_Backswing.png");
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
		
		for (i = 0; i < 10; i++) {
			rechargerects[i] = new Rectangle(132 + (i * 110), 356, 0, 10);
			rechargerects[i].setFill(Color.rgb(81, 255, 220));
			rechargerects[i].setVisible(false);
			root.getChildren().add(rechargerects[i]);
		}
		
		rechargearray = AssignRechargeTimes(rechargearray);
		//Setting up variables (buttons, scene, group)
		
		LineNumberReader lnr = new LineNumberReader(new FileReader(new File("Enemy_Spawn_Schematics.txt")));
		lnr.skip(Long.MAX_VALUE);
		linenumber = lnr.getLineNumber();
		//Getting the number of lines to simplify file reader and spawn instructions
		
		int readID, readspawncount, readfirstspawn, readnextspawnslower = 0, readnextspawnsupper = 0;
		
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
		selecthelp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				Instructions(scene, startbackground, selectstart, selecthelp, toMenu, textinstruct);
				root.getChildren().addAll(textinstruct[0], textinstruct[1], textinstruct[2], textinstruct[3], textinstruct[4]);
				event.consume();
			}
			
		});
		
		selectstart.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
				if (godmode) {
					slotpictures[5] = slotpictures[10];
					slotpicturesidle[5] = slotpicturesidle[10];
					slotdisplay[5].setImage(slotpictures[5]);
				}
				
				setupLevel(startbackground, selectstart, selecthelp, backgroundpicture, moneydisplay, workercaticon, workercatlevel, workercatcost, cannonicon,
						   slotdisplay, catbasehealthdisplay, enemybasehealthdisplay);
				inthelevel = true;
				firstrow = true;
				canaffordupgrade = false;
				activecannon = false;
				event.consume();
			}
			
		});
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			
			public void handle(KeyEvent event) {
				
				if (inthelevel) {
					
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
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			
			public void handle(KeyEvent event) {
				
				if (inthelevel) {
					
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
					
					if ((event.getText().equals("1") || event.getText().equals("2") || event.getText().equals("3") ||
						 event.getText().equals("4") || event.getText().equals("5")) && numofcats < 50) {
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
								catarray[numofcats] = new FieldCats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, false, false, false);
							}
							
						}
						
					}
					
				}
				
			}
			
		});
		
		enemyarray[0] = new FieldEnemies(1, 125, 2400000, 2400000, 4, 0, 120, 32000, 120, 0, 355, 200, 150, 0, 0, 0.2667, 75, 0, 19750, 0, true, true, false, false, true);
		numofenemies++;
		
		AnimationTimer timer = new AnimationTimer() {
            @Override
			
            public void handle(long now) {
				
				if (inthelevel) {
					backgroundpicture = moveLR(backgroundpicture, leftkey, rightkey);
					globaltimer++;
					
					if (cannonrecharge < 3000) {
						cannonrecharge = 3000;
					}
					/*
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
								spawninstructions[i].nextspawnrandom = random.nextInt(spawninstructions[i].nextspawnsupper - spawninstructions[i].nextspawnslower + 1) +
																	   spawninstructions[i].nextspawnslower;
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
					*/
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
									break;
								}
								
							} else {
								tempround1 = 0.0000375 * Math.pow(catarray[i].damageframedata, 4);
								tempround2 = -0.00263 * Math.pow(catarray[i].damageframedata, 3);
								tempround3 = -0.0225 * Math.pow(catarray[i].damageframedata, 2);
								tempround4 = 2.7 * catarray[i].damageframedata;
								
								catarray[i].xpos -= catarray[i].speed + 1;
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
								
								catarray[i].xpos = catarray[i].xpos - (tempround1 / tempround2) + catarray[i].speed;
							}
							
						}
						
						if (catarray[i].idle && catarray[i].alive) {
							allowmove = true;
							
							for (j = 0; j < numofenemies && allowmove; j++) {
								allowmove = StopMoving(catarray[i].xpos, catarray[i].baseline, catarray[i].range, enemyarray[j].xpos, enemyarray[j].baseline);
							}
							
							if (allowmove) {
								catarray[i].xpos -= catarray[i].speed;
							} else {
								catarray[i].idle = false;
							}
							
						} else if (catarray[i].alive) {
							catarray[i].framedata++;
							
							if (catarray[i].framedata == catarray[i].animation) {
								
								for (j = 0; j < numofenemies; j++) {
									
									if (catarray[i].xpos + catarray[i].baseline - catarray[i].range < enemyarray[j].xpos + enemyarray[j].baseline &&
										!(enemyarray[j].immunity || !enemyarray[j].cannonimmunity)) {
										
										if (catarray[i].area) {	
											enemyarray[j].health -= catarray[i].damage;
										} else if (lowestunit == -1) {
											lowestunit = j;
											
											if (j == numofenemies - 1) {
												enemyarray[lowestunit].health -= catarray[i].damage;
												System.out.println("-----------------------------------------");
											}
											
										} else if (enemyarray[j].xpos + enemyarray[j].baseline > enemyarray[lowestunit].xpos + enemyarray[lowestunit].baseline) {
											lowestunit = j;
											
											if (j == numofenemies - 1) {
												enemyarray[lowestunit].health -= catarray[i].damage;
												System.out.println("-----------------------------------------");
											}
											
										}
										
										for (k = enemyarray[j].knockbacks - enemyarray[j].knockbackcount; k > 0; k--) {
											
											if (enemyarray[j].health < k * ((double) enemyarray[j].maxhealth / (double) enemyarray[j].knockbacks)) {
												enemyarray[j].immunity = true;
												enemyarray[j].knockbackcount++;
											}
											
										}
									
									}
									
								}
								
								lowestunit = -1;
							} else if (catarray[i].framedata == catarray[i].animation + catarray[i].backswing) {
								catarray[i].framedata = 0;
								catarray[i].idle = true;
							}
							
						} else {
							catarray = removeCat(catarray, numofcats, i);
							numofcats--;
							break;
						}
						
					}
					
					for (i = 0; i < numofenemies; i++) {
						System.out.println(enemyarray[i].health);
						
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
									break;
								}
								
							} else {
								
								if (enemyarray[i].xpos > 0) {
									enemyarray[i].xpos -= enemyarray[i].speed + 1;
								}
								
								tempround1 = 0.0000375 * Math.pow(enemyarray[i].damageframedata, 4);
								tempround2 = -0.00263 * Math.pow(enemyarray[i].damageframedata, 3);
								tempround3 = -0.0225 * Math.pow(enemyarray[i].damageframedata, 2);
								tempround4 = 2.7 * enemyarray[i].damageframedata;
								enemyarray[i].ypos = Math.abs(tempround1 + tempround2 + tempround3 + tempround4);
							}
							
						}
						
						//cannon knockback animation
						if (activecannon && ((enemyarray[i].xpos > 1200 - (wavexpos * 50) && enemyarray[i].xpos < 1300 - (wavexpos * 50)) ||
							enemyarray[i].cannonimmunity) && (!enemyarray[i].cannonimmunity || enemyarray[i].cannonframedata != 0) && !enemyarray[i].immunity) {
							
							for (j = enemyarray[i].knockbacks - enemyarray[i].knockbackcount; j > 0 && !enemyarray[i].immunity; j--) {
							
								if (enemyarray[i].health - 500000 >= j * ((double) enemyarray[i].maxhealth / (double) enemyarray[i].knockbacks)) {
									enemyarray[i].cannonimmunity = true;
									enemyarray[i].cannonframedata++;
									
									if (enemyarray[i].cannonframedata > 14) {
										enemyarray[i].cannonimmunity = false;
										enemyarray[i].cannonframedata = 0;
										enemyarray[i].ypos = 0;
										enemyarray[i].health -= 500000;
									} else {
										
										if (enemyarray[i].xpos > 0) {
											enemyarray[i].xpos -= enemyarray[i].speed + 1;
										}
										
										tempround1 = -0.267 * Math.pow(enemyarray[i].cannonframedata, 2);
										tempround2 = 4 * enemyarray[i].cannonframedata;
										enemyarray[i].ypos = tempround1 + tempround2;
									}
									
								} else {
									enemyarray[i].health -= 500000;
									enemyarray[i].immunity = true;
								}
								
							}
							
						}
						
						if (enemyarray[i].xpos + enemyarray[i].baseline + enemyarray[i].minrange >= 1375 && enemyarray[i].idle) {
							enemyarray[i].idle = false;
						}
						
						if (enemyarray[i].idle && enemyarray[i].alive) {
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
							
						} else if (enemyarray[i].alive && !enemyarray[i].immunity && !enemyarray[i].cannonimmunity) {
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
					
					for (i = 0; i < 50; i++) {
						unitID = catarray[i].ID;
						unitanimation = catarray[i].animation;
						unitbackswing = catarray[i].backswing;
						unitframedata = catarray[i].framedata;
						slotarray = RenderCats(unitID, unitanimation, unitbackswing, unitframedata, slotarray, i, catpictures, catpicturesanimation, 
											   catpicturesattack, catpicturesbackswing);
					}
					
					for (i = 0; i < numofcats; i++) {
						unitxpos = catarray[i].xpos;
						unitypos = catarray[i].ypos;
						unitID = catarray[i].ID;
						slotarray = PositionCats(slotarray, i, unitxpos, unitypos, backgroundpicture, catpictures, unitID);
					}
					
					for (i = 0; i < 12; i++) {
						unitID = enemyarray[i].ID;
						unitanimation = enemyarray[i].animation;
						unitbackswing = enemyarray[i].backswing;
						unitframedata = enemyarray[i].framedata;
						limitarray = RenderEnemies(unitID, unitanimation, unitbackswing, unitframedata, limitarray, i, enemypictures, enemypicturesanimation,
												   enemypicturesattack, enemypicturesbackswing);
					}
					
					for (i = 0; i < numofenemies; i++) {
						unitxpos = enemyarray[i].xpos;
						unitypos = enemyarray[i].ypos;
						unitID = enemyarray[i].ID;
						limitarray = PositionEnemies(limitarray, i, unitxpos, unitypos, backgroundpicture, enemypictures, unitID);
					}
					
					if (truemoney != maxmoney) {
						truemoney = IncomeGeneration(truemoney, maxmoney, workercat);
						money = (int) Math.round(truemoney);
					}
					
					moneydisplay.setText(money + "/" + maxmoney + "$");
					
					if (workercat < 8) {
						canaffordupgrade = ChangeWorkerCatIcon(canaffordupgrade, truemoney, workercat, workercaticon, workercatactive, workercatidle, workercatlevel, active, idle);
					}
					
					for (i = 0; i < 5; i++) {
						rechargerects[i].setWidth(96 * (System.currentTimeMillis() - rechargearray[i].lastdeployed) / rechargearray[i].maxrecharge);
						
						if (System.currentTimeMillis() - rechargearray[i].lastdeployed > rechargearray[i].maxrecharge) {
							rechargerects[i].setVisible(false);
							slotdisplay[i].setImage(slotpictures[i]);
						}
						
					}
					
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
					
					for (i = 4; i >= 0; i--) {
						
						if (cannonrecharge >= i * 750) {
							cannonicon.setImage(catcannonicons[i]);
							break;
						}
						
					}
					
					catbasehealthdisplay.setText(Integer.toString(catbasehealth) + "/120000");
					catbasehealthdisplay.setLayoutX(backgroundpicture.getLayoutX() + 1375);
					enemybasehealthdisplay.setText(Integer.toString(enemybasehealth) + "/750000");
				}
				
			}
			
		};
		timer.start();
		
		primaryStage.setScene(scene);
		primaryStage.show();
		//Adding everything to the groups and scenes
		
	}
	
	public static DeploySlots[] AssignRechargeTimes(DeploySlots[] rechargearray1) {
		rechargearray1[0].maxrecharge = 4467;
		rechargearray1[1].maxrecharge = 3133;
		rechargearray1[2].maxrecharge = 5800;
		rechargearray1[3].maxrecharge = 8667;
		rechargearray1[4].maxrecharge = 2467;
		rechargearray1[5].maxrecharge = 4467;
		rechargearray1[6].maxrecharge = 17800;
		rechargearray1[7].maxrecharge = 2800;
		rechargearray1[8].maxrecharge = 2133;
		rechargearray1[9].maxrecharge = 96467;
		
		return rechargearray1;
	}
	
	public static void Instructions(Scene scene1, ImageView startbackground1, ImageView selectstart1, ImageView selecthelp1, ImageView toMenu1, Text textinstruct1[]) {
		startbackground1.setVisible(false);
		selectstart1.setVisible(false);
		selecthelp1.setVisible(false);
		toMenu1.setVisible(true);
		
		textinstruct1[0] = new Text("Use 1 - 5 to deploy units in those slots, and tab to switch to the next line of slots.");
		textinstruct1[1] = new Text("Use A/D to move left/right and W/D to zoom in/out.");
		textinstruct1[2] = new Text("(ZOOM NOT IMPLEMENTED YET BECAUSE IT'S FREAKIN HARD MAN IDK HOW TO YET)");
		textinstruct1[3] = new Text("To upgrade your worker cat level, press the up arrow.");
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
				
				toMenu1.setVisible(false);
				
				startbackground1.setVisible(true);
				selectstart1.setVisible(true);
				selecthelp1.setVisible(true);
				
				event.consume();
				return;
			}
			
		});
		
	}
	
	public static void setupLevel(ImageView startbackground1, ImageView selectstart1, ImageView selecthelp1, ImageView backgroundpicture1, Text moneydisplay1,
								  ImageView workercaticon1, Text workercatlevel1, Text workercatcost1, ImageView cannonicon1, ImageView[] slotdisplay1,
								  Text catbasehealthdisplay1, Text enemybasehealthdisplay1) {
		startbackground1.setVisible(false);
		selectstart1.setVisible(false);
		selecthelp1.setVisible(false);
		backgroundpicture1.setVisible(true);
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
	
	public static double UpgradeWallet(double truemoney1, int workercat1, Text workercatlevel1, Text workercatcost1) {
		truemoney1 -= workercat1 * 560;
		workercat1++;
		workercatlevel1.setText("Level " + workercat1);
		
		if (workercat1 < 8) {
			workercatcost1.setText((workercat1 * 560) + "$");
		} else {
			workercatcost1.setText("MAX");
		}
		
		return truemoney1;
	}
	
	public static ImageView moveLR(ImageView backgroundpicture1, boolean leftkey1, boolean rightkey1) {
		
		if (leftkey1 && backgroundpicture1.getLayoutX() < 0) {
			backgroundpicture1.relocate(backgroundpicture1.getLayoutX() + 10, -380);
		} else if (rightkey1 && backgroundpicture1.getLayoutX() > -800) {
			backgroundpicture1.relocate(backgroundpicture1.getLayoutX() - 10, -380);
		}
		
		return backgroundpicture1;
	}
	
	public static FieldEnemies[] addEnemy(FieldEnemies[] enemyarray1, int numofenemies1, int spawninstructionsID1) {
		
		switch (spawninstructionsID1) {
			case 0: enemyarray1[numofenemies1] = new FieldEnemies(1, 125, 2400000, 2400000, 4, 0, 120, 32000, 120, 0, 355, 200, 150, 0, 0, 0.2667, 75, 0, 19750, 0, true, true, false, false, true);
			break;
			case 1: enemyarray1[numofenemies1] = new FieldEnemies(2, 0, 363000, 363000, 2, 0, 15, 2247, 15, 0, 186, 186, 0, 0, 0, 0.3333, 150, 0, 3575, 15, true, true, false, false, true);
			break;
			case 2: enemyarray1[numofenemies1] = new FieldEnemies(3, 0, 196000, 196000, 6, 0, 10, 11134, 10, 0, 83, 83, 0, 0, 0, 2.1333, 150, 0, 1975, 0, true, true, false, false, true);
			break;
			case 3: enemyarray1[numofenemies1] = new FieldEnemies(4, 0, 500, 500, 1, 0, 15, 200, 5, 0, 110, 110, 0, 0, 0, 1.3333, 150, 0, 296, 15, true, true, false, false, true);
			break;
		}
		
		return enemyarray1;
	}
	
	public static boolean isRechargeDone(DeploySlots[] rechargearray1, boolean firstrow1, int i1) {
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
	
	public static FieldCats[] addCat(FieldCats[] catarray1, boolean firstrow1, int catdeployed1, int numofcats1) {
		
		if (firstrow1) {
		
			switch (catdeployed1) {
				case 1: catarray1[numofcats1] = new FieldCats(1, 60, 6290, 6290, 3, 0, 25, 925, 25, 0, 60, 0, 0, 0.6667, 1375, 0, 510, false, false, false, false, true, false, false, true);
				break;
				case 2: catarray1[numofcats1] = new FieldCats(2, 0, 11900, 11900, 3, 0, 0, 3400, 0, 0, 150, 0, 0, 0.5333, 1375, 0, 1125, true, false, false, false, true, false, false, true);
				break;
				case 3: catarray1[numofcats1] = new FieldCats(3, 0, 8500, 8500, 5, 0, 0, 595, 0, 0, 140, 0, 0, 3.1333, 1375, 0, 825, true, false, false, false, true, false, false, true);
				break;
				case 4: catarray1[numofcats1] = new FieldCats(4, 0, 15300, 15300, 2, 0, 0, 2176, 0, 0, 160, 0, 0, 0.5333, 1375, 0, 630, true, false, false, false, true, false, false, true);
				break;
				case 5: catarray1[numofcats1] = new FieldCats(5, 0, 17850, 17850, 1, 0, 0, 1020, 0, 0, 120, 0, 0, 0.5333, 1375, 0, 315, false, false, true, false, true, false, false, true);
				break;
			}
			
		} else {
			
			switch (catdeployed1) {
				case 1: catarray1[numofcats1] = new FieldCats(6, 0, 9435, 9435, 4, 0, 0, 3400, 0, 0, 200, 0, 0, 1.4, 1375, 0, 525, false, true, false, false, true, false, false, true);
				break;
				case 2: catarray1[numofcats1] = new FieldCats(7, 0, 15300, 15300, 1, 0, 0, 25500, 0, 0, 140, 0, 0, 5, 1375, 0, 750, false, false, false, true, true, false, false, true);
				break;
				case 3: catarray1[numofcats1] = new FieldCats(8, 0, 26100, 26100, 5, 0, 0, 682, 0, 0, 140, 0, 0, 4.4, 1375, 0, 750, true, false, false, false, true, false, false, true);
				break;
				case 4: catarray1[numofcats1] = new FieldCats(9, 0, 15660, 15660, 4, 0, 0, 10497, 0, 0, 220, 0, 0, 0.6667, 1375, 0, 975, true, false, false, false, true, false, false, true);
				break;
				case 5: catarray1[numofcats1] = new FieldCats(10, 0, 25500, 25500, 6, 0, 0, 85000, 0, 0, 200, 0, 0, 4, 1375, 0, 4500, true, false, false, false, true, false, false, true);
				break;
			}
			
		}
		
		return catarray1;
	}
	
	public static FieldCats[] removeCat(FieldCats[] catarray1, int numofcats1, int i1) {
		
		for (int j1 = i1; j1 < numofcats1 - 1; j1++) {
			catarray1[j1] = catarray1[j1 + 1];
		}
		
		catarray1[numofcats1 - 1] = new FieldCats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, false, false, false);
		return catarray1;
	}
	
	public static FieldEnemies[] removeEnemy(FieldEnemies[] enemyarray1, int numofenemies1, int i1) {
		
		for (int j1 = i1; j1 < numofenemies1 - 1; j1++) {
			enemyarray1[j1] = enemyarray1[j1 + 1];
		}
		
		enemyarray1[numofenemies1 - 1] = new FieldEnemies(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false);
		return enemyarray1;
	}
	
	public static boolean StopMoving(double selfxpos, double selfbaseline, double selfrange, double otherxpos, double otherbaseline) {
		boolean allowmove1 = true;
		
		if (selfxpos + selfbaseline - selfrange < otherxpos + otherbaseline && allowmove1) {
			allowmove1 = false;
		}
		
		return allowmove1;
	}
	
	public static ImageView[] RenderCats(int unitID1, int unitanimation1, int unitbackswing1, int unitframedata1, ImageView[] slotarray1, int i1,
										 Image[] catpictures1, Image[] catpicturesanimation1, Image[] catpicturesattack1, Image[] catpicturesbackswing1) {
		
		if (unitID1 != 0) {
			
			if (unitframedata1 > unitanimation1 + 15) {
				slotarray1[i1].setImage(catpicturesbackswing1[unitID1 - 1]);
			} else if (unitframedata1 >= unitanimation1) {
				slotarray1[i1].setImage(catpicturesattack1[unitID1 - 1]);
			} else if (unitframedata1 != 0) {
				slotarray1[i1].setImage(catpicturesanimation1[unitID1 - 1]);
			} else {
				slotarray1[i1].setImage(catpictures1[unitID1 - 1]);
			}
			
			slotarray1[i1].setVisible(true);
		} else {
			slotarray1[i1].setVisible(false);
		}
		
		return slotarray1;
	}
	
	public static ImageView[] PositionCats(ImageView[] slotarray1, int i1, double unitxpos1, double unitypos1, ImageView backgroundpicture1,
										   Image[] catpictures1, int unitID1) {
		slotarray1[i1].relocate(unitxpos1 + backgroundpicture1.getLayoutX(), 275 - unitypos1 - catpictures1[unitID1 - 1].getHeight());
		return slotarray1;
	}
	
	public static ImageView[] RenderEnemies(int unitID1, int unitanimation1, int unitbackswing1, int unitframedata1, ImageView[] limitarray1, int i1,
										    Image[] enemypictures1, Image[] enemypicturesanimation1, Image[] enemypicturesattack1, Image[] enemypicturesbackswing1) {
		
		if (unitID1 != 0) {
			
			if (unitframedata1 > unitanimation1 + 15) {
				limitarray1[i1].setImage(enemypicturesbackswing1[unitID1 - 1]);
			} else if (unitframedata1 >= unitanimation1) {
				limitarray1[i1].setImage(enemypicturesattack1[unitID1 - 1]);
			} else if (unitframedata1 != 0) {
				limitarray1[i1].setImage(enemypicturesanimation1[unitID1 - 1]);
			} else {
				limitarray1[i1].setImage(enemypictures1[unitID1 - 1]);
			}
			
			limitarray1[i1].setVisible(true);
		} else {
			limitarray1[i1].setVisible(false);
		}
		
		return limitarray1;
	}
	
	public static ImageView[] PositionEnemies(ImageView[] limitarray1, int i1, double unitxpos1, double unitypos1, ImageView backgroundpicture1,
											  Image[] enemypictures1, int unitID1) {
		limitarray1[i1].relocate(unitxpos1 + backgroundpicture1.getLayoutX(), 275 - unitypos1 - enemypictures1[unitID1 - 1].getHeight());
		return limitarray1;
	}
	
	public static double IncomeGeneration(double truemoney1, int maxmoney1, int workercat1) {
		
		if (truemoney1 <= maxmoney1) {
			truemoney1 += ((workercat1 * 9.44286) + 175.007) / 6;
		}
		
		if (truemoney1 > maxmoney1) {
			truemoney1 = maxmoney1;
		}
		
		return truemoney1;
	}
	
	public static boolean ChangeWorkerCatIcon(boolean canaffordupgrade1, double truemoney1, int workercat1, ImageView workercaticon1, Image workercatactive1,
											  Image workercatidle1, Text workercatlevel1, Color active1, Color idle1) {
		
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
	
}