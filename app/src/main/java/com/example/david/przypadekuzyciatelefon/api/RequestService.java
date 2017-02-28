package com.example.david.przypadekuzyciatelefon.api;

import com.example.david.przypadekuzyciatelefon.interfaces.EndPointsAPI;
import com.example.david.przypadekuzyciatelefon.model.Example;
import com.example.david.przypadekuzyciatelefon.model.Offer;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Klasa wykorzystująca wzorec fasady- służy tylko do wywoływania metod serwisowych
 */

public class RequestService {
    /**
     * Metoda, która wysyła zalecenie do API
     * @param callback zapytanie, które jest wysyłane na serwer
     * @param offer obiekt, który jest zlecenim, wysyałane w zapytaniu
     */
    public static void addOffer(Callback<Offer> callback, Offer offer) {
        EndPointsAPI service = PostServiceGenerator.createService(EndPointsAPI.class);
        Call<Offer> call = service.addOffer(offer);
        call.enqueue(callback);
    }

    /**
     * Metoda, która odbiera dane kursów z API
     * @param callback zapytanie, które jest wysyłane na serwer
     * @param currencyAdress obiekt, który pozwala na identyfikację kursów jakie chcę otrzymać do aplikacji
     */
    public static void getCurrency(Callback<Example> callback, Map currencyAdress) {
        EndPointsAPI service = GetServiceGenerator.createService(EndPointsAPI.class);
        Call<Example> call = service.getCurrency(currencyAdress);
        call.enqueue(callback);
    }
}
