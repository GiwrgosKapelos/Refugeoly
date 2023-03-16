package refugeoly;


public class Refugee extends Game
{
    
    private String name;
    private double money=10000;
    private final int board=1;     //Δεν αλλάζει ποτέ - Υπάρχει μόνο ένα ταμπλό
    private int square=0;         //Κάθε παίκτης ξεκινάει από το τετράγωνο 0
    private double expenses=0;
    
    
    public void setName(String n)
    {
        name=n;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setMoney(double m)
    {
        money=m;
    }
    
    public double getMoney()
    {
        return money;
    }
    
    public void receiveMoney(double m)
    {
        money=money+m;
    }
    
    
    public void setSquare(int s)
    {
        square=s;
    }
    
    public void moveTo(int s)
    {
        if (square+s<=39)
        {
            square=square+s;
        }
        else
        {
            int goback=0;
            
            for (int i=1 ; i<=s ; i++)
            {
                
                if (square+1<=39 && goback==0)
                {
                    square=square+1;
                }
                else
                {
                    square=square-1;
                    goback=1;
                }
            }
        }
    }
    
    public int getSquare()
    {
        return square;
    }
    
    
    public void setExpenses(double e)
    {
        expenses=e;
    }
    
    public void incExpenses(double e)
    {
        expenses=expenses+e;
    }
    
    public double getExpenses()
    {
        return expenses;
    }
    
    public double giveMoney(double exp)
    {
        if (exp>money)
        {
            System.out.printf("You have gone bankrupted! You lost! The other refugees shall continue living without you...\n");
            System.out.printf("Total money spent: %f\n\n", expenses);
            return -1;
        }
        else
        {
           money=money-exp;
           return exp;
        }
        
    }
}
