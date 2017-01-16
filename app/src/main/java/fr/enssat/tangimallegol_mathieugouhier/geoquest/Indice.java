package fr.enssat.tangimallegol_mathieugouhier.geoquest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TANGI on 16/01/2017.
 */

public class Indice {

    public Indice(){

    }
    @SerializedName("indice")
    String indice;

    @SerializedName("image_src")
    String imgSrc;

    @SerializedName("loc")
    float[] loc;

    @SerializedName("rayon")
    int rayon;

    @Override
    public String toString() {
        return indice;
    }
}
