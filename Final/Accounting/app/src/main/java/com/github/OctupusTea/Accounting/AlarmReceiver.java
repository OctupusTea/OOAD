package com.github.OctupusTea.Accounting;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TimePicker;

public class AlarmReceiver extends AppCompatActivity {
	TimePicker alarmTimePicker;
	PendingIntent pendingIntent;
	AlarmManager alarmManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_receiver);
		alarmTimePicker = findViewById( R.id.timePicker );
		alarmManager = (AlarmManager) getSystemService( ALARM_SERVICE );
	}
}
