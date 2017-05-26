package body;

import interfaces.BoardWindow;

public class WinnerChecker implements BoardWindow {
	Connect4 obj;
	int[][] board;
	int stone_num;

	public WinnerChecker(Connect4 obj) {
		this.obj = obj;
		this.board = obj.getBoard();
		this.stone_num = obj.getStoneNum();
	}

	public int getWinner() {
		board = obj.getBoard(); // board�� ���¸� ������Ʈ���ش�.

		int winner = 0; // �ʱⰪ�� 0���� ����. 0�� ���� Winner�� �������� �ʾҴٴ� �ǹ�
		winner = getWinner_Row();

		if (winner == 0)
			winner = getWinner_Col();

		if (winner == 0)
			winner = getWinner_Diag_LR();

		if (winner == 0)
			winner = getWinner_Diag_RL();

		if (winner == 0 && isFull())
			return 3; // ���ºθ� ����

		return winner;
	}

	private boolean isFull() { // board�� �� �� �ִ����� �˷���
		stone_num = obj.getStoneNum();
		return (stone_num >= 42);
	}

	private int getWinner_Row() { // ���η� �̰���� �˻�
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (board[i][j] == 1) {
					count1++;
					count2 = 0;
				} else if (board[i][j] == 2) {
					count2++;
					count1 = 0;
				} else {
					count1 = 0;
					count2 = 0;
				}
				if (count1 >= 4)
					return 1;
				if (count2 >= 4)
					return 2;
			}
		}
		return 0;
	}

	private int getWinner_Col() { // ���η� �̰���� �˻�
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < COLS; i++) {
			for (int j = 0; j < ROWS; j++) {
				if (board[j][i] == 1) {
					count1++;
					count2 = 0;
				} else if (board[j][i] == 2) {
					count2++;
					count1 = 0;
				} else {
					count1 = 0;
					count2 = 0;
				}
				if (count1 >= 4)
					return 1;
				if (count2 >= 4)
					return 2;
			}
		}
		return 0;
	}

	private int getWinner_Diag_LR() { // ���� ��� -> ���� �ϴ� �밢��
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < 3; i++) { // �Ʒ��� ����
			// board[i][COLS-1]; //������
			count1 = 0;
			count2 = 0;
			int increment = 0; // ������
			while (i + increment < ROWS && COLS - 1 - increment > 0) {
				int curpoint = board[i + increment][COLS - 1 - increment];
				if (curpoint == 1) {
					count1++;
					count2 = 0;
				} else if (curpoint == 2) {
					count2++;
					count1 = 0;
				} else {
					count1 = 0;
					count2 = 0;
				}
				if (count1 >= 4)
					return 1;
				if (count2 >= 4)
					return 2;
				increment++;
			}
		}

		for (int i = 3; i < COLS - 1; i++) { // �¿��� ���
			// board[0][i]; //������
			count1 = 0;
			count2 = 0;
			int increment = 0; // ������
			while (increment < ROWS && i - increment > 0) {
				int curpoint = board[increment][i - increment];
				if (curpoint == 1) {
					count1++;
					count2 = 0;
				} else if (curpoint == 2) {
					count2++;
					count1 = 0;
				} else {
					count1 = 0;
					count2 = 0;
				}
				if (count1 >= 4)
					return 1;
				if (count2 >= 4)
					return 2;
				increment++;
			}
		}
		return 0;
	}

	private int getWinner_Diag_RL() { // ���� ��� -> ���� �ϴ� �밢��
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < 3; i++) { // �Ʒ��� ����
			// board[i][0]; //start point
			count1 = 0;
			count2 = 0;
			int increment = 0; // ������
			while (i + increment < ROWS && increment < COLS) {
				int curpoint = board[i + increment][increment]; // ���� ���õ� ��
				if (curpoint == 1) {
					count1++;
					count2 = 0;
				} else if (curpoint == 2) {
					count2++;
					count1 = 0;
				} else {
					count1 = 0;
					count2 = 0;
				}
				if (count1 >= 4)
					return 1;
				if (count2 >= 4)
					return 2;
				increment++;
			}
		}

		for (int i = 1; i <= 3; i++) { // �¿��� ���
			// board[0][i]; //start point
			count1 = 0;
			count2 = 0;
			int increment = 0; // ������
			while (increment < ROWS && increment + i < COLS) {
				int curpoint = board[increment][increment + i]; // ���� ���õ� ��
				if (curpoint == 1) {
					count1++;
					count2 = 0;
				} else if (curpoint == 2) {
					count2++;
					count1 = 0;
				} else {
					count1 = 0;
					count2 = 0;
				}
				if (count1 >= 4)
					return 1;
				if (count2 >= 4)
					return 2;
				increment++;
			}
		}

		return 0;
	}
}
