package Assignment03;

public class ComprehensivePolicy extends InsurancePolicy
{
    //attributes
    private int driverAge; 
    private int level;
    
    //constructor
    public ComprehensivePolicy(String policyHolderName, int id, Car car,int numberOfClaims, MyDate expiryDate, int driverAge, int level) throws PolicyException, NameException
    {
        super(policyHolderName, id, car, numberOfClaims, expiryDate);
        this.driverAge = driverAge;
        this.level = level;
    }
    
    //copy constructor
    public ComprehensivePolicy(ComprehensivePolicy policy)
    {
        super(policy);
        driverAge = policy.driverAge;
        level = policy.level;
    }
    
    @Override //optional syntax used to help prevent errors when compiling
    public void print()
    {
        super.print();
        System.out.println(" Driver Age; "+driverAge+" Level: "+level);
    }
    
    public int getDriverAge()
    {
        return driverAge;
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public void setDriverAge(int age)
    {
        this.driverAge = age;
    }
    
    public void setLevel(int level)
    {
        this.level = level;
    }
    
    @Override
    public double calcPayment(int flatRate)
    {
        double compPremium = car.getPrice()/50+numberOfClaims*200+flatRate;
        if (driverAge<30)
        {
            compPremium = compPremium+(30-driverAge)*50;
        }
        return compPremium;
    }
    
    @Override
    public String toString(){
        return super.toString()+" Driver Age: "+driverAge+" Level: "+level;
    }
    
    // LAB 6 CODE --------------------------------------
    
    @Override
    public String toDelimitedString()
    {
        return "CP" + "," + policyHolderName + "," + id + "," + car.toDelimitedString() + "," + numberOfClaims + "," + expiryDate.toDelimitedString() + "," + driverAge + "," + level;
    }
 
}
