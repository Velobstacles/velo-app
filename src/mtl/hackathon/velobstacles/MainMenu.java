package mtl.hackathon.velobstacles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class MainMenu extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        
        Button reportButton = (Button) findViewById(R.id.report);
        Button browseButton = (Button) findViewById(R.id.browse);
        Button findButton = (Button) findViewById(R.id.find);
        
        reportButton.setOnClickListener(this);
        browseButton.setOnClickListener(this);
        findButton.setOnClickListener(this);
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		
		case R.id.report:
			
			Intent reportActivity = new Intent(this, ReportObstacle.class);
			startActivity(reportActivity);
			break;
		
		case R.id.browse:
			
			break;
			
		case R.id.find:
			
			break;
		
		}
		
	}
}
