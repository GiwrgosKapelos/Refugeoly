package refugeoly;

import java.util.*;


public class Square
{
    private String text;
    private int number;
    private final int board=1;  //Δεν αλλάζει ποτέ - Υπάρχει μόνο ένα ταμπλό
    ArrayList <Action> actions = new ArrayList<Action>();
    
    
    
    
    public void setText(String s)
    {
        text=s;
    }
    
    public String getText()
    {
        return text;
    }
    
    
    public void setNumber(int n)
    {
        number=n;
    }
    
    public int getNumber()
    {
        return number;
    }
    
    
    public void setAction(Action act)
    {
        actions.add(act);    //Το Action μπαίνει στο ArrayList
    }
}
