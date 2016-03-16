/* GameBoard.java */
package game;
import list.*;
/**The GameBoard class implements a 2D array of chips that represents the state of the game. A new GameBoard constructor should make an empty 8X8 array. The GameBoard must be able to allow a player to place chips in the array according to moves that the player chooses and makes (in the player class). Therefore it should have a set of public add chip and remove chip methods that allow players (which are outside the package) to place chips into the gameboard array (the add method necessarily knows where it can add chips--ie not on top of another or in clusters). The GameBoard also knows how many chips each player side has, so that it can enforce the maximum chip number rule. Therefore, GameBoard's add and remove methods should also be able to access the chip's color/side, a field contained in the chip class, in order to increment and decrement the chip counts. The GameBoard should implement a public method that allows players to determine whether or not it has a valid network for a certain side.
**/
public class GameBoard {
	private Chip[][] boardArray;
	private int blackCount;
	private int whiteCount;
	private DList chipList;
	private final static int DIMENSION = 8;

	/*The GameBoard() constructor is a private method for outside packages (such as player) to make a new GameBoard with no chips in it. Sets the blackCount and whiteCount to 0 and has a null Chip[][] array. Also makes an empty list to store game chips in (either side).
	*/
	public GameBoard() {
		blackCount = 0;
		whiteCount = 0;
		boardArray = new Chip[DIMENSION][DIMENSION];
		chipList = new DList();
		}
	/* getCount() is a public method for outside modules to get the number of chips of side--0 for blackCount and 1 for whiteCount. That way, only methods in the package can change the chip count.
	*/
	public int getCount(int side) {
		if (side == 0) {
			return blackCount;
		} else {
			return whiteCount;
		}
	}
	/*This in method returns a chipList of all the chips currently in the board, in no specific order. It contains chips on both sides.
	*/
	public DList getChipList() {
		return chipList;
	}
	/*getWidth() returns the width of this game, which is the constant DIMENSION
	*/
	public int getWidth() {
		return DIMENSION;
	}
	/*getWidth() returns the width of this game, which is the constant DIMENSION
	*/
	public int getHeight() {
		return DIMENSION;
	}
	/*addChip() allows an outside module or class to add a chip to the board. Takes one parameter, a Chip, which is added to the board in the coordinates specified by Chip. Adding a chip increases the chip count of the side that the chip has, and inserts the Chip into a list of all of this GameBoards chips. 
	*/
	public void addChip(Chip c) {
		int chipX = c.getCoordinates()[0];
		int chipY = c.getCoordinates()[1];
		this.boardArray[chipX][chipY] = c;
		if (c.getSide() == 0) { //black
			blackCount++;
		} else {
			whiteCount++;
		}
		chipList.insertBack(c);
	}
	/*removeChip() allows an outside module or class to remove a chip from the board. This method takes one parameter, which is a chip. removeChip only removes chip c (at the coordinates of c) if c is actually in the GameBoard.å
	*/
	public void removeChip(Chip c) {
		int chipX = c.getCoordinates()[0];
		int chipY = c.getCoordinates()[1];
		if (boardArray[chipX][chipY]==c){
			this.boardArray[chipX][chipY] = null;
			if (c.getSide() == 0) { //black
				blackCount--;
			} else {
				whiteCount--;
			}
			chipList.remove(chipList.find(c));
		}
	}
	/*getChip() allows outside modules to get a chip at coordinate (x, y), specified by the two int parameters x and y, in the gameboard. if no chip at (x,y), returns null
	*/
	public Chip getChip(int x, int y) {
		return this.boardArray[x][y];
	}
	/*checks if there is a chip at coordinate (x,y), specified by two int parameters x and y,  and returns true or false. hasChip returns false if the coordinates are out of the board (<0 or >7).
	*/
	public boolean hasChip(int x, int y) {
		if (x<0 || x>7 || y<0 || y>7) {
			return false;
		} else {
				return this.boardArray[x][y] != null;
			}		
		}
	/*checks if there is a chip for a particular parameter side at coordinate (x,y), specified by ints x and y, and returns true or false depending on if the side passed in has a chip in the gameboard at (x,y).
	*/
	public boolean sideHasChip(int x, int y, int side) {
		if (x<0 || x>7 || y<0 || y>7) {
			return false;
		} else {
			return ((this.boardArray[x][y] != null) && (this.boardArray[x][y].getSide() == side));
		}
	}	
	/*hasValidNetwork() is a Network identifier that returns true if this GameBoard has a winning Network (according to the rules) for the parameter side (a player), and false if the GameBoard does not contain a valid network. hasValidNetwork() uses a Depth First Search to find winning networks that enforce the network invariants. 
	*/
	public boolean hasValidNetwork(int side){
		if (side == 0) {		//black
			Chip[] startVertices = new Chip[6];		//Starts by checking top goal for black. at most 6 in start goal
			int k = 0;		//array index
			for (int i = 0; i<this.getWidth(); i++) {
				if (getChip(i, 0) != null) {		//black start goal has y = 0
					startVertices[k] = getChip(i,0);
					k++;
				}
			}
			if (startVertices[0] == null) {
				return false;		//no chips in beginning goal area returns false
			}
			for (int i = 0; startVertices[i] != null; i++){
				Chip start = startVertices[i];
				DList network = new DList();
				if (networkDFS(start, 0, network)) {
					start.unvisit();
					return true;
				}
				start.unvisit();
			}
			return false;
		} else {			//white
			Chip[] startVertices = new Chip[6];				//at most 6 in start goal
			int k = 0;		//array index
			for (int i = 0; i<this.getWidth(); i++) {
				if (getChip(0, i) != null) {			//white start goal has x = 0
					startVertices[k] = getChip(0,i);
					k++;
				}
			}
			if (startVertices[0] == null) {
				return false;		//no chips in beginning goal area;
			}
			for (int i = 0; startVertices[i] != null; i++){
				Chip start = startVertices[i];
				DList network = new DList();
				if (networkDFS(start, 1, network)) {
					start.unvisit();
					return true;
				}
				start.unvisit();
			}
			return false;
		}
	}

