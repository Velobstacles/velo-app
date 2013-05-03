/*
 * TO-DO:
 * Deal with leaked service problem.
 * Get GPS working.
 */

package mtl.hackathon.velobstacles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class MainScreen extends Activity implements OnClickListener {

	static final LocationUpdater _locationUpdater;
	
	static {
		_locationUpdater = LocationUpdater.getLocationUpdaterInstance();
	}
	
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
	
	@Override
	public void onBackPressed() {
		return;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		_locationUpdater.start(this);
	}
	
	@Override
	public void onStop() {
		super.onStop();
		_locationUpdater.stop();
	}
	
}

