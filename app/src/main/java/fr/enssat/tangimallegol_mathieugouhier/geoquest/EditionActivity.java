package fr.enssat.tangimallegol_mathieugouhier.geoquest;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.io.InputStreamReader;

public class EditionActivity  extends AppCompatActivity {
    JSON.Data data;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edition, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);
        ListView listView = (ListView)findViewById(android.R.id.list);
        SharedPreferences settings = getSharedPreferences("MyJSON", 0);
        data = JSON.parseString(settings.getString("json", ""));
        //On lie les données à la ListView
        ArrayAdapter<Indice> simpleCursorAdapter = new ArrayAdapter<Indice>(this, android.R.layout.simple_list_item_1, data.path);
        listView.setAdapter(simpleCursorAdapter);
        final Activity that = this;
        //On défini le listener lorsqu'on clique sur un objet existant
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(that, EditionIndice.class);
                System.out.println(data.path.get(i).loc[0]);
                intent.putExtra("longitude", data.path.get(i).loc[0]);
                intent.putExtra("latitude", data.path.get(i).loc[1]);
                intent.putExtra("rayon", data.path.get(i).rayon);
                intent.putExtra("indice", data.path.get(i).indice);
                intent.putExtra("position", i);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Evenement déclenché lorsqu'on clique dans le menu et qu'on ajoute
        if (item.getItemId() == R.id.add_indice) {
            Intent intent = new Intent(this, EditionIndice.class);
            startActivity(intent);
        }
        return true;
    }

}
