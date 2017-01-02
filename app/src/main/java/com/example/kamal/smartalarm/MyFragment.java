package com.example.kamal.smartalarm;

/**
 * Created by g588969 on 22/12/2016.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyFragment extends Fragment implements View.OnClickListener{
    private int position = 0;
    public static Fragment newInstance(Carousel context, int pos, float scale) {
        Bundle b = new Bundle();
        b.putInt("pos", pos);
        b.putFloat("scale", scale);
        return Fragment.instantiate(context, MyFragment.class.getName(), b);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        LinearLayout l = (LinearLayout)
                inflater.inflate(R.layout.mf, container, false);

        int pos = this.getArguments().getInt("pos");
        TextView tv = (TextView) l.findViewById(R.id.text);
        tv.setText("Position = " + pos);
        position = pos;
        ImageButton ib = (ImageButton)l.findViewById(R.id.content);
        ib.setOnClickListener(this);
        switch(pos){
            case 0:
                tv.setText("Reveil");
                ((ImageButton) ib).setImageResource(R.drawable.alarm1);
                break;
            case 1:
                tv.setText("Paramètres");
                ((ImageButton) ib).setImageResource(R.drawable.param1);
                break;
            case 2:
                tv.setText("Calendriers");
                ((ImageButton) ib).setImageResource(R.drawable.calendar);
                break;
            case 3:
                tv.setText("Sortir");
                ((ImageButton) ib).setImageResource(R.drawable.exit1);
                break;
        }

        MyLinearLayout root = (MyLinearLayout) l.findViewById(R.id.root);
        float scale = this.getArguments().getFloat("scale");
        root.setScaleBoth(scale);

        return l;
    }

    @Override
    public void onClick(View v) {
        switch(position){
            case 0:
                Intent i2 = new Intent(getActivity().getApplicationContext(),  AlarmActivity.class);
                startActivity(i2);
                Toast.makeText(getActivity().getApplicationContext(), "Reveil", Toast.LENGTH_LONG).show();
                break;
            case 1:
                Intent param = new Intent(getActivity(), Parametre.class);
                startActivity(param);
                Toast.makeText(getActivity().getApplicationContext(),"Paramètres",Toast.LENGTH_LONG).show();
                break;
            case 2:
                Intent reveil = new Intent(getActivity(), Calendrier.class);
                startActivity(reveil);
                break;
            case 3:
                new AlertDialog.Builder(getContext())
                        .setTitle("Sortie")
                        .setMessage("Voulez-vous vraiment fermer l'application ?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
//                                getActivity().finish();
                                getActivity().finishAffinity();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                Toast.makeText(getActivity().getApplicationContext(),"Exit",Toast.LENGTH_LONG).show();
                break;
        }
    }
}