package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * ������ ������� ������� ó���ϴ� ����
 * (LOCK ��ü�� �̿��� ����ȭ ó��)*/
public class T16LockAccountTest {
	/*
	 * lock ����� �����ϴ� Ŭ����
	 * - ReentrantLock : Read �� Write ���о��� ����ϱ� ���� �� Ŭ������
	 *                   ����ȭ ó���� ���� ���ȴ�. Synchronized�� �̿���
	 *                   ����ȭ ó������ �ΰ����� ����� �����ȴ�.
	 *                   ex) Fairness ���� ��...
	 *                   => ���� ���� ��ٸ� �����尡 ���� ���� ���� ȹ����*/
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock(true);
		
		LockAccount lAcc = new LockAccount(lock);
		lAcc.deposit(10000);
		
		Thread th1 = new BankThread2(lAcc);
		Thread th2 = new BankThread2(lAcc);
		
		th1.start();
		th2.start();
	}
}

//������� ����ϴ� Ŭ����
class LockAccount {
	private int balance;
	
	//lock��ü ���� => �ǵ����̸� private final�� �����
	private final Lock lock;
	
	public LockAccount(Lock lock) {
		this.lock = lock;
	}
	
	public int getBalance() {
		return balance;
	}
	
	//�Ա� �޼���
	public void deposit(int money) {
		//Lock��ü�� lock() �� ����ȭ �����̰�, unlock() �޼��尡 ����ȭ�� ���� 
		//��Ÿ����. lock()���� ����ȭ�� ������ �������� �ݵ�� unlock() �޼���� �����������
		
		lock.lock(); //�� ����
		balance += money; //�ܾ� + �Աݾ�
		lock.unlock(); //�� ����
	}
	
	public boolean withdraw(int money) {
		boolean check = false;
		
		lock.lock();
		
		try {
			if(balance >= money) {
				for (int i = 1; i<=100000000; i++) {}
				balance -= money;
				System.out.println("�޼��� �ȿ��� balance = " + getBalance());
				check = true;
			} 		
		} catch (Exception e) {
			check = false;
		} finally {
			// try~catch������ ����� ��쿡�� unlock ȣ���� finally ��Ͽ��� �ؾ��Ѵ�.
			lock.unlock();			
		}

		
		return check;
	}
}

class BankThread2 extends Thread {
	private LockAccount lAcc;

	public BankThread2(LockAccount lAcc) {
		this.lAcc = lAcc;
	}
	
	@Override
	public void run() {
		boolean result = lAcc.withdraw(6000);
		System.out.println(Thread.currentThread().getName() + 
				", ������ �ȿ��� result = " + result
				+ ", balance = " + lAcc.getBalance());
	}
}