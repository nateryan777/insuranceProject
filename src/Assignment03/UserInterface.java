package Assignment03;

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import javax.swing.JFrame;


public class UserInterface extends javax.swing.JFrame
{

    protected static String adminName;
    protected static String adminPassword;
    
    
    //MAIN MENU selection method
    public static void mainMenu(InsuranceCompany insuranceCompany) throws PolicyException, IOException, NameException
    {
        
        Scanner userIn = new Scanner(System.in);
        
        int choice = 0;
        while (choice!=3)
        {    
            displayMainMenu();
            do// check user input is valid
            {
                try
                {
                    choice = Integer.parseInt(userIn.nextLine());
                }
                catch (NumberFormatException e)
                {
                    System.out.print("Invalid Selection!\nPlease select from the above options (1-3): ");
                    choice = 0;
                }
            }
            while(choice==0);
            switch(choice)
            {
                case 1:
                    adminLogin(insuranceCompany);
                    break;
                case 2:
                    userLogin(insuranceCompany);
                    break;
                case 3:
                    System.out.print("Exiting application\n.\n.\n.\n.\n.\n");
                    break;
                default:
                    System.out.print("Invalid selection, please choose an option between 1-3");
            }
        }    
        System.out.println("Application terminated!");
    }
    
    //MAIN MENU---------------------
    public static void displayMainMenu()
    {
        System.out.println("\n\n\n");
        System.out.println("----------Main Menu----------\n");
        System.out.println(" 1) Admin Login");
        System.out.println(" 2) User Login");
        System.out.println(" 3) Quit");
        System.out.print("\nPlease select from the above options (1-3): ");
    }
    
    //Admin login method
    public static void adminLogin(InsuranceCompany insuranceCompany) throws PolicyException, IOException, NameException
    {
        Scanner userIn = new Scanner(System.in);
        
        boolean loggedIn = false;
        while(!loggedIn)
        {
            System.out.println("\n\n\n\n\n---------------Admin Login---------------\n");
            System.out.print("Enter username: ");
            String _username = userIn.nextLine();
            System.out.print("Enter password: ");
            String _password = userIn.nextLine();
            
            if (insuranceCompany.validateAdmin(_username, _password))
            {
                loggedIn = true;
                System.out.println("Login successful");
                adminName = _username;
                adminPassword=_password;
            }
            else
            {
                System.out.println("Login failed, incorrect user name or password!");
                pressEnterKey();
                mainMenu(insuranceCompany);
            }
        }
        adminMenu(insuranceCompany); //call method if successful login
    }
    
    //ADMIN MAIN MENU---------------------------
    public static void displayAdminMenu()
    {
        System.out.println("\n\n\n");
        System.out.println("---------------Admin Menu---------------\n");
        System.out.println(" 1) Test Code");
        System.out.println(" 2) Create User");
        System.out.println(" 3) Create ThirdParty Policy");
        System.out.println(" 4) Create Comprehensive Policy");
        System.out.println(" 5) Print User Information");
        System.out.println(" 6) Filter By Car Model");
        System.out.println(" 7) Filter By Expiry Date");
        System.out.println(" 8) Update Address");
        System.out.println(" 9) Remove User Policy");
        System.out.println("10) Remove User");
        System.out.println("11) Change Password");
        System.out.println("12) Reporting - Total Payments By City");
        System.out.println("13) Reporting - Total Payments By Car Model");
//        System.out.println("14) Save - Users to Binary File");
//        System.out.println("15) Save - Policies to Binary File");
//        System.out.println("16) Load - Users from Binary File");
//        System.out.println("17) Load - Policies from Binary File");
        System.out.println("14) Save - Users to Text File");
        System.out.println("15) Save - Policies to Text File");
        System.out.println("16) Load - Users from Text File");
        System.out.println("17) Load - Policies from Text File");
//        System.out.println("22) Save - Company to Binary File");
        System.out.println("18) Save - Company to Text File");
//        System.out.println("24) Load - Company from Binary File");
        System.out.println("19) Load - Company from Text File");
        System.out.println("20) Log Out");
        System.out.print("\nPlease select from the above options (1-20): ");
    }
    
