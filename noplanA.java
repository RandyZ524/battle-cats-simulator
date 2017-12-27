import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;
//Importing my stuff

public class noplanA extends Application {
	
 public static void main(String[] args) {
	Application.launch(args); //Launches application
 }

//Full variable initialization
	
	static int STAGE_WIDTH = 800;
	static int STAGE_HEIGHT = 380;
	static int MOVE_DISPLACEMENT = 10;
	static int VICTORY_MOVE_DISPLACEMENT = 40;
	static int MAX_NUM_OF_CATS = 50;
	static int NUM_OF_CAT_IDS = 11;
	static int NUM_OF_SLOTS = 10;
	static int NUM_OF_SLOTS_IN_ROW = 5;
	static int MAX_NUM_OF_ENEMIES = 12;
	static int DELIVERY_SLOT = 5;
	static int JIZO_SLOT = 10;
	static int WORKER_CAT_COST_INCREASE = 560;
	static double WORKER_CAT_INCOME_BASE = 175.007;
	static double WORKER_CAT_INCOME_INCREASE = 9.44286;
	static int WORKER_CAT_WALLET_BASE = 6000;
	static int WORKER_CAT_WALLET_INCREASE = 1500;
	static int MAX_WORKER_CAT_LEVEL = 8;
	static int MAX_DAMAGE_FRAMES = 60;
	static int MAX_PROC_FRAMES = 30;
	static int MAX_CANNON_FRAMES = 14;
	static int MAX_CANNON_RECHARGE = 3000;
	static int CANNON_DAMAGE = 6000;
	static int DROP_RATE = 30;
	static int CLIONEL_MAX_HEALTH = 2400000;
	static int NUM_OF_CANNON_STATES = 5;
	static int NUM_OF_TEXT_LINES = 5;
	//static constants
	
	int i, j, k, catdeployed, money;
	int truewavexpos = -40;
	int numofcats, numofenemies, cannonrecharge, linenumber = 0;
	int maxmoney = WORKER_CAT_WALLET_BASE;
	int workercat = 1;
	int catbasehealth = 120000;
	int enemybasehealth = 750000;
	double truemoney, wavexpos, tempround1, tempround2, tempround3, tempround4 = 0;
	long globaltimer, delaytimer = 0;
	boolean inthelevel, leftkey, rightkey, allowmove, firstrow, canaffordupgrade, activecannon;
	boolean godmode, winner, loser, paused, stagecomplete, getcatway, firsttimewinner, tofirsttimereward, tonextbox = false;
	//primitives
	
	FieldCats catarray[] = new FieldCats[MAX_NUM_OF_CATS];
	FieldEnemies enemyarray[] = new FieldEnemies[MAX_NUM_OF_ENEMIES];
	DeploySlots rechargearray[] = new DeploySlots[NUM_OF_SLOTS];
	EnemySchematics spawninstructions[] = new EnemySchematics[100];
	ArrayList<DeathSpirit> deathspirits = new ArrayList<DeathSpirit>();
	//object arrays
	
	Text textinstruct[] = new Text[NUM_OF_TEXT_LINES];
	Image slotpictures[] = new Image[NUM_OF_CAT_IDS];
	Image slotpicturesidle[] = new Image[NUM_OF_CAT_IDS];
	ImageView slotdisplay[] = new ImageView[NUM_OF_SLOTS];
	Image catcannonicons[] = new Image[NUM_OF_CANNON_STATES];
	Rectangle rechargerects[] = new Rectangle[NUM_OF_SLOTS];
	//node arrays
	
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
	//nodes (minus text)
	
	Text moneydisplay = new Text(0, 24, "0/" + WORKER_CAT_WALLET_BASE + "$");
	Text workercatlevel = new Text(2, 310, "Level 1");
	Text workercatcost = new Text(2, 375, WORKER_CAT_COST_INCREASE + "$");
	Text catbasehealthdisplay = new Text(STAGE_WIDTH, 105, catbasehealth + "/120000");
	Text enemybasehealthdisplay = new Text(STAGE_WIDTH, 105, enemybasehealth + "/750000");
	Text bosspercentage = new Text(425, 323, "100%");
	Text victorytext = new Text(280, 120, "Victory!!!");
	Text catwaytext = new Text(190, 320, "You can't get it from the Upgrades screen.");
	Text firstcatfoodtext = new Text(376, 296, "30 Cat Food!!");
	Text firstenergytext = new Text(413, 321, "fully recovered!!");
	//texts
	
	Color idle = Color.rgb(195, 195, 195);
	Color active = Color.rgb(255, 242, 0);
	Color purpleflash = Color.rgb(239, 60, 200);
	Color yellowflash = Color.rgb(243, 228, 51);
	Color bluerecharge = Color.rgb(81, 255, 220);
	Color purplelaser = Color.rgb(255, 174, 201);
	//colors
	
	byte inbuf[] = new byte[20500];
	//file readers
	
	Random random = new Random();
	//random number
	
//Full variable initialization end
	
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("A Battle Cats simulator");
		
