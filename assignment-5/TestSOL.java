public class TestSOL{
    public static void main(String[] args){
        SelfOrganizableList<Integer> list = new SelfOrganizableList<Integer>();
        
        list.add(14);
        list.add(12);
        list.add(10);
        list.add(8);
        list.add(6);
        list.add(4);
        
        System.out.println(list);
        
        list.add(1,3);
        System.out.println("add 3 at position 1: " + list);
        list.add(3,5);
        System.out.println("add 5 at position 3: " +list);
        list.add(5,7);
        System.out.println("add 7 at position 5: " +list);
        list.add(9,13);
        System.out.println("add 13 at position 9: " +list);
        list.add(11,15);
        System.out.println("add 15 at position 11: " +list);
        list.add(8,11);
        System.out.println("add 11 at position 8: " +list);
        list.add(7,9);
        System.out.println("add 9 at position 7: " +list);
        
        System.out.println("add 200 at position 30 is legal: " + list.add(30,200));
        
        list.remove(1);
        System.out.println("Remove item at position 1: " + list);
        
        list.remove(12);
        System.out.println("Remove item at position 12: " + list);
        
        list.remove(12);
        System.out.println("Remove item at position 12: " + list);
        
        list.remove(6);
        System.out.println("Remove item at position 6: " + list);
        
        list.remove(9);
        System.out.println("Remove item at position 9: " + list);
        
        list.remove(2);
        System.out.println("Remove item at position 2: " + list);
        
        System.out.println("Remove item at position 25 is legal: " + list.remove(25));
        System.out.println("Size of the list: " + list.size());
    }
    
    
}