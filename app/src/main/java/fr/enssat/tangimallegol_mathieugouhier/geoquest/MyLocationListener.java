package fr.enssat.tangimallegol_mathieugouhier.geoquest;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Debug;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;

/**
 * Created by tangimallegol on 22/11/2016.
 */
public class MyLocationListener implements LocationListener {
    private IMapController controller;
    private JSON.Data data;
    private int index_place;
    private boolean end;
    private Activity activity;
    private TextView textView;

    protected MyLocationListener(IMapController controller, JSON.Data data, Activity activity, TextView textView){
        this.controller = controller;
        this.data = data;
        this.index_place = 0;
        this.activity = activity;
        this.end = false;
        this.textView = textView;
        this.textView.setText(data.path[index_place].indice);
    }

    @Override
    public void onLocationChanged(Location loc) {
        double lat = loc.getLatitude();
        double lng = loc.getLongitude();
        GeoPoint startPoint = new GeoPoint(loc.getLatitude(), loc.getLongitude());
        controller.setCenter(startPoint);
        if(!end){
            GeoPoint pointRecherche = new GeoPoint(data.path[index_place].loc[0], data.path[index_place].loc[1]);
            if(startPoint.distanceTo(pointRecherche) < data.path[index_place].rayon){
                index_place ++;
                if(index_place == data.path.length){
                    end = true;
                    Toast.makeText(activity, "Bravo ! Vous avez terminé le parcours !", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(activity, "Bravo ! Vous avez trouvé le nouvel endroit. La suite de l'aventure peut continuer ...", Toast.LENGTH_LONG).show();
                    this.textView.setText(data.path[index_place].indice);
                }
            }
        }
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}
