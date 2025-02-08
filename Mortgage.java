
// Need to import this for user input functionality 
import java.util.Scanner;

// The main object to create that handles mortgage data and processing
public class Mortgage {
   // Declare Variables Used
   public float interestRate ;
   public int homeCost ;
   public int downPayment;


   // Private Constructor Enforces use of Builder
   private Mortgage(MortgageBuilder builder){
       this.interestRate = builder.interestRate ;
       this.homeCost = builder.homeCost ;
       this.downPayment = builder.downPayment ;
   }
   // MortgageBuilder class - Builder Constructor
   public static class MortgageBuilder {
       private float interestRate ;
       private int homeCost ;
       private int downPayment ;
       private static Scanner userPrompt = new Scanner(System.in);

        // Grabs interest rate from user
       public MortgageBuilder setInterestRate(){
           while (true) {
               System.out.println("Please enter the interest rate as X.XX:\n7.5% rate would be 7.5.");
               // Gets input as a String instead of float so we can validate the data
               // String instead of Float and nextline instead of nextFloat
               // Use built in method .trim on the string Object we create from the .nextLine method
               String input = userPrompt.nextLine().trim();


               try {
                   // Take the String object we created from user input and parse it for a float value.
                   float rate = Float.parseFloat(input);
                   if (rate >= 0) {
                       System.out.println("Your rate is " + rate + "." );
                       this.interestRate = rate;
                       break;
                   } else {
                       System.out.println("An Interest Rate cannot be negative. Please Redo.");
                   }
               } catch (Exception e) {
                   System.out.println("Error in your input. Please only enter data as a number.\n7.5% would be 7.5");
               }
           }
           return this;
       }

        // Gets home cost from user
       public MortgageBuilder setHomeCost(){
           while (true) {
               System.out.println("Please enter the home cost as XXXXXX:\n$750,000 home would be 750000.");
               // Gets input as a String instead of int so we can validate the data
               // String instead of Integer and nextline instead of nextInt
               // Use built in method .trim on the string Object we create from the .nextLine method
               String input = userPrompt.nextLine().trim();


               try {
                   // Take the String object we created from user input and parse it for a int value.
                   int cost = Integer.parseInt(input);
                   if (cost >= 0) {
                       System.out.println("Your prospective home is " + cost + "." );
                       this.homeCost = cost;
                       break;
                   } else {
                       System.out.println("Home cost cannot be negative. Please Redo.");
                   }
               } catch (Exception e) {
                   System.out.println("Error in your input. Please only enter data as a number.\n");
                   System.out.print("$750,000 home would be 750000.");
               }
           }
           return this;
       }

        // Gets down payment for user
       public MortgageBuilder setDownPayment(){
           while (true) {
               System.out.println("Please enter the down payment as XXXXX:\n$50,000 down payment would be 50000.");
               // Gets input as a String instead of int so we can validate the data
               // String instead of Integer and nextline instead of nextInt
               // Use built in method .trim on the string Object we create from the .nextLine method
               String input = userPrompt.nextLine().trim();


               try {
                   // Take the String object we created from user input and parse it for a int value.
                   int down = Integer.parseInt(input);
                   if (down >= 0) {
                       System.out.println("Your down payment is " + down + "." );
                       this.downPayment = down;
                       break;
                   } else {
                       System.out.println("Down payment cannot be negative. Please Redo.");
                   }
               } catch (Exception e) {
                   System.out.println("Error in your input. Please only enter data as a number.\n");
                   System.out.print("$70,000 down payment would be 70000.");
               }
           }
           return this;
       }


       // Build the Mortgage Object
       public Mortgage build() {
           return new Mortgage(this);
       }
   }

    // Perform the calcuations to determine monthly cost
   public double calculateMonthly(){
       System.out.println("Calculating your Monthly Payment...");
       // Turns Human readable percent into decimal for math
       double realRate = this.interestRate * .01;
       double monthlyRate = realRate / 12;


       System.out.println("Your monthly rate of interest is: " + monthlyRate);


       // 360 is all payments made in 30 year loan
       // r(1+r)^n
       double weightedRate = monthlyRate * Math.pow( 1 + monthlyRate, 360);
       // (1+r)^n -1
       double loanReduction = Math.pow( (1 + monthlyRate), 360) - 1 ;
       // Put it all together with principal for monthly payment
       System.out.println("Loan reduction: " + loanReduction + ". Weighted Rate: " + weightedRate);
       return (this.homeCost - this.downPayment) * ( weightedRate / loanReduction );
   }
}