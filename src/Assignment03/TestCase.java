package Assignment03;

import java.util.Comparator;
import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestCase implements Serializable
{
    //TEST CODE Assignment 1 ----------------------------------------------------
    public static void testCase() throws PolicyException, IOException, NameException
    {
        // CODE TEST set flat rate
        int flatRate = 100;
        
        // create required objects for TEST CODE------------------------------------
                
        //create car objects, 1 per third party and comprehensive policy combination
        
        Car carSuv1 = new Car(CarType.SUV, "Subaru Outback", 2015, 32000.00);
        Car carSuv2 = new Car(CarType.SUV, "Subaru Outback", 2015, 32000.00);
        Car carSed1 = new Car(CarType.SED, "Holden ClubSport", 2020, 36500.00);
        Car carSed2 = new Car(CarType.SED, "Holden ClubSport", 2020, 36500.00);
        Car carLux1 = new Car(CarType.LUX, "BMW M5", 2021, 76000.00);
        Car carLux2 = new Car(CarType.LUX, "BMW M5", 2021, 76000.00);
        Car carHatch1 = new Car(CarType.HATCH, "Toyota Yaris", 2012, 24499.00);
        Car carHatch2 = new Car(CarType.HATCH, "Toyota Yaris", 2012, 24499.00);
        Car carWagon1 = new Car(CarType.WAGON, "Hyundai i30", 2019, 28990.00);
        Car carWagon2 = new Car(CarType.WAGON, "Hyundai i30", 2019, 28990.00);
        Car car4WD1 = new Car(CarType.FOURWD, "Nissan Patrol", 2021, 78990.00);
        Car car4WD2 = new Car(CarType.FOURWD, "Nissan Patrol", 2021, 78990.00);
        Car carCoupe1 = new Car(CarType.COUPE, "Mercedes CLS", 2016, 164990.00);
        Car carCoupe2 = new Car(CarType.COUPE, "Mercedes CLS", 2016, 164990.00);
        Car carUte1 = new Car(CarType.UTE, "Mazda BT-50", 2016, 50990.00);
        Car carUte2 = new Car(CarType.UTE, "Mazda BT-50", 2016, 50990.00);
        
        // additional car for policy holder and car change
        Car carSed3 = new Car(CarType.SED, "Toyota Camry", 2018, 34990.00);
        
        //create expiry date objects for policies
        
        MyDate date1 = new MyDate(2023, 5, 30);
        MyDate date2 = new MyDate(2023, 3, 4);
        MyDate date3 = new MyDate(2023, 10, 15);
        MyDate date4 = new MyDate(2023, 9, 22);
        MyDate date5 = new MyDate(2023, 2, 6);
        MyDate date6 = new MyDate(2023, 12, 28);
        MyDate date7 = new MyDate(2023, 7, 19);
        MyDate date8 = new MyDate(2023, 5, 2);
        
        //create third party insurance policy objects
        
        ThirdPartyPolicy thirdParty1 = new ThirdPartyPolicy("Ivana Steergood", 3000001, carSuv1, 1, date1, "Returning customer");   
        ThirdPartyPolicy thirdParty2 = new ThirdPartyPolicy("Anita Drivebetter", 3000003, carSed1, 0, date2, "Valued customer");   
        ThirdPartyPolicy thirdParty3 = new ThirdPartyPolicy("Joey Bloggs", 3000005, carWagon1, 1, date3, "Multiple policy holder");
        ThirdPartyPolicy thirdParty4 = new ThirdPartyPolicy("Tim Glasgow", 3000007, carUte1, 1, date7, "Multiple policy holder");
        ThirdPartyPolicy thirdParty5 = new ThirdPartyPolicy("Ivana Steergood", 3000009, carHatch1, 0, date4, "Maximum no claim bonus");
        ThirdPartyPolicy thirdParty6 = new ThirdPartyPolicy("Anita Drivebetter", 3000011, carLux1, 3, date5, "Multiple policy holder");
        ThirdPartyPolicy thirdParty7 = new ThirdPartyPolicy("Joey Bloggs", 3000013, car4WD1, 1, date7, "10 year loyalty discount");
        ThirdPartyPolicy thirdParty8 = new ThirdPartyPolicy("Tim Glasgow", 3000015, carCoupe1, 2, date8, "Multiple policy holder");
        
        //create comprehensive insurance policy objects
        ComprehensivePolicy compPolicy1 = new ComprehensivePolicy("Ivana Steergood", 3000002, carSuv2, 0, date4, 38, 2);
        ComprehensivePolicy compPolicy2 = new ComprehensivePolicy("Anita Drivebetter", 3000004, carSed2, 3, date5, 29, 1);
        ComprehensivePolicy compPolicy3 = new ComprehensivePolicy("Joey Bloggs", 3000006, carWagon2, 4, date6, 31, 3);
        ComprehensivePolicy compPolicy4 = new ComprehensivePolicy("Tim Glasgow", 3000008, carUte2, 2, date8, 26, 1);
        ComprehensivePolicy compPolicy5 = new ComprehensivePolicy("Ivana Steergood", 3000010, carHatch2, 3, date2, 38, 2);
        ComprehensivePolicy compPolicy6 = new ComprehensivePolicy("Anita Drivebetter", 3000012, carLux2, 2, date6, 29, 1);
        ComprehensivePolicy compPolicy7 = new ComprehensivePolicy("Joey Bloggs", 3000014, car4WD2, 0, date1, 31, 3);
        ComprehensivePolicy compPolicy8 = new ComprehensivePolicy("Tim Glasgow", 3000016, carCoupe2, 0, date1, 26, 2);
        
        //extra policy for testing addPolicy method
        ComprehensivePolicy compPolicy9 = new ComprehensivePolicy("Test Policy", 3000017, carSed1, 0, date1, 26, 2);


        //create user objects that includes addresses
        User ivanaS = new User("Ivana Steergood", 15, "Runagutter Lane", "Blackwall", "Gosford", "Sivana", "password1");
        User anitaD = new User("Anita Drivebetter", 48, "Crasheeps Avenue", "Corrimal", "Wollongong", "Danita", "password2");
        User joeyB = new User("Joey Bloggs", 6, "Vague Street", "Janali", "Sydney", "Bjoey", "password3");
        User timG = new User("Tim Glasgow", 21, "Ninth Avenue", "Loftus", "Sydney", "Gtim", "password4");
        
        //create insurance company object
        InsuranceCompany youDingInsure = new InsuranceCompany("YouDing Insurance Pty Ltd", "Sal Scover", "insure1", 100);

        // ----------------------------------------------TEST CODE--------------------------------------------------
        
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
//        // TEST USer-----------------------------
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
////        ArrayList<InsurancePolicy> filteredPolicies = ivanaS.filterByCarModel(_carModel);
//        
//        HashMap<Integer, InsurancePolicy> filteredPolicies = ivanaS.filterByCarModel(_carModel);
//        
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
//        //TEST Insurance Company------------------------------------------
//        
//        // TEST succesful login
//        String _userName = "Sal Scover";
//        String _password = "insure1";
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
//        // TEST unsuccesful login
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
        
        //add user to insurance company with prompts using first method
//        if(youDingInsure.addUser(joeyB))
//        {
//            System.out.println("Successfully added user!");
//        }
//        else
//        {
//            System.out.println("Failed to add user, please check user details!");
//        }
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
//        
//        // add user to insurance company with prompts using overloaded method
//        if(youDingInsure.addUser("Ivana Steergood", ivanaS.getAddress(), "Sivana"))
//        {
//            System.out.println("Successfully added user!");
//        }
//        else
//        {
//            System.out.println("Failed to add user, please check user details!");
//        }
//        
//        // add user to insurance company with prompts overloaded method
//        if(youDingInsure.addUser("Tim Glasgow", timG.getAddress(), "Gtim"))
//        {
//            System.out.println("Successfully added user!");
//        }
//        else
//        {
//            System.out.println("Failed to add user, please check user details!");
//        }
//        
////        // add user to insurance company with prompts using overloaded method (duplicate user - expected fail)
////        if(youDingInsure.addUser("Joey Bloggs", joeyB.getAddress(), "Bjoey"))
////        {
////            System.out.println("Successfully added user!");
////        }
////        else
////        {
////            System.out.println("Failed to add user, please check user details!");
////        }
////        
////        // add user to insurance company with prompts using first method (duplicate user - expected fail)
////        if(youDingInsure.addUser(ivanaS))
////        {
////            System.out.println("Successfully added user!");
////        }
////        else
////        {
////            System.out.println("Failed to add user, please check user details!");
////        }
//        System.out.println(); //line spacing
//        
//        System.out.print(youDingInsure);
//        
//        // add policies to users with InsuranceCompany.addPolicy() method , print corresponding task validation messages
//        
//        youDingInsure.addPolicy(5, thirdParty1);
//        youDingInsure.addPolicy(2, thirdParty2);
//        youDingInsure.addPolicy(3, thirdParty3);
//        youDingInsure.addPolicy(6, thirdParty4);
//        youDingInsure.addPolicy(5, compPolicy1);
//        youDingInsure.addPolicy(2, compPolicy2);
//        youDingInsure.addPolicy(3, compPolicy3);
//        youDingInsure.addPolicy(6, compPolicy4);
//        youDingInsure.addPolicy(5, thirdParty5);
//        youDingInsure.addPolicy(2, thirdParty6);
//        youDingInsure.addPolicy(3, thirdParty7);
//        youDingInsure.addPolicy(6, thirdParty8);
//        youDingInsure.addPolicy(5, compPolicy5);
//        youDingInsure.addPolicy(2, compPolicy6);
//        youDingInsure.addPolicy(3, compPolicy7);
//        youDingInsure.addPolicy(6, compPolicy8);
//        
//        // policy add with non-existing user id (EXPECTED FAILURE)
//        youDingInsure.addPolicy(1999, compPolicy9);
//        
//        // policy add with duplicate policy ID (EXPECTED FAILURE)
//        youDingInsure.addPolicy(6, compPolicy8);
//        System.out.println();// line spacing
//        
//        // get user ID input from customer and print user policies
////        System.out.print("Please enter user ID number: ");
////        int _userID = Integer.parseInt(userIn.nextLine());
//                
//        // print user policies from customer ID
//         int _userID = 2;
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
//        _userID = 3;
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
//        _userID = 7;
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
//        int userID = 2;
//        System.out.printf("Name: %s \nUser Premium Payments Total: $%.2f\n",youDingInsure.findUser(userID).getName() ,youDingInsure.calcTotalPayments(userID));
//        
//        System.out.println();
//        
//        // ARRAY LIST CODE - print total number of premium payments for each user
//        
////        for (User user : youDingInsure.getUsers())
////        {
////            userID = user.getUserID();
////            System.out.printf("Name: %s \nUser Premium Payments Total: $%.2f\n",youDingInsure.findUser(userID).getName() ,youDingInsure.calcTotalPayments(userID));
////        }
////        System.out.println();// line spacing
//        
//        //HASH MAP CODE - print total number of premium payments for each user
//        
//        for (User user : youDingInsure.getUsers().values())
//        {
//            userID = user.getUserID();
//            System.out.printf("Name: %s \nUser Premium Payments Total: $%.2f\n",youDingInsure.findUser(userID).getName() ,youDingInsure.calcTotalPayments(userID));
//        }
//        System.out.println();// line spacing
//        
//        
//        // print total number of premium payments for user
//        System.out.printf("All Premium Payments Total: $%.2f\n",youDingInsure.calcTotalPayments());
//        
//        System.out.println(); //line spacing
//        
//        // ARRAY LIST CODE - call allPolicies() for the insuranceCompany and store it in an ArrayList and print the list by using InsurancePolicy.printPolicies()
//        
////        ArrayList<InsurancePolicy> outputPolicies = youDingInsure.allPolicies();
//        
//        //HASH MAP CODE - call allPolicies() for the insuranceCompany and store it in an ArrayList and print the list by using InsurancePolicy.printPolicies()
//        
//        HashMap<Integer, InsurancePolicy> outputPolicies = youDingInsure.allPolicies();
//        
//        InsurancePolicy.printPolicies(outputPolicies);
//        
//        // for a given userID and expiry date call filterByExpiryDate (int userID, MyDate date), store the filtered list and print the list by using InsurancePolicy.printPolicies()
//        
//            
//        MyDate inputDate = new MyDate(2023, 8, 10);
//        userID = 2;
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
//        // ARRAY LIST CODE - filter and print polices using InsuranceCompany.filterByCarModel() and InsurancePolicy.printPolicies()
////        ArrayList<InsurancePolicy> filteredByCar = youDingInsure.filterByCarModel(_userCarModel);
//        
//        // HASH MAP CODE - filter and print polices using InsuranceCompany.filterByCarModel() and InsurancePolicy.printPolicies()
//        
//        HashMap<Integer, InsurancePolicy> filteredByCar = youDingInsure.filterByCarModel(_userCarModel);
//        
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
//        // filter and print policies expiring before the specified date
////        MyDate _inputDate = new MyDate(_year, _month, _day);
////        ArrayList<InsurancePolicy> userFilteredPolicies = youDingInsure.filterByExpiryDate(_userID, _inputDate);
////        System.out.printf("Search results for User: %s\nPolicies expiring before: %s\n\n", youDingInsure.findUser(_userID).getName(), _inputDate.toString());
////        InsurancePolicy.printPolicies(userFilteredPolicies);
//        
//        // find a user with the given ID (valid) and save it in a user object. Ask user to provide a new address and change the current address for the given user
//        int givenID = 6; // valid given ID
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
//        //TEST NEW CODE-----------------------------------
//        
//        //call populateDistinctCityNames and store in arraylist
//        ArrayList<String> cities = youDingInsure.populateDistinctCityNames();
//        
//        //call getTotalPaymentPerCity and store in arraylist (method calls getTotalPaymentForCity)
//        ArrayList<Double> payments = youDingInsure.getTotalPaymentPerCity(cities);
//        
//        //call reportPaymentPerCity using previously called and stored methods
//        youDingInsure.reportPaymentPerCity(cities, payments);
//        
//        System.out.println(); //line spacing
//        
//        //call admin remove user policy
//        if(youDingInsure.removePolicy(6, 3000016))
//        {
//            System.out.println("Admin Removed policy!\n");
//        }
//        else
//        {
//            System.out.println("Operation Failed!! Policy not found.");
//        }
//        
//        youDingInsure.findUser(6).print();
//        
//        //call user remove policy
//        User user = youDingInsure.findUser(6);
//        
//        if(user.removePolicy(15))
//        {
//            System.out.println("User Removed policy!\n");
//        }
//        else
//        {
//            System.out.println("Operation Failed! Policy not found.");
//        }
//        
//        user.print();
//
//        //call admin remove user
//        youDingInsure.removeUser(6);
//        System.out.print(youDingInsure);
//        
//        //USER Reporting Test---------------------------------------------
//        
//        //call  User populateDistinctCarModels and print 
//        ArrayList<String> userModels = joeyB.populateDistinctCarModels();
//        
//        //call User getTotalCountPerCarModel() which also calls getTotalCountForCarModel() and display results
//        ArrayList<Integer> userCounts = joeyB.getTotalCountPerCarModel(userModels);
//        
//        //call User getTotalPaymentPerCarModel() which also calls getTotalPaymentForCarModel() and display result
//        ArrayList<Double> userPayments = joeyB.getTotalPaymentPerCarModel(userModels, flatRate);
//        
//        //call User report function using above arrays
//        joeyB.reportPaymentsPerCarModel(userModels, userCounts, userPayments);
//        
//        
//        //COMPANY Reporting Test---------------------------------------------
//        
//        //call  Company populateDistinctCarModels and print 
//        ArrayList<String> compModels = youDingInsure.populateDistinctCarModels();
//        
//        //call Company getTotalCountPerCarModel() which also calls getTotalCountForCarModel() and display results
//        ArrayList<Integer> compCounts = youDingInsure.getTotalCountPerCarModel(compModels);
//        
//        //call Company getTotalPaymentPerCarModel() which also calls getTotalPaymentForCarModel() and display result
//        ArrayList<Double> compPayments = youDingInsure.getTotalPaymentPerCarModel(compModels, flatRate);
//        
//        //call Company report function using above arrays
//        youDingInsure.reportPaymentsPerCarModel(compModels, compCounts, compPayments);
//        
//        // LAB 4 TEST CODE-----------------------------------------------------------------------------------------------------------
//        System.out.print("\n\nLAB 4 TEST CODE\n\n");// line spacings
//        
//        // USER TEST--------------------------------
//        
//        ArrayList<InsurancePolicy> anitaPolicyDeep = anitaD.deepCopyPolicies(); // deep policy copy anitaD
//        ArrayList<InsurancePolicy> anitaPolicyShallow = anitaD.shallowCopyPolicies(); // shallow policy copy anitaD
//        
//        //change city to New York
//        anitaD.setCity("New York");
//        
//        //add new policy to anitaD
//        anitaD.addPolicy(compPolicy8);
//        
//        //sort users policies by date
//        System.out.println(anitaD.sortPoliciesByDate());
//        
//        anitaD.findPolicy(3000011).car = carCoupe1;
//        
//        // print each resulting policy set
//        System.out.println("\nOriginal policies");
//        anitaD.print();
//        System.out.println("\nDeep Copy policies");        
//        InsurancePolicy.printPolicies(anitaPolicyDeep);
//        System.out.println("\nShallow Copy policies");        
//        InsurancePolicy.printPolicies(anitaPolicyShallow);
//        
//        //COMPANY TEST------------------
//        
//        //ARRAY LIST CODE - deep and shallow copy insurance company users
//        
////        ArrayList<User> deepCopyUsers = youDingInsure.deepCopyUsers(); // deep company users copy
////        ArrayList<User> shallowCopyUsers = youDingInsure.shallowCopyUsers(); // shallow company users copy
//        
//        //HASH MAP CODE - deep and shallow copy insurance company users
//        
//        HashMap<Integer, User> deepCopyUsers = youDingInsure.deepCopyUsers(); // deep company users copy
//        HashMap<Integer, User> shallowCopyUsers = youDingInsure.shallowCopyUsers(); // shallow company users copy
//        
//        youDingInsure.addUser(timG); // add user to original company users array list
//        
//        System.out.println(youDingInsure.sortUsers());
//        
////        System.out.println(); // line spacing
//        
//        // print each resulting set of user lists
//        System.out.println("\nOriginal users");
//        youDingInsure.printCompanyUsers(youDingInsure.getUsers());
//        System.out.println("\nDeep Copy users");        
//        youDingInsure.printCompanyUsers(deepCopyUsers);
//        System.out.println("\nShallow Copy users");        
//        youDingInsure.printCompanyUsers(shallowCopyUsers);
//        
//        System.out.print("\n\n\n");
//        
//        // create deep copy of insurance company
//        InsuranceCompany clonedCompany = new InsuranceCompany(youDingInsure); //clone using copy constructor
//        
//        
//        System.out.print("\nAdd user Franky Bob to Cloned Insurance Company\n");
//        
//        Address franksAdd = new Address(16, "Jame Road", "Dingal", "Berries");
//        clonedCompany.addUser("Franky Bob",franksAdd, "bing"); // add new user to cloned company
//        
//        System.out.print("\n\nYOU DING INSURE\n\n");
//        youDingInsure.printCompanyUsers(youDingInsure.getUsers());
//        System.out.print("\n\nCLONE COMPANY\n\n");
//        clonedCompany.printCompanyUsers(clonedCompany.getUsers());
//                       
//        System.out.print("\nRemove Tim Glasgow from Cloned Insurance Company\n");
//        
//        clonedCompany.removeUser(4); // remove Tim Glasgow from cloned company
//        
//        System.out.print("\n\nYOU DING INSURE\n\n");
//        youDingInsure.printCompanyUsers(youDingInsure.getUsers());
//        System.out.print("\n\nCLONE COMPANY\n\n");
//        clonedCompany.printCompanyUsers(clonedCompany.getUsers());
//        
//        System.out.print("\nRemove policy 16 from user ID 2 under You Ding Insure\n");
//        
//        youDingInsure.removePolicy(2, 3000016); //remove policy 16 from user ID 2 under You Ding Insure
//        
//        System.out.print("\n\nYOU DING INSURE\n\n");
//        youDingInsure.printCompanyUsers(youDingInsure.getUsers());
//        System.out.print("\n\nCLONE COMPANY\n\n");
//        clonedCompany.printCompanyUsers(clonedCompany.getUsers()); 
//        
//        System.out.print("\n*****LAB 5 TEST CODE*****\n");
//        // LAB 5 ------------------------------------------ TEST CODE (data aggregation/reports and policy id auto generation)
//        System.out.println("\n----------------Test Data Aggregation and Reporting using Hash Maps---------------\n");
//                
//        System.out.print("\nPremiums by car type for Anita Drivebetter:\n");
//        
//        // HASH MAP DATA AGGREGATION REPORT TEST - USER ---------- LAB 5
//    
//        //call report using internal hash map aggregation methods and display results
//        anitaD.reportPaymentsPerCarModel(flatRate);
//        
//        // HASH MAP DATA AGGREGATION REPORT TEST - INSURANCE COMPANY--------LAB 5
//        
//        //call report using internal aggregation methods
//        
//        System.out.print("\nPremiums by car type for Cloned Insurance Company:\n");
//        
//        clonedCompany.reportPaymentsPerCarModel(flatRate);
//        
//        System.out.print("\nPremiums by city for Cloned Insurance Company:\n");
//        
//        clonedCompany.reportTotalPremiumPerCity();
//        
//        System.out.println("\n----------------Test Policy Excpetion Error and ID generation---------------\n");
//        
//        ThirdPartyPolicy testPolicy;
//        try
//        {
//            testPolicy = new ThirdPartyPolicy("Tim Glasgow", 3007, carUte1, 1, date7, "Multiple policy holder");
//        }
//        catch (PolicyException e)
//        {
//            testPolicy = new ThirdPartyPolicy("Tim Glasgow", e.getNewID(), carUte1, 1, date7, "Multiple policy holder");
//            System.out.println(e);
//        }
//        
//        System.out.println(); //line spacing
//        System.out.println(testPolicy);
//        System.out.println(); //line spacing
//        
//        System.out.println("Test user policy addition of Comprehensive Policy with auto ID generation..\n");
//        
//        anitaD.print();
//        anitaD.createComprehensivePolicy("Anita Drivebetter", 23, carUte2, 2, date2, 32, 1);
//        System.out.println(); //line spacing
//        anitaD.print();
//        
//        System.out.println("\n\n-----------------------LAB 6 TEST CODE--------------------------------\n\n");
//        
//        // create files for serialised testing...
//        
//        // create company and save to serialised file for testing
//        InsuranceCompany insuranceCompany = new InsuranceCompany("Test Company", "Admin Name", "Admin Password", 100);
//        insuranceCompany.save("company.ser");
//        
//        // create policy hash map and save to serialised file for testing
//        HashMap<Integer, InsurancePolicy> testPolicies = new HashMap<>();
//        InsurancePolicy.save(testPolicies, "policies.ser");
//        
//        // create user hash map and save to serialised file for testing
//        HashMap<Integer, User> testUsers = new HashMap<>();
//        User.save(testUsers, "users.ser");
//        
//        System.out.println("\n---------------------------Load and Save Serialised Files---------------------------------\n\n");
//        
//        //---------------------save and load methods----------------------------
//        
//        //testing binary file and list of policies
//        HashMap<Integer,InsurancePolicy> policies1=InsurancePolicy.load("policies.ser");
//        InsurancePolicy.printPolicies(policies1);
//
//        policies1.put(3002713, new ThirdPartyPolicy("Jimbo Jones", 3002713, car4WD2, 0, date3, "10 year loyalty discount"));
//        InsurancePolicy.save(policies1,"policies.ser");
//        policies1.clear();
//
//        policies1=InsurancePolicy.load("policies.ser");
//        InsurancePolicy.printPolicies(policies1);
//        System.out.println();
//
//        //------------------------------------------------------------------------
//        //testing binary file and list of users
//        HashMap<Integer,User> users=User.load("users.ser");
//        User.printUsers(users);
//
//        User user1=new User("Wendy Peaks", 32, "Some Road", "Somewhere", "Outthere", "password");
//        user1.addPolicy(new ThirdPartyPolicy("Wendy Peaks", 3997583, carUte1, 2, date1, "No Comments"));
//        users.put(user1.getUserID(),user1);
//        User.save(users,"users.ser");
//        users.clear();
//
//        users=User.load("users.ser");
//        User.printUsers(users);
//        System.out.println();
////
////        //----------------------------------------------------------------------
////        //InsuranceCompany and binary file
//        InsuranceCompany insuranceCompany1=new InsuranceCompany();//using default constructor
//        insuranceCompany1.load("company.ser");//the whole company including all users and all policies are loaded
//        System.out.println(insuranceCompany1);
//
//        insuranceCompany1.addUser(user1);
//        insuranceCompany1.addPolicy(user1.getUserID(), new ThirdPartyPolicy("Wendy Peaks", 3489758, carSed1, 1, date5, "Extra Policy"));
//        insuranceCompany1.save("company.ser");
//        InsuranceCompany insuranceCompany2=new InsuranceCompany();
//
//        insuranceCompany2.load("company.ser");
//        System.out.println(insuranceCompany2);
////
////        //-----------------------------------------------------------------------------
//        
//        // create files for text output/input testing...(Save previous Serial files to text files)
//        
//        // save company and text file for testing
//        insuranceCompany2.saveTextFile("company.txt");
//        
//        // save policy hash map to text file for testing
//        InsurancePolicy.saveTextFile(policies1, "policies.txt");
//        
//        // save user hash map to text file for testing
//        User.saveTextFile(users, "users.txt");
//
//        
//        System.out.println("\n---------------------------Load and Save Text Files---------------------------------\n\n");
//        
//        //testing text file and list of policies with toDilimitedString
//        HashMap<Integer,InsurancePolicy> textPolicies=InsurancePolicy.loadTextFile("policies.txt");
//        InsurancePolicy.printPolicies(textPolicies);
//        System.out.println();
//        
//        textPolicies.put(3115888, new ThirdPartyPolicy("Darth Vader", 3115888, carSuv1, 5, date2, "Car Always Parked On DarkSide"));
//        InsurancePolicy.saveTextFile(textPolicies,"policies.txt");
//        textPolicies.clear();
//
//        textPolicies=InsurancePolicy.loadTextFile("policies.txt");
//        InsurancePolicy.printPolicies(textPolicies);
//        System.out.println();
//        
////        //------------------------------------------------------------------------------
////        //testing text file and list of users with toDilimitedString
//        HashMap<Integer,User> textUsers=User.loadTextFile("users.txt");
//        User.printUsers(textUsers);
//        System.out.println();
//
//        User user2=new User("Luke Skywalker", 23, "Force Street", "Jedi Hill", "Empire", "password");
//        user2.addPolicy(new ThirdPartyPolicy("Luke SkyWalker", 3333666, carWagon1, 0, date3, "Very Forceful!"));
//        textUsers.put(user2.getUserID(),user2);
//        User.saveTextFile(textUsers, "users.txt");
//        textUsers.clear();
//
//        textUsers=User.loadTextFile("users.txt");
//        User.printUsers(textUsers);
//        System.out.println();
//        
//        System.out.println("\n-----------------------Insurance Company Text File Test-----------------------------\n\n");
//        
////        //-------------------------------------------------------------------
////        //InsuranceCompany and text file
//        InsuranceCompany insuranceCompany3=new InsuranceCompany();//using default constructor
//        insuranceCompany3.loadTextFile("company.txt");//the whole company including all users and all policies are loaded
//        System.out.println(insuranceCompany3);
//        System.out.println();
//
//        insuranceCompany3.addUser(timG);
//        insuranceCompany3.addPolicy(timG.getUserID(), new ComprehensivePolicy("Tim Glasgow", 3112223, carSed1, 0, date6, 36, 2));
//        insuranceCompany3.saveTextFile("company.txt");
//        InsuranceCompany insuranceCompany4=new InsuranceCompany();
//
//        insuranceCompany4.loadTextFile("company.txt");
//        System.out.println(insuranceCompany4);
//        System.out.println();
    
    
        // ***************ASSIGNMENT 2 TEST CODE (filing_test.java)******************

        // create insurance company 1 with users and poilices

        InsuranceCompany insuranceCompany1 = new InsuranceCompany("NRMA", "Sal Scover", "insure1", 150);

        insuranceCompany1.addUser("Sal Scover", "insure1", anitaD);
        insuranceCompany1.addUser("Sal Scover", "insure1", joeyB);
        insuranceCompany1.addUser("Sal Scover", "insure1", timG);

        insuranceCompany1.addPolicy("Sal Scover", "insure1", anitaD.getUserID(), thirdParty6);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", anitaD.getUserID(), thirdParty2);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", joeyB.getUserID(), thirdParty3);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", joeyB.getUserID(), thirdParty7);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", anitaD.getUserID(), compPolicy6);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", anitaD.getUserID(), compPolicy2);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", joeyB.getUserID(), compPolicy3);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", joeyB.getUserID(), compPolicy7);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", timG.getUserID(), thirdParty8);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", timG.getUserID(), compPolicy8);
        
        System.out.println("\nPrint Original insuranceCompany1:\n");
        System.out.print(insuranceCompany1);
        
        insuranceCompany1.saveTextFile("company.txt");


        System.out.print("\n\n************Assignmnet 2 - Test filing using filing_test.java**************\n\n");

        //-----------------------------------------------------------------------------------
            //testing the save and load policies in/from BINARY FILE

        try 
        {
            InsurancePolicy.save(insuranceCompany1.allPolicies("Sal Scover", "insure1"),"policies.ser");
            HashMap<Integer,InsurancePolicy> policies=InsurancePolicy.load("policies.ser");
            System.out.println("\nPrinting a list of policies loaded from binary file\n");
            InsurancePolicy.printPolicies(policies);
        }
        catch (IOException e)
        {
            System.err.println(e);
        }

        //-----------------------------------------------------------------------------------
        //testing the save and load policies in/from TEXT FILE

        try 
        {
            InsurancePolicy.saveTextFile(insuranceCompany1.allPolicies("Sal Scover", "insure1"),"policies.txt");
            HashMap<Integer,InsurancePolicy> policies=InsurancePolicy.loadTextFile("policies.txt");
            System.out.println("\nPrinting a list of policies loaded from Text file\n");
            InsurancePolicy.printPolicies(policies);
        }
        catch (IOException e)
        {
            System.err.println(e);
        }

        //-----------------------------------------------------------------------------------
        //testing the save and load users in/from BINARY FILE

        try 
        {
            User.save(insuranceCompany1.getUsers(),"users.ser");
            HashMap<Integer,User> users=User.load("users.ser");
            System.out.println("\nPrinting a list of users loaded from binary file\n");
            User.printUsers(users);
        }
        catch (IOException e)
        {
                System.err.println(e);
        }

        //-----------------------------------------------------------------------------------
        //testing the save and load users in/from TEXT FILE

        try 
        {
            User.saveTextFile(insuranceCompany1.getUsers(),"users.txt");
            HashMap<Integer,User> users=User.loadTextFile("users.txt");
            System.out.println("\nPrinting a list of users loaded from Text file\n");
            User.printUsers(users);
        }
        catch (IOException e)
        {
            System.err.println(e);
        }


        //-----------------------------------------------------------------------------------
        //testing the save and load InsuranceCompany in/from BINARY FILE

            insuranceCompany1.save("company.ser");
            InsuranceCompany insuranceCompany2=new InsuranceCompany(); // use default constructor
            insuranceCompany2.load("company.ser");
            System.out.println("\nPrinting the insurance company loaded from binary file\n");
            System.out.println(insuranceCompany2);
       
        //-----------------------------------------------------------------------------------
        //testing the save and load InsuranceCompany in/from Text FILE

            insuranceCompany1.saveTextFile("company.txt");
            insuranceCompany2=new InsuranceCompany();
            insuranceCompany2.loadTextFile("company.txt");
            System.out.println("\nPrinting the insurance company loaded from text file\n");
            System.out.println(insuranceCompany2);
            
    
        //**********************ASSIGNMENT 2 TEST CODE**********************************
        
        
        // load insurance company from text file for testing
        
        InsuranceCompany testCompany = new InsuranceCompany();
        
        try
        {
            testCompany.loadTextFile("company.txt");
        }
        catch (IOException e)
        {
            System.err.println(e);
        }


        //*********sort users by premiums*********
       
        System.out.println("\n-------------Users sorted by total premium payments (uses comparator, high to low)-------------\n");
        
        ArrayList<User> userList = testCompany.sortUsers();
        
