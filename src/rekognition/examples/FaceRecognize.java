package rekognition.examples;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.FloatDict;
import rekognition.faces.Face;
import rekognition.faces.RekognitionFace;

public class FaceRecognize extends PApplet {
	String path = "data/obama2.jpg";
	PImage img;

	Face[] faces;

	public void setup() {
		size(800,600);
		String[] keys = loadStrings("key.txt");
		String key = keys[0];
		String secret = keys[1];
		img = loadImage(path);
		RekognitionFace face = new RekognitionFace(this,key,secret);
		face.setNamespace("test999");
		face.setUserID("1");
		faces = face.recognizeFace(path);
	}

	public void draw() {
		background(0);
		image(img,0,0);
		for (int i = 0; i < faces.length; i++) {
			stroke(255,0,0);
			strokeWeight(1);
			noFill();
			rectMode(CENTER);
			rect(faces[i].center.x,faces[i].center.y,faces[i].w,faces[i].h);

			FloatDict matches = faces[i].getMatches();
			fill(255);
			String display = "";
			for (String key : matches.keys()) {
				float likely = matches.get(key);
				display += key + ": " + likely + "\n";
			}
			text(display,faces[i].left(),faces[i].bottom()+20);	
		}
	}

	public static void main(String[] args) {
		PApplet.main(new String[] {FaceRecognize.class.getName()});
	}
}
