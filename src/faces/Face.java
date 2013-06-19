package faces;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import processing.core.PApplet;
import processing.core.PVector;

public class Face {

	String tid;

	public float confidence;

	public boolean smiling;
	public float smile_rating;
	public float smile_confidence;

	public String gender;
	public float gender_confidence;

	public PVector center;
	public PVector eye_left;
	public PVector eye_right;
	public PVector mouth_left;
	public PVector mouth_center;
	public PVector mouth_right;
	public PVector nose;

	// Adding some more points
	public PVector chin;
	public PVector ear_left;
	public PVector ear_right;
	public PVector top;

	public float w = -1;
	public float h = -1;

	public float photoWidth = 100;
	public float photoHeight = 100;

	String json;

	// Need to implement Gender, Smiling, Glasses
	// And all the rest of the fields

	public Face() {

	}

	public Face(int w, int h) {
		photoWidth = w;
		photoHeight = h;
	}

	public void setTid(String s) {
		tid = s;
	}

	public void setWidth(double d) {
		w = (float) d;
	}

	public void setHeight(double d) {
		h = (float) d;
	}


	private void transform(PVector v, float width, float height) {
		if (v != null) {
			v.x = PApplet.map(v.x,0,photoWidth,0,width);
			v.y = PApplet.map(v.y,0,photoHeight,0,height);
		}
	}

	public void scale(float width, float height) {
		if (width > 0 && height > 0) {
			transform(center,width,height);
			w = PApplet.map(w,0,photoWidth,0,width);
			h = PApplet.map(h,0,photoHeight,0,height);

			transform(eye_left,width,height);
			transform(eye_right,width,height);
			transform(mouth_left,width,height);
			transform(mouth_center,width,height);
			transform(mouth_right,width,height);
			transform(nose,width,height);
			transform(chin,width,height);
			transform(ear_left,width,height);
			transform(ear_right,width,height);
			transform(top,width,height);
			photoWidth = width;
			photoHeight = height;
		}
	}


	public void normalize() {
		scale(100,100);
	}


	public void setCenter(double x, double y) {
		center = new PVector((float)x,(float)y);
	}

	public void setCenter(PVector v) {
		center = v;
	}

	public void setEyeLeft(double x, double y) {
		eye_left = new PVector((float)x,(float)y);
	}

	public void setEyeLeft(PVector v) {
		eye_left = v;
	}


	public void setEyeRight(double x, double y) {
		eye_right = new PVector((float)x,(float)y);
	}

	public void setEyeRight(PVector v) {
		eye_right = v;
	}

	public void setMouthLeft(double x, double y) {
		mouth_left = new PVector((float)x,(float)y);
	}

	public void setMouthLeft(PVector v) {
		mouth_left = v;
	}


	public void setMouthCenter(double x, double y) {
		mouth_center = new PVector((float)x,(float)y);
	}

	public void setMouthCenter(PVector v) {
		mouth_center = v;
	}

	public void setMouthRight(double x, double y) {
		mouth_right = new PVector((float)x,(float)y);
	}

	public void setMouthRight(PVector v) {
		mouth_right = v;
	}

	public void setNose(double x, double y) {
		nose = new PVector((float)x,(float)y);
	}

	public void setNose(PVector v) {
		nose = v;
	}

	public void setJSON(String s) {
		json = s;
	}

	public String getJSON() {
		return json;
	}

	public void setConfidence(double d) {
		confidence = (float) d;
	}

	public void setSmile(JSONObject smile) throws JSONException {
		smiling = smile.getBoolean("smiling");
		smile_rating = (float) smile.getDouble("smile_rating");
		smile_confidence = (float) smile.getDouble("confidence");
	}

	public void setGender(JSONObject genderObj) throws JSONException {
		gender = genderObj.getString("gender");
		gender_confidence = (float) genderObj.getDouble("confidence");
	}


	public void fromJSON(String content) {
		try {
			JSONObject data = new JSONObject(content);
			fromJSON(data);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void fromJSON(JSONObject json) {
		try {
			setJSON(json.toString());


			setConfidence(json.getDouble("confidence"));

			JSONArray attributes = json.getJSONArray("attributes");
			setSmile(attributes.getJSONObject(0));

			setGender(attributes.getJSONObject(1));


			setTid(json.getString("tid"));
			setCenter(jsonPVector(json,"center"));
			setEyeLeft(jsonPVector(json,"eye_left"));
			setEyeRight(jsonPVector(json,"eye_right"));
			setMouthLeft(jsonPVector(json,"mouth_left"));
			setMouthCenter(jsonPVector(json,"mouth_center"));
			setMouthRight(jsonPVector(json,"mouth_right"));
			setNose(jsonPVector(json,"nose"));
			setWidth(json.getDouble("width"));
			setHeight(json.getDouble("height"));
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	private PVector jsonPVector(JSONObject obj, String s) throws JSONException {
		try {
			JSONObject point = obj.getJSONObject(s);
			PVector v = new PVector((float)point.getDouble("x"),(float)point.getDouble("y"));
			return v;
		} catch (JSONException e) {
			e.printStackTrace();
			return new PVector(-1,-1);
		}
	}

	public void setChin(float x, float y) {
		chin = new PVector(x,y);
	}

	public void setChin(PVector v) {
		chin = v;
	}

	public void setEarLeft(float x, float y) {
		ear_left = new PVector(x,y);
	}
	public void setEarLeft(PVector v) {
		ear_left = v;
	}

	public void setEarRight(float x, float y) {
		ear_right = new PVector(x,y);
	}
	public void setEarRight(PVector v) {
		ear_right = v;
	}

	public void setTop(float x, float y) {
		top = new PVector(x,y);
	}
	public void setTop(PVector v) {
		top = v;
	}

	public float left() {
		return  center.x - w/2;	
	}
	
	public float bottom() {
		return center.y + h/2;
	}





}
