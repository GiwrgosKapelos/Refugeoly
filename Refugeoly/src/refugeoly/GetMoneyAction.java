package refugeoly;


public class GetMoneyAction extends Action
{
    
    @Override
    public void act(Refugee r)
    {
        if (r.getSquare()==20)
        {
            r.receiveMoney(1000);
        }
        
    }
    
    
}
