abstract public class Account
{
	private double expend;
	private double income;
	private String ui_id;		// For UI showcase
	const private String db_id;	// For DB retrieval

	// expend getter
	public double Expend( void )
	{
		return expend;
	}

	// expend setter
	public boolean Expend( double expend )
	{
		if( expend >= 0.0 )
		{
			this.expend = expend;
			return true;
		}
		else
		{
			return false;
		}
	}

	// expend adder
	public void Add_Expend( double change_value )
	{
		expend += change_value;
	}

	// income getter
	public double Income( void )
	{
		return income;
	}

	// income setter
	public boolean Income( double income )
	{
		if( income >= 0.0 )
		{
			this.income = income;
			return true;
		}
		else
		{
			return false;
		}
	}

	// income adder
	public void Add_Income( double change_value )
	{
		income += change_value;
	}

	// return balance of expenditure and incom
	public double Balance( void )
	{
		return income - expend;
	}

	// ui_id getter
	public String UI_ID( void )
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
	public String DB_ID( void )
	{
		return db_id;
	}

	// no setter for db_id for consistance

	// TODO
	// add a abstract public method to perform something about accounts
}