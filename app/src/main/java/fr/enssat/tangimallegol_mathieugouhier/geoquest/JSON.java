package fr.enssat.tangimallegol_mathieugouhier.geoquest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Array;

/**
 * Created by matgo on 01/12/2016.
 */

public class JSON {

    class Indice {
        @SerializedName("indice")
        String indice;

        @SerializedName("image_src")
        String imgSrc;

        @SerializedName("loc")
        float[] loc;

        @SerializedName("rayon")
        int rayon;
    }

    public class Data {
        @SerializedName("titre")
        String title;

        @SerializedName("trajets")
        Indice[] path;
    }

    public static Data parse(Reader reader) {
        Gson gson = new Gson();
        Data data = gson.fromJson(reader, Data.class);
        for (Indice i : data.path) {
            System.out.println(i.indice);
        }
        return data;
    }

}
