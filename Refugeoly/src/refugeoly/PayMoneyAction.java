package refugeoly;


public class PayMoneyAction extends Action
{
    
    
    @Override
    public void act(Refugee r)
    {
        if (r.getSquare()==1)
        {
            r.incExpenses(100);
        }
        else if (r.getSquare()==3)
        {
            r.incExpenses(300);
        }
        else if (r.getSquare()==6)
        {
            r.incExpenses(1000);
        }
        else if (r.getSquare()==9)
        {
            r.incExpenses(3000);
        }
        else if (r.getSquare()==13)
        {
            r.incExpenses(200);
        }
        else if (r.getSquare()==16)
        {
            r.incExpenses(500);
        }
        else if (r.getSquare()==21)
        {
            r.incExpenses(1500);
        }
        else if (r.getSquare()==26)
        {
            r.incExpenses(1500);
        }
        else if (r.getSquare()==31)
        {
            r.incExpenses(800);
        }
        else if (r.getSquare()==37)
        {
            r.incExpenses(1000);
        }
        
        
    }
    
    
}
