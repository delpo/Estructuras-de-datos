package practica3;


/** Interface to define a search tree
 *  @author Koffman and Wolfgang
 * */

public interface SearchTree < E
    extends Comparable < E >> {
  /** Inserts item where it belongs in the tree.
      @param item The item to be inserted
      @return true If the item is inserted, false if the
      item was already in the tree.
   */
  boolean insert (E item);


  /** Find an object in the tree
      @param target The item being sought
      @return A reference to the object in the tree that compares
      equal as determined by compareTo to the target. If not found
      null is returned.
   */
  E find(E target);

  /** Removes target from tree.
      @param target Item to be removed
      @return A reference to the object in the tree that compares
      equal as determined by compareTo to the target. If not found
      null is returned.
      @post target is not in the tree
   */
  E remove(E target);

  /** Counts how many times is the element target in the tree
  @param target Item whose instances are counted
  @return int. Number of instances of item target
  */
  int count (E target);
  
  /** Removes all the instaces of target from tree.
   @param target Item to be removed
   @post target is not in the tree 
   */
   void removeAll (E target);
 
}
