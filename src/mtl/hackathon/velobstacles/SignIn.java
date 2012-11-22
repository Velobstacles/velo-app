package mtl.hackathon.velobstacles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import static mtl.hackathon.velobstacles.NumConst.*;

public class SignIn extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        
        Button skipButton = (Button) findViewById(R.id.skip_button);
        skipButton.setOnClickListener(this);
        
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()) {
		
		case R.id.skip_button:
			
			this.finish();
			Intent i = new Intent(this, MainScreen.class);
			startActivityForResult(i, NOTHING);
			break;
		
		}
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {
		
		case EXIT_ALL:
			
			this.finish();
			break;
			
		}
		
	}
	
}