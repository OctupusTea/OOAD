public class Account_CreditCard extends Account
{
    private double credits; // credits for a credit card

    // credits getter
    public double Credits( void )
    {
        return credits;
    }

    // credits setter
    public void boolean Credits( double credits )
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

}
