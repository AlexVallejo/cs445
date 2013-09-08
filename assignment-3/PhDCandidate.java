   import java.util.Scanner;

   public class PhDCandidate implements Comparable<PhDCandidate>{
      private static double threshold;   //a passing score is threshold or higher
      private static int numberOfExams;  //number of exams requiring a passing score
      private static int candidates;     //number of candidates taking the preliminary exams
    
      private String name;
      private Exam[] preliminaryExams;
    
      public static void setThreshold(double t){
         threshold = t;
      }
    
    
      public static void setNumberOfExams(int n){
         numberOfExams = n;
      }
    
    
      public static void setCandidates(int c){
         candidates = c;
      }
    
    
    
      public static double getThreshold(){
         return threshold;
      }
    
    
      public static int getNumberOfExams(){
         return numberOfExams;
      }
    
    
      public static int getCandidates(){
         return candidates;
      }
    
    
    
      public boolean equals(PhDCandidate other){
         return this.numberOfExamsAboveOrEqualThreshold() == other.numberOfExamsAboveOrEqualThreshold();
      }
    
      public PhDCandidate(Scanner stdin){
         preliminaryExams = new Exam[numberOfExams];
         
         stdin.nextLine();//consume the remaining line
         name = stdin.nextLine();
         
        
         for (int i = 0; i < numberOfExams; i++){
            if (i != 0)
               stdin.nextLine();
               
            preliminaryExams[i] = new Exam(stdin.nextLine(),stdin.nextDouble());
         }
      }
    
    
      public String getName(){
         return name;
      }
    
    
      public int numberOfExamsAboveOrEqualThreshold(){
         int n = 0;
         
         for (int i = 0; i < numberOfExams; i++){
            if (preliminaryExams[i].score >= threshold)
               n++;
         } 
         
         return n;
      }
    
    
      public String toString(){
         String output = new String(name) + "\n";
         
         for (int i = 0; i < numberOfExams; i++)
            output += preliminaryExams[i].toString() + "\n";
         
         return output;
      }
    
   
    
      public int compareTo(PhDCandidate other){
         if (this.numberOfExamsAboveOrEqualThreshold() > other.numberOfExamsAboveOrEqualThreshold())
            return 1;
            
         else if (this.numberOfExamsAboveOrEqualThreshold() < other.numberOfExamsAboveOrEqualThreshold())
            return -1;
         
         else
            return 0;
      }
    
   
    
      private class Exam{
         private String course;
         private double score;
      
         public Exam(String course, double score){
            this.course = course;
            this.score = score;
         }
      
      
         public String toString(){
            return String.format("%-25s: %1.2f", course, score);
         }
      }
   }