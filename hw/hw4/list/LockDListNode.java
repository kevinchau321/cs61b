/* LockDListNode.java */

package list;

//LockDLIstNode has locked boolean

public class LockDListNode extends DListNode {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *  locked is a boolean value. Default is false. 
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */
  protected boolean locked;


  //Constructor
  public LockDListNode(Object i, LockDListNode p, LockDListNode n){
  	super(i,p,n);
  	locked = false; 
  }
}