   import java.util.Scanner;
   import java.io.*;
   import java.util.ArrayList;
   
   public class PhDCanidateDriver{
      public static void main(String[] args) throws IOException{
      
      
         IArrayBSTree<Double> numbers = new IArrayBSTree<Double>();
      	
         IArrayBSTree<PhDCandidate> people = new IArrayBSTree<PhDCandidate>();
         Scanner input = new Scanner(new File("prelimfall2012.txt"));
         
         PhDCandidate.setThreshold(input.nextDouble());
         PhDCandidate.setNumberOfExams(input.nextInt());
         PhDCandidate.setCandidates(input.nextInt());
           
         while (input.hasNext())
            people.add(new PhDCandidate(input));
         	
         System.out.println("Minimum passed exams: " + people.min().numberOfExamsAboveOrEqualThreshold());
         System.out.println("Maximum passed exams: " + people.max().numberOfExamsAboveOrEqualThreshold() + "\n");
         
         int i = 0;
         for (PhDCandidate s : people){
            i++;
            System.out.println(i + ". " + s.getName() + " passed " + s.numberOfExamsAboveOrEqualThreshold() + " exams");
         }
         
         ArrayList<PhDCandidate> passed = new ArrayList<PhDCandidate>();
         ArrayList<PhDCandidate> worst = new ArrayList<PhDCandidate>();
            
         for (PhDCandidate s : people){
            if (s.numberOfExamsAboveOrEqualThreshold() >= PhDCandidate.getNumberOfExams())
               passed.add(s);
            	
            else if (s.equals(people.min()))
               worst.add(s);
         }
         
         System.out.println("\nPassing candidate's performance summary\n---------------------------------------");
         for(PhDCandidate s : passed)
            System.out.println(s);
           
         System.out.println("\nWorst performers summary\n------------------------");
         for(PhDCandidate s : worst)
            System.out.println(s);
      }
   }