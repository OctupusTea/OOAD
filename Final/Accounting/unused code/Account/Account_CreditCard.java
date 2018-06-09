package Account;

public class Account_CreditCard extends Account
{
	private double credits; // credits for a credit card, positive value

	// credits getter
	public double Credits( )
	{
		return credits;
	}

	// credits setter
	public boolean Credits( double credits )
	{
		if( credits >= 0 )
		{
			this.credits = credits;
			return true;
		}
		else
		{
			return false;
		}
	}

	// check credits, only for credit cards
	public boolean OutOfCredits( )
	{
		return AccountBalance( ) + credits >= 0;
	}
}