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
        
        Button main_upload = (Button) findViewById(R.id.upload_media);
        Button main_viewmap = (Button) findViewById(R.id.view_map);
        Button main_services = (Button) findViewById(R.id.services);
        
        main_upload.setOnClickListener(this);
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
		switch(v.getId()) { 
	
		
			case R.id.upload_media:
				
				Intent pictureActivity = new Intent(this, ServerUpload.class);
				startActivity(pictureActivity);
				break;
				
			case R.id.view_map:
				
				break;
				
			case R.id.services:
				
				break;
				
		}	
			
		
	}
}
