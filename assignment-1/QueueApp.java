   import java.util.Random;
   
   public class QueueApp{
      public static void main(String[] args){
      
      	//Initialize all variables
         int numStrings = Integer.parseInt(args[0]);
         int length = Integer.parseInt(args[1]);
         String supress = args[2];
         String[] keys = new String[numStrings];
         Random random = new Random(25);
         
      	//Fill the array with the number and length specified
         for (int i = 0; i < numStrings; i++){
            String entry = "";
            
            for (int j = 0; j < length; j++){
               char a = 65;
               a += random.nextInt(25);
               entry += a;
            }
            keys[i] = entry;
         }
         
      	//Create the radix sort object
         ARadixSort rs = new ARadixSort(keys);
         
      	//If the user selects to not supress output
         if (supress == "n"){
            for (int i = length; i >= 0; i--){
               rs.distribute(i);
               rs.collect();
               rs.display();
            }
         }
         
      	//If the user selects to display output
         if (supress == "y"){
            for (int i = length; i >= 0; i--){
               rs.distribute(i);
               rs.collect();
            }
         }
         
      	//Display the sorted array
         rs.fdisplay();
      }
   }