package Assignment03;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UITools {
    
    //user UI tools
    
    public static void addUser(String adminName, String adminPassword, InsuranceCompany insuranceCompany)
    {
        if (insuranceCompany.addUser(adminName, adminPassword, UITools.getUser()))
        {
            System.out.println("\nUser added succesfully");
        }
        else
        {
            System.out.println("\nOperation failed, user ID not unique!");
        }
    }
    
    // company UI tools
     
//    public static void addThirdPartyPolicy(InsuranceCompany insuranceCompany, int userID, String name, int id, Car car, int numberOfClaims, MyDate date, String comments)
//    {
//        if(insuranceCompany.createThirdPartyPolicy(userID, name, id, car, numberOfClaims, date, comments))
//            {
//                System.out.println("\nPolicy added successfully!");
//            }
//            else
//            {
//                System.out.println("\nOperation failed!! Duplicate Policy.");
//            }
//    }
    
    


    //collects info, generates and returns third party policy
    
     public static ThirdPartyPolicy getThirdPartyPolicy() throws PolicyException, NameException
    {
        Scanner userIn = new Scanner(System.in);
        
        System.out.print("Enter policy holder name: ");
        String name = userIn.nextLine();

        //use UITools class to get policy ID
        int id = UITools.getPolicyID();

        //use UITools class to get details and create car object
        Car car = UITools.getCarDetails();

        //get number of claims details
        int numberOfClaims = UITools.getNumberOfClaims();

        //get expiry date details
        MyDate date = UITools.getExpiryDate();

        System.out.print("\nEnter Comments: "); 
        String comments = userIn.nextLine();
        if (comments.equals(""))
            comments = "No Comment";
        
        ThirdPartyPolicy policy = null;
        try
        {
            policy = new ThirdPartyPolicy(name, id, car, numberOfClaims, date, comments);
        }
        catch (PolicyException e)
        {
            policy = new ThirdPartyPolicy(name, e.getNewID(), car, numberOfClaims, date, comments);
            System.out.println(e);
        }
        
        return policy;
    }    
    
    // collects info, generates, and return comprehensive policy
     
    public static ComprehensivePolicy getComprehensivePolicy() throws PolicyException, NameException
    {
        Scanner userIn = new Scanner(System.in);
        
        System.out.print("Enter Policy Holder Name: ");
        String name = userIn.nextLine();

        //get policy ID save to variable
        int id = UITools.getPolicyID();

        //get car details
        Car car = UITools.getCarDetails();

        //get number of claims and save to variable
        int numberOfClaims = UITools.getNumberOfClaims();

        //get expiry date details and save to variable
        MyDate date = UITools.getExpiryDate();

        //get driver age and save to variable
        int driverAge = UITools.getDriverAge();

        int level = UITools.getLevelOfCover();
        
        ComprehensivePolicy policy = null;
        try
        {
            policy = new ComprehensivePolicy(name, id, car, numberOfClaims, date, driverAge, level);
        }
        catch (PolicyException e)
        {
            policy = new ComprehensivePolicy(name, e.getNewID(), car, numberOfClaims, date, driverAge, level);
            System.out.println(e);
        }
        
        return policy;
    }
    
    // admin remove user policy
    
    public static void removePolicy(String adminName, String adminPassword, InsuranceCompany insuranceCompany, int userID)
    {
        int policyID = UITools.getPolicyID(); // get policy ID with exception handling
        
        //call remove policy method in class Insurance Company
        if(insuranceCompany.removePolicy(adminName, adminPassword, userID, policyID))
        {
            System.out.println("\nSuccessfully removed policy from user ID "+userID);
        }
        else
        {
            System.out.println("\nOperation Failed! Policy not found.");
        }
    }
    
    //user remove user policy (not required as done through insurance company)
    
//    public static void removePolicy(int userID, String password, User user)
//    {
//        int policyID = UITools.getPolicyID(); // get policy ID with exception handling
//        
//        //call remove policy method in class Insurance Company
//        if(user.removePolicy(userID, password, policyID))
//        {
//            System.out.println("\nSuccessfully removed policy from user ID "+user.getUserID());
//        }
//        else
//        {
//            System.out.println("\nOperation Failed! Policy not found.");
//        }
//    }
    
    // scanner and user input methods
     
    //obatin and check user ID, return int value
    public static int getUserID()
    {
        Scanner userIn = new Scanner(System.in);
        
        System.out.print("Enter the user ID: ");
//        int userID = Integer.parseInt(userIn.nextLine());
        int userID = 0;
        while (userID==0) //exception handling input mismatch (wrong input type String)
        {
            try
            {
                userID = userIn.nextInt();
                userIn.nextLine();
            }
            catch (InputMismatchException e)
            {
                String badInput = userIn.nextLine(); //clears keyboard buffer
                System.out.println("PLEASE ENTER INTEGER VALUE.");
                System.out.print("Enter the user ID: ");
            }
        }
        return userID;
    }
    
    public static int getPolicyID()
    {
        Scanner userIn = new Scanner(System.in);
        
        System.out.print("Enter policy ID: ");
//        int id = Integer.parseInt(userIn.nextLine());
        int id = 0;
        while (id==0)
        {
            try
            {
                id = userIn.nextInt();
                userIn.nextLine();
            }
            catch (InputMismatchException e)
            {
                String badInput = userIn.nextLine();
                System.out.println("PLEASE ENTER INTEGER VALUE.");
                System.out.print("Enter policy ID: ");

            }
        }
        return id;
    }
    
    // obtain car details from user, create and return car object
    public static Car getCarDetails()
    {    
        Scanner userIn = new Scanner(System.in);
        
        //get car details
        System.out.println("\n----------Car Details----------");
        System.out.print("Enter car type (SUV, SED, LUX, HATCH, WAGON, FOURWD, COUPE, UTE): ");
//            CarType type = CarType.valueOf(userIn.nextLine());
        CarType type = null;
        while (type==null)
            try 
            {
                type = CarType.valueOf(userIn.nextLine().toUpperCase());
            } 
            catch (IllegalArgumentException e)
            {
//                String badInput = userIn.nextLine();
                System.out.println("PLEASE ENTER SPECIFIED CAR TYPE ONLY.");
                System.out.print("Enter car type (SUV, SED, LUX, HATCH, WAGON, FOURWD, COUPE, UTE): ");
            }

        System.out.print("Enter model: ");
        String model = userIn.nextLine();
        System.out.print("Enter manufacturing year: ");
//            int manufacturingYear = Integer.parseInt(userIn.nextLine());
        int manufacturingYear = 0;
        while (manufacturingYear == 0)
        {
            try
            {
                manufacturingYear = userIn.nextInt();
                userIn.nextLine();
            }
            catch (InputMismatchException e)
            {
                String badInput = userIn.nextLine();
                System.out.println("PLEASE ENTER INTEGER VALUE.");
                System.out.print("Enter manufacturing year: ");
            }
        }
        System.out.print("Enter price: ");
//            double price = Double.parseDouble(userIn.nextLine());
        double price = 0;
        while (price == 0)
        {
            try
            {
                price = userIn.nextDouble();
                userIn.nextLine();
            }
            catch (InputMismatchException e)
            {
                String badInput = userIn.nextLine();
                System.out.println("PLEASE ENTER INTEGER VALUE.");
                System.out.print("Enter price: ");
            }
        }
        //create car object
        Car car = new Car(type, model, manufacturingYear, price);
        return car;
    }     
    
    // obtain number of claims and return value
    public static int getNumberOfClaims()
    {
        Scanner userIn = new Scanner(System.in);
        
        System.out.print("Enter number of claims: ");
//        int numberOfClaims = Integer.parseInt(userIn.nextLine());
        int numberOfClaims = -1;
        while (numberOfClaims == -1)
        {
            try
            {
                numberOfClaims = userIn.nextInt();
                userIn.nextLine();
            }
            catch (InputMismatchException e)
            {
                String badInput = userIn.nextLine();
                System.out.println("PLEASE ENTER INTEGER VALUE.");
                System.out.print("Enter number of claims: ");
            }
        }
        return numberOfClaims;
    }
    
    // obtain expiry date details from user, create and return expiry date
    public static MyDate getExpiryDate()
    {
        Scanner userIn = new Scanner(System.in);

        //get expiry date details
        System.out.println("\n---------Policy Expiry Date----------");
        System.out.print("Enter year: ");
//            int year = Integer.parseInt(userIn.nextLine());
        int year = 0;
        while (year == 0)
        {
            try
            {
                year = userIn.nextInt();
                userIn.nextLine();
            }
            catch (InputMismatchException e)
            {
                String badInput = userIn.nextLine();
                System.out.println("PLEASE ENTER INTEGER VALUE.");
                System.out.print("Enter year: ");
            }
        }
        System.out.print("Enter month: ");
//            int month = Integer.parseInt(userIn.nextLine());
        int month = 0;
        while (month == 0)
        {
            try
            {
                month = userIn.nextInt();
                userIn.nextLine();
            }
            catch (InputMismatchException e)
            {
                String badInput = userIn.nextLine();
                System.out.println("PLEASE ENTER INTEGER VALUE.");
                System.out.print("Enter month: ");
            }
        }
        System.out.print("Enter day: ");
//            int day = Integer.parseInt(userIn.nextLine());
        int day = 0;
        while (day == 0)
        {
            try
            {
                day = userIn.nextInt();
                userIn.nextLine();
            }
            catch (InputMismatchException e)
            {
                String badInput = userIn.nextLine();
                System.out.println("PLEASE ENTER INTEGER VALUE.");
                System.out.print("Enter day: ");
            }
        }
        //create expiry date object
        MyDate date = new MyDate(year, month, day);
        return date;
    }
    
    //get driver age and return value
    public static int getDriverAge()
    {
        Scanner userIn = new Scanner(System.in);
        
        System.out.print("\nEnter driver age: ");
//        int driverAge = Integer.parseInt(userIn.nextLine());
        int driverAge = 0;
        while (driverAge == 0)
        {
            try
            {
                driverAge = userIn.nextInt();
                userIn.nextLine();
            }
            catch (InputMismatchException e)
            {
                String badInput = userIn.nextLine(); //clears keyboard buffer
                System.out.println("PLEASE ENTER INTEGER VALUE.");
                System.out.print("Enter driver age: ");
            }
        }
        return driverAge;
    }
    
    //get level of cover and return value
    public static int getLevelOfCover()
    {
        Scanner userIn = new Scanner(System.in);
        
        System.out.print("Enter cover level: ");
//        int driverAge = Integer.parseInt(userIn.nextLine());
        int level = 0;
        while (level == 0)
        {
            try
            {
                level = userIn.nextInt();
                userIn.nextLine();
            }
            catch (InputMismatchException e)
            {
                String badInput = userIn.nextLine(); //clears keyboard buffer
                System.out.println("PLEASE ENTER INTEGER VALUE.");
                System.out.print("Enter cover level: ");
            }
        }
        return level;
    }
    
    //get address details and return address object
    public static Address getAddress()
    {
        Scanner userIn = new Scanner(System.in);
        
        System.out.println("\n----------Enter Address Details----------");
        System.out.print("Enter street number: ");
//        int streetNum = Integer.parseInt(userIn.nextLine());
        int streetNum = 0;
        while (streetNum==0) //exception handling for wrong input type: String
        {
            try
            {
                streetNum = userIn.nextInt();
                userIn.nextLine();
            }
            catch (InputMismatchException e)
            {
                String badInput = userIn.next(); //clears keyboard buffer
                System.out.println("PLEASE ENTER INTEGER VALUE.");
                System.out.print("Enter street number: ");
            }
        }
        System.out.print("Enter street name: ");
        String streetName = userIn.nextLine();
        System.out.print("Enter suburb: ");
        String suburb = userIn.nextLine();
        System.out.print("Enter city: ");
        String city = userIn.nextLine();
        
        return new Address(streetNum, streetName, suburb, city);
    }
    
    public static User getUser()
    {
        Scanner userIn = new Scanner(System.in);
        
        System.out.println("\n----------Create User----------");
        System.out.print("Enter Policy Holder Name: ");
        String name = userIn.nextLine();
//        System.out.print("Enter User ID: "); NO LONGER REQUIRED AS INTERNALLY GENERATED!!!
//        int userId = Integer.parseInt(userIn.nextLine());
        
        //get address details and return address object using UI Tools
        Address address = UITools.getAddress();

        System.out.print("\nPlease enter Username: ");
        String username = userIn.nextLine();
        
        System.out.print("\nPlease enter Password: ");
        String password = userIn.nextLine();
        
        return new User(name, address, username, password);
    }
    
    
}
