package mtl.hackathon.velobstacles;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class Splash extends Activity implements OnTouchListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        if (Locale.getDefault().getDisplayLanguage().equalsIgnoreCase("French")) {
        	
        	TextView englishText = (TextView) findViewById(R.id.tap);
        	englishText.setVisibility(View.INVISIBLE);
        	englishText = null;
        	
        } else {
        	
        	TextView frenchText = (TextView) findViewById(R.id.tap_french);
        	frenchText.setVisibility(View.INVISIBLE);
        	frenchText = null;
        	
        }
        
        System.out.println("My locale:: "+Locale.getDefault().getDisplayLanguage());

        
        View splashView = (View) findViewById(R.id.splash);
        splashView.setOnTouchListener(this);
        
    }
    
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		
		case R.id.splash:
			
			Intent i = new Intent(this, SignIn.class);
			startActivity(i);
			
			this.finish();
			break;
		
		}
		
		return false;
	}
    
    @Override
    protected void onPause() {
    	
    	super.onPause();
    	this.finish();
    	
    }
	
}

