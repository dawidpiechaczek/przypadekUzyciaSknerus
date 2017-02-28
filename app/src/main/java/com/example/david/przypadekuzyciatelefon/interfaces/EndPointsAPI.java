package com.example.david.przypadekuzyciatelefon.interfaces;

import com.example.david.przypadekuzyciatelefon.model.Example;
import com.example.david.przypadekuzyciatelefon.model.Offer;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Interfejs deklarujący istnieje metod, które służą do wymiany danych z API
 * W nim znajdują się adresy, pod które wysyłane są dane
 */

public interface EndPointsAPI {
    String ENDPOINT = "http://api.fixer.io";
    String BASE_URL = "http://192.168.43.64/api/";

    /**
     * Metoda GET, która prosi o dane API
     * @param filter parametry, po których chcemy sprecyzować otrzymywane dane - w tym przypadku jakie kusy chcemy otrzymać
     * @return zwraca obiekt z pobranymi danymi
     */
    @GET("/latest")
    Call<Example> getCurrency(@QueryMap Map<String,String> filter);

    /**
     * Metoda POST, która umieszcza dane w API
     * @param o jest to ciało metody, w którym umieszczam obiekt przeparsowany na JSON - aby serwer mógł go odebrać
     * @return zwraca ciało wysyłanego obiektu
     */
    @POST("zlecenies")
    Call<Offer>addOffer(@Body Offer o);

}
