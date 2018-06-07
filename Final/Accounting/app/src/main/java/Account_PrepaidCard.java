public class Account_PrepaidCard extends Account
{
    private double availableBalance; // balance deposited in the prepaid card


    // balance getter
    public double AvailableBalance( void )
    {
        return availableBalance;
    }

    // balance setter
    public boolean AvailableBalance( double availableBalance )
    {
        this.availableBalance = availableBalance;
    }

    // balance adder
    public void Add_AvailableBalance( double change_value )
    {
        availableBalance += change_value;
    }
}
