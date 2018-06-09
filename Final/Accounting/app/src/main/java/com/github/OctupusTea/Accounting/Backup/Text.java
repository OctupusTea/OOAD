package com.github.OctupusTea.Accounting.Backup;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.github.OctupusTea.Accounting.Data.Record;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Text extends DataFormat {

    public Text(Context context, String date) {
        super(context, date);
    }
    @Override
    public void processBackup() {
        try {
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(backupFile));
            for(Record record : recordList) {
                String inputData = String.format("%s;%s;%s;%s;%s;%s\n", record.getId(), record.getAccountName(), record.getCategorayName(), record.getDate(), record.getCurrencyType(), record.getCost());
                printWriter.println(inputData);
            }
            Toast toast = Toast.makeText(context, "Backup Success", Toast.LENGTH_LONG);
            toast.show();
            printWriter.close();
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
