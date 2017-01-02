package com.example.kamal.smartalarm;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kamal on 02/01/2017.
 */

public class MyViewHolder extends RecyclerView.ViewHolder{

    private TextView textViewView;
    private TextView textViewView1;
    private TextView textViewView2;
    //itemView est la vue correspondante à 1 cellule
    public MyViewHolder(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView

        textViewView = (TextView) itemView.findViewById(R.id.text);
        textViewView1 = (TextView) itemView.findViewById(R.id.date_debut);
        textViewView2 = (TextView) itemView.findViewById(R.id.date_fin);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(Event myObject){
        textViewView.setText(myObject.getSummary());
        String date_debut = myObject.getDateStart();
        String date_fin = myObject.getDateEnd();

        textViewView.setText(myObject.getSummary());
        textViewView1.setText(date_debut);
        textViewView2.setText(date_fin);
    }

    public String split_date(String date){
        date = date.substring(0,date.length()-1);
        String d[] = date.split("T");

        return d[0].substring(0,4)+"/"+d[0].substring(4,6)+"/"+d[0].substring(6,8)+" "+d[1].substring(0,2)+":"+d[1].substring(2,4)+":"+d[1].substring(4,6);
    }
}
