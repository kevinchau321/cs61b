/* GameBoard.java */
package game;
import list.*;
/**The GameBoard class implements a 2D array of chips that represents the state of the game. A new GameBoard constructor should make an empty 8X8 array. The GameBoard must be able to allow a player to place chips in the array according to moves that the player chooses and makes (in the player class). Therefore it should have a set of public add chip and remove chip methods that allow players (which are outside the package) to place chips into the gameboard array (the add method necessarily knows where it can add chips--ie not on top of another). The GameBoard also knows how many chips each player side has, so that it can enforce the maximum chip number rule. Therefore, GameBoard's add and remove methods should also be able to access the chip's color/side, a field contained in the chip class, in order to increment and decrement the chip counts. The GameBoard should implement a public method that allows players to determine whether or not it has a valid network for a certain side.
**/
public class GameBoard {
	private Chip[][] boardArray;
	private int blackcount;
	private int whitecount;  
  	private int blackMaxNetwork;
  	private int whiteMaxNetwork;
    private final static int DIMENSION = 8;
  
  	public int getMaxNetwork(int side) {
      if (side == 0) { //black
      	return blackMaxNetwork;
      } else { //white
      	return whiteMaxNetwork;
      }
    }

    public void setMax(int side, int max) {
    	if (side == 0) {	//black
    		blackMaxNetwork = max;
    	} else {
    		whiteMaxNetwork = max;
    	}
    }
  	
  	public int getCount(int side) {
      if (side == 0) {
        return blackcount;
      } else {
        return whitecount;
      }
    }
    
  	

	//Constructor
	public GameBoard() {
		blackcount = 0;
		whitecount = 0;
      	blackMaxNetwork = 0;
      	whiteMaxNetwork = 0;
		boardArray = new Chip[DIMENSION][DIMENSION];
 
	}
	
	
	//returns width and height of gameboard (possibly necessary)
	public int getWidth() {
		return DIMENSION;
	}
	public int getHeight() {
		return DIMENSION;
	}
	

	//allows an outside module or class to add a chip to the board
	public void addChip(Chip c) {
    	int chipx = c.getCoordinates()[0];
      	int chipy = c.getCoordinates()[1];
      	this.boardArray[chipx][chipy] = c;
      if (c.getSide() == 0) { //black
      	blackcount++;
      }
      else {
      	whitecount++;
      }
	}

	//allows an outside module or class to remove a chip from the board
	public void removeChip(Chip c) {
      int chipx = c.getCoordinates()[0];
      int chipy = c.getCoordinates()[1];
      this.boardArray[chipx][chipy] = null;
      if (c.getSide() == 0) { //black
      	blackcount--;
      }
      else {
      	whitecount--;
      }
	}

	//allows outside modules to get a pointer to a chip at coordinate (x, y) in the gameboard
	//if no chip at (x,y), return null
	public Chip getChip(int x, int y) {
      return this.boardArray[x][y];
	}

	//checks if there is a chip at coordinate (x,y), returns true/false
  	//hasChip returns false if the coordinates are out of the board (<0 or >7)
	public boolean hasChip(int x, int y) {
      if (x<0 || x>7 || y<0 || y>7) {
        return false;
      }	else {
      return this.boardArray[x][y] != null;
      }		
	}
  
    //checks if there is a chip for a particular side at coordinate (x,y), returns true/false
    public boolean sideHasChip(int x, int y, int side) {
      if (x<0 || x>7 || y<0 || y>7) {
      	return false;
      }	else {
      return ((this.boardArray[x][y] != null) && (this.boardArray[x][y].getSide() == side));
      }
    }	


	//allows outside modules to see if the gameboard is full for a certain side
	public boolean isFull(int side){
      if (side == 0) { //black
      	return (blackcount == 10);
      }
      else { //white
      	return (whitecount == 10);
      }
	}
	
