/* runDList.java */

/*DList class with a head and a tail. Not circularly linked. */
public class runDList {
	protected RNode head;
	protected RNode tail;
	protected long size; 

	/* DList invariants;
		1) head.prev = null;
		2) tail.next = null;
		3) Nonsentinel nodes have a next
		4) Nonsentinel nodes have a prev
		5) if x.next == y then y.prev == x
		6) if x.prev == y then y.next = x
		7) Size does not include the sentinels
	*/

	//Constructors
	public runDList() {
		head = new RNode();
		tail = new RNode();
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	public runDList(short intensity, int length) {
		size = 1;
		head = new RNode();
		tail = new RNode();
		RNode run = new RNode(intensity, intensity, intensity, length, head, tail);
		head.next = run;
		tail.prev = run;
	}

	/**Insert and Delete Methods**/
	//add z before v
	public void addBefore(RNode v, RNode z) {
		RNode u = v.prev;
		z.prev = u;
		z.next = v;
		v.prev = z;
		u.next = z;
		size++;
	}
	//add z after v
	public void addAfter(RNode v, RNode z) {
		RNode w = v.next;
		z.prev = v;
		z.next = w;
		w.prev = z;
		v.next = z;
		size++;
	}
	public void addFirst(RNode v) {
		addAfter(head, v);
	}
	public void addLast(RNode v) {
		addBefore(tail, v);
	}
	public void remove(RNode v) {
		RNode u = v.prev;
		RNode w = v.next;
		w.prev = u;
		u.next = w;
		v.prev = null;
		v.next = null;
		size--;
	}
	public RNode nth(int position) {
    	RNode currentNode;

    	if ((position < 1) || (head == null)) {
      		return null;
    	} else {
      		currentNode = head.next;
      	while (position > 1) {
        	currentNode = currentNode.next;
        	if (currentNode.next == null) {		//tail.nex is null
          		return null;
       		}
        	position--;
      	}
      return currentNode;
    }
  }

}