import httprocessing.*;

import rekognition.faces.*;

String path = "data/obama.jpg";
PImage img;
Face[] faces;

public void setup() {
  size(800, 600);
  String[] keys = loadStrings("key.txt");
  String api_key = keys[0];
  String api_secret = keys[1];

  RekognitionFace face = new RekognitionFace(this, api_key, api_secret);
  img = loadImage(path);
  faces = face.detectFacesPath(sketchPath(path));
}

public void draw() {
  background(0);
  image(img, 0, 0);
  for (int i = 0; i < faces.length; i++) {
    stroke(0, 0, 0);
    strokeWeight(1);
    noFill();
    rectMode(CENTER);
    rect(faces[i].center.x, faces[i].center.y, faces[i].w, faces[i].h);
    rect(faces[i].eye_right.x, faces[i].eye_right.y, 4, 4);
    rect(faces[i].eye_left.x, faces[i].eye_left.y, 4, 4);
    rect(faces[i].mouth_left.x, faces[i].mouth_left.y, 4, 4);
    rect(faces[i].mouth_right.x, faces[i].mouth_right.y, 4, 4);
    rect(faces[i].nose.x, faces[i].nose.y, 4, 4);
    fill(0,255,0);
    String display = "Age: " + int(faces[i].age) + "\n\n";
    display += "Gender: " + faces[i].gender + "\n";
    display += "Gender rating: " + nf(faces[i].gender_rating, 1, 2) + "\n\n";
    display += "Smiling: " + faces[i].smiling + "\n";
    display += "Smile rating: " + nf(faces[i].smile_rating, 1, 2) + "\n\n";
    display += "Glasses: " + faces[i].glasses + "\n";
    display += "Glasses rating: " + nf(faces[i].glasses_rating, 1, 2) + "\n\n";
    display += "Eyes closed: " + faces[i].eyes_closed + "\n";
    display += "Eyes closed rating: " + nf(faces[i].eyes_closed_rating, 1, 2) + "\n\n";    
    text(display, faces[i].left(), faces[i].bottom()+20);
  }
}

