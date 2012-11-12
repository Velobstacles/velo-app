package mtl.hackathon.velobstacles;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import static mtl.hackathon.velobstacles.NumConst.*;

/**
 * class that will handle server task.
 * @author David Danielson
 *
 */
public class ServerUpload extends Activity {
	
	private byte[] imageBytes = null;
	
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
		imageBytes = this.getIntent().getByteArrayExtra(IMAGE_BYTES);
		
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
			this.progressSpinner.setMessage("Uploading your data to server...");
			this.progressSpinner.setCancelable(false);
			this.progressSpinner.setIndeterminate(true);
			this.progressSpinner.show();
			
		}
		
		/**
		 * method that will upload the data to the server.
		 */
		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		/**
		 * method that will dismiss the progress dialogue, and start the next activity.
		 */
		@Override
		protected void onPostExecute(Void result) {
			
			this.progressSpinner.dismiss();
			this.progressSpinner = null;
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
	
	@Override
	public void onBackPressed() {
		
		// do nothing if back button is pressed
		return;
		
	}
	
}
