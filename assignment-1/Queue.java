   import java.util.Stack;
   import java.util.Iterator;

//A queue can be simulated by the actions of two stacks.
   public class Queue<T> implements QueueInterface<T>, Iterable<T>{
      private Stack<T> stack;  //auxiliary storage
      private Stack<T> queue;  //holds the elements in the queue
   
      public Queue(){
         queue = new Stack<T>();
         stack = new Stack<T>();
      }
   
      public boolean isEmpty(){
         return stack.empty();
      }
   
      public T dequeue(){
         if (stack.empty())
            return null;
      
         while(!stack.empty())
            queue.push(stack.pop());
      
         T returnItem = queue.pop();
      
         while(!queue.empty())
            stack.push(queue.pop());
      
         return returnItem;
      }
   
      public T front(){
         while(!stack.empty())
            queue.push(stack.pop());
      
         T retItem = queue.peek();
      
         while(!queue.empty())
            stack.push(queue.pop());
      
         return retItem;
      }
   
      public void enqueue(T entry){
         stack.push(entry);
      }
   
      public void clear(){
         stack.clear();
      }
   
      public String toString(){
         if (stack.empty())
            return "[]";
            
         return stack.toString();
      }
   
      public Iterator<T> iterator(){
         return stack.iterator();
      }
   }