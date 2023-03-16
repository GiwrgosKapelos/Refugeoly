package refugeoly;

import java.util.*;


public class RollDiceAction extends Action
{
    private int dice;
    Scanner input = new Scanner(System.in);
    
    @Override
    public void act(Refugee r)
    {
        if (r.getSquare()==2)
        {
            do
            {
                System.out.printf("Current Player rolls the dice again: ");
                dice=input.nextInt();
                input.nextLine();
                System.out.printf("\n");
                
            }while(dice<0 || dice>6);
            
            r.moveTo(dice);
        }
        else if (r.getSquare()==9)
        {
            
        }
        else if (r.getSquare()==12)
        {
            
        }
        else if (r.getSquare()==17)
        {
            
        }
        else if (r.getSquare()==22)
        {
            
        }
        else if (r.getSquare()==28)
        {
            
        }
        else if (r.getSquare()==31)
        {
            
        }
        else if (r.getSquare()==33)
        {
            
        }
    }
    
    public int rollDice(int n)
    {
        if (n==2)
        {
            return 1;
        }
        else if (n==9)
        {
            return 1;
        }
        else if (n==12)
        {
            return 1;
        }
        else if (n==17)
        {
            return 1;
        }
        else if (n==22)
        {
            return 1;
        }
        else if (n==28)
        {
            return 1;
        }
        else if (n==31)
        {
            return 1;
        }
        else if (n==33)
        {
            return 1;
        }
        else
        {
            return 0;
        }
        
    }
    
    
}
