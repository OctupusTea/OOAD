package Account;

import android.nfc.NfcAdapter;
import java.util.Date;

abstract public class Account
{
	protected double expend;	// records expanditure, in positive value
	protected double income;	// recprds incomes, in positive value
	protected String name;		// For UI showcase
	protected String id;		// For DB retrieval

	protected List<Catagory> catagoryList;

	// constructors
	Account( )
	{
		this.Init( "", 0.0, 0.0 );
	}

	Account( String name )
	{
		this.Init( name, 0.0, 0.0 );
	}

	Account( String name, double expend_init, double income_init )
	{
		this.Init( name, expend_init, income_init );
	}

	// account init for constructors
	private void Init( String name, double expend_init, double income_init )
	{
		expend = expend_init;
		income = income_init;
		this.name = name;
		this.id = GetIdFromName( name );
	}

	// generator for db_id
	static private String GetIdFromName( String name )
	{
		if( name.equals( "" ) )
		{
			return "";
		}
		else // TODO: add a proper algorithm to avoid repeating db_id
		{
			return "the_db_id";
		}
	}

	// expend getter
	public double Expend( )
	{
		return expend;
	}

	// expend adder
	public void Add_Expend( double change_value )
	{
		expend += change_value;
	}

	// income getter
	public double Income( )
	{
		return income;
	}

	// income adder
	public void Add_Income( double change_value )
	{
		income += change_value;
	}

	// return balance of expenditure and income
	public double AccountBalance( )
	{
		return income - expend;
	}

	// ui_id getter
	public String Name( )
	{
		return name;
	}

	// ui_id setter
	public boolean Rename( String name )
	{
		if( name.length( ) > 0 )
		{
			this.name = name;
			return true;
		}
		else
		{
			return false;
		}
	}

	// db_id getter
	public String ID( )
	{
		return id;
	}

	// no setter for db_id for consistance

	// TODO: add a abstract public method to perform something about accounts

	abstract public void SetRecord( Date recordDate, String recordDescription, double recordExpense );
}