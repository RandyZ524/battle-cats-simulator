import javafx.scene.image.*;

import java.util.Random;

public class DeathSpirit {
	int framedata;
	double xpos, ypos;
	ImageView fieldimage;
	
	DeathSpirit(int framedata, double xpos, double ypos, ImageView fieldimage) {
		this.framedata = framedata;
		this.xpos = xpos;
		this.ypos = ypos;
		this.fieldimage = fieldimage;
	}
	
	public void create(double unitxpos, double unitypos) {
		Random random = new Random();
		xpos = unitxpos;
		ypos = 200 + unitypos;
		framedata = random.nextInt(30);
		fieldimage.setImage(new Image("Death_Spirit.png"));
	}
	
	public void position(double backgroundpicturexpos) {
		fieldimage.setLayoutX(xpos + backgroundpicturexpos - 15 * Math.sin(Math.toRadians((double) framedata * 360 / 30)));
		fieldimage.setLayoutY(ypos -= 5);
		fieldimage.toBack();
		framedata = (framedata + 1) % 30;
	}
	
}