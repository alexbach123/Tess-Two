package com.tesseract.phonegap;

import java.io.File;


import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import com.googlecode.tesseract.android.TessBaseAPI;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;


public class TesseractStart extends CordovaPlugin {
	//Add tesseractEntry action to our plugin
	public static final String ACTION_ADD_TESSERACT_ENTRY = "addTesseractEntry";
	public static final String DATA_PATH = Environment.getExternalStorageDirectory().toString() + "/PhoneGapTessTwo/";
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
	 
		try {
		    if (ACTION_ADD_TESSERACT_ENTRY.equals(action)) { 
		             JSONObject arg_object = args.getJSONObject(0);
		             //Intent calIntent = new Intent(Intent.ACTION_EDIT)
		             //.setType("vnd.android.cursor.item/event")
		             //.putExtra("beginTime", arg_object.getLong("startTimeMillis"));
		             String sampleTest = arg_object.getString("sampleTest");
		             //this.SampleTest(sampleTest, callbackContext);
		             String result = TesseractExample();
		             callbackContext.error(""+result+"");
		       		            
		             
		       return true;
		    }
		    callbackContext.error("Invalid action");
		    return false;
		} catch(Exception e) {
		    System.err.println("Exception: " + e.getMessage());
		    callbackContext.error(e.getMessage());
		    return false;
		} 
	}
	
	public String TesseractExample() {
        File imageFile = new File("eurotext.png");
		// _path = path to the image to be OCRed
		
		
		//File myDir = DATA_PATH;
				//getExternalFilesDir(Environment.MEDIA_MOUNTED);
		

	     // lang = for which the language data exists, usually "eng"
		TessBaseAPI baseApi = new TessBaseAPI();
		
		 // DATA_PATH = Path to the storage
		baseApi.init(DATA_PATH, "eng"); // myDir + "/tessdata/eng.traineddata" must be present
		
		// Eg. baseApi.init("/mnt/sdcard/tesseract/tessdata/eng.traineddata", "eng");
		baseApi.setImage(imageFile);
		 
		String recognizedText = baseApi.getUTF8Text(); // Log or otherwise display this string...
		baseApi.end();
		
		return recognizedText;
    }
	
	
	private void SampleTest(String sampletest, CallbackContext callbackContext) {
		 if (sampletest != null && sampletest.length() > 0) {
	            callbackContext.success(sampletest);
	        } else {
	            callbackContext.error("Expected one non-empty string argument.");
	        }
		
	}

}