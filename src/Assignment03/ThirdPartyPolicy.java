package Assignment03;

public class ThirdPartyPolicy extends InsurancePolicy
{
    private String comments;
    
    //constructor
    public ThirdPartyPolicy(String policyHolderName, int id, Car car,int numberOfClaims, MyDate expiryDate, String comments) throws PolicyException, NameException
    {
        
        super(policyHolderName, id, car, numberOfClaims, expiryDate);
        this.comments = comments;
    }
    
    //copy constructor
    public ThirdPartyPolicy(ThirdPartyPolicy policy)
    {
        super(policy);
        comments = policy.comments;
    }
    
    public String getComments()
    {
        return comments;
    }
    
    public void setComments(String comments)
    {
        this.comments = comments;
    }
    
    @Override
    public void print()
    {
        super.print();
        System.out.println(" Comments: "+comments);
    } 
    @Override
    public double calcPayment(int flatRate)
    {
       return car.getPrice()/100+numberOfClaims*200+flatRate;
    }
    @Override
    public String toString(){
        return super.toString()+" Comments: "+comments;
    }
    
    //LAB 6 CODE--------------------------------
    
    @Override
    public String toDelimitedString()
    {
        return "TP" + "," + policyHolderName + "," + id + "," + car.toDelimitedString() + "," + numberOfClaims + "," + expiryDate.toDelimitedString() + "," + comments;
    }
   
}
