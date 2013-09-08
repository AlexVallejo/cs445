   import java.util.ArrayList;

   public class IBSTDriver{
      public static void main(String[] args){
         IArrayBSTree<String> bst = new IArrayBSTree<String>();
        
         bst.add("Bill");  //same entries as animation
         bst.add("Mel");
         bst.add("Ron");
         bst.add("Ben");
         bst.add("Pete");
         bst.add("Amy");
         bst.add("Bess");
        
         bst.printTree();
        
         System.out.println("\nDoes Bess belong? " + bst.contains("Bess"));
         System.out.println("Does George belong? " + bst.contains("George"));
        
         System.out.println("\nWho is the minimum? " + bst.min());
         System.out.println("Who is the maximum? " + bst.max());
        
         System.out.println("\nHow many occurrences of Mel? " + bst.countOccurrences("Mel"));
         System.out.println("Collect all occurrences of Mel? " + bst.collectAllEqualTo("Mel"));
        
        //Add some duplicates
         bst.add("Mel");
         bst.add("Ron");
         bst.add("Amy");
         bst.add("Mel");
        
         System.out.println("\nHow many occurrences of Mel? " + bst.countOccurrences("Mel"));
         System.out.println("Collect all occurrences of Mel? " + bst.collectAllEqualTo("Mel") + "\n");
        
         bst.printTree(); 
         System.out.println();
        
         ArrayList<String> list = new ArrayList<String>();
        
        //bst is Iterable
         for(String s : bst){
            if(!list.contains(s)){
               list.add(s);
               System.out.println("How many occurrences of " + s + "? " + bst.countOccurrences(s));
            }
         }
        
        
        //Add some items to exceed initial array-space size
         bst.add("Aly");
         bst.add("Mandy");
         bst.add("Paul");
         bst.add("George");
         bst.add("Bette");
        
         System.out.println();
        
         bst.printTree(); 
         System.out.println();
      }
   }