Rekognition for Processing
==========================

A quick Processing library to make using [Rekognition API](http://rekognition.com/) easy to use in Processing.  

[Rekognition](http://rekognition.com/) is an open API platform provided by Orbeus, which helps developers easily incorporate the state-of-the-art computer vision capabilities of facial recognition, object recognition and scene understanding into your app or software. 

Getting Started
===============

To use the library, you'll need to:

* [Register for an API Key](https://rekognition.com/user/create) with Orbeus, Inc.
* Install the [HTTP Requests for Processing](https://github.com/runemadsen/HTTP-Requests-for-Processing) library.

How-to
======


## Create library instance

```
Rekognition rekog = new Rekognition(this, APIKEY, APISECRET);
// Optionall set namespace and app name
rekog.setNamespace("processing");
rekog.setUserID("demo");
```

## Basic Face Detection

```
// Get a list of faces
RFace[] faces = rekog.detectFacesPath(filename);
// Get face meta data
for (int i = 0; i < faces.length; i++) {
  PVector center = faces[i].center;
  PVector eye_right = faces[i].eye_right;
  PVector eye_right = faces[i].eye_right;
  PVector eye_left = faces[i].eye_left;
  PVector mouth_right = faces[i].mouth_right;
  PVector mouth_left = faces[i].mouth_left;
  PVector nose = faces[i].nose;

  String gender = faces[i].gender;
  float age = faces[i].age;

  // and more!
}
```

## Train Face Recognition

```
// Here we tell Rekognition that the face in this image associated with this name
rekog.addFace("pitt.jpg", "Pitt");

// We need a second API call to train Rekognition of whatever faces have been added
// Here it's one face, then train, but you could add a lot of faces before training
rekog.train();
```

## Face Recognize

```
for (int i = 0; i < faces.length; i++) {
  // Possible face matches come back in a FloatDict
  // A string (name of face) is paired with a float from 0 to 1 (how likely is it that face)
  FloatDict matches = faces[i].getMatches();
  for (String key : matches.keys()) {
    float likely = matches.get(key);
    println(key + ": " + likely);
  }
}
```



