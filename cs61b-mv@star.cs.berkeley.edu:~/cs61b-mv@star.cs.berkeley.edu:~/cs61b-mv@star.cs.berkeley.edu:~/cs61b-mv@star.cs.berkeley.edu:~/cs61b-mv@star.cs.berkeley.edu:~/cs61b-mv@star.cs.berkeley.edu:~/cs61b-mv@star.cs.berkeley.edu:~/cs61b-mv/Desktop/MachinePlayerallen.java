/* MachinePlayer.java */

package player;
import list.*;
import game.*;
import dict.*;

/**
 *  An implementation of an automatic Network player.  Keeps track of moves
 *  made by both players.  Can select a move for itself.
 */
public class MachinePlayer extends Player {
	private int side;   ///0 is black, 1 is white
	private int otherside;
	private int searchDepth;
	private GameBoard game;
	private HashTableChained evalHash;

	// Creates a machine player with the given color.  Color is either 0 (black)
	// or 1 (white).  (White has the first move.)
	public MachinePlayer(int color) {
		side = color;
		if (color == 0) {
			otherside = 1;
		} else {
			otherside = 0;
		}
		game = new GameBoard();
		searchDepth = 3;
		evalHash = new HashTableChained(1000);
	}

	// Creates a machine player with the given color and search depth.  Color is
	// either 0 (black) or 1 (white).  (White has the first move.)
	public MachinePlayer(int color, int searchDepth) {
		side = color;
		if (color == 0) {
			otherside = 1;
		} else {
			otherside = 0;
		}
		game = new GameBoard();
		this.searchDepth = searchDepth;
		evalHash = new HashTableChained(1000);
	}
	
	public void setGame(GameBoard certainGame) {
		game = certainGame;
	}

	public GameBoard getGame() {
		return game;

	}

	public int getSide() {
		return side;
	}
	
	public int getOtherSide(){
		return otherside;
	}


	// Returns a new move by "this" player.  Internally records the move (updates
	// the internal game board) as a move by "this" player.
	public Move chooseMove() {
		int x;
		int y;
		if (game.getCount(side) < 2) {
			if (game.getCount(side) == 0){		//first move
				if (side == 0) {		//black
					x = 3;
					y = 0;
				}
				else {			//white
						x = 0;
						y = 3;
				}
				game.addChip(new Chip(side,x,y));
				return new Move(x,y);
			}
			if (game.getCount(side) == 1) {	//second move
				if (side == 0) {		//black
					x = 4;
					y = 7;
				} else {			//white
						x = 7;
						y = 4;
				}
				game.addChip(new Chip(side,x,y));
				return new Move(x,y);
			}
		}
		else {			///use minimax search for third move onwards
			Move chosenMove =  miniMax(side, -1, 1, game, searchDepth).bestMove;
			if (forceMove(chosenMove)) {
				return chosenMove;
			} else {
				return null;
			}
		}
		return null;
	} 
	
	public Best miniMax(int side, double a, double b, GameBoard g, int depth) {
		Best myBest = new Best();
		Best reply;
		if(g.hasValidNetwork(this.side)){  //base cases
			return new Best(null, 1);
		} else if (g.hasValidNetwork(this.otherside)) {
			return new Best(null, -1);
		} else if (depth == 0) {
			return new Best(null, evaluation(g));
		}
		
		if (side == this.side) {
			myBest.bestScore = a; //set alpha as the best score
		} else {
			myBest.bestScore = b; //set beta as the best score
		}
		
		int notside; 
		if (side == this.side) {
			notside = otherside; //set opponent as notside
		} else {
			notside = this.side; //set computer as notside
		}
		
		
		if (g.getCount(side) <10) {			///look at valid add moves
				DList moves = validAddMoves(g, side);
				DListNode currMoveNode = moves.front();
			myBest.bestMove = (Move) currMoveNode.item;
				while (currMoveNode != null) {
						Move m = (Move) currMoveNode.item;
					Chip newChip = new Chip(side, m.x1, m.y1);		//perform move
						game.addChip(newChip);
						reply = miniMax(notside, a, b, g, depth - 1);
					game.removeChip(newChip);			//undo move
					if (side == this.side && (reply.bestScore + .2*depth) > myBest.bestScore) {
							myBest.bestMove = m;
								myBest.bestScore = reply.bestScore + .2*depth;
								a = reply.bestScore;
					
						} else if (side == notside && (reply.bestScore - .2*depth) < myBest.bestScore) {
							myBest.bestMove = m;
								myBest.bestScore = reply.bestScore - .2*depth;
								b = reply.bestScore;
						}
					if (a > b) {
							return myBest;
					}
						currMoveNode = moves.next(currMoveNode);
				}
				return myBest;
		} else {                            //look at valid step moves
			DList moves = validStepMoves(g, side);
				DListNode currMoveNode = moves.front();
			myBest.bestMove = (Move) currMoveNode.item;
				while (currMoveNode != null) {
						Move m = (Move) currMoveNode.item;
					Chip newChip = new Chip(side, m.x1, m.y1);		//perform move
						Chip oldChip = game.getChip(m.x2,m.y2);
						game.removeChip(oldChip);
						game.addChip(newChip);
						reply = miniMax(notside, a, b, g, depth - 1);
					game.removeChip(newChip);			//undo move
						game.addChip(oldChip);
					if (side == this.side && (reply.bestScore + .2*depth) > myBest.bestScore) {
							myBest.bestMove = m;
								myBest.bestScore = reply.bestScore + .2*depth;
								a = reply.bestScore;
					
						} else if (side == notside && (reply.bestScore - .2*depth) < myBest.bestScore) {
							myBest.bestMove = m;
								myBest.bestScore = reply.bestScore - .2*depth;
								b = reply.bestScore;
						}
					if (a > b) {
							return myBest;
					}
						currMoveNode = moves.next(currMoveNode);
				}
				return myBest; 
		}
	}
	//get list of all valid moves
	//score children,and find max