    //ADMIN MAIN MENU selection method
    public static void adminMenu(InsuranceCompany insuranceCompany) throws PolicyException, IOException, NameException
    {
        Scanner userIn = new Scanner(System.in);
        
        int choice = 0;
        while (choice!=20)
        {
            displayAdminMenu();
            do // check user input is valid
            {
                try
                {
                    choice = Integer.parseInt(userIn.nextLine());
                }
                catch (NumberFormatException e)
                {
                    System.out.print("Invalid Selection!\nPlease select from the above options (1-26): ");
                    choice = 0;
                }
            }
            while(choice==0);
            switch(choice)
            {
                case 1:
                    runTestCase();
                    break;
                case 2:
                    createUser(insuranceCompany);
                    break;
                case 3:
                    createThirdPartyPolicy(insuranceCompany);
                    break;
                case 4:
                    createComprehensivePolicy(insuranceCompany);
                    break;
                case 5:
                    printUserInformation(insuranceCompany);
                    break;
                case 6:
                    filterByCarModel(insuranceCompany);
                    break;
                case 7:
                    filterByExpiryDate(insuranceCompany);
                    break;
                case 8:
                    updateAddress(insuranceCompany);
                    break;
                case 9:
                    removeUserPolicy(insuranceCompany);
                    break;
                case 10:
                    removeUser(insuranceCompany);
                    break;
                case 11:
                    setAdminPassword(insuranceCompany);
                    break;
                case 12:
                    paymentsPerCity(insuranceCompany);
                    break;
                case 13:
                    paymentsPerCarModel(insuranceCompany);
                    break;
//                case 14:
//                    saveUsersToBinary(insuranceCompany);
//                    break;
//                case 15:
//                    savePoliciesToBinary(insuranceCompany);
//                    break;
//                case 16:
//                    loadUsersFromBinary(insuranceCompany);
//                    break;
//                case 17:
//                    loadPoliciesFromBinary(insuranceCompany);
//                    break;
                case 14:
                    saveUsersToText(insuranceCompany);
                    break;
                case 15:
                    savePoliciesToText(insuranceCompany);
                    break;
                case 16:
                    loadUsersFromText(insuranceCompany);
                    break;
                case 17:
                    loadPoliciesFromText(insuranceCompany);
                    break;
//                case 22:
//                    saveCompanyToBinary(insuranceCompany);
//                    break;
                case 18:
                    saveCompanyToText(insuranceCompany);
                    break;
//                case 24:
//                    loadCompanyFromBinary(insuranceCompany);
//                    break;
                case 19:
                    loadCompanyFromText(insuranceCompany);
                    break;
                case 20:
                    System.out.println("\nLogging out and returning to Main Menu...\n");
                    break;
                default:
                    System.out.println("Invalid selection, please choose an option between 1-20\n");
            }
            pressEnterKey();
        }
    }
    
    //USER LOGIN method-------------------------
    public static void userLogin(InsuranceCompany insuranceCompany) throws PolicyException, IOException, NameException
    {
        Scanner userIn = new Scanner(System.in);
        
        User user=null;
        String username;
        String password;
        boolean loggedIn = false;
        while(!loggedIn)
        {
            System.out.println("\n\n\n\n\n---------------User Login---------------\n");
            System.out.print("Enter Username: ");

            username = userIn.nextLine();
            userIn.nextLine();

            System.out.print("Enter password: ");
            password = userIn.nextLine();
            
            user = insuranceCompany.validateUser(username, password);
            if (user!=null)
            {
                loggedIn = true;
                System.out.println("Login successful");
            }
            else
            {
                System.out.println("Login failed, incorrect user name or password!");
                pressEnterKey();
                mainMenu(insuranceCompany);
            }  
        }
        userMenu(insuranceCompany, user);        
    }
    
    //USER MAIN MENU selection method
    public static void userMenu(InsuranceCompany insuranceCompany, User user) throws PolicyException, NameException
    {
        Scanner userIn = new Scanner(System.in);
        
        int choice = 0;
        while(choice!=8)
        {
            displayUserMenu();
            do
            {
                try
                {
                    choice = Integer.parseInt(userIn.nextLine());
                }
                catch (NumberFormatException e)
                {
                    System.out.print("Invalid Selection!\nPlease select from the above options (1-8): ");
                    choice = 0;
                }
            }
            while(choice==0); 
            switch(choice)
            {
                case 1:
                    printAllUserPolicies(user, insuranceCompany);
                    break;
                case 2:
                    createThirdPartyPolicy(user, insuranceCompany);
                    break;
                case 3:
                    createComprehensivePolicy(user, insuranceCompany);
                    break;
                case 4:
                    printUserInformation(user, insuranceCompany);
                    break;
                case 5:
                    updateAddress(user, insuranceCompany);
                    break;
                case 6:
                    removePolicy(user, insuranceCompany);
                    break;
                case 7:
                    totalPaymentsByCarModel(user, insuranceCompany);
                    break;
                case 8:
                    System.out.println("\nLogging out and returning to Main Menu...\n");
                    break;
                default:
                    System.out.println("Invalid selection, please choose an option between 1-8\n");
            }
            pressEnterKey();
        }
    }
    
