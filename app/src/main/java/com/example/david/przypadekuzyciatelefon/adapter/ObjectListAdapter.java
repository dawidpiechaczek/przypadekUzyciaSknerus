package com.example.david.przypadekuzyciatelefon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.david.przypadekuzyciatelefon.R;
import com.example.david.przypadekuzyciatelefon.model.Promotion;

import java.util.ArrayList;

/**
 * Adapter prezentujący okazyjne kursy we fragmencie oferty po wybraniu interesujących kursów
 */

public class ObjectListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Promotion> mList;

    /**
     * Statyczna klasa Holdera- czyli elementu, który będzie przyjmował wygląd każdej linijki listy
     */
    private static class ViewHolder {
        TextView tvRate;
        TextView tvAmount;
        TextView tvValue;
    }

    /**
     * Konstruktor klasy, który ma za zadanie przyjmować parametry z klasy wywołującej
     * @param context  przyjmuje kontekst aktywności z której jest wywołany
     * @param list  parametr listy okazji, która nadpisze listę utworzoną jako pole w adapterze
     */
    public ObjectListAdapter(Context context, ArrayList<Promotion>list) {
        this.mContext = context;
        this.mList =(ArrayList<Promotion>)list.clone();
    }

    /**
     * Funkcja zwracjąca długość listy
     * @return ilość elementów w liście
     */
    @Override
    public int getCount() {
        return mList.size();
    }

    /**
     * Funkcja zwracająca kliknięty element
     * @param position parametr pozycji elementu na liście
     * @return zwraca element z listy
     */
    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    /**
     * Funkcja zwracająca ID elementu
     * @param position parametr pozycji elementu na liście
     * @return zwraca ID - równy pozycji na liście
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Funkcja towrząca widok listy z wielu holderów
     * @param position parametr pozycji danego elementu
     * @param convertView parametr widoku pojedynczego holdera
     * @param parent parametr widoku już istniającej listy - grupy holderów
     * @return zwraca widok po dodaniu kolejnego holdera
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.object_list_item, parent, false);

            holder = new ViewHolder();

            holder.tvRate = (TextView) v.findViewById(R.id.oli_kurs);
            holder.tvAmount = (TextView) v.findViewById(R.id.oli_ilosc);
            holder.tvValue = (TextView) v.findViewById(R.id.oli_wartosc);
            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }


        holder.tvRate.setText(mList.get(position).getKurs()+"");
        holder.tvAmount.setText(mList.get(position).getIlosc()+"");
        holder.tvValue.setText(mList.get(position).getWartosc()+"");

        return v;
    }
}
