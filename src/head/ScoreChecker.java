package head;

public class ScoreChecker {

	//score�鿡 ���� ����
	public static int WIN = Integer.MAX_VALUE;
	public static int LOSE = Integer.MIN_VALUE;
	public static int ONESTONE=100;
	public static int TWOSTONE=500;
	public static int THREESTONE=1400;
	
	public static int getWinner(int[][] board, int stone_num) {

		//Check for vertical win
		for(int row = 5;row >= 3;row--) {
			for(int col = 0;col < 7;col++) {

				if(board[row][col] == 1 && board[row - 1][col] == 1 && board[row - 2][col] == 1 && board[row - 3][col] == 1) {
					return 1;
				} else if(board[row][col] == 2 && board[row - 1][col] == 2 && board[row - 2][col] == 2 && board[row - 3][col] == 2) {
					return 2;
				}
			}
		}

		//check for horizontal win
		for(int row = 0;row < 6;row++) {
			for(int col = 0;col <= 3;col++) {
				if(board[row][col] == 1 && board[row][col + 1] == 1 && board[row][col + 2] == 1 && board[row][col + 3] == 1) {
					return 1;
				} else if(board[row][col] == 2 && board[row][col + 1] == 2 && board[row][col + 2] == 2 && board[row][col + 3] == 2) {
					return 2;
				}
			}
		}

		//check for diagonal win
		for(int row = 0;row <= 2;row++) {
			for(int col = 0;col < 4;col++) {
				if(board[row][col] == 1 && board[row + 1][col + 1] == 1 && board[row + 2][col + 2] == 1 && board[row + 3][col + 3] == 1) {
					return 1;
				} else if(board[row][col] == 2 && board[row + 1][col+ 1] == 2 && board[row + 2][col + 2] == 2 && board[row + 3][col + 3] == 2) {
					return 2;
				}
			}
		}

		for(int row = 0;row <= 2;row++) {
			for(int col = 6;col >= 3;col--) {
				if(board[row][col] == 1 && board[row + 1][col - 1] == 1 && board[row + 2][col - 2] == 1 && board[row + 3][col - 3] == 1) {
					return 1;
				} else if(board[row][col] == 2 && board[row + 1][col - 1] == 2 && board[row + 2][col - 2] == 2 && board[row + 3][col - 3] == 2) {
					return 2;
				}
			}
		}

		if(stone_num >=42) return 3; //���ºθ� ����

		return 0;
	}

	/*
	 * �� ���� ���� �� �ٿ� �����ִ� ���� �˻��Ѵ�.
	 * @input: player = ������ ���忡�� ����ϴ����� ���Ѵ�.
	 * @input: board = ���� ���� board�� ���¸� �ǹ��Ѵ�.
	 */
	
	public static int getScore(int player, int[][] board) {
		int resultScore = 0;
		
		int temp = 0;
		
		temp = checkHorizontalScore(player,board);
		if(temp == WIN || temp == LOSE)
			return temp;
		
		resultScore+=temp;
		
		temp = checkVerticalScore(player,board);
		if(temp == WIN || temp == LOSE)
			return temp;
		
		resultScore+=temp;
		
		temp = checkLRDiagonalScore(player,board);
		if(temp == WIN || temp == LOSE)
			return temp;
		
		resultScore+=temp;
		
		temp = checkRLDiagonalScore(player,board);
		if(temp == WIN || temp == LOSE)
			return temp;
		
		resultScore+=temp;
		
		return resultScore;
	}
	
