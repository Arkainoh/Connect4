package head;

import java.util.ArrayList;
import java.util.List;

import interfaces.BoardWindow;

public class StateNode implements BoardWindow{
	private int player; //���� �� ����� ��������, �� ���� �ܰ迡�� ���� ���� ���Ҵ����� ����
	private int updatedCol; //�θ𿡼� � Column�� ���� �־������� ����
	private int score;
	private int stoneNum;
	private int[][] board;
	private ArrayList<StateNode> children;
	
	public StateNode(int player, int[][] board, int stoneNum) {
		this.player = player;
		this.updatedCol=-1;
		this.score=0;
		this.board=board;
		this.stoneNum=stoneNum;
		this.children = new ArrayList<StateNode>();
	}
	
	public StateNode(int player, int updatedCol, int[][] board, int stoneNum) {
		this.player = player;
		this.updatedCol=updatedCol;
		this.score=0;
		this.board=board;
		this.stoneNum=stoneNum;
		this.children = new ArrayList<StateNode>();
	}
	
	public int getPlayer() {
		return player;
	}
	public int getUpdatedCol() {
		return updatedCol;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int[][] getBoard() {
		return board;
	}
	public void setBoard(int[][] board) {
		this.board = board;
	}
	public Iterable<StateNode> getChildren() {
		return children;
	}
	public int getChildrenNum() {
		return children.size();
	}
	
	public void switchPlayer() {
		this.player = (this.player==1)?2:1;
	}
	
	
	public List<StateNode> generateChildren() {
		for(int i=0;i<COLS;i++){
			int[][] duplicatedBoard = WhereToPut.duplicateBoard(board);
			int switchedPlayer = (player==1)?2:1;
			int[][] temp = WhereToPut.putInCol(duplicatedBoard, switchedPlayer, i);
			if(temp!=null) {
				StateNode newChild = new StateNode(switchedPlayer,i,temp, stoneNum+1); //���� ��� �����Ҷ� �迭�� ���� �����ϰ� �������� �� ����...
				//newChild.switchPlayer();
				if(ScoreChecker.getWinner(board, stoneNum)==0)
					children.add(newChild);
			}
		}
		return children; //������ child�� ������ ��ȯ
	}
	
	public String toString() {
		//�׽�Ʈ�� ��� �Լ�
		return "Node: [owner: " + player + "/ updated column: " + updatedCol + "]";
	}
		
}