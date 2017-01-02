package com.example.kamal.smartalarm;


import android.os.AsyncTask;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by kamal on 26/12/2016.
 */

public class Cal {

    private String reponseUrl = "";
    private ArrayList<Event> eventList = new ArrayList<Event>();

    public Cal(InputStream fin) throws IOException, ParserException {

        ArrayList<Event> eventl = new ArrayList<Event>();
        String dateS = "";
        String dateE = "";
        String sum = "";
        CalendarBuilder builder = new CalendarBuilder();

        Calendar calendar = builder.build(fin);

        for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
            Component component = (Component) i.next();

            for (Iterator j = component.getProperties().iterator(); j.hasNext();) {
                Property property = (Property) j.next();
                if(property.getName() == "DTSTART"){
                    dateS = property.getValue();
                }else if(property.getName() == "DTEND"){
                    dateE = property.getValue();
                }else if(property.getName() == "SUMMARY"){
                    sum = property.getValue();
                }
            }
            eventl.add(new Event(dateS, dateE, sum));
        }
        eventList = eventl;
    }


    public Cal(String url) throws IOException, ParserException {

        ArrayList<Event> eventl = new ArrayList<Event>();
        GetCal getc = new GetCal();
        try {
            eventl = getc.execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        eventList = eventl;
    }
    //Retourne les events sous forme d'array
    public ArrayList getEventList(){
        return eventList;
    }

    //Retourne le 1er events de demain et null si pas d'event
    public ArrayList<Event> getTomorrowFirstEvents(){
        ArrayList<Event> list = new ArrayList<>();

        String format = "yyyyMMdd";

        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
        java.util.Date date = new java.util.Date();
        java.util.Date dayAfter = new java.util.Date(date.getTime() + TimeUnit.DAYS.toMillis( 1 ));
        String tomoDate = formater.format( dayAfter );
        for(Event e : eventList){
            //substring(a,b) de a a b non inclus
            if(e.getDateStart().substring(0,8).equals(tomoDate)){
                list.add(e);
            }
        }
        return list;
    }

    public class GetCal extends AsyncTask<String, Void, ArrayList> {

        ArrayList<Event> eventList = new ArrayList<Event>();

        @Override
        protected void onPreExecute() { //avant de récupérer le calendrier
            super.onPreExecute();
        }

        @Override
        protected ArrayList doInBackground(String... url) { //fait en arrière-plan
            HttpHandler sh = new HttpHandler();//classe pour faire la requête http
            // Making a request to url and getting response
            String repUrl = sh.makeServiceCall(url[0]);

            reponseUrl = repUrl;
            StringReader sin = new StringReader(reponseUrl);
            CalendarBuilder builder = new CalendarBuilder();

            Calendar calendar = null;
            try {
                calendar = builder.build(sin);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserException e) {
                e.printStackTrace();
            }

            String dateS = "";
            String dateE = "";
            String sum = "";
            for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
                Component component = (Component) i.next();

                for (Iterator j = component.getProperties().iterator(); j.hasNext();) {
                    Property property = (Property) j.next();
                    if(property.getName() == "DTSTART"){
                        dateS = property.getValue();
                    }else if(property.getName() == "DTEND"){
                        dateE = property.getValue();
                    }else if(property.getName() == "SUMMARY"){
                        sum = property.getValue();
                    }
                }
                eventList.add(new Event(dateS, dateE, sum));
            }
            return eventList;
        }



        @Override
        protected void onPostExecute(ArrayList result) {
            super.onPostExecute(result);
        }
    }

}
