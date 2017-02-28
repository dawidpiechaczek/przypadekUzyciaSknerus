package com.example.david.przypadekuzyciatelefon.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.david.przypadekuzyciatelefon.fragment.OffersFragment;
import com.example.david.przypadekuzyciatelefon.R;
import com.example.david.przypadekuzyciatelefon.api.RequestService;
import com.example.david.przypadekuzyciatelefon.model.Example;
import com.example.david.przypadekuzyciatelefon.model.Offer;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Aktywność służąca do wymiany walut oparta na wzorcu obserwatora
 */

public class CurrencyExchangeActivity extends AppCompatActivity{
    private Spinner currencySpinner1, currencySpinner2;
    private EditText currencyAmount;
    private Button button;
    private Example repo;

    /**
     * Funkcja cyklu życia aktywności, która startuje aktywność
     * @param savedInstanceState - przesyłanie w parametrze wiązki Bundle w której są dane do otrzymania
     *                           przez aktywność
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_exchange_activity);
        initComponents();
    }

    /**
     * Funkcja inicjująca wszystkie komponenty interfajsu danej aktywności, oraz inicjująca ew. instancje klas
     * w aktywności
     */
    private void initComponents() {
        currencyAmount=(EditText)findViewById(R.id.editText);
        currencySpinner1 =(Spinner)findViewById(R.id.spinner);
        currencySpinner2 = (Spinner)findViewById(R.id.spinner2);
        button =(Button)findViewById(R.id.button) ;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        currencySpinner1.setAdapter(adapter);
        currencySpinner2.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            /**
             * Funkcja dodająca wydarzenie do przycisku w interfejsie
             * @param view - widok, który pojawi się po kliknięciu w przycisk
             */
            @Override
            public void onClick(View view) {
                if(currencyAmount.getText().toString().length()<1){
                    Toast.makeText(CurrencyExchangeActivity.this,"Pole wyrażające ilość nie może być puste",Toast.LENGTH_SHORT).show();
                } else if(Double.parseDouble(currencyAmount.getText().toString())<2){
                    Toast.makeText(CurrencyExchangeActivity.this,"Kwota jest zbyt mała, transakacje powyżej 2.00 "+currencySpinner1.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                } else if(currencySpinner1.getSelectedItem().toString().equals(currencySpinner2.getSelectedItem().toString())){
                    Toast.makeText(CurrencyExchangeActivity.this,"Nie trzeba wymieniać ze sobą tych samych walut :)",Toast.LENGTH_SHORT).show();
                }else{
                    showFavourites();
                }
            }
        });
    }

    /**
     * Metoda odpowiadająca za pokazanie Fragmentu z kursami, które są najbardziej atrakcyjne dla klienta
     */
    private void showFavourites() {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag(OffersFragment.TAG_FRAGMENT);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        Map<String,String> searchFilters = new HashMap<>();
        searchFilters.put("base",currencySpinner1.getSelectedItem().toString().substring(0, 3));
        RequestService.getCurrency(new Callback<Example>() {
            /**
             * Funkcja onResponse jest nieodłącznym elementem API, z którego pobieram dane dotyczące kursów walut
             * zwraca informację o tym, czy transakcja powiodła się,czy był błąd w czasie wymiany danych
             * @param call - paramter, który przekazuje zapytanie do API
             * @param response - odpowiedź serwera, pozytywna lub negatywna - w razie negatywnej zwraca kod błędu
             */
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                repo = response.body();
                OffersFragment favouritesDialogFragment = OffersFragment.newInstance(Double.parseDouble(currencyAmount.getText().toString()),(double)Math.round(repo.getRates(currencySpinner2.getSelectedItem().toString().substring(0, 3))*1000)/1000,currencySpinner1.getSelectedItem().toString().substring(0, 3),currencySpinner2.getSelectedItem().toString().substring(0, 3));
                favouritesDialogFragment.show(ft, OffersFragment.TAG_FRAGMENT);
            }

            /**
             * Funkcja onFailure jest implementowana przez mój interefejs ICallback, zwraca ona informację
             * na temat nieudanej próby połączenia z serwerem
             * @param call - parametr, który przekazuje zapytanie do API
             * @param t - parametr zwracjający błąd
             */
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("LogBledu","Blad");
            }
        },searchFilters);

    }
}
