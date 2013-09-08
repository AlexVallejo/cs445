   import java.util.ArrayList;
   import java.util.Iterator;
   import java.util.NoSuchElementException;
   import java.util.Arrays;

   public class IArrayBSTree<T extends Comparable<? super T>> implements BSTInterface<T>, Iterable<T>{
      private T store[];                     //storage for the items in this ArrayBSTree
      private int links[][];                 //a link is an array index 0 through store.length
      private int length;                    //number of items in this ArrayBSTree
      private int root;                      //index of the root node of this BSTree
      private ArrayList<T> list;					//used for storing the list returned when countInstances is called
   
      private static final int NULL = -1;   //analogous to the null reference
   
   /**Initialize an empty BSTree.
     */
      public IArrayBSTree(){
         root = NULL;
         length = 0;
      
         store = (T[]) new Comparable[10];
      
         links = new int[10][2];
         for (int i = 0; i < 10; i++){
            links[i][0] = NULL;
            links[i][1] = NULL;
         }
      }
   
   /**Returns the number of nodes in this tree
     *@return number of nodes in this tree
     */
      public int numberOfNodes(){
         return length;
      }
   
   /**Return the root of this tree.
     *@return root the root of this tree.
     */
      public int root(){
         return root;
      }
   
   /**Add an item to this binary tree.
     *@param item item to be added to this tree.
     */
      public void add(T item){
      
         ensureCapacity();
      
         if (root == NULL){
            root++;
            store[length] = item;
         }
         
         else{
            store[length] = item;
            add(root);
         }
      
         length++;
      }
   
      private void add(int p){
      
         if (store[length].compareTo(store[p]) >= 0 ){
            if (links[p][1] == NULL)
               links[p][1] = length;
            
            else if (links[p][1] != NULL)
               add(links[p][1]);
         }
         
         else if (store[length].compareTo(store[p]) < 0 ){
            if (links[p][0] == NULL)
               links[p][0] = length;
            
            else if (links[p][0] != NULL)
               add(links[p][0]);
         }
      }
   
   /**Display this Array Based Binary Search Tree.
     *Displays the links and store arrays.
     */
      public void printTree(){
         System.out.println("root = " + root);
         System.out.println("length = " + length);
      
         System.out.println("\nlinks:");
         for(int k=0; k<length; k++){
            System.out.printf("%2d|%3d|%3d%n", k, links[k][0], links[k][1]);
         }
      
         System.out.println("\ndata:");
         for(int k=0; k<length; k++){
            System.out.println(k + " " + store[k]);
         }
      }
   
   /**Does this BSTree contain an item?
     *@param item the item to find in this ArrayTree.
     */
      public boolean contains(T item){
         if (root == NULL)
            return false;
      
         return contains(item,root);
      }
   
      private boolean contains(T item, int p){
         if (p == NULL)
            return false;
         
         else if (item.compareTo(store[p]) > 0)
            return contains(item, links[p][1]);
         
         else if (item.compareTo(store[p]) < 0)
            return contains(item, links[p][0]);
      
         return true;
      }
   
   /**Find an item equivalent to the maximum item
     *@return max the maximum item in this tree.
     */
      public T max(){
         return max(root);
      }
   
      private T max(int p){
         if (links[p][1] == NULL)
            return store[p];
         else
            return max(links[p][1]);
      }
   
   /**Find an item equivalent to the minimum item
     *@return min the minimum item in this tree.
     */
      public T min(){
         return min(root);
      }
   
      private T min(int p){
         if (links[p][0] == NULL)
            return store[p];
         
         else
            return min(links[p][0]);
      }
   
   
   /**Returns an ArrayList with all items equivalent to item.
     *@param item the item to be collected into the list
     *@return list the list containing the items equal item
     */
      public ArrayList<T> collectAllEqualTo(T item){
         list = new ArrayList<T>();
         return collectAllEqualTo(item,root);
      }
   
      private ArrayList<T> collectAllEqualTo(T item, int p){
      
         if (p == NULL)
            return list;
         
         else if (item.compareTo(store[p]) < 0)
            return collectAllEqualTo(item,links[p][0]);
         
         else if (item.compareTo(store[p]) > 0)
            return collectAllEqualTo(item,links[p][1]);
      	
         list.add(item);
         
         return collectAllEqualTo(item,links[p][1]);
      }
   
   
   /**Count the number of occurrences of an item in this ArrayBSTree.
     *@param item the item to be counted
     */
      public int countOccurrences(T item){ 
         return countOccurrences(item,root);
      }
   
      private int countOccurrences(T item, int p){
         if (p == NULL)
            return 0;
         
         else if (item.compareTo(store[p]) < 0)
            return countOccurrences(item,links[p][0]);
         
         else if (item.compareTo(store[p]) > 0)
            return countOccurrences(item,links[p][1]);
      
         return 1 + countOccurrences(item,links[p][1]);
      }
   
   /**Return an iterator for an ArrayBSTree back to the caller.
     *Objects are iterated through in the order they were added
     *to the tree.\n
     *NOTE: Does not support the remove operation
     */
      public Iterator<T> iterator(){
         return new BSTIterator();
      }
   
    //Make sure there is room in the array-space, if not, double the 
    //size of the array-space for both the store and links arrays
      private void ensureCapacity(){
         if (length == store.length){
            store = Arrays.copyOf(store,length*2);
         
            int[][] temp = new int[length*2][2];
         
            for (int i = 0; i < length; i++){
               temp[i][0] = links[i][0];
               temp[i][1] = links[i][1];
            }
         
            for (int i = length; i < length*2; i++){
               temp[i][0] = NULL;
               temp[i][1] = NULL;
            }
         
            links = temp;
         }
      }
      
   /**Finds the height of the tree.
     *@return height the height of this tree.
     */
      public int height(){
         if (length == 0)
            return 0;
      		
         return height(root);
      }
   
      private int height(int p){
         if (p != NULL)
            return 1 + Math.max(height(links[p][0]),height(links[p][1]));
      
         return 0;
      }
   
   
    //A private inner-class iterator for the ArrayBSTree class.
    //Iterates through this tree in the order that the items were added.
    //NOTE: does not support remove functionality.
      private class BSTIterator implements Iterator<T>{
         private int p = -1;
      
         public boolean hasNext() {
            if (p + 1 < length)
               return true;
            else
               return false;
         }
      
         public T next() {
            return store[++p];
         }
      
         public void remove() {
            throw new UnsupportedOperationException();
         }
      }
   }