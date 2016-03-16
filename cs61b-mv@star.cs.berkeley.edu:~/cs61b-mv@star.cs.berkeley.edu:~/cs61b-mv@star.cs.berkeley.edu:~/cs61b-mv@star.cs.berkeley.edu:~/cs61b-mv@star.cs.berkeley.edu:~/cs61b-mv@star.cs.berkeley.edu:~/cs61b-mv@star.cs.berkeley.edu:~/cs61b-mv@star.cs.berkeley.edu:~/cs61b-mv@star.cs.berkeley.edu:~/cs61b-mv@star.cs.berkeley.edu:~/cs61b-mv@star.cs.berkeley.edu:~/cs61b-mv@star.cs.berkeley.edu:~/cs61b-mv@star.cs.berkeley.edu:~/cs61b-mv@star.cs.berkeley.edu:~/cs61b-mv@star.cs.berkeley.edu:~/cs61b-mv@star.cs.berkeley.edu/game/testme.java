package game;



public class testme {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameBoard gameBoard = new GameBoard();
		System.out.println("gameBoard count side 0, this is black count" + gameBoard.getCount(0));
		System.out.println("gameBoard count side 1, this is white count " + gameBoard.getCount(1));
		System.out.println("gameBoard get max network side 0, this is black max" + gameBoard.getMaxNetwork(0));
		System.out.println("gameBoard get max network sid3 1, this is white max" + gameBoard.getMaxNetwork(1));
		System.out.println("gameBoard get chip boardArray");
		/*for (int i = 0; i < gameBoard.getHeight(); i++) {
			for (int j = 0; j < gameBoard.getWidth(); j++) {
				System.out.println("gameBoard chip x and y has " + i + j + gameBoard.getChip(i, j));
			}
		}
		*/
		
		/*
		gameBoard.addChip(new Chip(0,2,0));
		gameBoard.addChip(new Chip(0,3,0));
		gameBoard.addChip(new Chip(0,2,3));
		gameBoard.addChip(new Chip(0,1,3));
		gameBoard.addChip(new Chip(0,5,2));
		gameBoard.addChip(new Chip(0,6,3));
		gameBoard.addChip(new Chip(0,6,7));
		*/
		//System.out.println("Black Has valid network? " + gameBoard.hasValidNetwork(0));
		
		gameBoard = new GameBoard();			//white has a winning network
		gameBoard.addChip(new Chip(1,0,3));
		gameBoard.addChip(new Chip(1,2,3));
		gameBoard.addChip(new Chip(1,4,2));
		gameBoard.addChip(new Chip(1,2,5));
		gameBoard.addChip(new Chip(1,4,5));
		gameBoard.addChip(new Chip(1,7,2));	
	
		
		gameBoard.addChip(new Chip(0,2,0));
		gameBoard.addChip(new Chip(0,4,0));
		gameBoard.addChip(new Chip(0,6,0));
		gameBoard.addChip(new Chip(0,2,7));
		gameBoard.addChip(new Chip(0,2,2));

		System.out.println("|___|___|_O_|___|_O_|___|_O_|___|\n"
						  +"|___|___|___|___|___|___|___|___|\n"
						  +"|___|___|_O_|___|_X_|___|___|_X_|\n"
						  +"|_X_|___|_X_|___|___|___|___|___|\n"
						  +"|___|___|___|___|___|___|___|___|\n"
						  +"|___|___|_X_|___|_X_|___|___|___|\n"
						  +"|___|___|___|___|___|___|___|___|\n"
						  +"|___|___|_O_|___|___|___|___|___|");
		System.out.println("Black Has valid network? " + gameBoard.hasValidNetwork(0));
		System.out.println("White Has valid network? " + gameBoard.hasValidNetwork(1));

		/*
		gameBoard = new GameBoard();
		gameBoard.addChip(new Chip(0,2,0));
		gameBoard.addChip(new Chip(0,2,2));
		gameBoard.addChip(new Chip(0,5,1));
		gameBoard.addChip(new Chip(0,2,4));
		gameBoard.addChip(new Chip(0,1,5));
		gameBoard.addChip(new Chip(0,4,2));
		gameBoard.addChip(new Chip(0,4,4));
		gameBoard.addChip(new Chip(0,4,7));
		*/
		gameBoard = new GameBoard();	//white has network
		gameBoard.addChip(new Chip(1,0,2));
		gameBoard.addChip(new Chip(1,2,2));
		gameBoard.addChip(new Chip(1,2,4));
		gameBoard.addChip(new Chip(1,4,3));
		gameBoard.addChip(new Chip(1,5,4));
		gameBoard.addChip(new Chip(1,4,6));
		gameBoard.addChip(new Chip(1,5,6));
		gameBoard.addChip(new Chip(1,7,3));

		gameBoard.addChip(new Chip(0,1,0));
		gameBoard.addChip(new Chip(0,3,0));
		gameBoard.addChip(new Chip(0,1,3));
		gameBoard.addChip(new Chip(0,3,3));
		gameBoard.addChip(new Chip(0,3,7));
		gameBoard.addChip(new Chip(0,5,2));
		gameBoard.addChip(new Chip(0,6,1));
		System.out.println("|___|_O_|___|_O_|___|___|___|___|\n"
						  +"|___|___|___|___|___|___|_O_|___|\n"
						  +"|_X_|___|_X_|___|___|_O_|___|___|\n"
						  +"|___|_O_|___|_O_|_X_|___|___|_X_|\n"
						  +"|___|___|_X_|___|___|_X_|___|___|\n"
						  +"|___|___|___|___|___|___|___|___|\n"
						  +"|___|___|___|___|_X_|_X_|___|___|\n"
						  +"|___|___|___|_O_|___|___|___|___|");
		System.out.println("Black Has valid network? " + gameBoard.hasValidNetwork(0));
		System.out.println("White Has valid network? " + gameBoard.hasValidNetwork(1));
		
		
		
	}
}
		

     