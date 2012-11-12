package mtl.hackathon.velobstacles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import static mtl.hackathon.velobstacles.NumConst.*;

/**
 * just a simple activity to thank the user before returning to the main menu.
 * @author David Danielson
 *
 */
public class ThanksActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thanks_page);
		
		Button returnButton = (Button) findViewById(R.id.return_to_main);
		
		returnButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ThanksActivity.this.finish();
				Intent mainIntent = new Intent(ThanksActivity.this, MainMenu.class);
				startActivityForResult(mainIntent, NOTHING);
				
			}
			
		});
		
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
		
		return;
		
	}
	
}
