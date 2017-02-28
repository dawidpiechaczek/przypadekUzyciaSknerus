package com.example.david.przypadekuzyciatelefon.api;

import com.example.david.przypadekuzyciatelefon.interfaces.EndPointsAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Klasa odpowiedzialna za wysyłkę danych do API
 */

public class PostServiceGenerator {
    /**
     * Tworzenie obiektu przetwarzającego format wysyłany przez androida na JSON
     */
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

    /**
     * Zadeklarowanie obiektu budującego zapytanie do API
     */
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    /**
     * Utworzenie adresu pod jaki będzie wysyłane zapytanie
     */
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(EndPointsAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson));

    /**
     * Funkacja, która  ma za zadanie uruchomić wymianę danych z API
     * @param serviceClass klasa, która jest dostosowana do odbioru danych
     * @param <S> typ generyczny, który precyzuje moją klasę odbierającą dane z API
     * @return zwraca utworzony serwis, który jest gotowy do wymiany danych z API
     */
    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

}