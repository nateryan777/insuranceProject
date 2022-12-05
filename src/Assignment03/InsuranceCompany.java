package Assignment03;

import java.util.Comparator;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

public class InsuranceCompany implements Serializable
{    
    //attributes
    protected String name; 
//    protected ArrayList<User> users; // list of all the users having a policy with the company
    protected HashMap<Integer, User> users;
    protected String adminUsername;
    protected String adminPassword; 
    protected int flatRate;

    //constructor
    public InsuranceCompany(String name, String adminUsername, String adminPassword, int flatRate)
    {
        this.name = name;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate;
//        this.users = new ArrayList<User>();
        this.users = new HashMap<>();
    }
    
    //copy constructor - ARRAY LIST METHOD
    
//    public InsuranceCompany(InsuranceCompany company)
//    {
//        name = company.name;
//        adminUsername = company.adminUsername;
//        adminPassword = company.adminPassword;
//        flatRate = company.flatRate;
//        
//        //ARRAY LIST CODE
//        users = new ArrayList<User>();
//        
//        // loop through users calling user constructor
//        for (User user : company.users)
//        {
//            users.add(new User(user)); // call copy constructor
//        }
//    }
    
    //copy constructor - HASH MAP METHOD
    
    public InsuranceCompany(InsuranceCompany company)
    {
        this.name = company.name;
        this.adminUsername = company.adminUsername;
        this.adminPassword = company.adminPassword;
        this.flatRate = company.flatRate;
        
        //HASH MAP CODE
        this.users = new HashMap<>();
        
        // loop through users calling user constructor
        for (User user : company.users.values())
        {
            User userCopy = new User(user);
            users.put(userCopy.getUserID(), userCopy); // call copy constructor
        }
    }
    
    // default constructor (creates empty object)
    
    public InsuranceCompany()
    {}
    
    // inner comparator class to compare users by premium payments
    public static class UserPremiumComparator implements Comparator<User>
    {
        @Override
        public int compare(User a, User b)
        {
            int flatRate = 100; // assume constant flat rate
            double thisTotal = a.calcTotalPremiums(a.getUsername(), a.getPassword(), flatRate); // calculate User a total premiums
            double otherTotal = b.calcTotalPremiums(b.getUsername(), b.getPassword(), flatRate); // calculate User b total premiums

            if (thisTotal==otherTotal)
                return 0;

            if (thisTotal > otherTotal)
            {
                return -1;
            }
            else
            {
                return 1;
            } 
        }
    }
    
    public ArrayList<User> sortUsersByPremium()
    {
        ArrayList<User> output = new ArrayList<>();
        
        //sort HashMap to array
        for (User user : users.values())
        {
            output.add(user);
        }
        // use comparator to sort by premiums
        Collections.sort(output, new UserPremiumComparator());
        return output;
    }
    
    //override clone method
    
    //HASH MAP METHOD
    @Override
    public InsuranceCompany clone() throws CloneNotSupportedException
    {
        InsuranceCompany output = (InsuranceCompany)super.clone(); //create shallow copy
        output.users = new HashMap<>(); // clear shallow copy of users array
        for (User user : users.values())
        {
            output.users.put(user.getUserID(), new User(user)); //calls User copy constructor
        }
        return output;
    }
    
    //ARRAY LIST METHOD
//    @Override
//    public InsuranceCompany clone() throws CloneNotSupportedException
//    {
//        InsuranceCompany output = (InsuranceCompany)super.clone(); //create shallow copy
//        output.users = new ArrayList<User>(); // clear shallow copy of users array
//        for (User user : users)
//        {
//            output.users.add(new User(user));
//        }
//        return output;
//    }
    
    //accessors
    
    public String getName()
    {
        return name;
    }
    public String getAdminUsername()
    {
        return adminUsername;
    }
    public String getAdminPassword()
    {
        return adminPassword;
    }
    public int getFlatRate()
    {
        return flatRate;
    }
    //HASH MAP METHOD
    public HashMap<Integer, User> getUsers()
    {
        return users;
    }
    
    //ARRAY LIST METHOD
//    public ArrayList<User> getUsers()
//    {
//        return users;
//    }
    //setters
    
