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
	int ID, maxhealth, health, knockbacks, knockbackcount, animation, damage, backswing, framedata, range, speed, xpos, price;
	boolean area, massivedamage, resistance, doublecash, idle, immunity, alive;
	
	FieldCats(int ID, int maxhealth, int health, int knockbacks, int knockbackcount, int animation, int damage, int backswing, int framedata, int range,
			  int speed, int xpos, int price, boolean area, boolean massivedamage, boolean resistance, boolean doublecash, boolean idle, boolean immunity,
			  boolean alive) {
		this.ID = ID;
		this.maxhealth = maxhealth;
		this.health = health;
		this.knockbacks = knockbacks;
		this.knockbackcount = knockbackcount;
		this.animation = animation;
		this.damage = damage;
		this.backswing = backswing;
		this.framedata = framedata;
		this.range = range;
		this.speed = speed;
		this.xpos = xpos;
		this.price = price;
		this.area = area;
		this.massivedamage = massivedamage;
		this.resistance = resistance;
		this.doublecash = doublecash;
		this.idle = idle;
		this.immunity = immunity;
		this.alive = alive;
	}
	
}

class FieldEnemies {
	int ID, maxhealth, health, knockbacks, knockbackcount, animation, damage, backswing, framedata, range, aggrorange, minrange, cannonframedata, pricedrop;
	double speed, xpos, ypos;
	boolean area, knockback, idle, immunity, cannonimmunity, alive;
	
	FieldEnemies(int ID, int maxhealth, int health, int knockbacks, int knockbackcount, int animation, int damage, int backswing, int framedata, int range,
				 int aggrorange, int minrange, int cannonframedata, double speed, double xpos, double ypos, int pricedrop, boolean area, boolean knockback,
				 boolean idle, boolean immunity, boolean cannonimmunity, boolean alive) {
		this.ID = ID;
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
		this.cannonframedata = cannonframedata;
		this.speed = speed;
		this.xpos = xpos;
		this.ypos = ypos;
		this.pricedrop = pricedrop;
		this.area = area;
		this.knockback = knockback;
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
 
	int i, j, k, catdeployed, deleteunit, unitID, money, success;
	int truewavexpos = -40;
	int numofcats, numofenemies, lowestunit, cannonrecharge, linenumber = 0;
	double unitxpos, unitypos, lowestxpos = 0;
	int maxmoney = 6000;
	int workercat = 1;
	int catbasehealth = 120000;
	int enemybasehealth = 750000;
	double truemoney, wavexpos, tempround1, tempround2;
	long globaltimer = 0;
	boolean inthelevel, leftkey, rightkey, allowmove, firstrow, canaffordupgrade, activecannon;
	
	FieldCats catarray[] = new FieldCats[50];
	FieldEnemies enemyarray[] = new FieldEnemies[12];
	DeploySlots rechargearray[] = new DeploySlots[10];
	EnemySchematics spawninstructions[] = new EnemySchematics[100];
	FieldCats temp1;
	FieldEnemies temp2;
	
	ImageView slotarray[] = new ImageView[50];
	Image catpictures[] = new Image[11];
	ImageView limitarray[] = new ImageView[12];
	Image enemypictures[] = new Image[4];
	Text textinstruct[] = new Text[5];
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
		catbasehealthdisplay.setLayoutY(100);
		catbasehealthdisplay.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		enemybasehealthdisplay.setText(Integer.toString(enemybasehealth) + "/750000");
		enemybasehealthdisplay.setLayoutX(800);
		enemybasehealthdisplay.setLayoutY(100);
		enemybasehealthdisplay.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		
		Group root = new Group(startbackground, selectstart, selecthelp, toMenu, backgroundpicture, moneydisplay, workercaticon, workercatlevel, workercatcost,
							   cannonicon, catcannonwave, cannonlaser, catbasehealthdisplay, enemybasehealthdisplay);
		Scene scene = new Scene(root, 800, 380);
		
		for (i = 0; i < 50; i++) {
			catarray[i] = new FieldCats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, false, false);
		}
		
