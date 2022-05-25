package kr.or.ddit.basic;

/*
 * ��Ƽ������ ���α׷�
 * */
public class T02ThreadTest {
	public static void main(String[] args) {
		// ���1. Thread Ŭ������ ����� class�� �ν��Ͻ��� ������ ��
		//       �� �ν��Ͻ��� start() �޼��带 ȣ���Ѵ�.
		MyThread1 mythread1 = new MyThread1();
		//�����带 ����ϱ� ���ؼ� start() �޼��尡 �ʿ��ϴ�.
		mythread1.start();
		
		// ��� 2. runnable() �������̽��� ���q�� Ŭ������ ��Ʈ�Ͻ�ȭ ���� ��,
		//        �� �ν��Ͻ��� Thread��ü�� �ν��Ͻ��� ������ �� �������� �Ű������� �Ѱ��ش�.
		//        �̶�, ������ �� �������� �Ű������� �Ѱ��ش�.
		//
		
		// ��� 3. ������ �͸�ü�� �̿��ϴ� ���
		
	}
}

class MyThread1 extends Thread {
	@Override
	public void run() {
		// ������ �ؾ��� ���� �κ��� �ִ� �κ�
		for(int i = 1 ; i < 20 ; i++) {
			System.out.println("*");
			
			try {
				//Thread.sleep(�ð�); 1000 => 1�� 
				//�־��� �ð����� ��� �۾��� �����.
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}