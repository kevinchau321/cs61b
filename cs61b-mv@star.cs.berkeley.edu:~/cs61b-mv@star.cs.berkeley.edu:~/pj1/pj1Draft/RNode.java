/* RNode.java */
/* Implements a DNode to be used in a DList. DNode holds an object which is intended to be a pixel (see pixel.java) and a prev and next pointer to surrounding DNodes in the DList. */

public class RNode {
	//item references the object stored, which is intended to be a pixel
	//prev references the previous node in Dlist
	//next references the next node in the Dlist
	public RNode prev;
	public RNode next; 
	public short red;
	public short green;
	public short blue;
	public int length;

	//Constructors
	RNode() {
		red = 0;
		green = 0;
		blue = 0;
		length = 0;
		prev = null;
		next = null;
	}
	RNode(short r, short g, short b, int l) {
		red = r;
		green = g;
		blue = b;
		length = l;
		prev = null;
		next = null;
	}
	RNode(short r, short g, short b, int l, RNode pNode, RNode nNode) {
		red = r;
		green = g;
		blue = b;
		length = l;
		prev = pNode;
		next = nNode;
	}
}

