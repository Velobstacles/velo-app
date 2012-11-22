package mtl.hackathon.velobstacles;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.app.Dialog; 
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface.*;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;

public class ReportObstacle extends Activity implements OnClickListener {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_obstacle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.report_obstacle, menu);
        return true;
    }
    
    @Override
    protected Dialog onCreateDialog(int id) {
          return new AlertDialog.Builder(this)
              .setTitle("Select One Digit")
             // .setMultiChoiceItems(R.string.r_category, null, new OnMultiChoiceClickListener())
              // .setPositiveButton("OK", new OnClickListener())
              .create();
    }

	public void onClick(DialogInterface arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
      
    
    
}
	