package kr.or.ddit.basic;

public class T03ThreadTest {
	public static void main(String[] args) {
		Thread th = new Thread(new MyRunner());
		//UTC(���� ���� ǥ�ؽ�)�� ����Ͽ� 1970�� 1�� 1�� 0�� 0�� 0�ʸ� �������� ����� �ð��� �и������� ������ ��Ÿ����.
		long startTime = System.currentTimeMillis();
		
		th.start();
		
		try {
			//���� �������� �����忡�� �۾� ���� ������
			//th �����尡 ����� ������ ��ٸ���.
			th.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("����� �ð� : " + (endTime - startTime));
	}
}

//1���� 10��
class MyRunner implements Runnable {

	@Override
	public void run() {
		long sum = 0;
		for(int i = 1 ; i < 1000000000; i++) {
			sum += i;
		}
		System.out.println("�հ� : " + sum);
	}
}