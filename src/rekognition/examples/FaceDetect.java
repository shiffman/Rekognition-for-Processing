package rekognition.examples;

import java.io.File;

import processing.core.PApplet;
import processing.core.PImage;
import rekognition.faces.RFace;
import rekognition.faces.Rekognition;

public class FaceDetect extends PApplet {

	String path = "data/obama.jpg";
	PImage img;
	RFace[] faces;
	
	public void setup() {
		size(800,600);
		String[] keys = loadStrings("key.txt");
		String key = keys[0];
		String secret = keys[1];
		
		Rekognition face = new Rekognition(this,key,secret);
		img = loadImage(path);
		faces = face.detect(path);
	}
	
	public void draw() {
		background(0);	
		for (int i = 0; i < faces.length; i++) {
			stroke(255,0,0);
			strokeWeight(1);
			noFill();
			rectMode(CENTER);
			rect(faces[i].center.x,faces[i].center.y,faces[i].w,faces[i].h);
			rect(faces[i].eye_right.x,faces[i].eye_right.y,4,4);
			rect(faces[i].eye_left.x,faces[i].eye_left.y,4,4);
			rect(faces[i].mouth_left.x,faces[i].mouth_left.y,4,4);
			rect(faces[i].mouth_right.x,faces[i].mouth_right.y,4,4);
			rect(faces[i].nose.x,faces[i].nose.y,4,4);
			
			fill(255);
			String display = "Age: " + faces[i].age + "\n\n";
			display += "Gender: " + faces[i].gender + "\n";
			display += "Gender confidence: " + nf(faces[i].gender_rating,1,2) + "\n\n";
			display += "Smiling: " + faces[i].smiling + "\n";
			display += "Smile rating: " + nf(faces[i].smile_rating,1,2) + "\n\n";
			display += "Glasses: " + faces[i].glasses + "\n";
			display += "Glasses rating: " + nf(faces[i].glasses_rating,1,2) + "\n\n";
			display += "Eyes closed: " + faces[i].eyes_closed + "\n";
			display += "Eyes closed rating: " + nf(faces[i].eyes_closed_rating,1,2) + "\n\n";		
			text(display,faces[i].left(),faces[i].bottom()+20);
			
		}
	}
	
	public static void main(String[] args) {
		PApplet.main(new String[] {FaceDetect.class.getName()});
	}
	
}
