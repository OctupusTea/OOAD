package com.github.OctupusTea.Accounting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

public class alarmSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmsetting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button remindersettingBtn = (Button) findViewById(R.id.button21);
        remindersettingBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(alarmSetting.this, MainActivity.class);
                startActivity(intent);
            }
        });

		SharedPreferences alarmPreferences = getSharedPreferences( "alarmPreferences", MODE_PRIVATE );
		String[ ] alarmTime = new String[ 5 ];
		for( int i = 0; i < 5; ++i )
		{
			alarmTime[ i ] = alarmPreferences.getString( "alarmTime" + i, "" );
			if( alarmTime[ i ].equals( "" ) )
			{
				alarmPreferences.edit( ).putString( "alarmTime" + i, "08:00" );
			}
		}
		alarmPreferences.edit( ).commit( );

        Switch[ ] switches = new Switch[ 5 ];
        int[ ] switches_idList = { R.id.switch0, R.id.switch1, R.id.switch2, R.id.switch3, R.id.switch4 };
        for( int i = 0; i < 5; ++i )
		{
			switches[ i ] = findViewById( switches_idList[ i ] );
			switches[ i ].setText( alarmTime[ i ] );
		}
    }
}
