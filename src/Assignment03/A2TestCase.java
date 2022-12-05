package Assignment03;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class A2TestCase {
    
    public static int[] ranges = {200, 500, 1000, 10000};
    
    //TEST CODE Assignment 1 ----------------------------------------------------
    public static void a2TestCase() throws PolicyException, IOException, NameException
    {
        // CODE TEST set flat rate
        int flatRate = 20;
        
        // create required objects for TEST CODE------------------------------------
                
        //create car objects, 1 per third party and comprehensive policy combination
        
        Car carSuv1 = new Car(CarType.SUV, "Nissan Dualis", 2015, 28000.00); // john $500
        Car carSuv2 = new Car(CarType.SUV, "Nissan Dualis", 2015, 49000.00); // john $1000
        Car carSed1 = new Car(CarType.SED, "Toyota Camry", 2020, 18000.00); // sara $200
        Car carSed2 = new Car(CarType.SED, "Toyota Camry", 2020, 21500.00); // sara $500
        Car carLux1 = new Car(CarType.LUX, "Toyota Camry", 2021, 154000.00); // Robert $5000
        Car carLux2 = new Car(CarType.LUX, "Ford Ranger", 2021, 89000.00); // john $2000
        Car carHatch1 = new Car(CarType.HATCH, "Toyota Camry", 2012, 23000.00); // sara $300
        Car carHatch2 = new Car(CarType.HATCH, "Toyota Yaris", 2012, 24499.00);
        Car carWagon1 = new Car(CarType.WAGON, "Ford Ranger", 2019, 13000.00);
        Car carWagon2 = new Car(CarType.WAGON, "Nissan Dualis", 2019, 49000.00); // alex, rob, sara $1000
        Car car4WD1 = new Car(CarType.FOURWD, "Porsche Cayenne", 2021, 18000.00); //robert $400
        Car car4WD2 = new Car(CarType.FOURWD, "Nissan Patrol", 2021, 78990.00);
        Car carCoupe1 = new Car(CarType.COUPE, "Ferrari 488", 2016, 8000.00); // alex $500
        Car carCoupe2 = new Car(CarType.WAGON, "Nissan Dualis", 2016, 49000.00);
        Car carUte1 = new Car(CarType.UTE, "Toyota Camry", 2016, 8000.00); // john $100
        Car carUte2 = new Car(CarType.UTE, "Mazda BT-50", 2016, 50990.00);
        
        // additional car for policy holder and car change
        Car carSed3 = new Car(CarType.SED, "Toyota Camry", 2018, 34990.00);
        
        //create expiry date objects for policies
        
        MyDate date1 = new MyDate(2023, 5, 30);
        MyDate date2 = new MyDate(2024, 3, 4);
        MyDate date3 = new MyDate(2023, 10, 15);
        MyDate date4 = new MyDate(2023, 9, 22);
        MyDate date5 = new MyDate(2024, 2, 6);
        MyDate date6 = new MyDate(2022, 12, 28);
        MyDate date7 = new MyDate(2022, 7, 19);
        MyDate date8 = new MyDate(2023, 5, 2);
        
        //create third party insurance policy objects
        
        ThirdPartyPolicy thirdParty1 = new ThirdPartyPolicy("John Steergood", 3000001, carSuv1, 1, date1, "Returning customer");   
        ThirdPartyPolicy thirdParty2 = new ThirdPartyPolicy("Sara Drivebetter", 3000003, carSed1, 0, date2, "Valued customer");   
        ThirdPartyPolicy thirdParty3 = new ThirdPartyPolicy("Robert Bloggs", 3000005, carWagon1, 0, date3, "Multiple policy holder");
        ThirdPartyPolicy thirdParty4 = new ThirdPartyPolicy("John Steergood", 3000007, carUte1, 0, date7, "Multiple policy holder");
//        ThirdPartyPolicy thirdParty5 = new ThirdPartyPolicy("Ivana Steergood", 3000009, carHatch1, 0, date4, "Maximum no claim bonus");
        ThirdPartyPolicy thirdParty6 = new ThirdPartyPolicy("Sara Drivebetter", 3000011, carHatch1, 0, date5, "Multiple policy holder");
        ThirdPartyPolicy thirdParty7 = new ThirdPartyPolicy("Robert Bloggs", 3000013, car4WD1, 1, date7, "10 year loyalty discount");
        ThirdPartyPolicy thirdParty8 = new ThirdPartyPolicy("Alex Sumguy", 3000015, carCoupe1, 2, date8, "Multiple policy holder");
        
        //create comprehensive insurance policy objects
        ComprehensivePolicy compPolicy1 = new ComprehensivePolicy("John Steergood", 3000002, carSuv2, 0, date4, 38, 2);
        ComprehensivePolicy compPolicy2 = new ComprehensivePolicy("Sara Drivebetter", 3000004, carSed2, 0, date5, 29, 1);
        ComprehensivePolicy compPolicy3 = new ComprehensivePolicy("Robert Bloggs", 3000006, carLux1, 9, date6, 28, 3);
        ComprehensivePolicy compPolicy4 = new ComprehensivePolicy("John Steergood", 3000008, carLux2, 0, date8, 26, 1);
//        ComprehensivePolicy compPolicy5 = new ComprehensivePolicy("Ivana Steergood", 3000010, carHatch2, 3, date2, 38, 2);
        ComprehensivePolicy compPolicy6 = new ComprehensivePolicy("Sara Drivebetter", 3000012, carWagon2, 0, date6, 31, 1);
        ComprehensivePolicy compPolicy7 = new ComprehensivePolicy("Robert Bloggs", 3000014, carWagon2, 0, date1, 31, 3);
        ComprehensivePolicy compPolicy8 = new ComprehensivePolicy("Alex Sumguy", 3000016, carWagon2, 0, date1, 36, 2);

        //create user objects that includes addresses
        User john = new User("John", 15, "Runagutter Lane", "Blackwall", "Wollongong", "john123", "pass1");
        User sara = new User("Sara", 48, "Crasheeps Avenue", "Corrimal", "Wollongong", "sara123","pass12");
        User robert = new User("Robert", 6, "Vague Street", "Janali", "Sydney", "rob123", "pass123");
        User alex = new User("Alex", 21, "Ninth Avenue", "St Kilda", "Melbourne", "alex123", "pass567");
        
        //create insurance company object
       InsuranceCompany insuranceCompany1 = new InsuranceCompany("NRMA", "Sal Scover", "insure1", 20);

        insuranceCompany1.addUser("Sal Scover", "insure1", sara);
        insuranceCompany1.addUser("Sal Scover", "insure1", robert);
        insuranceCompany1.addUser("Sal Scover", "insure1", john);
        insuranceCompany1.addUser("Sal Scover", "insure1", alex);

        insuranceCompany1.addPolicy("Sal Scover", "insure1", sara.getUserID(), thirdParty6);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", sara.getUserID(), thirdParty2);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", robert.getUserID(), thirdParty3);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", robert.getUserID(), thirdParty7);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", sara.getUserID(), compPolicy6);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", sara.getUserID(), compPolicy2);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", robert.getUserID(), compPolicy3);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", robert.getUserID(), compPolicy7);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", alex.getUserID(), thirdParty8);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", alex.getUserID(), compPolicy8);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", john.getUserID(), compPolicy1);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", john.getUserID(), thirdParty1);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", john.getUserID(), compPolicy4);
        insuranceCompany1.addPolicy("Sal Scover", "insure1", john.getUserID(), thirdParty4);

        // ----------------------------------------------TEST CODE--------------------------------------------------
        String adminName = "Sal Scover";
        String adminPassword = "insure1";
        
        System.out.println("\n***********************User policy counts***********************\n");
        
        //alex
