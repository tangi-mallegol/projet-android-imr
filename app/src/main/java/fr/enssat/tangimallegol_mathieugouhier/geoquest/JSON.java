package fr.enssat.tangimallegol_mathieugouhier.geoquest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.StringReader;

/**
 * Created by matgo on 01/12/2016.
 */

public class JSON {

    class Indice {

    }

    class Data {
        @SerializedName("titre")
        String title;

        @SerializedName("trajets")
        Indice[] path;
    }

    public static void parse() {
        String jsonLine = "{"+
                "\"titre\" : \"parcours1\","+
                "\"trajets\" : [{"+
            "\"indice\" : \"Allez sous la statue\","+
                    "\"image_src\" : \"photo.jpg\","+
                    "\"loc\" : {"+
                "\"geoJSON\" : \"GEOJSON\","+
                        "\"rayon\" : 5"+
            "}"+
        "}"+
        "]"+
        "}";

        Gson gson = new Gson();
        Data data = gson.fromJson(new StringReader(jsonLine), Data.class);



        JsonElement jelement = new JsonParser().parse(jsonLine);
        JsonObject jobject = jelement.getAsJsonObject();



        //jobject = jobject.getAsJsonObject("titre");

        /*
        JsonArray jarray = jobject.getAsJsonArray("trajets");
        jobject = jarray.get(0).getAsJsonObject();*/

        String result = jobject.get("titre").toString();

        System.out.println(result);
    }

}
