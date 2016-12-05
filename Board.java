public class Board{
	public char board[][];
	public int rows;
	public int cols;
	public char p1='o';
	public char p2='t';
	public int totalplays;
	public int stack[][];
	public int winnerID=0;
	
	public static void main(String[] args){
		
		}
	
	public Board(){
		board=new char[6][7];
		for (int i=0; i<board.length; i++){
				for(int j=0; j<board[0].length; j++){
				board[i][j]=' ';
				}
		}
		rows=6;
		cols=7;
		stack=new int[1][7];
		for (int k=0; k<7; k++){
			stack[0][k]=0;
		}
	}
	
	public Board(int row, int col){
		this.rows= row;
		this.cols=col;
		if(rows>=4&&cols>=4){
		board=new char[rows][cols];
		for (int i=0; i<board.length; i++){
				for(int j=0; j<board[0].length; j++){
				board[i][j]=' ';
				}
			}
		stack=new int[1][cols];
		for (int k=0; k<cols; k++){
			stack[0][k]=0;
			}
		}
		else{
			
		}
	}
	
	public int getNumRows(){
		return rows;
	}
	
	public int getNumCols(){
		return cols;
	}
	
	public char getPlayerOne(){
		return p1;
	}
	
	public char getPlayerTwo(){
		return p2;
	}
	
	public void setPlayerOne(char o){
		p1=o;
	}
	
	public void setPlayerTwo(char t){
		p2=t;
	}
	
	public char getToken(int row, int col){
		char token;
		if(row<rows && col<cols){
			token=board[row][col];
		}
		else{
			token='\0';
		}
		return token;
	}
		
	public boolean canPlay(){
		boolean canPlay;
		if(totalplays<rows*cols){
			canPlay=true;
		}
		else{ 
			canPlay=false;
			}
		return canPlay;
	}
	
	public boolean play(int p,int c){
		boolean played=false;
		if(c<board[0].length && winnerID==0 && (p==1 || p==2)){
			int s=stack[0][c];
			int prow=board.length-s-1;
			int rdc=0;
			int ldc=0;
			int hc=0;
			int vc=0;
			if(s<board.length){
				if(p==1){
					board[prow][c]=p1;
				}
				else if(p==2){
					board[prow][c]=p2;
				}
			played=true;
			totalplays++;
			stack[0][c]=s+1;
			if(prow+1<rows && c-1>=0 && board[prow][c]==board[prow+1][c-1]){
				rdc=2;
				if(prow+2<rows && c-2>=0 && board[prow+1][c-1]==board[prow+2][c-2]){
					rdc++;
					if(prow+3<rows && c-3>=0 && board[prow+2][c-2]==board[prow+3][c-3]){
						rdc++;
					}
				}	
			}
			if (rdc<4 && prow-1>=0 && c+1<cols && board[prow][c]==board[prow-1][c+1]){
				rdc++;
				if(rdc<4 && prow-2>=0 && c+2<cols && board[prow-1][c+1]==board[prow-2][c+2]){
					rdc++;
					if(rdc<4 && prow-3>=0 && c+3<cols && board[prow-2][c+2]==board[prow-3][c+3]){
						rdc++;
					}
				}	
			}//End check right diag winner
			if(rdc<4 && prow+1<rows && c+1<cols && board[prow][c]==board[prow+1][c+1]){
				ldc=2;
				if(prow+2<rows && c+2<cols && board[prow+1][c+1]==board[prow+2][c+2]){
					ldc++;
					if(prow+3<rows && c+3<cols && board[prow+2][c+2]==board[prow+3][c+3]){
						ldc++;
					}
				}	
			}
			if (rdc<4 && ldc<4 && prow-1>=0 && c-1>=0 && board[prow][c]==board[prow-1][c-1]){
				ldc++;
				if(ldc<4 && prow-2>=0 && c-2>=0 && board[prow-1][c-1]==board[prow-2][c-2]){
					ldc++;
					if(ldc<4 && prow-3>=0 && c-3>=0 && board[prow-2][c-2]==board[prow-3][c-3]){
						ldc++;
					}
				}	
			}//End check left diag winner
			if(rdc<4 && ldc<4 && c-1>=0 && board[prow][c]==board[prow][c-1]){
				hc=2;
				if(c-2>=0 && board[prow][c-1]==board[prow][c-2]){
					hc++;
					if(c-3>=0 && board[prow][c-2]==board[prow][c-3]){
						hc++;
					}
				}
			}
			if(rdc<4 && ldc<4 && hc<4 && c+1<cols && board[prow][c]==board[prow][c+1]){
					hc++;
					if(c+2<cols && board[prow][c+1]==board[prow][c+2]){
						hc++;
						if(c+3<cols && board[prow][c+2]==board[prow][c+3]){
							hc++;
					}
				}
			}//End check horiz winner
			if(rdc<4 && ldc<4 && hc<4 && prow+1<rows && board[prow][c]==board[prow+1][c]){
				vc=2;
				if(prow+2<rows && board[prow+1][c]==board[prow+2][c]){
					vc++;
					if(prow+3<rows && board[prow+2][c]==board[prow+3][c]){
						vc++;
					}
				}
			}//End check vert winner
		if(rdc>=4 || ldc>=4 || vc>=4 || hc>=4){
			if(board[prow][c]==p1){
			winnerID=1;
			}
			else if(board[prow][c]==p2){
			winnerID=2;
				}
			}
		}	
	}
		else{
			played=false;
		}
	
	return played;
}	
	
	public int isFinished(){
		int fin=-1;
		if(winnerID==0 && totalplays>=(rows*cols)){
			fin=0;
		}
		else if(winnerID!=0){
			fin=winnerID;
		}
		else{
			fin=-1;
		}
		return fin;
	}
	
	public void printBoard(){
		for (int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
			System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public void setBoardChar(){
		for (int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
			board[i][j]='x';
			}
		}
	}
	
}
	
