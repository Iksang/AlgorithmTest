package kr.co.tjeit.algorithmtest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import kr.co.tjeit.algorithmtest.R;
import kr.co.tjeit.algorithmtest.data.GuguData;

/**
 * Created by tjoeun on 2017-08-10.
 */

public class GuguAdapter extends ArrayAdapter<GuguData> {

    private Context mContext;
    private List<GuguData> mList;
    LayoutInflater inf;


    public GuguAdapter (Context context, List<GuguData> list){
        super(context, R.layout.gugu_item_list, list);
        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if(row == null){
            row = inf.inflate(R.layout.gugu_item_list, null);
        }

        TextView guguTxt = (TextView)row.findViewById(R.id.guguTxt);
        int dan = mList.get(position).getDan();
        int multiply = mList.get(position).getMultiply();

        guguTxt.setText(String.format(Locale.KOREA,"%d X %d = %d",dan,multiply,dan*multiply));

        return row;
    }
}
