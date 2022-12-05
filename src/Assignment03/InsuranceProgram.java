package Assignment03;

import java.io.IOException;

public class InsuranceProgram
{
//    private static int flatRate;
    
    public static void main(String[] args) throws PolicyException, IOException, NameException
    {
        //create insurance company object
        InsuranceCompany youDingInsure = new InsuranceCompany("YouDing Insurance Pty Ltd", "Sal Scover", "insure1", 100);
             
//        UserInterface.mainMenu(youDingInsure);
        
        //uncomment below TestCase.testCase() and comment out the above  UserInterface.mainMenu(youDingInsure) to just run test case (without using interface)
        
//        TestCase.testCase();

        A2TestCase.a2TestCase();


//         CODE Moved to class TestCase
        
//        Scanner userIn = new Scanner(System.in);
//    
//        //create insurance company object
//        InsuranceCompany youDingInsure = new InsuranceCompany("YouDing Insurance Pty Ltd", "Sal Scover", "insure1", flatRate);
//        
//        //login to insurance company with user prompts
//        System.out.printf("----------%s Login----------\n", youDingInsure.getName());
//        System.out.print("Please enter user name: ");
//        String _userName = userIn.nextLine();
//        System.out.print("Please enter password: ");
//        String _password = userIn.nextLine();
//        System.out.println(); //line spacing
        
        


        //get user input for flat rate
//        System.out.print("Please enter flat rate: ");
//        flatRate = Integer.parseInt(userIn.nextLine());
//        System.out.println();//line spacing
        
        //TEST CODE Assignment 1 ----------------------------------------------------
        
        // CODE TEST set flat rate
//        int flatRate = 100;
//        
//        // create required objects for TEST CODE------------------------------------
//                
//        //create car objects, 1 per third party and comprehensive policy combination
//        
//        Car carSuv1 = new Car(CarType.SUV, "Subaru Outback", 2015, 32000.00);
//        Car carSuv2 = new Car(CarType.SUV, "Subaru Outback", 2015, 32000.00);
//        Car carSed1 = new Car(CarType.SED, "Holden ClubSport", 2020, 36500.00);
//        Car carSed2 = new Car(CarType.SED, "Holden ClubSport", 2020, 36500.00);
//        Car carLux1 = new Car(CarType.LUX, "BMW M5", 2021, 76000.00);
//        Car carLux2 = new Car(CarType.LUX, "BMW M5", 2021, 76000.00);
//        Car carHatch1 = new Car(CarType.HATCH, "Toyota Yaris", 2012, 24499.00);
//        Car carHatch2 = new Car(CarType.HATCH, "Toyota Yaris", 2012, 24499.00);
//        Car carWagon1 = new Car(CarType.WAGON, "Hyundai i30", 2019, 28990.00);
//        Car carWagon2 = new Car(CarType.WAGON, "Hyundai i30", 2019, 28990.00);
//        Car car4WD1 = new Car(CarType.FOURWD, "Nissan Patrol", 2021, 78990.00);
//        Car car4WD2 = new Car(CarType.FOURWD, "Nissan Patrol", 2021, 78990.00);
//        Car carCoupe1 = new Car(CarType.COUPE, "Mercedes CLS", 2016, 164990.00);
//        Car carCoupe2 = new Car(CarType.COUPE, "Mercedes CLS", 2016, 164990.00);
//        Car carUte1 = new Car(CarType.UTE, "Mazda BT-50", 2016, 50990.00);
//        Car carUte2 = new Car(CarType.UTE, "Mazda BT-50", 2016, 50990.00);
//        
//        // additional car for policy holder and car change
//        Car carSed3 = new Car(CarType.SED, "Toyota Camry", 2018, 34990.00);
//        
//        //create expiry date objects for policies
//        
//        MyDate date1 = new MyDate(2023, 5, 30);
//        MyDate date2 = new MyDate(2023, 3, 4);
//        MyDate date3 = new MyDate(2023, 10, 15);
//        MyDate date4 = new MyDate(2023, 9, 22);
//        MyDate date5 = new MyDate(2023, 2, 6);
//        MyDate date6 = new MyDate(2023, 12, 28);
//        MyDate date7 = new MyDate(2023, 7, 19);
//        MyDate date8 = new MyDate(2023, 5, 2);
//        
//        //create third party insurance policy objects
//        
//        ThirdPartyPolicy thirdParty1 = new ThirdPartyPolicy("Ivana Steergood", 3001000, carSuv1, 1, date1, "Returning customer");   
//        ThirdPartyPolicy thirdParty2 = new ThirdPartyPolicy("Anita Drivebetter", 3230000, carSed1, 0, date2, "Valued customer");   
//        ThirdPartyPolicy thirdParty3 = new ThirdPartyPolicy("Joey Bloggs", 3005489, carWagon1, 1, date3, "Multiple policy holder");
//        ThirdPartyPolicy thirdParty4 = new ThirdPartyPolicy("Tim Glasgow", 3487698, carUte1, 1, date7, "Multiple policy holder");
//        ThirdPartyPolicy thirdParty5 = new ThirdPartyPolicy("Ivana Steergood", 9, carHatch1, 0, date4, "Maximum no claim bonus");
//        ThirdPartyPolicy thirdParty6 = new ThirdPartyPolicy("Anita Drivebetter", 11, carLux1, 3, date5, "Multiple policy holder");
//        ThirdPartyPolicy thirdParty7 = new ThirdPartyPolicy("Joey Bloggs", 13, car4WD1, 1, date7, "10 year loyalty discount");
//        ThirdPartyPolicy thirdParty8 = new ThirdPartyPolicy("Tim Glasgow", 15, carCoupe1, 2, date8, "Multiple policy holder");
//        
//        //create comprehensive insurance policy objects
//        ComprehensivePolicy compPolicy1 = new ComprehensivePolicy("Ivana Steergood", 3265987, carSuv2, 0, date4, 38, 2);
//        ComprehensivePolicy compPolicy2 = new ComprehensivePolicy("Anita Drivebetter", 3475112, carSed2, 3, date5, 29, 1);
//        ComprehensivePolicy compPolicy3 = new ComprehensivePolicy("Joey Bloggs", 3330115, carWagon2, 4, date6, 31, 3);
//        ComprehensivePolicy compPolicy4 = new ComprehensivePolicy("Tim Glasgow", 3447715, carUte2, 2, date8, 26, 1);
//        ComprehensivePolicy compPolicy5 = new ComprehensivePolicy("Ivana Steergood", 10, carHatch2, 3, date2, 38, 2);
//        ComprehensivePolicy compPolicy6 = new ComprehensivePolicy("Anita Drivebetter", 12, carLux2, 2, date6, 29, 1);
//        ComprehensivePolicy compPolicy7 = new ComprehensivePolicy("Joey Bloggs", 14, car4WD2, 0, date1, 31, 3);
//        ComprehensivePolicy compPolicy8 = new ComprehensivePolicy("Tim Glasgow", 16, carCoupe2, 0, date1, 26, 2);
//        
//        //extra policy for testing addPolicy method
//        ComprehensivePolicy compPolicy9 = new ComprehensivePolicy("Test Policy", 17, carSed1, 0, date1, 26, 2);
//
//
//        //create user objects that includes addresses
//        User ivanaS = new User("Ivana Steergood", 15, "Runagutter Lane", "Sutherland", "Sydney", "password");
//        User anitaD = new User("Anita Drivebetter", 48, "Crasheeps Avenue", "Corrimal", "Wollongong", "password");
//        User joeyB = new User("Joey Bloggs", 6, "Vague Street", "Janali", "Sydney", "password");
//        User timG = new User("Tim Glasgow", 21, "Ninth Avenue", "Loftus", "Sydney", "password");
//        
//        //create insurance company object
//        InsuranceCompany youDingInsure = new InsuranceCompany("YouDing Insurance Pty Ltd", "Sal Scover", "insure1", flatRate);
//
//        // ----------------------------------------------TEST CODE--------------------------------------------------
//        
//        //assign policies to user
//        ivanaS.addPolicy(thirdParty1);
//        ivanaS.addPolicy(thirdParty5);
////        ivanaS.addPolicy(thirdParty3);
//        ivanaS.addPolicy(compPolicy5);
//        ivanaS.addPolicy(compPolicy1);
////        ivanaS.addPolicy(compPolicy3);
////        ivanaS.addPolicy(thirdParty4);
////        ivanaS.addPolicy(compPolicy4);
//        
//        ivanaS.print();
//        System.out.println();//line spacing
//        
//        //find insurance policy and assign to newPolicy variable
//        
//        // FAILED ATTEMPT - Policy does not exist
//        InsurancePolicy newPolicy = ivanaS.findPolicy(7);
//        if (newPolicy==null)
//        {
//            System.out.println("Policy has not been found");
//            System.out.println();//line spacing
//        }
//        else
//        {
//        newPolicy.print();
//        
//        // increase car price by 10%
//        newPolicy.carPriceRise(0.1);
//        
//        newPolicy.print();
//        
//        //set new name and car on policy
//        
//        // change policy holder name
//        newPolicy.policyHolderName = "Robert Steergood";
//        
//        //modified the method to properly set the car object in policy
//        newPolicy.car = carSed3; 
//        
//        System.out.println();//line spacing
//        }
//
//        // SUCCESSFUL ATTEMPT - Policy exists
//        newPolicy = ivanaS.findPolicy(2);
//        if (newPolicy==null)
//        {
//            System.out.println("Policy has not been found");
//            System.out.println();//line spacing
//        }
//        else
//        {
//        newPolicy.print();
//        
//        // increase car price by 10%
//        newPolicy.carPriceRise(0.1);
//        
//        newPolicy.print();
//        
//        //set new name and car on policy
//        
//        // change policy holder name
//        newPolicy.policyHolderName = "Robert Steergood";
//        
//        //modified the method to properly set the car object in policy
//        newPolicy.car = carSed3; 
//        
//        System.out.println();//line spacing
//        }
//        
//        ivanaS.setCity("Wollongong");
//        ivanaS.print();
//        System.out.println();//line spacing
//        
//        //get new address details from user input and store using setters
////        System.out.print("Please enter new address details: \nStreet number: ");
////        ivanaS.setStreetNum(Integer.parseInt(userIn.nextLine()));
////        System.out.print("Street name: ");
////        ivanaS.setStreet(userIn.nextLine());
////        System.out.print("Suburb name: ");
////        ivanaS.setSuburb(userIn.nextLine());
////        System.out.print("City name: ");
////        ivanaS.setCity(userIn.nextLine());
//        
//        // TEST CODE - set new address
//           
//        ivanaS.setAddress(34, "Jaba Road", "Gymea", "Sydney");
//        
//        //print user details
//        ivanaS.print();
//        System.out.println();//line spacing
//        
//        //print the total premium payments
//        ivanaS.printPolicies(flatRate);
//        System.out.println();//line spacing
//        
//        ivanaS.carPriceRiseAll(0.1);
//        ivanaS.printPolicies(flatRate);
//        System.out.println();//line spacing
//        
////        //get car model from user input
////        System.out.print("Please enter car model to filter policies: ");
////        String _carModel = userIn.nextLine();
//        
//        // TEST CODE - filterByCarModel (set car model internally)
//        String _carModel = "Toyota Yaris";
//
//        //filter policies by car model and print InsurancePolicy.printPolicies()
//        ArrayList<InsurancePolicy> filteredPolicies = ivanaS.filterByCarModel(_carModel);
//        InsurancePolicy.printPolicies(filteredPolicies); //print filitered policies from array
//        
//
//        //login to insurance company with user prompts
////        System.out.printf("----------%s Login----------\n", youDingInsure.getName());
////        System.out.print("Please enter user name: ");
////        String _userName = userIn.nextLine();
////        System.out.print("Please enter password: ");
////        String _password = userIn.nextLine();
////        System.out.println(); //line spacing
//
//        //----------------------------------------LOGIN TEST Insurance Company------------------------------------------
//        
//        
//        
//        // TEST succesful login
//        _userName = "Tim Glasgow";
//        _password = "insure2";
//        
//        //validate user details and print appropriate message
//        if(youDingInsure.validateAdmin(_userName, _password))
//        {
//            System.out.println("Login successful!"); // if method call true
//        }
//        else
//        {
//            System.out.println("Login failed, incorrect user name or password!"); // if method call false
//        }
//        System.out.print("-------------------------------------------\n");
//        
//        
//        //add user to insurance company with prompts using first method
//        if(youDingInsure.addUser(anitaD))
//        {
//            System.out.println("Successfully added user!");
//        }
//        else
//        {
//            System.out.println("Failed to add user, please check user details!");
//        }
        
//        //add user to insurance company with prompts using first method
//        if(youDingInsure.addUser(joeyB))
//        {
//            System.out.println("Successfully added user!");
//        }
//        else
//        {
//            System.out.println("Failed to add user, please check user details!");
//        }
//        
//        // add user to insurance company with prompts using overloaded method
//        if(youDingInsure.addUser("Ivana Steergood", 1001, ivanaS.getAddress()))
//        {
//            System.out.println("Successfully added user!");
//        }
//        else
//        {
//            System.out.println("Failed to add user, please check user details!");
//        }
//        
//        // add user to insurance company with prompts overloaded method
//        if(youDingInsure.addUser("Tim Glasgow", 1004, timG.getAddress()))
//        {
//            System.out.println("Successfully added user!");
//        }
//        else
//        {
//            System.out.println("Failed to add user, please check user details!");
//        }
//        
//        // add user to insurance company with prompts using overloaded method (duplicate user - expected fail)
//        if(youDingInsure.addUser("Joey Bloggs", 1003, joeyB.getAddress()))
//        {
//            System.out.println("Successfully added user!");
//        }
//        else
//        {
//            System.out.println("Failed to add user, please check user details!");
//        }
//        
//        // add user to insurance company with prompts using first method (duplicate user - expected fail)
//        if(youDingInsure.addUser(ivanaS))
//        {
//            System.out.println("Successfully added user!");
//        }
//        else
//        {
//            System.out.println("Failed to add user, please check user details!");
//        }
//        System.out.println(); //line spacing
//        
//        System.out.print(youDingInsure);
//        
//        // add policies to users with InsuranceCompany.addPolicy() method , print corresponding task validation messages
//        
//        youDingInsure.addPolicy(anitaD.getUserID(), thirdParty1);
//        youDingInsure.addPolicy(anitaD.getUserID(), thirdParty2);
//        youDingInsure.addPolicy(joeyB.getUserID(), thirdParty3);
//        youDingInsure.addPolicy(joeyB.getUserID(), thirdParty4);
//        youDingInsure.addPolicy(anitaD.getUserID(), compPolicy1);
//        youDingInsure.addPolicy(anitaD.getUserID(), compPolicy2);
//        youDingInsure.addPolicy(joeyB.getUserID(), compPolicy3);
//        youDingInsure.addPolicy(joeyB.getUserID(), compPolicy4);
//        youDingInsure.addPolicy(1001, thirdParty5);
//        youDingInsure.addPolicy(1002, thirdParty6);
//        youDingInsure.addPolicy(1003, thirdParty7);
//        youDingInsure.addPolicy(1004, thirdParty8);
//        youDingInsure.addPolicy(1001, compPolicy5);
//        youDingInsure.addPolicy(1002, compPolicy6);
//        youDingInsure.addPolicy(1003, compPolicy7);
//        youDingInsure.addPolicy(1004, compPolicy8);
//        
        // TEST WRITE AND READ TEXT FILE************************************************************************************
        
//        if (youDingInsure.saveTextFile("company.txt"))
//            System.out.println("File written successfully!!");
//        
//        InsuranceCompany loadedCompany = new InsuranceCompany();
//        if (loadedCompany.loadTextFile("company.txt"))
//            System.out.println("File loaded successfully!");
//        
//        System.out.print("\nPrint Text File Company!\n\n");
//        System.out.print(loadedCompany);


//        // policy add with non-existing user id (EXPECTED FAILURE)
//        youDingInsure.addPolicy(1999, compPolicy9);
//        
//        // policy add with duplicate policy ID (EXPECTED FAILURE)
//        youDingInsure.addPolicy(1001, compPolicy8);
//        System.out.println();// line spacing
//        
//        // get user ID input from customer and print user policies
////        System.out.print("Please enter user ID number: ");
////        int _userID = Integer.parseInt(userIn.nextLine());
//                
//        // print user policies from customer ID
//         int _userID = 1002;
//        
//        //validate user input and print policies if correct
//        if (youDingInsure.findUser(_userID)==null)
//        {
//            System.out.println("No user found!");
//        }
//        else
//        {
//            youDingInsure.printPolicies(_userID);   
//        }
//        System.out.println();// line spacing
//        
//        // print user policies from customer ID (User Not Valid)
//         _userID = 1007;
//        
//        //validate user input and print policies if correct (EXPECTED FAIL)
//        if (youDingInsure.findUser(_userID)==null)
//        {
//            System.out.println("No user found!");
//        }
//        else
//        {
//            youDingInsure.printPolicies(_userID);   
//        }
//        System.out.println();// line spacing
//        
////        // get user and policy ID input from customer and print the specific policy
////        System.out.print("Please enter user ID number: ");
////        _userID = Integer.parseInt(userIn.nextLine()); //overwrite existing variable as no longer required
////        System.out.print("Please enter policy ID number: ");
////        int _policyID = Integer.parseInt(userIn.nextLine());
//
//        //print specific policy from user and policy ID (users InsuranceCompany.toString())
//        _userID = 1003;
//        int _policyID = 6;
//                
//        // find user and print policy using inputs
//        InsurancePolicy outputPolicy = youDingInsure.findPolicy(_userID, _policyID);
//        if(outputPolicy==null)
//        {
//            System.out.println("No results found!");
//        }
//        else
//        {
//            outputPolicy.print();
//        }
//        System.out.println();// line spacing
//        
//        //print specific policy from user and policy ID (users InsuranceCompany.toString() User does not exist)
//        _userID = 1007;
//        _policyID = 6;
//                
//        // find user and print policy using inputs (EXPECTED FAIL)
//        outputPolicy = youDingInsure.findPolicy(_userID, _policyID);
//        if(outputPolicy==null)
//        {
//            System.out.println("No results found!");
//        }
//        else
//        {
//            outputPolicy.print();
//        }
//        System.out.println();// line spacing
//        
//        // print all users in insurance company
//        System.out.print(youDingInsure);
//                
//        // raise price of all cars across all users and policies by 10% and reprint (InsuranceCompany.carPriceRise())
//        youDingInsure.carPriceRise(0.1);
//        System.out.print(youDingInsure);
//        System.out.println();
//        
//        // print total number of premium payments single user
//        int userID = 1001;
//        System.out.printf("Name: %s \nUser Premium Payments Total: $%.2f\n",youDingInsure.findUser(userID).getName() ,youDingInsure.calcTotalPayments(userID));
//        
//        System.out.println();
//        
//        // print total number of premium payments for each user
//        userID = 0;
//        for (User user : youDingInsure.getUsers())
//        {
//            userID = user.getUserID();
//            System.out.printf("Name: %s \nUser Premium Payments Total: $%.2f\n",youDingInsure.findUser(userID).getName() ,youDingInsure.calcTotalPayments(userID));
//        }
//        System.out.println();// line spacing
//        
//        // print total number of premium payments for user
//        System.out.printf("All Premium Payments Total: $%.2f\n",youDingInsure.calcTotalPayments());
//        
//        System.out.println(); //line spacing
//        
//        // call allPolicies() for the insuranceCompany and store it in an ArrayList and print the list by using InsurancePolicy.printPolicies()
//        ArrayList<InsurancePolicy> outputPolicies = youDingInsure.allPolicies();
//        InsurancePolicy.printPolicies(outputPolicies);
//        
//        // for a given userID and expiry date call filterByExpiryDate (int userID, MyDate date), store the filtered list and print the list by using InsurancePolicy.printPolicies()
//        
//            
//        MyDate inputDate = new MyDate(2023, 8, 10);
//        userID = 1002;
//        filteredPolicies = youDingInsure.filterByExpiryDate(userID, inputDate);
//        System.out.printf("Search results for User: %s\nPolicies expiring before: %s\n\n", youDingInsure.findUser(userID).getName(), inputDate);
//        InsurancePolicy.printPolicies(filteredPolicies);
//        
//        // for a given car model call insuranceCompany.filterByCarModel (String carModel) and print the filtered list 
//        
//        // get car model user input
////        System.out.print("Please enter car model to search: ");
////        String _userCarModel = userIn.nextLine();
//        
//        // CODE TEST set car model
//        String _userCarModel = "Mercedes CLS";
//
//        // filter and print polices using InsuranceCompany.filterByCarModel() and InsurancePolicy.printPolicies()
//        ArrayList<InsurancePolicy> filteredByCar = youDingInsure.filterByCarModel(_userCarModel);
//        InsurancePolicy.printPolicies(filteredByCar);
//        
//        // ask user to enter a date (year, month, and day) and call filterByExpiryDate(MyDate date) and print the filtered list   
//        
//        // get user inputs user ID and expiry date
////        System.out.print("To find policies expiring before a date, please enter;\nUser ID: ");
////        _userID = Integer.parseInt(userIn.nextLine());
////        System.out.print("\nPlease enter the choosen date;\nYear: ");
////        int _year = Integer.parseInt(userIn.nextLine());
////        System.out.print("Month: ");
////        int _month = Integer.parseInt(userIn.nextLine());
////        System.out.print("Day: ");
////        int _day = Integer.parseInt(userIn.nextLine());
////        System.out.println("---------------\n");
//
//        // TEST CODE set user ID and expiry date
////        _userID = 1001;
////        int _year = 2023;
////        int _month = 10;
////        int _day = 18;
//        
//        // filter and print policies expiring before the specified date
////        MyDate _inputDate = new MyDate(_year, _month, _day);
////        ArrayList<InsurancePolicy> userFilteredPolicies = youDingInsure.filterByExpiryDate(_userID, _inputDate);
////        System.out.printf("Search results for User: %s\nPolicies expiring before: %s\n\n", youDingInsure.findUser(_userID).getName(), _inputDate.toString());
////        InsurancePolicy.printPolicies(userFilteredPolicies);
//        
//        // find a user with the given ID (valid) and save it in a user object. Ask user to provide a new address and change the current address for the given user
//        int givenID = 1004; // valid given ID
//        User tempUser = youDingInsure.findUser(givenID);
//        
//        // get new address details from user input and store using setters
////        System.out.print("Please enter new address details: \nStreet number: ");
////        tempUser.setStreetNum(Integer.parseInt(userIn.nextLine()));
////        System.out.print("Street name: ");
////        tempUser.setStreet(userIn.nextLine());
////        System.out.print("Suburb name: ");
////        tempUser.setSuburb(userIn.nextLine());
////        System.out.print("City name: ");
////        tempUser.setCity(userIn.nextLine());
//        
//        // set new address to user saved in temp user variable
//        tempUser.setAddress(49, "Roldit Road", "Blackwall", "Gosford");
//        
//        System.out.println(); //line spacing
//        
//        // print user details to verify address
//        youDingInsure.findUser(givenID).print();
//      
//        youDingInsure.save("company.ser");
//        
//        loadedCompany.load("company.ser");
//        
//        System.out.print("\nPrint Serialised Company!\n\n");
//        System.out.print(loadedCompany);
        

    }
}
