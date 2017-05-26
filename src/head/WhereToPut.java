package head;

import java.util.List;

import body.Connect4;
import interfaces.BoardWindow;

public class WhereToPut implements BoardWindow {
	public static int POSITIVE_INFINITY = Integer.MAX_VALUE;
	public static int NEGATIVE_INFINITY = Integer.MIN_VALUE;

	static int z = 0;

	public static int evaluate(int player, int[][] board, int stoneNum, int DIFFICULTY) {
		long startTime = System.currentTimeMillis();
		long endTime;
		/*
		 * @input: ���� �� �������� player�� ����
		 * 
		 * @input: board�� ���� ���� ���¸� argument�� �ް� �̰��� root node�� �����Ѵ�. �׸��� minmax
		 * �˰����� ����
		 * 
		 * @output: ��� column�� �־�� �ϴ����� ��ȯ (0~COLS-1)
		 */
		// Node (Score, int[][] board, Node[7] children) �� ������ ��� ������
		// �̰ɷ� Ʈ���� ��������
		// ���ĺ�Ÿ ������� �ϴ��� DFS�� �� ������ ���� �� ����! �׸��� ���� Ȯ���� �� ������ å���Ѵ�

		int result = -1;
		int depth = 0;

		int switchedPlayer = (player == 1) ? 2 : 1;
		StateNode rootNode = new StateNode(switchedPlayer, board, stoneNum);

		if (DIFFICULTY == Connect4.HIGH) {
			// ���� ������ ���� depth�� ���������ִ� ����
			if (stoneNum >= 26) {
				depth = 14;
				System.out.println("depth" + depth + "�� ����");
			} else if (stoneNum >= 24) {
				depth = 13;
				System.out.println("depth" + depth + "�� ����");
			} else if (stoneNum >= 20) {
				depth = 12;
				System.out.println("depth" + depth + "�� ����");
			} else if (stoneNum >= 16) {
				depth = 11;
				System.out.println("depth" + depth + "�� ����");
			} else if (stoneNum >= 12) {
				depth = 10;
				System.out.println("depth" + depth + "�� ����");
			} else if (stoneNum >= 8) {
				depth = 9;
				System.out.println("depth" + depth + "�� ����");
			} else
				depth = 8;
		} else if (DIFFICULTY == Connect4.MODERATE)
			depth = 5;
		else
			depth = 2;

		// �̱�� �� �˻�
		StateNode testNode1 = new StateNode(switchedPlayer, board, stoneNum);
		testNode1.generateChildren();
		for (StateNode tempChild : testNode1.getChildren()) {
			tempChild.setScore(ScoreChecker.getScore(player, tempChild.getBoard()));
			if (tempChild.getScore() == ScoreChecker.WIN)
				return tempChild.getUpdatedCol();
		}
		// ���� ���� ��ο� ������ ����
		StateNode testNode2 = new StateNode(player, board, stoneNum);
		testNode2.generateChildren();
		for (StateNode tempChild : testNode2.getChildren()) {
			tempChild.setScore(ScoreChecker.getScore(player, tempChild.getBoard()));
			if (tempChild.getScore() == ScoreChecker.LOSE)
				return tempChild.getUpdatedCol();
		}
		// GameTree gt = new GameTree(player, board, stoneNum, depth);
		// //DEPTH��ŭ�� �� ���� ���ٺ��� Ʈ�� ����
		// Minimax(gt);
		// ABPruningMinimax(gt);
		newABPruningMinimax(rootNode, depth);

		// �� ������ ���ϱ� ���� Root Node�� �ٷ� �Ѵܰ� �� �ڽ� ���� �߿��� ���� ū �������� ���� ���� �����Ѵ�.
		if (rootNode.getChildrenNum() != 0) {
			int maxScore = NEGATIVE_INFINITY;
			for (StateNode child : rootNode.getChildren()) {
				if (child.getScore() > maxScore) {
					maxScore = child.getScore();
					result = child.getUpdatedCol();
				} else if (child.getScore() == maxScore) {
					if (child.getScore() == NEGATIVE_INFINITY) {
						maxScore = child.getScore();
						result = child.getUpdatedCol();
					}
				}
			}
		}
		// debug
		System.out.print("LOG: [" + (++z) + "] ");
		for (StateNode haha : rootNode.getChildren()) {
			System.out.print(haha.getScore() + " ");
		}
		System.out.println("\n     Column " + (result + 1) + " Selected");
		endTime = System.currentTimeMillis();
		System.out.println("     elapsed time : " + (endTime - startTime) + "milliseconds");
		return result;
	}

