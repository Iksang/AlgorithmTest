package kr.co.tjeit.algorithmtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private android.widget.Button firstQuestionBtn;
    private android.widget.Button secondQuestionBtn;
    private android.widget.Button thirdQuestionBtn;
    private android.widget.Button fourthQuestionBtn;
    private Button fifthQuestionBtn;
    private Button sixthQuestionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bindViews();
        setupEvents();
        setValues();

    }

    private void setValues() {
    }

    private void setupEvents() {

        firstQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Test01Activity.class);
                startActivity(myIntent);
            }
        });
        secondQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Test02Activity.class);
                startActivity(myIntent);
            }
        });
        thirdQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Test03Activity.class);
                startActivity(myIntent);
            }
        });
        fourthQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Test04Activity.class);
                startActivity(myIntent);
            }
        });

        fifthQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Test05Activity.class);
                startActivity(myIntent);
            }
        });
        sixthQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Test06Activity.class);
                startActivity(myIntent);
            }
        });
    }

    private void bindViews() {
        this.sixthQuestionBtn = (Button) findViewById(R.id.sixthQuestionBtn);
        this.fifthQuestionBtn = (Button) findViewById(R.id.fifthQuestionBtn);
        this.fourthQuestionBtn = (Button) findViewById(R.id.fourthQuestionBtn);
        this.thirdQuestionBtn = (Button) findViewById(R.id.thirdQuestionBtn);
        this.secondQuestionBtn = (Button) findViewById(R.id.secondQuestionBtn);
        this.firstQuestionBtn = (Button) findViewById(R.id.firstQuestionBtn);

    }
}
