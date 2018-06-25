package com.github.OctupusTea.Accounting;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AlarmSetting extends AppCompatActivity {

	Date[ ] alarmDate = new Date[ 5 ];
	static final SimpleDateFormat rawAlarmFormat = new SimpleDateFormat( "yyyy-MM-dd-HH:mm" );
	static final SimpleDateFormat timeFormat = new SimpleDateFormat( "h:mm a" );
	SharedPreferences alarmPreferences;

	int[ ] alarmButtons_idList = {R.id.alarmButton0, R.id.alarmButton1, R.id.alarmButton2, R.id.alarmButton3, R.id.alarmButton4};
	int[ ] alarmSwitches_idList = { R.id.alarmSwitch0, R.id.alarmSwitch1, R.id.alarmSwitch2, R.id.alarmSwitch3, R.id.alarmSwitch4 };

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        alarmPreferences = getSharedPreferences("alarmPreferences", MODE_PRIVATE );

        Button alarmConfirmButton = (Button) findViewById(R.id.alarmConfirm );
        alarmConfirmButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AlarmSetting.this, MainActivity.class);
                startActivity(intent);
            }
        });

        for( int i = 0; i < 5; ++i )
		{
			Switch alarmSwitch = findViewById( alarmSwitches_idList[ i ] );

			boolean switched = alarmPreferences.getBoolean( "alarmSet" + i, false );
			alarmSwitch.setChecked( switched );

			alarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
				{
					if ( isChecked )
					{
						setAlarm( buttonView );
					}
					else
					{
						cancelAlarm( buttonView );
					}
				}
			});
		}


		for( int i = 0; i < 5; ++i )
		{
			Button alarmButton = findViewById( alarmButtons_idList[ i ] );
			String dateString = alarmPreferences.getString( "alarmDate" + i, "" );

			try
			{
				alarmDate[ i ] = rawAlarmFormat.parse( dateString );
			}
			catch( ParseException e )
			{
				alarmDate[ i ] = Calendar.getInstance( ).getTime( );
				alarmPreferences.edit( ).putString( "alarmDate" + i, rawAlarmFormat.format( alarmDate[ i ] ) )
								.apply( );
			}

			alarmButton.setText( timeFormat.format( alarmDate[ i ] ) );
		}
	}

    public void onClick( View view )
	{
		Intent intent = new Intent();
		intent.setClass(AlarmSetting.this, AlarmPick.class );
		intent.putExtra( "callerID", view.getId() );
		startActivityForResult( intent, 0xFF0E );
	}

	private int getButtonOrder( Button buttonView )
	{
		int buttonOrder = 0;

		while( alarmButtons_idList[ buttonOrder ] != buttonView.getId( ) )
		{
			++buttonOrder;
		}

		return buttonOrder;
	}

	public void onActivityResult( int requestCode, int resultCode, Intent data )
	{
		if( requestCode == 0xFF0E )
		{
			if( resultCode == RESULT_OK )
			{
				String dateString = data.getStringExtra( "date" );
				int callerID = data.getIntExtra( "callerID", 0xFFFFFFFF );
				Button alarmButton = findViewById( callerID );
				int buttonOrder = getButtonOrder( alarmButton );

				try
				{
					alarmDate[ buttonOrder ] = rawAlarmFormat.parse( dateString );
				}
				catch( ParseException e )
				{
					alarmDate[ buttonOrder ] = Calendar.getInstance( ).getTime( );
				}

				alarmPreferences.edit( ).putString("alarmDate" + buttonOrder, rawAlarmFormat.format( alarmDate[ buttonOrder ] ) )
								.apply( );
				alarmButton.setText( timeFormat.format( alarmDate[ buttonOrder ] ) );
			}
		}
	}

	private int getSwitchOrder( CompoundButton buttonView )
	{
		int switchOrder = 0;

		while ( alarmSwitches_idList[ switchOrder ] != buttonView.getId( ) )
		{
			++switchOrder;
		}

		return switchOrder;
	}

	public void setAlarm( CompoundButton buttonView )
	{
		int switchOrder = getSwitchOrder( buttonView );

		alarmPreferences.edit().putBoolean("alarmSet" + switchOrder, true)
						.apply();


		Calendar calendar = Calendar.getInstance();
		calendar.setTime( alarmDate[ switchOrder ] );
/*
		calendar.set(Calendar.HOUR_OF_DAY,  );
		calendar.set(Calendar.MINUTE, alarmTimePicker.getMinute());
		calendar.set(Calendar.SECOND, 0);
*/
		if ( calendar.before( Calendar.getInstance( ) ) )
		{
			calendar.add( Calendar.DATE, 1 );
		}

		Intent intent = new Intent(this, AlarmReceiver.class);
		intent.addCategory("D" + rawAlarmFormat.format( calendar.getTime() ) );
		intent.putExtra("msg", "accountingNotify" );

		PendingIntent pendingIntent = PendingIntent.getBroadcast( getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT );

		AlarmManager alarmManager = ( AlarmManager ) getSystemService( Context.ALARM_SERVICE );
		alarmManager.setInexactRepeating( AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis(), 1000 * 60, pendingIntent );
	}

	public void cancelAlarm(CompoundButton buttonView )
	{
		int switchOrder = getSwitchOrder( buttonView );

		alarmPreferences.edit().putBoolean("alarmSet" + switchOrder, false)
						.apply();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime( alarmDate[ switchOrder ] );

		Intent intent = new Intent( this, AlarmReceiver.class );
		intent.addCategory( "D" + rawAlarmFormat.format( calendar.getTime() ) );
		intent.putExtra( "msg", "accountingNotify" );

		PendingIntent pendingIntent = PendingIntent.getBroadcast( getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT );

		AlarmManager alarmManager = ( AlarmManager ) getSystemService( Context.ALARM_SERVICE );
		alarmManager.cancel( pendingIntent );
	}
}