	//���� ���� ���� ������ üũ�ؼ� ������ ����.
	public static int checkHorizontalScore(int player, int[][] board) {
		int resultScore = 0;
		int enemy = (player == 1)?2:1;

		for(int row = 0;row < 6;row++) {
			for(int col = 0;col <= 3;col++) {

				//�� 1�� ���� �� üũ
				if(board[row][col] == enemy && board[row][col + 1] == 0 && board[row][col + 2] == 0 && board[row][col + 3] == 0)
					resultScore = resultScore - ONESTONE;

				if(board[row][col] == 0 && board[row][col + 1] == enemy && board[row][col + 2] == 0 && board[row][col + 3] == 0) 
					resultScore = resultScore - ONESTONE;

				if(board[row][col] == 0 && board[row][col + 1] == 0 && board[row][col + 2] == enemy && board[row][col + 3] == 0) 
					resultScore = resultScore - ONESTONE;

				if(board[row][col] == 0 && board[row][col + 1] == 0 && board[row][col + 2] == 0 && board[row][col + 3] == enemy) 
					resultScore = resultScore - ONESTONE;

				if(board[row][col] == player && board[row][col + 1] == 0 && board[row][col + 2] == 0 && board[row][col + 3] == 0) 
					resultScore = resultScore + ONESTONE;

				if(board[row][col] == 0 && board[row][col + 1] == player && board[row][col + 2] == 0 && board[row][col + 3] == 0) 
					resultScore = resultScore + ONESTONE;

				if(board[row][col] == 0 && board[row][col + 1] == 0 && board[row][col + 2] == player && board[row][col + 3] == 0) 
					resultScore = resultScore + ONESTONE;

				if(board[row][col] == 0 && board[row][col + 1] == 0 && board[row][col + 2] == 0 && board[row][col + 3] == player) 
					resultScore = resultScore + ONESTONE;

				//�� 2�� ���� �� üũ
				if(board[row][col] == enemy && board[row][col + 1] == enemy && board[row][col + 2] == 0 && board[row][col + 3] == 0)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == 0 && board[row][col + 1] == enemy && board[row][col + 2] == enemy && board[row][col + 3] == 0) 
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == 0 && board[row][col + 1] == 0 && board[row][col + 2] == enemy && board[row][col + 3] == enemy)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == enemy && board[row][col + 1] == 0 && board[row][col + 2] == enemy && board[row][col + 3] == 0)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == 0 && board[row][col + 1] == enemy && board[row][col + 2] == 0 && board[row][col + 3] == enemy)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == enemy && board[row][col + 1] == 0 && board[row][col + 2] == 0 && board[row][col + 3] == enemy)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == player && board[row][col + 1] == player && board[row][col + 2] == 0 && board[row][col + 3] == 0)
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == 0 && board[row][col + 1] == player && board[row][col + 2] == player && board[row][col + 3] == 0) 
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == 0 && board[row][col + 1] == 0 && board[row][col + 2] == player && board[row][col + 3] == player) 
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == player && board[row][col + 1] == 0 && board[row][col + 2] == player && board[row][col + 3] == 0) 
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == 0 && board[row][col + 1] == player && board[row][col + 2] == 0 && board[row][col + 3] == player) 
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == player && board[row][col + 1] == 0 && board[row][col + 2] == 0 && board[row][col + 3] == player)
					resultScore = resultScore + TWOSTONE;

				//�� 3�� ���� �� üũ
				if(board[row][col] == enemy && board[row][col + 1] == enemy && board[row][col + 2] == enemy && board[row][col + 3] == 0) 
					resultScore = resultScore - THREESTONE;

				if(board[row][col] == enemy && board[row][col + 1] == enemy && board[row][col + 2] == 0 && board[row][col + 3] == enemy) 
					resultScore = resultScore - THREESTONE;

				if(board[row][col] == enemy && board[row][col + 1] == 0 && board[row][col + 2] == enemy && board[row][col + 3] == enemy) 
					resultScore = resultScore - THREESTONE;

				if(board[row][col] == 0 && board[row][col + 1] == enemy && board[row][col + 2] == enemy && board[row][col + 3] == enemy) 
					resultScore = resultScore - THREESTONE;

				if(board[row][col] == player && board[row][col + 1] == player && board[row][col + 2] == player && board[row][col + 3] == 0) 
					resultScore = resultScore + THREESTONE;

				if(board[row][col] == player && board[row][col + 1] == player && board[row][col + 2] == 0 && board[row][col + 3] == player) 
					resultScore = resultScore + THREESTONE;

				if(board[row][col] == player && board[row][col + 1] == 0 && board[row][col + 2] == player && board[row][col + 3] == player) 
					resultScore = resultScore + THREESTONE;

				if(board[row][col] == 0 && board[row][col + 1] == player && board[row][col + 2] == player && board[row][col + 3] == player) 
					resultScore = resultScore + THREESTONE;

				if(board[row][col] == player && board[row][col + 1] == player && board[row][col + 2] == player && board[row][col + 3] == player) 
					return WIN;
				if(board[row][col] == enemy && board[row][col + 1] == enemy && board[row][col + 2] == enemy && board[row][col + 3] == enemy) 
					return LOSE;
				
			}
		}

		return resultScore;
	}
	
	//������ �� ���� ���� ������ ���� ������ ����.
	public static int checkVerticalScore(int player, int[][] board) {
		int resultScore = 0;
		int enemy = (player == 1)?2:1;

		for(int row = 5;row >= 3;row--) {
			for(int col = 0;col < 7;col++) {

				//�� 1�� ���� �� üũ
				if(board[row][col] == enemy && board[row-1][col] == 0 && board[row-2][col] == 0 && board[row-3][col] == 0)
					resultScore = resultScore - ONESTONE;

				if(board[row][col] == 0 && board[row-1][col] == enemy && board[row-2][col] == 0 && board[row-3][col] == 0) 
					resultScore = resultScore - ONESTONE;

				if(board[row][col] == 0 && board[row-1][col] == 0 && board[row-2][col] == enemy && board[row-3][col] == 0) 
					resultScore = resultScore - ONESTONE;

				if(board[row][col] == 0 && board[row-1][col] == 0 && board[row-2][col] == 0 && board[row-3][col] == enemy) 
					resultScore = resultScore - ONESTONE;

				if(board[row][col] == player && board[row-1][col] == 0 && board[row-2][col] == 0 && board[row-3][col] == 0) 
					resultScore = resultScore + ONESTONE;

				if(board[row][col] == 0 && board[row-1][col] == player && board[row-2][col] == 0 && board[row-3][col] == 0) 
					resultScore = resultScore + ONESTONE;

				if(board[row][col] == 0 && board[row-1][col] == 0 && board[row-2][col] == player && board[row-3][col] == 0) 
					resultScore = resultScore + ONESTONE;

				if(board[row][col] == 0 && board[row-1][col] == 0 && board[row-2][col] == 0 && board[row-3][col] == player) 
					resultScore = resultScore + ONESTONE;

				//�� 2�� ���� �� üũ
				if(board[row][col] == enemy && board[row-1][col] == enemy && board[row-2][col] == 0 && board[row-3][col] == 0)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == 0 && board[row-1][col] == enemy && board[row-2][col] == enemy && board[row-3][col] == 0) 
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == 0 && board[row-1][col] == 0 && board[row-2][col] == enemy && board[row-3][col] == enemy)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == enemy && board[row-1][col] == 0 && board[row-2][col] == enemy && board[row-3][col] == 0)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == 0 && board[row-1][col] == enemy && board[row-2][col] == 0 && board[row-3][col] == enemy)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == enemy && board[row-1][col] == 0 && board[row-2][col] == 0 && board[row-3][col] == enemy)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == player && board[row-1][col] == player && board[row-2][col] == 0 && board[row-3][col] == 0)
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == 0 && board[row-1][col] == player && board[row-2][col] == player && board[row-3][col] == 0) 
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == 0 && board[row-1][col] == 0 && board[row-2][col] == player && board[row-3][col] == player) 
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == player && board[row-1][col] == 0 && board[row-2][col] == player && board[row-3][col] == 0) 
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == 0 && board[row-1][col] == player && board[row-2][col] == 0 && board[row-3][col] == player) 
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == player && board[row-1][col] == 0 && board[row-2][col] == 0 && board[row-3][col] == player)
					resultScore = resultScore + TWOSTONE;

				//�� 3�� ���� �� üũ
				if(board[row][col] == enemy && board[row-1][col] == enemy && board[row-2][col] == enemy && board[row-3][col] == 0) 
					resultScore = resultScore - THREESTONE;

				if(board[row][col] == enemy && board[row-1][col] == enemy && board[row-2][col] == 0 && board[row-3][col] == enemy) 
					resultScore = resultScore - THREESTONE;

				if(board[row][col] == enemy && board[row-1][col] == 0 && board[row-2][col] == enemy && board[row-3][col] == enemy) 
					resultScore = resultScore - THREESTONE;

				if(board[row][col] == 0 && board[row-1][col] == enemy && board[row-2][col] == enemy && board[row-3][col] == enemy) 
					resultScore = resultScore - THREESTONE;

				if(board[row][col] == player && board[row-1][col] == player && board[row-2][col] == player && board[row-3][col] == 0) 
					resultScore = resultScore + THREESTONE;

				if(board[row][col] == player && board[row-1][col] == player && board[row-2][col] == 0 && board[row-3][col] == player) 
					resultScore = resultScore + THREESTONE;

				if(board[row][col] == player && board[row-1][col] == 0 && board[row-2][col] == player && board[row-3][col] == player) 
					resultScore = resultScore + THREESTONE;

				if(board[row][col] == 0 && board[row-1][col] == player && board[row-2][col] == player && board[row-3][col] == player) 
					resultScore = resultScore + THREESTONE;

				if(board[row][col] == player && board[row-1][col] == player && board[row-2][col] == player && board[row-3][col] == player) 
					return WIN;
				if(board[row][col] == enemy && board[row-1][col] == enemy && board[row-2][col] == enemy && board[row-3][col] == enemy) 
					return LOSE;
				
			}
		}

		return resultScore;
	}
	
	//�»��->���ϴ� �밢���� ���� �� üũ
	public static int checkLRDiagonalScore(int player, int[][] board) {
		int resultScore = 0;
		int enemy = (player == 1)?2:1;

		for(int row = 0;row <= 2;row++) {
			for(int col = 6;col >= 3;col--) {

				//�� 1�� ���� �� üũ
				if(board[row][col] == enemy && board[row + 1][col - 1] == 0 && board[row + 2][col - 2] == 0 && board[row + 3][col - 3] == 0)
					resultScore = resultScore - ONESTONE;

				if(board[row][col] == 0 && board[row + 1][col - 1] == enemy && board[row + 2][col - 2] == 0 && board[row + 3][col - 3] == 0) 
					resultScore = resultScore - ONESTONE;

				if(board[row][col] == 0 && board[row + 1][col - 1] == 0 && board[row + 2][col - 2] == enemy && board[row + 3][col - 3] == 0) 
					resultScore = resultScore - ONESTONE;

				if(board[row][col] == 0 && board[row + 1][col - 1] == 0 && board[row + 2][col - 2] == 0 && board[row + 3][col - 3] == enemy) 
					resultScore = resultScore - ONESTONE;

				if(board[row][col] == player && board[row + 1][col - 1] == 0 && board[row + 2][col - 2] == 0 && board[row + 3][col - 3] == 0) 
					resultScore = resultScore + ONESTONE;

				if(board[row][col] == 0 && board[row + 1][col - 1] == player && board[row + 2][col - 2] == 0 && board[row + 3][col - 3] == 0) 
					resultScore = resultScore + ONESTONE;

				if(board[row][col] == 0 && board[row + 1][col - 1] == 0 && board[row + 2][col - 2] == player && board[row + 3][col - 3] == 0) 
					resultScore = resultScore + ONESTONE;

				if(board[row][col] == 0 && board[row + 1][col - 1] == 0 && board[row + 2][col - 2] == 0 && board[row + 3][col - 3] == player) 
					resultScore = resultScore + ONESTONE;

				//�� 2�� ���� �� üũ
				if(board[row][col] == enemy && board[row + 1][col - 1] == enemy && board[row + 2][col - 2] == 0 && board[row + 3][col - 3] == 0)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == 0 && board[row + 1][col - 1] == enemy && board[row + 2][col - 2] == enemy && board[row + 3][col - 3] == 0) 
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == 0 && board[row + 1][col - 1] == 0 && board[row + 2][col - 2] == enemy && board[row + 3][col - 3] == enemy)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == enemy && board[row + 1][col - 1] == 0 && board[row + 2][col - 2] == enemy && board[row + 3][col - 3] == 0)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == 0 && board[row + 1][col - 1] == enemy && board[row + 2][col - 2] == 0 && board[row + 3][col - 3] == enemy)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == enemy && board[row + 1][col - 1] == 0 && board[row + 2][col - 2] == 0 && board[row + 3][col - 3] == enemy)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == player && board[row + 1][col - 1] == player && board[row + 2][col - 2] == 0 && board[row + 3][col - 3] == 0)
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == 0 && board[row + 1][col - 1] == player && board[row + 2][col - 2] == player && board[row + 3][col - 3] == 0) 
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == 0 && board[row + 1][col - 1] == 0 && board[row + 2][col - 2] == player && board[row + 3][col - 3] == player) 
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == player && board[row + 1][col - 1] == 0 && board[row + 2][col - 2] == player && board[row + 3][col - 3] == 0) 
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == 0 && board[row + 1][col - 1] == player && board[row + 2][col - 2] == 0 && board[row + 3][col - 3] == player) 
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == player && board[row + 1][col - 1] == 0 && board[row + 2][col - 2] == 0 && board[row + 3][col - 3] == player)
					resultScore = resultScore + TWOSTONE;

				//�� 3�� ���� �� üũ
				if(board[row][col] == enemy && board[row + 1][col - 1] == enemy && board[row + 2][col - 2] == enemy && board[row + 3][col - 3] == 0) 
					resultScore = resultScore - THREESTONE;

				if(board[row][col] == enemy && board[row + 1][col - 1] == enemy && board[row + 2][col - 2] == 0 && board[row + 3][col - 3] == enemy) 
					resultScore = resultScore - THREESTONE;

				if(board[row][col] == enemy && board[row + 1][col - 1] == 0 && board[row + 2][col - 2] == enemy && board[row + 3][col - 3] == enemy) 
					resultScore = resultScore - THREESTONE;

				if(board[row][col] == 0 && board[row + 1][col - 1] == enemy && board[row + 2][col - 2] == enemy && board[row + 3][col - 3] == enemy) 
					resultScore = resultScore - THREESTONE;

				if(board[row][col] == player && board[row + 1][col - 1] == player && board[row + 2][col - 2] == player && board[row + 3][col - 3] == 0) 
					resultScore = resultScore + THREESTONE;

				if(board[row][col] == player && board[row + 1][col - 1] == player && board[row + 2][col - 2] == 0 && board[row + 3][col - 3] == player) 
					resultScore = resultScore + THREESTONE;

				if(board[row][col] == player && board[row + 1][col - 1] == 0 && board[row + 2][col - 2] == player && board[row + 3][col - 3] == player) 
					resultScore = resultScore + THREESTONE;

				if(board[row][col] == 0 && board[row + 1][col - 1] == player && board[row + 2][col - 2] == player && board[row + 3][col - 3] == player) 
					resultScore = resultScore + THREESTONE;

				if(board[row][col] == player && board[row + 1][col - 1] == player && board[row + 2][col - 2] == player && board[row + 3][col - 3] == player)
					return WIN;
				if(board[row][col] == enemy && board[row + 1][col - 1] == enemy && board[row + 2][col - 2] == enemy && board[row + 3][col - 3] == enemy)
					return LOSE;
				
			}
		}

		return resultScore;
	}
	
	//����->���ϴ� �밢���� ���� �� üũ
	public static int checkRLDiagonalScore(int player, int[][] board) {
		int resultScore = 0;
		int enemy = (player == 1)?2:1;

		for(int row = 0;row <= 2;row++) {
			for(int col = 0;col < 4;col++) {

				//�� 1�� ���� �� üũ
				if(board[row][col] == enemy && board[row+1][col+1] == 0 && board[row+2][col+2] == 0 && board[row+3][col+3] == 0)
					resultScore = resultScore - ONESTONE;

				if(board[row][col] == 0 && board[row+1][col+1] == enemy && board[row+2][col+2] == 0 && board[row+3][col+3] == 0) 
					resultScore = resultScore - ONESTONE;

				if(board[row][col] == 0 && board[row+1][col+1] == 0 && board[row+2][col+2] == enemy && board[row+3][col+3] == 0) 
					resultScore = resultScore - ONESTONE;

				if(board[row][col] == 0 && board[row+1][col+1] == 0 && board[row+2][col+2] == 0 && board[row+3][col+3] == enemy) 
					resultScore = resultScore - ONESTONE;

				if(board[row][col] == player && board[row+1][col+1] == 0 && board[row+2][col+2] == 0 && board[row+3][col+3] == 0) 
					resultScore = resultScore + ONESTONE;

				if(board[row][col] == 0 && board[row+1][col+1] == player && board[row+2][col+2] == 0 && board[row+3][col+3] == 0) 
					resultScore = resultScore + ONESTONE;

				if(board[row][col] == 0 && board[row+1][col+1] == 0 && board[row+2][col+2] == player && board[row+3][col+3] == 0) 
					resultScore = resultScore + ONESTONE;

				if(board[row][col] == 0 && board[row+1][col+1] == 0 && board[row+2][col+2] == 0 && board[row+3][col+3] == player) 
					resultScore = resultScore + ONESTONE;

				//�� 2�� ���� �� üũ
				if(board[row][col] == enemy && board[row+1][col+1] == enemy && board[row+2][col+2] == 0 && board[row+3][col+3] == 0)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == 0 && board[row+1][col+1] == enemy && board[row+2][col+2] == enemy && board[row+3][col+3] == 0) 
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == 0 && board[row+1][col+1] == 0 && board[row+2][col+2] == enemy && board[row+3][col+3] == enemy)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == enemy && board[row+1][col+1] == 0 && board[row+2][col+2] == enemy && board[row+3][col+3] == 0)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == 0 && board[row+1][col+1] == enemy && board[row+2][col+2] == 0 && board[row+3][col+3] == enemy)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == enemy && board[row+1][col+1] == 0 && board[row+2][col+2] == 0 && board[row+3][col+3] == enemy)
					resultScore = resultScore - TWOSTONE;

				if(board[row][col] == player && board[row+1][col+1] == player && board[row+2][col+2] == 0 && board[row+3][col+3] == 0)
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == 0 && board[row+1][col+1] == player && board[row+2][col+2] == player && board[row+3][col+3] == 0) 
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == 0 && board[row+1][col+1] == 0 && board[row+2][col+2] == player && board[row+3][col+3] == player) 
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == player && board[row+1][col+1] == 0 && board[row+2][col+2] == player && board[row+3][col+3] == 0) 
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == 0 && board[row+1][col+1] == player && board[row+2][col+2] == 0 && board[row+3][col+3] == player) 
					resultScore = resultScore + TWOSTONE;

				if(board[row][col] == player && board[row+1][col+1] == 0 && board[row+2][col+2] == 0 && board[row+3][col+3] == player)
					resultScore = resultScore + TWOSTONE;

				//�� 3�� ���� �� üũ
				if(board[row][col] == enemy && board[row+1][col+1] == enemy && board[row+2][col+2] == enemy && board[row+3][col+3] == 0) 
					resultScore = resultScore - THREESTONE;

				if(board[row][col] == enemy && board[row+1][col+1] == enemy && board[row+2][col+2] == 0 && board[row+3][col+3] == enemy) 
					resultScore = resultScore - THREESTONE;

				if(board[row][col] == enemy && board[row+1][col+1] == 0 && board[row+2][col+2] == enemy && board[row+3][col+3] == enemy) 
					resultScore = resultScore - THREESTONE;

				if(board[row][col] == 0 && board[row+1][col+1] == enemy && board[row+2][col+2] == enemy && board[row+3][col+3] == enemy) 
					resultScore = resultScore - THREESTONE;

				if(board[row][col] == player && board[row+1][col+1] == player && board[row+2][col+2] == player && board[row+3][col+3] == 0) 
					resultScore = resultScore + THREESTONE;

				if(board[row][col] == player && board[row+1][col+1] == player && board[row+2][col+2] == 0 && board[row+3][col+3] == player) 
					resultScore = resultScore + THREESTONE;

				if(board[row][col] == player && board[row+1][col+1] == 0 && board[row+2][col+2] == player && board[row+3][col+3] == player) 
					resultScore = resultScore + THREESTONE;

				if(board[row][col] == 0 && board[row+1][col+1] == player && board[row+2][col+2] == player && board[row+3][col+3] == player) 
					resultScore = resultScore + THREESTONE;

				if(board[row][col] == player && board[row+1][col+1] == player && board[row+2][col+2] == player && board[row+3][col+3] == player) 
					return WIN;
				if(board[row][col] == enemy && board[row+1][col+1] == enemy && board[row+2][col+2] == enemy && board[row+3][col+3] == enemy) 
					return LOSE;
				
			}
		}

		return resultScore;
	}
}
