package com.example.myfirstapp.Backup;

import android.content.Context;

import com.example.myfirstapp.Data.Record;

import java.util.List;

public abstract class BackupFunction {
    public abstract List<Record> getAllRecord();
}
