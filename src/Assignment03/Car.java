package Assignment03;

import java.io.Serializable;

enum CarType {SUV, SED, LUX, HATCH, WAGON, FOURWD, COUPE, UTE}

public class Car implements Cloneable, Serializable
{
    //attributes
    private CarType type;
    private String model;
    private int manufacturingYear;
    private double price;
    
    //constructor
    public Car(CarType type, String model, int manufacturingYear, double price)
    {
        this.type = type;
        this.model = model;
        this.manufacturingYear = manufacturingYear;
        this.price = price;
    }
    
    //copy contructor
    public Car(Car car)
    {
        type = car.type;
        model = car.model;
        manufacturingYear = car.manufacturingYear;
        price = car.price;
    }
    
    //override clone method
    @Override
    public Car clone() throws CloneNotSupportedException
    {
        return (Car)super.clone();
    }
    
    public String toString()
    {
        return manufacturingYear+" "+model+" "+type+" Price: $"+price;
    }
    
    //accessors
    public CarType getType()
    {
        return type;
    }
    
    public String getModel()
    {
        return model;
    }
    
    public int getManufacturingYear()
    {
        return manufacturingYear;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    //mutators
    public void setType(CarType type)
    {
        this.type = type;
    }
    
    public void setModel(String model)
    {
        this.model = model;
    }
    
    public void setManufacturingYear(int manufacturingYear)
    {
        this.manufacturingYear = manufacturingYear;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    //car price rise method
    public double priceRise(double risePercent)
    {  
        return price*(1+risePercent);
    }
    
    // LAB 6 CODE---------------------------
    
    public String toDelimitedString()
    {
        return type + "," + model + "," + manufacturingYear + "," + price;
    }
}
