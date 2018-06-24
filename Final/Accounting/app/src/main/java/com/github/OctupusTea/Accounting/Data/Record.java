package com.github.OctupusTea.Accounting.Data;

import java.lang.reflect.Field;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import android.util.*;

import com.github.OctupusTea.Accounting.SQLite.*;

import static com.github.OctupusTea.Accounting.AccountingApp.getContext;

public class Record {
    private int id;
    private String accountName;
    private String categoryName;
    private String date;
    private String currencyType;
    private double cost;

	public Record( )
    {
        this.Init( 0, "","",getDate( ),"",0.0 );
    }

    public Record( Record record )
	{
		this.Init( record.id, record.accountName, record.categoryName, record.date, record.currencyType, record.cost );
	}

    public Record( String accountName, String categoryName )
    {
        this.Init( 0, accountName, categoryName, getDate( ), "", 0.0 );
    }

    public Record( String accountName, String categoryName, double cost )
    {
        this.Init( 0, accountName, categoryName, getDate( ), "", cost );
    }

    public Record( String accountName, String categoryName, String date )
    {
        this.Init( 0, accountName, categoryName, date, "", 0.0 );
    }

    public Record( String accountName, String categoryName, String date, double cost )
    {
        this.Init( 0, accountName, categoryName, date, "", cost );
    }

    public Record( int id, String accountName, String categoryName, String date, double cost )
    {
        this.Init( id, accountName, categoryName, date, "", cost );
    }

    private void Init( int id, String accountName, String categoryName, String date, String currencyType, double cost )
    {
        this.id = id;
        this.accountName = accountName;
        this.categoryName = categoryName;
        this.date = date;
        this.currencyType = currencyType;
        this.cost = cost;
    }

    public int getId( ) {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName( ) {
        return accountName;
    }

    public void setAccountName( String accountName ) {
        this.accountName = accountName;
    }

    public String getCategoryName( ) {
        return categoryName;
    }

    public void setCategoryName( String categorayName ) {
        this.categoryName = categorayName;
    }

    private void setDate( ) {
        Date date = new Date();
        SimpleDateFormat timeFormating = new SimpleDateFormat("yyyy-MM-dd");
        this.date = timeFormating.format(date);
    }

    public boolean setDate( String date )
    {
        SimpleDateFormat dateValidator = new SimpleDateFormat( "yyyy-MM-dd" );
        try
        {
            dateValidator.parse( date );
        }
        catch( ParseException e ) // if ill-formated  date string
        {
            return false;
        }

        this.date = date;
        return true;
    }

    public String getDate( ) {
        return this.date;
    }

    public String getCurrencyType( ) {
        return currencyType;
    }

    public void setCurrencyType( String currencyType ) {
        this.currencyType = currencyType;
    }

    public double getCost( ) {
        return cost;
    }

    public boolean setCost( double cost )
	{
        this.cost = cost;
        return true;
    }

    public boolean setCost( String cost )
	{
		try
		{
			this.cost = Double.parseDouble( cost );
		}
		catch( NumberFormatException e )
		{
			return false;
		}

		return true;
	}

    // functions for create, modify or delete a record.
    // creator: use constructor instead
    // single attribute modifier
    public boolean Modify( String modifyAttr, String modifyValue ) throws InvalidParameterException
    {
        DBFunction db = new DBFunction( getContext( ) );
        // TODO: fetch data from DB and modify (single attribute)

        if( modifyAttr.equalsIgnoreCase( "accountName" ) )
		{
			setAccountName( modifyValue );
			return true;
		}
		else if( modifyAttr.equalsIgnoreCase( "categoryName" ) )
		{
			setCategoryName( modifyValue );
			return true;
		}
		else if( modifyAttr.equalsIgnoreCase("date") )
		{
			return setDate( modifyValue );
		}
		else if( modifyAttr.equalsIgnoreCase( "currencyType") )
		{
			setCurrencyType( modifyValue );
			return true;
		}
		else if( modifyAttr.equalsIgnoreCase( "cost" ) )
		{
			return setCost( modifyValue );
		}
		else
		{
			return false;
		}
    }

    // multiple attribute modifier
    public boolean Modify( List< Pair< String, String > > modifyList )
	{
		Record record = new Record( this );

		for( Pair<String,String> i : modifyList )
		{
			if( !record.Modify( i.first, i.second ) )
			{
				return false;
			}
		}

		Init( record.id, record.accountName, record.categoryName, record.date, record.currencyType, record.cost );
		return true;
    }

    public boolean Delete( Record record )
    {
        DBFunction db = new DBFunction( getContext( ) );

        return db.delete( record );
    }
}
