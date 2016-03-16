/* LockDList.java */

package list;

public class LockDList extends DList {
	public LockDList() {
		super();
	}

	protected DListNode newNode(Object item, DListNode prev, DListNode next) {
   		return new LockDListNode(item, (LockDListNode) prev, (LockDListNode) next);
  	}

  	public void lockNode(DListNode node) {
  		((LockDListNode) node).locked = true;
  	}

  	public void remove(DListNode node) {
    // Your solution here.
  		if (((LockDListNode) node).locked == true) {
  			return;
  		} else {
  			super.remove(node);
  		}
  	}

  	public static void main(String[] args){
  		LockDList ldl = new LockDList();
  		ldl.insertFront(1);
  		ldl.insertBack(2);
  		System.out.println("head is a LockDListNode? " + (ldl.head instanceof LockDListNode));
  		if (ldl.front() instanceof LockDListNode) {
  			System.out.println("1 is a LockDListNode");
  		}
  		if (ldl.back() instanceof LockDListNode) {
  			System.out.println("2 is a LockDListNode");
  		}
  		System.out.println("1.next is: "+ ldl.front().next.item);
  		System.out.println(ldl.toString());
  		ldl.lockNode(ldl.front());
  		System.out.println("Locking 1");
  		System.out.println("Attempting to remove 1");
  		ldl.remove(ldl.front());
  		System.out.println(ldl.toString());
  		System.out.println("Attempting to remove 2 (Unlocked)");
  		ldl.remove(ldl.back());
  		System.out.println(ldl.toString());

  	}
}