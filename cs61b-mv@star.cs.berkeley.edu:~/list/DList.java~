/* DList.java */

package list;

/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class DList {

  /**
   *  head references the sentinel node.
   *  size is the number of items in the list.  (The sentinel node does not
   *       store an item.)
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode head;
  protected int size;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode x in a DList, x.next != null.
   *  3)  For any DListNode x in a DList, x.prev != null.
   *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNodes, NOT COUNTING the sentinel,
   *      that can be accessed from the sentinel (head) by a sequence of
   *      "next" references.
   */

  /**
   *  newNode() calls the DListNode constructor.  Use this class to allocate
   *  new DListNodes rather than calling the DListNode constructor directly.
   *  That way, only this method needs to be overridden if a subclass of DList
   *  wants to use a different kind of node.
   *  @param item the item to store in the node.
   *  @param prev the node previous to this node.
   *  @param next the node following this node.
   */
  protected DListNode newNode(Object item, DListNode prev, DListNode next) {
    return new DListNode(item, prev, next);
  }

  /**
   *  DList() constructor for an empty DList.
   */
  public DList() {
    head = newNode(null, null, null);
    head.next = head;
    head.prev = head;
    size = 0;
  }

  /**
   *  isEmpty() returns true if this DList is empty, false otherwise.
   *  @return true if this DList is empty, false otherwise. 
   *  Performance:  runs in O(1) time.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /** 
   *  length() returns the length of this DList. 
   *  @return the length of this DList.
   *  Performance:  runs in O(1) time.
   */
  public int length() {
    return size;
  }

  /**
   *  insertFront() inserts an item at the front of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertFront(Object item) {
    // Your solution here.
    DListNode inserted = newNode(item, head, head.next);
    head.next = inserted;
    inserted.next.prev = inserted;
    size++;    
  }

  /**
   *  insertBack() inserts an item at the back of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertBack(Object item) {
    DListNode inserted = newNode(item, head.prev, head);
    inserted.prev.next = inserted;
    head.prev = inserted;
    size++;
  }

  /**
   *  front() returns the node at the front of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the front of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode front() {
    // Your solution here.
    if (this.isEmpty()) {
      return null;
    } else {
      return head.next;
    }
  }

  /**
   *  back() returns the node at the back of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the back of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode back() {
    // Your solution here.
    if (this.isEmpty()) {
      return null;
    } else {
      return head.prev;
    }
  }

  /**
   *  next() returns the node following "node" in this DList.  If "node" is
   *  null, or "node" is the last node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose successor is sought.
   *  @return the node following "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode next(DListNode node) {
    // Your solution here.
    if (node == null || node.next == head){
      return null;
    } else {
      return node.next;
    }
  }

  /**
   *  prev() returns the node prior to "node" in this DList.  If "node" is
   *  null, or "node" is the first node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose predecessor is sought.
   *  @return the node prior to "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode prev(DListNode node) {
    // Your solution here.
    if (node == null || node.prev == head){
      return null;
    } else {
      return node.prev;
    }
  }

  /**
   *  insertAfter() inserts an item in this DList immediately following "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item after.
   *  Performance:  runs in O(1) time.
   */
  public void insertAfter(Object item, DListNode node) {
    // Your solution here.
    if (node == null){
      return;
    } else {
      DListNode inserted = newNode(item, node, node.next);
      node.next = inserted;
      inserted.next.prev = inserted;
      size++;
    }
  }

  /**
   *  insertBefore() inserts an item in this DList immediately before "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item before.
   *  Performance:  runs in O(1) time.
   */
  public void insertBefore(Object item, DListNode node) {
    // Your solution here.
    if (node == null){
      return;
    } else {
      DListNode inserted = newNode(item, node.prev, node);
      inserted.prev.next = inserted;
      node.prev = inserted;
      size++;
    }
  }

  /**
   *  remove() removes "node" from this DList.  If "node" is null, do nothing.
   *  Performance:  runs in O(1) time.
   */
  public void remove(DListNode node) {
    // Your solution here.
    if (node == null){
      return;
    } 
    //else if (this.isEmpty()) {
    //  return;
    //}                 Removed for part 2
     else {
      node.prev.next = node.next;
      node.next.prev = node.prev;
      node.next = null;
      node.prev = null;
      size--;
    }
  }
  public DListNode find(Object item) {
    DListNode first = head.next;
    while (first != null) {
        if (first.item == item) {
            return first;
        }
        first = this.next(first);
    }
    return null;
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   *  Performance:  runs in O(n) time, where n is the length of the list.
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head.next;
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }

  public static void main(String[] args){
      DList eList = new DList();

      DList list1 = new DList();
      System.out.println("Testing Empty Constructor");
      System.out.println("Is Empty? " +list1.isEmpty());
      System.out.println("Size: "+list1.size);
      System.out.println("head.next is head: "+ (list1.head.next == list1.head));
      System.out.println("head.prev is head: "+(list1.head.prev==list1.head));
      System.out.println("head.item is: "+list1.head.item);
      System.out.println("Testing methods");
      list1.insertFront(1);
      System.out.println("List with insertFront(1):" + list1.toString());
      list1.insertBack(2);
      System.out.println("List with insertBack(2):" + list1.toString());
      System.out.println("1.next is: "+ list1.head.next.next.item);
      System.out.println("2.prev is: " + list1.head.prev.prev.item);
      System.out.println("size: "+list1.size);
      System.out.println("front() item is: "+ list1.front().item);
      System.out.println("back() item is: "+list1.back().item);
      System.out.println("front() of empty DList: "+ eList.front());
      System.out.println("back() of empty DList: "+eList.back());
      DListNode back = list1.back();
      DListNode front = list1.front();
      System.out.println("prev(back).item is: "+ list1.prev(back).item);
      System.out.println("next(back) is: "+ list1.next(back));
      System.out.println("next(front).item is: "+ list1.next(front).item);
      System.out.println("prev(front) is: "+list1.prev(front));
      list1.insertBefore(0, list1.front());
      System.out.println("insertBefore(0, 1): " + list1.toString());
      list1.insertAfter(3, list1.back());
      System.out.println("insertAfter(3, 2): " +list1.toString());
      list1.insertAfter(4, null);
      System.out.println("insertAfter(4, null): "+list1.toString());
      list1.insertBefore(-1, null);
      System.out.println("insertBefore(-1, null): "+list1.toString());
      list1.remove(front);
      System.out.println("list1.remove(1): "+list1.toString());
      System.out.println("0.next: "+list1.front().next.item);
      list1.remove(null);
      System.out.println("list1.remove(null):"+ list1.toString());
      System.out.println("size is now:" +list1.size);
  }
}
