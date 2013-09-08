   import java.io.*;
   import java.util.Scanner;
   import java.util.ArrayList;

   public class Election{
      public static void main(String[] args) throws IOException{
      
         final String VOTER_NAMES = "names.txt";
         final String VOTE_MATRIX = "votes.txt";
      
         ArraySortedList<Canidate> canidates = new ArraySortedList<Canidate>();
      
         File file = new File(VOTER_NAMES);
         Scanner input = new Scanner(file);
      
         int numCanidates = input.nextInt();
         input.nextLine();
      
         int position = 0;
         while(input.hasNext()){
            canidates.add(new Canidate(input.nextLine(),position));
            position++;
         }
      
         file = new File(VOTE_MATRIX);
         input = new Scanner(file);
      
         ArrayList<Canidate> storage = new ArrayList<Canidate>();
      
         int lineNumber = 0;
         while (input.hasNext()){
         
            while (!canidates.isEmpty())
               storage.add(canidates.remove());
         
            String line = input.nextLine();
            String[] votes = line.split(" ");
         
            while (!storage.isEmpty()){
               Canidate temp = storage.remove(0);
               temp.addVotes(Integer.parseInt(votes[temp.getPosition()]));
               canidates.add(temp);
            }
         
            lineNumber++;
            System.out.println("After precinct " + lineNumber + ", leader is " + canidates.peek() +":" + canidates.peek().getVotes());
         }
      
         Canidate temp;
         System.out.println();
         System.out.println("The top 3 vote getters are: ");
         for (int i = 0; i < 3; i++){
            temp = canidates.remove();
            System.out.println(i+1 + ". " + temp + ":" + temp.getVotes());
         }
      }
   }