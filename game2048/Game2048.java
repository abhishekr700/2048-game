package game2048;

import java.util.Scanner;

import tictactoe.InvalidMoveException;

public class Game2048 {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		start();
	}
	public static Player getPlayer(){
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Name : ");
		String str = s.nextLine();
		Player p = new Player(str);
		return p;
	}
	
	public static void start()  {
		Player p = getPlayer();
		Scanner s = new Scanner(System.in);
		System.out.println("Enter board size:");
		Board b = new Board(p);
		b.printboard();
		while(b.getStatus() == b.INCOMPLETE){
		
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
