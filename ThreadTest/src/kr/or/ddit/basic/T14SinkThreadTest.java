package kr.or.ddit.basic;

public class T14SinkThreadTest {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		Thread th1 = new WorkThread("1��", sObj);
		Thread th2 = new WorkThread("2��", sObj);
		
		th1.start();
		th2.start();
	}
}

//�������� ����� ��ü
class ShareObject {
	private int sum = 0;
	
	//����ȭ ���1. �޼�����ü�� ����ȭ �����ϱ�
	public /*synchronized*/ void add() {
		for(int i = 0; i <1000000000; i++) {
			//����ȭó�� ������ �ð������
		}
		
		//����ȭ ���2. ����ȭ ���� �����ϱ� => 1,2�� ������ ���鼭 
		//synchronized (this) {			
			int n = sum;
			n += 10;
			sum = n;
			
			System.out.println(Thread.currentThread().getName() + 
					", �հ� : " + sum);
		//}
	}
}

//�۾��� �����ϴ� �޼���
class WorkThread extends Thread {
	private ShareObject sObj;
	
	public WorkThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	} 
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			synchronized (sObj) {				
				sObj.add();
			}
		}
	}
}
