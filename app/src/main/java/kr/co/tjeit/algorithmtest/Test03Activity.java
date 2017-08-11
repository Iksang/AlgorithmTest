package kr.co.tjeit.algorithmtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.algorithmtest.adapter.GuguAdapter;
import kr.co.tjeit.algorithmtest.data.GuguData;

public class Test03Activity extends AppCompatActivity {

    private android.widget.Button guguTwoBtn;
    private android.widget.Button guguThreeBtn;
    private android.widget.Button guguFourBtn;
    private android.widget.Button guguFiveBtn;
    private android.widget.Button guguSixBtn;
    private android.widget.Button guguSevenBtn;
    private android.widget.Button guguEightBtn;
    private android.widget.Button guguNineBtn;
    private android.widget.Button guguAllBtn;
    private android.widget.ListView guguList;
    List<GuguData> guguDatas = new ArrayList<>();
    GuguAdapter guguAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test03);

        bindViews();
        setupEvents();
        setValues();
    }

    private void setValues() {
        guguAdapter = new GuguAdapter(Test03Activity.this, guguDatas);
        guguList.setAdapter(guguAdapter);
    }

    private void setupEvents() {
        View.OnClickListener guguListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gugudan(Integer.parseInt(v.getTag().toString()));
                // Integer 클래스 : Wrapper 클래스
                // int 를 감싼다. => int : 기본형 변수.
                // 그외의 대문자로 시작하는 클래스 : 참조형 변수.
                // Ex. 참조형 변수 : String => 클래스 (변수, 메쏘드)
                // int -> 클래스화 : Integer
                // double -> 클래스화 : Double

                // parse -> 분쇄하다. => 개발 : 분석하다
                // 날아온 자료를 분석해서 다르게 활용하고자 할때 : parse

                // 클릭 된 버튼에 달려있는 Tag를 기반으로
                // 몇단을 출력해야 하는지 판단하여 실행 메쏘드
            }
        };
        guguTwoBtn.setOnClickListener(guguListener);
        guguThreeBtn.setOnClickListener(guguListener);
        guguFourBtn.setOnClickListener(guguListener);
        guguFiveBtn.setOnClickListener(guguListener);
        guguSixBtn.setOnClickListener(guguListener);
        guguSevenBtn.setOnClickListener(guguListener);
        guguEightBtn.setOnClickListener(guguListener);
        guguNineBtn.setOnClickListener(guguListener);
        guguAllBtn.setOnClickListener(guguListener);
    }

    private void gugudan(int dan) {
        guguDatas.clear();
        if (dan != 10) {
            for (int i = 1; i <= 9; i++) {
                guguDatas.add(new GuguData(dan, i));
            }
        }
        else {
            for (int i = 2; i <= 9; i++) {
                for (int j = 1; j <= 9; j++) {
                    guguDatas.add(new GuguData(i, j));
                }
            }
        }
        guguAdapter.notifyDataSetChanged();
    }

    private void bindViews() {
        this.guguList = (ListView) findViewById(R.id.guguList);
        this.guguAllBtn = (Button) findViewById(R.id.guguAllBtn);
        this.guguNineBtn = (Button) findViewById(R.id.guguNineBtn);
        this.guguEightBtn = (Button) findViewById(R.id.guguEightBtn);
        this.guguSevenBtn = (Button) findViewById(R.id.guguSevenBtn);
        this.guguSixBtn = (Button) findViewById(R.id.guguSixBtn);
        this.guguFiveBtn = (Button) findViewById(R.id.guguFiveBtn);
        this.guguFourBtn = (Button) findViewById(R.id.guguFourBtn);
        this.guguThreeBtn = (Button) findViewById(R.id.guguThreeBtn);
        this.guguTwoBtn = (Button) findViewById(R.id.guguTwoBtn);
    }
}
