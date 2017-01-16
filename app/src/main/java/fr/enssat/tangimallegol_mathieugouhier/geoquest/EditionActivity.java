package fr.enssat.tangimallegol_mathieugouhier.geoquest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.io.InputStreamReader;

public class EditionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);
        ListView listView = (ListView)findViewById(R.id.liste_indice);
        JSON.Data data = JSON.parse(new InputStreamReader(getResources().openRawResource(R.raw.road)));
        ArrayAdapter<JSON.Indice> simpleCursorAdapter = new ArrayAdapter<JSON.Indice>(this, R.layout.edition_indice, data.path);
    }
}
