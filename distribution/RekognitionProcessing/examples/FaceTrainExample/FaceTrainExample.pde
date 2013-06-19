import httprocessing.*;
import rekognition.faces.*;

PImage img;

void setup() {
  size(300, 200);
  String[] keys = loadStrings("key.txt");
  String api_key = keys[0];
  String api_secret = keys[1];
  RekognitionFace face = new RekognitionFace(this, api_key, api_secret);
  //face.addFace("data/obama.jpg", "Obama");
  face.addFace(sketchPath("data/pitt.jpg"), "Pitt");
  
  // You can train after multiple images are added
  face.train();
}

void draw() {
}

