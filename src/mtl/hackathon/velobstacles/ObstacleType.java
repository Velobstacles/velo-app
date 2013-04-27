package mtl.hackathon.velobstacles;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import static mtl.hackathon.velobstacles.NumConst.*;

/**
 * Activity to allow the user to select the type of obstacle.
 * @author ddaniels4869
 *
 */
public class ObstacleType extends Activity implements OnClickListener{
	
	private Bitmap obstacleBitmap;
	private Uri imageUri = null;
	private Button nextButton = null;
	private RadioGroup obstacleTypes = null;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.obstacle_type);
		
		nextButton = (Button) findViewById(R.id.next_button);
		// disable the submit button until radio button is selected
		nextButton.setEnabled(false);
	    obstacleTypes = (RadioGroup) findViewById(R.id.radioGroup1);
		
		nextButton.setOnClickListener(this);
		obstacleTypes.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO implement the cases to do something with the data once a radio button is clicked
				
				switch(checkedId) {
				
				case R.id.quality:
					
					
				case R.id.sudden_end:
					
				
				case R.id.congestion:
					
					
				case R.id.lighting:
					
					
				case R.id.other_problem:
					
					
				case R.id.ped_cc:
					
					
				case R.id.trans_cc:
					
					
				case R.id.veh_cc:
					ObstacleType.this.nextButton.setEnabled(true);

					break;
					
				default:
					ObstacleType.this.nextButton.setEnabled(false);
				
				}
				
			}
			
		});

	}

	/**
	 * method to handle button clicks (non-radio button)
	 * @param v
	 * 		the view that triggered this method
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()) {
		
		case R.id.next_button:
			
			// determine which option was selected
			int obstacleSelected = ((RadioGroup) findViewById(R.id.radioGroup1)).getCheckedRadioButtonId();
			
			String filename = "velobstacles" + System.currentTimeMillis();
			ContentValues values = new ContentValues();
			values.put(MediaStore.Images.Media.TITLE, filename);
			imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
			
			Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			startActivityForResult(cameraIntent, CAPTURE_IMAGE);
			break;
			
		}
		
	}
	
	/**
	 * method that handles photo data after picture has been taken, and then sends it to next activity
	 * to upload to server.
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		switch(requestCode) {
		
		case CAPTURE_IMAGE:
			
			if (resultCode == Activity.RESULT_OK) {
				
				//obstacleBitmap = (Bitmap) data.getExtras().get("data");
				//ByteArrayOutputStream bos = new ByteArrayOutputStream();
				//obstacleBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
				//byte[] imageBytes = bos.toByteArray();
				
			      String[] projection = { MediaStore.Images.Media.DATA}; 
		            Cursor cursor = null;
		            try {
		            	cursor = getContentResolver().query(imageUri, projection, null, null, null); 
		            } catch (Exception e) {
		            	e.printStackTrace();
		            }
		            int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA); 
		            cursor.moveToFirst(); 
		            String capturedImageFilePath = cursor.getString(column_index_data);
				
				//Uri u = data.getData();
				
				Intent serverIntent = new Intent(this, ServerUpload.class);
				//serverIntent.putExtra(IMAGE_BYTES, imageBytes);
				serverIntent.putExtra(IMAGE_PATH, capturedImageFilePath);
				startActivity(serverIntent);
			}
			
			break;
		
		}
		
	}
	
	@Override
	public void onStop() {
		
		super.onStop();
		obstacleBitmap = null;
		
	}

}
