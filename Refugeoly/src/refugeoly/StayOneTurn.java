package refugeoly;


public class StayOneTurn extends Action
{
    
    @Override
    public void act(Refugee r)  //Η StayOneTurn υλοποιείται αρκετά διαφορετικα από τις άλλες Action-related κλάσεις
    {
        
    }
    
    public int stay(int n)
    {
        if (n==8)
        {
            return 1;
        }
        else if (n==11)
        {
            return 1;
        }
        else if (n==14)
        {
            return 1;
        }
        else if (n==19)
        {
            return 1;
        }
        else if (n==24)
        {
            return 1;
        }
        else if (n==26)
        {
            return 2;
        }
        else if (n==27)
        {
            return 1;
        }
        else if (n==32)
        {
            return 1;
        }
        else if (n==34)
        {
            return 1;
        }
        else
        {
            return 0;
        }
        
    }
    
    
    
    
}
