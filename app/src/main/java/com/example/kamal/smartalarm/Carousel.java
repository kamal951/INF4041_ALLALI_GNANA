package com.example.kamal.smartalarm;

/**
 * Created by kamal on 23/12/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class Carousel extends AppCompatActivity {
    TextView tv = null;
    private static final int PICK_FILE_RESULT_CODE = 1;

    public final static int PAGES = 4;
    // You can choose a bigger number for LOOPS, but you know, nobody will fling
    // more than 200 times just in order to test your "infinite" ViewPager :D
    public final static int LOOPS = 200;
    public final static int FIRST_PAGE = PAGES * LOOPS / 2;

    public MyPagerAdapter adapter;
    public ViewPager pager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        tv = (TextView)findViewById(R.id.textView2);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(
                getApplicationContext());
        String login = prefs.getString("nom","");
        tv.setText(getText(R.string.bonjour)+" "+login);

        pager = (ViewPager) findViewById(R.id.myviewpager);

        adapter = new MyPagerAdapter(this, this.getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setPageTransformer(false, adapter);

        // Set current item to the middle page so we can fling to both
        // directions left and right
        pager.setCurrentItem(FIRST_PAGE);

        // Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        pager.setOffscreenPageLimit(3);

        // Set margin for pages as a negative number, so a part of next and
        // previous pages will be showed
        if(getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT){
            pager.setPageMargin(-420);
        }else{
            pager.setPageMargin(-700);
        }

    }

    public Context getContext(){
        return this.getContext();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.infobtn:
                Intent i2 = new Intent(Carousel.this,  Info.class);
                startActivity(i2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case PICK_FILE_RESULT_CODE: {
                if (resultCode == Activity.RESULT_OK &&
                        data!=null && data.getData()!=null)
                {
                    String theFilePath = data.getData().getPath();
                    Log.d("file",theFilePath);
                }
                break;
            }
        }
    }
}

