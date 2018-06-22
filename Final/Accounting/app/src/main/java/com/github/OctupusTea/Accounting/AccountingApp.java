package com.github.OctupusTea.Accounting;

import android.app.Application;
import android.content.Context;

public class AccountingApp extends Application {
	private static Application sApplication;

	public static Application getApplication()
	{
		return sApplication;
	}

	public static Context getContext( )
	{
		return sApplication.getApplicationContext( );
	}

	@Override
	public void onCreate( )
	{
		super.onCreate( );
		sApplication = this;
	}
}
