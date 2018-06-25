package com.github.OctupusTea.Accounting;

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

        alarmPreferences = getSharedPreferences("alarmPreferences", MODE_PRIVATE);

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
						unsetAlarm( buttonView );
					}
				}
			});
		}


		for( int i = 0; i < 5; ++i )
		{
			Button alarmButton = findViewById( alarmButtons_idList[ i ] );
			String dateString = alarmPreferences.getString( "alarmDate" + i, "" );
			Date alarmDate = new Date( );

			try
			{
				alarmDate = rawAlarmFormat.parse( dateString );
			}
			catch( ParseException e )
			{
				alarmDate = Calendar.getInstance( ).getTime( );
				alarmPreferences.edit( ).putString( "alarmDate" + alarmButton.getId( ), rawAlarmFormat.format( alarmDate ) );
			}

			alarmButton.setText( timeFormat.format( alarmDate ) );
		}

		alarmPreferences.edit().commit();
	}

    public void onClick( View view )
	{
		Intent intent = new Intent();
		intent.setClass(AlarmSetting.this, AlarmPick.class );
		intent.putExtra( "callerID", view.getId() );
		startActivityForResult( intent, 0xFF0E );
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
				Date alarmDate;

				try
				{
					alarmDate = rawAlarmFormat.parse( dateString );
				}
				catch( ParseException e )
				{
					alarmDate = Calendar.getInstance( ).getTime( );
				}

				alarmPreferences.edit().putString("alarmDate" + callerID, rawAlarmFormat.format( alarmDate ) );
				alarmButton.setText( timeFormat.format( alarmDate ) );

				alarmPreferences.edit( ).commit( );
			}
		}
	}

	public int getSwitchOrder( CompoundButton buttonView )
	{
		int switchOrder = 0;

		while (alarmSwitches_idList[switchOrder] != buttonView.getId())
		{
			++switchOrder;
		}

		return switchOrder;
	}

	public void setAlarm( CompoundButton buttonView )
	{
		int switchOrder = getSwitchOrder( buttonView );
	}

	public void unsetAlarm( CompoundButton buttonView )
	{
		int switchOrder = getSwitchOrder( buttonView );
	}
}