	//checks if side has a valid network that wins the game
	public boolean hasValidNetwork(int side){
		if (side == 0) {				//black
			Chip[] startVertices = new Chip[6];				//at most 6 in start goal
			int k = 0;		//array index
			for (int i = 0; i<this.getWidth(); i++) {
				if (getChip(i, 0) != null) {			//black start goal has y = 0
					startVertices[k] = getChip(i,0);
					k++;
				}
			}

			if (startVertices[0] == null) {
				return false;		//no chips in beginning goal area;
			}

			for (int i = 0; startVertices[i] != null; i++){
				Chip start = startVertices[i];
				DList network = new DList();
				if (networkDFS(start, 0, network)) {
					return true;
				}
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
					return true;
				}
			}
			return false;
		}
	}

	private boolean networkDFS(Chip chip, int side, DList network) {
      
      	//visit a chip and add it to the network list
		chip.visit();
		network.insertBack(chip);

		///gets last/previous chip in the constructed network before "chip", the piece we visited and inserted into the network
		DListNode lastNode = network.front();
		int i = 1;
		while (i < network.length()-1) {
			lastNode = network.next(lastNode);
			i++;
		}
		Chip lastChip = (Chip) lastNode.item;
      	

		//references to network children chips
		DListNode childNode = chip.getConnections(this).front();
		Chip child;
		if (childNode!=null){
			child = (Chip) childNode.item;
		} else {
			child = null;
		}
      
		if (network.length() < 5) {
			while (child != null) {
              	//useful booleans
      			boolean not3x = !(child.getCoordinates()[0]==lastChip.getCoordinates()[0] && child.getCoordinates()[0]==chip.getCoordinates()[0]); //makes sure that 3 chips are not in the same column
      			boolean not3y = !(child.getCoordinates()[1]==lastChip.getCoordinates()[1] && child.getCoordinates()[1]==chip.getCoordinates()[1]);  ///makes sure that 3 chips are not in the same row
      			boolean not3d = !(	(Math.abs(child.getCoordinates()[0]-lastChip.getCoordinates()[0]) == Math.abs(child.getCoordinates()[1]-lastChip.getCoordinates()[1]))		///last chip and child are diagonal
      								&& (Math.abs(child.getCoordinates()[0]-chip.getCoordinates()[0]) == Math.abs(child.getCoordinates()[1]-chip.getCoordinates()[1]))			///child and chip are diagonal
      								&&	(Math.abs(chip.getCoordinates()[0]-lastChip.getCoordinates()[0]) == Math.abs(chip.getCoordinates()[1]-lastChip.getCoordinates()[1]))  );	///last chip and chip are diagonal, therefore all 3 are diagonal
              	boolean notGoalVertex = !(child.getCoordinates()[0]==0 || child.getCoordinates()[1]==0 || child.getCoordinates()[0] == 7 || child.getCoordinates()[1]==7);		//for a list under six, we don't want to look at children in the end zones
             
              	if (lastChip == chip) {
                 	if (networkDFS(child, side, network)) {
                      	return true;
                    }
					child.unvisit();
					network.remove(network.back());
                  
              	} else if ((child.visited == false) && not3x && not3y && not3d && notGoalVertex) {
                  		if (networkDFS(child, side, network)) {
                    		return true;
                        }
						child.unvisit();
						network.remove(network.back());
				}
          
				childNode = network.next(childNode);
              	if (childNode != null) {
					child = (Chip) childNode.item;
             	}
              	else {
              		child = null;
              	}
         		
			}
                           
		} else {				//network list has at least 6 chips
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
                    			return true;
                        	}
							child.unvisit();
							network.remove(network.back());
						}	
						childNode = network.next(childNode);
        				if (childNode != null) {
							child = (Chip) childNode.item;
             			}
              			else {
              				child = null;
              			}
					}
				}
			} else {				///side is 1, white
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
                    			return true;
                        	}
							child.unvisit();
							network.remove(network.back());
						}
						childNode = network.next(childNode);
						if (childNode != null) {
							child = (Chip) childNode.item;
             			}
              			else {
              				child = null;
              			}
					}
				}
			}
		}
		return false;
	}
  
   
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
  
}