import java.util.Scanner;

public class PintsToGallons {
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      // Modify the following code
      double volPints;
      double volGals;
   
      volPints = scnr.nextDouble();
   
      volGals = volPints / 8.0;

   
      System.out.printf("%.3f gallons\n", volGals);
   }
}