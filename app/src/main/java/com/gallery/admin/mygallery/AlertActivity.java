package com.gallery.admin.mygallery;

import android.app.Activity;
import android.os.Bundle;


/**
 * Created by Admin on 11/29/2015.
 */
public class AlertActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
    }

    @Override
    public void onBackPressed() {
        finish();
        return;
    }
}
