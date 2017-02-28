package com.example.david.przypadekuzyciatelefon.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Klasa reprezentująca zlecenie, które wysyła użytkownik do serwera API
 */

public class Offer {
    @SerializedName("IdZlecenia")
    @Expose
    private int idZlecenia;
    @SerializedName("Pesel")
    @Expose
    @Nullable
    private int pesel;
    @SerializedName("WalutaKupowana")
    @Expose
    private String walutaKupowana;
    @SerializedName("WalutaSprzedawana")
    @Expose
    private String walutaSprzedawana;
    @SerializedName("IloscKupionej")
    @Expose
    private String iloscKupionej;
    @SerializedName("DataZlecenia")
    @Expose
    private String dataZlecenia;

    /**
     * Metoda dająca numer ID zlecenia
     * @return zwraca numer ID zlecenia w formacie liczby całkowitej
     */
    public Integer getIdZlecenia() {
        return idZlecenia;
    }

    /**
     * Metoda pozwalająca na ustawienie ID zlecenia
     * @param idZlecenia parametr, który przedtsawia ID zlecenia w formacie liczby całkowitej
     */
    public void setIdZlecenia(Integer idZlecenia) {
        this.idZlecenia = idZlecenia;
    }

    /**
     * Metoda dająca numer PESEL użytkownika
     * @return zwraca numer PESEL użytkownika w formacie liczby całkowietj
     */
    public Integer getPesel() {
        return pesel;
    }

    /**
     * Metoda pozwalajaca ustawić numer PESEL
     * @param pesel parametr, który jest PESELem w formacie liczby całkowitej
     */
    public void setPesel(Integer pesel) {
        this.pesel = pesel;
    }

    /**
     * Metoda zwracająca walutę jaką chce zakupić użytkownik
     * @return zwraca 3literowy ciąg znaków oznacząjący walutę
     */
    public String getWalutaKupowana() {
        return walutaKupowana;
    }

    /**
     * Metoda, która pozwala na ustawienie waluty, jaką chce kupić użytkownik
     * @param walutaKupowana parametr w postaci 3literowego ciągu znaków oznacząjący walutę
     */
    public void setWalutaKupowana(String walutaKupowana) {
        this.walutaKupowana = walutaKupowana;
    }
    /**
     * Metoda zwracająca walutę jaką chce sprzedać użytkownik
     * @return zwraca 3literowy ciąg znaków oznacząjący walutę
     */
    public String getWalutaSprzedawana() {
        return walutaSprzedawana;
    }

    /**
     * Metoda, która pozwala na ustawienie waluty, jaką chce kupić użytkownik
     * @param walutaSprzedawana parametr w postaci 3literowego ciągu znaków oznacząjący walutę
     */
    public void setWalutaSprzedawana(String walutaSprzedawana) {
        this.walutaSprzedawana = walutaSprzedawana;
    }

    /**
     * Metoda, która zwraca ilość kupionej waluty przez użytkownika
     * @return zwraca liczbę w postaci ciągu znaków oznaczający ilość zakupionej waluty
     */
    public String getIloscKupionej() {
        return iloscKupionej;
    }

    /**
     * Metoda, która pozwala na ustawienie ilośc kupionej waluty przez użytkownika
     * @param iloscKupionej parametr przedstawiający ilość zakupionej waluty przez użytkownika
     */
    public void setIloscKupionej(String iloscKupionej) {
        this.iloscKupionej = iloscKupionej;
    }

    /**
     * Metoda, która zwraca datę dokonananego zlecenia
     * @return  zwraca datę zlecenia w formacie ciągu znaków [YYYY-MM-DD HH:MM:SS]
     */
    public String getDataZlecenia() {
        return dataZlecenia;
    }

    /**
     * Metoda, która pozwala na ustawienie daty dokonanego zlecenia
     * @param dataZlecenia parametr, który jest ciągiem znaków reprezentującym datę zlecenia w formacie [YYYY-MM-DD HH:MM:SS]
     */
    public void setDataZlecenia(String dataZlecenia) {
        this.dataZlecenia = dataZlecenia;
    }
}
