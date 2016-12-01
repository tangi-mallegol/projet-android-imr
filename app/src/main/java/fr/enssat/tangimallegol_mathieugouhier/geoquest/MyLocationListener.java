package fr.enssat.tangimallegol_mathieugouhier.geoquest;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Debug;

import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;

/**
 * Created by tangimallegol on 22/11/2016.
 */
public class MyLocationListener implements LocationListener {
    IMapController controller;
    public MyLocationListener(IMapController controller){
        this.controller = controller;
    }

    @Override
    public void onLocationChanged(Location loc) {
        System.out.println("lel");
        GeoPoint startPoint = new GeoPoint(loc.getLatitude(), loc.getLongitude());
        controller.setCenter(startPoint);
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}