	/*networkDFS() takes in a starting chip (either in the white start goal (left) or the black start goal (top)) passed in a valid network identifier. The DFS also takes in a specified side, so it only returns true if it can construct a network (through recursive calls) for side. DFS takes in a DList which represents the chip network it is building, so it can recursively pass the list to itself. 
	*/
	private boolean networkDFS(Chip chip, int side, DList network) {	
		chip.visit(); //visit a chip and add it to the network list
		network.insertBack(chip);
		DListNode lastNode = network.front(); //gets last/previous chip in the constructed network before "chip", the piece we visited and inserted into the network
		int i = 1;
		while (i < network.length()-1) {
			lastNode = network.next(lastNode);
			i++;
		}
		Chip lastChip = (Chip) lastNode.item;  //references to network children chips
		DListNode childNode = chip.getConnections(this).front();
		Chip child;
		if (childNode!=null){
			child = (Chip) childNode.item;
		} else {
			child = null;
		}
		if (network.length() < 5) {
			while (child != null) {
				boolean not3x = !(child.getCoordinates()[0]==lastChip.getCoordinates()[0] && child.getCoordinates()[0]==chip.getCoordinates()[0]); //makes sure that 3 chips are not in the same column
				boolean not3y = !(child.getCoordinates()[1]==lastChip.getCoordinates()[1] && child.getCoordinates()[1]==chip.getCoordinates()[1]);  ///makes sure that 3 chips are not in the same row
				boolean not3d = !(	(Math.abs(child.getCoordinates()[0]-lastChip.getCoordinates()[0]) == Math.abs(child.getCoordinates()[1]-lastChip.getCoordinates()[1]))		///last chip and child are diagonal
											&& (Math.abs(child.getCoordinates()[0]-chip.getCoordinates()[0]) == Math.abs(child.getCoordinates()[1]-chip.getCoordinates()[1]))			///child and chip are diagonal
											&&	(Math.abs(chip.getCoordinates()[0]-lastChip.getCoordinates()[0]) == Math.abs(chip.getCoordinates()[1]-lastChip.getCoordinates()[1]))  );	///last chip and chip are diagonal, therefore all 3 are diagonal
				boolean notGoalVertex = !(child.getCoordinates()[0]==0 || child.getCoordinates()[1]==0 || child.getCoordinates()[0] == 7 || child.getCoordinates()[1]==7);		//for a list under six, we don't want to look at children in the end zones
				if (lastChip == chip) {
					if (networkDFS(child, side, network)) {
						child.unvisit();
						return true;
					}
					child.unvisit();
					network.remove(network.back());				
				} else if ((child.visited == false) && not3x && not3y && not3d && notGoalVertex) {
					if (networkDFS(child, side, network)) {
						child.unvisit();
						return true;
					}
					child.unvisit();
					network.remove(network.back());
				}
				childNode = network.next(childNode);
				if (childNode != null) {
					child = (Chip) childNode.item;
				} else {
					child = null;
				}
			}
		} else {		//network list has at least 6 chips
			if (side == 0) {			///black
				if (chip.getCoordinates()[1] == 7){			//6th black chip or any chip after is in the bottom goal, namely where y is 7
					return true;
				} else {
					while (child != null) {
						boolean not3x = !(child.getCoordinates()[0]==lastChip.getCoordinates()[0] && child.getCoordinates()[0]==chip.getCoordinates()[0]); //makes sure that 3 chips are not in the same column
						boolean not3y = !(child.getCoordinates()[1]==lastChip.getCoordinates()[1] && child.getCoordinates()[1]==chip.getCoordinates()[1]);  ///makes sure that 3 chips are not in the same row
						boolean not3d = !(	(Math.abs(child.getCoordinates()[0]-lastChip.getCoordinates()[0]) == Math.abs(child.getCoordinates()[1]-lastChip.getCoordinates()[1]))		///last chip and child are diagonal
											&& (Math.abs(child.getCoordinates()[0]-chip.getCoordinates()[0]) == Math.abs(child.getCoordinates()[1]-chip.getCoordinates()[1]))			///child and chip are diagonal
											&&	(Math.abs(chip.getCoordinates()[0]-lastChip.getCoordinates()[0]) == Math.abs(chip.getCoordinates()[1]-lastChip.getCoordinates()[1]))  );	///last chip and chip are diagonal, therefore all 3 are diagonal
						boolean notStartVertex = child.getCoordinates()[1] != 0;
						if ((child.visited == false) && not3x && not3y && not3d && notStartVertex) {
							if (networkDFS(child, side, network)) {
								child.unvisit();
								return true;
							}
							child.unvisit();
							network.remove(network.back());
						}	
						childNode = network.next(childNode);
						if (childNode != null) {
							child = (Chip) childNode.item;
						} else {
							child = null;
						}
					}
				}
			} else {	///side is 1, white
				if (chip.getCoordinates()[0] == 7){		//6th white chip or any chip after is in the right goal, namely where x is 7
					return true;
				} else {
					while (child != null) {
						boolean not3x = !(child.getCoordinates()[0]==lastChip.getCoordinates()[0] && child.getCoordinates()[0]==chip.getCoordinates()[0]); //makes sure that 3 chips are not in the same column
						boolean not3y = !(child.getCoordinates()[1]==lastChip.getCoordinates()[1] && child.getCoordinates()[1]==chip.getCoordinates()[1]);  ///makes sure that 3 chips are not in the same row
						boolean not3d = !(	(Math.abs(child.getCoordinates()[0]-lastChip.getCoordinates()[0]) == Math.abs(child.getCoordinates()[1]-lastChip.getCoordinates()[1]))		///last chip and child are diagonal
											&& (Math.abs(child.getCoordinates()[0]-chip.getCoordinates()[0]) == Math.abs(child.getCoordinates()[1]-chip.getCoordinates()[1]))			///child and chip are diagonal
											&&	(Math.abs(chip.getCoordinates()[0]-lastChip.getCoordinates()[0]) == Math.abs(chip.getCoordinates()[1]-lastChip.getCoordinates()[1]))  );	///last chip and chip are diagonal, therefore all 3 are diagonal
						boolean notStartVertex = child.getCoordinates()[0] != 0;
						if ((child.visited == false) && not3x && not3y && not3d && notStartVertex) {
							if (networkDFS(child, side, network)) {
								child.unvisit();
								return true;
							}
							child.unvisit();
							network.remove(network.back());
						}
						childNode = network.next(childNode);
						if (childNode != null) {
							child = (Chip) childNode.item;
						} else {
							child = null;
						}
					}
				}
			}
		}
		return false;
	}
	/*generates a hashCode value for a GameBoard. Used to store evaluated gameboards in a hast table.
	*/
	public int hashCode() {
				int val = 0;
				for (int x = 0; x < DIMENSION; x++) {
					for (int y = 0; y < DIMENSION; y++) {
				if (hasChip(x, y)) { //check if black or whie
					if (getChip(x, y).getSide() == 0) { //black, use 1
						val = (3 * val + 1) % 16908799;
					}
					else { //white, use 2
						val = (3 * val + 2) % 16908799;
					}
				}
				else { //use 0
					val = (3 * val + 0) % 16908799;
				}
			}
		}
		return val;
	}
	/*Useful method for debugging. Returns a string depicting the GameBoard with its chips.
	*/
	public String toString() {
		String gameString = new String();
		for (int y = 0; y < this.getHeight(); y++){
			for (int x = 0; x < this.getWidth(); x++){
				if ((x==0&&y==0)||(x==0&&y==7)||(x==7&&y==0)||(x==7&&y==7)){
					gameString = gameString + "|XXX";
				} else {
					if (!this.hasChip(x,y)) {
						gameString = gameString + "|___";
					} else {
						if (this.sideHasChip(x,y,0)) {
							gameString = gameString + "|_B_";
						} else {
							gameString = gameString + "|_W_";
						}
					}
				}
			}
			gameString = gameString + "|\n";
		}
		return gameString;
	}
}