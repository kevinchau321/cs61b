/* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
  public List setList;

  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    // Your solution here.
    setList = new DList();
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return setList.length();
  }
  
  @SuppressWarnings("unchecked")
  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c) {
    // Your solution here.
    if (this.cardinality()==0) {
      setList.insertFront(c);
    } else {
      try {
        ListNode curr = setList.front();
        for (int i = 1; i <= this.cardinality(); i++){
          if (c.compareTo(curr.item()) == 0) {
            return;
          } else if (c.compareTo(curr.item()) < 0) {
            curr.insertBefore(c);
            return;
          }

          if (i == this.cardinality()) {
            curr.insertAfter(c);
            return;
          } else {
            curr = curr.next();
          }
        }
      } catch (InvalidNodeException e1) {
        System.out.println("e1");
      }
      return;
    }
  }
  @SuppressWarnings("unchecked")
  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) {
    // Your solution here.
    if (s.cardinality()==0) {
      return;     ///adding empty set does nothing to this
    }
    try {
      ListNode thisNode = this.setList.front();
      ListNode sNode = s.setList.front();
      while (thisNode.isValidNode() && sNode.isValidNode()) {
        if (((Comparable) sNode.item()).compareTo(thisNode.item()) == -1) {
          thisNode.insertBefore(sNode.item());
          sNode = sNode.next();
        } else if (((Comparable) sNode.item()).compareTo(thisNode.item()) == 0) {
          sNode = sNode.next();
        } else if (((Comparable) sNode.item()).compareTo(thisNode.item()) == 1) {
          if (!thisNode.next().isValidNode()) {       //last node in this
            thisNode.insertAfter(sNode.item());
            sNode = sNode.next();
          } else {
            thisNode = thisNode.next();
          }
        }
      }
    } catch (InvalidNodeException e) {
      System.out.println(e);
    }
    return;
  }
  @SuppressWarnings("unchecked")
  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) {
    // Your solution here.
    if (s.cardinality() == 0) {
      try {
        for (int i = 1; i<=this.cardinality(); i++) {
          this.setList.front().remove();
        }
      } catch (InvalidNodeException e) {

      }
      return;
    }

    try {
      ListNode thisNode = this.setList.front();
      ListNode sNode = s.setList.front();
      while (thisNode.isValidNode() && sNode.isValidNode()) {
        if (((Comparable) sNode.item()).compareTo(thisNode.item()) == 0) {
          thisNode = thisNode.next();
          if (!sNode.next().isValidNode()) {
            while (thisNode.isValidNode()) {
              ListNode afterNode = thisNode.next();
              thisNode.remove();
              thisNode = afterNode;
            }
            return;
          } else {
            sNode = sNode.next();
          }
        } else if (((Comparable) sNode.item()).compareTo(thisNode.item()) == 1) {
          if (!thisNode.next().isValidNode()) {
            thisNode.remove();
            return;
          } else {
            ListNode afterNode = thisNode.next();
            thisNode.remove();
            thisNode = afterNode;
          }
        } else if (((Comparable) sNode.item()).compareTo(thisNode.item()) == -1) {
          if (!sNode.next().isValidNode()) {
            while (thisNode.isValidNode()) {
              ListNode afterNode = thisNode.next();
              thisNode.remove();
              thisNode = afterNode;
            }
            return;
          } else {
            sNode = sNode.next();
          }
        }
      }
    } catch (InvalidNodeException e) {
    }
    return;
  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    // Replace the following line with your solution.
    if (setList.isEmpty()) {
      return "{  }";
    }
    String setString = new String();
    setString = setString + "{ ";
    try {
      ListNode curr = setList.front();
      for (int i = 0; i < this.cardinality(); i++) {
        setString = setString + " " + curr.item() + " ";
        curr = curr.next();
      }
    } catch (InvalidNodeException e) {
    }
    setString = setString + " }";
    return setString;
  }

  public static void main(String[] argv) {
    Set s = new Set();
    s.insert(new Integer(3));
    s.insert(new Integer(4));
    s.insert(new Integer(3));
    System.out.println("Set s = " + s);
    System.out.println("s.cardinality() =  " + s.cardinality());

    Set s2 = new Set();
    s2.insert(new Integer(4));
    s2.insert(new Integer(5));
    s2.insert(new Integer(5));
    System.out.println("Set s2 = " + s2);
    System.out.println("s2.cardinality() = " + s2.cardinality());

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    System.out.println("Set s3 = " + s3);
    System.out.println("s3.cardinality() = " + s3.cardinality());

    s.union(s2);
    System.out.println("After s.union(s2), s = " + s);

    s.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s);

    System.out.println("s.cardinality() = " + s.cardinality());

    Set s4 = new Set();
    s4.insert(new Integer(1));
    s4.insert(new Integer(3));
    s4.insert(new Integer(5));
    s4.insert(new Integer(7));
    s4.insert(new Integer(9));
    System.out.println("Set s4 = " + s4);

    Set s5 = new Set();
    s5.insert(new Integer(0));
    s5.insert(new Integer(2));
    s5.insert(new Integer(4));
    s5.insert(new Integer(6));
    s5.insert(new Integer(8));
    System.out.println("Set s5 = " + s5);

    s4.union(s5);
    System.out.println("After s4.union(s5), s4 = " + s4);
    System.out.println("s4.cardinality() = " + s4.cardinality());

    s4.intersect(s5);
    System.out.println("After s4.intersect(s5), s4 = " + s4);
    System.out.println("s4.cardinality() = " + s4.cardinality());

    Set s6 = new Set();
    s6.insert(new Integer(1));
    s6.insert(new Integer(98));
    s6.insert(new Integer(15));
    s6.insert(new Integer(47));
    s6.insert(new Integer(4));
    System.out.println("Set s6 = " + s6);

    Set s7 = new Set();
    s7.insert(new Integer(5));
    s7.insert(new Integer(23));
    s7.insert(new Integer(14));
    s7.insert(new Integer(82));
    s7.insert(new Integer(7));
    System.out.println("Set s7 = " + s7);

    s6.union(s7);
    System.out.println("After s6.union(s7), s6 = " + s6);
    System.out.println("s6.cardinality() = " + s6.cardinality());
    s5.intersect(s7);
    System.out.println("After s5.union(s7), s5 = " + s5);






    // You may want to add more (ungraded) test code here.
  }
}