	private static void newABPruningMinimax(StateNode root, int depth) {
		int a = NEGATIVE_INFINITY;
		int b = POSITIVE_INFINITY;
		int player = root.getPlayer();
		int switchedPlayer = (player == 1) ? 2 : 1;
		newabpruningMinimax(root, switchedPlayer, a, b, depth);
	}

	private static int newabpruningMinimax(StateNode parentNode, int player, int a, int b, int depth) {
		// a�� �ڽ� ���鿡�Լ� ���ݱ��� ã�Ƴ� �ּ��� �� (maximizer�� ���忡��)
		// b�� �ڽ� ���鿡�Լ� ���ݱ��� ã�Ƴ� �ּ��� �� (minimizer�� ���忡��)
		// ���� - http://popungpopung.tistory.com/10
		int score;

		if (depth == 0) { // depth�� 0�� �Ǿ��ٸ� �ڱ� �ڽ��� ������ ���� �ش� ������ ���� �ø�
			score = ScoreChecker.getScore(player, parentNode.getBoard());
			parentNode.setScore(score);
			return score;
		} else { // depth�� 0�� �ƴ� ���
			parentNode.generateChildren();

			if (parentNode.getPlayer() == player) {
				/*
				 * ���� �ش� ��尡 �ڽ��� �ξ�� �� ���������, �ڽ� ������ ������ �� ���� ���� ���� ���Ѵ�. (�ڽ�
				 * ����� level�� ��밡 �ξ�� �� �������̱⶧��)
				 */
				for (StateNode child : parentNode.getChildren()) {
					score = newabpruningMinimax(child, player, a, b, depth - 1);
					if (b > score)
						b = score;
					if (b <= a)
						break;
					// a������ b���� Ŀ�� �� ���� ���� �� maximizer�� ���� ���� �� �ִ�.
					// �׷��� ���� ��� ������ �������.
				}
				parentNode.setScore(b);
				return b;

			} else {
				// ���� �ڽ��� �ξ�� �� �������� �ƴ϶��, �ڽĵ� �� ���� ū ���� ���Ѵ�.
				for (StateNode child : parentNode.getChildren()) {
					score = newabpruningMinimax(child, player, a, b, depth - 1);
					if (a < score)
						a = score;
					if (b <= a)
						break;
					// b���� a�� ���� ���� ���;� �ϴµ�, a�� �� �� ū ���� ���� b���� �۾��� �� ����.
				}
				parentNode.setScore(a);
				return a;
			}
		}
	}

	private static void Minimax(GameTree gt) {
		// alpha-beta pruning�� ���� �ܼ� Minimax
		int lastLevel = gt.getDepth();
		List<List<StateNode>> levels = gt.getLevels();
		List<StateNode> leaves = levels.get(lastLevel);
		int player = gt.getPlayer();

		while (leaves == null) {
			leaves = levels.get(--lastLevel);
		}
		// �ڽ� ��尡 �ϳ��� ���� ��츦 ����ؼ� Ʈ���� ������ ���� ���ϴ� �ܰ��̴�.
		// ������ ������ �˻��ؼ� �ƹ� ��嵵 ������ ������ ���� �Ѵܰ� ���� �����Ѵ�.

		/*
		 * if(lastLevel == 0) { //���� ������ ������ rootNode��� ���̻� �� �� ���ٴ� �ǹ��̹Ƿ� �̹�
		 * getWinner()���� draw�� ���̳� ���̹Ƿ� �� �κ��� �����ص� �ȴ�. return ������ ���� column }
		 */

		for (StateNode child : leaves) // �� �Ʒ����� �ڽ� ���鿡�� �������� �ο��Ѵ�.
			child.setScore(ScoreChecker.getScore(player, child.getBoard()));

		for (int i = lastLevel - 1; i > 0; i--) {
			List<StateNode> curLevel = levels.get(i);

			if (!curLevel.isEmpty()) {

				if (curLevel.get(0).getPlayer() == player) {
					// ���� �ش� level�� �ڽ��� �ξ�� �� ���������,
					for (StateNode node : curLevel) {
						if (node.getChildrenNum() != 0)
							minNode(node);
						// �ڽ� ������ ������ �� ���� ���� ���� ���Ѵ�.
						// (�ڽ� ����� level�� ��밡 �ξ�� �� �������̱� ����)
						else // �ڽ��� �ϳ��� ������ �ڱ� �ڽ��� ���ھ ����Ѵ�.
							node.setScore(ScoreChecker.getScore(player, node.getBoard()));

					}
				} else {
					for (StateNode node : curLevel) {
						if (node.getChildrenNum() != 0)
							maxNode(node);
						else // �ڽ��� �ϳ��� ������ �ڱ� �ڽ��� ���ھ ����Ѵ�.
							node.setScore(ScoreChecker.getScore(player, node.getBoard()));

					}
				}
			}
		}

	}

