package com.example.kamal.smartalarm;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import net.fortuna.ical4j.data.ParserException;

import java.io.IOException;

public class Calendrier extends AppCompatActivity implements View.OnClickListener {
    private Button b = null;
    private Button b1 = null;

    private RecyclerView recyclerView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendrier);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);
        //afficher le bouton retour
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        b = (Button)findViewById(R.id.button8);
        b.setOnClickListener(this);

        b1 = (Button)findViewById(R.id.button9);
        b1.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);

        //définit l'agencement des cellules, ici de façon verticale, comme une ListView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //pour adapter en grille comme une RecyclerView, avec 2 cellules par ligne
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        //puis créer un MyAdapter, lui fournir notre liste de villes.
        //cet adapter servira à remplir notre recyclerview

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, Carousel.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button8:
                try {
                    Cal c = new Cal(getApplicationContext().getResources().openRawResource(R.raw.mycalendar1));
                    recyclerView.setAdapter(new MyAdapter(c.getEventList()));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParserException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button9:
                try {

                    Cal c1 = new Cal("https://edt.esiea.fr/Telechargements/ical/EdT_ALLALI_Kamal.ics?version=14.0.2.6&amp;idICal=A12FF0ED943F4DBBFABAC85EEC100CF2&amp;param=643d5b312e2e36325d2666683d3126663d31");
                    recyclerView.setAdapter(new MyAdapter(c1.getEventList()));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParserException e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}
