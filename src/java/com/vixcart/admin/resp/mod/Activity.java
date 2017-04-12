/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.resp.mod;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @company techvay
 * @author rifaie
 */
public class Activity {

    private String uid;
    private String uType;
    private String activity;
    private String dateTime;
    private String activityUType;
    private String entryStatus;

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setuType(String uType) {
        this.uType = uType;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setDateTime(String dateTime) {
        long timeStamp = Long.parseLong(dateTime);
        Date date=new Date(timeStamp);
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        dateTime = simpleDateFormat.format(date);
        this.dateTime = dateTime;
    }

    public void setActivityUType(String activityUType) {
        this.activityUType = activityUType;
    }

    public void setEntryStatus(String entryStatus) {
        this.entryStatus = entryStatus;
    }

    public String getUid() {
        return uid;
    }

    public String getuType() {
        return uType;
    }

    public String getActivity() {
        return activity;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getActivityUType() {
        return activityUType;
    }

    public String getEntryStatus() {
        return entryStatus;
    }

    @Override
    public String toString() {
        return "{\"uid\":\"" + uid + "\", \"uType\":\"" + uType + "\", \"activity\":\"" + activity + "\",\"dateTime\":\"" + dateTime + "\",\"activityUType\":\"" + activityUType + "\",\"entryStatus\":\"" + entryStatus + "\"}";
    }
}
