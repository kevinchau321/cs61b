package game;
import player.*;

public class testMe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   
		//isValidMove tests
		//add moves
		GameBoard gameBoard = new GameBoard();
		MachinePlayer playa = new MachinePlayer(1); //white side player 
			playa.setGame(gameBoard);
		gameBoard.addChip(new Chip(1,0,6));
		Move move1 = new Move(0, 6);
		System.out.println(gameBoard.toString());
		System.out.println("is (0, 6) a valid white move? (should be false)" + playa.isValidMove(gameBoard, move1, 1));
		System.out.println("is (0, 6) a valid black move? (should be false)" + playa.isValidMove(gameBoard, move1, 0));
		gameBoard.addChip(new Chip(1,1,2)); 
		gameBoard.addChip(new Chip(0,2,4));  
		gameBoard.addChip(new Chip(0,2,5));
		System.out.println(gameBoard.toString());
		Move move2 = new Move(2, 4);  //tests for chips be at move location
		System.out.println("is (2, 4) a valid white move? (should be false)" + playa.isValidMove(gameBoard, move2, 1));
		System.out.println("is (2, 4) a valid black move? (should be false)" + playa.isValidMove(gameBoard, move2, 0));
		Move move3 = new Move(2, 6);  //tests for chips being adjacent
		System.out.println("is (2, 6) a valid white move? (should be true)" + playa.isValidMove(gameBoard, move3, 1));
		System.out.println("is (2, 6) a valid black move? (should be false)" + playa.isValidMove(gameBoard, move3, 0));
		Move move4 = new Move(3, 5);  //tests for chips being adjacent (special case of 1 adjacent)
			System.out.println("is (3, 5) a valid white move? (should be true)" + playa.isValidMove(gameBoard, move4, 1));
		System.out.println("is (3, 5) a valid black move? (should be false)" + playa.isValidMove(gameBoard, move4, 0));

		gameBoard.addChip(new Chip(1,1,3));
		Move move5 = new Move(2, 2);  //tests for chips being adjacent
			System.out.println("is (2, 2) a valid white move? (should be false)" + playa.isValidMove(gameBoard, move5, 1));
		System.out.println("is (2, 2) a valid black move? (should be true)" + playa.isValidMove(gameBoard, move5, 0));
		Move move6 = new Move(1, 4);  //tests for chips being adjacent
			System.out.println("is (1, 4) a valid white move? (should be false)" + playa.isValidMove(gameBoard, move6, 1));
		System.out.println("is (1, 4) a valid black move? (should be false)" + playa.isValidMove(gameBoard, move6, 0));

		//	gameBoard.addChip(new Chip(1,5,4));
		//gameBoard.addChip(new Chip(1,4,6));
		//gameBoard.addChip(new Chip(1,5,6));
		//gameBoard.addChip(new Chip(1,7,3));



	}
}
		

	 
