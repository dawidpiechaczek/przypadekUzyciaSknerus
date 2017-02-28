package com.example.david.przypadekuzyciatelefon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.david.przypadekuzyciatelefon.R;
import com.example.david.przypadekuzyciatelefon.api.RequestService;
import com.example.david.przypadekuzyciatelefon.fragment.OffersFragment;
import com.example.david.przypadekuzyciatelefon.model.Offer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Aktywność finalna, służąca do zatwierdzenia, bądź anulowania zlecenia klienta
 * Wykorzystuje wzorzec Obserwatora, gdzie ta aktywność jest emiterem, a obserwującym
 * -UserPanelActivity, a zarazem obserwującym dane z CurrencyExchangeActivity
 */

public class FinalActivity extends AppCompatActivity {
    protected Bundle mExtras;
    private Offer offer;
    private EditText buyCurrency, sellCurrency, rateCurrency, amountCurrency, valueCurrency;
    private Button btn_accept, btn_deny;

    /**
     * Funkcja cyklu życia aktywności, która startuje aktywność
     * @param savedInstanceState - przesyłanie w parametrze wiązki Bundle w której są dane do otrzymania
     *                           przez aktywność
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_layout);
        initComponents();
        getMyExtras();
        clickListenersOnButtons();
    }

    /**
     * Funkcja, która inicjuje wydarzenia na wszystkich klawiszach w aktywności
     */
    private void clickListenersOnButtons() {
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateStr = df.format(Calendar.getInstance().getTime());
                offer = new Offer();

                offer.setIdZlecenia(6);
                offer.setPesel(9849677);
                offer.setWalutaKupowana(buyCurrency.getText().toString());
                offer.setWalutaSprzedawana(sellCurrency.getText().toString());
                offer.setIloscKupionej(amountCurrency.getText().toString());
                offer.setDataZlecenia(dateStr);
                Toast.makeText(FinalActivity.this, "Trwa przetwarzanie zamówienia, proszę czekać", Toast.LENGTH_SHORT).show();
                initRetrofit();
                UserPanelActivity.upActivity.finish();
            }
        });

        btn_deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserPanelActivity.upActivity.finish();
                Intent intent = new Intent(getApplicationContext(), UserPanelActivity.class);
                intent.putExtra("WYNIK", "2");
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * Funkcja wykorzystująca wzorzec obserwatora, gdzie odbiera dane z CurrencyExchangeActivity
     * Jeśli takich dancyh brak, nic się nie dzieje
     */
    private void getMyExtras() {
        if (mExtras.getString(OffersFragment.WALUTA_KUPOWANA) != null) {
            buyCurrency.setText(mExtras.getString(OffersFragment.WALUTA_KUPOWANA));
        }
        if (mExtras.getString(OffersFragment.WALUTA_SPRZEDAWANA) != null) {
            sellCurrency.setText(mExtras.getString(OffersFragment.WALUTA_SPRZEDAWANA));
        }
        if (mExtras.getString(OffersFragment.ILOSC) != null) {
            amountCurrency.setText(mExtras.getString(OffersFragment.ILOSC));
        }
        if (mExtras.getString(OffersFragment.KURS) != null) {
            rateCurrency.setText(mExtras.getString(OffersFragment.KURS));
        }
        if (mExtras.getString(OffersFragment.WARTOSC) != null) {
            valueCurrency.setText(mExtras.getString(OffersFragment.WARTOSC));
        }
    }

    /**
     * Funkcja inicjująca wszystkie komponenty interfajsu danej aktywności, oraz inicjująca ew. instancje klas
     * w aktywności
     */
    private void initComponents() {
        mExtras = getIntent().getExtras();
        buyCurrency = (EditText) findViewById(R.id.editText2);
        sellCurrency = (EditText) findViewById(R.id.editText4);
        amountCurrency = (EditText) findViewById(R.id.editText3);
        rateCurrency = (EditText) findViewById(R.id.editText5);
        valueCurrency = (EditText) findViewById(R.id.editText6);
        btn_accept = (Button) findViewById(R.id.button2);
        btn_deny = (Button) findViewById(R.id.button5);
    }

    /**
     * Funkcja, która wysyła zlecenie do naszego serwera, tak aby umieścić zlecenie w panelu admina
     */
    private void initRetrofit() {
        RequestService.addOffer(new Callback<Offer>() {
            /**
             * Funkcja onResponse jest nieodłącznym elementem API, do któego wysyłam dane dotyczące zleceń,
             * zwraca informację o tym, czy transakcja powiodła się, czy nastąpił błąd w czasie wymiany danych
             * @param call - paramter, który przekazuje zapytanie do API
             * @param response - odpowiedź serwera, pozytywna lub negatywna - w razie negatywnej zwraca kod błędu
             */
            @Override
            public void onResponse(Call<Offer> call, Response<Offer> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), UserPanelActivity.class);
                    intent.putExtra("WYNIK", "1");
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(FinalActivity.this, "Strona serwera nie działa poprawnie", Toast.LENGTH_SHORT).show();
                }
            }

            /**
             * Funkcja onFailure jest implementowana przez mój interefejs ICallback, zwraca ona informację
             * na temat nieudanej próby połączenia z serwerem
             * @param call - parametr, który przekazuje zapytanie do API
             * @param t - parametr zwracjający błąd
             */
            @Override
            public void onFailure(Call<Offer> call, Throwable t) {
                Intent intent = new Intent(getApplicationContext(), UserPanelActivity.class);
                intent.putExtra("WYNIK", "2");
                startActivity(intent);
                Toast.makeText(FinalActivity.this, "Nastąpił błąd w połączeniu", Toast.LENGTH_SHORT).show();
                finish();
            }
        }, offer);
    }
}
