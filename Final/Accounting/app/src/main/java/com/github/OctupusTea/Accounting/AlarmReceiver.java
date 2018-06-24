package com.github.OctupusTea.Accounting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class alarmReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		Bundle bData = intent.getExtras();

		if( bData.get("msg").equals("accountingNotify") )
		{

		}
	}
}
