package kr.co.tjeit.algorithmtest.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import kr.co.tjeit.algorithmtest.R;
import kr.co.tjeit.algorithmtest.data.ChattingData;

/**
 * Created by tjoeun on 2017-08-11.
 */

//  Adapter를 만드는 이유?
//  => Data 클래스를 layout(뷰의 모양)에 입혀서 ListView에 한줄한줄 뿌려주기 위함.
//  1. 어댑터를 작성해서, getCount를 활용해 20개의 메세지가 뜨도록.
//     => getView 내부에서 (mList.get)을 하지 말자
//  2. 이 어댑터가 활용될 액티비티에 가서 Adapter와 ListView를 연결.
//      (List를 액티비티에서 생성해야함)


public class ChattingAdapter extends ArrayAdapter<ChattingData>{
    private Context mContext;
    private List<ChattingData> mList;
    LayoutInflater inf;


    public ChattingAdapter(Context context, List<ChattingData> list){
        super(context, R.layout.chatting_list_item, list);
        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if(row == null){
            row = inf.inflate(R.layout.chatting_list_item, null);
        }

        // setAdapter를 통해 연결까지 되는걸 확인하고 나면
        // getCount 메쏘드는 지워버리고,
        // 실제 내용이 출력될 수 있도록 getView 내부를 수정

        // 화면에 뿌려줄 근거가 되는 데이터 클래스를 mList로부터
        // 위치에 맞는 클래스를 가져온다. get(position)

        ChattingData data = mList.get(position);

        // 필요한 뷰 들을 row로부터 찾아내서 바인딩
        // row.findViewById 를 해야한다.
        LinearLayout myMessageLayout = (LinearLayout)row.findViewById(R.id.myMessageLayout);
        LinearLayout cpuMessageLayout = (LinearLayout)row.findViewById(R.id.cpuMessageLayout);

        // 상황에 맞는 출력 로직


        if(data.isSentByMe()){
            // 내가 보낸건지, CPU가 보낸건지에 따라 어떤 부분을 보이고 숨길지 부터 결정.
            myMessageLayout.setVisibility(View.VISIBLE);
            cpuMessageLayout.setVisibility(View.GONE);

            // 내가 보낸 경우, 내가 보낸 메세지만 바인딩 해서 setText
            TextView myMessageTxt = (TextView)row.findViewById(R.id.myMessageTxt);
            myMessageTxt.setText(data.getMessageText());
        }
        else{

            // CPU가 보낸 메세지에 대한 대처
            cpuMessageLayout.setVisibility(View.VISIBLE);
            myMessageLayout.setVisibility(View.GONE);
            TextView cpuMessageTxt = (TextView)row.findViewById(R.id.cpuMessageTxt);
            cpuMessageTxt.setText(data.getMessageText());


        }


        return row;
    }

//    @Override
//    public int getCount() {
//        return 20;
//    }
}
