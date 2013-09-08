   public class SortedListDriver{
      public static void main(String[] args){
         ArraySortedList<String> list = new ArraySortedList<String>();
        
         System.out.println(list);
         System.out.println("size is " + list.getSize());
         System.out.println("is list empty? " + list.isEmpty());
        
         String[] names = {"Nick", "Sela", "Milt", "Anne", "Jill", "Bob", "Jim", "Pam", "Cal", "Avery", "Edy", "Mel"};
        
         for(String x : names){
            System.out.println("add: " + x);
            list.add(x);
         }
        
         System.out.println(list);
         System.out.println("size is " + list.getSize());
         System.out.println("is list empty? " + list.isEmpty());
        
         for(int k=0; k<3; k++){
            System.out.println("min item is " + list.peek());
            list.remove();
            System.out.println("remove it");
         }
        
         System.out.println(list);
         System.out.println("size is " + list.getSize());
        
         list.add("Mike");
         System.out.println("add: Mike");
         System.out.println("size is " + list.getSize());
         System.out.println(list);
        
         System.out.println("clearing list ...");
         list.clear();
         System.out.println("size is " + list.getSize());
         System.out.println(list);
      }
   }