abstract public class Account
{
	private double expend;
	private double income;

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

	// TODO
	// add a abstract public method to perform something about accounts
}