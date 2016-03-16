package game;
import player.*;
import list.*;


public class testme {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameBoard gameBoard = new GameBoard();
		System.out.println("gameBoard count side 0, this is black count" + gameBoard.getCount(0));
		System.out.println("gameBoard count side 1, this is white count " + gameBoard.getCount(1));
		System.out.println("gameBoard get max network side 0, this is black max: " + gameBoard.getMaxNetwork(0));
		System.out.println("gameBoard get max network side 1, this is white max: " + gameBoard.getMaxNetwork(1));
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
		System.out.println(gameBoard.toString());


		//tests clusters
		MachinePlayer newPlayer = new MachinePlayer(0); //black side player
		newPlayer.setGame(gameBoard);
		Move newMove = new Move(6, 5);
		System.out.println("Player has valid move at (6,5): " + newPlayer.isValidMove(newPlayer.getGame() ,newMove, 1));
		System.out.println("Player has valid move at (6,5) should be: false");
		System.out.println("Black Has valid network? " + gameBoard.hasValidNetwork(0));
		System.out.println("White Has valid network? " + gameBoard.hasValidNetwork(1));
		
		System.out.println(gameBoard.toString());
		

		GameBoard gameBoard2 = new GameBoard();
		gameBoard2.addChip(new Chip(1,0,2));
		gameBoard2.addChip(new Chip(1,2,2));
		gameBoard2.addChip(new Chip(1,4,4));
		gameBoard2.addChip(new Chip(1,2,6));
		gameBoard2.addChip(new Chip(1,5,6));
		gameBoard2.addChip(new Chip(1,5,3));

		gameBoard2.addChip(new Chip(0,4,0));
		gameBoard2.addChip(new Chip(0,1,1));
		gameBoard2.addChip(new Chip(0,3,1));
		gameBoard2.addChip(new Chip(0,6,1));
		gameBoard2.addChip(new Chip(0,6,6));

		System.out.println(gameBoard2.toString());

		// MachinePlayer player2 = new MachinePlayer(0);
		// player2.setGame(gameBoard2);
		// Move newMove2 = player2.chooseMove();
		// System.out.println("Black chooses move: " + newMove2.toString());




		//placing a chip onto another chip
		GameBoard gb3 = new GameBoard();
		gb3.addChip(new Chip(1,0,2));
		Move newMove3 = new Move(0,2);
		MachinePlayer player3 = new MachinePlayer(1);
		
		player3.isValidMove(gb3, newMove3, 1);
		System.out.println("Move is valid: " + player3.isValidMove(gb3, newMove3, 1));
		System.out.println("Move is valid should be: false");


		/*NEW TEST*/
		//gameBoard = new GameBoard();
		//gameBoard.addChip
		






		//TESTING STEP BOARDS
		GameBoard gb4 = new GameBoard();
		gb4.addChip(new Chip(0,3,0));
		gb4.addChip(new Chip(0,4,0));
		gb4.addChip(new Chip(0,4,2));
		gb4.addChip(new Chip(0,6,2));
		gb4.addChip(new Chip(0,3,3));
		gb4.addChip(new Chip(0,3,5));
		gb4.addChip(new Chip(0,5,4));
		gb4.addChip(new Chip(0,5,5));
		gb4.addChip(new Chip(0,2,6));
		gb4.addChip(new Chip(0,4,7));
		
		gb4.addChip(new Chip(1,4,7));
		gb4.addChip(new Chip(1,0,3));
		gb4.addChip(new Chip(1,1,5));
		gb4.addChip(new Chip(1,2,4));
		gb4.addChip(new Chip(1,4,3));
		gb4.addChip(new Chip(1,4,5));
		gb4.addChip(new Chip(1,5,1));
		gb4.addChip(new Chip(1,5,6));
		gb4.addChip(new Chip(1,6,4));
		gb4.addChip(new Chip(1,7,5));

