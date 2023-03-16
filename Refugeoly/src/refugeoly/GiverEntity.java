package refugeoly;


public class GiverEntity extends MoneyGiver
{
    
    private String name;
    private double money=10000;
    private NoMoneyException NoMoneyException = new NoMoneyException("INSUFFICIENT N.G.O. FUNDS! CANNOT PAY PLAYER!!!\n\n");
    
    
    @Override
    public void setName(String n)
    {
        name=n;
    }
    
    @Override
    public String getName()
    {
        return name;
    }
    
    
    @Override
    public void setMoney(double m)
    {
        money=m;
    }
    
    @Override
    public double getMoney()
    {
        return money;
    }
    
    @Override
    public double giveMoney(double m) throws NoMoneyException
    {
        
        if (m<=money)
        {
            money=money-m;
            return m;
        }
        else
        {
            throw NoMoneyException;
        }
        
    }
    
}
