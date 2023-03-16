package refugeoly;


public class GoToAction extends Action
{
    
    
    @Override
    public void act(Refugee r)
    {
        if (r.getSquare()==4)
        {
            r.setSquare(0);
        }
        else if (r.getSquare()==5)
        {
            r.setSquare(0);
        }
        else if (r.getSquare()==15)
        {
            r.setSquare(5);
        }
        else if (r.getSquare()==18)
        {
            r.setSquare(22);
        }
        else if (r.getSquare()==23)
        {
            r.setSquare(29);
        }
        else if (r.getSquare()==25)
        {
            r.setSquare(15);
        }
        else if (r.getSquare()==29)
        {
            r.setSquare(31);
        }
        else if (r.getSquare()==30)
        {
            r.setSquare(24);
        }
        else if (r.getSquare()==33)
        {
            r.setSquare(17);
        }
        else if (r.getSquare()==35)
        {
            r.setSquare(25);
        }
        else if (r.getSquare()==38)
        {
            r.setSquare(0);
        }
        
    }
    
    
    
}
