/* Chip.java */
package game;
import list.*;          ///Dlist

/**
*  A Chip has a player side and knows its location in the GameBoard 
*  array in terms of private x and y coordinates. Therefore, the chip 
*  class will need getters and setters for public access to these variables. 
*  The chip will also have a list of other chips it can possibly connect 
*  with in a network. In addition, a chip can be stored in a NetworkListNode, 
*  and therefore can also belong to multiple lists, since different nodes 
*  could store the same chip. The chip should know what networks it belongs too. 
*  A chip cannot change color, so its side should be set during construction only.
*/

public class Chip {
    //fields
    private int side; //Black = 0; White = 1;
    private int[] location; //size 2 array for ordered pair x,y
    private DList connectionsList; //chips it can connect with  
    public boolean visited = false;

    public boolean visited() {
        return visited;
    }
    public void visit() {
        visited = true;
    }
    public void unvisit() {
        visited = false;
    }

    public void setXY(int x, int y) {
        location[0] = x;    
        location[1] = y;
    }   

    public Chip(int whichSide, int x, int y) {
        side = whichSide;
        location = new int[2];
        location[0] = x;
        location[1] = y;
    }


    public int[] getCoordinates() {
        return location;
    }


    public int getSide() {
        return side;
    }
  
    /** GetConnections returns a list of references to chips, which represents all of the chips
    that “this” chip can form a valid connection with. This method takes in two parameters, which
    are the current gameboard and the chip that you are trying to find the connections of. If a chip has no valid connections,
    getConnections should return an empty list. 
    **/
    public DList getConnections(GameBoard certainGame) {
        connectionsList = new DList(); //max connections is 9 because each player has 10 chips
        int thisXCoord = location[0]; //0th index of ordered pair is x coord
        int thisYCoord = location[1]; //1st index is y coord
        int thisSide = side; //checks which side the chip we are looking at is on
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) { //direction vectors
                if (i != 0 || j != 0) { //so we dont check (0,0)
                    //look along each direction vector
                    int checkX = thisXCoord + i;
                    int checkY = thisYCoord + j;
                    while (!certainGame.hasChip(checkX, checkY)) {
                        if (i < 0 && j < 0) {
                            checkX--;
                            checkY--;
                        }
                        if (i < 0 && j == 0) {
                            checkX--;
                        }
                        if (i < 0 && j > 0) {
                            checkX--;
                            checkY++;
                        }
                        if (i == 0 && j < 0) {
                            checkY--;
                        }
                        if (i == 0 && j > 0) {
                            checkY++;
                        }
                        if (i > 0 && j < 0) {
                            checkX++;
                            checkY--;
                        }
                        if (i > 0 && j == 0) {
                            checkX++;
                        }
                        if (i > 0 && j > 0) {
                            checkX++;
                            checkY++;
                        }
                        if (checkX < 0 || checkY < 0 || checkX > 7 || checkY > 7) { //have to check for edge cases
                            break;
                        }
                        //keep on incrementing so you check along the vector
                        //should increment checkX and checkY values
                        //bunch of if statements to check whether you add or subtract
                    }
                    if (certainGame.hasChip(checkX, checkY)) {
                        if ( (certainGame.getChip(checkX, checkY)).getSide() == thisSide ) { //if the chip that you find is on the same side as this chip
                            //check if they are in same endzone 
                            if (thisSide == 0) { //black side, check y = 0 or 7
                                if (this.getCoordinates()[1] == 0 || this.getCoordinates()[1] == 7) { //if chip is in black endzone
                                    if (certainGame.getChip(checkX, checkY).getCoordinates()[1] != this.getCoordinates()[1]) { //if not in same endzone, you can add it
                                        connectionsList.insertBack(certainGame.getChip(checkX, checkY));
                                    }
                                }
                                else {
                                    connectionsList.insertBack(certainGame.getChip(checkX, checkY));
                                }
                            }
                            if (thisSide == 1) { //white side, check x = 0 or 7
                                if (this.getCoordinates()[0] == 0 || this.getCoordinates()[0] == 7) { //if chip is in white endzone
                                    if (certainGame.getChip(checkX, checkY).getCoordinates()[0] != this.getCoordinates()[0]) { //if not in same endzone, you can add it
                                        connectionsList.insertBack(certainGame.getChip(checkX, checkY));
                                    }
                                }
                                else {
                                    connectionsList.insertBack(certainGame.getChip(checkX, checkY));
                                }
                            }
                        }
                    }
                }
            }
        }
        return connectionsList;
    }
}