		for (i = 0; i < 12; i++) {
			enemyarray[i] = new FieldEnemies(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, false);
		}
		
		for (i = 0; i < 50; i++) {
			slotarray[i] = new ImageView();
			slotarray[i].setVisible(false);
			root.getChildren().add(slotarray[i]);
		}
		
		for (i = 0; i < 12; i++) {
			limitarray[i] = new ImageView();
			limitarray[i].setPreserveRatio(true);
			limitarray[i].setVisible(false);
			root.getChildren().add(limitarray[i]);
		}
		
		for (i = 1; i < 2; i++) {
			catpictures[i - 1] = new Image("Unit_ID_" + i + ".png");
		}
		
		for (i = 0; i < 10; i++) {
			rechargearray[i] = new DeploySlots(0, 0, true);
		}
		
		for (i = 1; i < 5; i++) {
			enemypictures[i - 1] = new Image("Enemy_ID_" + i + ".png");
		}
		
		for (i = 1; i < 12; i++) {
			slotpictures[i - 1] = new Image("Slot_Image_" + i + ".png");
		}
		
		for (i = 1; i < 12; i++) {
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
				
				spawninstructions[i] = new EnemySchematics(readID, readspawncount, readfirstspawn, readnextspawnslower, readnextspawnsupper, 0, 0);
			} //while
			
		} catch (IOException exc) {
			System.out.println("Read error.");
			return;
		}
		
	//Image action handling
		selecthelp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				Instructions(startbackground, selectstart, selecthelp, toMenu, textinstruct);
				root.getChildren().addAll(textinstruct[0], textinstruct[1], textinstruct[2], textinstruct[3], textinstruct[4]);
				event.consume();
			}
			
		});
		
		selectstart.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
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
							
							if (workercat < 8) {
							
								if (truemoney >= workercat * 560) {
									truemoney = UpgradeWallet(truemoney, workercat, workercatlevel, workercatcost);
									workercat++;
									maxmoney += 1500;
								}
								
							}
							
							break;
						case ENTER:
							
							if (cannonrecharge == 3000) {
								cannonicon.setImage(catcannonicons[0]);
								cannonrecharge = 0;
								activecannon = true;
								cannonlaser.setVisible(true);
								catcannonwave.setVisible(true);
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
						
							if (firstrow) {
								firstrow = false;
								
								for (i = 0; i < 5; i++) {
									slotdisplay[i].setLayoutX(110 + (i * 110));
									slotdisplay[i].setLayoutY(300);
								}
								
								for (i = 5; i < 10; i++) {
									slotdisplay[i].setLayoutX(130 + ((i - 5) * 110));
									slotdisplay[i].setLayoutY(320);
									slotdisplay[i].toFront();
								}
								
								for (i = 5; i < 10; i++) {
									rechargerects[i].toFront();
								}
								
							} else {
								firstrow = true;
								
								for (i = 0; i < 5; i++) {
									slotdisplay[i].setLayoutX(130 + (i * 110));
									slotdisplay[i].setLayoutY(320);
									slotdisplay[i].toFront();
								}
								
								for (i = 5; i < 10; i++) {
									slotdisplay[i].setLayoutX(110 + ((i - 5) * 110));
									slotdisplay[i].setLayoutY(300);
								}
								
								for (i = 0; i < 5; i++) {
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
								catarray[numofcats] = new FieldCats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, false, false);
							}
							
						}
						
					}
					
				}
				
			}
			
		});
		
		AnimationTimer timer = new AnimationTimer() {
            @Override
			
            public void handle(long now) {
				
				if (inthelevel) {
					backgroundpicture = moveLR(backgroundpicture, leftkey, rightkey);
					globaltimer++;
					
					if (cannonrecharge < 3000) {
						cannonrecharge = 3000;
					}
					
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
					
					for (i = 0; i < numofcats; i++) {
						
						if (catarray[i].health < 0) {
							catarray[i].alive = false;
						}
						
						if (catarray[i].idle && catarray[i].alive) {
							allowmove = true;
							
							for (j = 0; j < numofenemies; j++) {
								allowmove = StopMoving(catarray[i].xpos, catarray[i].range, enemyarray[j].xpos, allowmove);
							}
							
							if (allowmove) {
								catarray[i].xpos -= catarray[i].speed;
							} else {
								catarray[i].idle = false;
							}
							
						} else if (catarray[i].alive) {
							catarray[i].framedata++;
							
							if (catarray[i].framedata == catarray[i].animation) {
								
								if (catarray[i].area) {
								
									for (j = 0; j < numofenemies; i++) {
										
										if (catarray[i].xpos - catarray[i].range < enemyarray[j].xpos && !enemyarray[j].immunity) {
											
											if (catarray[i].area) {	
												enemyarray[j].health -= catarray[i].damage;
											} else if (enemyarray[j].xpos > lowestxpos) {
												lowestxpos = enemyarray[j].xpos;
												lowestunit = j;
												
											}
												
											if (enemyarray[j].health <= 0) {
												enemyarray[j].alive = false;
											} else for (k = enemyarray[j].knockbacks - enemyarray[j].knockbackcount; k > 0; k--) {
												
												if (enemyarray[j].health < k * ((double) enemyarray[j].maxhealth / (double) enemyarray[j].knockbacks)) {
													enemyarray[j].immunity = true;
													enemyarray[j].knockbackcount++;
												}
												
											}
										
										}
										
									}
									
								} else {
									
									for (j = 0; j < numofenemies; j++) {
										
										if (catarray[i].xpos - catarray[i].range < enemyarray[j].xpos && catarray[i].xpos > enemyarray[j].xpos && !enemyarray[j].immunity) {
											
										}
										
									}
									
								}
								
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
						
						if (enemyarray[i].health < 0) {
							enemyarray[i].alive = false;
						}
						
						if (!enemyarray[i].alive) {
							enemyarray = removeEnemy(enemyarray, numofenemies, i);
							numofenemies--;
							break;
						}
						
						if (activecannon && ((enemyarray[i].xpos > 1200 - (wavexpos * 50) && enemyarray[i].xpos < 1300 - (wavexpos * 50)) ||
						    enemyarray[i].cannonimmunity) && (!enemyarray[i].cannonimmunity || enemyarray[i].cannonframedata != 0)) {
							enemyarray[i].cannonimmunity = true;
							enemyarray[i].cannonframedata++;
							
							if (enemyarray[i].cannonframedata > 14) {
								enemyarray[i].cannonimmunity = false;
								enemyarray[i].cannonframedata = 0;
								enemyarray[i].ypos = 0;
								enemyarray[i].health -= 6000;
							} else {
								enemyarray[i].xpos -= enemyarray[i].speed + 1;
								tempround1 = -0.267 * Math.pow(enemyarray[i].cannonframedata, 2);
								tempround2 = 4 * enemyarray[i].cannonframedata;
								enemyarray[i].ypos = tempround1 + tempround2;
							}
							
						}
						
						if (enemyarray[i].idle && enemyarray[i].alive) {
							allowmove = true;
							
							for (j = 0; j < numofcats; j++) {
								
								if (enemyarray[i].xpos + enemyarray[i].aggrorange > catarray[j].xpos && enemyarray[i].xpos < catarray[j].xpos && allowmove) {
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
									
									if (enemyarray[i].xpos + enemyarray[i].range > catarray[j].xpos && enemyarray[i].xpos + enemyarray[i].minrange < catarray[j].xpos && !catarray[j].immunity) {
										catarray[j].health -= enemyarray[i].damage;
										
										if (catarray[j].health <= 0) {
											catarray = removeCat(catarray, numofcats, j);
											numofcats--;
										} else for (k = catarray[j].knockbacks - catarray[j].knockbackcount; k > 0; k--) {
											
											if (catarray[j].health < k * ((double) catarray[j].maxhealth / (double) catarray[j].knockbacks)) {
												catarray[j].immunity = true;
												catarray[j].knockbackcount++;
											}
											
										}
										
									}
									
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
						slotarray = RenderCats(unitID, slotarray, i, catpictures);
					}
					
					for (i = 0; i < numofcats; i++) {
						unitxpos = catarray[i].xpos;
						unitID = catarray[i].ID;
						slotarray = PositionCats(slotarray, i, unitxpos, backgroundpicture, catpictures, unitID);
					}
					
					for (i = 0; i < 12; i++) {
						unitID = enemyarray[i].ID;
						limitarray = RenderEnemies(unitID, limitarray, i, enemypictures);
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
					catbasehealthdisplay.setLayoutX(backgroundpicture.getLayoutX() + 1200);
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
	
	public static void Instructions(ImageView startbackground1, ImageView selectstart1, ImageView selecthelp1, ImageView toMenu1, Text textinstruct1[]) {
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
			backgroundpicture1.setLayoutX(backgroundpicture1.getLayoutX() + 10);
		} else if (rightkey1 && backgroundpicture1.getLayoutX() > -800) {
			backgroundpicture1.setLayoutX(backgroundpicture1.getLayoutX() - 10);
		}
		
		return backgroundpicture1;
	}
	
	public static FieldEnemies[] addEnemy(FieldEnemies[] enemyarray1, int numofenemies1, int spawninstructionsID1) {
		
		switch (spawninstructionsID1) {
			case 0: enemyarray1[numofenemies1] = new FieldEnemies(1, 2400000, 2400000, 4, 0, 120, 32000, 120, 0, 600, 300, 200, 0, 0.4, 0, 0, 19750, true, false, true, false, false, true);
			break;
			case 1: enemyarray1[numofenemies1] = new FieldEnemies(2, 363000, 363000, 2, 0, 15, 2247, 15, 0, 186, 186, 0, 0, 0.5, 0, 0, 3575, true, true, true, false, false, true);
			break;
			case 2: enemyarray1[numofenemies1] = new FieldEnemies(3, 196000, 196000, 6, 0, 10, 11134, 10, 0, 83, 83, 0, 0, 3.2, 0, 0, 1975, true, false, true, false, false, true);
			break;
			case 3: enemyarray1[numofenemies1] = new FieldEnemies(4, 500, 500, 1, 0, 15, 200, 5, 0, 110, 110, 0, 0, 2, 0, 0, 296, true, true, true, false, false, true);
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
				case 1: catarray1[numofcats1] = new FieldCats(1, 6290, 6290, 3, 0, 0, 925, 0, 0, 125, 1, 1375, 510, false, false, false, false, true, false, true);
				break;
				case 2: catarray1[numofcats1] = new FieldCats(2, 11900, 11900, 3, 0, 0, 3400, 0, 0, 150, 8, 1375, 1125, true, false, false, false, true, false, true);
				break;
				case 3: catarray1[numofcats1] = new FieldCats(3, 8500, 8500, 5, 0, 0, 595, 0, 0, 140, 47, 1375, 825, true, false, false, false, true, false, true);
				break;
				case 4: catarray1[numofcats1] = new FieldCats(4, 15300, 15300, 2, 0, 0, 2176, 0, 0, 160, 8, 1375, 630, true, false, false, false, true, false, true);
				break;
				case 5: catarray1[numofcats1] = new FieldCats(5, 17850, 17850, 1, 0, 0, 1020, 0, 0, 120, 8, 1375, 315, false, false, true, false, true, false, true);
				break;
			}
			
		} else {
			
			switch (catdeployed1) {
				case 1: catarray1[numofcats1] = new FieldCats(6, 9435, 9435, 4, 0, 0, 3400, 0, 0, 200, 21, 1375, 525, false, true, false, false, true, false, true);
				break;
				case 2: catarray1[numofcats1] = new FieldCats(7, 15300, 15300, 1, 0, 0, 25500, 0, 0, 140, 75, 1375, 750, false, false, false, true, true, false, true);
				break;
				case 3: catarray1[numofcats1] = new FieldCats(8, 26100, 26100, 5, 0, 0, 682, 0, 0, 140, 66, 1375, 750, true, false, false, false, true, false, true);
				break;
				case 4: catarray1[numofcats1] = new FieldCats(9, 15660, 15660, 4, 0, 0, 10497, 0, 0, 220, 10, 1375, 975, true, false, false, false, true, false, true);
				break;
				case 5: catarray1[numofcats1] = new FieldCats(10, 25500, 25500, 6, 0, 0, 85000, 0, 0, 200, 60, 1375, 4500, true, false, false, false, true, false, true);
				break;
			}
			
		}
		
		return catarray1;
	}
	
	public static FieldCats[] removeCat(FieldCats[] catarray1, int numofcats1, int i1) {
		
		for (int j1 = i1; j1 < numofcats1 - 1; j1++) {
			catarray1[j1] = catarray1[j1 + 1];
		}
		
		catarray1[numofcats1 - 1] = new FieldCats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, false, false);
		return catarray1;
	}
	
	public static FieldEnemies[] removeEnemy(FieldEnemies[] enemyarray1, int numofenemies1, int i1) {
		
		for (int j1 = i1; j1 < numofenemies1 - 1; j1++) {
			enemyarray1[j1] = enemyarray1[j1 + 1];
		}
		
		enemyarray1[numofenemies1 - 1] = new FieldEnemies(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, false);
		return enemyarray1;
	}
	
	public static boolean StopMoving(int selfxpos, int selfrange, double otherxpos, boolean allowmove1) {
		
		if (selfxpos - selfrange < otherxpos && allowmove1) {
			allowmove1 = false;
		}
		
		return allowmove1;
	}
	
	public static ImageView[] RenderCats(int unitID1, ImageView[] slotarray1, int i1, Image[] catpictures1) {
		
		if (unitID1 != 0) {
			slotarray1[i1].setImage(catpictures1[unitID1 - 1]);
			slotarray1[i1].setVisible(true);
		} else {
			slotarray1[i1].setVisible(false);
		}
		
		return slotarray1;
	}
	
	public static ImageView[] PositionCats(ImageView[] slotarray1, int i1, double unitxpos1, ImageView backgroundpicture1, Image[] catpictures1, int unitID1) {
		slotarray1[i1].setLayoutX(unitxpos1 + backgroundpicture1.getLayoutX());
		slotarray1[i1].setLayoutY(275 - catpictures1[unitID1 - 1].getHeight());
		
		return slotarray1;
	}
	
	public static ImageView[] RenderEnemies(int unitID1, ImageView[] limitarray1, int i1, Image[] enemypictures1) {
		
		if (unitID1 != 0) {
			limitarray1[i1].setImage(enemypictures1[unitID1 - 1]);
			limitarray1[i1].setVisible(true);
		} else {
			limitarray1[i1].setVisible(false);
		}
		
		return limitarray1;
	}
	
	public static ImageView[] PositionEnemies(ImageView[] limitarray1, int i1, double unitxpos1, double unitypos1, ImageView backgroundpicture1, Image[] enemypictures1, int unitID1) {
		limitarray1[i1].setLayoutX(150 + unitxpos1 + backgroundpicture1.getLayoutX() - enemypictures1[unitID1 - 1].getWidth());
		limitarray1[i1].setLayoutY(275 - unitypos1 - enemypictures1[unitID1 - 1].getHeight());
		
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