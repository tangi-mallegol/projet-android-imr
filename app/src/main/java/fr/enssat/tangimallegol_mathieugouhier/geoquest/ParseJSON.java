package fr.enssat.tangimallegol_mathieugouhier.geoquest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by matgo on 01/12/2016.
 */

public class ParseJSON {



    public static void parse() {
        String jsonLine = "{"+
            "\"data\": {"+
            "\"translations\": ["+
            "{"+
                "\"translatedText\": \"Hello world\""+
            "}"+
            "]"+
        "}"+
        "}";

        JsonElement jelement = new JsonParser().parse(jsonLine);
        JsonObject jobject = jelement.getAsJsonObject();

        jobject = jobject.getAsJsonObject("data");
        JsonArray jarray = jobject.getAsJsonArray("translations");

        jobject = jarray.get(0).getAsJsonObject();
        String result = jobject.get("translatedText").toString();

        System.out.println(result);
    }
}