    public void setName(String name)
    {
        this.name = name;
    }
    public void setAdminUsername(String adminUsername)
    {
        this.adminUsername = adminUsername;
    }
    public void setAdminPassword(String adminName, String adminPassword, String adminNewPassword)
    {
        if (validateAdmin(adminName, adminPassword))
        {
            this.adminPassword = adminNewPassword;
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
    }
    public void setFlatRate(int flatRate)
    {
        this.flatRate = flatRate;
    }
    //HASH MAP METHOD
    public void setUsers(HashMap<Integer, User> users)
    {
        this.users = users;
    }    
    
    //ARRAY LIST METHOD
//    public void setUsers(ArrayList<User> users)
//    {
//        this.users = users;
//    }
    
    // returns true if username and password matches the admin login details
    public boolean validateAdmin(String username, String password)
    {
        return (adminUsername.equals(username) && adminPassword.equals(password));
    }
    
    // adds the user to users list if userID is unique, if not returns false
    
    public boolean addUser(String adminName, String adminPassword, User user)
    {
        boolean output=false;
        if (validateAdmin(adminName, adminPassword))
        {
            int userID = user.getUserID();
            if (!users.containsValue(user))
            {
                {
                    users.put(userID, user);
                    output = true;
                }
            }
            else
            {
                output = false;
            }
        }
        else
        {
            System.out.println("Invalid Acess Details! Operation Terminated.");
        }
        return output;
    }
    
    //ARRAY LIST METHOD
//    public boolean addUser(User user)
//    {
//        for (User existingUser : users)
//        {
//            if (existingUser.getUserID()==user.getUserID()) //ID not unique if true
//            {
//                return false;
//            }
//        }
//        users.add(user);
//        return true;
//    }
    
    // creates and adds the User to users list if userID is unique, if not returns false.
    public boolean addUser(String adminName, String adminPassword, String name, Address address, String username, String password)
    {
        User newUser = new User(name, address.getStreetNum(), address.getStreet(), address.getSuburb(), address.getCity(), username, password);
        //will return false if user ID not unique and user is not added, otherwise will add user and return true
        return addUser(adminName, adminPassword, newUser);
    }
    
    // finds the user with the given ID or returns null if user does not exist
    
    //HASH MAP CODE
    public User findUser(String adminName, String adminPassword, int userID)
    {
        User output = null;
        if (validateAdmin(adminName, adminPassword))
        {
            output = users.get(userID);
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
        return output;
    }
    
    //ARRAY LIST CODE
//    public User findUser(int userID)
//    {
//        for (User user : users)
//        {
//            if (user.getUserID()==(userID))
//            {
//                return user;
//            }
//        }
//        return null;
//    }
    
    // finds the user with the given userID by using findUser method and adds the policy to the user, unsuccessful if userID does not exist or policy is not unique
    
    // HASH MAP
    public boolean addPolicy (String adminName, String adminPassword, int userID, InsurancePolicy policy)
    {
        User user=null;
        if (validateAdmin(adminName, adminPassword)) //check access rights
        {        
            user = users.get(userID);
            if (user==null)
            {
                System.out.println("Failed to add policy, user does not exist!");
                return false;
            }
            HashMap<Integer, InsurancePolicy> userPolicies = user.getPolicies(user.getUsername(), user.getPassword());
            if (userPolicies.get(policy.id)!=null)
            {
                System.out.println("Failed to add policy, duplicate policy ID!");
                return false;
            }
        }
        return user.addPolicy(user.getUsername(), user.getPassword(), policy); // verified admin has access to users
    }
    
    // proxy call user add policy
    public boolean addPolicy (User user, InsurancePolicy policy)
    {
        return user.addPolicy(user.getUsername(), user.getPassword(), policy); // verified admin has access to users
    }
    
        // ARRAY LIST METHOD
//        public boolean addPolicy (int userID, InsurancePolicy policy)
//    {
//        User user = findUser(userID); // if user ID does not exist in insurance company users return false
//        if (user==null)
//        {
//            System.out.println("Failed to add policy, user does not exist!");
//            return false;
//        }
//        for (InsurancePolicy tempPolicy : allPolicies()) // if policy ID exists in all policies return false
//        {
//            if (policy.id==tempPolicy.id)
//            {
//                System.out.println("Failed to add policy, duplicate policy ID!");
//                return false;
//            }
//        }
//        System.out.println("Successfully added policy!");
//        return user.addPolicy(policy);
//    }
    
    // finds the insurance policy for the given userID and returns it. Returns null if userID does not exist or policyID does not exist for the given user
    
    // HASH MAP
    public InsurancePolicy findPolicy (String adminName, String adminPassword, int userID ,int policyID)
    {
        User user = users.get(userID);
        if (user==null)
        {
            return null;
        }
        
        return user.getPolicies(user.getUsername(), user.getPassword()).get(policyID);
    }
    
    //ARRAY METHOD
//    public InsurancePolicy findPolicy (int userID ,int policyID)
//    {
//        User user = findUser(userID);
//        if (user==null)
//        {
//            return null;
//        }
//        return user.findPolicy(policyID);
//    }
    
    // prints the user information and all the policies for the given userID 
    public void printPolicies(String adminName, String adminPassword, User user)
    {
        if (validateAdmin(adminName, adminPassword)) // check access rights
        {
            user.printPolicies(user.getUsername(),user.getPassword(), flatRate);
        }
        else
        {
            System.out.println("Invalid access details. Operation Termintated.");
        }
    }
    
    // prints all the users and for each user all the policies by calling User. PrintPolicies(int flatRate)

    //HASH MAP METHOD
    public void print(String adminName, String adminPassword)
    {
        if (validateAdmin(adminName, adminPassword))
        {
            for (User user : users.values())
            {
                user.printPolicies(user.getUsername(), user.getPassword(), flatRate);
            }
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
    }
    
    // proxy call from UI to Insurance Policy method "print policies"
    public void printPolicies(String adminName, String adminPassword, HashMap<Integer, InsurancePolicy>policies)
    {
        if (validateAdmin(adminName, adminPassword))
        {
            for (InsurancePolicy policy : policies.values())
                policy.print(); //print using print method
        }
    }
    
    public void printUserInfo(String adminName, String adminPassword, User user)
    {
        if (validateAdmin(adminName, adminPassword))
        {
            user.print(user.getUsername(), user.getPassword());
        }
    }
    public void printUserInfo(User user)
    {
            user.print(user.getUsername(), user.getPassword());
    }

    //ARRAY LIST METHOD
//    public void print()
//    {
//        for (User user : users)
//        {
//            user.printPolicies(flatRate);
//        }
//    }
    
    // coverts the whole object to string (including all the users and their policies)

    //HASH MAP METHOD
    @Override
    public String toString()
    {
        String returnString = "Insurance Company: "+name+"\n\n";
        for (User user : users.values())
        {
            returnString += user.toString()+"\n";
        }    
        return returnString;
    }

    // ARRAY LIST METHOD
//    @Override
//    public String toString()
//    {
//        String returnString = "Insurance Company: "+name+"\n\n";
//        for (User user : users)
//        {
//            returnString += user.toString()+"\n";
//        }    
//        return returnString;
//    }
    
    // finds the user with the given userID (by calling findUser) and calls the createThirdPartyPolicy for that user. Returns false if the user does not exist or if User.createThirdPartyPolicy returns false
    public boolean createThirdPartyPolicy(String adminName, String adminPassword, int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) throws PolicyException, NameException
    {
        Boolean value=false;
        if (validateAdmin(adminName, adminPassword))
        {
            User user = findUser(adminName, adminPassword, userID);
            if (user != null)
            {
                value = user.createThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments); //if policy creation fails, returns false else returns true
            }
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
        return value;
    }
    
    // finds the user with the given userID (by calling findUser) and calls the createComprehensivePolicy for that user. Returns false if the user does not exist or if User.createComprehensivePolicy returns false
    public boolean createComprehensivePolicy(String adminName, String adminPassword, int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) throws PolicyException, NameException
    {
        boolean value = false;
        if (validateAdmin(adminName, adminPassword))
        {
            User user = findUser(adminName, adminPassword, userID);
            if (user != null)
            {
                value = user.createComprehensivePolicy(user.getUsername(), user.getPassword(), policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level); //if policy creation fails, returns false else returns true
            }
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
        return value;
    }
    
    // returns the total premium payments for the given user
    public double calcTotalPayments(String adminName, String adminPassword, User user)
    {
        double output = 0;
        if (validateAdmin(adminName, adminPassword))
        {
            output = findUser(adminName, adminPassword, user.getUserID()).calcTotalPremiums(user.getUsername(), user.getPassword(), flatRate); // find user by ID, calculate premiums and return result
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
        return output;
    }
    
    // returns the total premium payments for all the users in the company

    //HASH MAP METHOD
//    public double calcTotalPayments(String adminName, String adminPassword)
//    {
//        double premiumTotal = 0;
//        if (validateAdmin(adminName, adminPassword))
//        {
//            for (User user : users.values())
//            {
//                premiumTotal += user.calcTotalPremiums(user.getUsername(), user.getPassword(), flatRate);
//            }
//        }
//        return premiumTotal;
//    }
    
    // use LAMDA / STREAMS
    public double calcTotalPayments(String adminName, String adminPassword)
    {
        return (double) users.values().stream()
                .mapToDouble(user -> user.calcTotalPremiums(user.getUsername(), user.getPassword(), flatRate))
                .reduce(0, (x, y) -> x + y);
    }

    //ARRAY LIST METHOD
//    public double calcTotalPayments()
//    {
//        double premiumTotal = 0;
//        for (User user : users)
//        {
//            premiumTotal += user.calcTotalPremiums(flatRate);
//        }
//        return premiumTotal;
//    }
    
    // proxy call to Insurance Policy method
    
    public double calcTotalPayments(String adminName, String adminPassword, HashMap<Integer, InsurancePolicy> policies, int flatRate)
    {
        double premiumTotal = 0; //initialise premium total variable
        if (validateAdmin(adminName, adminPassword))
        {
            for (InsurancePolicy policy : policies.values())
                premiumTotal += policy.calcPayment(flatRate); //calculate premium and add to total
        }
        else
        {
            System.out.println("Invalid Access Details. Operation terminated.");
        }
        return premiumTotal;
    }

    // calls carPriceRiseAll method for the given user. Returns false if user cannot be found
    //HASH MAP METHOD
    public boolean carPriceRise (String adminName, String adminPassword, int userID, double risePercent)
    {
        boolean value = false;
        if (validateAdmin(adminName, adminPassword))
        {
            User user = findUser(adminName, adminPassword, userID);
            if (user != null)
            {
                for (InsurancePolicy policy : user.getPolicies(user.getUsername(), user.getPassword()).values())
                {
                    policy.carPriceRise(risePercent);
                }
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
//    public boolean carPriceRise (int userID, double risePercent)
//    {
//        User user = findUser(userID);
//        if (user == null)
//        {
//            return false;
//        }
//        for (InsurancePolicy policy : user.getPolicies())
//        {
//            policy.carPriceRise(risePercent);
//        }
//        return true;
//    }
    
    //Raise the price of all cars for all users in the company

    //HASH MAP METHOD
    public void carPriceRise(String adminName, String adminPassword, double risePercent)
    {
        if (validateAdmin(adminName, adminPassword))
        {
            for (User user : users.values())
            {
                for (InsurancePolicy policy : user.getPolicies(user.getUsername(), user.getPassword()).values())
                {
                    policy.carPriceRise(risePercent);
                }
            }
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
    }

    //ARRAY LIST METHOD
//    public void carPriceRise(double risePercent)
//    {
//        for (User user : users)
//        {
//            for (InsurancePolicy policy : user.getPolicies())
//            {
//                policy.carPriceRise(risePercent);
//            }
//        }
//    }
    
    // returns a list of all the policies in the company across all users

    //HASH MAP METHOD
    public HashMap<Integer, InsurancePolicy> allPolicies(String adminName, String adminPassword)
    {
        // initialise new hash map to store all policies
        HashMap<Integer, InsurancePolicy> allPolicies = new HashMap<>();
        
        if (validateAdmin(adminName, adminPassword))
        {
            for (User user : users.values())
            {
                for (InsurancePolicy policy : user.getPolicies(user.getUsername(), user.getPassword()).values())
                {
                    allPolicies.put(policy.id, policy);
                }
            }   
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
        return allPolicies;
    }

    //ARRAY LIST METHOD
//    public ArrayList<InsurancePolicy> allPolicies()
//    {
//        // initialise new array list to store all policies
//        ArrayList<InsurancePolicy> allPolicies = new ArrayList<InsurancePolicy>();
//        
//        for (User user : users)
//        {
//            for (InsurancePolicy policy : user.getPolicies())
//            {
//                allPolicies.add(policy);
//            }
//        }        
//
//        return allPolicies;
//    }
    
    // find the user by calling findUser and calls filterByCarModel for the given user 

    //HASH MAP METHOD
    public HashMap<Integer, InsurancePolicy> filterByCarModel(String adminName, String adminPassword, int userID, String carModel)
    {
        HashMap<Integer, InsurancePolicy> output = new HashMap<>();
        if (validateAdmin(adminName, adminPassword))
        {
            User user = findUser(adminName, adminPassword, userID);
            if (user != null)
            {
            output = user.filterByCarModel(carModel);
            }
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
        return output;
    }

    //ARRAY LIST METHOD
//    public ArrayList<InsurancePolicy> filterByCarModel (int userID, String carModel)
//    {
//        User user = findUser(userID);
//        if (user != null)
//        {
//        return user.filterByCarModel(carModel);
//        }
//        else
//        {
//        return new ArrayList<InsurancePolicy>();
//        }
//    }
    
    // find the user by calling findUser and calls filterByExpiryDate for the given user

    //HASH MAP METHOD
    public HashMap<Integer, InsurancePolicy> filterByExpiryDate (String adminName, String adminPassword, int userID, MyDate date)
    {
        HashMap<Integer, InsurancePolicy> output = new HashMap<>();
        if (validateAdmin(adminName, adminPassword))
        {
            User user = findUser(adminName, adminPassword, userID);
            if (user != null)
            {
            output = user.filterByExpiryDate(user.getUsername(), user.getPassword(), date);
            }
        }
        return output;
    }

     //ARRAY LIST METHOD
//   public ArrayList<InsurancePolicy> filterByExpiryDate (int userID, MyDate date)
//   {
//        User user = findUser(userID);
//        if (user != null)
//        {
//        return user.filterByExpiryDate(date);
//        }
//        else
//        {
//        return new ArrayList<InsurancePolicy>();
//        }
//   }
   
   // filters all the policies in the company by carModel across all users. Iterate over a loop for all users and for each user call the filterByCarModel method and add all the results together for a global list including all users.

    //HASH MAP METHOD
//    public HashMap<Integer, InsurancePolicy> filterByCarModel (String adminName, String adminPassword, String carModel)
//    {
//        HashMap<Integer, InsurancePolicy> filterByCarModel = new HashMap<>();
//        
//        if (validateAdmin(adminName, adminPassword))
//        {
//            for (User user : users.values())
//            {
//                for (InsurancePolicy policy: user.filterByCarModel(carModel).values())
//                {
//                    filterByCarModel.put(policy.id, policy);
//                }
//            }
//        }
//        else
//        {
//            System.out.println("Invalid Access Details. Operation Terminating.");
//        }
//        return filterByCarModel;
//    } 
    
    //user LAMBDA / STREAMS
    public HashMap<Integer, InsurancePolicy> filterByCarModel (String adminName, String adminPassword, String carModel)
    {
        HashMap<Integer, InsurancePolicy> output = new HashMap<>();
        
        if (validateAdmin(adminName, adminPassword))
        {
            users.values().forEach(user -> user.getPolicies(user.getUsername(), user.getPassword()).values().stream()
                    .filter(policy -> policy.getCar().getModel().contains(carModel))
                    .forEach(policy -> output.put(policy.id, policy)));            
        }
        else
        {
            System.out.println("Invalid Access Details. Operation Terminating.");
        }
        return output;
    } 

     //ARRAY LIST METHOD
//   public ArrayList<InsurancePolicy> filterByCarModel (String carModel)
//   {
//       ArrayList<InsurancePolicy> filterByCarModel = new ArrayList<InsurancePolicy>();
//       for (User user : users)
//       {
//           for (InsurancePolicy policy: user.filterByCarModel(carModel))
//           {
//               filterByCarModel.add(policy);
//           }
//       }
//       return filterByCarModel;
//   }
   
    // filters all the policies in the company by ExpiryDate across all users. The same as above 

    //HASH MAP METHOD
    public HashMap<Integer, InsurancePolicy> filterByExpiryDate(String adminName, String adminPassword, MyDate date)
    {
       HashMap<Integer, InsurancePolicy> filterByExpiryDate = new HashMap<>();
       if (validateAdmin(adminName, adminPassword))
        {
            for (User user : users.values())
            {
                for (InsurancePolicy policy: user.filterByExpiryDate(user.getUsername(),user.getPassword(), date).values())
                {
                   filterByExpiryDate.put(policy.id, policy);
                }
            }
        }
        return filterByExpiryDate;
    }

    //ARRAY LIST METHOD
//    public ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date)
//    {
//       ArrayList<InsurancePolicy> filterByExpiryDate = new ArrayList<InsurancePolicy>();
//       for (User user : users)
//       {
//           for (InsurancePolicy policy: user.filterByExpiryDate(date))
//            {
//               filterByExpiryDate.add(policy);
//            }
//        }
//       return filterByExpiryDate;
//    }
            
    //payments across cities reporting-----------------------------------------------
   
    //goes through all the users and populate a list of distinct city names for all users and returns it as a list. 

    //HASH MAP METHOD
    public ArrayList<String> populateDistinctCityNames(String adminName, String adminPassword)
    {
        ArrayList<String> cities = new ArrayList<>();
        if (validateAdmin(adminName, adminPassword))
        {
            for (User user: getUsers().values())
            {
                String city = user.getAddress().getCity();
                if (!cities.contains(city))
                {
                    cities.add(city);
                }   
            }
        }
        else
        {
            System.out.print("Invalid Access Rights. Operation Terminated.");
        }
        return cities;
    }

    //ARRAY LIST METHOD
//    public ArrayList<String> populateDistinctCityNames()
//    {
//        ArrayList<String> cities = new ArrayList<String>();
//        for (User user: getUsers())
//        {
//            String city = user.getAddress().getCity();
//            if (!cities.contains(city))
//            {
//                cities.add(city);
//            }   
//        }
//        return cities;
//    }
    
    // returns the total premium payment for the given city across all users.

    //HASH MAP METHOD
    public double getTotalPaymentForCity(String adminName, String adminPassword, String city)
    {
        double cityTotalPayment=0;
        if (validateAdmin(adminName, adminPassword))
        {
            for (User user: users.values())
            {
                String currentCity = user.getAddress().getCity();
                if (currentCity.equals(city))
                {
                    cityTotalPayment += user.calcTotalPremiums(user.getUsername(), user.getPassword(), flatRate);
                } 
            }
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
        return cityTotalPayment;
    }

    //ARRAY LIST METHOD
//    public double getTotalPaymentForCity(String city)
//    {
//        double cityTotalPayment=0;
//        for (User user: getUsers())
//        {
//            String currentCity = user.getAddress().getCity();
//            if (currentCity.equals(city))
//            {
//                cityTotalPayment += user.calcTotalPremiums(flatRate);
//            } 
//        }
//        return cityTotalPayment;
//    }
    // aggregates the total premium payments for each city in the list and returns it as a double list with the same order as city names. This method calls getTotalPaymentForCity
    public ArrayList<Double> getTotalPaymentPerCity(String adminName, String adminPassword, ArrayList<String> cities)
    {
        ArrayList<Double> totalPaymentPerCity = new ArrayList<>();
        if (validateAdmin(adminName, adminPassword))
        {
            for (String city : cities)
            {
                totalPaymentPerCity.add(getTotalPaymentForCity(adminName, adminPassword, city));
            }
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
        return totalPaymentPerCity;
    }
    
    // generates the report as follow:
    public void reportPaymentPerCity(String adminName, String adminPassword, ArrayList<String> cities, ArrayList<Double> payments)
    {
        if (validateAdmin(adminName, adminPassword))
        {
            //print headings
            System.out.printf("\n%s%26s\n","City Name", "Total Premium Payment");

            for (int index = 0; index < cities.size() ; index++)
            {
                System.out.printf("%-12s%13.2f\n", cities.get(index), payments.get(index));
            }
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
    }
    
    public User validateUser(String username, String password)
    {
        User output = null;
        for (User user : users.values())
        {
            if (user.validateUser(username, password))
            {
                output = user;
            }
        }
        return output;
    }
    
    //removes a policy from the user

    //HASH MAP METHOD
    public boolean removePolicy (String adminName, String adminPassword, int userID, int policyID)
    {
        boolean value = false;
        if (validateAdmin(adminName, adminPassword))
        {
            InsurancePolicy oldPolicy;
            User user = findUser(adminName, adminPassword, userID); // if user ID exists in insurance company users then find policy
            if (user!=null)
            {
                oldPolicy = user.findPolicy(user.getUsername(), user.getPassword(),policyID);
                if (oldPolicy!=null)
                {
                    user.getPolicies(user.getUsername(), user.getPassword()).remove(oldPolicy.id);
                    value = true;
                }
            }
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
        return value;
    }
    
    // proxy call to user remove policy
    public boolean removePolicy (User user, int policyID)
    {
        return user.removePolicy(user.getUsername(), user.getPassword(), policyID);
    }

    //ARRAY LIST METHOD
//    public boolean removePolicy (int userID, int policyID)
//    {
//        boolean value = false;
//        InsurancePolicy oldPolicy;
//        User user = findUser(userID); // if user ID exists in insurance company users then find policy
//        if (user!=null)
//        {
//            oldPolicy = user.findPolicy(policyID);
//            if (oldPolicy!=null)
//            {
//                user.getPolicies().remove(oldPolicy);
//                value = true;
//            }
//        }
//        return value;
//    }
    
    //remove user from array list users

    //HASH MAP METHOD
    public boolean removeUser(String adminName, String adminPassword, int userID)
    {
        boolean value = false;
        if (validateAdmin(adminName, adminPassword))
        {
            User user = findUser(adminName, adminPassword, userID);
            if (user!=null)
            {
                users.remove(userID);
                value = true;
            }
        }
        return value;
    }

    //ARRAY LIST METHOD
//    public boolean removeUser(int userID)
//    {
//        boolean value = false;
//        User user = findUser(userID);
//        if (user!=null)
//        {
//            users.remove(user);
//            value = true;
//        }
//        return value;
//    }
    
    //Reporting payments by car models--------------------------------------------------------------------------------------------------
    
    // goes through all the users within the InsuranceCompany and populates a list of distinct car models 

    //HASH MAP METHOD
    public ArrayList<String> populateDistinctCarModels()
    {
        ArrayList<String> carModels = new ArrayList<>();
        for (User user : users.values())
        {
            ArrayList<String> userModels = user.populateDistinctCarModels(user.getUsername(), user.getPassword());
            
            for (String model : userModels)
            {
                if (!carModels.contains(model))
                {
                    carModels.add(model);
                }
            }
        }
        return carModels;
    } 

    //ARRAY LIST METHOD
//    public ArrayList<String> populateDistinctCarModels()
//    {
//        ArrayList<String> carModels = new ArrayList<String>();
//        for (User user : users)
//        {
//            ArrayList<String> userModels = user.populateDistinctCarModels();
//            for (String model : userModels)
//            {
//                if (!carModels.contains(model))
//                {
//                    carModels.add(model);
//                }
//            }
//        }
//        return carModels;
//    } 
    
    // returns the count for each model across all the users. You need to call the corresponding method inside the User and aggregate them for all the users

    //HASH MAP METHOD
    public ArrayList<Integer> getTotalCountPerCarModel (ArrayList<String> carModels)
    {
        ArrayList<Integer> carCount = new ArrayList<>();
        for (User user : users.values())
        {
            ArrayList<Integer> userCount = user.getTotalCountPerCarModel(carModels);
            carCount.addAll(userCount); // adds the contents of userCount to carCount
        }
        return carCount;
    }

    //ARRAY LIST METHOD
//    public ArrayList<Integer> getTotalCountPerCarModel (ArrayList<String> carModels)
//    {
//        ArrayList<Integer> carCount = new ArrayList<Integer>();
//        for (User user : users)
//        {
//            ArrayList<Integer> userCount = user.getTotalCountPerCarModel(carModels);
//            carCount.addAll(userCount); // adds the contents of userCount to carCount
//        }
//        return carCount;
//    }
    
    // returns the Total Payment for each model in the carModels as a list of doubles across all users in company

    //HASH MAP METHOD returns arraylist
    public ArrayList<Double> getTotalPaymentPerCarModel (ArrayList<String> carModels, int flatRate)
    {
        ArrayList<Double> carPayments = new ArrayList<>();
        for (User user : users.values())
        {
            ArrayList<Double> userPayments = user.getTotalPaymentPerCarModel(carModels, flatRate);
            carPayments.addAll(userPayments); // adds the contents of userCount to carCount
        }
        return carPayments;
    }

    //ARRAY LIST METHOD
//    public ArrayList<Double> getTotalPaymentPerCarModel (ArrayList<String> carModels, int flatRate)
//    {
//        ArrayList<Double> carPayments = new ArrayList<Double>();
//        for (User user : users)
//        {
//            ArrayList<Double> userPayments = user.getTotalPaymentPerCarModel(carModels, flatRate);
//            carPayments.addAll(userPayments); // adds the contents of userCount to carCount
//        }
//        return carPayments;
//    }
    
    // to generate the same report as before but across all users in the system
//    public void reportPaymentsPerCarModel(ArrayList<String> carModels, ArrayList<Integer>counts, ArrayList<Double> premiumPayments)
//    {
//        //print headings
//        System.out.printf("\n%-17s%26s%28s\n","Car Model", "Total Premium Payment", "Average Premium Payment");
//        
//        for (int index = 0; index < carModels.size() ; index++)
//        {
//            System.out.printf("%-28s$%-27.2f$%-17.2f\n", carModels.get(index), premiumPayments.get(index), premiumPayments.get(index)/counts.get(index));
//        }
//    }
    
    // use LAMBDA to generate the same report as before but across all users in the system
    public void reportPaymentsPerCarModel(HashMap<String, Integer> carModels, HashMap<String, Integer>counts, HashMap<String, Double> premiumPayments)
    {
        //print headings
        System.out.printf("\n%-17s%26s%28s\n","Car Model", "Total Premium Payment", "Average Premium Payment");
      
        carModels.keySet().forEach(x -> System.out.printf("%-28s$%-27.2f$%-17.2f\n", x, premiumPayments.get(x), premiumPayments.get(x)/counts.get(x)));
        
    }
    
    // deep copy of users in company

    //HASH MAP METHOD
    public HashMap<Integer, User> deepCopyUsers()
    {
        HashMap<Integer, User> deepCopy = new HashMap<>();
        for (User user : users.values())
        {
            deepCopy.put(user.getUserID(), new User(user)); // call user copy contructor for deep copy
        }        
        return deepCopy;
    }

    //ARRAY LIST METHOD
//    public ArrayList<User> deepCopyUsers()
//    {
//        ArrayList<User> deepCopy = new ArrayList<User>();
//        for (User user : users)
//        {
//            deepCopy.add(new User(user)); // call user copy contructor for deep copy
//        }        
//        
//        return deepCopy;
//    }
    
    // shallow copy of users in company

    //HASH MAP METHOD
    public HashMap<Integer, User> shallowCopyUsers()
    {
        HashMap<Integer, User> shallowCopy = users;        
        return shallowCopy; 
    }

    //ARRAY LIST METHOD
//    public ArrayList<User> shallowCopyUsers()
//    {
//        ArrayList<User> shallowCopy = users;        
//        return shallowCopy; 
//    }
    
    // sort users by city name in addresses and return sorted array

    //HASH MAP METHOD
    public ArrayList<User> sortUsers()
    {
        ArrayList<User> output = new ArrayList<>();
        //convert hash map to array list for sorting
        for (User user : users.values())
        {
            output.add(user);
        }
        Collections.sort(output);
        
        return output;
    }

    //ARRAY LIST METHOD
//    public ArrayList<User> sortUsers()
//    {
//        ArrayList<User> output = users;
//        Collections.sort(output);
//        
//        return output;
//    }
    
    // print list of company users
    public void printCompanyUsers(ArrayList<User> users)
    {
        for (User user : users)
        {
            System.out.println(user);
        }
    }
    
     public void printCompanyUsers(HashMap<Integer, User> users)
    {
        for (User user : users.values())
        {
            System.out.println(user);
        }
    }
    
    // which aggregates the total premium payments for users from any given city within the company as <City name, Total Premium Payments for all users from the given city>
     
//    public HashMap<String, Double> getTotalPremiumPerCity(String adminName, String adminPassword)
//    {
//        HashMap<String, Double> cityTotals = new HashMap<>();
//        if (validateAdmin(adminName, adminPassword))
//        {
//            for (User user : users.values())
//            {
//                String city = user.getAddress().getCity();
//                if (!cityTotals.containsKey(city))
//                {
//                    double totalPremiums = getTotalPaymentForCity(adminName, adminPassword, city);
//                    cityTotals.put(city, totalPremiums);
//                }
//            }
//        }
//        else
//        {
//            System.out.println("Invalid Access Details! Operation Terminated.");
//        }
//        return cityTotals;
//    }

    // use LAMBDA / STREAMS which aggregates the total premium payments for users from any given city within the company as <City name, Total Premium Payments for all users from the given city>
    public HashMap<String, Double> getTotalPremiumPerCity(String adminName, String adminPassword)
    {
        HashMap<String, Double> cityTotals = new HashMap<>();
        if (validateAdmin(adminName, adminPassword))
        {
            users.values().stream()
                    .forEach(user -> {String city = user.getAddress().getCity();
                    if (!cityTotals.containsKey(city))
                    {
                        double totalPremiums = getTotalPaymentForCity(adminName, adminPassword, city);
                        cityTotals.put(city, totalPremiums);
                    }    
                    });  
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
        return cityTotals;
    }
    
    // returns a list of pairs of (Car Model, Count) as a HashMap across all the users within the company (by calling the corresponding method inside User)
    
//    public HashMap<String, Integer> getTotalCountPerCarModel()
//    {
//        HashMap<String, Integer> allCarCount = new HashMap<>();
//        
//        for (User user : users.values())
//        {
//            HashMap<String, Integer> userCarCount = user.getTotalCountPerCarModel(); // call user car count method and store in userTotal
//            for (String car : userCarCount.keySet())
//            {
//                if (allCarCount.containsKey(car))
//                {
//                    allCarCount.put(car, allCarCount.get(car)+userCarCount.get(car));
//                }
//                else
//                {
//                    allCarCount.put(car, userCarCount.get(car));
//                }
//            }
//        }
//        return allCarCount;
//    }
    
    // uses LAMDBDA to returns a list of pairs of (Car Model, Count) as a HashMap across all the users within the company (by calling the corresponding method inside User)
    public HashMap<String, Integer> getTotalCountPerCarModel()
    {
        return (HashMap<String, Integer>) populateDistinctCarModels().stream()
                .collect(Collectors.toMap(x -> x, x -> users.values().stream()
                .mapToInt(y -> y.getTotalCountForCarModel(x)).sum()));
    }
    
    // returns a list of pairs of (Car Model, Total Premium Payments)  as a HashMap across all the users within the company (by calling the corresponding method inside User)
    
//    public HashMap<String,Double> getTotalPremiumPerCarModel(int flatRate)
//    {
//        HashMap<String, Double> allCarPremiums = new HashMap<>();
//        
//        for (User user : users.values())
//        {
//            HashMap<String, Double> userCarPremiums = user.getTotalPremiumPerCarModel(flatRate);
//            for (String car : userCarPremiums.keySet())
//            {
//                if (allCarPremiums.containsKey(car))
//                {
//                    allCarPremiums.put(car, allCarPremiums.get(car)+userCarPremiums.get(car));
//                }
//                else
//                {
//                    allCarPremiums.put(car, userCarPremiums.get(car));
//                }
//            }
//        }
//        return allCarPremiums;
//    }
    
    
    // using LAMBDA / STREAMS
    public HashMap<String,Double> getTotalPremiumPerCarModel(int flatRate)
    {
        return (HashMap<String, Double>) populateDistinctCarModels().stream()
                .collect(Collectors.toMap(x -> x, x -> users.values().stream()
                .mapToDouble(y -> y.getTotalPaymentForCarModel(x, flatRate)).sum()));
    }
    
    // report method diplays total premiums by car model, using previous data aggregations methods
    
//    public String reportPaymentsPerCarModel(String adminName, String adminPassword)
//    {
//        String output = "";
//                
//        if (validateAdmin(adminName, adminPassword))
//        {
//            HashMap<String, Integer> totalCarCount = getTotalCountPerCarModel();
//
//            HashMap<String, Double> totalCarPremiums = getTotalPremiumPerCarModel(flatRate);
//
//            output = String.format("\n%-17s%26s%28s\n","Car Model", "Total Premium Payment", "Average Premium Payment");
//            
//            for (String key : totalCarCount.keySet())
//            {
//                output += String.format("%-28s$%-27.2f$%-17.2f\n", key, totalCarPremiums.get(key), totalCarPremiums.get(key)/totalCarCount.get(key));
//            }
//            
//            //print headings
////            System.out.printf("\n%-17s%26s%28s\n","Car Model", "Total Premium Payment", "Average Premium Payment");
////
////            for (String key : totalCarCount.keySet())
////            {
////                System.out.printf("%-28s$%-27.2f$%-17.2f\n", key, totalCarPremiums.get(key), totalCarPremiums.get(key)/totalCarCount.get(key));
////            }
//        }
//        return output;
//    }
    
    
    public String reportPaymentsPerCarModel(String adminName, String adminPassword)
    {
       String output="";
                
        if (validateAdmin(adminName, adminPassword))
        {
            HashMap<String, Integer> totalCarCount = getTotalCountPerCarModel();
            HashMap<String, Double> totalCarPremiums = getTotalPremiumPerCarModel(flatRate);

            output = String.format("\n%-17s%26s%28s\n","Car Model", "Total Premium Payment", "Average Premium Payment");
            
            output += totalCarPremiums.keySet().stream()
                                      .map( x -> String.format("%-28s$%-27.2f$%-17.2f\n", x, totalCarPremiums.get(x), totalCarPremiums.get(x)/totalCarCount.get(x)))
                                      .collect(Collectors.joining());
            
        }
        return output;
    }
    
    // report method for premiums by city using getTotalPremiumPerCity()
    
    public String reportTotalPremiumPerCity(String adminName, String adminPassword)
    {
        HashMap<String, Double> cityTotals = getTotalPremiumPerCity(adminName, adminPassword);
        
        String output = String.format("\n%-17s%26s\n","City", "Total Premium Payment");
        for (String key : cityTotals.keySet())
        {
            output += String.format("%-28s$%-29.2f\n", key, cityTotals.get(key));
        }
        

        //print headings
//        System.out.printf("\n%-17s%26s\n","Car Model", "Total Premium Payment\n");
//        
//        for (String key : cityTotals.keySet())
//        {
//            System.out.printf("%-28s$%-27.2f\n", key, cityTotals.get(key));
//        }
        return output;
    }
    
    // LAB 6 -----------------------------------
    
    public boolean load(String fileName)
    {
        ObjectInputStream inputStream = null;
        boolean value = false;
        
        try // open file
        {
            inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
        }
        catch (IOException e)
        {
            System.out.println("Error opening file. Terminating.");
            return false;
        }
        try // load file contents into object
        {
            InsuranceCompany insuranceCompany = (InsuranceCompany)inputStream.readObject();
            name = insuranceCompany.name;
            adminUsername = insuranceCompany.adminUsername;
            adminPassword = insuranceCompany.adminPassword;
            flatRate = insuranceCompany.flatRate;
            users = insuranceCompany.users;
        }
        catch (IOException e)
        {
            System.out.println("Error reading from file. Terminating.");
            return false;
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Error, class not found. Terminating.");
            return false;
        }
        try // close file
        {
            inputStream.close();
            value = true;
        }
        catch (IOException e)
        {
            System.out.println("Error closing file. Terminating.");
            return false;
        }    
        return value;
    }
    
    public boolean save(String fileName)
    {
        ObjectOutputStream output = null;
        boolean value = false;
        
        try // open file
        {
            output = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
        }
        catch (IOException e)
        {
            System.out.println("Error opening file. Terminating.");
            return false;
        }
        try // save/write object to file
        {     
            output.writeObject(this);
            value = true;
        }
        catch (IOException e)
        {
            System.out.println("Error in writing to file. Terminating.");
            return false;
        }
        try
        {
            output.close();
        }
        catch (IOException e)
        {
            System.err.println("Error closing file. Terminating.");
            return false;
        }
       return value;
    }
    
    public String toDelimitedString()
    {
        String output = name + "," + adminUsername + "," + adminPassword + "," + flatRate;
        for (User user : users.values())
        {
            output += "\n" + user.toDelimitedString() + ",";
        }
        return output;
    }
    
    // save/write insurance company data to text file
    
    public boolean saveTextFile(String fileName) throws IOException
    {
        BufferedWriter output = new BufferedWriter(new FileWriter(fileName));
        
        output.write(toDelimitedString() + "\n");
        output.close();
        return true;
    }

    // load insurance company data from text file
    
    public boolean loadTextFile(String fileName) throws IOException, PolicyException, NameException
    {
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        
        String line = input.readLine();
        line = line.trim();
        String[] field = line.split(",");
        this.name = field[0];
        this.adminUsername = field[1];
        this.adminPassword = field[2];
        this.flatRate = Integer.parseInt(field[3]);
        
        this.users = new HashMap<>(); // create hash map to store loaded users
        
        line = input.readLine();
        while (line!=null)
        {
            line = line.trim();
            field = line.split(",");
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
            
            users.put(userID, user); // add user to insurance company
            
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
                    
                    user.addPolicy(username, password, new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, date, comments));
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
                    
                    user.addPolicy(username, password, new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, date, driverAge, level));
                    index+=13;
                }      
            }
        line = input.readLine();
        }
        input.close();
        return true;
    }
    
    //***********ASSIGNMENT 2 CODE*********************
    
    // return users by city in Hash Map
    public HashMap<String, ArrayList<User>> getUsersPerCity()
    {
        HashMap<String, ArrayList<User>> output = new HashMap<>();
        
        for (User user : users.values())
        {
            String city = user.getAddress().getCity();
            
            if (!output.containsKey(city))
            {
                output.put(city, new ArrayList<>()); // create new arraylist with city key
                output.get(city).add(user); // add current user to arraylist with city key
            }
            else
            {
                output.get(city).add(user); // find array list with city key and add user
            }
        }
        return output;
    }
    
    // return user name and expired policies in hash map
    public HashMap<String, ArrayList<InsurancePolicy>> filterPoliciesByExpiryDate(String adminName, String adminPassword, MyDate expiryDate)
    {
        HashMap<String, ArrayList<InsurancePolicy>> output = new HashMap<>();
        if (validateAdmin(adminName, adminPassword))
        {
            for (User user : users.values())
            {            
                HashMap<Integer, InsurancePolicy> userPolicies = user.filterByExpiryDate(user.getUsername(), user.getPassword(), expiryDate);
                output.put(user.getName(), new ArrayList<>(userPolicies.values()));
            }
        }
        else
        {
            System.out.println("Invalid Access Details! Operation Terminated.");
        }
        return output;
    }
    
    // proxy call from UI
    public void setUserAddress(User user)
    {
        user.setAddress(user.getUsername(), user.getPassword(), UITools.getAddress());
    }
    
    // proxy call from UI
    public void reportUserPaymentsPerCarModel(User user)
    {
            user.reportPaymentsPerCarModel(user.getUsername(), user.getPassword(), flatRate);
    }
    
    // proxy call from UI
    public boolean saveUserTextFile(String adminName, String adminPassword) throws IOException
    {
        boolean value = false;
        if (validateAdmin(adminName, adminPassword))
        {
            User.saveTextFile(users, "companyUsers.txt");
            value = true;
        }
        else
        {
            System.out.println("Invalid Access Rights!");
        }
        return value;
    }
    
    // proxy call from UI
    public boolean savePoliciesTextFile(String adminName, String adminPassword) throws IOException
    {
        boolean value = false;
        if (validateAdmin(adminName, adminPassword))
        {
            InsurancePolicy.saveTextFile(allPolicies(adminName, adminPassword), "companyPolicies.txt");
            value = true;
        }
        else
        {
            System.out.println("Invalid Access Rights!");
        }
        return value;
    }
    // proxy call from UI
    public void loadUsersFromText(String adminName, String adminPassword) throws IOException, PolicyException, NameException
    {
        if (validateAdmin(adminName, adminPassword))
        {
            users = (User.loadTextFile("companyUsers.txt"));
            System.out.println("The Following Users Loaded Successfully:");
            User.printUsers(users);
        }
        else
        {
            System.out.println("Invalid Access Rights!");
        }
    }
    
    public void loadPoliciesFromText(String adminName, String adminPassword) throws IOException, PolicyException, NameException
    {
        if (validateAdmin(adminName, adminPassword))
        {
            HashMap<Integer, InsurancePolicy> policies = (InsurancePolicy.loadTextFile("companyPolicies.txt"));
            System.out.println("The Following Policies Loaded Successfully:");
            InsurancePolicy.printPolicies(policies); 
        }
        else
        {
            System.out.println("Invalid Access Rights!");
        }
    }
    
    //policy count from given array ranges returns array
//    public int[] policyCount(String adminName, String adminPassword, int[] ranges)
//    {
//        int[] output = new int[ranges.length]; 
//        if (validateAdmin(adminName, adminPassword))
//        {
//            for (User user : users.values())
//            {
//                int[] userPolicy = user.policyCount(user.getUsername(), user.getPassword(), ranges, flatRate);
//                for (int i = 0 ; i < ranges.length ; i++)
//                {   
//                    output[i] += userPolicy[i];
//                }
//            }
//        }
//        return output;
//    }
    
    // users LAMBDA policy count from given array ranges returns array
    public int[] policyCount(String adminName, String adminPassword, int[] ranges)
    {
        int[] output = new int[ranges.length];
        if (validateAdmin(adminName, adminPassword))
        {
                users.values().forEach(x ->{
                        int[] userPolicy = x.policyCount(x.getUsername(), x.getPassword(), ranges, flatRate);
                for (int i = 0 ; i < ranges.length ; i++)
                {   
                    output[i] += userPolicy[i];
                }
                });
        }
        return output;
    }
    
    // proxy call from UI (not yet implemented in UI)
    public int[] userPolicyCount(User user, int[] ranges)
    {
        // calls user method for policy count after validation
        int[] output = user.policyCount(user.getUsername(), user.getPassword(), ranges, flatRate);

        return output;
    }
    
    // policy count by city from array range return HashMap (city, [count])
    public HashMap<String, int[]> policyCityCount(String adminName, String adminPassword, int[] ranges)
    {
        HashMap<String, int[]> output = new HashMap<>();
        if (validateAdmin(adminName, adminPassword))
        {   
            for (User user : users.values())
            {
                String city = user.getAddress().getCity();
                for (InsurancePolicy policy : user.getPolicies(user.getUsername(), user.getPassword()).values())
                {
                    double premium = policy.calcPayment(flatRate);
                    if (output.containsKey(city)) // check if city already exists in HashMap (only update counts)
                    {
                        for (int i = 0 ; i < ranges.length ; i++)
                        {   
                            if(premium <= ranges[i])
                            {
                                output.get(city)[i] += 1; //add one to the correct position in exiting array for city
                                break;
                            }
                        }
                    }
                    else // if city not exist, add city and count to hashmap
                    {
                        output.put(city, new int[ranges.length]); // create new city array entry
                        for (int i = 0 ; i < ranges.length ; i++)
                        {   
                            if(premium <= ranges[i])
                            {
                                output.get(city)[i] += 1; //add one to the correct position in exiting array for city
                                break;
                            }
                        }
                    }
                }
            }
        }
        return output;
    } 
    
    //policy count from given array ranges returns array
    public int[] userCount(String adminName, String adminPassword, int[] ranges)
    {
        int[] output = new int[ranges.length]; 
        if (validateAdmin(adminName, adminPassword))
        {
            for (User user : users.values())
            {
                int[] temp = new int[ranges.length]; //setup temp array to store user count
                for (InsurancePolicy policy : user.getPolicies(user.getUsername(), user.getPassword()).values())
                {
                    double premium = policy.calcPayment(flatRate);
                    // code counts users per range without doubling up
                    for (int i = 0 ; i < ranges.length ; i++)
                    {   
                        if(premium <= ranges[i]) // checks if user already counted
                        {
                            if (temp[i] < 1)
                            {
                                temp[i] = 1;
                                break;
                            }
                            else // if found but value already in output, break (don't look any further)
                            {
                            break; 
                            }
                        }
                    }
                }
                for (int k = 0 ; k < temp.length ; k++) // go through temp array and populate output by adding values
                    {
                        output[k] += temp[k];
                    }
            }
        }
        return output;
    }
    
    // user count by car model in given range returns hashmap <"car model", [user count]>
    public HashMap<String, int[]> userCarModelCount(String adminName, String adminPassword, int[] ranges)
    {
        HashMap<String, int[]> output = new HashMap<>(); // carmodel, user count per range 
        if (validateAdmin(adminName, adminPassword))
        {
            ArrayList<String> cars = populateDistinctCarModels(); // setup hashmap with all distinct car models and empty count arrays
            for (String car : cars)
            {
                output.put(car, new int[ranges.length]);
            }
            // iterate over hash map by car model
            for (String car : output.keySet())
            {
                for (User user : users.values()) // iterate over users
                {
                    int[] temp = new int[ranges.length]; //setup temp array to store user count
                    for (InsurancePolicy policy : user.getPolicies(user.getUsername(), user.getPassword()).values()) //iterate over policies
                    {
                        if (policy.car.getModel().equals(car)) // only count policies with matching car model
                        {
                            double premium = policy.calcPayment(flatRate);
                            // code counts users per range without doubling up
                            for (int i = 0 ; i < ranges.length ; i++)
                            {   
                                if(premium <= ranges[i]) // checks if user already counted
                                {
                                    if (temp[i] <= 1)
                                    {
                                        temp[i] = 1;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    for (int k = 0 ; k < temp.length ; k++) // go through temp array and and to output
                            {
                                output.get(car)[k] += temp[k]; // update the array for the car model in hash map
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
    
    // policy by car model per range returns <"car model", [policy count]>
    public HashMap<String, int[]> policyCarModelCount(String adminName, String adminPassword, int[] ranges)
    {
        HashMap<String, int[]> output = new HashMap<>();
        if (validateAdmin(adminName, adminPassword))
        {
            for (User user : users.values())
            {
                HashMap<String, int[]> userPolicyCarCount = user.policyCarModelCount(user.getUsername(), user.getPassword(), ranges, flatRate);
                for (String car : userPolicyCarCount.keySet())
                {
                    if (output.containsKey(car))
                    {
                        for (int i = 0 ; i < userPolicyCarCount.get(car).length ; i++)
                        {
                            output.get(car)[i] += userPolicyCarCount.get(car)[i];
                        }
                    }
                    else
                    {
                        output.put(car, userPolicyCarCount.get(car));
                    }
                }
            }
        }
        return output;
    }
    
    
}