	// If the Move m is legal, records the move as a move by the opponent
	// (updates the internal game board) and returns true.  If the move is
	// illegal, returns false without modifying the internal state of "this"
	// player.  This method allows your opponents to inform you of their moves.
	public boolean opponentMove(Move m) {
		if (isValidMove(game, m, otherside)) {
			int kind = m.moveKind;
			if (kind == 1) {    //add move
				int x1 = m.x1;
				int y1 = m.y1;
				Chip newChip = new Chip(otherside, x1, y1);
				game.addChip(newChip);
				return true;
			}
			else if (kind == 2) { //step move
				int x1 = m.x1;
				int y1 = m.y1;
				int x2 = m.x2;
				int y2 = m.y2;
				Chip oldChip = game.getChip(x2, y2);
				game.removeChip(oldChip);
				Chip newChip = new Chip(otherside, x1, y1);
				game.addChip(newChip);
				return true;
			}
		}
		return false;
	}

	// If the Move m is legal, records the move as a move by "this" player
	// (updates the internal game board) and returns true.  If the move is
	// illegal, returns false without modifying the internal state of "this"
	// player.  This method is used to help set up "Network problems" for your
	// player to solve.
	public boolean forceMove(Move m) {
		if (isValidMove(game, m, side)) {
			int kind = m.moveKind;
			if (kind == 1) { //add move
				int x1 = m.x1;
				int y1 = m.y1;
				Chip newChip = new Chip(side, x1, y1);
				game.addChip(newChip);
				return true;
			}
			else if (kind == 2) { //step move
				int x1 = m.x1;
				int y1 = m.y1;
				int x2 = m.x2;
				int y2 = m.y2;
				Chip oldChip = game.getChip(x2, y2);
				game.removeChip(oldChip);
				Chip newChip = new Chip(side, x1, y1);
				game.addChip(newChip);
				return true;
			}
		}
		return false;
	}


