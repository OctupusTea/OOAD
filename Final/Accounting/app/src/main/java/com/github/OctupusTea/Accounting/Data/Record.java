package com.github.OctupusTea.Accounting.Data;

import java.lang.ref.SoftReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import android.util.*;
import android.content.Context;

import com.github.OctupusTea.Accounting.AccountingApp;
import com.github.OctupusTea.Accounting.SQLite.*;

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

    public void setDate( String date )
    {
        SimpleDateFormat dateValidator = new SimpleDateFormat( "yyyy-MM-dd" );
        try
        {
            dateValidator.parse( date );
        }
        catch( ParseException e ) // if ill-formated  date string
        {
            return;
        }

        this.date = date;
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

    public void setCost( double cost ) {
        this.cost = cost;
    }

    // functions for create, modify or delete a record.
    // creator: use constructor instead
    // single attribute modifier
    static public Record Modify( Record record, String modifyAttr, String modifyValue )
    {
        AccountingDBHelper dbHelper = AccountingDBHelper.getDBHelper( AccountingApp.getContext( ) );
        // TODO: fetch data from DB and modify (single attribute)
        return new Record( );
    }

    // multiple attribute modifier
    static public Record Modify(Record record, List< Pair< String, String > > modifyList )
    {
        // TODO: fetch data from DB and modify (multiple attribute)
        /** taking efficiency into consideration, not using multiple "single Modify( )". **/
        return new Record( );
    }

    static public Record Delete( Record record )
    {
        // TODO: send query to DB and delete entry
        return new Record( );
    }
}
