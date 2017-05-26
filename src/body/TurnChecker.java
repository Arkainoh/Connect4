package body;

import head.WhereToPut;

public class TurnChecker implements Runnable {
	Thread th;
	int MYPLAYING;
	int TURN;
	Connect4 obj;

	public TurnChecker(Connect4 obj) {
		this.th = new Thread(this);
		this.obj = obj;
		this.MYPLAYING = obj.getMyplaying();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!Thread.currentThread().isInterrupted()) {
			TURN = obj.getTurn(); // TURN�� �ֽ� ���·� ������Ʈ�Ѵ�.

			if (TURN == MYPLAYING) { // ���� ��� �������� ���� �ΰ� ���� ��ǻ�Ͱ� �� ���ʰ� �Ǿ��� ��
				// System.out.println("minmax computing...");
				int col = WhereToPut.evaluate(MYPLAYING, obj.getBoard(), obj.getStoneNum(), obj.getDifficulty());
				// ��ǻ�Ͱ� �� ������ ��� minmax�˰����� �����Ѵ�.
				obj.put(MYPLAYING, col);
			}
			System.out.print("");
		}
	}

	public void start() {
		th.start();
	}

	public void stop() {
		th.interrupt();
	}
}
