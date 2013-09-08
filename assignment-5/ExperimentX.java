public class ExperimentX{
    public static void main(String[] args){
        SelfOrganizableList<BritishLength> list = new SelfOrganizableList<BritishLength>();
        SelfOrganizableList<BritishLength> swaplist = new SelfOrganizableList<BritishLength>();
        SelfOrganizableList<BritishLength> mtflist = new SelfOrganizableList<BritishLength>();
        SelfOrganizableList<BritishLength> aclist = new SelfOrganizableList<BritishLength>();
        
        BritishLength[] cr = {new BritishLength(50,0), new BritishLength(40,0), new BritishLength(30,0), new BritishLength(20,0), new BritishLength(10,0)};
        for(int k=0; k<cr.length; k++){
            list.add(cr[k]);
            aclist.add(cr[k]);
            swaplist.add(cr[k]);
            mtflist.add(cr[k]);
        }
        
        
        
        BritishLength[] ar = {cr[3], cr[2], cr[1], cr[2], cr[2], cr[0], cr[1], cr[2], cr[0], cr[4], cr[4], cr[3], cr[4], cr[3], cr[4], cr[4]};
      
        System.out.println("\n\nArrangement by Move-to-Front Stategy\n" + mtflist);       
        for(BritishLength x : ar){
            System.out.println("searching for: " + x);
            mtflist.searchElementMTF(x);
            System.out.println(mtflist);
        }
        
        
        System.out.println("\n\nArrangement by Swap-toward-Front Stategy\n" + swaplist);       
        for(BritishLength x : ar){
            System.out.println("searching for: " + x);
            swaplist.searchElementSwap(x);
            System.out.println(swaplist);
        }
        
        
        System.out.println("\n\nArrangement by Access-Count Strategy\n" + aclist.display());        
        for(BritishLength x : ar){
            System.out.println("searching for: " + x);
            aclist.searchElementAccessCount(x);
            System.out.println(aclist.display());
        }
    }
}