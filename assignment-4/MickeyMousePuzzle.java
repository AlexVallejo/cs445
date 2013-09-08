/*
* MickeyMousePuzzle.java
*
* Alex Vallejo
* CS445
* 27 November 2012
*
* This is the main class that plays and solves the Mickey Mouse puzzle game.
* The code uses backtracking to enumerate over all combinations of the tiles
* and keeps those that are valid board configurations. The filename containing
* the tile settings is loaded in as an argument:
*
* java MickeyMousePuzzle puzzle1.txt 1
*
* The second argument is a 0 or 1 determining if the filter should be used to 
* remove redundant solutions (1: yes).
*
*/


   import java.util.*;
   import java.lang.*;
   import java.io.*;

   public class MickeyMousePuzzle{
      private LinkedSet<Tile> board;   //board[j] is the j-th item  
      private Tile[] partial; 
      private int configs;             //number of solutions found
      private int N;                   //number of tiles in solution
      private Tile[] possibles;        //a list of all posible tiles 27 total
   
   //extra data structures used to look for redundant solutions
      private ArrayList<String> all_solutions;
      private boolean use_filter;
   
   //constructor. the constructor handles reading in the given text file
   //and creating all 27 possible tiles (9 tiles + their rotations)
      public MickeyMousePuzzle(int n, String filename, boolean filter){
         N = n;
         board = new LinkedSet<Tile>();
         configs = 0;
         possibles = new Tile[27];
         partial = new Tile[27];
         use_filter = filter;
      
      //process the tile file
         try{
            Scanner s = new Scanner(new File(filename)).useDelimiter(" ");
            String name, line;
         
         //skip the first line
            s.nextLine();
         
            int j = 0;
            for(int i = 0; i < N; i++){
               int sides[] = new int[3];
               line = s.nextLine();
               String data[] = line.split(" ");
            
            //read each line
               name = data[0];
               sides[0] = Integer.parseInt(data[1]);
               sides[1] = Integer.parseInt(data[2]);
               sides[2] = Integer.parseInt(data[3]);
            
            //create the original triangle and its rotations
               Tile original = new Tile(name, sides);
               possibles[j] = original;
               possibles[j+1] = possibles[j].rotate();
               possibles[j+2] = possibles[j+1].rotate();
               j+=3;
            }
         }
            catch(FileNotFoundException e){
               e.printStackTrace();
            }
      
         all_solutions = new ArrayList<String>(); 
      }
   
   //begin the recursive backtracking code to find all solutions to the puzzle
      public void solve(){
         System.out.println(N + "-Permutations");
         tryToPlaceItem(0); //place the first tile in position 0
      
         System.out.println("There are no more configurations");
         System.out.println("Configurations = " + configs);
      }
   
   
   //goes through the list of possible tiles and checks which tiles can
   //be placed into position c. (this is recursive).
      private void tryToPlaceItem(int c){
         for(int k=0; k<3*N; k++){
            if( !board.contains(possibles[k]) && acceptable(k,c)){
               board.add(possibles[k]);
               partial[c] = possibles[k];
            
            //when c == 8 that means all pieces have been placed. check for repeats
            //if that option is set
               if(c==8){
               // convert board to a single string and check if it's a repeat
                  String s = boardToString(partial); 
                  if( !use_filter || !isRepeat(s)){
                     all_solutions.add(s);          
                     printSolution();
                     configs++;
                  }
               }
               else{
               //recursive call to try the next position in the board
                  tryToPlaceItem(c+1);
               }
               board.remove(possibles[k]);
               partial[c] = null;
            }
         }
      }
   
   //checks to see if tile possibles[k] is a legal for position c of the board
   //a tile is legal if the sum of its edges and the edges it touches sum to 0
      private boolean acceptable(int k, int c){
         LinkedSet temp = new LinkedSet<Tile>();
         temp.addAll(board);
         temp.add(possibles[k]);
      
         if(c == 0 || c == 1 || c == 4)        //these positions require no comparisons
            return true;
         else if(c == 3 || c == 6 || c == 8)   //require 1 comparison
            return ((possibles[k].getValue(0) + partial[c-1].getValue(0)) == 0 );
         else if(c == 5 || c == 7)             //require 2 comparisons
            return ((possibles[k].getValue(1) + partial[c-1].getValue(1)) == 0) &&
               ((possibles[k].getValue(2) + partial[c-4].getValue(2) ) == 0);
         else if(c == 2 )                      //require 2 different comparisons
            return ((possibles[k].getValue(1) + partial[c-1].getValue(1)) == 0) &&
               ((possibles[k].getValue(2) + partial[c-2].getValue(2) ) == 0);
      
         return false;
      }
   
   //prints the solution (the board). I have this disabled
      public void printSolution(){
         Iterator<Tile> itr = board.iterator();
         Tile item;
      
         while(itr.hasNext()){
            item  = itr.next();
            System.out.println(item);
         }
         System.out.println();
      }
   
   //creates the string representation of the board. eg: ABCDEFHIJ
   //where the first letter is position 0 and the last letter is position 8
      public String boardToString(Tile all[]){
         String s = "";
         for(int i = 0; i < N; i++)
            s += all[i].getName();
         return s;
      }
   
   //checks if the given string representation of a board is a rotation of
   //a previous board solution. this is done by checking for string rotations.
   //This strategy is from week 14 recication slides
      public boolean isRepeat(String a){
         String agrp1, agrp2, agrp3; //the three letter groups of the given board
         String grp1, grp2, grp3;    //the letter groups of a previous solution
      
         agrp1 = a.substring(0, 1) + a.substring(4, 5) + a.substring(8, 9); 
         agrp2 = a.substring(1, 2) + a.substring(3, 4) + a.substring(6, 7); 
         agrp3 = a.substring(2, 3) + a.substring(5, 6) + a.substring(7, 8); 
      
         for(String s : all_solutions){
            grp1 = s.substring(0, 1) + s.substring(4, 5) + s.substring(8, 9); 
            grp2 = s.substring(1, 2) + s.substring(3, 4) + s.substring(6, 7); 
            grp3 = s.substring(2, 3) + s.substring(5, 6) + s.substring(7, 8); 
         
         //now for the checking
            int i1 = (grp1 + grp1).indexOf(agrp1);
            int i2 = (grp3 + grp3).indexOf(agrp3);
            int i3 = (grp2 + grp2).indexOf(agrp2);
         
            if(i1 == i2 && Math.abs(i2 - i3) == 1)
               return true;
         //else continue checking
         
         }
         return false;
      }
   
   //execution starts here
      public static void main(String[] args){
         String filename = "";
         boolean filter;
         if(args.length == 2){
            filename = args[0];
            filter = (args[1].equals("1")) ? true : false;
         }
         else{
            System.out.println("Usage: java MickeyMousePuzzle <path_to_puzzle_file> <{0, 1}>");
            return;
         }
        
         MickeyMousePuzzle perm = new MickeyMousePuzzle(9, filename, filter);
         perm.solve();
      }
   }