//        Comparator<User> comparator = new InsuranceCompany.UserPremiumComparator();
        Collections.sort(userList, new InsuranceCompany.UserPremiumComparator());
        
        for (User user: userList)
        {
            System.out.println(user);
            System.out.printf("Total Premium: %.2f\n", user.calcTotalPremiums(user.getUsername(), user.getPassword(), flatRate));
            System.out.println();
        }
        
        System.out.println("\n\n-------------Filter users by city-------------\n");
        
        // get users by city in hashmap with city key and user values
        
        HashMap <String, ArrayList<User>> userByCity = testCompany.getUsersPerCity();
        
        // print above results with formating
        
        System.out.printf("%-25s%s\n", "City", "User");
        for (String city : userByCity.keySet())
        {   
            System.out.printf("%-25s", city);
            var counter=0;
            for (User user : userByCity.get(city))
            {
                if (counter==0)
                {    
                    System.out.print(user.getName()+"\n");
                }
                else
                {
                    System.out.printf("%-25s%s\n", "", user.getName());
                }
                counter++;
            }
        }
        
//*******filter all policies by expiry date returning user names with expiring policies*******
        
        System.out.println("\n\n-------------Filter policies by expiry date 15/6/2023 (using compare)-------------\n");

        MyDate testDate = new MyDate(2023, 6, 15); // create test date object for testing date filtering
        
        HashMap<String, ArrayList<InsurancePolicy>> filteredByDate = testCompany.filterPoliciesByExpiryDate("Sal Scover", "insure1", testDate);
        
        // print filtered by date with formatting
        System.out.printf("%-25s%s\n", "Name", "Policy"); // headings

        for (String userName : filteredByDate.keySet())
        {   
            if (!filteredByDate.get(userName).isEmpty()) // check if policies exist for given user
            {
                System.out.printf("%-25s", userName);
                int counter=0;
                for (InsurancePolicy policy : filteredByDate.get(userName))
                {
                    if (counter==0)
                    {    
                        System.out.println(policy);
                    }
                    else
                    {
                        System.out.printf("%-25s%s\n", "", policy);
                    }
                    counter++;
                }
            }
            else
            {
                System.out.printf("%-25s\n", userName);
            }
            System.out.println(); //spacing between users
        }
        
        
        //***********************LAB 8 TEST CODE*********************************
        
        System.out.println("\n**************PRINT LAMBDA EXPRESSIONS - LAB 8*************\n");
        
        System.out.println("\n**************Example A*************\n");
        
        HashMap<Integer, InsurancePolicy> policies = insuranceCompany1.allPolicies("Sal Scover", "insure1");
        
        policies.values().stream()
                .filter(x -> x.getPolicyHolderName().contains("Tim"))
                .forEach(InsurancePolicy::print);
        
        System.out.println("\n**************Example B*************\n");
                
        System.out.printf("Premium Total: $%.2f\n", policies.values().stream()
                  .filter(x -> x.getPolicyHolderName().contains("Anita"))
                  .mapToDouble(x -> x.calcPayment(flatRate))
                  .reduce(0.0, (x, y) -> x + y));
        
        System.out.println("\n**************Example C*************\n");
     
        Predicate<InsurancePolicy> p1= p -> p.calcPayment(flatRate)>=200 && p.calcPayment(flatRate)<=500;
        
        System.out.printf("First policy with premium between 200 to 500 is %s %n",
        policies.values().stream()
                .filter(p1)
                .findFirst()
                .map(p -> String.format("%s, ID: %d,  Price: $%.2f", p.getPolicyHolderName(), p.getId(), p.calcPayment(flatRate)))
                .get());
        
        
        System.out.println("\n**************Example D*************\n");
        
        policies.values().stream()
                .filter(p1)
                .sorted(Comparator.comparing(InsurancePolicy::getId))
                .forEach(p -> System.out.printf("%s, ID: %d,  Price: $%.2f\n", p.getPolicyHolderName(), p.getId(), p.calcPayment(flatRate)));
        
        
        System.out.println("\n**************Example E*************\n");
        
        System.out.printf("Total premium for all policies between 200 to 500 is $%.2f %n",
        policies.values().stream()
                .filter(p1)
                .mapToDouble(policy -> policy.calcPayment(flatRate))
                .reduce(0.0, (x, y) -> x + y));
        
        System.out.println("\n**************Example F*************\n");
     
        ArrayList<InsurancePolicy> policiesArray = new ArrayList<>(policies.values()); // Convert - ALL OUR CODE WORKS ON HASH MAPS!!
        
        Predicate<InsurancePolicy> c1= x -> x.getPolicyHolderName().equals("Joey Bloggs");
	
        ArrayList<InsurancePolicy> policies1= filterPolicies(policiesArray,c1);
        
        InsurancePolicy.printPolicies(policies1);
        
        System.out.println("\nBy Expiry Year 2023\n");
        InsurancePolicy.printPolicies(filterPolicies(policiesArray, x -> x.getExpiryDate().getYear()==2023));
        
        System.out.println("\nBy Car Model Nissan\n");
        InsurancePolicy.printPolicies (filterPolicies(policiesArray, x -> x.car.getModel().contains("Nissan")));
        
        
        System.out.println("\n**************Example G*************\n");
        
        filterPolicies(policiesArray, x -> x.car.getType().equals(CarType.LUX)).stream()
                .sorted(Comparator.comparingDouble(policy -> policy.getCar().getPrice()))
                .forEach(policy -> policy.print());
        
        System.out.println("\n**************Example H*************\n");
        
        Map<Integer, List<InsurancePolicy>> groupByDate = policies.values().stream()
                .collect(Collectors.groupingBy(policy -> policy.getExpiryDate().getYear()));
        groupByDate.forEach((exp, pol) -> 
        {
            System.out.println(exp);
            pol.forEach(policy -> System.out.printf("   %s%n", policy));
        });

        
    }
    
    // LAB 8 METHOD - sort policies based on Predicate
    public static ArrayList<InsurancePolicy> filterPolicies(ArrayList<InsurancePolicy> policies, Predicate<InsurancePolicy> criteria)
    {
        return policies.stream()
                       .filter(criteria)
                       .collect(Collectors.toCollection(ArrayList::new));
    }

}
