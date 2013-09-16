package rekognition.examples;

import java.io.File;

import processing.core.PApplet;
import processing.core.PImage;
import rekognition.faces.RFace;
import rekognition.faces.Rekognition;

public class AddFaceAndTrain extends PApplet {

	PImage img;
	
	public void setup() {
		size(800,600);
		String[] keys = loadStrings("key.txt");
		String key = keys[0];
		String secret = keys[1];
		Rekognition face = new Rekognition(this,key,secret);
		face.setNamespace("test999");
		face.setUserID("1");
		
		face.addFace("data/obama.jpg", "Obama");
		face.addFace("data/pitt.jpg", "Pitt");
		
		face.train();	
	}
	
	public void draw() {
		background(0);	
	}
	
	public void mousePressed() {
		//face.train();
	}
	
	public static void main(String[] args) {
		PApplet.main(new String[] {AddFaceAndTrain.class.getName()});
	}
	
}
