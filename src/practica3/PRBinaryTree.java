package practica3;


import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PRBinaryTree<T>  {
	
	protected static class BinaryNode<U> {
		protected U data;
		protected BinaryNode<U> left;
		protected BinaryNode<U> right;

	
		//constructors
		public BinaryNode (U data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
		
		public String toString() {
			return this.data.toString();
		}	
	}
	
	BinaryNode<T> root;  //reference to the node root of the tree
	
	//constructor empty binary tree
	public PRBinaryTree(){
		root = null;
	}
	
	//constructor: creates a new tree with a node with data in the root and left and right empty
	public PRBinaryTree(T data) {
		BinaryNode<T> n = new  BinaryNode<T>(data);
		this.root = n;
	}
	
	//constructor: given a node, constructs a binary tree whose root is that node
	private PRBinaryTree(BinaryNode<T> n){
		this.root = n;
	}
	
	//constructor: given data, and two binary trees constructs a new binary tree
	public PRBinaryTree(T data, PRBinaryTree<T> leftS, PRBinaryTree<T> rightS) {
		BinaryNode<T> root = new BinaryNode<T>(data);
		if (leftS != null) {
			root.left = leftS.root;
		}
		if (rightS != null) {
			root.right = rightS.root;
		}
		this.root = root;
	}
	
	public boolean isEmpty() {
		return (this.root == null);
	}
	
	public boolean isLeaf() {
		if (root != null) {
			if (root.left == null && root.right == null)
				return true;
			else 
				return false;
		}
		else return false;
	}
	
	public T getData () {
		if (root != null) 
			return root.data;
		else
			return null;
	}
	
	public PRBinaryTree<T> getLeftSubTree(){
		if (root != null) {
			if (root.left != null) {
				PRBinaryTree<T> leftTree = new PRBinaryTree<T>(root.left);
				return leftTree;
			}
			else 
				return new PRBinaryTree<T>();  //empty tree
		}	
		else
				return null;
	}
	
	public PRBinaryTree<T> getRightSubTree(){
		if (root != null) {
			if (root.right != null) {
				PRBinaryTree<T> rightTree = new PRBinaryTree<T>(root.right);
				return rightTree;
			}
			else 
				return new PRBinaryTree<T>();  //empty tree
		}	
		else
				return null;
	}
	
	public void inOrder(List<T> listData) {
		if (!isEmpty()){
			inOrder(root,listData);
		}
	}

	private void inOrder(BinaryNode<T> r, List<T> listData) {
		if (r.left!=null) {
			inOrder(r.left,listData);
		}
		listData.add(r.data);
		if (r.right!=null) {
			inOrder(r.right,listData);
		}
	}
	
	//displays a tree in the console
	public void displayTree() {
		Stack<BinaryNode<T>> globalStack = new Stack<BinaryNode<T>>();
	    globalStack.push(root);
	    int nBlanks = 32;
	    boolean isRowEmpty = false;
	    System.out.println(
	    "......................................................");
	    while(isRowEmpty==false)
	       {
	       Stack<BinaryNode<T>> localStack = new Stack<BinaryNode<T>>();
	       isRowEmpty = true;

	       for(int j=0; j<nBlanks; j++)
	          System.out.print(' ');

	       while(globalStack.isEmpty()==false)
	          {
	          BinaryNode<T> temp = (BinaryNode<T>)globalStack.pop();
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
		
	}
	
	//METHODS TO BE IMPLEMENTED 
	//It builds a binary tree given an string s with an arithmetic expression in postfix notation
	
	public PRBinaryTree<Character> createsBinaryExprTree(String chain) throws IllegalArgumentException{
		Stack<PRBinaryTree<Character>> pila = new Stack<PRBinaryTree<Character>>();
		int cont = 0;
		Character caracter_a_comprobar;
		PRBinaryTree<Character> arbin = new PRBinaryTree<Character>();
		while(cont <= chain.length()-1){
			caracter_a_comprobar = chain.charAt(cont);			
			if(isaNumber(caracter_a_comprobar)){
				//es un entero
				arbin = new PRBinaryTree<Character>(caracter_a_comprobar);
				pila.push(arbin);
				
			}else if(caracter_a_comprobar.equals('+') || caracter_a_comprobar.equals('-') || caracter_a_comprobar.equals('*') || caracter_a_comprobar.equals('/') || caracter_a_comprobar.equals('^')){
				//es una operación válida
				//pop() desapila y devuelve el objeto desapilado
				//pop puede fallar si está vacía, hacer try catch
				try{
					PRBinaryTree<Character> arbin_der = pila.pop();
					PRBinaryTree<Character> arbin_izq = pila.pop();
					arbin = new PRBinaryTree<Character>(caracter_a_comprobar, arbin_izq, arbin_der);
				}catch(Exception e){
					throw new IllegalArgumentException(e);
				}
				//añado a la pila
				pila.push(arbin);
			}else{
				//error!
				throw new IllegalArgumentException();
			}
			cont++;
		}
		arbin = pila.pop();
		
		if(!pila.isEmpty()){
			throw new IllegalArgumentException();
		}
		
		return arbin;
	}
	
	public boolean isaNumber(Character caracter_a_comprobar){
		String numeros = "0123456789";
		if(numeros.contains(caracter_a_comprobar.toString())){
			return true;
		}
		return false;
	}
	
	//Returns an string with the elements of the binary tree in postorder
	public String printPostOrder() {
		String arbol = "";
		if(!isEmpty()){
			arbol+=getLeftSubTree().printPostOrder();
			arbol+=getRightSubTree().printPostOrder();
			arbol+=root.data;
		}
		return arbol;
	}
	
	//Returns true if it is a extended binary tree, otherwise it returns false
	public boolean isExtendedTree() {
		if(isEmpty()) return true;
		else if(isLeaf()) return true;
		else if(getLeftSubTree().isEmpty() || getRightSubTree().isEmpty()) return false;
		else return getLeftSubTree().isExtendedTree() && getRightSubTree().isExtendedTree();
	}
	
	//Returns a string with the expression in the tree in infix notation with parenthesis
	public String printInOrder() {
		String arbol = "";
		if(!isEmpty()){
			if(!isLeaf()){ //si es nodo hoja hay que imprimir los paréntesis correspondientes
				arbol+="(";
				arbol+=getLeftSubTree().printInOrder();
				arbol+=root.data;
				arbol+=getRightSubTree().printInOrder();
				arbol+=")";
			}else{
				//arbol+=getLeftSubTree().printInOrder();
				arbol+=root.data;
				//arbol+=getRightSubTree().printInOrder();
			}
		}
		return arbol;
	}
	
	//Returns a string containing the expression in the tree using functional notation
	public String functionalNotation() {
		String arbol = "";
		if(!isEmpty()){
			if(!isLeaf()){ //si no es nodo hoja hay que imprimir los paréntesis correspondientes
				if(root.data.equals('+')) arbol+="Suma";
				else if(root.data.equals('-')) arbol+="Resta";
				else if(root.data.equals('*')) arbol+="Mult";
				else if(root.data.equals('/')) arbol+="Div";
				else if(root.data.equals('^')) arbol+="Exp";
				arbol+="(";
				arbol+=getLeftSubTree().functionalNotation();
				arbol+=",";
				arbol+=getRightSubTree().functionalNotation();
				arbol+=")";
			}else{
				//muestro
				arbol+=root.data;
				//arbol+=getLeftSubTree().functionalNotation();
				//arbol+=getRightSubTree().functionalNotation();
			}
		}
		return arbol;
	}
	
	//Returns the value resulting of the evaluation of the arithmetic expression in the tree
	public float evaluate() {
		float resultado = 0;
		if(!isEmpty()){
			if(!isLeaf()){ //si no es nodo hoja hay que imprimir los paréntesis correspondientes
				float left = getLeftSubTree().evaluate();
				float right = getRightSubTree().evaluate();
				if(root.data.equals('+')){
					resultado = left + right;
				}else if(root.data.equals('-')){
					resultado = left - right;
				}else if(root.data.equals('*')){
					resultado = left * right;
				}else if(root.data.equals('/')){
					resultado = left / right;
				}else if(root.data.equals('^')){
					resultado = (float) Math.pow(left, right);
				}
			}else{
				return root.data.hashCode() - '0';
			}
		}
		return resultado;
	}
	
	//It returns the elements of the tree in an array traversing the tree from level 1 to height.
	public void toArray(T[] v) {
		toArray2(v, 1);
	}

	public void toArray2(T[] v, int pos) {
		if(isEmpty()){
			v[pos] = root.data;
		}
		if(!isLeaf()){
			getLeftSubTree().toArray2(v,2*pos);
			getRightSubTree().toArray2(v,(2*pos)+1);
		}
		v[pos] = root.data;
	}

		
	public int height() {
		int h = 0;
		if (root != null)
			h = 1 + Math.max(getLeftSubTree().height(), getRightSubTree().height());
		return h;
	}
	
	
	
}
