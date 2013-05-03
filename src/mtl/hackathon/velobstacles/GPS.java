package mtl.hackathon.velobstacles;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
 
public final class GPS implements LocationListener {

	private static GPS _gps;
	private double _latitude;
	private double _longitude;

	static {
		_gps = null;
	} {
		_latitude = 0.0;
		_longitude = 0.0;
	}
	
	private GPS() {

	}
	
	public static GPS getGPSInstance() {
		return _gps == null ? new GPS() : _gps;
	}
	
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		_latitude = location.getLatitude();
		_longitude = location.getLongitude();
		
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}