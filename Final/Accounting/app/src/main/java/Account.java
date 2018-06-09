import android.nfc.NfcAdapter;

abstract public class Account
{
	protected double expend;		// records expanditure, in positive value
	protected double income;		// recprds incomes, in positive value
	protected String ui_id;		// For UI showcase
	protected String db_id;	// For DB retrieval

	// constructors
	Account( )
	{
		this.Init( "", 0.0, 0.0 );
	}

	Account( String id )
	{
		this.Init( id, 0.0, 0.0 );
	}

	Account( String id, double expend_init, double income_init )
	{
		this.Init( id, expend_init, income_init );
	}

	// account init for constructors
	private void Init( String id, double expend_init, double income_init )
	{
		expend = expend_init;
		income = income_init;
		ui_id = id;
		db_id = GetDbId( ui_id );
	}

	// generator for db_id
	static private String GetDbId( String ui_id )
	{
		if( ui_id.equals( "" ) )
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
	public String UI_ID( )
	{
		return ui_id;
	}

	// ui_id setter
	public boolean UI_ID( String id )
	{
		if( id.length( ) > 0 )
		{
			ui_id = id;
			return true;
		}
		else
		{
			return false;
		}
	}

	// db_id getter
	public String DB_ID( )
	{
		return db_id;
	}

	// no setter for db_id for consistance

	// TODO: add a abstract public method to perform something about accounts
}