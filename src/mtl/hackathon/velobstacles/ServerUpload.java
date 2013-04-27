package mtl.hackathon.velobstacles;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.activation.MimetypesFileTypeMap;

import mtl.hackathon.velobstacles.GPS.LocalBinder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.webkit.MimeTypeMap;
import static mtl.hackathon.velobstacles.NumConst.*;

/**
 * class that will handle server task.
 * @author David Danielson
 *
 */
public class ServerUpload extends Activity {
	org.apache.http.auth.AUTH auth;
	private String imagePath = null;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.server_upload);
		
		ProgressDialog dialogue = new ProgressDialog(this);
		
		/*
		 * getting the bytes from the image (from the previous activity)
		 *  that will be sent to the server.
		 */
		imagePath = this.getIntent().getStringExtra(IMAGE_PATH);
		
		
		// executing the server upload
		new UploadTask(dialogue).execute();
		
	}
	
	/**
	 * private inner-class specifically responsible for uploading the data to the server.
	 * @author David Danielson
	 *
	 */
	private class UploadTask extends AsyncTask<Void, Void, Void> {

		private ProgressDialog progressSpinner;
		
		/**
		 * public constructor.
		 * @param pProgressDialog
		 * 		the progress dialogue to be shown during server upload
		 */
		public UploadTask(ProgressDialog pProgressDialog) {
			
			this.progressSpinner = pProgressDialog;
			
		}
		
		/**
		 * method to handle showing the progress dialogue BEFORE the server upload begins.
		 */
		@Override
		protected void onPreExecute() {
			
			super.onPreExecute();
			/*this.progressSpinner.setMessage("Uploading your data to server...");
			this.progressSpinner.setCancelable(false);
			this.progressSpinner.setIndeterminate(true);
			this.progressSpinner.show();*/
			
		}
		
		/**
		 * method that will upload the data to the server.
		 */
		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			/*try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			// Get GPS Data
			LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE); 
			Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			double longitude;
			double latitude;
			
			if (location == null) {
			
			longitude = 0d;//location.getLongitude();
			latitude = 0d;//location.getLatitude();
			
			} else {
				longitude = gpsService.getLongitude();
				latitude = gpsService.getLatitude();
			}
			
			
			HttpClient client = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			//File imageFile = new File(imageUri.getPath());
			HttpPost post = new HttpPost("http://api.velobstacles.com/media");
			
			File imageFile = new File (imagePath);	
			
		    String type = null;

		        MimeTypeMap mime = MimeTypeMap.getSingleton();
		        type = mime.getMimeTypeFromExtension("jpg");
		        
	       // String mimetype = new MimetypesFileTypeMap().getContentType(imageFile);
	        System.out.println("Content Type: " + type);
	        FileBody content = new FileBody(imageFile, type);


			
			try {
				MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
		                // If the key equals to "image", we use FileBody to transfer the data
		                entity.addPart("content", content);
		                entity.addPart("latitude", new StringBody(Double.toString(latitude)));
		                entity.addPart("longitude", new StringBody(Double.toString(longitude)));

		        post.setEntity(entity);

		        HttpResponse response = client.execute(post, localContext);
		        System.out.println("Response: " + response.getStatusLine().getReasonPhrase());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			return null;
		}
		
		/**
		 * method that will dismiss the progress dialogue, and start the next activity.
		 */
		@Override
		protected void onPostExecute(Void result) {
			
			/*this.progressSpinner.dismiss();
			this.progressSpinner = null;*/
			ServerUpload.this.finish();
			Intent thanksIntent = new Intent(ServerUpload.this, ThanksActivity.class);
			startActivityForResult(thanksIntent, NOTHING);
			
			
		}
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {
		
		case EXIT_ALL:
			
			setResult(EXIT_ALL);
			this.finish();
			break;
			
		}
		
	}
	
	GPS gpsService;
	boolean mBound = false;
	private ServiceConnection mConnection = new ServiceConnection() {
	    // Called when the connection with the service is established
	    public void onServiceConnected(ComponentName className, IBinder service) {
	        // Because we have bound to an explicit
	        // service that is running in our own process, we can
	        // cast its IBinder to a concrete class and directly access it.
	        LocalBinder binder = (LocalBinder) service;
	        gpsService = binder.getGPS();
	        mBound = true;
	    }


		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub
            mBound = false;

		}

	};
	@Override
	public void onStart() {
		super.onStart();
		Intent intent = new Intent(this, GPS.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (gpsService != null) {
			gpsService.stopUsingGPS();
            unbindService(mConnection);
		}
	}
	
	@Override
	public void onBackPressed() {
		
		// do nothing if back button is pressed
		return;
		
	}
	
}
