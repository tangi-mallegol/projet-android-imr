package fr.enssat.tangimallegol_mathieugouhier.geoquest;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.OverlayItem;

import java.io.InputStreamReader;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //On initialise le carte
        org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants.setUserAgentValue(BuildConfig.APPLICATION_ID);
        MapView map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        map.setMaxZoomLevel(22);
        IMapController mapController = map.getController();
        mapController.setZoom(16);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //On récupère le textView pour afficher les indices
        TextView textView = (TextView)findViewById(R.id.indice);
        //On récupère l'imageView pour afficher la photo optionnelle pour les indices
        ImageView imageView = (ImageView)findViewById(R.id.photo);
        //On récupère le parcours
        SharedPreferences settings = getSharedPreferences("MyJSON", 0);
        String res = settings.getString("json", "");
        JSON.Data data = JSON.parseString(res);
        //Initialisation du LocationListener qui mettra à jour la position de l'utilisateur et modifiera les indices en fonction de cette position
        LocationListener locationListener = new MyLocationListener(mapController, data, this, textView, imageView);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if(loc != null) {
            GeoPoint startPoint = new GeoPoint(loc.getLongitude(), loc.getLatitude());
            mapController.setCenter(startPoint);
        }
    }

}
