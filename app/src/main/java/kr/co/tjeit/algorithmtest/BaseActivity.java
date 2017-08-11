package kr.co.tjeit.algorithmtest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tjoeun on 2017-08-11.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public abstract void bindViews();
    public abstract void setupEvents();
    public abstract void setValues();

    public Context mContext = this;

}
