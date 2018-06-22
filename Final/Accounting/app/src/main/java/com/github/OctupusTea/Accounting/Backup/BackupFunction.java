package com.github.OctupusTea.Accounting.Backup;

import android.content.Context;

import com.github.OctupusTea.Accounting.Data.Record;

import java.util.List;

public abstract class BackupFunction {
    public abstract List<Record> getAllRecord();
}