//        alex.reportPaymentsPerCarModel(alex.getUserID(), alex.getPassword(), flatRate);
//        System.out.println();
//        
        int[] alexPCount = alex.policyCount(alex.getUsername(), alex.getPassword(), ranges, flatRate);
        System.out.printf("%-20s%s\n", "Alex: ", Arrays.toString(alexPCount));
      
//        //robert
//        robert.printPolicies(robert.getUserID(), robert.getPassword(), flatRate);
//        System.out.println();
     
        int[] robertPCount = robert.policyCount(robert.getUsername(), robert.getPassword(), ranges, flatRate);
        System.out.printf("%-20s%s\n", "Robert: ", Arrays.toString(robertPCount));
//        
//        //sara
//        sara.printPolicies(sara.getUserID(), sara.getPassword(), flatRate);
//        System.out.println();
       
        int[] saraPCount = sara.policyCount(sara.getUsername(), sara.getPassword(), ranges, flatRate);
        System.out.printf("%-20s%s\n", "Sara: ", Arrays.toString(saraPCount));
//        
//        //john
//        john.printPolicies(john.getUserID(), john.getPassword(), flatRate);
//        System.out.println();
    
        int[] johnPCount = john.policyCount(john.getUsername(), john.getPassword(), ranges, flatRate);
        System.out.printf("%-20s%s\n", "John: ", Arrays.toString(johnPCount));
        
        System.out.println("\n***********************Insurance company policy counts***********************\n");
        

        int[] companyCount = insuranceCompany1.policyCount(adminName, adminPassword, ranges);
        System.out.printf("%-20s%s\n", "Company Policy Count: ", Arrays.toString(companyCount));
        
        
        System.out.println("\n***********************Company Policy City Count***********************\n"); 
        
        System.out.println();
        HashMap<String, int[]> cityCount = insuranceCompany1.policyCityCount(adminName, adminPassword, ranges);
        cityCount.forEach((key, value) -> System.out.printf("%-20s%s\n", key, Arrays.toString(value)));
        
        System.out.println("\n***********************Company User Count***********************\n");
        
        int[] userCount = insuranceCompany1.userCount(adminName, adminPassword, ranges);
        System.out.printf("%-20s%s\n", "Company User Count: ", Arrays.toString(userCount));
        
        System.out.println("\n***********************Company User Car Model Count***********************\n");
    
        HashMap<String, int[]> carCount = insuranceCompany1.userCarModelCount(adminName, adminPassword, ranges);
        carCount.forEach((key, value) -> System.out.printf("%-20s%s\n", key, Arrays.toString(value)));
        
        System.out.println("\n***********************User Policy Car Model Count***********************\n");
        
        HashMap<String, int[]> saraCarCount = sara.policyCarModelCount(sara.getUsername(), sara.getPassword(), ranges, flatRate);
        System.out.println("\nSara");
        saraCarCount.forEach((key, value) -> System.out.printf("%-20s%s\n", key, Arrays.toString(value)));
        
        HashMap<String, int[]> johnCarCount = john.policyCarModelCount(john.getUsername(), john.getPassword(), ranges, flatRate);
        System.out.println("\nJohn");
        johnCarCount.forEach((key, value) -> System.out.printf("%-20s%s\n", key, Arrays.toString(value)));
        
        HashMap<String, int[]> robertCarCount = robert.policyCarModelCount(robert.getUsername(), robert.getPassword(), ranges, flatRate);
        System.out.println("\nRobert");
        robertCarCount.forEach((key, value) -> System.out.printf("%-20s%s\n", key, Arrays.toString(value)));
        
        HashMap<String, int[]> alexCarCount = alex.policyCarModelCount(alex.getUsername(), alex.getPassword(), ranges, flatRate);
        System.out.println("\nRobert");
        alexCarCount.forEach((key, value) -> System.out.printf("%-20s%s\n", key, Arrays.toString(value)));
        
        
        System.out.println("\n***********************Company Policy Car Model Count***********************\n");
        
        HashMap<String, int[]> policyCarCount = insuranceCompany1.policyCarModelCount(adminName, adminPassword, ranges);
        policyCarCount.forEach((key, value) -> System.out.printf("%-20s%s\n", key, Arrays.toString(value)));
        
        System.out.print("\n***********************END TESTING***********************\n");
        
        

        //***********************LAB 8 TEST CODE*********************************
        
        System.out.println("\n**************PRINT LAMBDA EXPRESSIONS - LAB 8*************\n");
        
        
        System.out.println("\n**************Example A*************\n");
        
        HashMap<Integer, InsurancePolicy> policies = insuranceCompany1.allPolicies("Sal Scover", "insure1");
        
        policies.values().stream()
                .filter(x -> x.getPolicyHolderName().contains("John"))
                .forEach(InsurancePolicy::print);
        
        System.out.println("\n**************Example B*************\n");
                
        System.out.printf("Premium Total: $%.2f\n", policies.values().stream()
                  .filter(x -> x.getPolicyHolderName().contains("Robert"))
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
        
        Predicate<InsurancePolicy> c1= x -> x.getPolicyHolderName().equals("Alex Sumguy");
	
        ArrayList<InsurancePolicy> policies1= filterPolicies(policiesArray,c1);
        
        InsurancePolicy.printPolicies(policies1);
        
        System.out.println("\nBy Expiry Year 2023\n");
        InsurancePolicy.printPolicies(filterPolicies(policiesArray, x -> x.getExpiryDate().getYear()==2023));
        
        System.out.println("\nBy Car Model Nissan\n");
        InsurancePolicy.printPolicies (filterPolicies(policiesArray, x -> x.car.getModel().contains("Nissan")));
        
        
        System.out.println("\n**************Example G*************\n");
        System.out.println("\nBy Car Type (WAGON) Ordered By Price\n");
        
        filterPolicies(policiesArray, x -> x.car.getType().equals(CarType.WAGON)).stream()
                .sorted(Comparator.comparingDouble(policy -> policy.getCar().getPrice()))
                .forEach(policy -> policy.print());
        
        System.out.println("\n**************Example H*************\n");
        System.out.println("\nPolicy Aggregation By Expiry Year\n");
        
        Map<Integer, List<InsurancePolicy>> groupByDate = policies.values().stream()
                .collect(Collectors.groupingBy(policy -> policy.getExpiryDate().getYear()));
        groupByDate.forEach((exp, pol) -> 
        {
            System.out.println(exp);
            pol.forEach(policy -> System.out.printf("   %s%n", policy));
        });
        
        
        System.out.print("\n***********************END TESTING***********************\n");
        
    }
    
    // LAB 8 METHOD - sort policies based on Predicate
    public static ArrayList<InsurancePolicy> filterPolicies(ArrayList<InsurancePolicy> policies, Predicate<InsurancePolicy> criteria)
    {
        return policies.stream()
                       .filter(criteria)
                       .collect(Collectors.toCollection(ArrayList::new));
    }
}
