package kr.or.ddit.basic;

public class T11ThreadYieldTest {
	/*
	 * yield()
	 * ���� ���� ������� ������ �켱���� �̻��� �ٸ� �����忡�� �����ȸ��
	 * �����Ѵ�(�纸)
	 * 
	 * ���� �������� �������� ���¸� Runnable���·� �ٲ۴�
	 * 
	 * yield()�޼��带 �����Ѵٰ� �ؼ� ���� �������� �����尡 ��ٷ� Runnable
	 * ���·� �ٲ�ٰ� Ȯ���� �� ����. */
	public static void main(String[] args) {
		Thread th1 = new YieldThreadEx1();
		Thread th2 = new YieldThreadEx1();
		
		th1.start();
		th2.start();
	}
}

class YieldThreadEx1 extends Thread {
	public YieldThreadEx1() {
		super("�纸 ������");
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName()+" : "  + i);
			Thread.yield();
		}
	}
}