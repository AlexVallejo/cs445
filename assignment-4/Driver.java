   import java.io.*;

   class Driver{
   
      public static void main(String args[]){
         int[] ar = {-1,2,3};
         Tile b = new Tile("A",ar);
      
         System.out.println("Tile's label is: " + b.getName());
         System.out.println("Tile's integers are: ");
         for(int k=0; k<ar.length; k++)
            System.out.println(k + ":" + b.getValue(k) + " ");
      
         System.out.println("\nDisplay this tile and it's rotations:"); 
         for(int cnt=1; cnt<=3; cnt++){
            System.out.println(b);
            b = b.rotate();
         }
      
         System.out.println("Display this tile's reorientation for each rotation:");
         for(int cnt=1; cnt<=3; cnt++){   
            System.out.println("tile\n" + b);
            System.out.println("reorient\n" + b.reorient());
            b = b.rotate();
         } 
      }
   }

