   import java.util.ArrayList;

   public interface BSTInterface<T extends Comparable<? super T>>{
   //Return nodes in tree
      public int numberOfNodes();
    
   //add an item to this BST
      public void add(T item); //must be implemented recursively 
   
   //Return the root
      public int root();
   
   //Determine whether or not something equivalent to item is in this BST
      public boolean contains(T item); //must be implemented recursively
   
   //Determine a maximum item in this BST
      public T max(); //must be implemented recursively
   
   //Determine a minimum item in this BST
      public T min(); //must be implemented recursively
   
   //Determine the number of items equivalent to item
      public int countOccurrences(T item);
   
   //Collect all items equivalent to item in an ArrayList
      public ArrayList<T> collectAllEqualTo(T item);
   }