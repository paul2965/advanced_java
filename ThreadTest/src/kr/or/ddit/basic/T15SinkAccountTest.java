package kr.or.ddit.basic;

public class T15SinkAccountTest {
	//은행 입출금을 스레드로 처리하는 예제
	//synchronized를 이용한 동기화 처리
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
	private int balance; //잔액

	public synchronized int getBalance() {
		return balance;
	}
	
	//입금 처리를 수행하는 메서드
	public void deposit(int money) {
		balance += money;
	}
	//출금 처리를 수행하는 메서드(성공 : true, 실패 : false)
	//동기화 영역에서 호출하는 메서드도 동기화 처리를 해주어야 한다.
	public synchronized boolean withdraw(int money) {
		if(balance >= money) {
			//for(int i = 1 ; i <= 1000000000; i++) {}
			balance -= money;
			System.out.println("메서드 안에서 balance = " + getBalance());
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
		System.out.println("스레드 안에서 result = " + result + ", balance = " + sAcc.getBalance());
	}
}
