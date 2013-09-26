package rekognition.examples;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.FloatDict;
import rekognition.faces.RFace;
import rekognition.faces.Rekognition;

public class FaceRecognize extends PApplet {
	String path = "http://rack.3.mshcdn.com/media/ZgkyMDEzLzA2LzA1L2IzL2JhcmFja29iYW1hLmNkN2IwLmpwZwpwCXRodW1iCTk1MHg1MzQjCmUJanBn/751beb17/8c6/barack-obama.jpg";
	PImage img;

	RFace[] faces;

	public void setup() {
		size(800,600);
		String[] keys = loadStrings("key.txt");
		String key = keys[0];
		String secret = keys[1];
		img = loadImage(path);
		Rekognition face = new Rekognition(this,key,secret);
		face.setNamespace("faceit1");
		face.setUserID("shiffman");
		faces = face.recognize(path);
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
