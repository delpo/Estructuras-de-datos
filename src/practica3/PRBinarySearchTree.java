package practica3;



import java.util.Stack;

public class PRBinarySearchTree<E extends Comparable <E>> {
	
	protected class BinaryNode {
		protected E data;
		protected BinaryNode left;
		protected BinaryNode right;
		
		BinaryNode(E data){
			this.data = data;
		}
		BinaryNode(E data, BinaryNode lnode, BinaryNode rnode) {
			this.data = data;
			this.left = lnode;
			this.right = rnode;
		}
	}
	
	private BinaryNode root;
	protected boolean insertReturn; //Return value for the public insert method
	protected E removeReturn; //Return value for the public remove method

	public PRBinarySearchTree() {
		root = null;
	}
	
	public boolean isEmpty() {
		return (root == null);
	}
	
	///

	public int contadorVeces(E item){
		if(isEmpty()) return 0;
		int veces = 0;
		return contadorVeces2(root, item, veces);
	}

	public int contadorVeces2(BinaryNode r, E item, int veces){
		if (r != null) {
			if (r.data == item)
				veces++;
			if (item.compareTo(r.data)< 0) {
				return contadorVeces2(r.left, item, veces);
			}
			else {
				return contadorVeces2(r.right, item, veces);
			}
		}
		return veces;
	}
	
	public void borradoTotal(E item){
		while(remove(item) != null){
			remove(item);
		}
	}
	///
	
	private BinaryNode insert (BinaryNode r, E item) {
		if (r == null) {
			r = new BinaryNode(item);
			insertReturn = true;
		}
		else if (item.compareTo(r.data) <= 0) {   //less or equal to the left //EJ 1: he añadido un = para añadir duplicados en la rama izq. de su copia
			r.left = insert (r.left, item);
		}
		else if (item.compareTo(r.data) >0 ) {
			r.right = insert (r.right, item);
		}
		return r;	
	}
	
	//returns true if the item is inserted, otherwise returns false
	public boolean insert (E item) {
		
		root = insert(root, item);
		return insertReturn;
	}
	
	private E find (BinaryNode r, E target) {
		if (r != null) {
			if (r.data.equals(target))
				return r.data;
			else if (target.compareTo(r.data)< 0) {
				return find (r.left, target);
			}
			else {
				return find (r.right, target);
			}
		}
		else return null;
	}
	
	
	public E find(E target) {
		
		return find (root, target);
	}


	public E remove (E target) {
		
		root = remove (root, target);
		return removeReturn;
	}
	
	private BinaryNode remove (BinaryNode r, E target) {
		if( r == null )
            //throw new ItemNotFoundException( target.toString( ) );
			removeReturn = null;  //element not found
		else {
			if( target.compareTo( r.data ) < 0 )
				r.left = remove(r.left, target );
			else if( target.compareTo( r.data ) > 0 )
				r.right = remove(r.right, target );
			else if( r.left != null && r.right != null ) // Two children
			{
				BinaryNode nodeMin = findMinNode(r.right); //node with the minimun of the right subTree
				removeReturn = r.data;
				r.data = nodeMin.data; //replaces the element to be deleted
				r.right = removeMin( r.right ); //removes the minimun from the right subTree
			} 
			else { //one or none children
				removeReturn = r.data;
				if (r.left == null) 
					r = r.right;
				else
					r = r.left;
            }
		}
        return r;
	}

	private BinaryNode findMinNode (BinaryNode r) {
		if (r != null) {
			while (r.left != null)
				r = r.left;
		}
		return r;
	}
	
	private BinaryNode removeMin (BinaryNode r) {
		if (r != null) {
			if (r.left != null) {
				r.left = removeMin(r.left);
				return r;
			}
			else 
				return r.right;
		}
		return r; // null if here
	}

	 
	 /*=========================================*/
	 public void displayTree() {
	      Stack<BinaryNode> globalStack = new Stack<BinaryNode>();
	      globalStack.push(root);
	      int nBlanks = 32;
	      boolean isRowEmpty = false;
	      System.out.println(
	      "......................................................");
	      while(isRowEmpty==false)
	         {
	         Stack<BinaryNode> localStack = new Stack<BinaryNode>();
	         isRowEmpty = true;

	         for(int j=0; j<nBlanks; j++)
	            System.out.print(' ');

	         while(globalStack.isEmpty()==false)
	            {
	            BinaryNode temp = (BinaryNode)globalStack.pop();
	            if(temp != null)
	               {
	               System.out.print(temp.data);
	               localStack.push(temp.left);
	               localStack.push(temp.right);

	               if(temp.left != null || temp.right != null)
	                  isRowEmpty = false;
	               }
	            else
	               {
	               System.out.print("--");
	               localStack.push(null);
	               localStack.push(null);
	               }
	            for(int j=0; j<nBlanks*2-2; j++)
	               System.out.print(' ');
	        }  // end while globalStack not empty
	         System.out.println();
	         nBlanks /= 2;
	         while(localStack.isEmpty()==false)
	            globalStack.push( localStack.pop() );
	         }  // end while isRowEmpty is false
	      System.out.println(
	      "......................................................");
	    }  // end displayTree()
}
