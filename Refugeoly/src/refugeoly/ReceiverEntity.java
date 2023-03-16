package refugeoly;


public class ReceiverEntity extends MoneyReceiver
{
    
    private String name;
    private double money=0;
    
    
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
    public void receiveMoney(double m)
    {
        money=money+m;
    }
    
    
}
