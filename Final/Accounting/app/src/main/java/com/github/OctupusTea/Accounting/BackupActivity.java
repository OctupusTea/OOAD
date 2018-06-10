package com.github.OctupusTea.Accounting;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.accounting.R;
import com.github.OctupusTea.Accounting.Backup.Backup;

public class BackupActivity extends AppCompatActivity {

    private Spinner spinnerBackupType;
    private Button exportButton;
    private String selectedBackupType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup);
        findViews();
        ArrayAdapter<CharSequence> backupTypeList = ArrayAdapter.createFromResource(BackupActivity.this, R.array.spinner_backupType, android.R.layout.simple_spinner_dropdown_item);
        spinnerBackupType.setAdapter(backupTypeList);
    }

    private void findViews() {
        spinnerBackupType = (Spinner) findViewById(R.id.spinner_backupType);
        exportButton = (Button) findViewById(R.id.button_export);
    }

    public void exportButton(View v) {
        Backup backup = new Backup(this, spinnerBackupType.getSelectedItem().toString());
        backup.backup();
    }
}
