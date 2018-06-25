package com.github.OctupusTea.Accounting;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AlarmPick extends AppCompatActivity {
	TimePicker alarmTimePicker;
	int callerID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_pick);
		alarmTimePicker = findViewById( R.id.timePicker );

		callerID = getIntent( ).getIntExtra( "callerID", 0xFFFFFFFF );

		Button timePick = (Button) findViewById(R.id.timePickerButton );
		timePick.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick( View v ) {
				setAndReturn( v );
				/*Intent intent = new Intent();
				intent.setClass(AlarmSetting.this, MainActivity.class);
				startActivity(intent);*/
			}
		});
	}

	public void setAndReturn( View view )
	{
		Calendar calendar = Calendar.getInstance();

		calendar.set( Calendar.HOUR_OF_DAY, alarmTimePicker.getHour() );
		calendar.set( Calendar.MINUTE, alarmTimePicker.getMinute() );

		if( calendar.before( Calendar.getInstance() ) )
		{
			calendar.add( Calendar.DATE, 1 );
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd-HH:mm" );
		String date = dateFormat.format( calendar.getTime( ) );

		Intent resultData = new Intent( );
		resultData.putExtra( "date", date );
		resultData.putExtra( "callerID", callerID );
		setResult( RESULT_OK, resultData );
		finish();
	}
}