    //USER MAIN MENU
    public static void displayUserMenu()
    {
        System.out.println("\n\n\n");
        System.out.println("---------------User Menu---------------\n");
        System.out.println(" 1) Print Statement Of All Policies");
        System.out.println(" 2) Create ThirdParty Policy");
        System.out.println(" 3) Create Comprehensive Policy");
        System.out.println(" 4) Print User Information");
        System.out.println(" 5) Update Address");
        System.out.println(" 6) Remove Policy");
        System.out.println(" 7) Reporting - Total Payments By Car Model");
        System.out.println(" 8) Log Out");
        System.out.print("\nPlease select from the above options (1-8): ");
    }
   
    // pause and wait for enter key method
    public static void pressEnterKey()
    {
        Scanner userIn = new Scanner(System.in);
        System.out.print("\nPress enter to continue...");
        userIn.nextLine();
    }
    
    //run the test code file
    
    public static void runTestCase() throws PolicyException, IOException, NameException
    {
        System.out.println("\n--------------------Run System Test Code--------------------\n\n");        
        TestCase.testCase();
    }
    
    //admin create user
    public static void createUser(InsuranceCompany insuranceCompany)
    {
        UITools.addUser(adminName, adminPassword, insuranceCompany);
    }
    
    //admin create third party policy
    public static void createThirdPartyPolicy(InsuranceCompany insuranceCompany) throws PolicyException, NameException
    {
        
        System.out.println("\n---------Create Third Party Policy---------");
        
        int _userID = UITools.getUserID();
        
        User user = insuranceCompany.findUser(adminName, adminPassword, _userID); //first check user exists, if true continue to create policy
        if (user!=null)
        {
            try
            {    
                insuranceCompany.addPolicy(adminName, adminPassword, _userID, UITools.getThirdPartyPolicy());    
            }
            catch (NameException e)
            {
                System.out.println("Invalid Policy Holder Name Format. Operation Terminated.");
            }
        }
        else
        {
            System.out.println("Operation Failed! User not found.");
        }
    }
    
    //admin create comprehensive policy
    public static void createComprehensivePolicy(InsuranceCompany insuranceCompany) throws PolicyException, NameException
    {
        
        System.out.println("\n---------Create Comprehensive Policy---------");
        
        //get user ID and save to variable
        int _userID = UITools.getUserID();
        
        User user = insuranceCompany.findUser(adminName, adminPassword, _userID); //check user exists, else abort operation
        if (user!=null)
        {
            try
            {    
                insuranceCompany.addPolicy(adminName, adminPassword, _userID, UITools.getComprehensivePolicy());    
            }
            catch (NameException e)
            {
                System.out.println("Invalid Policy Holder Name Format. Operation Terminated.");
            }
        }
        else
        {
            System.out.println("\nOperation Failed! User not found.");
        }
    }
    
    //admin print user info
    public static void printUserInformation(InsuranceCompany insuranceCompany)
    {
        System.out.println("\n----------Print User Information----------\n");
        // get user ID with exception handling
        int _userID = UITools.getUserID();
        
        User user = insuranceCompany.findUser(adminName, adminPassword, _userID); //check user exists
        if (user!=null)
        {        
            insuranceCompany.printUserInfo(adminName, adminPassword, user);
        }
        else
        {
            System.out.println("\nOperation Failed. User not found.");
        }
    }
    
