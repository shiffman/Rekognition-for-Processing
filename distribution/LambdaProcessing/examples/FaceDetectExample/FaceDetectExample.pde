import faces.*;

String path = "data/superman.jpg";
PImage img;
Face[] faces;

public void setup() {
  size(800, 600);
  String key = loadStrings("key.txt")[0];
  LambdaFace face = new LambdaFace(this, key);
  img = loadImage(path);
  faces = face.detectFacesPath(sketchPath(path));
}

public void draw() {
  background(0);
  image(img, 0, 0);
  for (int i = 0; i < faces.length; i++) {
    stroke(0);
    strokeWeight(4);
    fill(255,25);
    rectMode(CENTER);
    rect(faces[i].center.x, faces[i].center.y, faces[i].w, faces[i].h);
    strokeWeight(1);
    rect(faces[i].eye_right.x, faces[i].eye_right.y, 4, 4);
    rect(faces[i].eye_left.x, faces[i].eye_left.y, 4, 4);
    rect(faces[i].mouth_left.x, faces[i].mouth_left.y, 4, 4);
    rect(faces[i].mouth_center.x, faces[i].mouth_center.y, 4, 4);
    rect(faces[i].mouth_right.x, faces[i].mouth_right.y, 4, 4);
    rect(faces[i].nose.x, faces[i].nose.y, 4, 4);

    fill(255);

    String display = "Gender: " + faces[i].gender + "\n";
    display += "Gender confidence: " + nf(faces[i].gender_confidence, 1, 2) + "\n\n";

    display += "Smiling: " + faces[i].smiling + "\n";
    display += "Smile rating: " + nf(faces[i].smile_rating, 1, 2) + "\n";

    text(display, faces[i].left(), faces[i].bottom()+20);
  }
}

