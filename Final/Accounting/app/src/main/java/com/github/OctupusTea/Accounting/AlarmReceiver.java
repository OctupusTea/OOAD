package com.github.OctupusTea.Accounting;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		Bundle bData = intent.getExtras();

		if( bData.get("msg").equals("accountingNotify") )
		{
			NotificationHelper notificationHelper = new NotificationHelper( context );
			notificationHelper.createNotification( "Accounting App", "記帳記起來！", "說好提醒現在來的對吧？" );
		}
	}
}

class NotificationHelper
{
	private Context mContext;
	private NotificationManager mNotificationManager;
	private NotificationCompat.Builder mBuilder;

	static public final String NOTIFICATION_CHANNEL_ID = "10001";

	public NotificationHelper( Context context )
	{
		mContext = context;
	}

	public void createNotification( String title, String message, String info )
	{
		Intent resultIntent = new Intent( mContext, AlarmReceiver.class );
		resultIntent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );

		PendingIntent resultPendingIntent = PendingIntent.getActivity( mContext, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT );

		mBuilder = new NotificationCompat.Builder( mContext, NOTIFICATION_CHANNEL_ID );
		mBuilder.setSmallIcon( R.mipmap.ic_launcher );
		mBuilder.setContentTitle( title )
				.setContentText( message )
				.setContentInfo( info )
				.setAutoCancel( false )
				.setSound( Settings.System.DEFAULT_NOTIFICATION_URI )
				.setContentIntent( resultPendingIntent );

		mNotificationManager = ( NotificationManager ) mContext.getSystemService( Context.NOTIFICATION_SERVICE );

		if( Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O )
		{
			@SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel( NOTIFICATION_CHANNEL_ID, "Accounting", NotificationManager.IMPORTANCE_DEFAULT );
			notificationChannel.enableLights( true );
			notificationChannel.setLightColor( Color.rgb( 255, 127, 0 ) );
			notificationChannel.enableVibration( true );
			notificationChannel.setVibrationPattern( new long[ ] { 100, 100, 100 } );

			assert mNotificationManager != null;

			mBuilder.setChannelId( NOTIFICATION_CHANNEL_ID );
			mNotificationManager.createNotificationChannel( notificationChannel );
		}

		assert mNotificationManager != null;
		mNotificationManager.notify( 0, mBuilder.build( ) );
	}
}
