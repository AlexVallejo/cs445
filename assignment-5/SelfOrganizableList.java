   public class SelfOrganizableList<T> implements SelfOrganizableListInterface<T>{
      private int size;
      private Node<T> firstNode;
   
      public SelfOrganizableList(){
         size = 0;
      }
   
      public boolean isEmpty(){
         return size == 0;
      }
   
      public int size(){
         return size;
      }
      
      public int getHighestAccessCount(){
         return firstNode.accessCount;
      }
   
      public boolean add(T item){
      
      //If the list is empty, add the new node
         if (size == 0){
            firstNode = new Node<T>(item);
            size++;
            return true;
         }
      
      	//Verify that the node doesnt already exist within the list
         Node<T> current = firstNode;
         for (int i = 1; i <= size; i++){
            if (current.data.equals(item))
               return false;
            current = current.next;
         }
      
         //Node is unique and will be added to the front of the list
      	//Establish the connections necessiary for the node to be the new first item
         Node<T> newNode = new Node<T>(item);
         newNode.next = firstNode;
         firstNode.prev = newNode;
         firstNode = newNode;
         size++;
         return true;
      }
   
   	//Position must be a positive integer > 0
      public boolean add(int position, T item){
      
         if (position > size + 1)
            return false;
            
      	//Node current holds the current node during the iteration
         Node<T> current = firstNode;
      
      	//Verify the node does not already exist within the list
         for (int i = 1; i <= size; i++){
            if (current.data.equals(item))
               return false;
            current = current.next;
         }
         
         if (position == 1){
            Node<T> newNode = new Node<T>(item);
            newNode.next = firstNode;
            firstNode.prev = newNode;
            firstNode = newNode;
            size++;
            return true;
         }
         
         current = firstNode;
         if (position == size + 1){
            for (int i = 1; i < size; i++)
               current = current.next;
               
            Node<T> newNode = new Node<T>(item);
            current.next = newNode;
            newNode.prev = current;
            size++;
            return true;
         }
      
         current = firstNode;
         //continue iterating until the end of the list
         for (int i = 1; i <= size; i++){
         
            if (i == position){
            
            	//Establish the connections required to insert the newNode at the required location
               Node<T> newNode = new Node<T>(item);
               //Establish the double connection between the node before the new node
               current.prev.next = newNode;
               newNode.prev = current.prev;
               //Establish the connection between the node after the new node
               newNode.next = current;
               current.prev = newNode;
               size++;
               return true;
            }
            current = current.next;
         }
      
         return false;
      }
   
   	//Iterate through the list. When the item is found return
   	//the number of iterations. or Return -1 as a flag
      public int searchElement(T item){
         int numIterations = 1;
         Node<T> current = firstNode;
      
         for (int i = 1; i <= size; i++){
            numIterations = i;
            if (current.data.equals(item))
               return numIterations;
            
            current=current.next;
         }
      
         return -1;
      }
   
   	//Serch the list and when the element is found increment
   	//the elements access count. Store the number of iterations
   	//neeed to reach the desired item in the "rc" variable.
   	//Then swap the accessed item to its
   	//proper place in the list. List is maintained in decending order based on access counts
      public int searchElementAccessCount(T item){
         Node<T> current = firstNode;
         int numIterations = -1;
      
      	//Move through the list until you find the item being serched for.
      	//When found, increment its accessCount and break. Leaving current
      	//pointing to the requested item
         for (int i = 1; i <= size; i++){
            if (current.data.equals(item)){
               numIterations = i;
               ++current.accessCount;
               break;
            }
            current=current.next;
         }
      
      	//Reuested item is not within the list, return -1 as a flag
         if (numIterations == -1)
            return numIterations;
      
      	//Requested item is the first list entry
      	//firstNode.accessCount has already been incremented in above block
         if (firstNode.data.equals(item))
            return numIterations;
      
      	//Find the first item that is less than the accessCount of the
      	//requested item. Then create a deep copy of this item in the
      	//new Node<T> temp
      	//SPECIAL CASE: if the item is already in proper order, no swaps are required.
         Node<T> position = firstNode;
         while (position.accessCount >= current.accessCount){
            if (position == current)
               return numIterations;
            position = position.next;
         }
      
         Node<T> temp = new Node<T>(position.data);
         temp.accessCount = position.accessCount;
      
      	//Swap the data and access count of position and current
      	//Which effectivly swaps the nodes WITHOUT worrying about
      	//modifying their respective prev and next references
         int tempAC = current.accessCount;
         T tempData = current.data;
         while (current != firstNode && tempAC > current.prev.accessCount){
            current.accessCount = current.prev.accessCount;
            current.data = (T) current.prev.data;
            current = current.prev;
         }
         
         current.accessCount = tempAC;
         current.data = tempData;
         
          
         return numIterations;
      }
   
      public int searchElementMTF(T item){
      
         Node<T> current = firstNode;
         int numIterations = -1;
      
      	//Move through the list until you find the item being serched for.
      	//When found --> break. Leaving current pointing to the requested item
         for (int i = 1; i <= size; i++){
            if (current.data.equals(item)){
               numIterations = i;
               break;
            }
            current=current.next;
         }
      
      	//Reuested item is not within the list, return -1 as a flag
         if (numIterations == -1)
            return numIterations;
      
      	//Requested item is already the first item, no need to Move To Front
         if (firstNode.data.equals(item))
            return numIterations;
            	
      	//Requested item is at the end of the list
         if (numIterations == size){
            current.prev.next = null;
            Node<T> temp = new Node<T>(current.data);
            temp.next = firstNode;
            firstNode.prev = temp;
            firstNode = temp;
            return numIterations;
         }
      
      	//Requested item is in the middle of the list!
      	//cut out the requested item and re do the adjacent nodes' references
      	//create a new node with the same data and put it as the new firstNode
         current.prev.next = current.next;
         current.next.prev = current.prev;
         Node<T> temp = new Node<T>(current.data);
         temp.next = firstNode;
         firstNode.prev = temp;
         firstNode = temp;
      
         return numIterations;
      }
   
     //
      public int searchElementSwap(T item){
         
         Node<T> current = firstNode;
         int numIterations = -1;
      
      	//Move through the list until you find the item being serched for.
      	//When found --> break. Leaving current pointing to the requested item
         for (int i = 1; i <= size; i++){
            if (current.data.equals(item)){
               numIterations = i;
               break;
            }
            current=current.next;
         }
      
      	//Reuested item is not within the list, return -1 as a flag
         if (numIterations == -1)
            return numIterations;
      
      	//Requested item is already the first item, no need to Move Towards Front
         if (firstNode.data.equals(item))
            return numIterations;
      
         T dataPiece = (T) current.prev.data;
         Node<T> temp = new Node<T>(dataPiece);
         current.prev.data = current.data;
         current.data = temp.data;
      
         return numIterations;
      }
   
      public T remove(int position){
         Node<T> current;
      
      	//If the index for removal is invalid return null
         if (position > size)
            return null;
      
      	//Special Case: if position equals 1, drop the first node
      	//by setting it equal to its next node
      	//Special Case: if the list size is 1, return the data and
      	//set the firstNode equal to null
         current = firstNode;
         if (position == 1){
            if (size == 1){
               T temp = firstNode.data;
               firstNode = null;
               size--;
               return temp;
            }
         
            T temp = firstNode.data;
            firstNode.next.prev = null;
            firstNode = firstNode.next;
            size--;
            return temp;
         }
      
      	//Special Case: if the item to be removed is the last item
      	//itererate through until it is reached then cut its connection
      	//to the second to last item in the list thus making the second
      	//to last item in the list the new last item
         if(position == size){
         
            for(int i = 1; i < size; i++)
               current = current.next;
         
            T temp = current.data;
            assert current.next == null; //Be sure we are really at the last item
            current.prev.next = null;
            size--;
            return temp;
         }
      
      	//If the node is found in the middle of the list, establish the
      	//connections necessiary to circumvent the node at "position"
      	//effectivly removing its reference and the node itself.
      	//Decrement the size, and return its data
         for (int i = 1; i <= size; i++){
            if (i == position){
               current.prev.next = current.next;
               current.next.prev = current.prev;
               size--;
               return current.data;
            }
            current = current.next;
         }
      
      	//If the item was not removed return null.
         return null;
      }
   
      public String display(){
      
         if (size == 0)
            return "[]";
      
         if (size == 1)
            return "[" + firstNode.data + " (" + firstNode.accessCount + ")]";
      
      	//Establish a StringBuffer to hold the contents of the list as it is iterated through
         StringBuffer result = new StringBuffer();
         Node<T> temp = firstNode;
         result.append("[" + temp.data + " (" + temp.accessCount + "), ");
      
      	//If the node is the last node in the list, omit the final comma.
         //Otherwise, print the comma to delimit the list
         while (temp.next != null){
            temp = temp.next;
            if (temp.next == null)
               result.append(temp.data + " (" + temp.accessCount + ")");
            else
               result.append(temp.data + " (" + temp.accessCount + "), ");
         }
         result.append("]");
      
         return result.toString();
      }
   
      public String toString(){
      
         if (size == 0)
            return "[]";
      
         if (size == 1)
            return "[" + firstNode.data + "]";
      
         //Establish a StringBuffer to hold the contents of the list as it is iterated through
         StringBuffer result = new StringBuffer();
         Node<T> temp = firstNode;
         result.append("[" + temp.data + ", ");
      
         //If the node is the last node in the list, omit the final comma.
         //Otherwise, print the comma to delimit the list
         while (temp.next != null){
            temp = temp.next;
            if (temp.next == null)
               result.append(temp.data);
            else
               result.append(temp.data + ", ");
         }
         result.append("]");
      
         return result.toString();
      }
   
      private class Node<T>{
         public Node prev;
         public Node next;
         public int accessCount;
         public T data;
      
         public Node(T item){
            this.data = item;
            this.prev = null;
            this.next = null;
            this.accessCount = 0;
         }
      }
   }