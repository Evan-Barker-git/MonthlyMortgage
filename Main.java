// Begin to Perform actual operations
public class Main {
   public static void main(String[] args) {
       // Create instance of builder
       Mortgage.MortgageBuilder builder = new Mortgage.MortgageBuilder();
           builder.setInterestRate();
           builder.setHomeCost();
           builder.setDownPayment();

        // Use data gathered to make the mortgage object and calculate
       Mortgage myMortgage = builder.build();
       long myMonthlyRate = Math.round(myMortgage.calculateMonthly()) ;
       
       // Check if number is zero or below. Then notify user that the numbers are off.
       if (myMonthlyRate <= 0) {
           System.out.println("My Monthly rate is: "+ myMonthlyRate );
           System.out.println("That doesn't look right. Let's try running that again and check our numbers.");
       } else {
           System.out.println("My Monthly rate is: "+ myMonthlyRate );
       }
   }
}