		System.out.println(gb4.toString());

		MachinePlayer p4 = new MachinePlayer(1); //white side
		Move nm4 = new Move(1,1,2,4); //moved to a valid location
		System.out.println("Move is valid: " + p4.isValidMove(gb4, nm4, 1));
		System.out.println("Move is valid should be: true");

		Move nm5 = new Move(2, 4, 1, 5); //moved into another chip\
		System.out.println("Move is valid: " + p4.isValidMove(gb4, nm5, 1));
		System.out.println("Move is valid should be: false");

		Move nm6 = new Move(5,3,3,2); //moved into cluster, should be false
		System.out.println("Move is valid: " + p4.isValidMove(gb4, nm6, 1));
		System.out.println("Move is valid should be: false");

		Move nm7 = new Move(2,1,2,2); //moving a blank chip
		System.out.println("Move is valid: " + p4.isValidMove(gb4, nm7, 1));
		System.out.println("Move is valid should be: false");


		//kevins test
		GameBoard game4 = new GameBoard(); 
		System.out.println(game4.toString()); 
		MachinePlayer player4 = new MachinePlayer(0); 
		player4.setGame(game4); 
		System.out.println("Opponent move (0,1) valid?"+player4.isValidMove(player4.getGame(), new Move(0,1), 1)); 
		System.out.println("Opponent Move (0,1):"+player4.opponentMove(new Move(0,1)));
	


		//sams tests for isvalidmove and validmovelist
		// TODO Auto-generated method stub
		   
		//isValidMove tests
		//add moves
		GameBoard gameBoard6 = new GameBoard();
		MachinePlayer playa = new MachinePlayer(1); //white side player 
			playa.setGame(gameBoard6);
		gameBoard6.addChip(new Chip(1,0,6));
		Move move1 = new Move(0, 6);
		System.out.println(gameBoard6.toString());
		System.out.println("is (0, 6) a valid white move? (should be false)" + playa.isValidMove(gameBoard6, move1, 1));
		System.out.println("is (0, 6) a valid black move? (should be false)" + playa.isValidMove(gameBoard6, move1, 0));
		gameBoard6.addChip(new Chip(1,1,2)); 
		gameBoard6.addChip(new Chip(0,2,4));  
		gameBoard6.addChip(new Chip(0,2,5));
		System.out.println(gameBoard.toString());
		Move move2 = new Move(2, 4);  //tests for chips be at move location
		System.out.println("is (2, 4) a valid white move? (should be false)" + playa.isValidMove(gameBoard6, move2, 1));
		System.out.println("is (2, 4) a valid black move? (should be false)" + playa.isValidMove(gameBoard6, move2, 0));
		Move move3 = new Move(2, 6);  //tests for chips being adjacent
		System.out.println("is (2, 6) a valid white move? (should be true)" + playa.isValidMove(gameBoard6, move3, 1));
		System.out.println("is (2, 6) a valid black move? (should be false)" + playa.isValidMove(gameBoard6, move3, 0));
		Move move4 = new Move(3, 5);  //tests for chips being adjacent (special case of 1 adjacent)
			System.out.println("is (3, 5) a valid white move? (should be true)" + playa.isValidMove(gameBoard6, move4, 1));
		System.out.println("is (3, 5) a valid black move? (should be false)" + playa.isValidMove(gameBoard6, move4, 0));

		gameBoard6.addChip(new Chip(1,1,3));
		Move move5 = new Move(2, 2);  //tests for chips being adjacent
			System.out.println("is (2, 2) a valid white move? (should be false)" + playa.isValidMove(gameBoard6, move5, 1));
		System.out.println("is (2, 2) a valid black move? (should be true)" + playa.isValidMove(gameBoard6, move5, 0));
		Move move6 = new Move(1, 4);  //tests for chips being adjacent
			System.out.println("is (1, 4) a valid white move? (should be false)" + playa.isValidMove(gameBoard6, move6, 1));
		System.out.println("is (1, 4) a valid black move? (should be false)" + playa.isValidMove(gameBoard6, move6, 0));

