package Assignment03;

import java.io.Serializable;
import java.time.LocalDate;

public class MyDate implements Cloneable, Comparable<MyDate>, Serializable
{
    //attributes
    private int year;
    private int month;
    private int day;
    
    //constructor
    public MyDate(int year, int month, int day)
    {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    //copy constructor
    public MyDate(MyDate date)
    {
        year = date.year;
        month = date.month;
        day = date.day;
    }
    
    //override clone method
    @Override
    public MyDate clone() throws CloneNotSupportedException
    {
        return (MyDate)super.clone();        
    }
    
    //accessors
    public int getYear()
    {
        return year;
    }
    
    public int getMonth()
    {
        return month;
    }
    
    public int getDay()
    {
        return day;
    }
    
    //mutators
    public void setYear(int year)
    {
        this.year = year;
    }
    
    public void setMonth(int month)
    {
        this.month = month;
    }
    
    public void setDay(int day)
    {
        this.day = day;
    }
    
    @Override
    public String toString()
    {
        return day+"/"+month+"/"+year;
    }
    
    //LAB 3 CODE
    
    //compare given date to policy expiry date and return true if given date after policy expiry date, otherwise return false
    public boolean isExpired(MyDate expiryDate)
    {
        LocalDate date1 = LocalDate.of(year, month, day); //did some research to fing this method converts to local date that can be compared
        LocalDate date2 = LocalDate.of(expiryDate.year, expiryDate.month, expiryDate.day);
        
        boolean result = date1.isAfter(date2);
        
        return result;
    }


    @Override
    public int compareTo(MyDate other) // compare based on expiry date
    { 
        LocalDate date1 = LocalDate.of(year, month, day); // create comparable local date 1
        LocalDate date2 = LocalDate.of(other.year, other.month, other.day); // create comparable local date 2
        
        if (date1.isEqual(date2))
            return 0;
        if (date1.isAfter(date2))
        {
            return 1;
        }
        else
        {
            return -1;
        } 
    }
    
    // LAB 6 CODE-----------------------------
    
    public String toDelimitedString()
    {
        return year + "," + month + "," + day;
    }
    
}