    //admin filter polices by car model
    public static void filterByCarModel(InsuranceCompany insuranceCompany)
    {
        Scanner userIn = new Scanner(System.in);
        
        System.out.println("\n----------Filter Polices By Car Model----------");
        System.out.print("Enter Car Model: ");
        String model = userIn.nextLine();
        //filter policies and then print returned array list
//        ArrayList<InsurancePolicy> filteredPolicies = insuranceCompany.filterByCarModel(model);
        //HAH MAP CODE OF THE ABOVE ARRAY LIST CODE
        HashMap<Integer, InsurancePolicy> filteredPolicies = insuranceCompany.filterByCarModel(adminName, adminPassword, model);
        insuranceCompany.printPolicies(adminName, adminPassword, filteredPolicies);
        //calculate total payment of all policies and then print amount
        double totalPayment = insuranceCompany.calcTotalPayments(adminName, adminPassword, filteredPolicies, insuranceCompany.getFlatRate());
        System.out.println("----------------------------------------");
        System.out.printf("Total Premium Payment: $%.2f", totalPayment);
    }
    
    //admin filter policies by expiry date
    public static void filterByExpiryDate(InsuranceCompany insuranceCompany)
    {
        System.out.println("\n----------Filter Polices By Expiry Date----------");
        // get user ID with exception handling from user
        int _userID = UITools.getUserID();
        
        if (insuranceCompany.findUser(adminName, adminPassword, _userID)!=null) //check if user exits, else abort operation
        {
            //get expiry date details and return expiry date object
            MyDate date = UITools.getExpiryDate();

            //filter policies to new array list and print out user details and filtered policies
//            ArrayList<InsurancePolicy> filteredPolicies = insuranceCompany.filterByExpiryDate(userID, date);
            //HASH MAP CODE OF THE ABOVE ARRAY LIST CODE
            HashMap<Integer, InsurancePolicy> filteredPolicies = insuranceCompany.filterByExpiryDate(adminName, adminPassword, _userID, date);
            System.out.printf("\nSearch results for User: %s\nPolicies expiring before: %s\n\n", insuranceCompany.findUser(adminName, adminPassword, _userID).getName(), date);
            InsurancePolicy.printPolicies(filteredPolicies);
        }
        else
        {
            System.out.println("Operation Failed! User not found.");
        }
    }
    
    //admin update user address information
    public static void updateAddress(InsuranceCompany insuranceCompany)
    {
        
        System.out.println("\n----------Update Address----------");
        // get user ID with exception handling
        int _userID = UITools.getUserID();
        
        User user = insuranceCompany.findUser(adminName, adminPassword, _userID); // check user exists, else abort operation
        if (user!=null)
        {
            //get address details and return address object
            insuranceCompany.setUserAddress(user);
            System.out.println("\nNew address updated to: ");
            System.out.println(insuranceCompany.findUser(adminName, adminPassword, _userID).getAddress());
        }
        else
        {
            System.out.println("\nOperation Failed! User not found!");
        }
    }
    //user all policies with payments and total payments
    public static void printAllUserPolicies(User user, InsuranceCompany insuranceCompany)
    {
        System.out.println("\n----------Print Statement Of All Policies----------\n");
        insuranceCompany.printPolicies(adminName, adminPassword, user);
    }

    // admin generates payments per cities report
    public static void paymentsPerCity(InsuranceCompany insuranceCompany)
    {
        System.out.println("\n----------Reporting: Total Premium Payments Per City----------\n");
        
        ArrayList<String> cities = insuranceCompany.populateDistinctCityNames(adminName, adminPassword); //get list of cities
        ArrayList<Double> payments = insuranceCompany.getTotalPaymentPerCity(adminName, adminPassword, cities); //get payments per city

        insuranceCompany.reportPaymentPerCity(adminName, adminPassword, cities, payments); //display report
        System.out.println("\n-----------------------------------End Report-----------------------------------\n");

    }
        
    // USER create third party policy
    public static void createThirdPartyPolicy(User user, InsuranceCompany insuranceCompany) throws PolicyException, NameException
    {
        try
        {    
            insuranceCompany.addPolicy(user, UITools.getThirdPartyPolicy()); //use UI Tools to create third party policy    
        }
        catch (NameException e)
        {
            System.out.println("Invalid Policy Holder Name Format. Operation Terminated.");
        }
    }
    
    // USER create comprehensive policy
    public static void createComprehensivePolicy(User user, InsuranceCompany insuranceCompany) throws PolicyException, NameException
    {   
        try
        {    
            insuranceCompany.addPolicy(user, UITools.getComprehensivePolicy()); //use UI Tools to create comprehansive policy    
        }
        catch (NameException e)
        {
            System.out.println("Invalid Policy Holder Name Format. Operation Terminated.");
        }
    }
    
