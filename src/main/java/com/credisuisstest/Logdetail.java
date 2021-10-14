package com.credisuisstest;

public class Logdetail {
    public String eventid;
    public String eventduration;
    public String type;
    public String host;
    public String alert;

    public Logdetail(String eventid, String eventduration, String type, String host, String alert) {
        this.eventid = eventid;
        this.eventduration = eventduration;
        this.type = type;
        this.host = host;
        this.alert = alert;
    }


    @Override
    public String toString() {
        return "Logdetail{" +
                "eventid='" + eventid + '\'' +
                ", eventduration='" + eventduration + '\'' +
                ", type='" + type + '\'' +
                ", host='" + host + '\'' +
                ", alert='" + alert + '\'' +
                '}';
    }
}
