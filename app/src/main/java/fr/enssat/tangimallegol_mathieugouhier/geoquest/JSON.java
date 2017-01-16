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
import java.util.ArrayList;

/**
 * Created by matgo on 01/12/2016.
 */

public class JSON {

    public class Data {
        @SerializedName("titre")
        String title;

        @SerializedName("trajets")
        ArrayList<Indice> path;
    }

    public static Data parse(Reader reader) {
        Gson gson = new Gson();
        Data data = gson.fromJson(reader, Data.class);
        System.out.println(data.title);
        for (Indice i : data.path) {
            System.out.println(i.indice);
        }
        return data;
    }

    public static Data parseString(String s){
        Gson gson = new Gson();
        Data data = gson.fromJson(s, Data.class);
        for (Indice i : data.path) {
            System.out.println(i.indice);
        }
        return data;
    }

    public static String toString(Data data){
        Gson gson = new Gson();
        return gson.toJson(data);
    }

}
