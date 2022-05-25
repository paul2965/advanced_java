package kr.or.ddit.basic;

public class T12TheadStopTest {
	public static void main(String[] args) {
		ThreadStopEx1 th1 = new ThreadStopEx1();
		th1.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//th1.stop(); <-- �ڿ�����, ���� ���Ḧ ������� �ʰ�
		
		//th1.setStop(true); <--�÷��� ���� �̿��Ͽ� ����
		
		ThreadStopEx2 th2 = new ThreadStopEx2();
		th2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		th2.interrupt();
	}
}

class ThreadStopEx1 extends Thread {
	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}

	@Override
	public void run() {
		while(!stop) {
			System.out.println("������ ó�� ��...");
		}
		System.out.println("�ڿ����� ��...");
		System.out.println("���� ����");
	}
}

//interrupt()�� �̿��Ͽ� ������Ŭ���� ���߰� �ϱ�
class ThreadStopEx2 extends Thread {
	@Override
	public void run() {
		//���1. sleep()�̳� join() ���� ������� ��, interrupt()�� ȣ���ϸ� InterruptedException�� �߻��Ѵ�.��
		/*try {
			while(true) {
				System.out.println("������ ó�� ��...");
				Thread.sleep(1);
			}
		} catch (InterruptedException e) {}
			
		System.out.println("�ڿ� ���� ��...");
		System.out.println("������ ����")*/
		
		//���2. interrupt()�� ȣ�� �Ǿ����� �˻��ϱ�
		
		while(true) {
			System.out.println("������ ó�� ��...");
			//�˻���1 => �������� �ν��Ͻ���ü�� �޼��带 �̿��ϴ� ���
			/*if(this.isInterrupted()) {
				System.out.println("�ν��Ͻ��� isInterrupted() ȣ���");
				break;
			}*/
			
			//�˻���2 => �������� static�޼�
			
		}
	}
}