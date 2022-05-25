package kr.or.ddit.basic;

public class T08DaemonThread {
	/*
	 * 데이먼 쓰레드
	 * 데이먼 : 사용자가 직접적으로 제어하지 않고, 백그라운드에서 돌면서 미리 작업을 하는 프로그램
	 * 데이먼 쓰레드 : 일반 쓰레드가 실행될때 백그라운드에서 작업중인 쓰레드(일반 쓰레드없이는 의미가 없다)
	*/
	public static void main(String[] args) {
		Thread autoSaveThread = new AutoSaveThread();
		
		//데이먼쓰레드로 설정하기 start메서드 호출 전 설정
		//setDaemon의 default값 = false;
		autoSaveThread.setDaemon(true); 
		
		autoSaveThread.start();
		
		try {
			for(int i = 1; i <= 20; i++) {
				System.out.println("작업" + i);
				Thread.sleep(1000);
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("메인 쓰레드 종료");
	}
}

/* 자동 저장 기능을 제공하는 쓰레드(3초에 한번 저장)*/
class AutoSaveThread extends Thread {
	public void save() {
		System.out.println("작업 내용을 저장합니다.");		
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
