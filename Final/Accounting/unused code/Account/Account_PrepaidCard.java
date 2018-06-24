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

	// expend adder, income adder and balance adder override
	@Override
    public void Add_Expend( double change_value )
	{
		expend += change_value;
		availableBalance -= change_value;
	}

	@Override
	public void Add_Income( double change_value )
    {
        income += change_value;
        availableBalance += change_value;
    }

    public void Add_Balance( double change_value )
    {
        availableBalance += change_value;
    }

	// check balance, only for prepaid card
	public boolean OutOfBalance( )
	{
		return availableBalance <= 0;
	}
}
