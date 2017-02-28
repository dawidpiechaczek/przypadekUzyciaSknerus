package com.example.david.przypadekuzyciatelefon.model;

/**
 * Klasa służaca do przedstawiania oferty atrakcyjnych kursów
 */

public class Promotion {
    double rate;
    double amount;
    double value;

    /**
     * Konstruktor klasy przyjmujący pramatery, aby utworzyć dany obiekt
     * @param kurs parametr przyjmujący wysokość kursu danej oferty
     * @param ilosc parametr ilości waluty jaką chce kupić użytkownik
     * @param wartosc parametr wartości całego zamówienia
     */
    public Promotion(double kurs, double ilosc, double wartosc){
        this.rate = kurs;
        this.amount = ilosc;
        this.value = wartosc;
    }

    /**
     * Metoda zwracająca wysokośc kursu po jakiej walutę kupuję użytkownik
     * @return zwraca kurs w postaci liczby zmiennoprzecinkowej
     */
    public double getKurs() {
        return rate;
    }

    /**
     * Metoda pozwalająca na ustwienie wysokości kursu, po jakiej kupuje zlecenie użytkownik
     * @param kurs parametr przedtsawiający wysokość kursu w postaci zmiennoprzecinkowej
     */
    public void setKurs(double kurs) {
        this.rate = kurs;
    }
    /**
     * Metoda, która zwraca ilość kupionej waluty przez użytkownika
     * @return zwraca liczbę w postaci liczby zmiennoprzecinkowej oznaczający ilość zakupionej waluty
     */
    public double getIlosc() {
        return amount;
    }
    /**
     * Metoda, która pozwala na ustawienie ilośc kupionej waluty przez użytkownika
     * @param ilosc parametr przedstawiający ilość zakupionej waluty przez użytkownika
     */
    public void setIlosc(double ilosc) {
        this.amount = ilosc;
    }

    /**
     * Metoda, która zwraca wartość złożonego zlecenia
     * @return zwraca wartość zlecenia w postaci zmiennoprzecinkowej
     */
    public double getWartosc() {
        return value;
    }

    /**
     * Metoda, która pozwala na ustaiwnie wysokości wartości danego zlecenia
     * @param wartosc parametr przezntujący wartość zlecenia w postaci zmiennoprzecinkowej
     */
    public void setWartosc(double wartosc) {
        this.value = wartosc;
    }
}
