package com.example.david.przypadekuzyciatelefon.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Obiekt DTO, który jest pobierany z API i serializowany
 */

public class Example {

    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("rates")
    @Expose
    private Rates rates;

    /**
     * Metoda zwracająca ciąg znaków reprezentujący bazową walutę
     * @return zwraca walutę, do której porównujemy kursy
     */
    public String getBase() {
        return base;
    }

    /**
     * Metoda ustawiająca ciąg znaków reprezentujący bazową walutę
     * @param base ciąg znaków reprezentujący walutę, którą chcę ustawić jako bazową
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     * Metoda zwracająca ciąg znaków w postaci daty
     * @return zwraca datę, w której dokonano zmiany kursów
     */
    public String getDate() {
        return date;
    }

    /**
     * Metoda zpozwalająca na ustawienie daty
     * @param date parametr w postaci ciągu znaków reprezentujący datę
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Metoda zwracająca kursy walut dotyczące waluty bazowej
     * @return zwraca kursy walut dotyczące wybranej waluty bazowej
     */
    public Rates getRates() {
        return rates;
    }

    /**
     * Metoda pozwalająca na ustawienie kursów dotyczących waluty bazowej
     * @param rates parametr będący kursami waluty bazowej
     */
    public void setRates(Rates rates) {
        this.rates = rates;
    }

    /**
     * Metoda zwracająca metodę danego kursu
     * @param rate kurs, do którego metodę chcemy uruchomić
     * @return zwraca metodę, która wykona się tylko dla żądanego kursu
     */
    public Double getRates(String rate){

        switch(rate){
            case "AUD": return rates.getAUD();
            case "BGN": return rates.getBGN();
            case "BRL": return rates.getBRL();
            case "CAD": return rates.getCAD();
            case "CHF": return rates.getCHF();
            case "CNY": return rates.getCNY();
            case "DKK": return rates.getDKK();
            case "EUR": return rates.getEUR();
            case "GBP": return rates.getGBP();
            case "HKD": return rates.getHKD();
            case "HRK": return rates.getHRK();
            case "HUF": return rates.getHUF();
            case "IDR": return rates.getIDR();
            case "CZK": return rates.getCZK();
            case "ILS": return rates.getILS();
            case "INR": return rates.getINR();
            case "JPY": return rates.getJPY();
            case "KRW": return rates.getKRW();
            case "MXN": return rates.getMXN();
            case "NOK": return rates.getNOK();
            case "NZD": return rates.getNZD();
            case "PHP": return rates.getPHP();
            case "PLN": return rates.getPLN();
            case "MYR": return rates.getMYR();
            case "RON": return rates.getRON();
            case "RUB": return rates.getRUB();
            case "SEK": return rates.getSEK();
            case "SGD": return rates.getSGD();
            case "THB": return rates.getTHB();
            case "TRY": return rates.getTRY();
            case "USD": return rates.getUSD();
            case "ZAR": return rates.getZAR();
        }
        return 1.0;
    }

}