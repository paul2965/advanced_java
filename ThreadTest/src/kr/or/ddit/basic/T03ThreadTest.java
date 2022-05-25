package kr.or.ddit.basic;

public class T03ThreadTest {
	public static void main(String[] args) {
		Thread th = new Thread(new MyRunner());
		//UTC(협정 세계 표준시)를 사용하여 1970년 1월 1일 0시 0분 0초를 기준으로 경과한 시간을 밀리세컨드 단위로 나타낸다.
		long startTime = System.currentTimeMillis();
		
		th.start();
		
		try {
			//현재 진행중인 스레드에서 작업 중인 스레드
			//th 스레드가 종료될 때까지 기다린다.
			th.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과한 시간 : " + (endTime - startTime));
	}
}

//1부터 10억
class MyRunner implements Runnable {

	@Override
	public void run() {
		long sum = 0;
		for(int i = 1 ; i < 1000000000; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
}