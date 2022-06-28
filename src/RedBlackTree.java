
 import java.util.Scanner;
 

 class RedBlackPoint
 {    
     RedBlackPoint left, right;
     int element;
     int colour;
 
   
     public RedBlackPoint(int theValue)
     {
         this( theValue, null, null );
     } 
   
     public RedBlackPoint(int theValue, RedBlackPoint lVal, RedBlackPoint rVal)
     {
         left = lVal;
         right = rVal;
         element = theValue;
         colour = 1;
     }    
 }
 

 class RedBlackTree
 {
     private RedBlackPoint present;
     private RedBlackPoint parent;
     private RedBlackPoint major;
     private RedBlackPoint Max;
     private RedBlackPoint header;    
     private static RedBlackPoint nullPoint;
     
     static 
     {
    	 nullPoint = new RedBlackPoint(0);
    	 nullPoint.left = nullPoint;
    	 nullPoint.right = nullPoint;
     }
     
     static final int BLACK = 1;    
     static final int RED   = 0;
 
     
     public RedBlackTree(int noInf)
     {
         header = new RedBlackPoint(noInf);
         header.left = nullPoint;
         header.right = nullPoint;
     }
    
     public boolean isEmpty()
     {
         return header.right == nullPoint;
     }
    
     public void makeTreeEmpty()
     {
         header.right = nullPoint;
     }
    
     public void insert(int object )
     {
    	 present = parent = major = header;
    	 nullPoint.element = object;
         while (present.element != object)
         {            
        	 Max = major; 
             major = parent; 
             parent = present;
             present = object < present.element ? present.left : present.right;
                       
             if (present.left.colour == RED && present.right.colour == RED)
            	 changeColour( object );
         }
       
         if (present != nullPoint)
             return;
         present = new RedBlackPoint(object, nullPoint, nullPoint);
      
         if (object < parent.element)
             parent.left = present;
         else
             parent.right = present;        
         changeColour( object );
     }
     private void changeColour(int object)
     {
         
    	 present.colour = RED;
    	 present.left.colour = BLACK;
    	 present.right.colour = BLACK;
 
         if (parent.colour == RED)   
         {
            
        	 major.colour = RED;
             if (object < major.element != object < parent.element)
                 parent = rotate( object, major );  
             present = rotate(object, Max );
             present.colour = BLACK;
         }
        
         header.right.colour = BLACK; 
     }      
     private RedBlackPoint rotate(int object, RedBlackPoint parent)
     {
         if(object < parent.element)
             return parent.left = object < parent.left.element ? rotateWithLeftChild(parent.left) : rotateWithRightChild(parent.left) ;  
         else
             return parent.right = object < parent.right.element ? rotateWithLeftChild(parent.right) : rotateWithRightChild(parent.right);  
     }
    
     private RedBlackPoint rotateWithLeftChild(RedBlackPoint c2)
     {
    	 RedBlackPoint c1 = c2.left;
         c2.left = c1.right;
         c1.right = c2;
         return c1;
     }
    
     private RedBlackPoint rotateWithRightChild(RedBlackPoint c1)
     {
    	 RedBlackPoint c2 = c1.right;
         c1.right = c2.left;
         c2.left = c1;
         return c2;
     }
    
     public int countPoints()
     {
         return countPoints(header.right);
     }
     private int countPoints(RedBlackPoint R)
     {
         if (R == nullPoint)
             return 0;
         else
         {
             int L = 1;
             L += countPoints(R.left);
             L += countPoints(R.right);
             return L;
         }
     }
    
     public boolean search(int val)
     {
         return search(header.right, val);
     }
     private boolean search(RedBlackPoint r, int val)
     {
         boolean test = false;
         while ((r != nullPoint) && !test)
         {
             int rval = r.element;
             if (val < rval)
                 r = r.left;
             else if (val > rval)
                 r = r.right;
             else
             {
            	 test = true;
                 break;
             }
             test = search(r, val);
         }
         return test;
     }
  
     public void inorder()
     {
         inorder(header.right);
     }
     private void inorder(RedBlackPoint R)
     {
         if (R != nullPoint)
         {
             inorder(R.left);
             char c = 'B';
             if (R.colour == 0)
                 c = 'R';
             System.out.print(R.element +""+c+" ");
             inorder(R.right);
         }
     }
   
     public void preorder()
     {
         preorder(header.right);
     }
     private void preorder(RedBlackPoint r)
     {
         if (r != nullPoint)
         {
             char c = 'B';
             if (r.colour == 0)
                 c = 'R';
             System.out.print(r.element +""+c+" ");
             preorder(r.left);             
             preorder(r.right);
         }
     }
    
     public void postorder()
     {
         postorder(header.right);
     }
     private void postorder(RedBlackPoint r)
     {
         if (r != nullPoint)
         {
             postorder(r.left);             
             postorder(r.right);
             char c = 'B';
             if (r.colour == 0)
                 c = 'R';
             System.out.print(r.element +""+c+" ");
         }
     }     
 }
 

 public class RedBlackTreeTest
 {
     public static void main(String[] args)
     {            
        Scanner input = new Scanner(System.in);
       
        RedBlackTree RBT = new RedBlackTree(Integer.MIN_VALUE); 
        System.out.println("Tree Test\n");          
        char InChar;
       
        do    
        {
            System.out.println("\nRed Black Tree Functions\n");
            System.out.println("1. insert ");
            System.out.println("2. search");
            System.out.println("3. count points");
            System.out.println("4. check if empty");
            System.out.println("5. clear Red black tree");
 
            int order = input.nextInt();            
            switch (order)
            {
            case 1 : 
                System.out.println("Enter integer element to insert");
                RBT.insert( input.nextInt() );                     
                break;                          
            case 2 : 
                System.out.println("Enter integer element to search");
                System.out.println("Search result : "+ RBT.search( input.nextInt() ));
                break;                                          
            case 3 : 
                System.out.println("Points = "+ RBT.countPoints());
                break;     
            case 4 : 
                System.out.println("Empty status = "+ RBT.isEmpty());
                break;     
            case 5 : 
                System.out.println("\nTree is Cleared");
                RBT.makeTreeEmpty();
                break;         
            default : 
                System.out.println("Your input is invalid \n ");
                break;    
            }
           
            System.out.print("\nPost order : ");
            RBT.postorder();
            System.out.print("\nPre order : ");
            RBT.preorder();
            System.out.print("\nIn order : ");
            RBT.inorder(); 
 
            System.out.println("\nDo you want to continue  (Type y for (yes) or n for (no)) \n");
            InChar = input.next().charAt(0);                        
        } while (InChar == 'Y'|| InChar == 'y');               
     }
 }