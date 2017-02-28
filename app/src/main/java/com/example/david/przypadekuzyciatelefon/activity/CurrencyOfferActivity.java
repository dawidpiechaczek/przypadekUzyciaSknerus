package com.example.david.przypadekuzyciatelefon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.przypadekuzyciatelefon.R;
import com.example.david.przypadekuzyciatelefon.api.RequestService;
import com.example.david.przypadekuzyciatelefon.model.Example;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Aktywność ponadprogramowa, prezentująca kursy walut dla niezalogowanych użytkowników
 */

public class CurrencyOfferActivity extends AppCompatActivity {
    private Button button;
    private Example repo;
    private TextView tvBuyPLNGBR,tvBuyPLNUSD,tvBuyPLNJPN,tvBuyPLNEUR,tvBuyPLNRUS,tvSellPLNRUS,tvSellPLNGBR,tvSellPLNEUR,tvSellPLNJPN,tvSellPLNUSD;

    /**
     * Funkcja cyklu życia aktywności, która startuje aktywność
     * @param savedInstanceState - przesyłanie w parametrze wiązki Bundle w której są dane do otrzymania
     *                           przez aktywność
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_offer_activity);
        initComponents();
        initRetrofit();
    }

    /**
     * Funkcja inicjująca komunikację z API (w argumencie funkcji put wyznaczam dla jakiej waluty mają być to kursy -
     * - w wersji dla niezalogowanych jest to jedynie waluta polska PLN)
     */
    private void initRetrofit() {
        Map<String,String> searchFilters = new HashMap<>();
        searchFilters.put("base","PLN");

        RequestService.getCurrency(new Callback<Example>() {
            /**
             * Funkcja onResponse jest nieodłącznym elementem API, z którego pobieram dane dotyczące kursów walut
             * zwraca informację o tym, czy transakcja powiodła się,czy był błąd w czasie wymiany danych
             * @param call - paramter, który przekazuje zapytanie do API
             * @param response - odpowiedź serwera, pozytywna lub negatywna - w razie negatywnej zwraca kod błędu
             */
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()) {
                    repo = response.body();
                    final DecimalFormat df = new DecimalFormat("#.####");
                    final double addition = 0.0213;
                    tvBuyPLNGBR.setText(df.format(1/(repo.getRates().getGBP())));
                    tvBuyPLNEUR.setText(df.format(1/(repo.getRates().getEUR())));
                    tvBuyPLNUSD.setText(df.format(1/(repo.getRates().getUSD())));
                    tvBuyPLNJPN.setText(df.format(1/(repo.getRates().getJPY())));
                    tvBuyPLNRUS.setText(df.format(1/(repo.getRates().getRUB())));

                    tvSellPLNEUR.setText(df.format(1/(repo.getRates().getEUR())+addition));
                    tvSellPLNUSD.setText(df.format(1/(repo.getRates().getUSD())+addition));
                    tvSellPLNGBR.setText(df.format(1/(repo.getRates().getGBP())+addition));
                    tvSellPLNJPN.setText(df.format(1/(repo.getRates().getJPY())+addition));
                    tvSellPLNRUS.setText(df.format(1/(repo.getRates().getRUB())+addition));
                } else
                {
                    Toast.makeText(CurrencyOfferActivity.this, "Error code " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            /**
             * Funkcja onFailure jest implementowana przez mój interefejs ICallback, zwraca ona infomrację
             * na temat nie udanej próby połączenia z serwerem
             * @param call - parametr, który przekazuje zapytanie do API
             * @param t - parametr zwracjający błąd
             */
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(CurrencyOfferActivity.this, "Did not work " +  t.getMessage(), Toast.LENGTH_SHORT).show();
            }}, searchFilters);}

    /**
     * Funkcja inicjująca wszystkie komponenty interfajsu danej aktywności, oraz inicjująca ew. instancje klas
     * w aktywności
     */
    private void initComponents(){
        tvBuyPLNEUR = (TextView)findViewById(R.id.tv_buy_plneur);
        tvBuyPLNGBR = (TextView)findViewById(R.id.tv_buy_plngbr);
        tvBuyPLNJPN = (TextView)findViewById(R.id.tv_buy_plnjpn);
        tvBuyPLNRUS = (TextView)findViewById(R.id.tv_buy_plnrus);
        tvBuyPLNUSD = (TextView)findViewById(R.id.tv_buy_plnusd);
        tvSellPLNEUR = (TextView)findViewById(R.id.tv_sell_plneur);
        tvSellPLNJPN = (TextView)findViewById(R.id.tv_sell_plnjpn);
        tvSellPLNGBR = (TextView)findViewById(R.id.tv_sell_plngbr);
        tvSellPLNUSD = (TextView)findViewById(R.id.tv_sell_plnusd);
        tvSellPLNRUS = (TextView)findViewById(R.id.tv_sell_plnrus);
        button = (Button)findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * Funkcja dodająca wydarzenie do przycisku w interfejsie
             * @param view - widok, który pojawi się po kliknięciu w przycisk
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
