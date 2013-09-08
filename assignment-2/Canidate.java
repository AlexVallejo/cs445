   public class Canidate implements Comparable<Canidate>{ //why not <T>?!
   
      private String name;
      private int numVotes;
      private int position;
      private int positiveVotes;
   
      public Canidate(String newName){
         name = newName;
         numVotes = 0;
         position = 0;
         positiveVotes = 0;
      }
   
      public Canidate(String newName, int location){
         name = newName;
         position = location;
         numVotes = 0;
         positiveVotes = 0;
      }
   
      public String getName(){
         return name;
      }
   
      public int getPosition(){
         return position;
      }
   
      public int numVotes(){
         return numVotes;
      }
   
      public int getVotes(){
         return positiveVotes;
      }
   
      public void addVotes(int newVotes){
         numVotes -= newVotes;
         positiveVotes += newVotes;
      }
   
      public int compareTo(Canidate thatGuy){
         if (this.numVotes() < thatGuy.numVotes())
            return -1;
         
         else if (this.numVotes() > thatGuy.numVotes())
            return 1;
         
         else
            return 0;
      }
   
      public String toString(){
         return name;
      }
   }