		//Saving image files to Image nodes
		Image logo = new Image("Battle_Cats_Logo.png");
		Image startbutton = new Image("Start_Button.png");
		Image helpbutton = new Image("Help_Button.png");
		Image whitescreen = new Image("White_Screen.png");
		Image exithelpbutton = new Image("Exit_Help_Button.png");
		Image background = new Image("Background_Picture.png");
		Image workercatidle = new Image("Worker_Cat_Idle.png");
		Image workercatactive = new Image("Worker_Cat_Active.png");
		Image wave = new Image("Cat_Cannon_Wave.png");
		Image pause = new Image("Pause_Menu.png");
		Image pausehelpbutton = new Image("Pause_Help_Button.png");
		Image catway = new Image("Unlocked_Catway_Box.png");
		Image firsttime = new Image("First_Time_Box.png");
		Image ok = new Image("Ok_Button.png");
		
	//Defining parameters for individual nodes
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
		backgroundpicture.setLayoutX(-STAGE_WIDTH);
		backgroundpicture.setLayoutY(-STAGE_HEIGHT);
		backgroundpicture.setVisible(false);
		
		workercaticon.setImage(workercatidle);
		workercaticon.setLayoutX(0);
		workercaticon.setLayoutY(295);
		workercaticon.setVisible(false);
		
		moneydisplay.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
		moneydisplay.setStroke(Color.BLACK);
		moneydisplay.setFill(active);
		moneydisplay.setVisible(false);
		
		workercatlevel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		workercatlevel.setStroke(Color.BLACK);
		workercatlevel.setFill(idle);
		workercatlevel.setVisible(false);
		
		workercatcost.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		workercatcost.setStroke(Color.BLACK);
		workercatcost.setFill(active);
		workercatcost.setVisible(false);
		
		cannonicon.setImage(catcannonicons[0]);
		cannonicon.setLayoutX(700);
		cannonicon.setLayoutY(300);
		cannonicon.setVisible(false);
		
		catcannonwave.setImage(wave);
		catcannonwave.setLayoutX(STAGE_WIDTH);
		catcannonwave.setLayoutY(220);
		catcannonwave.setVisible(false);
		
		cannonlaser.setStroke(purplelaser);
		cannonlaser.setStrokeWidth(5);
		
		catbasehealthdisplay.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		catbasehealthdisplay.setStroke(Color.BLACK);
		catbasehealthdisplay.setFill(Color.WHITE);
		
		enemybasehealthdisplay.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		enemybasehealthdisplay.setStroke(Color.BLACK);
		enemybasehealthdisplay.setFill(Color.WHITE);
		
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
		
		victorytext.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		victorytext.setStroke(Color.BLACK);
		victorytext.setStrokeWidth(3);
		victorytext.setFill(Color.WHITE);
		victorytext.setVisible(false);
		
		catwayunlock.setImage(catway);
		catwayunlock.setLayoutX(141);
		catwayunlock.setLayoutY(230);
		catwayunlock.setVisible(false);
		
		catwaytext.setFont(Font.font("Verdana", 20));
		catwaytext.setVisible(false);
		
		firsttimereward.setImage(firsttime);
		firsttimereward.setLayoutX(141);
		firsttimereward.setLayoutY(230);
		firsttimereward.setVisible(false);
		
		firstcatfoodtext.setFont(Font.font("Verdana", 20));
		firstcatfoodtext.setVisible(false);
		
		firstenergytext.setFont(Font.font("Verdana", 20));
		firstenergytext.setVisible(false);
		
		levelfinish.setImage(ok);
		levelfinish.setLayoutX(253);
		levelfinish.setLayoutY(300);
		levelfinish.setVisible(false);
		
	//Defining parameters for individual nodes end
		
		Group root = new Group(startbackground, selectstart, selecthelp, whitebackground, toMenu, backgroundpicture, moneydisplay, workercaticon, workercatlevel, workercatcost, cannonicon, catcannonwave, cannonlaser, catbasehealthdisplay, enemybasehealthdisplay, bosspercentage, pausemenu, selectpausehelp, victorytext, catwayunlock, catwaytext, firsttimereward, firstcatfoodtext, firstenergytext, levelfinish);
		Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
		
	//Filling arrays used in application
		
