package com.example.myfirstapp.Backup;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Backup {
    private String date;
    private SimpleDateFormat timeFormatting;
    private DataFormat dataformat;

    public Backup(Context context, String formatType) {
        this.date = getTime();
        switch(formatType) {
            case "CSV":
                dataformat = new CSV(context, this.date);
                break;
            case "Text":
                dataformat =new Text(context, this.date);
                break;
            default:
                break;
        }
    }

    private String getTime() {
        timeFormatting = new SimpleDateFormat("yyyy-MM-dd");
        return timeFormatting.format(new Date());
    }

    public void backup() {
        dataformat.processBackup();
    }
}
