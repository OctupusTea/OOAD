package Account;

public class Account_PrepaidCard extends Account
{
	private double availableBalance; // balance deposited in the prepaid card

	// balance getter
	public double AvailableBalance( )
	{
		return availableBalance;
	}

	// balance adder
	public void Deposit(double change_value) {
		availableBalance += change_value;
	}

	// override: expend adder, income adder and balance adder
	public void Add_Expend( double change_value )
	{
		expend += change_value;
		availableBalance -= change_value;
	}

	// check balance, only for prepaid card
	public boolean OutOfBalance( )
	{
		return availableBalance <= 0;
	}
}
