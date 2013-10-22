
package dk.evry.tesseract;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.Activity;
import android.content.Intent;


public class TesseractStart extends CordovaPlugin {
	//Add tesseractEntry action to our plugin
	public static final String ACTION_ADD_TESSERACT_ENTRY = "addTesseractEntry";
	
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
        Tesseract instance = Tesseract.getInstance(); // JNA Interface Mapping
        // Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
        String result = "";
        try {
            result = instance.doOCR(imageFile);
            
        } catch (TesseractException e) {
            result = "OOOOOO";
        }
        return result;
    }
	
	
	private void SampleTest(String sampletest, CallbackContext callbackContext) {
		 if (sampletest != null && sampletest.length() > 0) {
	            callbackContext.success(sampletest);
	        } else {
	            callbackContext.error("Expected one non-empty string argument.");
	        }
		
	}

}
