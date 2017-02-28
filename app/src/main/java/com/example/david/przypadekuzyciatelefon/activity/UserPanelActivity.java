package com.example.david.przypadekuzyciatelefon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.david.przypadekuzyciatelefon.R;
import com.example.david.przypadekuzyciatelefon.adapter.UserPanelAdapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Aktywność używająca wzorca obserwatora, prezentująca panel użytkownika, w którym można dokonywać
 * różnych operacji dostępnych dla zalogowanych użytkowników
 */

public class UserPanelActivity extends AppCompatActivity {
    public static UserPanelActivity upActivity;
    private TextView tv_success, tv_fail;
    private Bundle mExtras;
    private ListView list;

    /**
     * Funkcja cyklu życia aktywności, która startuje aktywność
     * @param savedInstanceState - przesyłanie w parametrze wiązki Bundle w której są dane do otrzymania
     *                           przez aktywność
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_panel_activity);
        initComponents();
        getMyExtras();
        createAdapter();
    }

    /**
     * Funkcja tworząca adpater - jest to obiekt, który nadaje wygląd ListView - czyli listy operacji
     * dostepnej dla użytkownika
     */
    private void createAdapter() {
        String cars[] = {"Wymień walutę", "Portfel walutowy", "Historia operacji", "Ustawienia"};
        Integer ints[] = {R.drawable.firstlist, R.drawable.walletlist, R.drawable.booklist, R.drawable.settingslist};

        ArrayList<String> carL = new ArrayList<String>();
        carL.addAll(Arrays.asList(cars));

        ArrayList<Integer> intL = new ArrayList<>();
        intL.addAll(Arrays.asList(ints));

        UserPanelAdapter mAdapterObj = new UserPanelAdapter(getApplicationContext(), carL, intL);
        list.setAdapter(mAdapterObj);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Funkcja, która określa zachowania adaptera po naciśnięciu w wybrany element listy
             * @param adapterView - parametr daje dostęp do całej listy
             * @param view - parametr zwracjący wygląd listy
             * @param i - parametr zwracjący numer indeksu klikniętej operacji
             * @param l
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent intent = new Intent(UserPanelActivity.this, CurrencyExchangeActivity.class);
                        startActivity(intent);
                }
            }
        });
    }

    /**
     * Funkcja wykorzystująca wzorzec obserwatora, przyjmująca dane z aktywności FinalActivity
     */
    private void getMyExtras() {
        if (mExtras.getString("WYNIK") != null) {
            if(mExtras.get("WYNIK").equals("0")){
                tv_fail.setVisibility(View.GONE);
                tv_success.setVisibility(View.GONE);
            }else if(mExtras.get("WYNIK").equals("1")){
                tv_fail.setVisibility(View.GONE);
                tv_success.setVisibility(View.VISIBLE);
            }else{
                tv_fail.setVisibility(View.VISIBLE);
                tv_success.setVisibility(View.GONE);
            }
        }
    }

    /**
     * Funkcja inicjująca wszystkie komponenty interfajsu danej aktywności, oraz inicjująca ew. instancje klas
     * w aktywności
     */
    private void initComponents() {
        mExtras = getIntent().getExtras();
        upActivity = this;
        tv_fail = (TextView) findViewById(R.id.textView9);
        tv_success = (TextView) findViewById(R.id.textView20);
        list = (ListView) findViewById(R.id.lv_user_panel);
    }


}
