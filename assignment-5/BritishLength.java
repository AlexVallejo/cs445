/** @author George Novacky
  * @version 1.1
  */
   import java.math.*;
   import java.io.*;
   public class BritishLength{
      private int feet;           //1. component feet (non-static variable - means every instance has a feet component)
      private int inches;         //2. component inches (non-static variable - means every instace has an inches component)
   
   
   /** Constructs a new BritishLength and assigns values to its number of feet and inches.
     * @param ft number of feet
     * @param inc number of inches
     */
      public BritishLength(int ft, int inc){ //constructor
         int totalInches;
      
         feet = ft;
         inches = inc;
         totalInches = convertToInches();
         feet = totalInches / 12;
         inches = totalInches % 12;
      }
   
   
      public boolean equals(Object other){
         if(other instanceof BritishLength){
            if(this.feet == ((BritishLength)other).feet && this.inches == ((BritishLength)other).inches)
               return true;
            else
               return false;
         }
         else
            return false;
      }
   
      public String toString(){ //makes BritishLength displayable
         return feet + "'" + inches + "\"";
      }
   
   
      public int convertToInches(){  //non-static method is a message intended to be sent to a BritishLength like 5'9"
         return 12*feet + inches;
      }
   
   
   /** Adds this BritishLength with the other BritishLength and returns their sum to the caller.
     * @param other the BritishLength that is combined with this BritishLength
     * @return the sum of this and other BritishLengths
     */
      public BritishLength add(BritishLength other){
         return new BritishLength(this.feet + other.feet,
                             this.inches + other.inches);
      }
   
   
      public int feet(){  //getter or accessor method
         return feet;
      }
   
   
      public void setFeet(int ft){ //setter or mutator method
         feet = ft;
      }
   
   
   
   }