	//ALLENS VALIDMOVE
	public boolean isValidMove(GameBoard b, Move m, int side) {
		int x1 = m.x1;
		int y1 = m.y1;
		if ((x1 == 0 && y1 == 0) || (x1 == 0 && y1 == 7) || (x1 == 7 && y1 == 0) || (x1 == 7 && y1 == 7)) { //cant add chips into corners
			return false;
		}
		if (side == 0) { //black side, cant add chip to left/right goalzone
		  if ((x1 == 0) || (x1 == 7)) {
			return false;
		  }
		}
		if (side == 1) { //white side, cant add chip to top/bot goalzone
		  if ((y1 == 0) || (y1 == 7)) {
			return false;
		  }
		}
		if (b.hasChip(x1, y1)) {
			return false;
		}

		if (m.moveKind == 1) {  // add move
		  if (b.getCount(side) == 10) {
			return false;
		  }
		  else {
			int count = 0;
			int x3 = 0;
			int y3 = 0;
			for (int x = -1; x<2; x++) {
			  for (int y = -1; y<2; y++) {
				if (x != 0 || y != 0) {
					if (b.sideHasChip(x1+x, y1+y, side)) {
						count++;
						x3 = x1+x;
						y3 = y1+y;
					  for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
						  if (i != 0 || j != 0) {
							if (b.sideHasChip(x3+i, y3+j, side)) {
								return false;
							}
						  }
						}
					  }
					}
				}
			  }
			}
			if (count >= 2) {
				return false;
			}
			return true;
		  }
		}   
		else if (m.moveKind == 2) {  // step move
		  int x2 = m.x2;
		  int y2 = m.y2;
		  if (!b.sideHasChip(x2, y2, side) || b.getCount(side) < 10) {
			return false;
		  }
		  else {
			int count = 0;
			int x3 = 0;
			int y3 = 0;
			for (int x = -1; x<2; x++) {
			  for (int y = -1; y<2; y++) {
				if (x != 0 || y != 0) {
					if (b.sideHasChip(x1+x, y1+y, side)) {
					count++;
					x3 = x1+x;
					y3 = y1+y;
						for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
						  if (i != 0 || j != 0) {
							if (b.sideHasChip(x3+i, y3+j, side)) {
								return false;
							}
						  }
						}
					  }
				}
				}
			  }
			}
			if (count >= 2) {
				return false;
			}
			return true;
		  }
		}
	  return false;
	}






		
	//a list of possible valid moves that a player could make 
	//given a gameboard with a certain configuration
	public DList validAddMoves(GameBoard board, int side){
			DList validMoveList = new DList();
			if (side==1) {
				for (int x = 0; x<8; x++) {
					for (int y = 1; y<7; y++) {
						Move move = new Move(x, y);
						if (isValidMove(board, move, side)){
							validMoveList.insertBack(move);
						}
					}
				}
			} else {
				for (int x = 1; x<7; x++) {
					for (int y = 0; y<8; y++) {
						Move move = new Move(x, y);
						if (isValidMove(board, move, side)){
							validMoveList.insertBack(move);
						}
					}
				}
			}
		return validMoveList;
	}

	public DList validStepMoves(GameBoard board, int side){
			DList validMoveList = new DList();
			if (side==1) {
				for (int x = 0; x<8; x++) {
					for (int y = 1; y<7; y++) {
						if (board.sideHasChip(x, y, side)) {
							for (int x1 = 0; x<8; x++) {
								for (int y1 = 1; y<7; y++) {
									Move move = new Move(x1, y1, x, y);
									if (isValidMove(board, move, side)) {
										validMoveList.insertBack(move);
									}
								}
							}
						}
					}
				}
			} else {
				for (int x = 1; x<7; x++) {
					for (int y = 0; y<8; y++) {
						if (board.sideHasChip(x, y, side)) {
							for (int x1 = 1; x<7; x++) {
								for (int y1 = 0; y<8; y++) {
									Move move = new Move(x1, y1, x, y);
									if (isValidMove(board, move, side)) {
										validMoveList.insertBack(move);
									}
								}
							}
						}
					}
				}
			}
		return validMoveList;
	}
		
		//white(1) has sides (no y=0,7)
		//black(0) has top and bottom (no x=0, 7) 
		// iterate through spaces (possibly valid moves) and add 
		// them to the back of the DList if they are valid
	
	public double evaluation(GameBoard certainGame) {
		if (certainGame.hasValidNetwork(side)) { //if you win
			return 1;
		}
		if (certainGame.hasValidNetwork(otherside)) { //if opponent wins
			return -1;
		}
		int yourPairs = 0;
		int opponentPairs = 0;
		//check if it is in hashtable, if yes, search hash table
		if (evalHash.find(certainGame) != null) { //gameboard is in hashtable
			return evalHash.find(certainGame).value();
		}
		
		for (int i = 0; i < certainGame.getWidth(); i ++) {
			for (int j = 0; i < certainGame.getHeight(); j++) { //loop through the whole gameboard
				if (certainGame.hasChip(i, j)) { //if you find a chip...
					if (certainGame.getChip(i, j).getSide() == side) { //your side
						int countYourPairs = certainGame.getChip(i, j).getConnections(certainGame).length();
						yourPairs += countYourPairs;
					}
					if (certainGame.getChip(i, j).getSide() == otherside) { //opponent side
						int countOppPairs = certainGame.getChip(i, j).getConnections(certainGame).length();
						opponentPairs += countOppPairs;
					}
				}
			}
		}
		
		int pairDiff = yourPairs - opponentPairs;
		//add score to hashtable so you dont have to evaluate again
		double evalValue = (double) pairDiff/25;
	evalHash.insert(certainGame, evalValue);
		return evalValue;
	}

}

