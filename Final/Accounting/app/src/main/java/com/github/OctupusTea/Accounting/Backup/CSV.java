package com.github.OctupusTea.Accounting.Backup;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.github.OctupusTea.Accounting.Data.Record;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class CSV extends DataFormat {

    public CSV(Context context, String date) {
        super(context, date);
    }

    @Override
    public void processBackup() {
        try {
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(backupFile));
            for(Record record : recordList) {
                String inputData = String.format("%s,%s,%s,%s,%s,%s", record.getId(), record.getAccountName(), record.getCategoryName(), record.getDate(), record.getCurrencyType(), record.getCost());
                printWriter.println(inputData);
            }
            Toast toast = Toast.makeText(context, "Backup Success", Toast.LENGTH_LONG);
            toast.show();
            printWriter.close();
        } catch ( IOException e ) {
            Log.d("File error", e.toString());
            e.printStackTrace();
        }
    }
}
