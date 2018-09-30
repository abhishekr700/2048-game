package game2048;

import java.util.Scanner;

public class Game2048 {

    /* To start game */
	public static void main(String[] args)  {
		start();
	}

	/* To take input of Player's details */
	public static Player getPlayer(){
		Scanner s = new Scanner(System.in);

		System.out.println("Enter Name : ");
		String str = s.nextLine();

		Player p = new Player(str);

		return p;
	}
	
	public static void start()  {

	    // Player input details
	    Player p = getPlayer(); 

	    // Desired Board
	    Scanner s = new Scanner(System.in);
	    System.out.println("Enter board size:"); 

	    Board b = new Board(p);
	    b.printboard();

	    // To retrieve status of Player according to Board
	    while(b.getStatus() == b.INCOMPLETE){

		    //Enter desired position
		    System.out.println("Enter the position(left,right,up,down)");
		    String pos = s.next();

		    try{
			    b.move(pos);
			    b.printboard();
		    }catch(InvalidMoveException e){
			    System.out.println("Invalid Move !! ");
		    }
	    }

	    if(b.getStatus() == b.PLAYERWON){
		    System.out.println("Player Won");
	    }else{
		    System.out.println("GAMEOVER");
	    }
	}
}
