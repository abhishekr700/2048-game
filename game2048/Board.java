package game2048;
import java.util.Random;
import java.util.Scanner;

public class Board {

    int[][] board;
	Scanner s = new Scanner(System.in);
	
	int boardsize = s.nextInt();
	Player player;

	//Constructor - To Initialize Board
	public Board(Player player) {

	    this.player = player;
		
	    board = new int[boardsize][boardsize];

	    for(int i=0 ; i<boardsize ; i++){
		for(int j=0 ; j<boardsize ; j++){
			board[i][j] = 0;
		}
	    }
	}

	// Specifies Status of Player
	public int PLAYERWON = 1;
	public int INCOMPLETE = 2;
	public int GAME_OVER = 3;

	// Returns Current Status of Player according to Board
	public int getStatus(){

	    for(int i=0 ; i<boardsize ; i++){
		for(int j=0 ; j<boardsize ; j++){
		    /* When player reaches 2048, player wins */
		    if(board[i][j] == 1024)
                    {
                    	return PLAYERWON;
                    }
		}
	     }

	     for(int i=0 ; i<boardsize-1 ; i++){
		for(int j=0 ; j<boardsize-1 ; j++){
			    /* Whenever 2048 is not made and there are still matching numbers left Game is Incomplete */
				if(board[i][j] == board[i][j+1] || board[i][j] == board[i+1][j] )
					return INCOMPLETE;
			}
		}

		/* Whenever 2048 is not made and there is space to make moves Game is Incomplete*/
		for(int i=0 ; i<boardsize ; i++){
			for(int j=0 ; j<boardsize ; j++){
				if(board[i][j] == 0){
					return INCOMPLETE;
				}
			}
		}
        /* When all the above conditions fail Game gets Over */
		return GAME_OVER;
	}

	/* Check for Valid Move */
	public void move(String position) throws InvalidMoveException{
		
		if(position.equals("left")){
			leftMove();
		}else if(position.equals("right")){
			rightMove();
		}else if(position.equals("up")){
		    	upMove();
		}else if(position.equals("down")){
			downMove();
		}else{
			InvalidMoveException e = new InvalidMoveException();
			throw e;
		}
		
	}

	/* Player selects - Left Move */
	private void leftMove(){

	    int k=0;

        //Check if there is a row in which either matching number is found or empty cell(i,k) before non empty cell(i,j)
        for(int i=0 ; i<boardsize ; i++){
			for(int j=1 ; j<boardsize ; j++){
				if((board[i][j] != 0 && board[i][k] == board[i][j] ) || (board[i][k] == 0 && board[i][j]!=0)){
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

    /* Player selects - Right Move */
    private void rightMove(){

        int k=boardsize-1;

        //Check if there is a row in which either matching number is found or empty cell(i,k) before non empty cell(i,j)
        for(int i=0 ; i<boardsize ; i++){
			for(int j=k-1 ; j>=0 ;j-- ){
				if(((board[i][j] != 0) && (board[i][k] == board[i][j])) || ((board[i][k] == 0) && (board[i][j] != 0))){
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

    /* Player selects - Up Move */
    private void upMove(){
		int k=0;

        //Check if there is a column in which either matching number is found or empty cell(k,i) before non empty cell(j,i)
        for(int i=0 ; i<boardsize ; i++){
			for(int j=1 ; j<boardsize ; j++){
				if((board[j][i] != 0 && board[k][i] == board[j][i]) || (board[k][i] == 0 && board[j][i]!=0)){
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

    /* Player selects - Left Move */
	private void downMove(){
		int k=boardsize-1;

		//Check if there is a column in which either matching number is found or empty cell(k,i) before non empty cell(j,i)
		for(int i=0 ; i<boardsize ; i++){
			for(int j=k-1 ; j>=0 ; j--){
				if((board[j][i] != 0 && board[k][i] == board[j][i] ) || (board[k][i] == 0 && board[j][i]!=0)){
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

	/* To Print Board in every move*/
	public void printboard(){

	    int i,j,no;

	    Random ran = new Random(); // Generates Random Number

	    do{
		no = Math.random() > 0.1 ? 2:4;
			
		int max = boardsize-1;
		int min = 0;
			
		i = ran.nextInt(max-min)+1;
		j = ran.nextInt(max-min+1);

	    }while(board[i][j] != 0);

	    board[i][j] = no;

	    //Print Board
	    for( i=0 ; i<boardsize ; i++){
		System.out.println();
		for(j = 0 ; j<boardsize ; j++){
			
			System.out.print(" | "+ board[i][j] +" | ");
				
		}
		System.out.println();
	    }
	}
}
