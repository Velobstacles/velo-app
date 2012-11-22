package mtl.hackathon.velobstacles;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
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
	
	Bitmap obstacleBitmap;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.obstacle_type);
		
		Button nextButton = (Button) findViewById(R.id.next_button);
		RadioGroup obstacleTypes = (RadioGroup) findViewById(R.id.radioGroup1);
		
		nextButton.setOnClickListener(this);
		obstacleTypes.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO implement the cases to do something with the data once a radio button is clicked
				
				switch(checkedId) {
				
				case R.id.quality:
					
					break;
					
				case R.id.sudden_end:
					
					break;
				
				case R.id.congestion:
					
					break;
					
				case R.id.lighting:
					
					break;
					
				case R.id.other_problem:
					
					break;
					
				case R.id.ped_cc:
					
					break;
					
				case R.id.trans_cc:
					
					break;
					
				case R.id.veh_cc:
					
					break;
				
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
			
			Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
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
				
				obstacleBitmap = (Bitmap) data.getExtras().get("data");
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				obstacleBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
				byte[] imageBytes = bos.toByteArray();
				
				Intent serverIntent = new Intent(this, ServerUpload.class);
				serverIntent.putExtra(IMAGE_BYTES, imageBytes);
				startActivityForResult(serverIntent, NOTHING);
			}
			
			break;
			
		case EXIT_ALL:
			
			setResult(EXIT_ALL);
			finish();
			
			break;
		
		}
		
	}
	
	@Override
	public void onStop() {
		
		super.onStop();
		obstacleBitmap = null;
		
	}

}
