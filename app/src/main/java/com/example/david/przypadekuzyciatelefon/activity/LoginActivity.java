package com.example.david.przypadekuzyciatelefon.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.david.przypadekuzyciatelefon.R;
import com.example.david.przypadekuzyciatelefon.activity.CurrencyOfferActivity;
import com.example.david.przypadekuzyciatelefon.activity.UserPanelActivity;

/**
 * Aktywność startowa, z której można się zalogować do serwisu
 */
public class LoginActivity extends FragmentActivity {
    private Button buttonOffer;
    private Button buttonLogin;

    /**
     * Funkcja cyklu życia aktywności, która startuje aktywność
     * @param savedInstanceState - przesyłanie w parametrze wiązki Bundle w której są dane do otrzymania
     *                           przez aktywność
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        buttonOffer = (Button)findViewById(R.id.btn_offer);
        buttonOffer.setOnClickListener(new View.OnClickListener() {
            /**
             * Funkcja dodająca wydarzenie do przycisku w interfejsie
             * @param view - widok, który pojawi się po kliknięciu w przycisk
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CurrencyOfferActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin = (Button)findViewById(R.id.btn_login_2);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            /**
             * Funkcja dodająca wydarzenie do przycisku w interfejsie
             * @param view - widok, który pojawi się po kliknięciu w przycisk
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UserPanelActivity.class);
                intent.putExtra("WYNIK","0");
                startActivity(intent);
            }
        });
    }
}