package fr.enssat.tangimallegol_mathieugouhier.geoquest;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Debug;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;

import java.io.InputStreamReader;

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
    private ImageView imageView;

    protected MyLocationListener(IMapController controller, JSON.Data data, Activity activity, TextView textView, ImageView imageView){
        this.controller = controller;
        this.data = data;
        this.index_place = 0;
        this.activity = activity;
        this.end = false;
        this.textView = textView;
        this.textView.setText(data.path[index_place].indice);
        this.imageView = imageView;
    }

    @Override
    public void onLocationChanged(Location loc) {
        double lat = loc.getLatitude();
        double lng = loc.getLongitude();
        GeoPoint startPoint = new GeoPoint(lat, lng);
        controller.setCenter(startPoint);
        //Si tous les points n'ont pas été trouvés
        if(!end){
            GeoPoint pointRecherche = new GeoPoint(data.path[index_place].loc[0], data.path[index_place].loc[1]);
            //Si l'utilisateur se trouve à moins de "rayon" du point recherché
            if(startPoint.distanceTo(pointRecherche) < data.path[index_place].rayon){
                //On passe à l'indice suivant
                index_place ++;
                if(index_place == data.path.length){
                    //Si il ne reste plus d'indice, on va indiquer que l'aventure est terminée
                    end = true;
                    Toast.makeText(activity, "Bravo ! Vous avez terminé le parcours !", Toast.LENGTH_LONG).show();
                } else {
                    //Sinon on met à jour les variables
                    Toast.makeText(activity, "Bravo ! Vous avez trouvé le nouvel endroit. La suite de l'aventure peut continuer ...", Toast.LENGTH_LONG).show();
                    this.textView.setText(data.path[index_place].indice);
                    if(data.path[index_place].imgSrc != ""){
                        //this.imageView.setImageResource(new InputStreamReader(activity.getResources().getIdentifier() .openRawResource(path[index_place].indice)));
                    }
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
