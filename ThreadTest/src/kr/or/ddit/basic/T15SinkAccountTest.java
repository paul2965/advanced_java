package kr.or.ddit.basic;

public class T15SinkAccountTest {
	//���� ������� ������� ó���ϴ� ����
	//synchronized�� �̿��� ����ȭ ó��
	public static void main(String[] args) {
		SyncAccount sAcc = new SyncAccount();
		sAcc.deposit(10000);
		
		Thread th1 = new BankThread(sAcc);
		Thread th2 = new BankThread(sAcc);
		
		th1.start();
		th2.start();
	}
}

class SyncAccount {
	private int balance; //�ܾ�

	public synchronized int getBalance() {
		return balance;
	}
	
	//�Ա� ó���� �����ϴ� �޼���
	public void deposit(int money) {
		balance += money;
	}
	//��� ó���� �����ϴ� �޼���(���� : true, ���� : false)
	//����ȭ �������� ȣ���ϴ� �޼��嵵 ����ȭ ó���� ���־�� �Ѵ�.
	public synchronized boolean withdraw(int money) {
		if(balance >= money) {
			//for(int i = 1 ; i <= 1000000000; i++) {}
			balance -= money;
			System.out.println("�޼��� �ȿ��� balance = " + getBalance());
			return true;
		} else {
			return false;
		}
	}
}

class BankThread extends Thread {
	private SyncAccount sAcc;
	
	public BankThread(SyncAccount sAcc) {
		this.sAcc = sAcc;
	}
	
	@Override
	public void run() {
		boolean result = sAcc.withdraw(6000);
		System.out.println("������ �ȿ��� result = " + result + ", balance = " + sAcc.getBalance());
	}
}