		for (i = 0; i < MAX_NUM_OF_CATS; i++) {
			catarray[i] = new FieldCats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, false, false, false, new Image("Units/Unit_0/0.png"), new Image("Units/Unit_0/0_Animation.png"), new Image("Units/Unit_0/0_Attack.png"), new Image("Units/Unit_0/0_Backswing.png"), new ImageView());
			root.getChildren().add(catarray[i].fieldimage);
		}
		
		for (i = 0; i < MAX_NUM_OF_ENEMIES; i++) {
			enemyarray[i] = new FieldEnemies(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, new Image("Enemies/Enemy_0/0.png"), new Image("Enemies/Enemy_0/0_Animation.png"), new Image("Enemies/Enemy_0/0_Attack.png"), new Image("Enemies/Enemy_0/0_Backswing.png"), new ImageView());
			root.getChildren().add(enemyarray[i].fieldimage);
		}
		
		for (i = 0; i < NUM_OF_SLOTS; i++) {
			rechargearray[i] = new DeploySlots(0, 0, true);
			rechargearray[i].assignRechargeTimes(i);
			
			rechargerects[i] = new Rectangle(132 + ((i % NUM_OF_SLOTS_IN_ROW) * 110), 356, 0, 10);
			rechargerects[i].setFill(bluerecharge);
			rechargerects[i].setVisible(false);
			root.getChildren().add(rechargerects[i]);
		}
		
		for (i = 1; i <= NUM_OF_CAT_IDS; i++) {
			slotpictures[i - 1] = new Image("Slot_Image_" + i + ".png");
			slotpicturesidle[i - 1] = new Image("Slot_Image_" + i + "_Idle.png");
		}
		
		for (i = NUM_OF_SLOTS_IN_ROW; i < NUM_OF_SLOTS; i++) {
			slotdisplay[i] = new ImageView();
			slotdisplay[i].setImage(slotpictures[i]);
			slotdisplay[i].setVisible(false);
			slotdisplay[i].setLayoutX(110 + ((i - NUM_OF_SLOTS_IN_ROW) * 110));
			slotdisplay[i].setLayoutY(300);
			root.getChildren().add(slotdisplay[i]);
		}
		
		for (i = 0; i < NUM_OF_SLOTS_IN_ROW; i++) {
			slotdisplay[i] = new ImageView();
			slotdisplay[i].setImage(slotpictures[i]);
			slotdisplay[i].setVisible(false);
			slotdisplay[i].setLayoutX(130 + (i * 110));
			slotdisplay[i].setLayoutY(320);
			root.getChildren().add(slotdisplay[i]);
		}
		
		for (i = 1; i <= NUM_OF_CANNON_STATES; i++)
			catcannonicons[i - 1] = new Image("Cat_Cannons/Icon_" + i + ".png");
		
	//Filling arrays used in application end
		
		LineNumberReader lnr = new LineNumberReader(new FileReader(new File("Enemy_Spawn_Schematics.txt")));
		lnr.skip(Long.MAX_VALUE);
		linenumber = lnr.getLineNumber();
		//Getting the number of lines to simplify file reader and spawn instructions
		
		int readID, readspawncount, readfirstspawn, readnextspawnslower = 0, readnextspawnsupper = 0;
		
		//File reader that pulls the level schematics information from the text file "Enemy_Spawn_Schematics.txt"
		try (DataInputStream dataIn = new DataInputStream(new FileInputStream("Enemy_Spawn_Schematics.txt"))) {
			dataIn.read(inbuf);
			String str = new String(inbuf);
			String[] lineschematics = str.split("\n");
			
			for (i = 0; i < linenumber; i++) {
				String[] schematic = lineschematics[i].split(",");
				readID = Integer.parseInt(schematic[0].trim());
				readspawncount = Integer.parseInt(schematic[1].trim());
				readfirstspawn = Integer.parseInt(schematic[2].trim());
				
				if (schematic.length == 5) {
					readnextspawnslower = Integer.parseInt(schematic[3].trim());
					readnextspawnsupper = Integer.parseInt(schematic[4].trim());
				}
				
				spawninstructions[i] = new EnemySchematics(readID, readspawncount, readfirstspawn, readnextspawnslower, readnextspawnsupper, 0, 0, false);
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
			}
			
		});
		
		//action for clicking on the start button
		selectstart.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
				if (godmode) {
					slotpictures[DELIVERY_SLOT] = slotpictures[JIZO_SLOT];
					slotpicturesidle[DELIVERY_SLOT] = slotpicturesidle[JIZO_SLOT];
					slotdisplay[DELIVERY_SLOT].setImage(slotpictures[DELIVERY_SLOT]);
				}
				
				setupLevel(startbackground, selectstart, selecthelp, backgroundpicture, moneydisplay, workercaticon, workercatlevel, workercatcost, cannonicon, slotdisplay, catbasehealthdisplay, enemybasehealthdisplay);
				inthelevel = true;
				firstrow = true;
				canaffordupgrade = false;
				activecannon = false;
			}
			
		});
		
		selectpausehelp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
				if (paused) {
					Instructions(whitebackground, toMenu, textinstruct, paused);
					root.getChildren().addAll(textinstruct[0], textinstruct[1], textinstruct[2], textinstruct[3], textinstruct[4]);
				}
				
			}
			
		});
		
		backgroundpicture.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
				if (loser) {
					backgroundpicture.setImage(background);
					backgroundpicture.relocate(-STAGE_WIDTH, -STAGE_HEIGHT);
					backgroundpicture.setVisible(false);
					moneydisplay.setVisible(false);
					workercaticon.setVisible(false);
					workercatlevel.setVisible(false);
					workercatcost.setVisible(false);
					cannonicon.setVisible(false);
					catbasehealthdisplay.setVisible(false);
					enemybasehealthdisplay.setVisible(false);
					bosspercentage.setVisible(false);
					catcannonwave.setVisible(false);
					
					for (i = 0; i < NUM_OF_SLOTS; i++) {
						slotdisplay[i].setVisible(false);
						rechargerects[i].setVisible(false);
						rechargearray[i].lastdeployed = 0;
					}
					
					for (FieldCats cat : catarray) {
						cat.reset();
						cat.fieldimage.setVisible(false);
					}
					
					for (FieldEnemies enemy : enemyarray) {
						enemy.reset();
						enemy.fieldimage.setVisible(false);
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
				
			}
			
		});
		
		catwayunlock.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
				if (delaytimer >= 90) {
					catwayunlock.setVisible(false);
					catwaytext.setVisible(false);
					
					delaytimer = 105;
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
					
					delaytimer = 105;
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
					
					delaytimer = 45;
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
					
					delaytimer = 45;
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
					
					delaytimer = 45;
					tonextbox = true;
				}
				
			}
			
		});
		
		levelfinish.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				backgroundpicture.relocate(-STAGE_WIDTH, -STAGE_HEIGHT);
				backgroundpicture.setVisible(false);
				victorytext.setVisible(false);
				levelfinish.setVisible(false);
				
				for (DeploySlots slot : rechargearray)
					slot.lastdeployed = 0;
				
				for (FieldCats cat : catarray) {
					cat.reset();
					cat.fieldimage.setVisible(false);
				}
				
				startbackground.setVisible(true);
				selectstart.setVisible(true);
				selecthelp.setVisible(true);
				
				inthelevel = false;
				winner = false;
				firsttimewinner = false;
				
				numofcats = 0;
				numofenemies = 0;
				catbasehealth = 120000;
				enemybasehealth = 750000;
				workercat = 1;
				truemoney = 0;
				cannonrecharge = 0;
				globaltimer = 0;
				delaytimer = 0;
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
						//Moves the screen left and right
						case A:
							leftkey = true;
							break;
						case D:
							rightkey = true;
							break;
						//Upgrades the worker cat when possible
						case UP:
							
							if (workercat < MAX_WORKER_CAT_LEVEL && truemoney >= workercat * WORKER_CAT_COST_INCREASE) {
								truemoney = UpgradeWallet(truemoney, workercat, workercatlevel, workercatcost);
								workercat++;
								maxmoney += WORKER_CAT_WALLET_INCREASE;
							}
							
							break;
						//Fires the cat cannon when possible
						case ENTER:
							
							if (cannonrecharge == MAX_CANNON_RECHARGE) {
								cannonicon.setImage(catcannonicons[0]);
								cannonrecharge = 0;
								activecannon = true;
								cannonlaser.setVisible(true);
								catcannonwave.setVisible(true);
							}
							
							break;
					}
				
				}
				
				if (inthelevel && !winner) {
					
					switch (event.getCode()) {
						//Pauses/unpauses the game when in the level
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
						//Enables Jizo mode
						case G:
							
							if (!godmode)
								godmode = true;
							
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
						//Stops moving left or right
						case A:
							leftkey = false;
							break;
						case D:
							rightkey = false;
							break;
						//Switches cat deploy rows
						case TAB:
							firstrow = !firstrow;
						
							if (firstrow) {
								
								for (i = NUM_OF_SLOTS_IN_ROW; i < NUM_OF_SLOTS; i++)
									slotdisplay[i].relocate(110 + ((i - NUM_OF_SLOTS_IN_ROW) * 110), 300);
								for (i = 0; i < NUM_OF_SLOTS_IN_ROW; i++) {
									slotdisplay[i].relocate(130 + (i * 110), 320);
									slotdisplay[i].toFront();
									
									rechargerects[i].toFront();
								}
								
							} else {
								
								for (i = 0; i < NUM_OF_SLOTS_IN_ROW; i++)
									slotdisplay[i].relocate(110 + (i * 110), 300);
								for (i = NUM_OF_SLOTS_IN_ROW; i < NUM_OF_SLOTS; i++) {
									slotdisplay[i].relocate(130 + ((i - NUM_OF_SLOTS_IN_ROW) * 110), 320);
									slotdisplay[i].toFront();
									
									rechargerects[i].toFront();
								}
								
							}
							
							break;
					}
					
					//Checks if cat can be added to deployed units when 1-5 is pressed, and nullifies input if not enough money or room
					try {
						catdeployed = Integer.parseInt(event.getText());
						
						if (catdeployed >= 0 && catdeployed <= 5 && numofcats < MAX_NUM_OF_CATS) {
							catdeployed += firstrow ? 0 : 5;
							
							if (rechargearray[catdeployed - 1].isRechargeDone()) {
								int index;
								
								for (index = 0; index < MAX_NUM_OF_CATS; index++) {
									
									if (catarray[index].ID == 0) {
										catarray[index].setAttributes(catdeployed, firstrow);
										break;
									}
									
								}
								
								if (catarray[index].price < truemoney) {
									truemoney -= catarray[index].price;
									numofcats++;	
									rechargearray[catdeployed - 1].lastdeployed = System.currentTimeMillis();
									rechargearray[catdeployed - 1].ready = false;
									slotdisplay[catdeployed - 1].setImage(slotpicturesidle[catdeployed - 1]);
									rechargerects[catdeployed - 1].setVisible(true);
								} else
									catarray[index].reset();
								
							}
							
						}
						
					} catch (NumberFormatException nfe) {
					}
					
				}
				
			}
			
		});
		
	//Keyboard action handling end
		enemyarray[0].setAttributes(0);
		numofenemies++;
		AnimationTimer timer = new AnimationTimer() {
            @Override
			
            public void handle(long now) {
				
				if (inthelevel && !loser && !paused) {
					moveLR(backgroundpicture, leftkey, rightkey);
					globaltimer++;
					
					if (cannonrecharge < MAX_CANNON_RECHARGE)
						cannonrecharge = MAX_CANNON_RECHARGE;
					
					//spawning enemies schematics
					if (!winner) {
					
						for (EnemySchematics spawner : spawninstructions) {
							
							if (spawner != null) {
								
								if (globaltimer >= spawner.firstspawn && !spawner.onespawn && numofenemies < MAX_NUM_OF_ENEMIES) {
							
									for (int index = 0; index < MAX_NUM_OF_ENEMIES; index++) {
										
										if (enemyarray[index].ID == 0) {
											enemyarray[index].setAttributes(spawner.ID);
											numofenemies++;
											spawner.onespawn = true;
											break;
										}
										
									}
									
									if (spawner.spawns != -1)
										spawner.spawns--;
									
								}
								
								if (spawner.spawns != 0 && spawner.onespawn) {
									
									if (spawner.nextspawnrandom == 0)
										spawner.nextspawnrandom = random.nextInt(spawner.nextspawnsupper - spawner.nextspawnslower + 1) + spawner.nextspawnslower;
									else if (spawner.nextspawnframes >= spawner.nextspawnrandom && numofenemies < MAX_NUM_OF_ENEMIES) {
										
										for (int index = 0; index < MAX_NUM_OF_ENEMIES; index++) {
											
											if (enemyarray[index].ID == 0) {
												enemyarray[index].setAttributes(spawner.ID);
												numofenemies++;
												break;
											}
											
										}
										
										spawner.nextspawnframes = 0;
										spawner.nextspawnrandom = 0;
										
										if (spawner.spawns != -1)
											spawner.spawns--;
										
									} else
										spawner.nextspawnframes++;
									
								}
								
							}
							
						}
						
					}
					
					//cat unit logic
					for (FieldCats cat : catarray) {
						
						if (cat.ID != 0) {
							
							if (cat.health <= 0)
								cat.alive = false;
							
							//damage knockback animation
							if (cat.immunity) {
								cat.damageframedata++;
								
								if (cat.damageframedata > MAX_DAMAGE_FRAMES) {
									cat.immunity = false;
									cat.damageframedata = 0;
									cat.ypos = 0;
									cat.fieldimage.setRotate(0);
								} else {
									tempround1 = 0.0000375 * Math.pow(cat.damageframedata, 4);
									tempround2 = -0.00263 * Math.pow(cat.damageframedata, 3);
									tempround3 = -0.0225 * Math.pow(cat.damageframedata, 2);
									tempround4 = 2.7 * cat.damageframedata;
									
									cat.xpos++;
									cat.ypos = Math.abs(tempround1 + tempround2 + tempround3 + tempround4);
								}
								
							}
							
							//proc knockback animation
							if (cat.knockbackimmunity) {
								cat.knockbackframedata++;
								
								if (cat.knockbackframedata > MAX_PROC_FRAMES) {
									cat.knockbackimmunity = false;
									cat.knockbackframedata = 0;
								} else {
									tempround1 = Math.pow(cat.knockbackframedata - 30, 3);
									tempround2 = 6750;
									
									cat.xpos = cat.xpos - (tempround1 / tempround2);
									
									if (cat.framedata != 0)
										cat.framedata = 0;
									
								}
								
							}
							
							//stops when in range of enemy base
							if (cat.xpos + cat.baseline - cat.range <= 180 && cat.idle)
								cat.idle = false;
							if (cat.framedata > cat.animation || cat.framedata == 0)
								cat.cooldownframedata++;
							
							//at each frame, checks all enemies to see if the cat should stop moving
							if (cat.idle && cat.alive && !(cat.immunity || cat.knockbackimmunity)) {
								allowmove = true;
								
								for (FieldEnemies enemy : enemyarray) {
									
									if (cat.stopMovement(enemy)) {
										allowmove = false;
										break;
									}
									
								}
								
								if (allowmove)
									cat.xpos -= cat.speed;
								else
									cat.idle = false;
							
							//otherwise, increment framedata and see where in the attack cycle the cat is
							} else if (cat.alive && !(cat.immunity || cat.knockbackimmunity) && (cat.cooldownframedata >= cat.cooldown || cat.framedata >= cat.animation)) {
								cat.framedata++;
								
								if (cat.framedata == cat.animation) {
									int lowestunit = -1;
									
									for (j = 0; j < 12; j++) {
										
										if (enemyarray[j].ID != 0 && cat.xpos + cat.baseline - cat.range < enemyarray[j].xpos + enemyarray[j].baseline &&
											!(enemyarray[j].immunity || enemyarray[j].cannonimmunity)) {
											
											if (cat.area) {
												enemyarray[j].health -= cat.damage;
												enemyarray[j].determineKnockback();
												
												if (cat.doublecash && enemyarray[j].health <= 0)
													enemyarray[j].pricedrop *= 2;
												
											} else if (lowestunit == -1)
												lowestunit = j;
											else if (enemyarray[j].xpos + enemyarray[j].baseline > enemyarray[lowestunit].xpos + enemyarray[lowestunit].baseline)
												lowestunit = j;
											
										}
										
									}
									
									if (lowestunit != -1) {
										enemyarray[lowestunit].health -= cat.damage;
										enemyarray[lowestunit].determineKnockback();
										
										if (cat.doublecash && enemyarray[lowestunit].health <= 0)
											enemyarray[lowestunit].pricedrop *= 2;
										
									}
									
									if (cat.xpos + cat.baseline - cat.range <= 180 && !winner)
										enemybasehealth -= cat.damage;
									
									cat.cooldownframedata = 0;
								} else if (cat.framedata == cat.animation + cat.backswing) {
									cat.framedata = 0;
									cat.idle = true;
								}
								
							} else if (cat.alive) {
								cat.framedata = 0;
								cat.idle = true;
							}
							
						}
						
					}
					
					for (FieldCats cat : catarray) {
						
						if (cat.checkAndRemove()) {
							numofcats--;
							deathspirits.add(new DeathSpirit(0, 0, 0, new ImageView()));
							deathspirits.get(deathspirits.size() - 1).create(cat.xpos, cat.ypos);
							root.getChildren().add(deathspirits.get(deathspirits.size() - 1).fieldimage);
						}
						
					}
					
					//enemy unit logic
					for (FieldEnemies enemy : enemyarray) {
						
						if (enemy.ID != 0) {
							
							if (enemy.health <= 0)
								enemy.alive = false;
							
							//damage knockback animation
							if (enemy.immunity) {
								enemy.damageframedata++;
								
								if (enemy.damageframedata > MAX_DAMAGE_FRAMES) {
									enemy.immunity = false;
									enemy.damageframedata = 0;
									enemy.ypos = 0;
									enemy.fieldimage.setRotate(0);
								} else {
									
									if (enemy.xpos > 0)
										enemy.xpos--;
									
									tempround1 = 0.0000375 * Math.pow(enemy.damageframedata, 4);
									tempround2 = -0.00263 * Math.pow(enemy.damageframedata, 3);
									tempround3 = -0.0225 * Math.pow(enemy.damageframedata, 2);
									tempround4 = 2.7 * enemy.damageframedata;
									enemy.ypos = Math.abs(tempround1 + tempround2 + tempround3 + tempround4);
								}
								
							}
							
							//cannon knockback animation
							if (activecannon && ((enemy.xpos > 1200 - (wavexpos * 50) && enemy.xpos < 1300 - (wavexpos * 50)) || enemy.cannonimmunity) && (!enemy.cannonimmunity || enemy.cannonframedata != 0) && !enemy.immunity) {
								
								if (enemy.cannonframedata == 0) {
									enemy.health -= CANNON_DAMAGE;
									enemy.determineKnockback();
								}
								
								if (!enemy.immunity) {
									enemy.cannonimmunity = true;
									enemy.cannonframedata++;
									
									if (enemy.cannonframedata > MAX_CANNON_FRAMES) {
										enemy.cannonimmunity = false;
										enemy.cannonframedata = 0;
										enemy.ypos = 0;
									} else {
										
										if (enemy.xpos > 0)
											enemy.xpos -= enemy.speed + 1;
										
										tempround1 = -0.267 * Math.pow(enemy.cannonframedata, 2);
										tempround2 = 4 * enemy.cannonframedata;
										enemy.ypos = tempround1 + tempround2;
									}
									
								}
								
							}
							
							//stops when in range of cat base
							if (enemy.xpos + enemy.baseline + enemy.minrange >= 1375 && enemy.idle)
								enemy.idle = false;
							
							//at each frame, checks all cats to see if the enemy should stop moving
							if (enemy.idle && enemy.alive && !(enemy.immunity || enemy.cannonimmunity)) {
								allowmove = true;
								
								for (FieldCats cat : catarray) {
									
									if (enemy.stopMovement(cat)) {
										allowmove = false;
										break;
									}
									
								}
								
								if (allowmove)
									enemy.xpos += enemy.speed;
								else
									enemy.idle = false;
								
							//otherwise, increment framedata and see where in the attack cycle the enemy is
							} else if (enemy.alive && !(enemy.immunity || enemy.cannonimmunity)) {
								enemy.framedata++;
								
								if (enemy.framedata == enemy.animation) {
									
									for (FieldCats cat : catarray) {
										
										if (enemy.xpos + enemy.baseline + enemy.range > cat.xpos + cat.baseline && enemy.xpos + enemy.baseline + enemy.minrange < cat.xpos + cat.baseline && !(cat.immunity || cat.knockbackimmunity)) {
											double temp = 1;
											
											if (cat.resistance && enemy.ID != 4)
												temp = 0.25;
											
											cat.health -= (int) ((double) enemy.damage * temp);
											cat.determineKnockback();
											
											if (enemy.knockbackchance > 0 && random.nextInt(100) < enemy.knockbackchance)
												cat.knockbackimmunity = true;
											
										}
										
									}
									
									if (enemy.xpos + enemy.baseline + enemy.range >= 1375)
										catbasehealth -= enemy.damage;
									
								} else if (enemy.framedata == enemy.animation + enemy.backswing) {
									enemy.framedata = 0;
									enemy.idle = true;
								}
								
							} else if (enemy.alive) {
								enemy.framedata = 0;
								enemy.idle = true;
							}
							
						}
						
					}
					
					for (FieldEnemies enemy : enemyarray) {
						
						if (enemy.checkAndRemove()) {
							truemoney += enemy.pricedrop;
							numofenemies--;
							deathspirits.add(new DeathSpirit(0, 0, 0, new ImageView()));
							deathspirits.get(deathspirits.size() - 1).create(enemy.xpos, 0);
							root.getChildren().add(deathspirits.get(deathspirits.size() - 1).fieldimage);
						}
						
					}
					
					//renders and positions cat images
					for (FieldCats cat : catarray) {
						
						if (cat.ID == 0)
							cat.fieldimage.setVisible(false);
						else
							cat.render();
						
						cat.position(backgroundpicture.getLayoutX());
					}
					
					//renders and positions enemy images
					for (FieldEnemies enemy : enemyarray) {
						
						if (enemy.ID == 0)
							enemy.fieldimage.setVisible(false);
						else
							enemy.render();
						
						enemy.position(backgroundpicture.getLayoutX());
					}
					
					for (i = deathspirits.size() - 1; i >= 0; i--) {
						
						if (deathspirits.get(i).ypos < -100) {
							deathspirits.get(i).fieldimage.setVisible(false);
							root.getChildren().remove(deathspirits.get(i).fieldimage);
							deathspirits.remove(i);
						} else
							deathspirits.get(i).position(backgroundpicture.getLayoutX());
						
					}
					
					//adds money to the wallet when applicable
					if (truemoney != maxmoney) {
						truemoney = IncomeGeneration(truemoney, maxmoney, workercat);
						money = (int) Math.round(truemoney);
					}
					
					moneydisplay.setText(money + "/" + maxmoney + "$");
					moneydisplay.setX(STAGE_WIDTH - moneydisplay.getLayoutBounds().getWidth() - 3);
					
					//displays whether or not the worker cat can be upgraded
					if (workercat < MAX_WORKER_CAT_LEVEL) {
						canaffordupgrade = ChangeWorkerCatIcon(canaffordupgrade, truemoney, workercat, workercaticon, workercatactive, workercatidle, workercatlevel, active, idle);
					}
					
					//displays approppriate recharge rectangles for recently spawned cats
					if (firstrow) {
					
						for (i = 0; i < NUM_OF_SLOTS_IN_ROW; i++) {
							rechargerects[i].setWidth(96 * (System.currentTimeMillis() - rechargearray[i].lastdeployed) / rechargearray[i].maxrecharge);
							rechargerects[i].toFront();
							
							if (System.currentTimeMillis() - rechargearray[i].lastdeployed > rechargearray[i].maxrecharge) {
								rechargerects[i].setVisible(false);
								slotdisplay[i].setImage(slotpictures[i]);
							}
							
						}
						
					} else {
						
						for (i = NUM_OF_SLOTS_IN_ROW; i < NUM_OF_SLOTS; i++) {
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
					for (i = NUM_OF_CANNON_STATES - 1; i >= 0; i--) {
						
						if (cannonrecharge >= i * MAX_CANNON_RECHARGE / (NUM_OF_CANNON_STATES - 1)) {
							cannonicon.setImage(catcannonicons[i]);
							break;
						}
						
					}
					
					//Updates and displays the cat and enemy base healths
					catbasehealthdisplay.setText(Integer.toString(catbasehealth) + "/120000");
					catbasehealthdisplay.setLayoutX(backgroundpicture.getLayoutX() + 575);
					enemybasehealthdisplay.setText(Integer.toString(enemybasehealth) + "/750000");
					enemybasehealthdisplay.setLayoutX(backgroundpicture.getLayoutX() - 750);
					
					if (catbasehealth <= 0) {
						DefeatScreen(backgroundpicture, bosspercentage, enemyarray[0].health);
						loser = true;
					} else if (enemybasehealth <= 0) {
						
						if (!firsttimewinner) {
							winner = true;
							firsttimewinner = true;
							leftkey = false;
							rightkey = false;
							numofenemies = 0;
							
							for (FieldEnemies enemy : enemyarray)
								enemy.reset();
							
							for (i = 0; i < NUM_OF_SLOTS; i++) {
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
							
							if (!getcatway) {
								getcatway = DROP_RATE > random.nextInt(100);
								tofirsttimereward = false;
							} else
								tofirsttimereward = true;
							
							if (stagecomplete)
								tonextbox = true;
							
						}
						
						delaytimer++;
						
						if (delaytimer == 120 && !tofirsttimereward) {
							tofirsttimereward = true;
							
							if (getcatway) {
								catwayunlock.setVisible(true);
								catwaytext.setVisible(true);
								catwayunlock.toFront();
								catwaytext.toFront();
								delaytimer = 0;
							} else
								delaytimer--;
							
						} else if (delaytimer == 120 && !stagecomplete && !tonextbox) {
							stagecomplete = true;
							firsttimereward.setVisible(true);
							firstcatfoodtext.setVisible(true);
							firstenergytext.setVisible(true);
							firsttimereward.toFront();
							firstcatfoodtext.toFront();
							firstenergytext.toFront();
							
							delaytimer = 0;
							tonextbox = false;
						} else if (delaytimer == 60 && tonextbox && tofirsttimereward) {
							levelfinish.setVisible(true);
							levelfinish.toFront();
						}
						
						catwaytext.setFill(delaytimer % 10 < 5 ? purpleflash : yellowflash);
						firstcatfoodtext.setFill(delaytimer % 10 < 5 ? purpleflash : yellowflash);
						firstenergytext.setFill(delaytimer % 10 < 5 ? purpleflash : yellowflash);
					}
					
					//Forces the screen to the enemy base
					if (winner && backgroundpicture.getLayoutX() <= -VICTORY_MOVE_DISPLACEMENT) {
						backgroundpicture.setLayoutX(backgroundpicture.getLayoutX() + VICTORY_MOVE_DISPLACEMENT);
						
						if (backgroundpicture.getLayoutX() > 0)
							backgroundpicture.setLayoutX(0);
						
					}
					
					backgroundpicture.toBack();
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
		
		for (int i = 0; i < NUM_OF_TEXT_LINES; i++) {
			textinstruct1[i].setX(10);
			textinstruct1[i].setY((30 * i) + 20);
			textinstruct1[i].setFont(Font.font(16));
		}
		
		toMenu1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
				for (Text instruction : textinstruct1)
					instruction.setVisible(false);
				
				whitebackground1.setVisible(false);
				toMenu1.setVisible(false);
			}
			
		});
		
	}
	
	private static void setupLevel(ImageView startbackground1, ImageView selectstart1, ImageView selecthelp1, ImageView backgroundpicture1, Text moneydisplay1, ImageView workercaticon1, Text workercatlevel1, Text workercatcost1, ImageView cannonicon1, ImageView[] slotdisplay, Text catbasehealthdisplay1, Text enemybasehealthdisplay1) {
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
		
		for (ImageView slot : slotdisplay)
			slot.setVisible(true);
		
	}
	
	private static double UpgradeWallet(double truemoney1, int workercat1, Text workercatlevel1, Text workercatcost1) {
		truemoney1 -= workercat1 * WORKER_CAT_COST_INCREASE;
		workercat1++;
		workercatlevel1.setText("Level " + workercat1);
		workercatcost1.setText(workercat1 < MAX_WORKER_CAT_LEVEL ? (workercat1 * WORKER_CAT_COST_INCREASE) + "$" : "MAX");
		
		return truemoney1;
	}
	
	private static void moveLR(ImageView backgroundpicture, boolean leftkey, boolean rightkey) {
		
		if (leftkey && backgroundpicture.getLayoutX() < 0)
			backgroundpicture.relocate(backgroundpicture.getLayoutX() + MOVE_DISPLACEMENT, -STAGE_HEIGHT);
		if (rightkey && backgroundpicture.getLayoutX() > -STAGE_WIDTH)
			backgroundpicture.relocate(backgroundpicture.getLayoutX() - MOVE_DISPLACEMENT, -STAGE_HEIGHT);
		
	}
	
	private static double IncomeGeneration(double truemoney1, int maxmoney1, int workercat1) {
		truemoney1 = truemoney1 + ((workercat1 * WORKER_CAT_INCOME_INCREASE) + WORKER_CAT_INCOME_BASE) / 60;
		
		if (truemoney1 > maxmoney1)
			truemoney1 = maxmoney1;
		
		return truemoney1;
	}
	
	private static boolean ChangeWorkerCatIcon(boolean canaffordupgrade1, double truemoney1, int workercat1, ImageView workercaticon1, Image workercatactive1, Image workercatidle1, Text workercatlevel1, Color active1, Color idle1) {
		
		if (!canaffordupgrade1 && truemoney1 > workercat1 * WORKER_CAT_COST_INCREASE) {
			canaffordupgrade1 = true;
			workercaticon1.setImage(workercatactive1);
			workercatlevel1.setFill(active1);
		} else if (canaffordupgrade1 && truemoney1 < workercat1 * WORKER_CAT_COST_INCREASE) {
			canaffordupgrade1 = false;
			workercaticon1.setImage(workercatidle1);
			workercatlevel1.setFill(idle1);
		}
		
		return canaffordupgrade1;
	}
	
	private static void DefeatScreen(ImageView backgroundpicture, Text bosspercentage, int clionelhealth) {
		backgroundpicture.setImage(new Image("Defeat_Screen.png"));
		backgroundpicture.relocate(0, 0);
		backgroundpicture.toFront();
		
		bosspercentage.setText(100 * clionelhealth / CLIONEL_MAX_HEALTH < 1 ? "1%" : Integer.toString(Math.round(100 * clionelhealth / CLIONEL_MAX_HEALTH)) + "%");
		bosspercentage.setVisible(true);
		bosspercentage.toFront();
	}
	
}