/*
 * TO-DO:
 * Deal with leaked service problem.
 * Get GPS working.
 */

package mtl.hackathon.velobstacles;

import mtl.hackathon.velobstacles.GPS.LocalBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class MainScreen extends Activity implements OnClickListener {

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        
        Button main_report = (Button) findViewById(R.id.report);
        Button main_viewmap = (Button) findViewById(R.id.view_map);
        Button main_services = (Button) findViewById(R.id.services);
        
        main_report.setOnClickListener(this);
        main_viewmap.setOnClickListener(this);
        main_services.setOnClickListener(this);
        
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }
*/
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		
		case R.id.report:
			
			Intent reportActivity = new Intent(this, ObstacleType.class);
			startActivity(reportActivity);
			break;
		
		case R.id.browse:
			
			break;
			
		case R.id.find:
			
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
		return;
	}
	
}

