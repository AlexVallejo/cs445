   import java.util.Arrays;

   public class ArraySortedList<T extends Comparable<? super T>> implements PriorityQueueInterface<T>{
      private T[] entry;
      private int numEntries;
   
      public ArraySortedList(){
         entry = (T[]) new Comparable[10];
         numEntries = 0;
      }
   
      public void add(T newEntry){
         ensureCapacity();
         entry[numEntries] = newEntry;
      	
         if (numEntries > 0){
            for (int i = 0; i < numEntries; i++){
               if (entry[numEntries - i].compareTo(entry[numEntries - 1 - i]) > 0){
                  T temp = entry[numEntries - i];
                  entry[numEntries - i] = entry[numEntries - 1 - i];
                  entry[numEntries - 1 - i] = temp;
               }
               else //If the added item is before the item before it in the array, break to avoid unnecessiary comparisons.
                  break;
            }
         }
         numEntries++;
      }
   
      public void clear(){
         numEntries = 0;
      }
   
      public T remove(){
         if (numEntries == 0)
            return null;
      	
         numEntries--;
         T temp = entry[numEntries];
         entry[numEntries] = null;
         return temp;
      }
   
      public T peek(){
         if (numEntries == 0)
            return null;
            
         return entry[numEntries - 1];
      }
   
      public int getSize(){
         return numEntries;
      }
   
      public boolean isEmpty(){
         if (entry[0] != null)
            return false;
         
         return true;
      }
   
      public String toString(){
         if (numEntries == 0)
            return "array = []\n";
            
         String retString = "array = [" + entry[0];
         
         for (int i = 1; i < numEntries; i++)
            retString += ", " + entry[i];
      	
         return retString + "]\n";
      }
      
      private void ensureCapacity(){
         if (entry.length == numEntries)
            entry = Arrays.copyOf(entry, numEntries * 2);
      }
   }