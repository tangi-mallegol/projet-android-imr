package fr.enssat.tangimallegol_mathieugouhier.geoquest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.InputStreamReader;

public class EditionIndice extends AppCompatActivity {

    EditText lng;
    EditText lat;
    EditText indice;
    EditText rayon;
    Button sauvegarder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //On initialise les différents champs
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition_indice);
        final float longitude = getIntent().getFloatExtra("longitude", 0);
        final float latitude = getIntent().getFloatExtra("latitude", 0);
        int rayon_ = getIntent().getIntExtra("rayon", -1);
        final String indice_ = getIntent().getStringExtra("indice");
        final int position = getIntent().getIntExtra("position", -1);
        lng = (EditText)findViewById(R.id.longitude);
        lng.setText(Double.toString(longitude));
        lat = (EditText)findViewById(R.id.latitude);
        lat.setText(Double.toString(latitude));
        indice = (EditText)findViewById(R.id.indice);
        indice.setText(indice_);
        rayon = (EditText)findViewById(R.id.rayon);
        rayon.setText(Integer.toString(rayon_));
        sauvegarder = (Button)findViewById(R.id.sauvegarder);
        final Activity that = this;
        sauvegarder.setOnClickListener(new View.OnClickListener() {
            @Override
            //On sauvegarde dans le cache les données
            public void onClick(View view) {
                SharedPreferences settings = getSharedPreferences("MyJSON", 0);
                JSON.Data data = JSON.parseString(settings.getString("json", ""));
                Indice e = new Indice();
                e.indice = indice.getText().toString();
                e.rayon = Integer.parseInt(rayon.getText().toString());
                e.loc = new float[2];
                e.loc[0] = Float.parseFloat(lng.getText().toString());
                e.loc[1] = Float.parseFloat(lat.getText().toString());
                if(position == -1){
                    data.path.add(e);
                } else {
                    data.path.set(position, e);
                }
                settings.edit().putString("json", JSON.toString(data)).apply();
                Intent intent = new Intent(that, EditionActivity.class);
                startActivity(intent);
            }
        });
    }
}
