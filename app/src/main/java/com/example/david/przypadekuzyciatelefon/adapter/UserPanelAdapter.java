package com.example.david.przypadekuzyciatelefon.adapter;

import android.content.Context;;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.przypadekuzyciatelefon.R;

import java.util.ArrayList;

/**
 * Adapter prezentujący panel użytkownika - operacje dostępne w liście
 */

public class UserPanelAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mListString;
    private ArrayList<Integer> mListInt;

    /**
     * Statyczna klasa Holdera- czyli elementu, który będzie przyjmował wygląd każdej linijki listy
     */
    private static class ViewHolder {
        ImageView ivImage;
        TextView tvName;
    }
    /**
     * Konstruktor klasy, który ma za zadanie przyjmować parametry z klasy wywołującej
     * @param context  przyjmuje kontekst aktywności z której jest wywołany
     * @param list  parametr listy okazji, która nadpisze listę utworzoną jako pole w adapterze
     */
    public UserPanelAdapter(Context context, ArrayList<String> list, ArrayList<Integer>list2) {
        this.mContext = context;
        this.mListString = (ArrayList<String>) list.clone();
        this.mListInt = (ArrayList<Integer>) list2.clone();
    }

    /**
     * Funkcja zwracjąca długość listy
     * @return ilość elementów w liście
     */
    @Override
    public int getCount() {
        return mListString.size();
    }

    /**
     * Funkcja zwracająca kliknięty element
     * @param position parametr pozycji elementu na liście
     * @return zwraca element z listy
     */
    @Override
    public Object getItem(int position) {
        return mListString.get(position);
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
            v = inflater.inflate(R.layout.list_view_row, parent, false);

            holder = new ViewHolder();
            holder.ivImage = (ImageView) v.findViewById(R.id.iv_row);
            holder.tvName = (TextView) v.findViewById(R.id.tv_row);
            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.tvName.setText(mListString.get(position));
        holder.ivImage.setImageResource(mListInt.get(position));
        return v;
    }

}