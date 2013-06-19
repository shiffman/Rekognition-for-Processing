import httprocessing.*;
import rekognition.faces.*;

String path = "data/obama2.jpg";
PImage img;

Face[] faces;

void setup() {
  size(800, 400);
  String[] keys = loadStrings("key.txt");
  String api_key = keys[0];
  String api_secret = keys[1];
  img = loadImage(path);
  RekognitionFace face = new RekognitionFace(this, api_key, api_secret);
  faces = face.recognizeFace(sketchPath(path));
}

void draw() {
  background(0);
  image(img, 0, 0);
  for (int i = 0; i < faces.length; i++) {
    stroke(255, 0, 0);
    strokeWeight(1);
    noFill();
    rectMode(CENTER);
    rect(faces[i].center.x, faces[i].center.y, faces[i].w, faces[i].h);

    FloatDict matches = faces[i].getMatches();
    fill(255);
    String display = "";
    for (String key : matches.keys()) {
      float likely = matches.get(key);
      display += key + ": " + likely + "\n";
    }
    display += "\nAge: " + int(faces[i].age) + "\n\n";
    display += "Gender: " + faces[i].gender + "\n";
    display += "Gender rating: " + nf(faces[i].gender_rating, 1, 2) + "\n\n";
    display += "Smiling: " + faces[i].smiling + "\n";
    display += "Smile rating: " + nf(faces[i].smile_rating, 1, 2) + "\n\n";
    display += "Glasses: " + faces[i].glasses + "\n";
    display += "Glasses rating: " + nf(faces[i].glasses_rating, 1, 2) + "\n\n";
    display += "Eyes closed: " + faces[i].eyes_closed + "\n";
    display += "Eyes closed rating: " + nf(faces[i].eyes_closed_rating, 1, 2) + "\n\n";    
    
    
    text(display, img.width+10, 60);
  }
}