    //USER print info
    public static void printUserInformation(User user, InsuranceCompany insuranceCompany)
    {
        System.out.println("\n----------Print User Information----------\n");
        insuranceCompany.printUserInfo(user);
    }
    
    //USER update address
    public static void updateAddress(User user, InsuranceCompany insuranceCompany)
    {        
        System.out.println("\n----------Update Address----------");
        
        insuranceCompany.setUserAddress(user);
        System.out.println("\nNew address updated to: ");
        System.out.println(user.getAddress());
        
    }
    
    //admin remove user policy method call
    public static void removeUserPolicy(InsuranceCompany insuranceCompany)
    {
        
        System.out.println("\n----------Remove User Policy----------");
        
        int _userID = UITools.getUserID(); //get user ID with exception handling
        
        User user = insuranceCompany.findUser(adminName, adminPassword, _userID); //check user exists, else abort operation
        if (user!=null)
        {
            UITools.removePolicy(adminName, adminPassword, insuranceCompany, _userID); // use UI Tool to remove policy with exception handling
        }
        else
        {
            System.out.println("\nOperation Failed! User not found.");
        }
    }
    
    //admin remove user method call
    public static void removeUser(InsuranceCompany insuranceCompany)
    {
        System.out.println("\n----------Remove User----------");
        //get user ID with exception handling
        int _userID = UITools.getUserID();
        
        User user = insuranceCompany.findUser(adminName, adminPassword, _userID); //check user exists
        if (user!=null)
        {
            insuranceCompany.removeUser(adminName, adminPassword, _userID);
            System.out.println("\nSuccessfully removed user!");
        }
        else
        {
            System.out.println("\nOperation Failed! User not found.");
        }
    }
    
    //admin set password
    public static void setAdminPassword(InsuranceCompany insuranceCompany)
    {
        Scanner userIn = new Scanner(System.in);
        
        System.out.println("\n----------Set Admin Password----------");
        //get new password
        System.out.print("Enter New Admin Password: ");
        String newPassword = userIn.nextLine();
        
        //call set password method
        insuranceCompany.setAdminPassword(adminName, adminPassword, newPassword);
        
        System.out.println("\nPassword successfully updated!");
    }
    
    //user remove policy method
    public static void removePolicy(User user, InsuranceCompany insuranceCompany)
    {
        System.out.println("\n----------Remove Policy----------");
        
        int policyID = UITools.getPolicyID(); // get policy ID with exception handling
        
        insuranceCompany.removePolicy(user, policyID);
    }
    
    // admin generates report for total premium payments by car model
    public static void paymentsPerCarModel(InsuranceCompany insuranceCompany)
    {
        System.out.println("\n----------Reporting: Total Premium Payments By Car Model----------\n");
        
//        ArrayList<String> models = insuranceCompany.populateDistinctCarModels(); //list of models
//        ArrayList<Integer> count = insuranceCompany.getTotalCountPerCarModel(models); //count of models list
//        ArrayList<Double> payments = insuranceCompany.getTotalPaymentPerCarModel(models, insuranceCompany.getFlatRate()); //list of payments per model
//        
//        insuranceCompany.reportPaymentsPerCarModel(models, count, payments); //display report
        
        // HASH MAP METHOD - report total payments by car model
        insuranceCompany.reportPaymentsPerCarModel(adminName, adminPassword);
            
        System.out.println("\n-----------------------------------End Report-----------------------------------\n");
        
    }
    
    //user generates report of premium payments by car model
    public static void totalPaymentsByCarModel(User user, InsuranceCompany insuranceCompany)
    {
        System.out.println("\n----------Reporting: Total Premium Payments By Car Model----------\n");
//        
//        ArrayList<String> models = user.populateDistinctCarModels(); //list of distinct models
//        ArrayList<Integer> count = user.getTotalCountPerCarModel(models); //list of counts for models
//        ArrayList<Double> payments = user.getTotalPaymentPerCarModel(models, insuranceCompany.getFlatRate()); //total payments per car model
//        
//        user.reportPaymentsPerCarModel(models, count, payments); //display report

        // HASH MAP METHOD - report total payments by car model
        insuranceCompany.reportUserPaymentsPerCarModel(user);
        
        System.out.println("\n-----------------------------------End Report-----------------------------------\n");
    }
    
