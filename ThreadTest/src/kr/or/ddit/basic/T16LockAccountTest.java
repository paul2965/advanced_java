package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 은행의 입출금을 스레드로 처리하는 에제
 * (LOCK 객체를 이용한 동기화 처리)*/
public class T16LockAccountTest {
	/*
	 * lock 기능을 제공하는 클래스
	 * - ReentrantLock : Read 및 Write 구분없이 사용하기 위한 락 클래스로
	 *                   동기화 처리를 위해 사용된다. Synchronized를 이용한
	 *                   동ㄱ화 처리보다 부가적인 기능이 제공된다.
	 *                   ex) Fairness 설정 등...
	 *                   => 가장 오래 기다린 스레드가 가장 먼저 락을 획득함*/
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

//입출금을 담당하는 클래스
class LockAccount {
	private int balance;
	
	//lock객체 생성 => 되도록이면 private final로 만든다
	private final Lock lock;
	
	public LockAccount(Lock lock) {
		this.lock = lock;
	}
	
	public int getBalance() {
		return balance;
	}
	
	//입금 메서드
	public void deposit(int money) {
		//Lock객체으 lock() 가 동기화 시작이고, unlock() 메서드가 동기화의 끝을 
		//나타낸다. lock()으로 동기화를 설정한 곳에서는 반드시 unlock() 메서드로 해제해줘야함
		
		lock.lock(); //락 설정
		balance += money; //잔액 + 입금액
		lock.unlock(); //락 해제
	}
	
	public boolean withdraw(int money) {
		boolean check = false;
		
		lock.lock();
		
		try {
			if(balance >= money) {
				for (int i = 1; i<=100000000; i++) {}
				balance -= money;
				System.out.println("메서드 안에서 balance = " + getBalance());
				check = true;
			} 		
		} catch (Exception e) {
			check = false;
		} finally {
			// try~catch문에서 사용할 경우에는 unlock 호출은 finally 블록에서 해야한다.
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
				", 스레드 안에서 result = " + result
				+ ", balance = " + lAcc.getBalance());
	}
}