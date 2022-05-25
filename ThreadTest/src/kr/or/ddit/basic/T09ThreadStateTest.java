package kr.or.ddit.basic;

public class T09ThreadStateTest {
/*
	< �������� ���� >
	
	- new : ������ �����°� ���� state()�� ȣ����� �ʴ� ����
	- runnable : ���� �� �Ǵ� ���� ������ ����
	- blocked : ����ȭ��Ͽ� ���ؼ� �Ͻ� ������ ����(Lock�� Ǯ�������� ��ٸ��� ����)
	- waiting, timed_Waiting : ������ �۾��� ������� �ʾ����� ���� �������� �ʴ� �Ͻ����� ����
	                          timed_Waiting�� �Ͻ����� �ð��� ������ �����
	- terminated : �������� �۾��� ����� ����
*/
	public static void main(String[] args) {
		Thread th = new StatePrintThread(new TargetThread());
		th.start();		
	}
}

// �������� ���¸� ����ϴ� Ŭ����( ��Ŭ������ ������� �ۼ�)
class StatePrintThread extends Thread {
	private Thread targetThread;

	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() {
		// Thread�� ���� ���ϱ�
		Thread.State state = targetThread.getState();
		
		System.out.println("Ÿ�� �������� ���� : state");
		
		if(state == Thread.State.NEW) {
			targetThread.start();
		}
		
		if(state == Thread.State.TERMINATED) {
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class TargetThread extends Thread {
	@Override
	public void run() {
		for(long i = 0; i < 20000000L; i++) {
			//�ð�����
		}
		
		try {
			Thread.sleep(1500);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}