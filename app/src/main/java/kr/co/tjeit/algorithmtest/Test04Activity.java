package kr.co.tjeit.algorithmtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Locale;

public class Test04Activity extends AppCompatActivity {

    private android.widget.CheckBox chkBox01;
    private android.widget.CheckBox chkBox02;
    private android.widget.CheckBox chkBox03;
    private android.widget.CheckBox chkBox04;
    private android.widget.CheckBox chkBox05;
    private android.widget.CheckBox chkBox06;
    private android.widget.CheckBox chkBox07;
    private android.widget.CheckBox chkBox08;
    private android.widget.CheckBox chkBox09;
    private android.widget.CheckBox chkBox10;
    private android.widget.CheckBox chkBox11;
    private android.widget.CheckBox chkBox12;
    private android.widget.CheckBox chkBox13;
    private android.widget.CheckBox chkBox14;
    private android.widget.CheckBox chkBox15;
    private android.widget.CheckBox chkBox16;
    private android.widget.CheckBox chkBox17;
    private android.widget.CheckBox chkBox18;
    private android.widget.CheckBox chkBox19;
    private android.widget.CheckBox chkBox20;
    private android.widget.CheckBox chkBox21;
    private android.widget.CheckBox chkBox22;
    private android.widget.CheckBox chkBox23;
    private android.widget.CheckBox chkBox24;
    private android.widget.CheckBox chkBox25;
    private android.widget.Button allBtn;
    private android.widget.Button diagonalXBtn;
    private android.widget.Button edgeBtn;
    private android.widget.Button defaultBtn;
    private android.widget.TextView bingoCountTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test04);


        bindViews();
        setupEvents();
        setValues();
        
        
    }

    private void setValues() {


    }

    private void setupEvents() {
        final CheckBox[][] checkBoxs = { {chkBox01, chkBox02, chkBox03, chkBox04, chkBox05},
                {chkBox06, chkBox07, chkBox08, chkBox09, chkBox10},
                {chkBox11, chkBox12, chkBox13, chkBox14, chkBox15},
                {chkBox16, chkBox17, chkBox18, chkBox19, chkBox20},
                {chkBox21, chkBox22, chkBox23, chkBox24, chkBox25}};


        allBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultCheckedBox(checkBoxs);
                allCheckedBox(checkBoxs);
            }
        });

        diagonalXBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultCheckedBox(checkBoxs);
                diagonalXCheckedBox(checkBoxs);
            }
        });

        edgeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultCheckedBox(checkBoxs);
                edgeCheckedBox(checkBoxs);

            }
        });

        defaultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultCheckedBox(checkBoxs);
            }
        });

        CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bingoDetection(checkBoxs);
            }
        };

        for(int i = 0; i < 5; i++){
            for(int j = 0 ; j < 5 ; j++) {
                checkBoxs[i][j].setOnCheckedChangeListener(checkListener);
            }

        }

    }

    private void bingoDetection(CheckBox[][] checkBoxs){
        boolean bingoCheck = true;
        int totalBingoCount = 0;

        /*가로 빙고 검사 부분*/
        for(int i = 0 ; i < 5 ; i++){
            for(int j = 0; j < 5 ; j++){
                if(!checkBoxs[i][j].isChecked()){
                    bingoCheck = false;
                    break;
                }
            }
            if(bingoCheck){
                totalBingoCount++;
            }
            else{
                bingoCheck = true;
            }
        }

        /*세로 빙고 검사 부분*/
        for(int i = 0 ; i < 5 ; i++){
            for(int j = 0; j < 5 ; j++){
                if(!checkBoxs[j][i].isChecked()){
                    bingoCheck = false;
                    break;
                }
            }
            if(bingoCheck){
                totalBingoCount++;
            }
            else{
                bingoCheck = true;
            }
        }

        //좌상 -> 우상 대각선 검사
        /*대각선 빙고 검사 부분*/
        for(int i = 0; i < 5 ; i++) {
            if(!checkBoxs[i][i].isChecked()){
                bingoCheck = false;
                break;
            }
        }
        if(bingoCheck){
            totalBingoCount++;
        }
        else{
            bingoCheck = true;
        }


        //우상 -> 좌하 대각선 검사
        /*대각선 빙고 검사 부분*/
        for(int i = 0; i < 5 ; i++) {
            if(!checkBoxs[i][4-i].isChecked()){
                bingoCheck = false;
                break;
            }
        }

        if(bingoCheck){
            totalBingoCount++;
        }
        else{
            bingoCheck = true;
        }


        bingoCountTxt.setText(String.format(Locale.KOREA,"빙고의 갯수는 %d개 입니다",totalBingoCount));
    }


    private void edgeCheckedBox(CheckBox[][] checkBoxs) {
        for(int i = 0; i < 5 ; i++){
            for(int j = 0; j < 5 ; j++){
                if(i==0||j==0||i==4||j==4) {
                    checkBoxs[i][j].setChecked(true);
                }
            }
        }
    }

    private void diagonalXCheckedBox(CheckBox[][] checkBoxs) {
        for(int i = 0; i < 5 ; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == j || (i + j) == 4) {
                    checkBoxs[i][j].setChecked(true);
                }
            }
        }
    }

    private void allCheckedBox(CheckBox[][] checkBoxs) {
        for(int i = 0; i < 5 ; i++){
            for(int j = 0; j < 5 ; j++){
                checkBoxs[i][j].setChecked(true);
            }
        }
    }

    private void defaultCheckedBox(CheckBox[][] checkBoxs) {
        for(int i = 0; i < 5 ; i++){
            for(int j = 0; j < 5 ; j++){
                checkBoxs[i][j].setChecked(false);
            }
        }
    }

    private void bindViews() {
        this.chkBox25 = (CheckBox) findViewById(R.id.chkBox25);
        this.chkBox24 = (CheckBox) findViewById(R.id.chkBox24);
        this.chkBox23 = (CheckBox) findViewById(R.id.chkBox23);
        this.chkBox22 = (CheckBox) findViewById(R.id.chkBox22);
        this.chkBox21 = (CheckBox) findViewById(R.id.chkBox21);
        this.chkBox20 = (CheckBox) findViewById(R.id.chkBox20);
        this.chkBox19 = (CheckBox) findViewById(R.id.chkBox19);
        this.chkBox18 = (CheckBox) findViewById(R.id.chkBox18);
        this.chkBox17 = (CheckBox) findViewById(R.id.chkBox17);
        this.chkBox16 = (CheckBox) findViewById(R.id.chkBox16);
        this.chkBox15 = (CheckBox) findViewById(R.id.chkBox15);
        this.chkBox14 = (CheckBox) findViewById(R.id.chkBox14);
        this.chkBox13 = (CheckBox) findViewById(R.id.chkBox13);
        this.chkBox12 = (CheckBox) findViewById(R.id.chkBox12);
        this.chkBox11 = (CheckBox) findViewById(R.id.chkBox11);
        this.chkBox10 = (CheckBox) findViewById(R.id.chkBox10);
        this.chkBox09 = (CheckBox) findViewById(R.id.chkBox09);
        this.chkBox08 = (CheckBox) findViewById(R.id.chkBox08);
        this.chkBox07 = (CheckBox) findViewById(R.id.chkBox07);
        this.chkBox06 = (CheckBox) findViewById(R.id.chkBox06);
        this.chkBox05 = (CheckBox) findViewById(R.id.chkBox05);
        this.chkBox04 = (CheckBox) findViewById(R.id.chkBox04);
        this.chkBox03 = (CheckBox) findViewById(R.id.chkBox03);
        this.chkBox02 = (CheckBox) findViewById(R.id.chkBox02);
        this.chkBox01 = (CheckBox) findViewById(R.id.chkBox01);

        this.defaultBtn = (Button) findViewById(R.id.defaultBtn);
        this.edgeBtn = (Button) findViewById(R.id.edgeBtn);
        this.diagonalXBtn = (Button) findViewById(R.id.diagonalXBtn);
        this.allBtn = (Button) findViewById(R.id.allBtn);
        this.bingoCountTxt = (TextView) findViewById(R.id.bingoCountTxt);
    }
}
