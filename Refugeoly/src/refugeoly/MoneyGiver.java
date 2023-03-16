package refugeoly;


public abstract class MoneyGiver extends Game
{
    public abstract void setName(String n);
    public abstract String getName();
    public abstract void setMoney(double m);
    public abstract double getMoney();
    public abstract double giveMoney(double m) throws NoMoneyException;
     
}
