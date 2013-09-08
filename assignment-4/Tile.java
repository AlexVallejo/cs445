/*
* Tile.java
* 
* Alex Vallejo
* CS445
* Novemeber 27 2012
*
* This class represents a single triangular tile in the "Mickey Mouse" puzzle
* game: http://sharedreviews.com/review/mickeys-magic-triangle-puzzle
*
*/



   public class Tile{
   
      private String name;
      private int store[];
   
      Tile(String name, int data[]){
         this.store = data;
         this.name = name;
      }
   
   //"rotates" the triangle 120degs by performing a single right shift of the 
   //store array. a new Tile is returned representing the rotated tile
      public Tile rotate(){
         int rot[] = new int[3];
      
         rot[0] = store[2];
         rot[1] = store[0];
         rot[2] = store[1];
      
         Tile t = new Tile(name, rot);
         return t;
      } 
   
   //string representation of the tile. prints the value of each edge plus the
   //name of the tile
      public String toString(){
         String str = store[0] + "   " + store[1] + "\n" +
            "  " + name + "  \n" + 
            "  " + store[2] + " ";
      
         return str;
      }
   
   //string representation of the tile after a 60deg rotation.
      public String reorient(){
         String str = "  " + store[0] + "  \n" +
            "  " + name + "  \n" +
            store[2] + "   " + store[1];
      
         return str;
      }
   
   
   //getters
      public String getName(){
         return name;
      }
   
      public int getValue(int i){
         return store[i];
      }
   
   //tile comparison
      public boolean equals(Object other){
         return this.name.equals(((Tile)other).name);
      }
   }


