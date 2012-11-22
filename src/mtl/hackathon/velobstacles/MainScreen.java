package mtl.hackathon.velobstacles;

import static mtl.hackathon.velobstacles.NumConst.EXIT_ALL;
import static mtl.hackathon.velobstacles.NumConst.NOTHING;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;

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
			startActivityForResult(reportActivity, NOTHING);
			break;
		
		case R.id.browse:
			
			break;
			
		case R.id.find:
			
			break;
		
		}
		
	}
		
	private void exitApplication() {
		
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainScreen.this);
		
		alertDialog.setPositiveButton("Yes", new android.content.DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				
				setResult(EXIT_ALL);
				finish();
				//System.runFinalizersOnExit(true);
				//System.exit(0);
			    //android.os.Process.killProcess(android.os.Process.myPid());
				
			}			
		});
		
		alertDialog.setNegativeButton("No", null);
		alertDialog.setMessage("Do you wish to exit?");
		alertDialog.setTitle("Velobstacles");
		alertDialog.show();
		
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
	
	/**
	 * prompts the user to exit the application if the back button is pressed.
	 */
	@Override
	public void onBackPressed() {
		
		exitApplication();
		
	}		
	
		
}

