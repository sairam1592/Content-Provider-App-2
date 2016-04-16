package com.gallery.admin.mygallery;

/**
 * Created by Admin on 11/28/2015.
 */


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;


public class SplashActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    Animation fadeIn;
    ImageView im;
    Intent i;
    CursorLoader cursorLoader;
    String flag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        animText();
        moveToNextActivity();
        getSupportLoaderManager().initLoader(1, null, this);
    }

    public void animText() {
        im = (ImageView) findViewById(R.id.imageView_splash);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        im.startAnimation(fadeIn);
    }


    public void moveToNextActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (flag.equalsIgnoreCase(Constants.TRUE_FLAG)) {
                    i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                } else if (flag.equalsIgnoreCase(Constants.FALSE_FLAG)) {
                    i = new Intent(SplashActivity.this, AlertActivity.class);
                    startActivity(i);
                }
            }
        }, 4000);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        cursorLoader = new CursorLoader(this, Uri.parse(Constants.PROVIDER_ID), null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    flag = cursor.getString(cursor.getColumnIndex(Constants.COL_FLAG));
                    cursor.moveToNext();
                }
            } else {
                Toast.makeText(this, Constants.REGISTER_ALERT, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, Constants.INSTALL_ALERT, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
    }

}