		//	gameBoard.addChip(new Chip(1,5,4));
		//gameBoard.addChip(new Chip(1,4,6));
		//gameBoard.addChip(new Chip(1,5,6));
		//gameBoard.addChip(new Chip(1,7,3));



		//test3 forcemove step
		GameBoard gb7 = new GameBoard();
		gb7.addChip(new Chip(0,4,0));
		gb7.addChip(new Chip(0,2,1));
		gb7.addChip(new Chip(0,1,7));
		gb7.addChip(new Chip(0,3,2));
		gb7.addChip(new Chip(0,5,2));
		gb7.addChip(new Chip(0,6,3));
		gb7.addChip(new Chip(0,2,4));
		gb7.addChip(new Chip(0,3,5));
		gb7.addChip(new Chip(0,5,5));
		gb7.addChip(new Chip(0,6,6));

		gb7.addChip(new Chip(1,0,6));
		gb7.addChip(new Chip(1,1,1));
		gb7.addChip(new Chip(1,1,4));
		gb7.addChip(new Chip(1,2,2));
		gb7.addChip(new Chip(1,2,5));
		gb7.addChip(new Chip(1,4,2));
		gb7.addChip(new Chip(1,4,5));
		gb7.addChip(new Chip(1,5,3));
		gb7.addChip(new Chip(1,5,6));
		gb7.addChip(new Chip(1,6,1));

		System.out.println(gb7.toString());
		MachinePlayer mp7 = new MachinePlayer(0);
		mp7.setGame(gb7);

		String stepString = new String();
		DList validSteps = mp7.validStepMoves(mp7.getGame(),mp7.getSide());
		System.out.println("number of valid steps:"+ validSteps.length());
		DListNode curr = validSteps.front();
		while (curr!=null){
			stepString = stepString + (Move) curr.item;
			curr = validSteps.next(curr);
		}

		System.out.println("valid steps:"+stepString);
		Move m7 = new Move(1, 7, 1, 0);
		System.out.println("Step move from 10 to 17 is valid: " + mp7.isValidMove(gb7, m7, 0));
		System.out.println("Force step move from 10 to 17: " + mp7.forceMove(m7));
		System.out.println("ChooseMove():"+ mp7.chooseMove());

		//test3 forcemove step
		GameBoard gb8 = new GameBoard();
		gb8.addChip(new Chip(0,4,0));
		gb8.addChip(new Chip(0,2,1));
		gb8.addChip(new Chip(0,1,7));
		gb8.addChip(new Chip(0,3,2));
		gb8.addChip(new Chip(0,5,2));
		gb8.addChip(new Chip(0,6,3));
		gb8.addChip(new Chip(0,2,4));
		gb8.addChip(new Chip(0,3,5));
		gb8.addChip(new Chip(0,5,5));
		gb8.addChip(new Chip(0,6,6));

		System.out.println(gb8.toString());
		MachinePlayer mp8 = new MachinePlayer(0);
		mp8.setGame(gb7);

		String stepString2 = new String();
		DList validSteps2 = mp7.validStepMoves(mp7.getGame(),mp7.getSide());
		DListNode curr2 = validSteps.front();
		while (curr2!=null){
			stepString = stepString + (Move) curr2.item;
			curr2 = validSteps.next(curr2);
		}
		System.out.println("number of valid steps:"+ validSteps2.length());
		System.out.println("valid steps:"+stepString);
		Move m8 = new Move(1, 7, 1, 0);
		System.out.println("Step move from 10 to 17 is valid: " + mp8.isValidMove(gb7, m8, 0));
		System.out.println("Force step move from 10 to 17: " + mp8.forceMove(m8));
		System.out.println("ChooseMove():"+ mp8.chooseMove());
	}
}
		

     
