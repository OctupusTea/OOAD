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

	/*public void onClickSetup( View view )
	{
		long time;

		if( ((ToggleButton)view).isChecked( ) )
		{
			Calendar calendar = Calendar.getInstance();

			calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getHour());
			calendar.set(Calendar.MINUTE, alarmTimePicker.getMinute());
			calendar.set(Calendar.SECOND, 0);

			if (calendar.before(Calendar.getInstance())) {
				calendar.add(Calendar.DATE, 1);
			}

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");

			Intent intent = new Intent(this, alarmReceiver.class);
			intent.addCategory("D" + dateFormat.format(calendar.DATE));
			intent.putExtra("msg", "accountingNotify");

			PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

			AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
			alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
		}
	}*/

	/*public void OnToggleClicked( View view )
	{
		long time;

		if( ( (ToggleButton)view).isChecked( ) )
		{
			Toast.makeText( alarmPick.this, "ALARM ON", Toast.LENGTH_SHORT ).show( );

			Calendar calendar = Calendar.getInstance( );
			calendar.set( Calendar.HOUR_OF_DAY, alarmTimePicker.getHour() );
			calendar.set( Calendar.MINUTE, alarmTimePicker.getMinute() );

			Intent intent = new Intent( this, alarmPick.class );
			pendingIntent = PendingIntent.getBroadcast( this, 0, intent, 0 );

			time = ( calendar.getTimeInMillis() - ( calendar.getTimeInMillis() % 60000 ) );

			if( System.currentTimeMillis() > time )
			{
				if( calendar.getTimeInMillis() > time )
				{
					if( calendar.AM_PM == 0 )
					{
						time += 1000 * 60 * 60 * 12;
					}
					else
					{
						time += 1000 * 60 * 60 * 24;
					}
				}

				alarmManager.setRepeating( AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent );
			}
			else
			{
				alarmManager.cancel( pendingIntent );
				Toast.makeText( alarmPick.this, "ALARM OFF", Toast.LENGTH_SHORT ).show( );
			}
		}
	}*/
}
