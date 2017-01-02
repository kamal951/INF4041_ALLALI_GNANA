package com.example.kamal.smartalarm;

/**
 * Created by kamal on 29/12/2016.
 */

public class Event {
    private String dateStart;
    private String dateEnd;
    private String summary;

    public Event(String dStart, String dEnd, String sum){
        dateStart = dStart;
        dateEnd = dEnd;
        summary = sum;
    }

    public String getDateStart(){
        return dateStart;
    }

    public String getDateEnd(){
        return dateEnd;
    }

    public String getSummary(){
        return summary;
    }

    public String toString(){
        return summary+" || "+dateStart+" || "+dateEnd;
    }
}
