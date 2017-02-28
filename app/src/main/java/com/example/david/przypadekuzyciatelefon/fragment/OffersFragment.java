package com.example.david.przypadekuzyciatelefon.fragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.david.przypadekuzyciatelefon.activity.FinalActivity;
import com.example.david.przypadekuzyciatelefon.adapter.ObjectListAdapter;
import com.example.david.przypadekuzyciatelefon.R;
import com.example.david.przypadekuzyciatelefon.model.Promotion;

import java.util.ArrayList;

/**
 * Fragment prezentujący okazyjne kursy proponowane użytkownikowi
 */

public class OffersFragment extends DialogFragment {

    public static final String TAG_FRAGMENT = OffersFragment.class.getName();
    public static final String WALUTA_KUPOWANA = "kupno";
    public static final String WALUTA_SPRZEDAWANA = "sprzedaz";
    public static final String KURS = "kurs";
    public static final String ILOSC = "ilosc";
    public static final String WARTOSC = "wartosc";
    private static ArrayList<Promotion> list;

    private Context mContext;
    private ListView mLvObjects;
    static String kupno, sprzedaz;

    /**
     * Funkcja, która daje instancję fragmentu - bezpieczniejsze rozwiązanie od przekazywania parametrów przez konstruktor
     * ze względu na brak możliwości wycieku dancyh
     * @param ilosc liczba zmiennoprzecinkowa, któa przekazuje ilość zakupionej waluty
     * @param kurs liczba zmiennoprzecinkowa, która przekazuje wysokość kursu, po jakim waluta jest kupowana
     * @param walutakupno ciąg znaków, który przekzauje walutę która jest kupowana
     * @param walutasprzedaz ciąg znaków, który przekazuje walutę za którą się kupuje
     * @return zwraca instancję fragmentu
     */
    public static OffersFragment newInstance(double ilosc, double kurs, String walutakupno, String walutasprzedaz) {
        list = new ArrayList<>();
        list.add(new Promotion(kurs,ilosc,(double)Math.round(ilosc*kurs * 100) / 100));
        list.add(new Promotion(((double)Math.round((kurs+0.07)*1000)/1000),2000,(double)Math.round(((double)Math.round((kurs+0.07)*1000)/1000)*2000*100)/100));
        list.add(new Promotion(((double)Math.round((kurs+0.18)*1000)/1000),5000,(double)Math.round(((double)Math.round((kurs+0.18)*1000)/1000)*5000*100)/100));
        list.add(new Promotion(((double)Math.round((kurs+0.31)*1000)/1000),10000,(double)Math.round(((double)Math.round((kurs+0.31)*1000)/1000)*10000*100)/100));
        kupno=walutakupno;
        sprzedaz=walutasprzedaz;
        return new OffersFragment();
    }

    /**
     * Funkcja cyklu życia fragmentu, odpowiada za uruchomienie fragmentu w aplikacji
     * @param savedInstanceState wiązka danych przekazywana fragmentowi
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, 0); //hiding title

    }

    /**
     * Funkcja cyklu życia fragmentu, która odpowaida za przechwytywanie kontekstu aktywności
     * @param activity aktywność, która jest parametrem, z niej pobiera się kontekst
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity.getBaseContext();
    }

    /**
     * Funkcja cyklu życia fragmentu odpowiedzialna za stworzenie widoku
     * @param inflater obiekt odpowiedzialny za umieszczenie widoku na ekranie
     * @param container miejsce, w którym znajduje się widok
     * @param savedInstanceState wiązka, w której przekazywane są informacje do widoku
     * @return zwraca widok, jaki chcemy nadać fragmentowi
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.offer_fragment, container, false);
        mLvObjects = (ListView) v.findViewById(R.id.of_lv_objects);
        ObjectListAdapter mAdapterObj = new ObjectListAdapter(mContext,list);
        mLvObjects.setAdapter(mAdapterObj);
        mLvObjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getFragmentManager().popBackStack();
                Intent intent = new Intent(mContext, FinalActivity.class);
                intent.putExtra(WALUTA_KUPOWANA,kupno);
                intent.putExtra(WALUTA_SPRZEDAWANA,sprzedaz);
                intent.putExtra(KURS,list.get(i).getKurs()+"");
                intent.putExtra(ILOSC,list.get(i).getIlosc()+"");
                intent.putExtra(WARTOSC,list.get(i).getWartosc()+"");
                startActivity(intent);
                getActivity().finish();
            }
        });

                return v;
    }

}