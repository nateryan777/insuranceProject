package Assignment03;

import java.io.Serializable;

public class Address implements Cloneable, Comparable<Address>, Serializable
{
    //attributes
    private int streetNum;
    private String street;
    private String suburb;
    private String city;
    
    //constructor
    public Address(int streetNum, String street, String suburb, String city)
    {
        this.streetNum = streetNum;
        this.street = street;
        this.suburb = suburb;
        this.city = city;
                
    }
    
    //copy constructor
    public Address(Address address)
    {
        streetNum = address.streetNum;
        street = address.street;
        suburb = address.suburb;
        city = address.city;
    }
    
    //override clone method
    @Override
    public Address clone() throws CloneNotSupportedException
    {
        return (Address)super.clone();  
    }
    
    //accessors
    public int getStreetNum()
    {
        return streetNum;
    }
    
    public String getStreet()
    {
        return street;
    }
    
    public String getSuburb()
    {
        return suburb;
    }
    
    public String getCity()
    {
        return city;
    }
    
    //mutators
    public void setStreetNum(int streetNum)
    {
        this.streetNum = streetNum;
    }
    
    public void setStreet(String street)
    {
        this.street = street;
    }
    
    public void setSuburb(String suburb)
    {
        this.suburb = suburb;
    }
    
    public void setCity(String city)
    {
        this.city = city;
    }
    @Override
    public String toString() //added this for easier printing
    {
        return streetNum+" "+street+", "+suburb+", "+city;
    }
    
    @Override
    public int compareTo(Address other)
    {
        return this.city.compareTo(other.city);
    }
    
    // LAB 6 CODE---------------------------------------
    
    public String toDelimitedString()
    {
        return streetNum + "," + street + "," + suburb + "," + city;
    }
}
