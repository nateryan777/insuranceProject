package Assignment03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.Serializable;
import java.io.IOException;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public abstract class InsurancePolicy implements Cloneable, Comparable<InsurancePolicy>, Serializable
{
    //attributes
    protected String policyHolderName;
    protected int id;
    protected Car car;
    protected int numberOfClaims;
    protected MyDate expiryDate;

    //constructor
    public InsurancePolicy(String policyHolderName, int id, Car car,int numberOfClaims, MyDate expiryDate) throws NameException, PolicyException
    {
       
        if (!Pattern.matches("[A-Z][A-Za-z]{2,}[\\s][A-Z][A-Za-z]{2,}", policyHolderName)) // check name matches valid pattern
        {
            throw new NameException(); // user defined exception
        }
        else
        {
            this.policyHolderName = policyHolderName;
        }
        try
        {
            if (id < 3000000 || id > 3999999)
            {
                throw new PolicyException(id); // user defined exception
            }
            else
            {
                this.id = id;
            }
        }
        catch(PolicyException e)
        {
            this.id = e.getNewID();
            JOptionPane.showMessageDialog(null, "Invalid Policy ID format!\n\nAdmin has generated and assigned a policy ID.");
        }

        this.car = car;
        this.numberOfClaims = numberOfClaims;
        this.expiryDate = expiryDate;
    }
    
    //copy constructor
    public InsurancePolicy(InsurancePolicy policy)
    {
        policyHolderName = policy.policyHolderName;
        id = policy.id;
        car = new Car(policy.car); //call copy contructor
        numberOfClaims = policy.numberOfClaims;
        expiryDate = new MyDate(policy.expiryDate); //call copy contructor
    }
    
        public void setPolicyHolderName(String policyHolderName) throws NameException
    {
        if (!Pattern.matches("[A-Z][A-Za-z]{2,}[\\s][A-Z][A-Za-z]{2,}", policyHolderName)) // check name matches valid pattern
        {
            throw new NameException(); // user defined exception
        }
        else
        {
            this.policyHolderName = policyHolderName;
        }
    }
    
    //override clone method
    @Override
    @SuppressWarnings("CloneDeclaresCloneNotSupported")
    public InsurancePolicy clone()
    {
        InsurancePolicy output = null;
        try{
            output = (InsurancePolicy)super.clone();
            output.car = new Car(car);
            output.expiryDate = new MyDate(expiryDate);
        }
        catch(CloneNotSupportedException e)
        {
            System.out.print("Policy - Clone not supported error!");
        }
        
            
        return output;
    }
    
    public String getPolicyHolderName()
    {
        return policyHolderName;
    }
        public int getId() {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Car getCar()
    {
        return car;
    }

    public void setCar(Car car)
    {
        this.car = car;
    }

    public int getNumberOfClaims()
    {
        return numberOfClaims;
    }

    public void setNumberOfClaims(int numberOfClaims)
    {
        this.numberOfClaims = numberOfClaims;
    }

    public MyDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(MyDate expiryDate)
    {
        this.expiryDate = expiryDate;
    }
    
    public void print()
    {
        System.out.print("Policy Holder Name: "+policyHolderName+" ID: "+id+" Car: "+car+" Number Of Claims: "+numberOfClaims+" Expiry Date: "+expiryDate);
    }
    
    @Override
    public String toString()
    {
        return "Policy Holder Name: "+policyHolderName+" ID: "+id+" Car: "+car+" Number Of Claims: "+numberOfClaims+" Expiry Date: "+expiryDate;
    }
    
    public abstract double calcPayment(int flatRate);
    
    //accessors
//    public String getPolicyHolderName()
//    {
//        return policyHolderName;
//    }
//    
//    public int getId()
//    {
//        return id;
//    }
//    
//    public Car getCar()
//    {
//        return car;
//    }
//    
//    public int getNumberOfClaims()
//    {
//        return numberOfClaims;
//    }
//    
//    public MyDate getExpiryDate()
//    {
//        return expiryDate;
//    }
//    
//    //mutators
//    public void setPolicyHolderName(String policyHolderName)
//    {
//        this.policyHolderName = policyHolderName;
//    }
//    
//    public void setId(int id)
//    {
//        this.id = id;
//    }
//    
//    public void setCarModel(String model)
//    {
//        car.setModel(model);
//    }
//    
//    public void setCar(Car car)
//    {
//        this.car = car;
//    }
//    
//    public void setNumberOfClaims(int numberOfClaims)
//    {
//        this.numberOfClaims = numberOfClaims;
//    }
//    
//    public void setExpiryDate(MyDate expiryDate)
//    {
//        this.expiryDate = expiryDate;
//    }
    
    //prints list of policies
    
    //HASH MAP METHOD
//    public static void printPolicies(HashMap<Integer, InsurancePolicy> policies)
//    { 
//        for (InsurancePolicy policy : policies.values())
//            policy.print(); //print using print method
//    }
    
    //ARRAY LIST METHOD
//    public static void printPolicies(ArrayList<InsurancePolicy> policies)
//    { 
//        for (InsurancePolicy policy : policies)
//            policy.print(); //print using print method
//        System.out.println(); //add line space for better readability
//    }
    
    // uses LAMBDA expression with functional interface
    public static void printPolicies(ArrayList<InsurancePolicy> policies)
    {
        policies.forEach(x -> x.print()); // print each object using print meathod
    }
    
    // uses LAMBDA expression with functional interface
    public static void printPolicies(HashMap<Integer, InsurancePolicy> policies)
    { 
        policies.values().forEach(policy -> {policy.print();});
    }
    
    
    //overloading previous method to include premium costs
    
    //HASH MAP METHOD
    public static void printPolicies(HashMap<Integer, InsurancePolicy> policies, int flatRate)
    {
        //print third party prices and total
        double thirdPartyTotal = 0;
        for (InsurancePolicy policy : policies.values())
        {
            if (policy instanceof ThirdPartyPolicy)
            {
                thirdPartyTotal += policy.calcPayment(flatRate);
                policy.print();
                System.out.printf("Policy Price: $%.2f\n", policy.calcPayment(flatRate));
                System.out.println();
            }
        }
        if(thirdPartyTotal>0)
        {
            System.out.println("---------------------------------------");
            System.out.printf("Third Party Policy Total: $%.2f\n", thirdPartyTotal);
            System.out.println("---------------------------------------");
            System.out.println();
        }
        //print comprehensive prices and total
        double comprehensiveTotal = 0;
        for (InsurancePolicy policy : policies.values())
        {
            if (policy instanceof ComprehensivePolicy)//find Comprehensive policies in array list
            {
                comprehensiveTotal += policy.calcPayment(flatRate);
                policy.print();
                System.out.printf("Policy Price: $%.2f\n", policy.calcPayment(flatRate));
                System.out.println();
            }
        }
        if(comprehensiveTotal>0)
        {
            System.out.println("---------------------------------------");
            System.out.printf("Comprehensive Policy Total: $%.2f\n", comprehensiveTotal);
            System.out.println("---------------------------------------");
        }
    }
    
    //ARRAY LIST METHOD
    public static void printPolicies(ArrayList<InsurancePolicy> policies, int flatRate)
    {
        //print third party prices and total
        double thirdPartyTotal = 0;
        for (InsurancePolicy policy : policies)
        {
            if (policy instanceof ThirdPartyPolicy)
            {
                thirdPartyTotal += policy.calcPayment(flatRate);
                policy.print();
                System.out.printf("Policy Price: $%.2f\n", policy.calcPayment(flatRate));
                System.out.println();
            }
        }
        if(thirdPartyTotal>0)
        {
            System.out.println("---------------------------------------");
            System.out.printf("Third Party Policy Total: $%.2f\n", thirdPartyTotal);
            System.out.println("---------------------------------------");
            System.out.println();
        }
        //print comprehensive prices and total
        double comprehensiveTotal = 0;
        for (InsurancePolicy policy : policies)
        {
            if (policy instanceof ComprehensivePolicy)//find Comprehensive policies in array list
            {
                comprehensiveTotal += policy.calcPayment(flatRate);
                policy.print();
                System.out.printf("Policy Price: $%.2f\n", policy.calcPayment(flatRate));
                System.out.println();
            }
        }
        if(comprehensiveTotal>0)
        {
            System.out.println("---------------------------------------");
            System.out.printf("Comprehensive Policy Total: $%.2f\n", comprehensiveTotal);
            System.out.println("---------------------------------------");
        }
    }
    
    //ARRAY LIST METHOD
//    public static double calcTotalPayments(ArrayList<InsurancePolicy> policies, int flatRate)
//    {
//        double premiumTotal = 0; //initialise premium total variable
//        for (InsurancePolicy policy : policies)
//            premiumTotal += policy.calcPayment(flatRate); //calculate premium and add to total
//        return premiumTotal;
//    }

    // uses LAMBDA with functional interface
    public static double calcTotalPayments(ArrayList<InsurancePolicy> policies, int flatRate)
    {
        return policies.stream()
                .mapToDouble(x -> x.calcPayment(flatRate))
                .sum();
    }
    
    //HASH MAP METHOD
//    public static double calcTotalPayments(HashMap<Integer, InsurancePolicy> policies, int flatRate)
//    {
//        double premiumTotal = 0; //initialise premium total variable
//        for (InsurancePolicy policy : policies.values())
//            premiumTotal += policy.calcPayment(flatRate); //calculate premium and add to total
//        return premiumTotal;
//    }
    
    // uses LAMBDA / STREAMS
    public static double calcTotalPayments(HashMap<Integer, InsurancePolicy> policies, int flatRate)
    {
        return policies.values().stream()
                .mapToDouble(x -> x.calcPayment(flatRate))
                .sum();
    }
    
    //car price rise method
    public void carPriceRise(double risePercent)
    {
        car.setPrice(car.priceRise(risePercent));
    }
    
    //car price rise for all policies in array list
    
    //HASH MAP METHOD
    public static void carPriceRiseAll(HashMap<Integer, InsurancePolicy> policies, double risePercent)
    {
        for (InsurancePolicy policy : policies.values())
        {
            policy.carPriceRise(risePercent);
        }
    }
    
    //ARRAY LIST METHOD
//    public static void carPriceRiseAll(ArrayList<InsurancePolicy> policies, double risePercent)
//    {
//        for (InsurancePolicy policy : policies)
//        {
//            policy.carPriceRise(risePercent);
//        }
//    }
    
    // uses LAMBDA / STREAMS
    public static void carPriceRiseAll(ArrayList<InsurancePolicy> policies, double risePercent)
    {
        policies.forEach(policy -> {policy.carPriceRise(risePercent);});
    }
    
    //filter policies by car model into an array and return it
    
    //HASH MAP METHOD
    public static HashMap<Integer, InsurancePolicy> filterByCarModel(HashMap<Integer, InsurancePolicy> policies, String carModel)
    {
        HashMap<Integer, InsurancePolicy> filteredPolicies = new HashMap<>();
        policies.values().stream()
                .filter(policy -> (policy.car.getModel().contains(carModel)))
                .forEachOrdered(policy -> {filteredPolicies.put(policy.id, policy);
        });
        return filteredPolicies;
    }
    
    //ARRAY LIST METHOD
//    public static ArrayList<InsurancePolicy> filterByCarModel(ArrayList<InsurancePolicy> policies, String carModel)
//    {
//        ArrayList<InsurancePolicy> filteredPolicies = new ArrayList<>();
//        for (InsurancePolicy policy : policies)
//        {
//            if (policy.car.getModel().toLowerCase().contains(carModel.toLowerCase())) // ignores the user input by converting to lower case before sorting
//            {
//                filteredPolicies.add(policy);
//            }
//        }
//        return filteredPolicies;
//    }
    
    // uses LAMBDA / STREAMS
    public static ArrayList<InsurancePolicy> filterByCarModel(ArrayList<InsurancePolicy> policies, String carModel)
    {
        return (ArrayList<InsurancePolicy>)(policies.stream()
                .filter(x -> x.car.getModel().toLowerCase().contains(carModel))
                .collect(Collectors.toCollection(ArrayList::new)));
    }
    
    // filter policies by expiry date returning policies that are expired by the given date
    
    //HASH MAP METHOD
    public static HashMap<Integer, InsurancePolicy> filterByExpiryDate (HashMap<Integer, InsurancePolicy> policies, MyDate date)
    {
        HashMap<Integer, InsurancePolicy> filterByExpiryDate = new HashMap<>();
        for (InsurancePolicy policy : policies.values())
        {
            if (date.isExpired(policy.expiryDate))
            {
                filterByExpiryDate.put(policy.id, policy);
            }
        }
        return filterByExpiryDate;
    }
    
    //ARRAY METHOD LIST
//    public static ArrayList<InsurancePolicy> filterByExpiryDate (ArrayList<InsurancePolicy> policies, MyDate date)
//    {
//        ArrayList<InsurancePolicy> filterByExpiryDate = new ArrayList<>();
//        for (InsurancePolicy policy : policies)
//        {
//            if (!date.isExpired(policy.expiryDate))
//            {
//                filterByExpiryDate.add(policy);
//            }
//        }
//        return filterByExpiryDate;
//    }
    
    // uses LAMBDA / STREAMS
    public static ArrayList<InsurancePolicy> filterByExpiryDate (ArrayList<InsurancePolicy> policies, MyDate date)
    {
        return(ArrayList<InsurancePolicy>)policies.stream()
                .filter(policy -> (!date.isExpired(policy.expiryDate)))
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    // shallow copy method
//    public static ArrayList<InsurancePolicy> shallowCopy(ArrayList<InsurancePolicy> policies)
//    {
//        ArrayList<InsurancePolicy>shallowCopy = new ArrayList<>();
//        for (InsurancePolicy policy : policies) //Shallow copy 
//        {
//            shallowCopy.add(policy);
//        }
//        return shallowCopy;
//    }
    
    // uses LAMBDA / STREAMS
    public static ArrayList<InsurancePolicy> shallowCopy(ArrayList<InsurancePolicy> policies)
    {        
        return (ArrayList<InsurancePolicy>) policies.stream()
                .collect(Collectors.toList());           
    }
    
    // shallow copy method from Hash Map to Array List
    public static ArrayList< InsurancePolicy> shallowCopy(HashMap<Integer, InsurancePolicy> policies)
    {
        ArrayList<InsurancePolicy>shallowCopy = new ArrayList<>();
        for (InsurancePolicy policy : policies.values()) //Shallow copy 
        {
            shallowCopy.add(policy);
        }
        return shallowCopy;
    }
    
    // shallow copy method HASH MAP
//    public static HashMap<Integer, InsurancePolicy> shallowCopyHashMap(HashMap< Integer, InsurancePolicy> policies)
//    {
//        HashMap<Integer, InsurancePolicy>shallowCopy = new HashMap<>();
//        for (InsurancePolicy policy : policies.values()) //Shallow copy 
//        {
//            shallowCopy.put(policy.id, policy);
//        }
//        return shallowCopy;
//    }
    
    public static HashMap<Integer, InsurancePolicy> shallowCopyHashMap(HashMap< Integer, InsurancePolicy> policies)
    {
        HashMap<Integer, InsurancePolicy> shallowCopy = new HashMap<>();
        policies.values().stream()
                .forEachOrdered(policy -> {shallowCopy.put(policy.id, policy);});
        return shallowCopy;
    }
    
    // deep copy method
//    public static ArrayList<InsurancePolicy> deepCopy(ArrayList<InsurancePolicy> policies) throws CloneNotSupportedException
//    {    
//        ArrayList<InsurancePolicy>deepCopy = new ArrayList<>();
//        for (InsurancePolicy policy : policies) //Deep copy by using copy constructor
//        {
//            deepCopy.add(policy.clone());
//        }
//        return deepCopy;  
//    }
    
    // uses LAMBDA / STREAMS
    public static ArrayList<InsurancePolicy> deepCopy(ArrayList<InsurancePolicy> policies)
    {    
        return (ArrayList<InsurancePolicy>)policies.stream()
                .map(policy -> policy.clone())
                .collect(Collectors.toList());
    }
    
    // deep copy method from Hash Map to Array List
    public static ArrayList<InsurancePolicy> deepCopy(HashMap<Integer, InsurancePolicy> policies)
    {    
        ArrayList<InsurancePolicy>deepCopy = new ArrayList<>();
        for (InsurancePolicy policy : policies.values()) //Deep copy by using copy constructor
        {
            deepCopy.add(policy.clone());
        }
        return deepCopy;  
    }
    
    // deep copy method HASH MAP
    public static HashMap<Integer, InsurancePolicy> deepCopyHashMap(HashMap<Integer, InsurancePolicy> policies)
    {    
        HashMap<Integer, InsurancePolicy> deepCopy = new HashMap<>();
        for (InsurancePolicy policy : policies.values()) //Deep copy by using copy constructor
        {
            InsurancePolicy copyPolicy = policy.clone();
            deepCopy.put(copyPolicy.id, copyPolicy);
        }
        return deepCopy;  
    }
    
    // compare insurance policies based on expiry date
    @Override
    public int compareTo(InsurancePolicy other) //compare based on expiry date
    {
        return this.policyHolderName.compareTo(other.policyHolderName);
    }
    
    //----------------LAB 6--------------------------
    
    // read data from external file into Hash Map and return result (DESERIALIZATION)
    
    public static HashMap<Integer, InsurancePolicy> load(String fileName) throws IOException
    {
        ObjectInputStream inputStream = null; //declare input object to open file
        HashMap<Integer, InsurancePolicy> output = new HashMap<>();
        
        // attempt to open file 
        try
        {
            inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
        }
        catch (IOException ioException)
        {
            System.out.println("Error opening file.");
            System.exit(1);
        }
        
        // attempt to read from file and store in HashMap to return results
        try
        {
            while (true)
            {
                InsurancePolicy policy = (InsurancePolicy) inputStream.readObject();
                output.put(policy.id, policy);
            }
        }
        catch (EOFException endOfFile)
        {
            System.out.println("Policy Load Complete.\n");
        }
        catch (ClassNotFoundException classNotFound)
        {
            System.out.println("Invalid object type. Terminating.");
            System.exit(1);
        }
        catch (IOException inputOutputError)
        {
            System.out.println("Error reading from file. Terminating.");
            System.exit(1);
        }
        
        // close the file
        try
        {   
            if (inputStream!=null)
                inputStream.close();
        }
        catch (IOException inputOutputError)
        {
            System.out.println("Error closing file. Terminating.");
            System.exit(1);
        }
        
        return output;
    }
    
    // write data from Hash Map to external file (SERIALIZATION)
    public static boolean save(HashMap<Integer, InsurancePolicy> policies, String fileName) throws IOException
    {
        ObjectOutputStream outputStream = null; //declare output stream to open file
        boolean value = false;
        
        try //open file
        {
            outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
        }
        catch (IOException inputOutputX)
        {
            System.out.println("Error opening file. Terminating.");
            System.exit(1);
        }
        try // write to file
        {
            for (InsurancePolicy policy : policies.values())
            {
                outputStream.writeObject(policy);
            }
            value = true;
        }
        catch (IOException inputOutputX)
        {
            System.out.println("Error in writing to file. Terminating.");
            System.exit(1);
        }
        try //close file
        {
            if (outputStream!=null)
                outputStream.close();
        }
        catch (IOException inputOutputX)
        {
            System.out.println("Error closing file. Terminating.");
            System.exit(1);
        }
        return value;
    }
    //LAB 6 CODE---------------------------------
    
    public String toDelimitedString()
    {
        return policyHolderName + "," + id + "," + car.toDelimitedString() + "," + numberOfClaims + "," + expiryDate.toDelimitedString();
    }
    
    // load data from text file
    
    public static HashMap<Integer, InsurancePolicy> loadTextFile(String fileName) throws IOException, PolicyException, NameException
    {
        HashMap<Integer, InsurancePolicy> output = new HashMap<>();
        
        BufferedReader input = new BufferedReader(new FileReader(fileName)); // create object to read in data
        String line = input.readLine(); // read in first line
        while(line!=null)
        {
            line = line.trim(); // remove white space form either end of string
            String[] field = line.split(","); // split line into array using the commas
            switch (field[0])
            {
                case "TP": // if third party policy read in values
                    String policyHolderName = field[1];
                    int id = Integer.parseInt(field[2]);
                    CarType type = CarType.valueOf(field[3]);
                    String model = field[4];
                    int manufacturingYear = Integer.parseInt(field[5]);
                    double price = Double.parseDouble(field[6]);
                    int numberOfClaims = Integer.parseInt(field[7]);
                    int year = Integer.parseInt(field[8]);
                    int month = Integer.parseInt(field[9]);
                    int day = Integer.parseInt(field[10]);
                    String comments = field[11];
                    
                    Car car = new Car(type, model, manufacturingYear, price);
                    MyDate date = new MyDate(year, month, day);
                    
                    output.put(id, new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, date, comments));
                    break;
                case "CP": // if comprehensive policy read in values
                    policyHolderName = field[1];
                    id = Integer.parseInt(field[2]);
                    type = CarType.valueOf(field[3]);
                    model = field[4];
                    manufacturingYear = Integer.parseInt(field[5]);
                    price = Double.parseDouble(field[6]);
                    numberOfClaims = Integer.parseInt(field[7]);
                    year = Integer.parseInt(field[8]);
                    month = Integer.parseInt(field[9]);
                    day = Integer.parseInt(field[10]);
                    int driverAge = Integer.parseInt(field[11]);
                    int level = Integer.parseInt(field[12]);
                    
                    car = new Car(type, model, manufacturingYear, price);
                    date = new MyDate(year, month, day);
                    
                    output.put(id, new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, date, driverAge, level));
                    break;
            }
            line = input.readLine(); // read in next line
        }
        input.close();
        return output;
    }
    
    // save data to text file
    
    public static Boolean saveTextFile(HashMap<Integer, InsurancePolicy> policies, String fileName) throws IOException
    {
        BufferedWriter output = new BufferedWriter(new FileWriter(fileName)); // create object to write data to file
        
        for (InsurancePolicy policy : policies.values()) // go through policies in policies hash map
        {
            output.write(policy.toDelimitedString() + "\n"); // write each policy to text file using delimited string method
        }
        output.close();
        return true;
    }
    
}