    //save company users to binary file
//    public static void saveUsersToBinary(InsuranceCompany insuranceCompany) throws IOException
//    {
//        if (User.save(insuranceCompany.getUsers(), "companyUsers.ser"))
//        {
//            System.out.println("Company Users Exported Successfully.");
//        }
//        else
//        {
//            System.out.println("Operation failed. Terminating.");
//        }
//    }
    
    //save company policies to binary file
//    public static void savePoliciesToBinary(InsuranceCompany insuranceCompany) throws IOException
//    {
//        if (InsurancePolicy.save(insuranceCompany.allPolicies(adminName, adminPassword), "companyPolicies.ser"))
//        {
//            System.out.println("Company Polices Exported Successfully.");
//        }
//        else
//        {
//            System.out.println("Operation failed. Terminating.");
//        }
//    }
    
    //load company users from binary file
//    public static void loadUsersFromBinary(InsuranceCompany insuranceCompany) throws IOException
//    {
//        HashMap<Integer, User> users = (User.load("companyUsers.ser"));
//        System.out.println("The Following Users Loaded Successfully:");
//        User.printUsers(users); 
//    }
    
    //load company polices from binary file
//    public static void loadPoliciesFromBinary(InsuranceCompany insuranceCompany) throws IOException
//    {
//        HashMap<Integer, InsurancePolicy> policies = (InsurancePolicy.load("companyPolicies.ser"));
//        System.out.println("The Following Policies Loaded Successfully:");
//        InsurancePolicy.printPolicies(policies);
//    }
    
    //save company users to text file
    public static void saveUsersToText(InsuranceCompany insuranceCompany) throws IOException
    {
        if (insuranceCompany.saveUserTextFile(adminName, adminPassword))
        {
            System.out.println("Company Users Exported Successfully.");
        }
        else
        {
            System.out.println("Operation failed. Terminating.");
        }
    }
    
    //save company policies to text file
    public static void savePoliciesToText(InsuranceCompany insuranceCompany) throws IOException
    {
        if (insuranceCompany.savePoliciesTextFile(adminName, adminPassword))
        {
            System.out.println("Company Polices Exported Successfully.");
        }
        else
        {
            System.out.println("Operation failed. Terminating.");
        }
    }
    
    //load company users from text file
    public static void loadUsersFromText(InsuranceCompany insuranceCompany) throws IOException, PolicyException, NameException
    {
        insuranceCompany.loadUsersFromText(adminName, adminPassword);
    }
    
    //load company policies from text file
    public static void loadPoliciesFromText(InsuranceCompany insuranceCompany) throws IOException, PolicyException, NameException
    {
        insuranceCompany.loadPoliciesFromText(adminName, adminPassword);
    }
    
    //save company to binary file
//    public static void saveCompanyToBinary(InsuranceCompany insuranceCompany) throws IOException
//    {
//        if (insuranceCompany.save("insuranceCompany.ser"))
//        {
//            System.out.println("Company Exported Successfully.");
//        }
//        else
//        {
//            System.out.println("Operation failed. Terminating.");
//        }
//    }
    //save company to text file
    public static void saveCompanyToText(InsuranceCompany insuranceCompany) throws IOException
    {
        if (insuranceCompany.saveTextFile("insuranceCompany.txt"))
        {
            System.out.println("Company Exported Successfully.");
        }
        else
        {
            System.out.println("Operation failed. Terminating.");
        }
    }
    
    //load company from binary file
//    public static void loadCompanyFromBinary(InsuranceCompany insuranceCompany) throws IOException, PolicyException, NameException
//    {
//        InsuranceCompany insuranceCompany1 = new InsuranceCompany();
//        insuranceCompany1.load("insuranceCompany.ser");
//        System.out.println("The Following Company Loaded Successfully:");
//        System.out.println(insuranceCompany1); 
//    }
    
    // load company from text file
    public static void loadCompanyFromText(InsuranceCompany insuranceCompany) throws IOException, PolicyException, NameException
    {
        InsuranceCompany insuranceCompany1 = new InsuranceCompany();
        insuranceCompany1.loadTextFile("insuranceCompany.txt");
        System.out.println("The Following Company Loaded Successfully:");
        System.out.println(insuranceCompany1); 
    }
}