	private static void ABPruningMinimax(GameTree gt) {
		int a = NEGATIVE_INFINITY;
		int b = POSITIVE_INFINITY;
		StateNode root = gt.getRootNode();
		abpruningMinimax(root, gt.getPlayer(), a, b);
	}

	private static int abpruningMinimax(StateNode parentNode, int player, int a, int b) {
		// a�� �ڽ� ���鿡�Լ� ���ݱ��� ã�Ƴ� �ּ��� �� (maximizer�� ���忡��)
		// b�� �ڽ� ���鿡�Լ� ���ݱ��� ã�Ƴ� �ּ��� �� (minimizer�� ���忡��)
		// ���� - http://popungpopung.tistory.com/10
		int score;

		if (parentNode.getChildrenNum() == 0) {
			// �ڽ��� �ϳ��� ���ٸ� �ڱ� �ڽ��� ������ ���� �ش� ������ ���� �ø�
			score = ScoreChecker.getScore(player, parentNode.getBoard());
			parentNode.setScore(score);
			return score;
		} else { // �ڽ��� �ִ� ���
			if (parentNode.getPlayer() == player) {
				// ���� �ش� ��尡 �ڽ��� �ξ�� �� ���������,
				// �ڽ� ������ ������ �� ���� ���� ���� ���Ѵ�.
				// (�ڽ� ����� level�� ��밡 �ξ�� �� �������̱� ����)
				for (StateNode child : parentNode.getChildren()) {
					score = abpruningMinimax(child, player, a, b);
					if (b > score)
						b = score;
					if (b <= a)
						break;
					// a������ b���� Ŀ�� �� ���� ���� �� maximizer�� ���� ���� �� �ִ�.
					// �׷��� ���� ��� ������ �������.
				}
				parentNode.setScore(b);
				return b;

			} else {
				// ���� �ڽ��� �ξ�� �� �������� �ƴ϶��,
				// �ڽĵ� �� ���� ū ���� ���Ѵ�.
				for (StateNode child : parentNode.getChildren()) {
					score = abpruningMinimax(child, player, a, b);
					if (a < score)
						a = score;
					if (b <= a)
						break;
					// b���� a�� ���� ���� ���;� �ϴµ�, a�� �� �� ū ���� ���� b���� �۾��� �� ����.
				}
				parentNode.setScore(a);
				return a;
			}
		}
	}

	public static int[][] duplicateBoard(int[][] board) {
		int[][] newBoard = new int[ROWS][COLS];
		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLS; j++)
				newBoard[i][j] = board[i][j];
		return newBoard;
	}

	public static int[][] putInCol(int[][] board, int player, int col) {
		if (player != 1 && player != 2)
			return null;
		// col�� ������ 0~6�� �ƴ� ��� ������ index outofbound exception�� �߻��� ��

		for (int i = 0; i < ROWS; i++) {
			if (board[i][col] == 0) {
				board[i][col] = player;
				return board;
			}
		}
		return null;
		// ���� �� ���ִ� column�� put���� �� null�� ��ȯ -> alpha-beta pruning�� ����!
	}

	// �ڽ� ����� ������ �߿��� ���� ū ���� ���Ѵ�.
	private static void maxNode(StateNode node) {
		int maxScore = NEGATIVE_INFINITY;

		for (StateNode child : node.getChildren()) {
			if (maxScore <= child.getScore())
				maxScore = child.getScore();
		}
		node.setScore(maxScore);
	}

	// �ڽ� ����� ������ �߿��� ���� ���� ���� ���Ѵ�.
	private static void minNode(StateNode node) {
		int minScore = POSITIVE_INFINITY;

		for (StateNode child : node.getChildren()) {
			if (minScore >= child.getScore())
				minScore = child.getScore();
		}
		node.setScore(minScore);
	}

}