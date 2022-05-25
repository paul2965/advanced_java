package kr.or.ddit.basic;

public class T08DaemonThread {
	/*
	 * ���̸� ������
	 * ���̸� : ����ڰ� ���������� �������� �ʰ�, ��׶��忡�� ���鼭 �̸� �۾��� �ϴ� ���α׷�
	 * ���̸� ������ : �Ϲ� �����尡 ����ɶ� ��׶��忡�� �۾����� ������(�Ϲ� ��������̴� �ǹ̰� ����)
	*/
	public static void main(String[] args) {
		Thread autoSaveThread = new AutoSaveThread();
		
		//���̸վ������ �����ϱ� start�޼��� ȣ�� �� ����
		//setDaemon�� default�� = false;
		autoSaveThread.setDaemon(true); 
		
		autoSaveThread.start();
		
		try {
			for(int i = 1; i <= 20; i++) {
				System.out.println("�۾�" + i);
				Thread.sleep(1000);
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("���� ������ ����");
	}
}

/* �ڵ� ���� ����� �����ϴ� ������(3�ʿ� �ѹ� ����)*/
class AutoSaveThread extends Thread {
	public void save() {
		System.out.println("�۾� ������ �����մϴ�.");		
	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
			save();
		}
	}
}
