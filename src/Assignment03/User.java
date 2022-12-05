package Assignment03;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class User implements Cloneable, Comparable<User>, Serializable
{
    //attributes
    private String name;
    private int userID;
    private Address address;
//    private ArrayList<InsurancePolicy> policies;
    private HashMap<Integer, InsurancePolicy> policies;
    private String password;
    private static int counter = 1;
    private String username;
    
    //constructor 1 - enter address details
    public User(String name, int streetNum, String street, String suburb, String city, String username, String password)
    {
        this.name = name;
        this.userID = User.counter++;
        this.address = new Address(streetNum, street, suburb, city);
//        this.policies = new ArrayList<InsurancePolicy>();
        this.policies = new HashMap<>();
        this.username = username;
        this.password = password;
    }
    
    //constructor 2 - uses address object
    public User(String name, Address address, String username, String password)
    {
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;
        this.userID = User.counter++;
//        this.policies = new ArrayList<InsurancePolicy>();
        this.policies = new HashMap<>();

    }
    
    //copy constructor
    
    //HASH MAP METHOD
     public User(User user)
    {   
        this.name = user.name;
        this.userID = user.userID;
        this.address = new Address(user.address); // call address copy constructor
        this.policies = new HashMap<>();
        this.username = user.username;
        this.password = user.password;
        
        for (InsurancePolicy policy : user.policies.values())
        {
            if (policy instanceof ThirdPartyPolicy) // determine type of policy (third party or comprehensive)
            {
                ThirdPartyPolicy copyThirdPolicy = new ThirdPartyPolicy((ThirdPartyPolicy)policy); // call copy constructor for third party
                policies.put(copyThirdPolicy.id, copyThirdPolicy); 
            }
            else
            {
                ComprehensivePolicy copyCompPolicy = new ComprehensivePolicy((ComprehensivePolicy)policy); // call copy constructor for comprehensive policy
                policies.put(copyCompPolicy.id, copyCompPolicy);
            }
        }    
    }
    
    //ARRAY LIST METHOD
//    public User(User user)
//    {   
//        name = user.name;
//        userID = user.userID;
//        address = new Address(user.address); // call address copy constructor
//        policies = new ArrayList<InsurancePolicy>(); // create new empty array to store cloned policies
//        password = user.password;
//        
//        for (InsurancePolicy policy : user.policies)
//        {
//            if (policy instanceof ThirdPartyPolicy) // determine type of policy (third party or comprehensive
//            {
//                policies.add(new ThirdPartyPolicy((ThirdPartyPolicy)policy)); // call copy constructor for third party
//            }
//            else
//            {
//                policies.add(new ComprehensivePolicy((ComprehensivePolicy)policy)); // call copy constructor for third party
//            }
//        }    
//    }
    
    //override clone method
    
    //HASH MAP METHOD 
    @Override
    @SuppressWarnings("CloneDeclaresCloneNotSupported")
    public User clone()
    {
        User output = null;
        try
        {
            output = (User)super.clone(); //create shallow copy
            output.address = new Address(address); // call copy constructor
            output.policies=new HashMap<>(); //clear the shallow copy
            for (InsurancePolicy policy : policies.values())
            {
                output.policies.put(policy.id, policy.clone()); // use clone for a deep copy and populate cleared shallow copy
            }
        }
        catch(CloneNotSupportedException e)
        {
            System.out.print("User - Clone not supported error!");
        }
        return output;
    }
     
     //ARRAY LIST METHOD 
//    @Override
//    public User clone() throws CloneNotSupportedException
//    {
//        User output = (User)super.clone(); //create shallow copy
//        output.address = address.clone();
//        output.policies=new ArrayList<InsurancePolicy>(); //clear the shallow copy
//        for (InsurancePolicy policy : policies)
//        {
//            output.policies.add(policy.clone()); // use clone for a deep copy and populate cleared shallow copy
//        }
//        return output;
//    }
    
    //accessors
    public String getName()
    {
       return name;
    }
    public int getUserID()
    {
        return userID;
    }
    public Address getAddress()
    {
        return address;
    }
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String newPassword)
    {
        this.password = newPassword;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    // HashMap code
    public HashMap<Integer, InsurancePolicy> getPolicies(String username, String password)
    {
        HashMap<Integer, InsurancePolicy> output = new HashMap<>();
        if (validateUser(username, password))
        {
            output = policies;
        }
        return output;
    }
    
    //ARRAY LIST METHOD
//    public ArrayList<InsurancePolicy> getPolicies()
//    {
//        return policies;
//    }
    
    //mutators (no mutator for userID as this should be final in the system)
    public void setName(String name)
    {
        this.name = name;
    }
    //set address with details
    public void setAddress(int streetNum, String street, String suburb, String city)
    {
        Address userAddress = new Address(streetNum, street, suburb, city);
        this.address = userAddress;
    }
    //set address with object
    public void setAddress(String username, String password, Address address)
    {
        if (validateUser(username, password))
        {
            this.address = address;
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
    }
    public void setStreetNum(int streetNum)
    {
        address.setStreetNum(streetNum);
    }
    public void setStreet(String street)
    {
        address.setStreet(street);
    }
    public void setSuburb(String suburb)
    {
        address.setSuburb(suburb);
    }
    public void setCity(String city)
    {
        address.setCity(city);
    }
    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    //HASH MAP METHOD
    public void setPolicies(HashMap<Integer, InsurancePolicy> policies)
    {
        this.policies = policies;
    }

    //ARRAY LIST METHOD
//    public void setPolicies(ArrayList<InsurancePolicy> policies)
//    {
//        this.policies = policies;
//    }
    
    //find policy in array and return it else return null
    
    //HASH MAP METHOD
    public InsurancePolicy findPolicy(String username, String password, int policyId)
    {
        InsurancePolicy output=null;
        if (validateUser(username, password))
        {
            output = policies.get(policyId);
        }
        return output;
    }
    
    //ARRAY LIST METHOD
//    public InsurancePolicy findPolicy(int policyId)
//    {
//        for (InsurancePolicy policy : policies)
//        {
//            if (policy.id == policyId)
//                return policy;
//        }
//        return null;
//    }  
    
    //call findPolicy() to check if policy already exists, if not add policy to policies array
    
    //HAS MAP METHOD
    public boolean addPolicy (String username, String password, InsurancePolicy policy)
    {
        if (validateUser(username, password))
        {
            if (policies.get(policy.id)==null)
            {
                policies.put(policy.id, policy);
                return true;
            }
            else
            {
                System.out.println("Invalid Access Rights! Operation Terminated.");
            }
        }
        return false;
    }
    
    //ARRAY LIST METHOD
//    public boolean addPolicy (InsurancePolicy policy)
//    {
//        if (findPolicy(policy.id)==null)
//        {
//            policies.add(policy);
//            return true;
//        }
//        return false;
//    }
    
    //prints all info including policies
    public void print(String username, String password)
    {
        if (validateUser(username, password))
        {
            System.out.println("Name: "+name+" User ID: "+userID+" Address: "+address);
            System.out.println("----------------------------------------------------------------------Policies----------------------------------------------------------------------");
            InsurancePolicy.printPolicies(policies);
        }
        else
        {
            System.out.println("Invalid access details! Operation Terminated.");
        }
    }
    
    //HASH MAP METHOD
    @Override
    public String toString()
    {
        String userString = "Name: "+name+" User ID: "+userID+" Address: "+address+"\n";
        userString += "\n----------------------------------------------------------------------Policies----------------------------------------------------------------------\n";
        for (InsurancePolicy policy : policies.values())
        {
            userString += policy.toString()+"\n";
        }
        
        return userString;
    }
    
    //ARRAY LIST METHOD
//    @Override
//    public String toString()
//    {
//        String userString = "Name: "+name+" User ID: "+userID+" Address: "+address+"\n";
//        userString += "\n----------------------------------------------------------------------Policies----------------------------------------------------------------------\n";
//        for (InsurancePolicy policy : policies)
//        {
//            userString += policy.toString()+"\n";
//        }
//        
//        return userString;
//    }
    
    //print all policies and premiumes including the totals for third party and comprehensive policies
//    public void printPolicies(String username, String password, int flatRate)
//    {
//        if (validateUser(username, password))
//        {
//            InsurancePolicy.printPolicies(policies, flatRate);
//        }
//        else
//        {
//            System.out.println("Invalid Access Details. Operation Terminated.");
//        }
//    }

    // uses LAMBDA / STREAMS
    public void printPolicies(String username, String password, int flatRate)
    {
        if (validateUser(username, password))
        {
            policies.values().forEach(policy -> {System.out.printf("%s\nPolicy Price %.2f", policy, policy.calcPayment(flatRate));});
        }
        else
        {
            System.out.println("Invalid Access Details. Operation Terminated.");
        }
    }
    //calculate and return the total cost off all policies
    public double calcTotalPremiums(String username, String password, int flatRate)
    {
        double output=0;
        if (validateUser(username, password))
        {
            output = InsurancePolicy.calcTotalPayments(policies, flatRate);
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
        return output;
    }
    
    //print users from hash map
    
    public static void printUsers(HashMap<Integer, User> users)
    {
        for (User user : users.values())
        {
            System.out.println("Name: "+user.getName() +" User ID: "+user.getUserID()+" Address: "+user.getAddress());
        }
    }
    
    //increase all car prices for user policies
    public void carPriceRiseAll(double risePercent)
    {
        InsurancePolicy.carPriceRiseAll(policies, risePercent);
    }
    
    //return array list of policies filtered by car model
    
    //HASH MAP METHOD
    public HashMap<Integer, InsurancePolicy > filterByCarModel (String carModel)
    {
        return InsurancePolicy.filterByCarModel(policies, carModel);
    }
    
    //ARRAY LIST METHOD
//    public ArrayList< InsurancePolicy > filterByCarModel (String carModel)
//    {
//        return InsurancePolicy.filterByCarModel(policies, carModel);
//    }
    
    //LAB 3 CODE --------------------------------------------------------------------------------
    
    //method to filter user's policies that expire before a given date
    
    public HashMap<Integer, InsurancePolicy> filterByExpiryDate (String username, String password, MyDate date)
    {
        HashMap<Integer, InsurancePolicy> output = new HashMap<>();
        if (validateUser(username, password))
        {
            output = InsurancePolicy.filterByExpiryDate(policies, date);
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
        return output;
    }
    
    //ARRAY LIST METHOD
//    public ArrayList<InsurancePolicy> filterByExpiryDate (MyDate date)
//    {
//        return InsurancePolicy.filterByExpiryDate(policies, date);
//    }
    
    // creates a Third-Party Policy and adds it to the list of the user’s policies, returns false if the id is not unique.

    //HASH MAP METHOD
    public boolean createThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) throws PolicyException, NameException
    {
        ThirdPartyPolicy thirdPartyPolicy;
        try
        {
            thirdPartyPolicy = new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
        }
        catch (PolicyException e)
        {
            thirdPartyPolicy = new ThirdPartyPolicy(policyHolderName, e.getNewID(), car, numberOfClaims, expiryDate, comments);
            System.out.println(e);
        }
        
        if (policies.containsKey(thirdPartyPolicy.id))
        {
            return false;
        }    
        policies.put(thirdPartyPolicy.id, thirdPartyPolicy);
        return true;
    }

    //ARRAY LIST METHOD
//    public boolean createThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments)
//    {
//        ThirdPartyPolicy thirdPartyPolicy = new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
//        for (InsurancePolicy policy : policies)
//        {
//            if (policy.id ==thirdPartyPolicy.id)
//            {
//                return false;
//            }    
//        }
//        policies.add(thirdPartyPolicy);
//        return true;
//    }
    
    // creates a Comprehensive Policy and adds it to the list of the user’s policies, returns false if the id is not unique

    //HASH MAP METHOD
    public boolean createComprehensivePolicy(String username, String password, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) throws PolicyException, NameException
    {
        ComprehensivePolicy comprehensivePolicy;
        boolean value = false;
        if (validateUser(username, password))
        {
            try
            {
                comprehensivePolicy = new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level);
            }
            catch (PolicyException e)
            {
                comprehensivePolicy = new ComprehensivePolicy(policyHolderName, e.getNewID(), car, numberOfClaims, expiryDate, driverAge, level);
                System.out.println(e);
            }
            // check if policy with same policy id already exists
            if (policies.containsKey(comprehensivePolicy.id))
            {
                value = false;
            }
            else
            {
                policies.put(comprehensivePolicy.id, comprehensivePolicy);
                value = true;
            }
        }
        return value;
    }

    //ARRAY LIST METHOD
//    public boolean createComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level)
//    {
//        ComprehensivePolicy comprehensivePolicy = new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level);
//        for (InsurancePolicy policy : policies)
//        {
//            if (policy.id==comprehensivePolicy.id)
//            {
//                return false;
//            }
//        }
//        policies.add(comprehensivePolicy);
//        return true;
//    }
    //validate user login details
    public boolean validateUser(String username, String password)
    {
        return this.username.equals(username) && this.password.equals(password);
    }
    
    //remove a policy from the users policy array list

    //HASH MAP METHOD
    public boolean removePolicy(String username, String password, int policyID)
    {
        boolean value = false;
        if (validateUser(username, password))
        {
            InsurancePolicy policy = findPolicy(username, password, policyID);
            if (policy!=null)
            {
                this.policies.remove(policyID);
                value = true;
            }
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
        return value;
    }

    //ARRAY LIST METHOD
//    public boolean removePolicy(int policyID)
//    {
//        boolean value = false;
//        InsurancePolicy policy = findPolicy(policyID);
//        if (policy!=null)
//        {
//            this.policies.remove(policy);
//            value = true;
//        }
//        return value;
//    }
    
    //Reporting payments by car model-------------------------------------------------------
    
    // goes through all the policies for a user and populates a list of distinct car model names-----------------------------------------------------------

    //HASH MAP METHOD
    public ArrayList<String> populateDistinctCarModels(String username, String password)
    {
        ArrayList<String> carModels= new ArrayList<>();
        if (validateUser(username, password))
        {
            for (InsurancePolicy policy : policies.values())
            {
                String carModel = policy.car.getModel();
                if (!carModels.contains(carModel))
                {
                    carModels.add(carModel);
                }
            }
        }
        else
        {
            System.out.println("Invalid Access Rights. Operation Terminated.");
        }
        return carModels;
    }

    //ARRAY LIST METHOD
//    public ArrayList<String> populateDistinctCarModels()
//    {
//        ArrayList<String> carModels= new ArrayList<String>();
//        for (InsurancePolicy policy : policies)
//        {
//            String carModel = policy.car.getModel();
//            if (!carModels.contains(carModel))
//            {
//                carModels.add(carModel);
//            }
//        }
//        return carModels;
//    }
    // returns the number of policies this user owns for the given carModel

    //HASH MAP METHOD
    public int getTotalCountForCarModel(String carModel)
    {
        int numPolicies = 0;
        
        HashMap<Integer, InsurancePolicy> filteredPolicies = filterByCarModel(carModel);
        if (filteredPolicies!=null)
        {
            numPolicies = filteredPolicies.size();
        }
        return numPolicies;
    }

    //ARRAY LIST METHOD
//    public int getTotalCountForCarModel(String carModel)
//    {
//        int numPolicies = 0;
//        
//        ArrayList<InsurancePolicy> filteredPolicies = filterByCarModel(carModel);
//        if (filteredPolicies!=null)
//        {
//            numPolicies = filteredPolicies.size();
//        }
//        return numPolicies;
//    }  
    
    // returns the total payments for the given carModel across all the policies this user owns

    //HASH MAP METHOD
    public double getTotalPaymentForCarModel(String carModel, int flatRate)
    {
        double total = 0;
        
        HashMap<Integer, InsurancePolicy> filteredPolicies = filterByCarModel(carModel);
        if (filteredPolicies!=null)
        {
            total = InsurancePolicy.calcTotalPayments(filteredPolicies, flatRate);
        }
        return total;
    }

    //ARRAY LIST METHOD
//    public double getTotalPaymentForCarModel(String carModel, int flatRate)
//    {
//        double total = 0;
//        
//        ArrayList<InsurancePolicy> filteredPolicies = filterByCarModel(carModel);
//        if (filteredPolicies!=null)
//        {
//            total = InsurancePolicy.calcTotalPayments(filteredPolicies, flatRate);
//        }
//        return total;
//    }

    // returns the count for each model in the carModels as a list of integers. Call getTotalCountForCarModel(String carModel) in a loop for each carModel in the list
    public ArrayList<Integer> getTotalCountPerCarModel(ArrayList<String> carModels)
    {
        ArrayList<Integer> carCount = new ArrayList<>();
        for (String car : carModels)
        {
            int count = getTotalCountForCarModel(car);
            if (count>0){
                carCount.add(count);
            }    
        }
        return carCount;
    }
    
    // returns the Total Payment for each model in the carModels as a list of doubles
    public ArrayList<Double> getTotalPaymentPerCarModel (ArrayList<String> carModels, int flatRate)
    {
        ArrayList<Double> carPayments = new ArrayList<>();
        for (String car : carModels)
        {
            double payment = getTotalPaymentForCarModel(car, flatRate);
            if (payment>0)
            {
                carPayments.add(payment);
            }
        }
        return carPayments;
    }
//    public void reportPaymentsPerCarModel(HashMap<String, Integer> carModels, HashMap<String, Integer>counts, HashMap<String, Double> premiumPayments)
//    {
//        //print headings
//        System.out.printf("\n%-17s%26s%28s\n","Car Model", "Total Premium Payment", "Average Premium Payment");
//        
//        for (var index = 0; index < carModels.size() ; index++)
//        {
//            System.out.printf("%-28s$%-27.2f$%-17.2f\n", carModels.get(index), premiumPayments.get(index), premiumPayments.get(index)/counts.get(index));
//        }
//    }
    
    
    // uses LAMBDA to report total and average premium payments per car model
    public void reportPaymentsPerCarModel(HashMap<String, Integer> carModels, HashMap<String, Integer>counts, HashMap<String, Double> premiumPayments)
    {
        //print headings
        System.out.printf("\n%-17s%26s%28s\n","Car Model", "Total Premium Payment", "Average Premium Payment");
        
        carModels.keySet().forEach(x -> System.out.printf("%-28s$%-27.2f$%-17.2f\n", x, premiumPayments.get(x), premiumPayments.get(x)/counts.get(x)));
    }
    
    //LAB 4 -----------------------------------------
    
    // shallow copy method of users array: ARRAY LIST METHOD
    
    public static HashMap<Integer, User> shallowCopyHashMap(HashMap<Integer, User> users)
    {
        HashMap<Integer, User>shallowCopy = users;
        return shallowCopy;
    }

    // shallow copy method of users array: HASH MAP -> ARRAY LIST METHOD
    
    public static ArrayList<User> shallowCopy(HashMap<Integer, User> users)
    {
        ArrayList<User>shallowCopy = new ArrayList<>();
        for (User user : users.values())
        {
            shallowCopy.add(user); // just assign same value (reference) for shallow copy
        }
        return shallowCopy;
    }    

// shallow copy method of users array: ARRAY LIST METHOD
//    public static ArrayList<User> shallowCopy(ArrayList<User> users)
//    {
//        ArrayList<User>shallowCopy = new ArrayList<>();
//        for (User user : users)
//        {
//            shallowCopy.add(user); // just assign same value (reference) for shallow copy
//        }
//        return shallowCopy;
//    }
    
    // uses LAMBDA / STREAMS
    public static ArrayList<User> shallowCopy(ArrayList<User> users)
    {
        ArrayList<User>shallowCopy = new ArrayList<>();
        users.forEach(user ->
        {
            shallowCopy.add(user); // just assign same value (reference) for shallow copy
        });
        
        return shallowCopy;
    }
    
    // deep copy method users array: HASH MAP METHOD
    
    public static HashMap<Integer, User> deepCopyHashMap(HashMap<Integer, User> users) throws CloneNotSupportedException
    {    
        HashMap<Integer, User> deepCopy = new HashMap<>();
        for (User user : users.values()) 
        {
            User copyUser = new User(user); //Deep copy by using copy constructor
            deepCopy.put(copyUser.getUserID(), copyUser);
        }
        return deepCopy;  
    }
    
    // deep copy method users array: HASH MAP -> ARRAY LIST METHOD
    
    public static ArrayList<User> deepCopy(HashMap<Integer, User> users) throws CloneNotSupportedException
    {    
        ArrayList<User>deepCopy = new ArrayList<>();
        for (User user : users.values()) 
        {
            User copyUser = new User(user); //Deep copy by using copy constructor
            deepCopy.add(copyUser.getUserID(), copyUser);
        }
        return deepCopy;  
    }

    // deep copy method users array: ARRAY LIST METHOD   
//    public static ArrayList<User> deepCopy(ArrayList<User> users) throws CloneNotSupportedException
//    {    
//        ArrayList<User>deepCopy = new ArrayList<>();
//        for (User user : users) 
//        {
//            deepCopy.add(user.clone()); //Deep copy by using copy constructor
//        }
//        return deepCopy;  
//    }
    
    // use LAMBDA / STREAMS
    public static ArrayList<User> deepCopy(ArrayList<User> users) throws CloneNotSupportedException
    {    
        ArrayList<User>deepCopy = new ArrayList<>();
        users.forEach(user -> {deepCopy.add(user.clone());});
        
        return deepCopy;  
    }

    // deep copy users insurance polices: HASH MAP METHOD
    
    public HashMap<Integer, InsurancePolicy> deepCopyPoliciesHashMap()
    {
        HashMap<Integer, InsurancePolicy> deepCopy = new HashMap<>();
        for (InsurancePolicy policy : policies.values())
        {
            if (policy instanceof ThirdPartyPolicy) // determine type of policy (third party or comprehensive
            {
                ThirdPartyPolicy copy = new ThirdPartyPolicy((ThirdPartyPolicy)policy); // deep copy by calling constructor and type casting third party
                deepCopy.put( copy.id, copy);
            }
            else
            {
                ComprehensivePolicy copy = new ComprehensivePolicy((ComprehensivePolicy)policy); // deep copy by calling constructor and type casting comprehensive
                deepCopy.put(copy.id, copy); 
            }
        }
        return deepCopy;
    }

    // deep copy users insurance polices: HASH MAP -> ARRAY LIST METHOD
    
    public ArrayList<InsurancePolicy> deepCopyPolicies()
    {
        ArrayList<InsurancePolicy> deepCopy = new ArrayList<>();
        for (InsurancePolicy policy : policies.values())
        {
            if (policy instanceof ThirdPartyPolicy) // determine type of policy (third party or comprehensive
            {
                deepCopy.add(new ThirdPartyPolicy((ThirdPartyPolicy)policy)); // deep copy by calling constructor for third party
            }
            else
            {
                deepCopy.add(new ComprehensivePolicy((ComprehensivePolicy)policy)); // deep copy by calling constructor for comprehensive
            }
        }
        return deepCopy;
    }
    
    // deep copy users insurance polices: ARRAY LIST METHOD
//    public ArrayList<InsurancePolicy> deepCopyPolicies()
//    {
//        ArrayList<InsurancePolicy> deepCopy = new ArrayList<InsurancePolicy>();
//        for (InsurancePolicy policy : policies)
//        {
//            if (policy instanceof ThirdPartyPolicy) // determine type of policy (third party or comprehensive
//            {
//                deepCopy.add(new ThirdPartyPolicy((ThirdPartyPolicy)policy)); // deep copy by calling constructor for third party
//            }
//            else
//            {
//                deepCopy.add(new ComprehensivePolicy((ComprehensivePolicy)policy)); // deep copy by calling constructor for comprehensive
//            }
//        }
//        return deepCopy;
//    }

    // shallow copy users insurance policies: HASH MAP METHOD
    
    public HashMap<Integer, InsurancePolicy> shallowCopyPoliciesHashMap()
    {
        HashMap<Integer, InsurancePolicy> shallowCopy = policies;
        return shallowCopy;
    }

    // shallow copy users insurance policies: HASH MAP -> ARRAY LIST METHOD
    
    public ArrayList<InsurancePolicy> shallowCopyPolicies()
    {
        ArrayList<InsurancePolicy> shallowCopy = new ArrayList<>();
        for (InsurancePolicy policy :policies.values())
        {
            shallowCopy.add(policy);
        }
        return shallowCopy;
    }
    
    // shallow copy users insurance policies: ARRAY LIST METHOD
//    public ArrayList<InsurancePolicy> shallowCopyPolicies()
//    {
//        ArrayList<InsurancePolicy> shallowCopy = policies;
//        return shallowCopy;
//    }
    
    @Override
    public int compareTo(User other)
    {
        return this.name.compareTo(other.name); 
    }
    
    // compare total premium payments for users
//    public int compareTo1(User other)
//    {
//        int flatRate = 100; // assume constant flat rate
//        double thisTotal = calcTotalPremiums(flatRate); // calculate this total premiums
//        double otherTotal = other.calcTotalPremiums(flatRate); // calculate other total premiums
//        
//        if (thisTotal==otherTotal)
//            return 0;
//        
//        if (thisTotal > otherTotal)
//        {
//            return 1;
//        }
//        else
//        {
//            return -1;
//        } 
//    }
    
    // sort user polices by expiry date and return as array

    //HASH MAP METHOD
    public ArrayList<InsurancePolicy> sortPoliciesByDate()
    {
        ArrayList<InsurancePolicy> output = new ArrayList<>();
        
        for (InsurancePolicy policy : policies.values())
        {
            output.add(policy);
        }
        Collections.sort(output);
        
        return output;
    }

    //ARRAY LIST METHOD
//    public ArrayList<InsurancePolicy> sortPoliciesByDate()
//    {
//        ArrayList<InsurancePolicy> output = policies;
//        Collections.sort(output);
//        
//        return output;
//    }
    
    //DATA AGGREGATION METHODS
    
    // aggregates the number of policies the user owns for any given car model as <Car Model, Number of policies for that given model> and returns the hashmap
    
    public HashMap<String, Integer> getTotalCountPerCarModel()
    {
        HashMap<String, Integer> totalCarCount = new HashMap<>(); 
        
        for (InsurancePolicy policy : policies.values()) 
        {
            String carModel = policy.car.getModel(); //
            if (!totalCarCount.containsKey(carModel))
            {
                HashMap<Integer, InsurancePolicy> carModelPolicies = filterByCarModel(carModel);
                totalCarCount.put(carModel, carModelPolicies.size());
            }
        }
        return totalCarCount;
    }
    
    // aggregates the total premium payments of policies the user owns for any given car model as a list of pairs of <Car Model, Total Premium Payment> and returns the HashMap
    
//    public HashMap<String, Double> getTotalPremiumPerCarModel(int flatRate)
//    {
//        HashMap<String, Double> totalCarPremiums = new HashMap<>();
//        
//       for (InsurancePolicy policy : policies.values()) 
//        {
//            String carModel = policy.car.getModel(); //
//            if (totalCarPremiums.get(carModel)==null)
//            {
//                Double carTotal = getTotalPaymentForCarModel(carModel, flatRate);
//                totalCarPremiums.put(carModel, carTotal);
//            }
//        }
//       return totalCarPremiums;
//    }
    
    //uses LAMBDA / STREAMS
    public HashMap<String, Double> getTotalPremiumPerCarModel(int flatRate)
    {
        HashMap<String, Double> totalCarPremiums = new HashMap<>();
               
        populateDistinctCarModels(getUsername(), getPassword()).stream()
                .forEach(carModel -> totalCarPremiums.put(carModel, getTotalPaymentForCarModel(carModel, flatRate)));
              
//        for (InsurancePolicy policy : policies.values()) 
//        {
//            String carModel = policy.car.getModel(); //
//            if (totalCarPremiums.get(carModel)==null)
//            {
//                Double carTotal = getTotalPaymentForCarModel(carModel, flatRate);
//                totalCarPremiums.put(carModel, carTotal);
//            }
//        }
       return totalCarPremiums;
    }
    
    // report method diplays total premiums by car model, using previous two data aggregations methods
    
//    public void reportPaymentsPerCarModel(String username, String password, int flatRate)
//    {
//        if (validateUser(username, password))
//        {
//            HashMap<String, Integer> totalCarCount = getTotalCountPerCarModel();
//
//            HashMap<String, Double> totalCarPremiums = getTotalPremiumPerCarModel(flatRate);
//
//            //print headings
//            System.out.printf("\n%-17s%26s%28s\n","Car Model", "Total Premium Payment", "Average Premium Payment");
//
//            for (String key : totalCarCount.keySet())
//            {
//                System.out.printf("%-28s$%-27.2f$%-17.2f\n", key, totalCarPremiums.get(key), totalCarPremiums.get(key)/totalCarCount.get(key));
//            }
//        }
//    }

    // uses LAMBDA to report method diplays total premiums by car model, using previous two data aggregations methods
    public void reportPaymentsPerCarModel(String username, String password, int flatRate)
    {
        if (validateUser(username, password))
        {
            HashMap<String, Integer> totalCarCount = getTotalCountPerCarModel();

            HashMap<String, Double> totalCarPremiums = getTotalPremiumPerCarModel(flatRate);

            System.out.printf("\n%-17s%26s%28s\n","Car Model", "Total Premium Payment", "Average Premium Payment");
            totalCarCount.keySet().forEach(x -> System.out.printf("%-28s$%-27.2f$%-17.2f\n", x, totalCarPremiums.get(x), totalCarPremiums.get(x)/totalCarCount.get(x)));
        }
    }
    
    //----------------LAB 6 SERIALIZATION------------
    
    // read data from external file into Hash Map and return result (DESERIALIZATION)
    
    public static HashMap<Integer, User> load(String fileName) throws IOException
    {
        ObjectInputStream inputStream = null; //declare input object to open file
        HashMap<Integer, User> output = new HashMap<>();
        
        // attempt to open file 
        try
        {
            inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
        }
        catch (IOException ioException)
        {
            System.err.println("Error opening file.");
            System.exit(1);
        }
        
        // attempt to read from file and store in HashMap to return results
        try
        {
            while (true)
            {
                User user = (User) inputStream.readObject();
                output.put(user.getUserID(), user);
            }
        }
        catch (EOFException endOfFile)
        {
            System.out.println("\nUser Load Complete.\n");
        }
        catch (ClassNotFoundException classNotFound)
        {
            System.err.println("Invalid object type. Terminating.");
        }
        catch (IOException inputOutputError)
        {
            System.err.println("Error reading from file. Terminating.");
        }
        
        // close the file
        try
        {   
            if (inputStream!=null)
                inputStream.close();
        }
        catch (IOException inputOutputError)
        {
            System.err.println("Error closing file. Terminating.");
        }
        
        return output;
    }
    
    // write data from Hash Map to external file (SERIALIZATION)
    public static boolean save(HashMap<Integer, User> users, String fileName) throws IOException
    {
        ObjectOutputStream outputStream = null; //declare output stream to open file
        boolean value = false;
        
        try //open file
        {
            outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
        }
        catch (IOException inputOutputX)
        {
            System.err.println("Error opening file. Terminating.");
            System.exit(1);
        }
        try // write to file
        {
            for (User user : users.values())
            {
                outputStream.writeObject(user);
            }
        }
        catch (IOException inputOutputX)
        {
            System.err.println("Error in writing to file. Terminating.");
            System.exit(1);
        }
        try //close file
        {
            if (outputStream!=null)
                outputStream.close();
            value = true;
        }
        catch (IOException inputOutputX)
        {
            System.err.println("Error closing file. Terminating.");
            System.exit(1);
        }
        return value;
    }
    
    // ---------------------LAB 6 TEXT FILE------------------------------------
    
    public String toDelimitedString()
    {
        String output = name + "," + userID + "," + address.toDelimitedString() + "," + username + "," + password + "," + policies.size();
        for (InsurancePolicy policy : policies.values())
        {
            output+= "," + policy.toDelimitedString(); 
        }
        
        return output;
    }
    
    // save write data to file from hash map
    
    public static boolean saveTextFile(HashMap<Integer, User> users, String fileName) throws IOException
    {
        BufferedWriter output = new BufferedWriter(new FileWriter(fileName)); // create object to write data to file
        
        for (User user : users.values())
        {
            output.write(user.toDelimitedString() + "\n");
        }
        output.close();
        return true;
    }
    
    public static HashMap<Integer, User> loadTextFile(String fileName) throws IOException, PolicyException, NameException
    {
        HashMap<Integer, User> output = new HashMap<>(); // create new hash map to store and return loaded data

        BufferedReader input = new BufferedReader(new FileReader(fileName)); // create object to read in data
        String line = input.readLine(); // read in first line
        while(line!=null)
        {
            line = line.trim(); // trim white space from either end of string
            String[] field = line.split(","); // split string into array using comma seperators
            
            // store values from field array
            String userName = field[0];
            int userID = Integer.parseInt(field[1]);
            int streetNum = Integer.parseInt(field[2]);
            String street = field[3];
            String suburb = field[4];
            String city = field[5];
            String username = field[6];
            String password = field[7];
            int numPolicies = Integer.parseInt(field[8]);

            Address address = new Address(streetNum, street, suburb, city); // create address object
            User user = new User(userName, address, username, password); // create user object
            user.setUserID(userID); // set user ID from file
                 
            int index = 9;
            for (int i=0 ; i<numPolicies; i++)
            {
                if(field[index].equals("TP"))
                {
                    String policyHolderName = field[index+1];
                    int id = Integer.parseInt(field[index+2]);
                    CarType type = CarType.valueOf(field[index+3]);
                    String model = field[index+4];
                    int manufacturingYear = Integer.parseInt(field[index+5]);
                    double price = Double.parseDouble(field[index+6]);
                    int numberOfClaims = Integer.parseInt(field[index+7]);
                    int year = Integer.parseInt(field[index+8]);
                    int month = Integer.parseInt(field[index+9]);
                    int day = Integer.parseInt(field[index+10]);
                    String comments = field[index+11];
                    
                    Car car = new Car(type, model, manufacturingYear, price);
                    MyDate date = new MyDate(year, month, day);
                    
                    user.addPolicy(user.getUsername(), user.getPassword(), new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, date, comments));
                    index+=12;
                }
                else
                {
                    String policyHolderName = field[index+1];
                    int id = Integer.parseInt(field[index+2]);
                    CarType type = CarType.valueOf(field[index+3]);
                    String model = field[index+4];
                    int manufacturingYear = Integer.parseInt(field[index+5]);
                    double price = Double.parseDouble(field[index+6]);
                    int numberOfClaims = Integer.parseInt(field[index+7]);
                    int year = Integer.parseInt(field[index+8]);
                    int month = Integer.parseInt(field[index+9]);
                    int day = Integer.parseInt(field[index+10]);
                    int driverAge = Integer.parseInt(field[index+11]);
                    int level = Integer.parseInt(field[index+12]);
                    
                    Car car = new Car(type, model, manufacturingYear, price);
                    MyDate date = new MyDate(year, month, day);
                    
                    user.addPolicy(user.getUsername(), user.getPassword(), new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, date, driverAge, level));
                    index+=13;
                }      
            }
        output.put(user.getUserID(), user);
        line = input.readLine();
        }
        input.close();
        return output;
    }
    
    // policy count from give range array returns array[policy count]
//    public int[] policyCount(String username, String password, int[] ranges, int flatRate)
//    {
//        int[] output = new int[ranges.length]; 
//        if (validateUser(username, password))
//        {
//            for (InsurancePolicy policy : policies.values())
//            {
//                double premium = policy.calcPayment(flatRate);
//                for (int i = 0 ; i < ranges.length ; i++)
//                {   
//                    if(premium <= ranges[i])
//                    {
//                        output[i] += 1;
//                        break;
//                    }
//                }        
//            }
//        }
//        return output;
//    }
    
    //uses LAMBDA policy count from give range array returns array[policy count]
    public int[] policyCount(String username, String password, int[] ranges, int flatRate)
    {
        int[] output = new int[ranges.length]; 
        if (validateUser(username, password))
        {
            
            policies.values().stream()
                    .forEach(x -> {
                        double premium = x.calcPayment(flatRate);
            for (int i = 0 ; i < ranges.length ; i++)
                {   
                    if(premium <= ranges[i])
                    {
                        output[i] += 1;
                        break;
                    }
                }
            });
        }
        return output;
    }
    
    // policy count by car model per range for current user returns <"car model", [policy count]>
        public HashMap<String, int[]> policyCarModelCount(String username, String password, int[] ranges, int flatRate)
    {
        HashMap<String, int[]> output = new HashMap<>(); // carmodel, user count per range 
        if (validateUser(username, password)) //validation
        { 
            ArrayList<String> cars = populateDistinctCarModels(username, password);
            for (String car : cars)
            {
                output.put(car, new int[ranges.length]); // populate output with cars and empty arrays
            }
            
            for (InsurancePolicy policy : policies.values()) //iterate over policies
            {
                double premium = policy.calcPayment(flatRate);
                // code counts users per range without doubling up
                for (int i = 0 ; i < ranges.length ; i++)
                {   
                    if(premium <= ranges[i])        // finds range
                    {
                        output.get(policy.car.getModel())[i] += 1;    // adds policy count
                        break;
                    }
                }
            }  
        }
        else
        {
            System.out.println("Invalid Access Rights. Operation Terminated.");
        }
        return output;
    }
    
}
