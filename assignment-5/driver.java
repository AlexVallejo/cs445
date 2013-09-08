   import java.util.Scanner;
   import java.io.File;
   import java.io.IOException;
   
   public class driver{
   
      public static void main(String[] args) throws IOException{
      //List objects
         SelfOrganizableList<String> list = new SelfOrganizableList<String>();
         SelfOrganizableList<String> swaplist = new SelfOrganizableList<String>();
         SelfOrganizableList<String> mtflist = new SelfOrganizableList<String>();
         SelfOrganizableList<String> aclist = new SelfOrganizableList<String>();
         SelfOrganizableList<String> aclistCopy = new SelfOrganizableList<String>();
         
      	//Ints to keep track of the accesses
         int mtf = 0, stf = 0, acs = 0, linear = 0, lines = 0;
      	
      	//String array of the values
         String[] ar = {"Tootsie", "The Godfather", "The Dead Poet Society","One Flew Over the Cuckoo's Nest", "Midnight Cowboy", "Gladiator", "Ghost", "Forrest Gump", "Cast Away","Casablanca"};
      	
      	//Add ach string to each list
         for (String s : ar){
            list.add(s);
            swaplist.add(s);
            mtflist.add(s);
            aclist.add(s);
            aclistCopy.add(s);
         }
         
         System.out.println("Enter the filename: ");
      	
         Scanner keyboard = new Scanner(System.in);
         Scanner file = new Scanner(new File(keyboard.nextLine()));
         String line;
         System.out.println(list);
         while (file.hasNext()){
            line = file.nextLine();
            lines++;
            linear += list.searchElement(line);
            stf += swaplist.searchElementSwap(line);
            mtf += mtflist.searchElementMTF(line);
            acs += aclist.searchElementAccessCount(line);
            aclistCopy.searchElementAccessCount(line);
         }
         
         System.out.println("Lines processed: " + lines);
         System.out.println("\n------Frequency of Occurance-----");
         
         String item;
         while (!aclistCopy.isEmpty()){
            int ac = aclistCopy.getHighestAccessCount();
            item = aclistCopy.remove(1);
            System.out.println(String.format("%8d " + item,ac));
         }
         
         System.out.println("\naccesses in originial list: " + linear);
         System.out.println("accesses in Swap-Toward-Front strategy: " + stf);
         System.out.println("accesses in Move-To-Front strategy: " + mtf);
         System.out.println("accesses in Access-Count strategy: " + acs);
      
         System.out.println("\nOriginal List: " + list);
         System.out.println("Swap-Toward-Front list: " + swaplist);
         System.out.println("Move-To-Front list: " + mtflist);
         System.out.println("Access Count list: " + aclist.display());
      }
   }