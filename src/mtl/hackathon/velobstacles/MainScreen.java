package mtl.hackathon.velobstacles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
        Button main_settings = (Button) findViewById(R.id.settings);
        
        main_report.setOnClickListener(this);
        main_viewmap.setOnClickListener(this);
        main_services.setOnClickListener(this);
        main_settings.setOnClickListener(this);        
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }
*/
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) { 
	
		
			case R.id.report:
				
				Intent reportActivity = new Intent(this, ObstacleType.class);
				startActivity(reportActivity);
				break;
				
			case R.id.view_map:
				
				break;
				
			case R.id.services:
				
				break;
				
			case R.id.settings:
				
				break;
		}	
			
		
	}
}
