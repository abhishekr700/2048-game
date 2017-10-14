package game2048;
import java.util.Random;
import java.util.Scanner;

import tictactoe.InvalidMoveException;



public class Board {
	
	int[][] board;
	Scanner s = new Scanner(System.in);
	
	int boardsize = s.nextInt();
	Player player;
	public Board(Player player) {
		this.player = player;
		
		board = new int[boardsize][boardsize];
		for(int i=0 ; i<boardsize ; i++){
			for(int j=0 ; j<boardsize ; j++){
				board[i][j] = 0;
			}
		}
	}
	public int PLAYERWON = 1;
	public int INCOMPLETE = 2;
	public int GAME_OVER = 3	;
	public int getStatus(){
		for(int i=0 ; i<boardsize ; i++){
			for(int j=0 ; j<boardsize ; j++){
				if(board[i][j] == 1024)
					return PLAYERWON;
			}
		}
		for(int i=0 ; i<boardsize-1 ; i++){
			for(int j=0 ; j<boardsize-1 ; j++){
				if(board[i][j] == board[i][j+1] || board[i][j] == board[i+1][j] )// || board[i+1][j+1] == 0)
					return INCOMPLETE;
			}
		}
		for(int i=0 ; i<boardsize ; i++){
			for(int j=0 ; j<boardsize ; j++){
				if(board[i][j] == 0){
					return INCOMPLETE;
				}
			}
		}
		return GAME_OVER;
	}
	
	public void move(String pos) throws InvalidMoveException{
		
		if(pos.equals("left")){
			leftm();
			
		}else if(pos.equals("right")){
		
			rightm();
		}else if(pos.equals("up")){
		upm();
	
		}else if(pos.equals("down")){
				downm();
				
		}else{
			InvalidMoveException e = new InvalidMoveException();
			//System.out.println("Invalid Move || Try again");
			throw e;
			
		}
		
	}
	public void leftm(){
		int k=0;
		for(int i=0 ; i<boardsize ; i++){
			for(int j=1 ; j<boardsize ; j++){
				if((board[i][j] != 0 && board[i][k] == board[i][j] ) || (board[i][k] == 0&&board[i][j]!=0)){
					board[i][k] += board[i][j];
					board[i][j] = 0;
					k++;
				}else{
					if(board[i][j] != 0){
						k++;
					}
				}
			}
			k=0;
		}
	}
	public void rightm(){
		int k=boardsize-1;
		for(int i=0 ; i<boardsize ; i++){
			for(int j=k-1 ; j>=0 ;j-- ){
				if((board[i][j] != 0 && board[i][k] == board[i][j] ) || (board[i][k] == 0&&board[i][j]!=0)){
					board[i][k] += board[i][j];
					board[i][j] = 0;
					k--;
					
				}else{
					if(board[i][j] != 0){
						k--;
					}
					
				}
			}
			k=boardsize-1;
		}
	}
	public void upm(){
		int k=0;
		for(int i=0 ; i<boardsize ; i++){
			for(int j=1 ; j<boardsize ; j++){
				if((board[j][i] != 0 && board[k][i] == board[j][i]) || (board[k][i] == 0&&board[j][i]!=0)){
					board[k][i] += board[j][i];
					board[j][i] = 0;
					k++;
				}else{
					if(board[j][i] != 0){
						k++;
					}
					
				}
			}
			k=0;
		}
	}
	public void downm(){
		int k=boardsize-1;
		for(int i=0 ; i<boardsize ; i++){
			for(int j=k-1 ; j>=0 ; j--){
				if((board[j][i] != 0 && board[k][i] == board[j][i] ) || (board[k][i] == 0&&board[j][i]!=0)){
					board[k][i] += board[j][i];
					board[j][i] = 0;
					k--;
				}else{
					if(board[j][i] != 0){
						k--;
					}
					
				}
			}
			k=boardsize-1;
		}
	}
	public void printboard(){
		int i,j,no;
		Random ran = new Random();
		do{

		
			 no = Math.random() > 0.1 ? 2:4;
			
			int max = boardsize-1;
			int min = 0;
			
			i = ran.nextInt(max-min)+1;
			j = ran.nextInt(max-min+1);
		

		}while(board[i][j] != 0);
		board[i][j] = no;
		for( i=0 ; i<boardsize ; i++){
			System.out.println();
			for(j = 0 ; j<boardsize ; j++){
			
					System.out.print(" | "+ board[i][j] +" | ");
				
			}
			System.out.println();
		}
	}
}
