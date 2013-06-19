package rekognition.faces;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import processing.core.PApplet;
import processing.core.PVector;
import processing.data.FloatDict;

public class Face {


	public PVector topleft;
	public PVector center;
	public float w = -1;
	public float h = -1;

	public float confidence;

	public PVector eye_left;
	public PVector eye_right;

	public PVector nose;

	public PVector mouth_left;
	public PVector mouth_right;

	public float age;

	public boolean smiling;
	public float smile_rating;

	public boolean glasses;
	public float glasses_rating;

	public boolean eyes_closed;
	public float eyes_closed_rating;

	public String gender;
	public float gender_rating;

	public float photoWidth = 100;
	public float photoHeight = 100;

	String json;
	FloatDict names;

	public Face() {
	}

	public Face(int w, int h) {
		photoWidth = w;
		photoHeight = h;
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
			transform(mouth_right,width,height);
			transform(nose,width,height);
			photoWidth = width;
			photoHeight = height;
		}
	}


	public void normalize() {
		scale(100,100);
	}


	public void setTopLeft(double x, double y) {
		topleft = new PVector((float)x,(float)y);
	}
	public void setTopLeft(PVector v) {
		topleft = v;
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

	public void setSmile(double s) {
		if (s > 0.5) {
			smiling = true;
		}
		smile_rating = (float) s;
	}

	public void setGender(double g)  {
		if (g > 0.5) {
			gender = "male";
		} else {
			gender = "female";
		}
		gender_rating = (float) g;
	}

	public void setGlasses(double s) {
		if (s > 0.5) {
			glasses = true;
		}
		glasses_rating = (float) s;
	}

	public void setEyesClosed(double s) {
		if (s > 0.5) {
			eyes_closed = true;
		}
		eyes_closed_rating = (float) s;
	}


	public void setBox(JSONObject box) throws JSONException {
		setTopLeft(jsonPVector(box,"tl"));
		JSONObject size = box.getJSONObject("size");
		setWidth(size.getDouble("width"));
		setHeight(size.getDouble("height"));
		setCenter(topleft.x + w/2,topleft.y + h/2);
	}

	public void setAge(double a) {
		age = (float) a;
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
			setBox(json.getJSONObject("boundingbox"));
			setConfidence(json.getDouble("confidence"));

			setEyeLeft(jsonPVector(json,"eye_left"));
			setEyeRight(jsonPVector(json,"eye_right"));
			setNose(jsonPVector(json,"nose"));
			setMouthLeft(jsonPVector(json,"mouth_l"));
			setMouthRight(jsonPVector(json,"mouth_r"));

			setAge(json.getDouble("age"));
			setSmile(json.getDouble("smile"));
			setGender(json.getDouble("sex"));
			setGlasses(json.getDouble("glasses"));
			setEyesClosed(json.getDouble("eye_closed"));


			if (json.has("matches")) {
				JSONArray matches = json.getJSONArray("matches");
				names = new FloatDict();

				for (int i = 0; i < matches.length(); i++) {
					JSONObject match = matches.getJSONObject(i);
					names.set(match.getString("tag"),(float)match.getDouble("score"));
				} 
			}

		} catch (JSONException e) {
			//e.printStackTrace();
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

	public float left() {
		return  center.x - w/2;	
	}

	public float bottom() {
		return center.y + h/2;
	}

	public FloatDict getMatches() {
		if (names == null) return new FloatDict();
		return names;
	}





}
