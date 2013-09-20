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
		face.setNamespace("faceit2");
		face.setUserID("shiffman");
		
		face.addFace("data/pitt.jpg", "Brad Pitt");
		face.addFace("data/obama.jpg","Barack Obama");
		  	
		face.train();	
	}
	
	public void draw() {
		background(0);	
	}
	
	public static void main(String[] args) {
		PApplet.main(new String[] {AddFaceAndTrain.class.getName()});
	}
	
}
