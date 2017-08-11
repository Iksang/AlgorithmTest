package kr.co.tjeit.algorithmtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Test02Activity extends AppCompatActivity {

    private android.widget.EditText starLineEdt;
    private android.widget.Button forwardLeftBtn;
    private android.widget.Button forwardRightBtn;
    private android.widget.Button reverseLeftBtn;
    private android.widget.Button reverseRightBtn;
    private android.widget.TextView resultTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test02);




        bindViews();
        setupEvents();
        setValues();

    }

    private void setValues() {
    }

    private void setupEvents() {
        forwardLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int forSize = Integer.parseInt(starLineEdt.getText().toString());
                String star = "";
                for(int i = 1 ; i <= forSize ; i++){
                    for(int j = 1 ; j <= i ; j++){
                        star+="★";
                    }
                    star+="\n";
                }
                resultTxt.setGravity(Gravity.LEFT);
                resultTxt.setText(star);
            }
        });
        forwardRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int forSize = Integer.parseInt(starLineEdt.getText().toString());
                String star = "";
                for(int i = 1 ; i <= forSize ; i++){
                    for(int j = 1 ; j <= i ; j++){
                        star+="★";
                    }
                    star+="\n";
                }
                resultTxt.setGravity(Gravity.RIGHT);
                resultTxt.setText(star);
            }
        });
        reverseLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int forSize = Integer.parseInt(starLineEdt.getText().toString());

                String star = "";
                for(int i = forSize ; i >= 1 ; i--){
                    for(int j = 1 ; j <= i ; j++){
                        star+="★";
                    }
                    star+="\n";
                }
                resultTxt.setGravity(Gravity.LEFT);

                resultTxt.setText(star);
            }
        });
        reverseRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int forSize = Integer.parseInt(starLineEdt.getText().toString());


                String star = "";
                for(int i = forSize ; i >= 1 ; i--){
                    for(int j = 1 ; j <= i ; j++){
                        star+="★";
                    }
                    star+="\n";
                }
                resultTxt.setGravity(Gravity.RIGHT);
                resultTxt.setText(star);
            }
        });


    }

    private void bindViews() {
        this.reverseRightBtn = (Button) findViewById(R.id.reverseRightBtn);
        this.reverseLeftBtn = (Button) findViewById(R.id.reverseLeftBtn);
        this.forwardRightBtn = (Button) findViewById(R.id.forwardRightBtn);
        this.forwardLeftBtn = (Button) findViewById(R.id.forwardLeftBtn);
        this.starLineEdt = (EditText) findViewById(R.id.starLineEdt);
        this.resultTxt = (TextView) findViewById(R.id.resultTxt);
    }
}
