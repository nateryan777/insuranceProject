package Assignment03;

import java.io.Serializable;
import java.util.Random;

public class PolicyException extends Exception implements Serializable
{
    
    private int id;
    
    public PolicyException(int id)
    {
        this.id = generateID();
    }
    
    public int getNewID()
    {
        return id;
    }
    
    public int generateID()
    {
        // genrate random number in format 3XXXXXX
        Random rand = new Random();
        id = 3000000 + rand.nextInt(999999);
        
        return id;
    }
    
    @Override
    public String toString()
    {
        return "Invalid Policy ID.\n";
    }
}
