package kr.or.ddit.basic;


/*
 * �̱� ������� ��Ƽ �������� �ð� ���� ����
 * 
 * ����) 1���� 20����� �հ踦 ���ϴµ� �ɸ� �ð� üũ
 * ��ü �հ踦 ���ϴ� �۾��� �̱۰� ��Ƽ�� ������ ��, �ð� ���̰� ��ŭ ���°�?
 * */
public class T04ThreadTest {
	public static void main(String[] args) {
		SumThread sm = new SumThread(1L,2000000000L);
		long startTime = System.currentTimeMillis();
		
		sm.start();
		
		try {
			sm.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("ó���ð� : " + (endTime - startTime) + "ms");
		
		System.out.println();
	}
}

class SumThread extends Thread {
	private long min, max;
	
	public SumThread(long min, long max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public void run() {
		long sum = 0;
		for(long i = min; i < max; i++) {
			sum += i;
		}
		System.out.printf("min(%d) ~ max(%d) �հ� : sum(%d)",min,max,sum);
	}
}