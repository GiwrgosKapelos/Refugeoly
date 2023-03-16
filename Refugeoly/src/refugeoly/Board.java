
package refugeoly;

import java.util.ArrayList;


public class Board extends Game
{
    private Square sq = new Square();
    private ArrayList<Square> squares = new ArrayList<Square>();
    
    
    public void addSquare(Square sq)
    {
        this.sq=sq;
        
        squares.add(sq);  //Το τετράγωνο μπαίνει στο ArrayList
    }
    
    
    public Square getSquare(int n)
    {
        return squares.get(n);
    }
    
    
    public void addSquare(String s, int n)
    {
        Square sq = new Square();
        
        sq.setText(s);
        sq.setNumber(n);
        
        
        squares.add(sq);
        
    }
    
    
    /*
    public void setLivesandTurn(int L, int T)
    {
        Lives[T+1]=L;
        
        Turn=T;
    }
    */

}  

